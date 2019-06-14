package com.github.nicolasperuch.service;

import com.github.nicolasperuch.entity.RulingStatusEntity;
import com.github.nicolasperuch.model.OpenRulingForVoteModel;
import com.github.nicolasperuch.repository.RulingStatusRepository;
import com.google.gson.Gson;
import org.springframework.amqp.core.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SessionStartedService {

    @Autowired
    private Gson gson;
    @Autowired
    private RulingStatusRepository rulingStatusRepository;

    public void openRulingForVote(Message message){
        byte[] messageAsBytes = transformMessageToBytes(message);
        String messageAsJson = transformBytesToJson(messageAsBytes);
        OpenRulingForVoteModel messageAsModel = transformJsonToModel(messageAsJson);
        RulingStatusEntity rulingStatusEntity = buildEntity(messageAsModel);
        rulingStatusRepository.save(rulingStatusEntity);
    }

    public byte[] transformMessageToBytes(Message message){
        return message.getBody();
    }

    public String transformBytesToJson(byte[] bytes){
        return new String(bytes);
    }

    public OpenRulingForVoteModel transformJsonToModel(String json){
        return gson.fromJson(json, OpenRulingForVoteModel.class);
    }

    public RulingStatusEntity buildEntity(OpenRulingForVoteModel openRulingForVoteModel){
        RulingStatusEntity rulingStatusEntity = new RulingStatusEntity();
        rulingStatusEntity.setOpenForVote(true);
        rulingStatusEntity.setRulingId(openRulingForVoteModel.getRulingId());
        rulingStatusEntity.setRulingFinished(false);
        return rulingStatusEntity;
    }
}
