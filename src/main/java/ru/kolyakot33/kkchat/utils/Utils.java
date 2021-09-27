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
