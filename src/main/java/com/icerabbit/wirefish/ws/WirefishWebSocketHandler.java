package com.icerabbit.wirefish.ws;

import com.icerabbit.wirefish.service.WebSSH;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author iceRabbit
 * @Date 7/22/22 2:15 PM
 **/
@Slf4j
public class WirefishWebSocketHandler implements WebSocketHandler {

    @Autowired
    WebSSH wirefish;

    private static Map<String, WebSocketBean> wsMap = new HashMap<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        log.info("websocket connected");
        WebSocketBean webSocketBean = new WebSocketBean();
        webSocketBean.setSession(session);
        webSocketBean.setId(session.getId());
        wsMap.put(webSocketBean.getId(), webSocketBean);
    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        log.info("Received message from:" + wsMap.get(session.getId()) + ", Content is [" + message.getPayload());
        TextMessage textMessage = new TextMessage("Server has received your message.");
        session.sendMessage(textMessage);
    }



    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        log.error(exception.getMessage());
        session.close();
        wsMap.remove(session.getId());
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        log.info("websocket disconnected");
        wsMap.remove(session.getId());
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }
}
