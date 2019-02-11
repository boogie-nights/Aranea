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

import com.aranea.net.codec.ChannelMessageDecoder;
import com.aranea.net.codec.ChannelMessageEncoder;
import com.aranea.net.codec.GameMessageEncoder;
import com.aranea.net.packet.builders.PacketBuilder;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;

public class ChannelSession {

    private final SelectionKey token;
    private final SocketChannel socket;
    
    private ChannelMessageEncoder encoder;
    private ChannelMessageDecoder decoder;

    public ChannelSession(SelectionKey token, SocketChannel socket) {
        this.token = token;
        this.socket = socket;
    }
    
    public void write(ByteBuffer buffer) {
        buffer.flip();
        try {
            socket.write(buffer);
        } catch (IOException exception) {
            exception.printStackTrace(System.out);
        }
    }
    
    public void encode(PacketBuilder builder) {
        if (encoder == null || !(encoder instanceof GameMessageEncoder))
            throw new UnsupportedOperationException("The current encoder does not support this operation.");
        ByteBuffer payload = encoder.encode(this, builder);
        write(payload);
    }

    public SelectionKey getToken() {
        return token;
    }

    public SocketChannel getSocket() {
        return socket;
    }

    public ChannelMessageEncoder getEncoder() {
        return encoder;
    }

    public void setEncoder(ChannelMessageEncoder encoder) {
        this.encoder = encoder;
    }

    public ChannelMessageDecoder getDecoder() {
        return decoder;
    }

    public void setDecoder(ChannelMessageDecoder decoder) {
        this.decoder = decoder;
    }
}
