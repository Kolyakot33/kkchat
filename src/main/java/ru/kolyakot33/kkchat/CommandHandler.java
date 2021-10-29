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
        }
        else {
            sender.sendMessage("KKChat by Kolyakot33.");
        }
        return true;
    }
}
