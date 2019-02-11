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
import com.aranea.net.codec.ChannelMessageDecoder;
import java.nio.ByteBuffer;

public class LoginPayloadMessageDecoder implements ChannelMessageDecoder {

    private final int length;

    public LoginPayloadMessageDecoder(int length) {
        this.length = length;
    }

    @Override
    public boolean decode(ChannelSession session) {
        ByteBuffer response = ByteBuffer.allocate(Byte.BYTES + Byte.BYTES + Byte.BYTES);
        response.put((byte) 2);
        response.put((byte) 0);
        response.put((byte) 0);
        session.write(response);

        session.setDecoder(new GameMessageDecoder());
        return true;
    }
}
