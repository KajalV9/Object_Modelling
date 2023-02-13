package com.crio.jukebox.services;

import com.crio.jukebox.entities.Song;

public interface ISongService {
    public Song create(String songName, String genre, String albumName, String artistName, String artists);
}
