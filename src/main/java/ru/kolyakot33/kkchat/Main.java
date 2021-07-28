package ru.kolyakot33.kkchat;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;


public class Main extends JavaPlugin {
    FileConfiguration configuration = getConfig();
    public int range;
    public String globalSymbol;
    public String globalFormat;
    public String localFormat;
    private static Main INSTANCE;
    public String welcomeMessage;
    @Override
    public void onEnable() {
        INSTANCE = this;
        saveDefaultConfig();
        range = configuration.getInt("local-chat-range");
        globalSymbol = configuration.getString("global-chat-symbol");
        globalFormat = configuration.getString("global-chat-format");
        localFormat = configuration.getString("local-chat-format");
        welcomeMessage = configuration.getString("welcome-message");
        getLogger().log(Level.INFO, "Configuration loaded!");
        getServer().getPluginManager().registerEvents(new EventsListener(), this);
        getLogger().log(Level.INFO, "Events registered!");
        setEnabled(true);
        getLogger().log(Level.INFO, "Loaded!");
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("kkchat")) {
            if (args[0].equalsIgnoreCase("reload")) {
                if (sender.hasPermission("kkchat.reload")) {
                    reloadConfig();
                    sender.sendMessage("Конфигурация перезагружена!");
                } else {
                    sender.sendMessage("У вас нет разрешения");
                }
            }
        }
        return super.onCommand(sender, command, label, args);
    }

    public static Main getInstance() {
        return INSTANCE;
    }
}
