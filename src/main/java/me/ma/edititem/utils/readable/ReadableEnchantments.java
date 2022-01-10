package me.ma.edititem.utils.readable;

import org.bukkit.enchantments.Enchantment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum ReadableEnchantments {
    PROTECTION("PROTECTION",Enchantment.PROTECTION_ENVIRONMENTAL, 4),
    FIRE_PROTECTION("FIRE_PROTECTION",Enchantment.PROTECTION_FIRE, 4),
    FEATHER_FALLING("FEATHER_FALLING",Enchantment.PROTECTION_FALL, 4),
    BLAST_PROTECTION("BLAST_PROTECTION",Enchantment.PROTECTION_EXPLOSIONS,4 ),
    PROJECTILE_PROTECTION("PROJECTILE_PROTECTION",Enchantment.PROTECTION_PROJECTILE,4 ),
    RESPIRATION("RESPIRATION",Enchantment.OXYGEN, 3),
    AQUA_AFFINITY("AQUA_AFFINITY",Enchantment.WATER_WORKER, 1),
    THORNS("THORNS",Enchantment.THORNS, 3),
    DEPTH_STRIDER("DEPTH_STRIDER",Enchantment.DEPTH_STRIDER, 3),
    FROST_WALKER("FROST_WALKER",Enchantment.FROST_WALKER, 2),
    BINDING_CURSE("CURSE_OF_BINDING",Enchantment.BINDING_CURSE, 1),
    SHARPNESS("SHARPNESS",Enchantment.DAMAGE_ALL, 5),
    SMITE("SMITE",Enchantment.DAMAGE_UNDEAD, 5),
    BANE_OF_ARTHROPODS("BANE_OF_ARTHROPODS",Enchantment.DAMAGE_ARTHROPODS, 5),
    KNOCKBACK("KNOCKBACK",Enchantment.KNOCKBACK, 2),
    FIRE_ASPECT("FIRE_ASPECT",Enchantment.FIRE_ASPECT, 2),
    LOOTING("LOOTING",Enchantment.LOOT_BONUS_MOBS, 3),
    SWEEPING_EDGE("SWEEPING_EDGE",Enchantment.SWEEPING_EDGE, 3),
    EFFICIENCY("EFFICIENCY",Enchantment.DIG_SPEED, 5),
    SILK_TOUCH("SILK_TOUCH",Enchantment.SILK_TOUCH, 1),
    UNBREAKING("UNBREAKING",Enchantment.DURABILITY, 3),
    FORTUNE("FORTUNE",Enchantment.LOOT_BONUS_BLOCKS, 3),
    POWER("POWER",Enchantment.ARROW_DAMAGE, 5),
    PUNCH("PUNCH",Enchantment.ARROW_KNOCKBACK, 2),
    FLAME("FLAME",Enchantment.ARROW_FIRE, 1),
    INFINITY("INFINITY",Enchantment.ARROW_INFINITE, 1),
    LUCK_OF_THE_SEA("LUCK_OF_THE_SEA",Enchantment.LUCK, 3),
    LURE("LURE",Enchantment.LURE, 3),
    LOYALTY("LOYALTY",Enchantment.LOYALTY, 3),
    IMPALING("IMPALING",Enchantment.IMPALING, 5),
    RIPTIDE("RIPTIDE",Enchantment.RIPTIDE, 3),
    CHANNELING("CHANNELING",Enchantment.CHANNELING, 1),
    MULTISHOT("MULTISHOT",Enchantment.MULTISHOT, 1),
    QUICK_CHARGE("QUICK_CHARGE",Enchantment.QUICK_CHARGE, 3),
    PIERCING("PIERCING",Enchantment.PIERCING, 4),
    MENDING("MENDING",Enchantment.MENDING, 1),
    VANISHING_CURSE("CURSE_OF_VANISHING",Enchantment.VANISHING_CURSE, 1),
    SOUL_SPEED("SOUL_SPEED",Enchantment.SOUL_SPEED, 3);

    public final Enchantment correspondingValue;
    public final String findByString;
    public final int maxLevel;

    ReadableEnchantments(String label, Enchantment correspondingValue, int maxLevel) {
        this.correspondingValue = correspondingValue;
        this.findByString = label;
        this.maxLevel = maxLevel;
    }
    private static final Map<String, ReadableEnchantments> BY_LABEL = new HashMap<>();

    static {
        for (ReadableEnchantments e: values()) {
            BY_LABEL.put(e.findByString, e);
        }
    }
    private static final Map<Enchantment, ReadableEnchantments> BY_ENCHANTMENT = new HashMap<>();
    static {
        for (ReadableEnchantments e: values()) {
            BY_ENCHANTMENT.put(e.correspondingValue, e);
        }
    }
    public static ReadableEnchantments valueOfLabel(String label) {
        return BY_LABEL.get(label);
    }
    public static ReadableEnchantments valueOfEnchantment(Enchantment e) {
        return BY_ENCHANTMENT.get(e);
    }
    public static List<ReadableEnchantments> toReadableEnchantment(List<Enchantment> input) {
        List<ReadableEnchantments> reList = new ArrayList<>();
        for (Enchantment e : input) {
            reList.add(valueOfEnchantment(e));
        }
        return reList;
    }
    public static Map<ReadableEnchantments, Integer> toReadableEnchantmentMap(Map<Enchantment, Integer> input) {
        Map<ReadableEnchantments, Integer> reList = new HashMap<>();
        for (Enchantment e : input.keySet()) {
            reList.put(valueOfEnchantment(e), input.get(e));
        }
        return reList;
    }
}
