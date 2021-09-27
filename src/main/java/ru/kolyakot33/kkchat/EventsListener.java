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

public class EventsListener implements Listener {
    final KKChat instance = KKChat.getInstance();

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent e) {
        String msg = e.getMessage();
        Logger log = getLogger();
        String msgToSend;
        //Global
        if (msg.startsWith(instance.globalSymbol)) {
            msg = msg.replaceFirst(instance.globalSymbol, "");
            msgToSend = instance.globalFormat.replace("%player%", e.getPlayer().getDisplayName());
            msgToSend = msgToSend.replace("%message%", msg);
            for (Player pp : Bukkit.getOnlinePlayers()) {
                pp.sendMessage(msgToSend);

            }
        }
        //Local
        else {
            msgToSend = instance.localFormat.replace("%player%", e.getPlayer().getDisplayName());
            msgToSend = msgToSend.replace("%message%", msg);
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
