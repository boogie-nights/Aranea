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

public class LoginHandshakeMessageEncoder implements ChannelMessageEncoder<LoginHandshakeResponse> {

    @Override
    public ByteBuffer encode(ChannelSession session, LoginHandshakeResponse response) {
        ByteBuffer buffer = ByteBuffer.allocate(Byte.BYTES + Long.BYTES + Long.BYTES);
        buffer.put((byte) response.getStatus());
        buffer.putLong(0);
        buffer.putLong(response.getSeed());
        return buffer;
    }
}
