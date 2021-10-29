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

package ru.kolyakot33.kkchat.utils;

import org.bukkit.entity.Player;
import ru.kolyakot33.kkchat.KKChat;

public class Utils {
    public static String formatMessage(String message, Player player, String format) {
        VaultHook vaultHook = KKChat.getInstance().vaultHook;
        if (vaultHook != null) {
            format = format.replace("\\{prefix\\}",
                    vaultHook.getChat().getPlayerPrefix(player));
            format = format.replace("\\{suffix\\}",
                    vaultHook.getChat().getPlayerSuffix(player));
        }else {
            format = format.replace("\\{prefix\\}", "");
            format = format.replace("\\{suffix\\}", "");
        }
        format = format.replace("\\{player\\}", player.getName());
        format = format.replace("\\{message\\}", message);
        return format;
    }
}
