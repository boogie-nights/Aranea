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
package com.aranea.net.codec.game;

import com.aranea.net.ChannelSession;
import com.aranea.net.OutboundByteBuffer;
import com.aranea.net.codec.ChannelMessageEncoder;
import com.aranea.net.packet.PacketHeader;
import com.aranea.net.packet.builders.PacketBuilder;
import java.nio.ByteBuffer;

public class GameMessageEncoder implements ChannelMessageEncoder<PacketBuilder> {

    @Override
    public ByteBuffer encode(ChannelSession session, PacketBuilder builder) {
        OutboundByteBuffer payload = builder.build(session);
        payload.flip();

        final int opcode = builder.getOpcode();
        final PacketHeader header = builder.getHeader();
        int capacity = payload.limit();

        capacity++;
        switch (header) {

            case VARIABLE_BYTE:
                capacity += Byte.BYTES;
                break;

            case VARIABLE_SHORT:
                capacity += Short.BYTES;
                break;
        }

        ByteBuffer packet = ByteBuffer.allocate(capacity);
        packet.put((byte) opcode);
        switch (header) {

            case VARIABLE_BYTE:
                packet.put((byte) payload.limit());
                break;

            case VARIABLE_SHORT:
                packet.putShort((short) payload.limit());
                break;
        }

        packet.put(payload.getBuffer());
        return packet;
    }
}
