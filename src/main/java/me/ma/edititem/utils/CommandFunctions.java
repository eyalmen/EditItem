package me.ma.edititem.utils;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class CommandFunctions {
    public static boolean senderIsPlayer(CommandSender sender) {
        if (sender instanceof Player) {
            return true;
        } else {
            return false;
        }
    }
    public static boolean noArgs(String[] args) {
        if (args[0].isEmpty()) {
            return true;
        } else {
            return false;
        }
    }
    public static boolean checkArgs(String[] args, int location, String checkString) {
        if (args[location].equalsIgnoreCase(checkString)) {
            return true;
        } else {
            return false;
        }
    }
    public static boolean specIsEmpty(String[] args, int location) {
        if (args[location] == null) {
            return true;
        } else {
            return false;
        }
    }
    public static ItemStack getPlayersHand(Player p) {
        if (p.getEquipment().getItemInMainHand() != null) {
            return p.getEquipment().getItemInMainHand();
        } else {
            return null;
        }
    }
}
