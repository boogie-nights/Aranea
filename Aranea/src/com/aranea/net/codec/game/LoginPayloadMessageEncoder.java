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
import com.aranea.net.codec.ChannelMessageEncoder;
import java.nio.ByteBuffer;

public class LoginPayloadMessageEncoder implements ChannelMessageEncoder<LoginPayloadResponse> {

    @Override
    public ByteBuffer encode(ChannelSession session, LoginPayloadResponse response) {
        ByteBuffer buffer = ByteBuffer.allocate(Byte.BYTES + Byte.BYTES + Byte.BYTES);
        buffer.put((byte) response.getOpcode());
        buffer.put((byte) response.getRights());
        buffer.put((byte) (response.isFlagged() ? 1 : 0));
        return buffer;
    }
}
