package com.github.nicolasperuch.service;

import com.github.nicolasperuch.api.dto.VoteResponse;
import com.github.nicolasperuch.model.VoteModel;
import org.springframework.stereotype.Service;

@Service
public class VoteService {

    public VoteResponse computeVote(VoteModel voteModel){
        //save vote into database
        return new VoteResponse("Vote succesfully computed");
    }
}
