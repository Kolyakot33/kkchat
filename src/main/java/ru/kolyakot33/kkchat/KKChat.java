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

import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;
import ru.kolyakot33.kkchat.utils.VaultHook;
import java.util.logging.Level;

public class KKChat extends JavaPlugin {
    @Getter private static KKChat instance;
    @Getter VaultHook vaultHook;
    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();
        Configuration.setup(this);
        if (getServer().getPluginManager().isPluginEnabled("Vault")) {
            vaultHook = new VaultHook();
        }
        getServer().getPluginManager().registerEvents(new EventsListener(), this);
        getLogger().log(Level.INFO, "Events registered!");
        getCommand("kkchat").setExecutor(new CommandHandler());
        setEnabled(true);
        getLogger().log(Level.INFO, "Loaded!");
    }
}
