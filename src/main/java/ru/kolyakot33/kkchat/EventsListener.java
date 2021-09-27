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
