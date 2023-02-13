package com.crio.jukebox.repositories;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import com.crio.jukebox.entities.Playlist;
import com.crio.jukebox.entities.Song;

public class PlaylistRepository implements IPlaylistRepository{


    private final Map<String,Playlist> playlistMap;
    private Integer autoIncrement = 0;

    
    public PlaylistRepository() {
        playlistMap = new LinkedHashMap<String,Playlist>();
    }

    public PlaylistRepository(LinkedHashMap<String, Playlist> playlistMap) {
        this.playlistMap = playlistMap;
    }

    @Override
    public Playlist save(Playlist playlist) {
        if(playlist.getId() == null){
            autoIncrement++;
            Playlist p = new Playlist(Integer.toString(autoIncrement),playlist.getName(),playlist.getSongList());
            playlistMap.put(p.getId(),p);
            return p;
        }
        playlistMap.put(playlist.getId(),playlist);
        return playlist;
    }

    @Override
    public List<Playlist> findAll() {
        // TODO Auto-generated method stub
        return new ArrayList<>(playlistMap.values());
    }

    @Override
    public Optional<Playlist> findById(String id) {
        return Optional.ofNullable(playlistMap.get(id));
    }

    @Override
    public boolean existsById(String id) {
        // TODO Auto-generated method stub
        return false;
    }
    @Override
    public void deleteById(String id) {
        if(playlistMap.containsKey(id)){
            playlistMap.remove(id);
        }
    }

    public Playlist getPlaylist(String playlistid){

        return playlistMap.get(playlistid);

        // return playlistMap.stream().filter(e -> playlist.equals(e)).findAny().orElse(null);
    }

    public List<String> addSong(String playlistid, List<Song> songs) {

      
        Playlist p = getPlaylist(playlistid);

        if (p != null) {
            for (int i = 0; i < songs.size(); i++) {
                if (!p.getSongList().contains(songs.get(i))) {
                    p.getSongList().add(songs.get(i));
                }
            }
        }

        // List<String> songids = new ArrayList<>();
        // for(Song s : p.getSongList()){
        //     songids.add(s.getId());
        // }
        List<String> songids =  p.getSongList().stream().map(Song::getId).collect(Collectors.toList());
        return songids;

    }

    public List<String> removeSong(String playlistid, List<Song> songs) {

        Playlist p = getPlaylist(playlistid);
        if (p != null) {
            for (int i = 0; i < songs.size(); i++) {
                if (p.getSongList().contains(songs.get(i))) {
                    p.getSongList().remove(songs.get(i));
                }
            }
        }
        List<String> songid = p.getSongList().stream().map(Song::getId).collect(Collectors.toList());
        return songid;

    }


    public boolean getSelectedSong(String p ,String sid){
        Playlist playlist = getPlaylist(p);
        List<String> songIdList = playlist.getSongList().stream().map(Song::getId).collect(Collectors.toList());
        if(songIdList.contains(sid))
        return true;
        else
        return false;
    }
    
}
