package com.crio.jukebox.entities;

import java.util.List;
import java.util.stream.Collectors;

public class Playlist extends BaseEntity {
    private final String name;
    List<Song> songList;


    public Playlist(String id, String name, List<Song> songList) {
        this.id = id;
        this.name = name;
        this.songList = songList;
    }


    public Playlist(String name, List<Song> songList) {
        this.name = name;
        this.songList = songList;
    }


    public String getName() {
        return name;
    }


    public List<Song> getSongList() {
        return songList;
    }



    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((songList == null) ? 0 : songList.hashCode());
        return result;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Playlist other = (Playlist) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (songList == null) {
            if (other.songList != null)
                return false;
        } else if (!songList.equals(other.songList))
            return false;
        return true;
    }


    public List<Song> getSongs() {
        return songList.stream().collect(Collectors.toList());
    }



}
