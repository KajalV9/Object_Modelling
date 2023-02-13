package com.crio.jukebox.repositories;

import java.util.List;
import com.crio.jukebox.entities.Song;

public interface ISongRepository extends CRUDRepository<Song,String> {
    List<Song> getAllSongs();
    List<Song> getListSongsByID(List<String> id);
    public Song getSongbyId(String id);
    
    

}
