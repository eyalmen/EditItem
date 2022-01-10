package me.ma.edititem.utils;

import org.bukkit.ChatColor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextFunctions {
    public static String tColor(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }
    public static String removeWordsFromText(String text) {
        Pattern stopWords = Pattern.compile("(§0|§1|§2|§3|§4|§5|§6|§7|§8|§9|§a|§b|§c|§d|§e|§f)", Pattern.CASE_INSENSITIVE);
        Matcher matcher = stopWords.matcher(text);
        return matcher.replaceAll("");
    }
    public static char getCharForNumber(int i) {
        char[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
        return Character.toString(alphabet[i]).charAt(0);
    }
    public static String convertObjectArrayToString(Object[] arr, String delimiter) {
        StringBuilder sb = new StringBuilder();
        for (Object obj : arr)
            sb.append(obj.toString()).append(delimiter);
        return sb.substring(0, sb.length() - 1);
    }
}

