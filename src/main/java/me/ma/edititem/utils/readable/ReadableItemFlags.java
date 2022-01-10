package me.ma.edititem.utils.readable;

import org.bukkit.inventory.ItemFlag;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum ReadableItemFlags {
    HIDE_ENCHANTMENTS("HIDE_ENCHANTMENTS", ItemFlag.HIDE_ENCHANTS),
    HIDE_ATTRIBUTES("HIDE_ATTRIBUTES", ItemFlag.HIDE_ATTRIBUTES),
    HIDE_UNBREAKABLE("HIDE_UNBREAKABLE", ItemFlag.HIDE_UNBREAKABLE),
    HIDE_WHAT_IT_DESTROYS("HIDE_WHAT_IT_DESTROYS", ItemFlag.HIDE_DESTROYS),
    HIDE_WHAT_IT_CAN_BE_PLACED_ON("HIDE_WHAT_IT_CAN_BE_PLACED_ON", ItemFlag.HIDE_PLACED_ON),
    HIDE_POTION_EFFECTS("HIDE_POTION_EFFECTS", ItemFlag.HIDE_POTION_EFFECTS),
    HIDE_DYED_ON_LEATHER("HIDE_DYED_ON_LEATHER", ItemFlag.HIDE_DYE);

    public final ItemFlag correspondingValue;
    public final String findByString;

    ReadableItemFlags(String findByString, ItemFlag correspondingValue) {
        this.correspondingValue = correspondingValue;
        this.findByString = findByString;
    }
    private static final Map<ItemFlag, ReadableItemFlags> BY_FLAG = new HashMap<>();
    private static final Map<String, ReadableItemFlags> BY_LABEL = new HashMap<>();
    static {
        for (ReadableItemFlags e: values()) {
            BY_LABEL.put(e.findByString, e);
        }
    }
    static {
        for (ReadableItemFlags e: values()) {
            BY_FLAG.put(e.correspondingValue, e);
        }
    }
    public static ReadableItemFlags valueOfLabel(String label) {
        return BY_LABEL.get(label);
    }
    public static ReadableItemFlags valueOfFlag(ItemFlag e) {
        return BY_FLAG.get(e);
    }
    public static List<ReadableItemFlags> toReadableFlag(List<ItemFlag> input) {
        List<ReadableItemFlags> reList = new ArrayList<>();
        for (ItemFlag e : input) {
            reList.add(valueOfFlag(e));
        }
        return reList;
    }
    public static Map<ReadableItemFlags, Boolean> toReadableItemFlagMap(Map<ItemFlag, Boolean> input) {
        Map<ReadableItemFlags, Boolean> reList = new HashMap<>();
        for (ItemFlag e : input.keySet()) {
            reList.put(valueOfFlag(e), input.get(e));
        }
        return reList;
    }
}
