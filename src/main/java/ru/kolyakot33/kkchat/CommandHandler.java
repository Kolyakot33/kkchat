package ru.kolyakot33.kkchat;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;


public class CommandHandler implements CommandExecutor {
    KKChat instance = KKChat.getInstance();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args[0].equalsIgnoreCase("reload")) {
            if (sender.hasPermission("kkchat.reload")) {
                    instance.reloadConfig();
                    sender.sendMessage("Конфигурация перезагружена!");
            } else {
                    sender.sendMessage("У вас нет разрешения");
            }
        } else {
                sender.sendMessage("KKChat by Kolyakot33.");
        }
        return true;
    }
}
