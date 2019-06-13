package com.github.nicolasperuch.api;

import com.github.nicolasperuch.api.dto.VoteDto;
import com.github.nicolasperuch.api.exception.handler.ExceptionHandlerApi;
import com.github.nicolasperuch.model.VoteModel;
import com.github.nicolasperuch.service.VoteService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Stream;

@RestController
@RequestMapping("vote")
public class VoteApi extends ExceptionHandlerApi {

    @Autowired
    private VoteService voteService;

    @ApiOperation(value = "Vote for a specific ruling")
    @PostMapping("{rulingId}")
    public ResponseEntity<?> voteForASpecificRuling(@PathVariable("rulingId") Long rulingId,
                                                    @RequestBody VoteDto voteDto){
        return Stream
                .of(buildVoteModel(rulingId, voteDto))
                .map(model -> voteService.computeVote(model))
                .map(ResponseEntity::ok)
                .findFirst()
                .get();
    }

    public VoteModel buildVoteModel(Long rulingId, VoteDto voteDto){
        return new VoteModel()
                    .setRulingId(rulingId)
                    .setUserId(voteDto.getUserId())
                    .setUserCpf(voteDto.getUserCpf())
                    .setInFavor(voteDto.isInFavor());
    }
}
