package com.github.nicolasperuch.service;

import com.github.nicolasperuch.api.dto.VoteResponse;
import com.github.nicolasperuch.client.RestClient;
import com.github.nicolasperuch.client.dto.CpfValidationResponse;
import com.github.nicolasperuch.entity.RulingStatusEntity;
import com.github.nicolasperuch.model.VoteModel;
import com.github.nicolasperuch.repository.RulingStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VoteService {

    @Autowired
    private RestClient restClient;
    @Autowired
    private RulingStatusRepository rulingStatusRepository;
    private final String ABLE_TO_VOTE = "ABLE_TO_VOTE";
    private final String INVALID_CPF_MESSAGE = "Your cpf is not able to vote";
    private final String VOTE_COMPUTED_MESSAGE = "Vote succesfully computed";
    private final String UNVAILABLE_RULING = "Ruling not available";

    public VoteResponse computeVote(VoteModel voteModel){
        Optional<RulingStatusEntity> rulingStatusEntity = rulingStatusRepository.findByRulingId(voteModel.getRulingId());
        if(!isRulingOpenForVote(rulingStatusEntity)){
            return new VoteResponse(UNVAILABLE_RULING);
        }
        if(!isUserAbleToVote(voteModel.getUserCpf())){
            return new VoteResponse(INVALID_CPF_MESSAGE);
        }
        //save vote into database
        return new VoteResponse(VOTE_COMPUTED_MESSAGE);
    }

    public boolean isRulingOpenForVote(Optional<RulingStatusEntity> rulingStatusEntity){
        return rulingStatusEntity.isPresent() && !(rulingStatusEntity.get().isRulingFinished());
    }

    public boolean isUserAbleToVote(String cpf) {
        HttpEntity<CpfValidationResponse> response = restClient.checkIfCpfIsAbleToVote(cpf);
        return isStatusAbleToVote(response.getBody().getStatus());
    }

    public boolean isStatusAbleToVote(String status){
        return status.equalsIgnoreCase(ABLE_TO_VOTE);
    }
}
