package me.ma.edititem.utils.readable;

import org.bukkit.Color;

import java.util.HashMap;
import java.util.Map;

public enum ReadableColors {
    WHITE("white",Color.WHITE),
    SILVER("silver",Color.SILVER),
    GRAY("gray",Color.GRAY),
    BLACK("black",Color.BLACK),
    RED("red",Color.RED),
    MAROON("maroon",Color.MAROON),
    YELLOW("yellow",Color.YELLOW),
    OLIVE("olive",Color.OLIVE),
    LIME("lime",Color.LIME),
    GREEN("green",Color.GREEN),
    AQUA("aqua",Color.AQUA),
    TEAL("teal",Color.TEAL),
    BLUE("blue",Color.BLUE),
    NAVY("navy",Color.NAVY),
    FUCHSIA("fuchsia",Color.FUCHSIA),
    PURPLE("purple",Color.PURPLE),
    ORANGE("orange",Color.ORANGE);

    public final Color correspondingValue;
    public final String findByString;

    ReadableColors(String findByString, Color correspondingValue) {
        this.correspondingValue = correspondingValue;
        this.findByString = findByString;
    }
    private static final Map<String, ReadableColors> BY_LABEL = new HashMap<>();
    static {
        for (ReadableColors e: values()) {
            BY_LABEL.put(e.findByString, e);
        }
    }
    public static ReadableColors valueOfLabel(String label) {
        return BY_LABEL.get(label);
    }
}
