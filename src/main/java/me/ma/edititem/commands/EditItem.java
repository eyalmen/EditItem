package me.ma.edititem.commands;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import me.ma.edititem.Main;
import me.ma.edititem.utils.CommandFunctions;
import me.ma.edititem.utils.TextFunctions;
import me.ma.edititem.utils.readable.ReadableColors;
import me.ma.edititem.utils.readable.ReadableEnchantments;
import me.ma.edititem.utils.readable.ReadableItemFlags;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Field;
import java.util.*;

public class EditItem implements CommandExecutor, TabCompleter {
    Main plugin = Main.getPlugin(Main.class);
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {
        if (label.equalsIgnoreCase("edititem")) {
            if (CommandFunctions.senderIsPlayer(sender)) {
                Player p = (Player) sender;
                if (!CommandFunctions.noArgs(args)) {
                    if (CommandFunctions.getPlayersHand(p) != null) {
                        ItemStack editStack = p.getEquipment().getItemInMainHand();
                        ItemMeta editMeta = editStack.getItemMeta();
                        List<String> lore;
                        if (args[0].equalsIgnoreCase("name")) {
                            if (args.length >= 2) {
                                String[] nameArgs = Arrays.copyOfRange(args, 1, args.length);
                                String nameText = TextFunctions.convertObjectArrayToString(nameArgs, " ");
                                editMeta.setDisplayName(TextFunctions.tColor(nameText));
                            } else {
                                p.sendMessage("You need to input a name!");
                            }
                        }
                        if (args[0].equalsIgnoreCase("unbreakable")) {
                            if (args.length >=2) {
                                if (args[1].equalsIgnoreCase("true")) {
                                    editMeta.setUnbreakable(true);
                                } else if (args[1].equalsIgnoreCase("false")) {
                                    editMeta.setUnbreakable(false);
                                } else {
                                    p.sendMessage("You need to input true or false!");
                                }
                            } else {
                                p.sendMessage("You need to input true or false!");
                            }
                        }
                        if (CommandFunctions.checkArgs(args, 0, "enchantmentGlint")) {
                            if (args.length >= 2) {
                                if (CommandFunctions.checkArgs(args, 1, "true")) {
                                    if (editMeta.getEnchants().isEmpty()) {
                                        editMeta.addEnchant(Enchantment.LURE, 0, true);
                                        editMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                                    } else {
                                        p.sendMessage("You can't have enchantment glint and enchantments at the same time!");
                                    }
                                } else if (args[1].equalsIgnoreCase("false")) {
                                    editMeta.removeEnchant(Enchantment.LURE);
                                    editMeta.removeItemFlags(ItemFlag.HIDE_ENCHANTS);
                                } else {
                                    p.sendMessage("You need to input true or false!");
                                }
                            } else {
                                p.sendMessage("You need to input true or false!");
                            }
                        }
                        if (CommandFunctions.checkArgs(args, 0, "addEnchant")) {
                            if (!editMeta.getItemFlags().contains(ItemFlag.HIDE_ENCHANTS) && !editMeta.hasEnchant(Enchantment.LURE)) {
                                if (args.length >=2) {
                                    int enchLevel = 0;
                                    if (!args[2].isEmpty()) {
                                        try {
                                            enchLevel = Integer.parseInt(args[2]) - 1;
                                        } catch (Exception e) {
                                            p.sendMessage("Only put numbers in the enchantment level!");
                                        }
                                    }
                                    for (ReadableEnchantments re : ReadableEnchantments.values()) {
                                        if (re.findByString.equalsIgnoreCase(args[1])) {
                                            editMeta.addEnchant(re.correspondingValue, enchLevel + 1, true);
                                        }
                                    }
                                    if (editMeta.getEnchants().isEmpty()) {
                                        p.sendMessage("You need to input a valid enchantment!");
                                    }
                                } else {
                                    p.sendMessage("You need to input an enchantment!");
                                }
                            } else {
                                p.sendMessage("You can't have enchantment glint and enchantments at the same time!");
                            }
                        }
                        if (CommandFunctions.checkArgs(args, 0, "removeEnchant")) {
                            if (args.length >=2) {
                                for (ReadableEnchantments re : ReadableEnchantments.values()) {
                                    if (re.findByString.equalsIgnoreCase(args[1])) {
                                        editMeta.removeEnchant(re.correspondingValue);
                                    }
                                }
                            } else {
                                p.sendMessage("You need to input an enchantment!");
                            }
                        }
                        if (CommandFunctions.checkArgs(args, 0, "hideInDescription")) {
                            if (args.length >=2) {
                                for (ReadableItemFlags re : ReadableItemFlags.values()) {
                                    if (re.findByString.equalsIgnoreCase(args[1])) {
                                        if (args.length >=3) {
                                            if (CommandFunctions.checkArgs(args, 2, "true")) {
                                                editMeta.addItemFlags(re.correspondingValue);
                                            } else if (CommandFunctions.checkArgs(args, 2, "false")) {
                                                editMeta.removeItemFlags(re.correspondingValue);
                                            } else {
                                                p.sendMessage("You need to input true or false!");
                                            }
                                        } else {
                                            p.sendMessage("You need to input true or false!");
                                        }
                                    }
                                }
                            } else {
                                p.sendMessage("You need to input an ItemFlag!");
                            }
                        }
                        if (CommandFunctions.checkArgs(args, 0, "addLore")) {
                            if (args.length >= 2) {
                                if (editMeta.getLore() != null) {
                                    lore = editMeta.getLore();
                                } else {
                                    lore = new ArrayList<>();
                                }
                                String[] loreArgs = Arrays.copyOfRange(args, 1, args.length);
                                String loreText = TextFunctions.convertObjectArrayToString(loreArgs, " ");
                                lore.add(TextFunctions.tColor(loreText));
                                editMeta.setLore(lore);
                            } else {
                                p.sendMessage("You need to input some lore!");
                            }
                        }
                        if (CommandFunctions.checkArgs(args, 0, "removeLore")) {
                            if (args.length >=2) {
                                if (editMeta.getLore() != null) {
                                    lore = editMeta.getLore();
                                    int getLoreEntry;
                                    try {
                                        getLoreEntry = Integer.parseInt(args[1]) - 1;
                                        if (lore.get(getLoreEntry) != null) {
                                            lore.remove(getLoreEntry);
                                            editMeta.setLore(lore);
                                        }
                                    } catch (Exception e) {
                                        p.sendMessage("Theres no lore on that line!");
                                    }
                                } else {
                                    p.sendMessage("This item doesn't have lore!");
                                }
                            } else {
                                p.sendMessage("You need to input a line to remove lore from!");
                            }
                        }
                        if (CommandFunctions.checkArgs(args, 0, "armorColor")) {
                            if (args.length >= 2) {
                                List<String> colors = new ArrayList<>();
                                for (ReadableColors rc : ReadableColors.values()) {
                                    colors.add(rc.findByString);
                                }
                                Material armor = editStack.getType();
                                if (armor == Material.LEATHER_HORSE_ARMOR || (armor == Material.LEATHER_BOOTS) || (armor == Material.LEATHER_LEGGINGS) ||
                                        (armor == Material.LEATHER_CHESTPLATE) || (armor == Material.LEATHER_HELMET)) {
                                    LeatherArmorMeta leatherArmorMeta = (LeatherArmorMeta) editMeta;
                                    int count = 0;
                                    for (String s : colors) {
                                        if (colors.contains(args[1])) {
                                            leatherArmorMeta.setColor(ReadableColors.valueOfLabel(args[1]).correspondingValue);
                                            break;
                                        }
                                        count++;
                                    }
                                    if (count == 17) {
                                        String hex = "#" + args[1];
                                        try {
                                            if (!(hex.length() > 7)) {
                                                leatherArmorMeta.setColor(Color.fromRGB(Integer.valueOf(hex.substring(1, 3), 16), Integer.valueOf(hex.substring(3, 5), 16), Integer.valueOf(hex.substring(5, 7), 16)));
                                            } else {
                                                p.sendMessage("That's not a valid hexadecimal color!");
                                            }
                                        } catch (Exception e) {
                                            p.sendMessage("That's not a valid hexadecimal color!");
                                        }
                                    }
                                } else {
                                    p.sendMessage("You need to have leather armor or horse armor in your hand!");
                                }
                            } else {
                                p.sendMessage("You need to input a color!");
                            }
                            editStack.setItemMeta(editMeta);
                        }
                        if (CommandFunctions.checkArgs(args, 0, "skin")) {
                            if (editStack.getType().equals(Material.PLAYER_HEAD)) {
                                if (args.length >= 2) {
                                    SkullMeta headMeta = (SkullMeta) editMeta;
                                    if (args[1].contains("http://")) {
                                        GameProfile profile = new GameProfile(UUID.randomUUID(), null);
                                        byte[] encodedData = Base64.getEncoder().encode((String.format("{textures:{SKIN:{url:\"%s\"}}}", args[1]).getBytes()));
                                        profile.getProperties().put("textures", new Property("textures", new String(encodedData)));
                                        Field profileField;
                                        try {
                                            profileField = headMeta.getClass().getDeclaredField("profile");
                                            profileField.setAccessible(true);
                                            profileField.set(headMeta, profile);
                                        } catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException e1) {
                                            e1.printStackTrace();
                                        }
                                    } else {
                                        headMeta.setOwner(args[1]);
                                    }
                                } else {
                                    p.sendMessage("You need to input a skin url!");
                                }
                            } else {
                                p.sendMessage("That's not a player head!");
                            }
                        }
                        editStack.setItemMeta(editMeta);
                    }
                } else {
                    p.sendMessage(TextFunctions.tColor("&7You need to say what you want to change."));
                }
            } else {
                sender.sendMessage("Only players can use this command!");
            }
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
        if (label.equalsIgnoreCase("edititem") || label.equalsIgnoreCase("ei")) {
            if (args.length == 1) {
                return possibleChanges;
            } else {
                List<String> trueFalse = new ArrayList<>();
                trueFalse.add("true");
                trueFalse.add("false");
                if (CommandFunctions.checkArgs(args,0,"unbreakable")) {
                    return trueFalse;
                }
                if (CommandFunctions.checkArgs(args,0,"enchantmentGlint")) {
                    return trueFalse;
                }
                List<String> enchants = new ArrayList<>();
                for (ReadableEnchantments re : ReadableEnchantments.values()) {
                    enchants.add(re.findByString);
                }
                List<String> enchantsMaxLevels = new ArrayList<>();
                for (ReadableEnchantments re : ReadableEnchantments.values()) {
                    enchantsMaxLevels.add(Integer.toString(re.maxLevel));
                }
                if (CommandFunctions.checkArgs(args,0,"removeEnchant")) {
                    return enchants;
                }
                if (CommandFunctions.checkArgs(args,0,"addEnchant")) {
                    int count = 0;
                    for (String s : enchants) {
                        if (CommandFunctions.checkArgs(args, 1, s)) {
                            List<String> all = new ArrayList<>();
                            int maxLevel = Integer.parseInt(enchantsMaxLevels.get(count));
                            for (int i = maxLevel; i > 0; i--) {
                                all.add(Integer.toString(i));
                            }
                            return all;
                        }
                        count++;
                    }
                    return enchants;
                }
                List<String> flags = new ArrayList<>();
                for (ReadableItemFlags rif : ReadableItemFlags.values()) {
                    flags.add(rif.findByString);
                }
                if (CommandFunctions.checkArgs(args,0,"hideInDescription")) {
                    for (String s : flags) {
                        if (CommandFunctions.checkArgs(args, 1, s)) {
                            return trueFalse;
                        }
                    }
                    return flags;
                }
                if (CommandFunctions.checkArgs(args,0,"removeLore")) {
                    List<String> line = new ArrayList<>();
                    line.add("line");
                    return line;
                }
                List<String> hexadecimal = new ArrayList<>();
                hexadecimal.add("hexadecimal");
                for (ReadableColors rc : ReadableColors.values()) {
                    hexadecimal.add(rc.findByString);
                }
                if (CommandFunctions.checkArgs(args,0,"armorColor")) {
                    return hexadecimal;
                }
                List<String> url = new ArrayList<>();
                url.add("textures.minecraft.net url");
                url.add("Player name");
                if (CommandFunctions.checkArgs(args,0,"skin")) {
                    return url;
                }
                return null;
            }
        } else {
            return null;
        }
    }
}
