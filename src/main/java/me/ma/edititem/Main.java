package me.ma.edititem;

import me.ma.edititem.commands.EditItem;
import me.ma.edititem.commands.Help;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {
    @Override
    public void onEnable() {
        getCommand("EditItem").setExecutor(new EditItem());
        getCommand("eihelp").setExecutor(new Help());
    }
}
