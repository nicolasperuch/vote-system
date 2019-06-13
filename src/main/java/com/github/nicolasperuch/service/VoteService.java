package com.github.nicolasperuch.service;

import com.github.nicolasperuch.api.dto.VoteResponse;
import com.github.nicolasperuch.client.RestClient;
import com.github.nicolasperuch.client.dto.CpfValidationResponse;
import com.github.nicolasperuch.model.VoteModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;

@Service
public class VoteService {

    @Autowired
    private RestClient restClient;
    private final String ABLE_TO_VOTE = "ABLE_TO_VOTE";
    private final String INVALID_CPF_MESSAGE = "Your cpf is not able to vote";
    private final String VOTE_COMPUTED_MESSAGE = "Vote succesfully computed";

    public VoteResponse computeVote(VoteModel voteModel){
        //isRulingOpenForVote?
        if(!isUserAbleToVote(voteModel.getUserCpf())){
            return new VoteResponse(INVALID_CPF_MESSAGE);
        }
        //save vote into database
        return new VoteResponse(VOTE_COMPUTED_MESSAGE);
    }

    public boolean isUserAbleToVote(String cpf) {
        HttpEntity<CpfValidationResponse> response = restClient.checkIfCpfIsAbleToVote(cpf);
        return isStatusAbleToVote(response.getBody().getStatus());
    }

    public boolean isStatusAbleToVote(String status){
        return status.equalsIgnoreCase(ABLE_TO_VOTE);
    }
}
