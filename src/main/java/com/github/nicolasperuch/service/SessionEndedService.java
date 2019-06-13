package com.github.nicolasperuch.service;

import com.github.nicolasperuch.model.OpenRulingForVoteModel;
import com.google.gson.Gson;
import org.springframework.amqp.core.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SessionEndedService {

    @Autowired
    private Gson gson;

    public void closeRuling(Message message){
        byte[] messageAsBytes = transformMessageToBytes(message);
        String messageAsJson = transformBytesToJson(messageAsBytes);
        OpenRulingForVoteModel messageAsModel = transformJsonToModel(messageAsJson);
        System.out.println(messageAsModel.toString());
        //disabling for voting into database
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
}
