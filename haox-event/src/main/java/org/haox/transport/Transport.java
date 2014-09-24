package org.haox.transport;

import org.haox.dispatch.Dispatcher;
import org.haox.event.NewInboundMessageEvent;
import org.haox.event.NewOutboundMessageEvent;
import org.haox.message.Message;

import java.io.IOException;
import java.net.InetSocketAddress;

public abstract class Transport {
    private InetSocketAddress remoteAddress;
    private boolean isActive;
    private Dispatcher dispatcher;

    public Transport(InetSocketAddress remoteAddress, boolean isActive) {
        this.remoteAddress = remoteAddress;
        this.isActive = isActive;
    }

    public void setDispatcher(Dispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }

    public InetSocketAddress getRemoteAddress() {
        return remoteAddress;
    }

    public void postMessage(Message message) {
        handleOutboundMessage(message);
    }

    public void sendMessage(Message message) throws IOException {
        doSendMessage(message);
    }

    public void onReadable() {

    }

    public void onWriteable() {

    }

    protected abstract void doSendMessage(Message message) throws IOException;

    protected void handleInboundMessage(Message message) {
        dispatcher.dispatch(new NewInboundMessageEvent(this, message));
    }

    protected void handleOutboundMessage(Message message) {
        dispatcher.dispatch(new NewOutboundMessageEvent(this, message));
    }
}
