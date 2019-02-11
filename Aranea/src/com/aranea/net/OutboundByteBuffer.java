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

import java.nio.ByteBuffer;

public class OutboundByteBuffer {

    private ByteBuffer buffer;

    public OutboundByteBuffer(int capacity) {
        this(ByteBuffer.allocate(capacity));
    }
    
    public OutboundByteBuffer writeByte(int value) {
        buffer.put((byte) value);
        return this;
    }
    
    public OutboundByteBuffer writeBytes(byte[] values) {
        buffer.put(values);
        return this;
    }

    public OutboundByteBuffer(ByteBuffer buffer) {
        this.buffer = buffer;
    }
    
    public void flip() {
        buffer.flip();
    }
    
    public void compact() {
        buffer.compact();
    }
    
    public int limit() {
        return buffer.limit();
    }

    public ByteBuffer getBuffer() {
        return buffer;
    }

    public void setBuffer(ByteBuffer buffer) {
        this.buffer = buffer;
    }
}
