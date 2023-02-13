package com.crio.jukebox.commands;

import java.util.List;
import com.crio.codingame.exceptions.UserNotFoundException;
import com.crio.jukebox.exceptions.PlaylistNotAvailableException;
import com.crio.jukebox.services.IPlaylistService;

public class DeletePlaylistCommand implements ICommand {

    private IPlaylistService playlistService;

    public DeletePlaylistCommand(IPlaylistService playlistService) {
        this.playlistService = playlistService;
    }

    //DELETE-PLAYLIST 1 2

    @Override
    public void execute(List<String> tokens) {
        try{
            String message = playlistService.deletePlaylist(tokens.get(1), tokens.get(2)); 
            System.out.println(message);
            }catch(UserNotFoundException e){
                System.out.println(e.getMessage());
            }
            catch(PlaylistNotAvailableException e){
                System.out.println(e.getMessage());
            } 
    }
}
