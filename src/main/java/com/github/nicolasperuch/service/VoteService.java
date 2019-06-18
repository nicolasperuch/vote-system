package com.github.nicolasperuch.service;

import com.github.nicolasperuch.api.dto.VoteResponse;
import com.github.nicolasperuch.client.RestClient;
import com.github.nicolasperuch.client.dto.CpfValidationResponse;
import com.github.nicolasperuch.entity.RulingStatusEntity;
import com.github.nicolasperuch.entity.RulingVoteEntity;
import com.github.nicolasperuch.model.VoteModel;
import com.github.nicolasperuch.repository.RulingStatusRepository;
import com.github.nicolasperuch.repository.RulingVoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.logging.Logger;

@Service
public class VoteService {

    @Autowired
    private RestClient restClient;
    @Autowired
    private RulingStatusRepository rulingStatusRepository;
    @Autowired
    private RulingVoteRepository rulingVoteRepository;
    static Logger log = Logger.getLogger(VoteService.class.getName());
    private final String ABLE_TO_VOTE = "ABLE_TO_VOTE";
    private final String INVALID_CPF_MESSAGE = "Your cpf is not able to vote";
    private final String VOTE_COMPUTED_MESSAGE = "Vote succesfully computed";
    private final String UNVAILABLE_RULING_MESSAGE = "Ruling not available";
    private final String USER_CANT_VOTE_TWICE_MESSAGE = "You can not vote twice in the same ruling";

    public VoteResponse computeVote(VoteModel voteModel){
        log.info("Request for compute vote: " + voteModel.toString());
        Optional<RulingStatusEntity> rulingStatusEntity = rulingStatusRepository.findByRulingId(voteModel.getRulingId());
        if(!isRulingOpenForVote(rulingStatusEntity)){
            log.info("RESPONSE: " + UNVAILABLE_RULING_MESSAGE);
            return new VoteResponse(UNVAILABLE_RULING_MESSAGE);
        }
        if(!isUserAbleToVote(voteModel.getUserCpf())){
            log.info("RESPONSE: " + INVALID_CPF_MESSAGE);
            return new VoteResponse(INVALID_CPF_MESSAGE);
        }
        if(hasUserAlreadyVotedInThisRuling(voteModel.getRulingId(), voteModel.getUserId())){
            log.info("RESPONSE: " + USER_CANT_VOTE_TWICE_MESSAGE);
            return new VoteResponse(USER_CANT_VOTE_TWICE_MESSAGE);
        }
        return saveVote(voteModel);
    }

    public VoteResponse saveVote(VoteModel voteModel) {
        RulingVoteEntity rulingVoteEntity = modelToEntity(voteModel);
        log.info("Saving vote into database");
        rulingVoteRepository.save(rulingVoteEntity);
        log.info("RESPONSE: " + VOTE_COMPUTED_MESSAGE);
        return new VoteResponse(VOTE_COMPUTED_MESSAGE);
    }

    public RulingVoteEntity modelToEntity(VoteModel voteModel){
        log.info("Building entity from model");
        log.info(voteModel.toString());
        RulingVoteEntity rulingVoteEntity = new RulingVoteEntity();
        rulingVoteEntity.setUserId(voteModel.getUserId());
        rulingVoteEntity.setRulingId(voteModel.getRulingId());
        rulingVoteEntity.setInFavor(voteModel.isInFavor());
        log.info(rulingVoteEntity.toString());
        return rulingVoteEntity;
    }

    public boolean isRulingOpenForVote(Optional<RulingStatusEntity> rulingStatusEntity){
        log.info("Checking if ruling is open for vote");
        return rulingStatusEntity.isPresent() && !(rulingStatusEntity.get().isRulingFinished());
    }

    public boolean isUserAbleToVote(String cpf) {
        log.info("Checking if user is able to vote");
        HttpEntity<CpfValidationResponse> response = restClient.checkIfCpfIsAbleToVote(cpf);
        return isStatusAbleToVote(response.getBody().getStatus());
    }

    public boolean hasUserAlreadyVotedInThisRuling(Integer rulingId, Integer userId){
        log.info("Checking if user is trying to vote twice on the same ruling");
        return rulingVoteRepository.existsByRulingIdAndUserId(rulingId, userId);
    }

    public boolean isStatusAbleToVote(String status){
        return status.equalsIgnoreCase(ABLE_TO_VOTE);
    }
}