package org.flyfill.controller;

import javax.inject.Inject;
import javax.websocket.OnClose;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.flyfill.service.RequestDispatcher;

/**
 * Websocket API that receives connections from the browser extension
 */
@ServerEndpoint("/ws/queue/{id}")
public class SessionHandler {

    @Inject
    RequestDispatcher requestDispatcher;

    @OnOpen
    public void onOpen(Session session, @PathParam("id") String id) {
        requestDispatcher.openSession(id, session);
    }

    @OnClose
    public void onClose(Session session, @PathParam("id") String id) {
        requestDispatcher.closeSession(id);
    }
}
