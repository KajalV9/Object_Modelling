package com.crio.jukebox.dto;

import java.util.List;

public class PlaylistDTO {
    
    private final String playlistid;
    private final String playlistname;
    private final List<String> songid;
    public PlaylistDTO(String playlistid, String playlistname, List<String> songid) {
        this.playlistid = playlistid;
        this.playlistname = playlistname;
        this.songid = songid;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((playlistid == null) ? 0 : playlistid.hashCode());
        result = prime * result + ((playlistname == null) ? 0 : playlistname.hashCode());
        result = prime * result + ((songid == null) ? 0 : songid.hashCode());
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
        PlaylistDTO other = (PlaylistDTO) obj;
        if (playlistid == null) {
            if (other.playlistid != null)
                return false;
        } else if (!playlistid.equals(other.playlistid))
            return false;
        if (playlistname == null) {
            if (other.playlistname != null)
                return false;
        } else if (!playlistname.equals(other.playlistname))
            return false;
        if (songid == null) {
            if (other.songid != null)
                return false;
        } else if (!songid.equals(other.songid))
            return false;
        return true;
    }
    @Override
    public String toString() {
        return "PlaylistDTO [playlistid=" + playlistid + ", playlistname=" + playlistname
                + ", songid=" + songid + "]";
    }
  
    
}
