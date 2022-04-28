package me.santres.serverreloader;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class ServerReloader extends JavaPlugin {

    @Override
    public void onEnable() {
        sendConsoleMessage(ChatColor.GREEN + "ServerReloader started.");
    }


    @Override
    public void onDisable() {
        sendConsoleMessage(ChatColor.RED + "Stopping ServerReloader");
    }

    public static void sendConsoleMessage(String msg) {
        Bukkit.getServer().getConsoleSender().sendMessage(msg);
    }
}
