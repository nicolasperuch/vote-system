package com.github.nicolasperuch.service;


import com.github.nicolasperuch.api.dto.VoteResponse;
import com.github.nicolasperuch.client.RestClient;
import com.github.nicolasperuch.client.dto.CpfValidationResponse;
import com.github.nicolasperuch.entity.RulingStatusEntity;
import com.github.nicolasperuch.entity.RulingVoteEntity;
import com.github.nicolasperuch.model.VoteModel;
import com.github.nicolasperuch.repository.RulingStatusRepository;
import com.github.nicolasperuch.repository.RulingVoteRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static junit.framework.TestCase.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.OK;

@RunWith(MockitoJUnitRunner.class)
public class VoteServiceTest {

    @InjectMocks
    private VoteService voteService;
    @Mock
    private RestClient restClient;
    @Mock
    private RulingStatusRepository rulingStatusRepository;
    @Mock
    private RulingVoteRepository rulingVoteRepository;
    private final String UNABLE_TO_VOTE = "UNABLE_TO_VOTE";
    private final String ABLE_TO_VOTE = "ABLE_TO_VOTE";
    private final String EXPECTED_VOTE_COMPUTED_MESSAGE = "Vote succesfully computed";
    private final String EXPECTED_UNVAILABLE_RULING_MESSAGE = "Ruling not available";


    @Test
    public void computeVoteWhenRulingIsNotOpenForVoteThenShouldReturnAnUnavailableRulingMessage(){
        VoteResponse voteResponse = voteService.computeVote(new VoteModel());
        assertEquals(EXPECTED_UNVAILABLE_RULING_MESSAGE, voteResponse.getMessage());
    }

    @Test
    public void isUserAbleToVoteWhenCpfIsValidThenShouldReturnTrue(){
        String validCpf = "26485558705";
        CpfValidationResponse cpfResponse = new CpfValidationResponse();
        cpfResponse.setStatus(ABLE_TO_VOTE);
        ResponseEntity<CpfValidationResponse> responseEntity = new ResponseEntity<>(cpfResponse, OK);
        when(restClient.checkIfCpfIsAbleToVote(anyString()))
                .thenReturn(responseEntity);
        assertTrue(voteService.isUserAbleToVote(validCpf));
    }

    @Test
    public void isUserAbleToVoteWhenCpfIsInvalidThenShouldReturnFalse(){
        String invalidCpf = "26481908222";
        CpfValidationResponse cpfResponse = new CpfValidationResponse();
        cpfResponse.setStatus(UNABLE_TO_VOTE);
        ResponseEntity<CpfValidationResponse> responseEntity = new ResponseEntity<>(cpfResponse, OK);
        when(restClient.checkIfCpfIsAbleToVote(anyString()))
            .thenReturn(responseEntity);
        assertFalse(voteService.isUserAbleToVote(invalidCpf));
    }

    @Test
    public void saveVoteWhenDatabaseSavesSuccesfullyThenShouldReturnVoteResponseWithSuccessMessage(){
        RulingVoteEntity rulingVoteEntity = new RulingVoteEntity();
        when(rulingVoteRepository.save(any(RulingVoteEntity.class)))
            .thenReturn(rulingVoteEntity);
        VoteResponse voteResponse = voteService.saveVote(new VoteModel());
        assertEquals(EXPECTED_VOTE_COMPUTED_MESSAGE, voteResponse.getMessage());
    }

    @Test
    public void hasUserAlreadyVotedInThisRulingWhenDatabaseReturnsFalseThenShouldReturnFalse(){
        when(rulingVoteRepository.existsByRulingIdAndUserId(anyInt(), anyInt()))
                .thenReturn(false);
        assertFalse(voteService.hasUserAlreadyVotedInThisRuling(1, 1));
    }

    @Test
    public void hasUserAlreadyVotedInThisRulingWhenDatabaseReturnsTrueThenShouldReturnTrue(){
        when(rulingVoteRepository.existsByRulingIdAndUserId(anyInt(), anyInt()))
            .thenReturn(true);
        assertTrue(voteService.hasUserAlreadyVotedInThisRuling(1, 1));
    }

    @Test
    public void modelToEntityWhenModelHasAllFieldsThenShouldReturnAnEntityWithTheSameFieldsAndIdNull(){
        VoteModel expectedVoteModel = buildExpectedVoteModel();
        RulingVoteEntity voteEntity = voteService.modelToEntity(expectedVoteModel);
        assertEquals(expectedVoteModel.getRulingId(), voteEntity.getRulingId());
        assertEquals(expectedVoteModel.getUserId(), voteEntity.isUserId());
        assertEquals(expectedVoteModel.isInFavor(), voteEntity.isInFavor());
        assertNull(voteEntity.getId());
    }

    @Test
    public void isStatusAbleToVoteWhenStatusIsUnableToVoteThenShouldReturnFalse(){
        assertFalse(voteService.isStatusAbleToVote(UNABLE_TO_VOTE));
    }

    @Test
    public void isStatusAbleToVoteWhenStatusIsAbleToVoteThenShouldReturnTrue(){
        assertTrue(voteService.isStatusAbleToVote(ABLE_TO_VOTE));
    }

    @Test
    public void isRulingOpenForVoteWhenRulingStatusEntityIsEmptyThenShouldReturnFalse(){
        Optional<RulingStatusEntity> rulingStatusEntity = Optional.empty();
        assertFalse(voteService.isRulingOpenForVote(rulingStatusEntity));
    }

    @Test
    public void isRulingOpenForVoteWhenRulingFinishedIsTrueThenShouldReturnFalse(){
        RulingStatusEntity rulingStatusEntity = new RulingStatusEntity();
        rulingStatusEntity.setRulingFinished(true);
        Optional<RulingStatusEntity> optionalRulingStatusEntity = Optional.of(rulingStatusEntity);
        assertFalse(voteService.isRulingOpenForVote(optionalRulingStatusEntity));
    }

    @Test
    public void isRulingOpenForVoteWhenRulingFinishedIsFalseThenShouldReturnTrue(){
        RulingStatusEntity rulingStatusEntity = new RulingStatusEntity();
        rulingStatusEntity.setRulingFinished(false);
        Optional<RulingStatusEntity> optionalRulingStatusEntity = Optional.of(rulingStatusEntity);
        assertTrue(voteService.isRulingOpenForVote(optionalRulingStatusEntity));
    }

    public VoteModel buildExpectedVoteModel(){
        return new VoteModel()
                .setInFavor(true)
                .setRulingId(1)
                .setUserId(4)
                .setUserCpf("26485558705");
    }
}
