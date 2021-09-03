package ru.kolyakot33.kkchat;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;


public class KKChat extends JavaPlugin {
    FileConfiguration configuration = getConfig();
    public int range;
    public String globalSymbol;
    public String globalFormat;
    public String localFormat;
    private static KKChat instance;
    public String welcomeMessage;
    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();
        loadConfigValues();
        getServer().getPluginManager().registerEvents(new EventsListener(), this);
        getLogger().log(Level.INFO, "Events registered!");
        getCommand("kkchat").setExecutor(new CommandHandler());
        setEnabled(true);
        getLogger().log(Level.INFO, "Loaded!");
    }

    public static KKChat getInstance() {
        return instance;
    }
    
    public static void loadConfigValues() {
        range = configuration.getInt("local-chat-range");
        globalSymbol = configuration.getString("global-chat-symbol");
        globalFormat = configuration.getString("global-chat-format");
        localFormat = configuration.getString("local-chat-format");
        welcomeMessage = configuration.getString("welcome-message");
        getLogger().log(Level.INFO, "Configuration loaded!");
    }
}
        

        

   


