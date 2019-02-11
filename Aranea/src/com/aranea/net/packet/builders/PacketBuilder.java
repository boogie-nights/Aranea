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

public abstract class PacketBuilder {

    private int opcode;
    private PacketHeader header;

    public abstract OutboundByteBuffer build(ChannelSession session);

    public PacketBuilder(int opcode, PacketHeader header) {
        this.opcode = opcode;
        this.header = header;
    }

    public int getOpcode() {
        return opcode;
    }

    public void setOpcode(int opcode) {
        this.opcode = opcode;
    }

    public PacketHeader getHeader() {
        return header;
    }

    public void setHeader(PacketHeader header) {
        this.header = header;
    }
}
