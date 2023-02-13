package com.crio.jukebox.services;

import com.crio.jukebox.entities.Song;
import com.crio.jukebox.repositories.ISongRepository;

public class SongService implements ISongService {
    private ISongRepository songRepository;

    public SongService(ISongRepository songRepository) {
        this.songRepository = songRepository;
    }

    @Override
    public Song create(String songName, String genre, String albumName, String artistName,
            String artists) {
        Song song = (Song) songRepository
                .save(new Song(songName, genre, albumName, artistName, artists));
        return song;
    }

}
