package me.ma.edititem.commands;

import me.ma.edititem.utils.CommandFunctions;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public class Help implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player p) {
            if (!Arrays.stream(args).toList().isEmpty()) {
                if (args[0].equalsIgnoreCase("name")) {
                    p.sendMessage("This sets the name of the item in your hand to whatever you type.");
                    p.sendMessage("You can use the bukkit color codes if you want to by using '&' followed by a valid color code.");
                }
                if (args[0].equalsIgnoreCase("unbreakable")) {
                    p.sendMessage("This allows you to set whether the item in your hand is unbreakable or not.");
                    p.sendMessage("Use true to enable it and false to disable it.");
                }
                if (CommandFunctions.checkArgs(args, 0, "enchantmentGlint")) {
                    p.sendMessage("This makes your item have the enchantment glint while not showing an enchantment in the lore.");
                    p.sendMessage("Your item can't be enchanted if you want to add this effect because all it does is add a useless enchan" +
                            "tment to the item and hide the enchant in the lore.");
                    p.sendMessage("Use true to enable the effect and false to disable it.");
                }
                if (CommandFunctions.checkArgs(args, 0, "addEnchant")) {
                    p.sendMessage("This adds the enchantment you have selected to the item.");
                    p.sendMessage("If you don't set a level for your enchantment it will be 1 by default.");
                    p.sendMessage("You can set the level to any number regardless of the maximum enchant level.");
                    p.sendMessage("You can't have enchantments and the enchantment glint enabled at the same time.");
                    p.sendMessage("You can however hide the enchantments using the hideInDescription feature.");
                }
                if (CommandFunctions.checkArgs(args, 0, "removeEnchant")) {
                    p.sendMessage("Removes the specified enchantment from the item.");
                }
                if (CommandFunctions.checkArgs(args, 0, "hideInDescription")) {
                    p.sendMessage("This allows you to prevent a specific part of the item lore from showing.");
                    p.sendMessage("The names are self explanatory.");
                }
                if (CommandFunctions.checkArgs(args, 0, "addLore")) {
                    p.sendMessage("Adds a line of lore to the item.");
                    p.sendMessage("You can use the bukkit color codes by using the '&' character and a valid code.");
                }
                if (CommandFunctions.checkArgs(args, 0, "removeLore")) {
                    p.sendMessage("Removes the lore at a specified line. Doesn't work if there is no line at that number or if you didn't input a number.");
                }
                if (CommandFunctions.checkArgs(args, 0, "armorColor")) {
                    p.sendMessage("Changes the color of a leather item (leather armor, leather horse armor).");
                    p.sendMessage("Only works if you are holding leather armor.");
                    p.sendMessage("You can either use a predetermined color (red, blue, etc) or a hexadecimal code.");
                }
                if (CommandFunctions.checkArgs(args, 0, "skin")) {
                    p.sendMessage("Sets the skin of a player head. Only works if you are holding a player head");
                    p.sendMessage("You can either use a player's name for the skin or you can use a link from textures.minecraft.net");
                }
            } else {
                p.sendMessage("Choose an option to get help with.");
            }
        } else {
            sender.sendMessage("You can only use this command as a player!");
        }
        return true;
    }
    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {
        List<String> possibleChanges = new ArrayList<>();
        possibleChanges.add("name");
        possibleChanges.add("unbreakable");
        possibleChanges.add("addEnchant");
        possibleChanges.add("removeEnchant");
        possibleChanges.add("enchantmentGlint");
        possibleChanges.add("hideInDescription");
        possibleChanges.add("addLore");
        possibleChanges.add("removeLore");
        possibleChanges.add("armorColor");
        possibleChanges.add("skin");
        if (label.equalsIgnoreCase("eihelp")) {
            return possibleChanges;
        }
        return null;
    }
}
