package com.crio.jukebox.commands;

import java.util.ArrayList;
import java.util.List;
import com.crio.jukebox.dto.PlaylistDTO;
import com.crio.jukebox.exceptions.SongNotAvailableException;
import com.crio.jukebox.exceptions.UserNotFoundException;
import com.crio.jukebox.services.IPlaylistService;

public class ModifyPlaylistCommand implements ICommand {

    private IPlaylistService playlistService;

    public ModifyPlaylistCommand(IPlaylistService playlistService) {
        this.playlistService = playlistService;
    }


    //MODIFY-PLAYLIST ADD-SONG 1 1 7
    //MODIFY-PLAYLIST DELETE-SONG 1 1 7
    @Override
    public void execute(List<String> tokens) {

        String operationPlaylist = tokens.get(1);
        String userId = tokens.get(2);
        String playlistId = tokens.get(3);
        List<String> songId = new ArrayList<>();
        for(int i=4 ;i<tokens.size();i++){
                songId.add(tokens.get(i));
           }
      

      
        if(operationPlaylist.equals("ADD-SONG")){
            try{
                PlaylistDTO playlist = playlistService.modifyPlaylistAdd(userId, playlistId, songId);
                System.out.println(playlist);

            }catch(SongNotAvailableException s){
                System.out.println(s.getMessage());
            }catch(UserNotFoundException e){
                System.out.println(e.getMessage());
            }
        }

        else{
            try{
                PlaylistDTO playlist = playlistService.modifyPlaylistDelete(userId, playlistId, songId);
                System.out.println(playlist);

            }catch(SongNotAvailableException s){
                System.out.println(s.getMessage());
            }catch(UserNotFoundException e){
                System.out.println(e.getMessage());
            }
        }
    }
    
}
