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

@SuppressWarnings("unused")
public class EventsListener implements Listener {
    final Main INSTANCE = Main.getInstance();

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent e) {
        String msg = e.getMessage();
        Logger log = getLogger();
        String msgToSend;
        //Global
        if (msg.startsWith("!")) {
            msg = msg.replaceFirst("!", "");
            msgToSend = INSTANCE.globalFormat.replace("%player%", e.getPlayer().getDisplayName());
            msgToSend = msgToSend.replace("%message%", msg);
            for (Player pp : Bukkit.getOnlinePlayers()) {
                pp.sendMessage(msgToSend);

            }
        }
        //Local
        else {
            msgToSend = INSTANCE.localFormat.replace("%player%", e.getPlayer().getDisplayName());
            msgToSend = msgToSend.replace("%message%", msg);
            for (Player pp : Bukkit.getOnlinePlayers()) {
                if (Math.sqrt(
                        Math.pow(Math.abs(pp.getLocation().getX() - e.getPlayer().getLocation().getX()), 2) +
                                Math.pow(Math.abs(pp.getLocation().getZ() - e.getPlayer().getLocation().getZ()), 2)
                ) <= INSTANCE.range) {

                    pp.sendMessage(msgToSend);

                }
            }
        }
        log.log(Level.INFO, msgToSend);
        e.setCancelled(true);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        e.getPlayer().sendMessage(INSTANCE.welcomeMessage);
    }
}
