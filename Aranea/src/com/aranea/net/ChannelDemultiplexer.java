/*
 * Copyright (C) 2019 Dylan Vicchiarelli
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.aranea.net;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.HashMap;
import java.util.Iterator;

public abstract class ChannelDemultiplexer implements Runnable {

    public static final boolean CHANNEL_BLOCKING_MODE = false;
    public static final int DEFAULT_THREAD_RATE = 30;

    private ServerSocketChannel server;
    private Selector selector;
    private InetSocketAddress address;
    private final HashMap<Integer, ChannelDemultiplexerEvent> events = new HashMap<>();

    public abstract void close(ChannelSession session);
    public abstract void accept(ChannelSession session);

    public ChannelDemultiplexer(InetSocketAddress address) throws IOException {
        this(ServerSocketChannel.open(), Selector.open(), address);
    }

    public ChannelDemultiplexer(ServerSocketChannel server, Selector selector, InetSocketAddress address) {
        this.server = server;
        this.selector = selector;
        this.address = address;
    }

    public void start() throws IOException {
        events.put(SelectionKey.OP_ACCEPT, new ChannelDemultiplexerAcceptEvent());
        events.put(SelectionKey.OP_READ, new ChannelDemultiplexerReadEvent());

        server.configureBlocking(CHANNEL_BLOCKING_MODE);
        server.register(selector, SelectionKey.OP_ACCEPT);
        server.bind(address);
    }

    @Override
    public void run() {
        try {
            selector.selectNow();
        } catch (IOException exception) {
            exception.printStackTrace(System.out);
        }
        Iterator<SelectionKey> $it = selector.selectedKeys().iterator();
        while ($it.hasNext()) {
            SelectionKey token = $it.next();
            if (!token.isValid())
                return;
            if (token.isAcceptable()) {
                events.get(SelectionKey.OP_ACCEPT).execute(this, token);
            } else if (token.isReadable()) {
                events.get(SelectionKey.OP_READ).execute(this, token);
            }
            $it.remove();
        }
    }

    public ServerSocketChannel getServer() {
        return server;
    }

    public void setServer(ServerSocketChannel server) {
        this.server = server;
    }

    public Selector getSelector() {
        return selector;
    }

    public void setSelector(Selector selector) {
        this.selector = selector;
    }

    public InetSocketAddress getAddress() {
        return address;
    }

    public void setAddress(InetSocketAddress address) {
        this.address = address;
    }
}
