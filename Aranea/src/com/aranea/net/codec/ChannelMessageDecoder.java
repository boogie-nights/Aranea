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
package com.aranea.net.codec;

import com.aranea.net.ChannelSession;

public interface ChannelMessageDecoder {

    /**
     * Decodes an array of bytes that has been read from a
     * {@link SocketChannel}.
     *
     * @param session The {@link ChannelSession} that received the message.
     * @return Denotes if the operation was successful. If the value returned is
     * <code> false </code> then the associated {@link ChannelSession} will
     * automatically terminate.
     */
    boolean decode(ChannelSession session);
}
