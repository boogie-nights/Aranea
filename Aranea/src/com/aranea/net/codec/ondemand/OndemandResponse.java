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
package com.aranea.net.codec.ondemand;

import java.nio.ByteBuffer;

public class OndemandResponse {

    private int size;
    private int chunk;
    private ByteBuffer payload;
    
    public OndemandResponse(int size, int chunk, ByteBuffer payload) {
        this.size = size;
        this.chunk = chunk;
        this.payload = payload;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getChunk() {
        return chunk;
    }

    public void setChunk(int chunk) {
        this.chunk = chunk;
    }

    public ByteBuffer getPayload() {
        return payload;
    }

    public void setPayload(ByteBuffer payload) {
        this.payload = payload;
    }
}
