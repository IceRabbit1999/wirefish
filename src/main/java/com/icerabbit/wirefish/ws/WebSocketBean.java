package com.icerabbit.wirefish.ws;

import lombok.Data;
import org.springframework.web.socket.WebSocketSession;

/**
 * @Author iceRabbit
 * @Date 7/22/22 12:05 PM
 **/
@Data
public class WebSocketBean {

    private WebSocketSession session;
    private String id;
    private String data;

}
