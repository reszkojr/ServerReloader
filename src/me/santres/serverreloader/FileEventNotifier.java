package me.santres.serverreloader;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import java.nio.file.StandardWatchEventKinds;

import static java.nio.file.StandardWatchEventKinds.*;

public class FileEventNotifier implements Observer {

    private FileEventWatcher watcher;

    public FileEventNotifier(FileEventWatcher watcher) {
        watcher.addObserver(this);
    }:wq

    @Override
    public void update(UpdatedFile file) {
        ServerReloader.sendConsoleMessage("File: " + file.getName() + ", Event: " + file.getEventKind());
        String verb;
        if (ENTRY_CREATE == file.getEventKind()) {
            verb = "added";
        } else if (ENTRY_MODIFY == file.getEventKind()) {
            verb = "modified";
        } else if (ENTRY_DELETE == file.getEventKind()) {
            verb = "deleted";
        } else {
            verb = "nhamnhamnhamnham";
        }

        Bukkit.broadcastMessage(ChatColor.GREEN + file.getName() + " was " + verb + "!");
        Bukkit.broadcastMessage(ChatColor.GREEN + "Reloading server...");
        Bukkit.getServer().reload();
    }
}
