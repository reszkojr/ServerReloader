package me.santres.serverreloader;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static java.nio.file.StandardWatchEventKinds.*;

public class FileEventWatcher {

    private final List<Observer> observers = new ArrayList<>();
    private static WatchKey key;

    public FileEventWatcher(JavaPlugin plugin) {
        try {
            Path pluginsFolder = Paths.get(plugin.getDataFolder().getParent());
            key = pluginsFolder.register(FileSystems.getDefault().newWatchService(), ENTRY_CREATE, ENTRY_DELETE, ENTRY_MODIFY);
        } catch (Exception e) {
            ServerReloader.sendConsoleMessage(ChatColor.RED + "It was not possible to initialize ServerReloader!");
            ServerReloader.sendConsoleMessage(e.getMessage());
            e.printStackTrace();
        }
        Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, () -> {
            for (WatchEvent<?> event : key.pollEvents()) {
                WatchEvent.Kind<?> kind = event.kind();
                if (kind == OVERFLOW) {
                    continue;
                }
                String filename = event.context().toString();
                //ServerReloader.sendConsoleMessage("File: " + filename + ", " + "Event: " + kind);
                notifyObservers(new UpdatedFile(filename, kind));
            }
            key.reset();
        }, 20 * 3, 0);
    }

    public void addObserver(Observer obs) {
        observers.add(obs);
    }

    public void notifyObservers(UpdatedFile file) {
        for (Observer obs : observers) {
            obs.update(file);
        }
    }
}
