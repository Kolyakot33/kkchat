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

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.logging.Level;
import java.util.logging.Logger;

import static org.bukkit.Bukkit.getLogger;
import static ru.kolyakot33.kkchat.utils.Utils.formatMessage;

public class EventsListener implements Listener {
    KKChat instance;
    public EventsListener() {
        instance = KKChat.getInstance();
    }
    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent e) {
        String msg = e.getMessage();
        Logger log = getLogger();
        String msgToSend;
        //Global
        if (msg.startsWith(instance.globalSymbol)) {
            msgToSend = formatMessage(msg.replaceFirst(instance.globalSymbol, ""), e.getPlayer(), instance.globalFormat);
            for (Player pp : Bukkit.getOnlinePlayers()) {
                pp.sendMessage(msgToSend);

            }
        }
        //Local
        else {
            msgToSend = formatMessage(msg, e.getPlayer(), instance.localFormat);
            for (Player pp : Bukkit.getOnlinePlayers()) {
                if (e.getPlayer().getLocation().distance(pp.getLocation()) <= instance.range) {

                    pp.sendMessage(msgToSend);

                }
            }
        }
        log.log(Level.INFO, msgToSend);
        e.setCancelled(true);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        e.getPlayer().sendMessage(instance.welcomeMessage);
    }
}
