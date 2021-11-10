/*
 * Copyright (c) 2021. Kolyakot33
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package ru.kolyakot33.kkchat;

import org.bukkit.configuration.file.FileConfiguration;

import java.util.HashMap;

public class Configuration {
    private static KKChat kkChat;
    static FileConfiguration configuration;
    static int range;
    static String globalSymbol;
    static String globalFormat;
    static String localFormat;
    static String welcomeMessage;
    static String reloadMessage;

    public static void setup(KKChat inst) {
        kkChat = inst;
        configuration = kkChat.getConfig();
        loadConfigValues();
    }
    public static void loadConfigValues() {
        range = configuration.getInt("local-chat-range");
        globalSymbol = configuration.getString("global-chat-symbol");
        globalFormat = configuration.getString("global-chat-format");
        localFormat = configuration.getString("local-chat-format");
        welcomeMessage = configuration.getString("welcome-message");
        reloadMessage = configuration.getString("reload-message");
    }
    public static void reloadConfigValues() {
        configuration = kkChat.getConfig();
        loadConfigValues();
    }
}
