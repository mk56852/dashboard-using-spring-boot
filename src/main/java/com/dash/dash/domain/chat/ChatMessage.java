package com.dash.dash.domain.chat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Id;
import java.util.Date;


@AllArgsConstructor
@Getter
@Setter
public class ChatMessage {

    @Id
    private String id;
    private String senderName;
    private String recipientName;
    private String content;
    private Date timestamp;
    private MessageStatus status;
}


