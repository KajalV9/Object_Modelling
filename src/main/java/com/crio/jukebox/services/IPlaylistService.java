package com.crio.jukebox.services;

import java.util.List;
import com.crio.jukebox.dto.PlaylistDTO;
import com.crio.jukebox.dto.SongDTO;
import com.crio.jukebox.entities.ActionEnum;
import com.crio.jukebox.entities.Playlist;
import com.crio.jukebox.exceptions.EmptyPlaylistException;
import com.crio.jukebox.exceptions.PlaylistNotAvailableException;
import com.crio.jukebox.exceptions.SongNotAvailableException;

public interface IPlaylistService {
    public Playlist createPlaylist(String userId , String playlistname , List<String> songId) throws SongNotAvailableException;
    public String deletePlaylist(String userId ,String playlistId) throws PlaylistNotAvailableException;
    public PlaylistDTO modifyPlaylistAdd(String userId , String playlistId , List<String> songId) throws SongNotAvailableException;
    public PlaylistDTO modifyPlaylistDelete(String userId , String playlistId , List<String> songId) throws SongNotAvailableException;
    public SongDTO playPlaylist(String userId , String playlistId) throws EmptyPlaylistException;
    public SongDTO playSelectedSong(String userId, String songId) throws SongNotAvailableException;
    public SongDTO playSong(String userId , ActionEnum action);
}
