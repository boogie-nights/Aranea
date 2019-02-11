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
package com.aranea.net.packet.builders;

import com.aranea.net.ChannelSession;
import com.aranea.net.OutboundByteBuffer;
import com.aranea.net.packet.PacketHeader;

public class GameTextPacketBuilder extends PacketBuilder {

    private final String text;

    public GameTextPacketBuilder(String text) {
        super(PacketBuilderConstants.GAME_TEXT_MESSAGE_PACKET_OPCODE, PacketHeader.VARIABLE_BYTE);
        this.text = text;
    }

    @Override
    public OutboundByteBuffer build(ChannelSession session) {
        OutboundByteBuffer buffer = new OutboundByteBuffer(text.length() + 3);
        buffer.writeBytes(text.getBytes());
        buffer.writeByte(PacketBuilderConstants.STRING_TERMINATION_VALUE);
        return buffer;
    }
}
