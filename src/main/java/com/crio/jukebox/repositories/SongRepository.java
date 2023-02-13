package com.crio.jukebox.repositories;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import com.crio.jukebox.entities.Song;

public class SongRepository implements ISongRepository{


    private final Map<String,Song> songMap;
    private Integer autoIncrement = 0;
    


    public SongRepository(Map<String, Song> actualSongList) {
        this.songMap = actualSongList;
        this.autoIncrement = songMap.size();
    }

    public SongRepository() {
        this.songMap = new LinkedHashMap<String,Song>();
       
    }

    @Override
    public Song save(Song song) {
        if(song.getId() == null){
            autoIncrement++;
            Song s = new Song(Integer.toString(autoIncrement), song.getSname() , song.getGenre() ,song.getAlbum_name() ,song.getAlbum_artist_name() , song.getSide_artist_name());
            songMap.put(s.getId(),s);
            return s;
        }
        songMap.put(song.getId(),song);
        return song;
    }

    @Override
    public List<Song> findAll() {
        // TODO Auto-generated method stub
        return new ArrayList<>(songMap.values());
    }

    @Override
    public Optional<Song> findById(String id) {
        return Optional.ofNullable(songMap.get(id));
    }

    @Override
    public boolean existsById(String id) {
        // TODO Auto-generated method stub
        return false;
    }

  

    @Override
    public void deleteById(String id) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public List<Song> getAllSongs() {
        // TODO Auto-generated method stub
        return new ArrayList<>(songMap.values());

    }

  
    
    @Override
    public List<Song> getListSongsByID(List<String> id) {

        // List<Song> song = new ArrayList<>();
        // for(int i = 0 ; i < id.size() ; i++){
        //     if(songMap.containsKey(id.get(i)))
        //       song.add(songMap.get(id.get(i)));
        // }
        // return song;

        
        return songMap.values().stream().filter(i->id.contains(i.getId())).collect(Collectors.toList());
    
    }    

    public Song getSongbyId(String songId){

        // for(int i = 0 ; i < songMap.size() ; i++){
        //     if(songMap.containsKey(songId))
        //     return songMap.get(songId);
        // }
        // return 
        return songMap.values().stream().filter(c ->songId.equals(c.getId())).findAny().orElse(null);



    }
}
