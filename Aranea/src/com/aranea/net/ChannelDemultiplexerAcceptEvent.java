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
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;

public class ChannelDemultiplexerAcceptEvent implements ChannelDemultiplexerEvent {

    @Override
    public void execute(ChannelDemultiplexer demultiplexer, SelectionKey token) {
        try {
            SocketChannel socket = demultiplexer.server().accept();
            if (socket == null)
                return;

            socket.configureBlocking(ChannelDemultiplexer.CHANNEL_BLOCKING_MODE);
            SelectionKey priv_token = socket.register(demultiplexer.selector(), SelectionKey.OP_READ);
            ChannelSession session = new ChannelSession(demultiplexer, priv_token, socket);
            session.setState(ChannelState.CONNECTED);
            priv_token.attach(session);

            demultiplexer.accept(session);
        } catch (IOException exception) {
            exception.printStackTrace(System.out);
        }
    }
}
