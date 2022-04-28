package me.santres.serverreloader;

import org.bukkit.Bukkit;

public class FileEventNotifier implements Observer {

    private FileEventWatcher watcher;

    public FileEventNotifier(FileEventWatcher watcher) {
        watcher.addObserver(this);
    }

    @Override
    public void update(UpdatedFile file) {
        ServerReloader.sendConsoleMessage("File: " + file.getName() + ", Event: " + file.getEventKind());
        Bukkit.getServer().reload();
    }
}
