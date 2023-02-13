package com.crio.jukebox.commands;

import java.util.ArrayList;
import java.util.List;
import com.crio.codingame.exceptions.UserNotFoundException;
import com.crio.jukebox.entities.Playlist;
import com.crio.jukebox.exceptions.SongNotAvailableException;
import com.crio.jukebox.services.IPlaylistService;

public class CreatePlaylistCommand implements ICommand {

    private final IPlaylistService playlistService;



    public CreatePlaylistCommand(IPlaylistService playlistService) {
        this.playlistService = playlistService;
    }


//CREATE-PLAYLIST 1 MY_PLAYLIST_1 1 4 5 6

    @Override
    public void execute(List<String> tokens) {
        // System.out.println(tokens);
        String userID = tokens.get(1);
        String playlistname = tokens.get(2);
        List<String> songIds = new ArrayList<>();
        for(int i=3 ;i<tokens.size();i++){
             songIds.add(tokens.get(i));
        }
        try{
            Playlist playlist = playlistService.createPlaylist(userID, playlistname, songIds);
            System.out.println("Playlist ID " + playlist.getId());
        }
        catch(SongNotAvailableException s){
            System.out.println(s.getMessage());
        }
        catch(UserNotFoundException u){
            System.out.println(u.getMessage());
        }
}

}
