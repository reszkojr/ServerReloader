package me.santres.serverreloader;

import java.nio.file.WatchEvent;

public class UpdatedFile {
    private String name;
    private WatchEvent.Kind<?> eventKind;

    public UpdatedFile(String name, WatchEvent.Kind<?> eventKind) {
        this.name = name;
        this.eventKind = eventKind;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public WatchEvent.Kind<?> getEventKind() {
        return eventKind;
    }

    public void setEventKind(WatchEvent.Kind<?> eventKind) {
        this.eventKind = eventKind;
    }
}
