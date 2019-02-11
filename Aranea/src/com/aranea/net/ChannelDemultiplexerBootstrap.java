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

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class ChannelDemultiplexerBootstrap {

    private ScheduledExecutorService executor;
    private ChannelDemultiplexer demultiplexer;

    public ChannelDemultiplexerBootstrap(ChannelDemultiplexer demultiplexer) {
        this(Executors.newSingleThreadScheduledExecutor(), demultiplexer);
    }

    public ChannelDemultiplexerBootstrap(ScheduledExecutorService executor, ChannelDemultiplexer demultiplexer) {
        this.executor = executor;
        this.demultiplexer = demultiplexer;
    }

    public ScheduledFuture<?> initialize() throws IOException {
        demultiplexer.start();
        return executor.scheduleAtFixedRate(demultiplexer, 0, ChannelDemultiplexer.DEFAULT_THREAD_RATE, TimeUnit.MILLISECONDS);
    }

    public ScheduledExecutorService getExecutor() {
        return executor;
    }

    public void setExecutor(ScheduledExecutorService executor) {
        this.executor = executor;
    }

    public ChannelDemultiplexer getDemultiplexer() {
        return demultiplexer;
    }

    public void setDemultiplexer(ChannelDemultiplexer demultiplexer) {
        this.demultiplexer = demultiplexer;
    }
}
