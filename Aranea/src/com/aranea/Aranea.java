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
package com.aranea;

import com.aranea.net.ChannelDemultiplexerBootstrap;
import com.aranea.net.GameChannelDemultiplexer;
import com.aranea.net.JaggrabChannelDemultiplexer;
import java.io.IOException;
import java.net.InetSocketAddress;

public class Aranea {

    public static void main(String[] commands) {
        try {
            ChannelDemultiplexerBootstrap jaggrabBootstrap = new ChannelDemultiplexerBootstrap(
                    new JaggrabChannelDemultiplexer(new InetSocketAddress(43595)));
            jaggrabBootstrap.initialize();

            ChannelDemultiplexerBootstrap gameBootstrap = new ChannelDemultiplexerBootstrap(
                    new GameChannelDemultiplexer(new InetSocketAddress(43594)));
            gameBootstrap.initialize();
        } catch (IOException exception) {
            exception.printStackTrace(System.out);
        }
    }
}
