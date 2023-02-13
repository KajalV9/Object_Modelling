package com.crio.jukebox.repositories;

import java.util.List;
import com.crio.jukebox.entities.Playlist;
import com.crio.jukebox.entities.Song;

public interface IPlaylistRepository extends CRUDRepository<Playlist,String>{
    public List<String> addSong(String playlistid, List<Song> songs);
    public List<String> removeSong(String playlistid, List<Song> songs);
    public boolean getSelectedSong(String p ,String sid);
}
