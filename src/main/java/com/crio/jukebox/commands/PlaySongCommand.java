package com.crio.jukebox.commands;

import java.util.List;
import com.crio.codingame.exceptions.UserNotFoundException;
import com.crio.jukebox.dto.SongDTO;
import com.crio.jukebox.entities.ActionEnum;
import com.crio.jukebox.exceptions.SongNotAvailableException;
import com.crio.jukebox.services.IPlaylistService;

public class PlaySongCommand implements ICommand {

    private IPlaylistService playlistService;


    public PlaySongCommand(IPlaylistService playlistService) {
        this.playlistService = playlistService;
    }


    // PLAY-SONG 1 5
    // PLAY-SONG 1 NEXT
    // PLAY-SONG 1 BACK

    @Override
    public void execute(List<String> tokens) {
        String userID = tokens.get(1);
        String check = tokens.get(2);
        if (check.equalsIgnoreCase("NEXT") || check.equalsIgnoreCase("BACK")) {
            try {
                SongDTO song = playlistService.playSong(userID, ActionEnum.valueOf(check));
                System.out.println(song);

            } catch (UserNotFoundException e) {
                System.out.println(e.getMessage());
            }

        } else {
            try {
                SongDTO song = playlistService.playSelectedSong(userID, check);
                System.out.println(song);
            } catch (UserNotFoundException e) {
                System.out.println(e.getMessage());
            } catch (SongNotAvailableException e) {
                System.out.println(e.getMessage());
            }
        }


    }

}
