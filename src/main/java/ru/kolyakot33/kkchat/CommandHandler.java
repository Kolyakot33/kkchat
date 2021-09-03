package ru.kolyakot33.kkchat;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;


public class CommandHandler implements CommandExecutor {
    KKChat instance = KKChat.getInstance();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        String subcommand = args.length > 0 ? args[0] : "none";
        if (subcommand.equalsIgnoreCase("reload")) {
            if (sender.hasPermission("kkchat.reload")) {
                    instance.reloadConfig();
                    instance.loadConfigValues();
                    sender.sendMessage(instance.reloadMessage);
            } else {
                    sender.sendMessage(command.getPermissionMessage());
            }
            return true;
        }
        else {
            sender.sendMessage("KKChat by Kolyakot33.");
            return true;
        }
    }
}
