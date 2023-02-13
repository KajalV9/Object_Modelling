package com.crio.jukebox.commands;

import java.util.List;
import com.crio.codingame.exceptions.UserNotFoundException;
import com.crio.jukebox.dto.SongDTO;
import com.crio.jukebox.exceptions.EmptyPlaylistException;
import com.crio.jukebox.services.IPlaylistService;

public class PlayPlaylistCommand implements ICommand {

    private IPlaylistService playlistService;


    public PlayPlaylistCommand(IPlaylistService playlistService) {
        this.playlistService = playlistService;
    }


    //PLAY-PLAYLIST 1 1

    @Override
    public void execute(List<String> tokens) {
        try{
            SongDTO song = playlistService.playPlaylist(tokens.get(1), tokens.get(2));
            System.out.println(song);
            }catch(UserNotFoundException e){
                System.out.println(e.getMessage());
            }
            catch(EmptyPlaylistException e){
                System.out.println(e.getMessage());
            }         
    }
    
}
