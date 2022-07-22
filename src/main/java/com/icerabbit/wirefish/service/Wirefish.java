package com.icerabbit.wirefish.service;

import com.icerabbit.wirefish.ws.WebSocketBean;
import org.springframework.web.socket.WebSocketSession;

/**
 * @Author iceRabbit
 * @Date 7/22/22 2:22 PM
 **/
public interface Wirefish {

    void init(WebSocketSession session);

    void handleData(WebSocketSession session, WebSocketBean bean);

    void sendData(WebSocketSession session, WebSocketBean bean);

    void close(WebSocketSession session);

}
