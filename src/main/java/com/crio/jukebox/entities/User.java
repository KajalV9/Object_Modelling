package com.crio.jukebox.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class User extends BaseEntity {

    private final String name;
    private List<Playlist> listPlaylist; 


    public User(String name) {
        this.name = name;
    }


    public User(String id, String name, List<Playlist> listPlaylist) {
        this.id = id;
        this.name = name;
        this.listPlaylist = listPlaylist;
    }



    public User(String id, String name) {
        this.id = id;
        this.name = name;
        this.listPlaylist = new ArrayList<>();
    }



    public String getName() {
        return name;
    }


    @Override
    public String toString() {
        return "User [id=" + id + ",name=" + name + "]";
    }



    public boolean checkIFPlaylistExists(Playlist playlist) {
        for (int i = 0; i < listPlaylist.size(); i++) {
            if (listPlaylist.get(i).equals(playlist))
                return true;
        }
        return false;
    }

    public Playlist getPlaylist(Playlist playlist){
        return listPlaylist.stream().filter(e -> playlist.equals(e)).findAny().orElse(null);
    }

    public void addPlaylist(Playlist playlist) {
        listPlaylist.add(playlist);
    }

    public boolean deletePlaylist(Playlist playlist) {
        boolean check = listPlaylist.removeIf(c -> c.getId() == playlist.getId());
        if(check)
        return true;
        else
        return false;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((listPlaylist == null) ? 0 : listPlaylist.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
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
        User other = (User) obj;
        if (listPlaylist == null) {
            if (other.listPlaylist != null)
                return false;
        } else if (!listPlaylist.equals(other.listPlaylist))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }



    public Song getFirstSong(Playlist playlist) {

        Playlist p = listPlaylist.stream().filter(e -> playlist.equals(e)).findAny().orElse(null);


        List<Song> song = null;
        Song s = null;
        if (p != null) {

            song = p.getSongList();
            s = song.get(0);
        }
        return s;

    }

 

    public List<String> getSongIDList(Playlist playlist){

        // Playlist p;
        // int id = Integer.valueOf(playlist.getId());
        // p = playlistmap.get(id);

        // for(int i = 0 ; i < playlistmap.size() ; i++){
        //     if(playlistmap.contains(playlist)){
        //         p = playlistmap.get(id);
        //     }
        // }

        Playlist p = getPlaylist(playlist);
        List<String> songid = p.getSongList().stream().map(Song::getId).collect(Collectors.toList());
        return songid;
   
    }
    public Song getSong(Playlist playlist,String songIndex){
        Playlist p = getPlaylist(playlist);
        // List<String> songids = p.getSongList().stream().map(Song::getId).collect(Collectors.toList());
        List<Song> songs = p.getSongList();
        Song s = null;
        int intSongId = Integer.valueOf(songIndex);
        for(int i = 0 ; i <  p.getSongList().size() ; i++){
            if(p.getSongList().contains(songs.get(intSongId))){
                s = songs.get(intSongId);
                break;
            }
        }  
        // for (int i = 0; i < p.getSongList().size(); i++) {
        //     if (p.getSongList().contains(song.get(intSongId))) {
        //         s = song.get(intSongId);
        //         return s;
        //     }
        // }
        return s;
    }

  


}
