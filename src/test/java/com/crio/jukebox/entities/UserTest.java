package com.crio.jukebox.entities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("UserTest")
public class UserTest {
    @Test
    @DisplayName("checkIFPlaylistExists should Return true If Playlist is Found")
    public void checkIFPlaylistExists_ShouldReturnTrue_GivenPlaylist(){
        //Arrange
        List<String> list = Arrays.asList(new String("SideArtist1"), new String("SideArtist2"));
        List<Song> song1 =  new ArrayList<Song>(){
            {
            add(new Song("1", "Song1","Genre1","Album1","Artist1",list));
            add(new Song("2", "Song2","Genre2","Album2","Artist2",list));
            add(new Song("3", "Song3","Genre3","Album3","Artist3" ,list ));
            }
        };

        String pname = "Playlist1";
        String id = "1";
        Playlist expectedplaylist = new Playlist(id, pname, song1);
        
         User user = new User("1","User1", new ArrayList<Playlist>(){{ add(expectedplaylist); }});

 
         //Act
         Assertions.assertTrue(user.checkIFPlaylistExists(expectedplaylist));

    }

    @Test
    @DisplayName("checkIfPlaylistExists should Return False If No Playlist is Found")
    public void checkIfPlaylistExists_ShouldReturnFalse_GivenContest(){
        //Arrange
        List<String> list = Arrays.asList(new String("SideArtist1"), new String("SideArtist2"));
        List<Song> song1 =  new ArrayList<Song>(){
            {
            add(new Song("1", "Song1","Genre1","Album1","Artist1",list));
            add(new Song("2", "Song2","Genre2","Album2","Artist2",list));
            add(new Song("3", "Song3","Genre3","Album3","Artist3" ,list ));
            }
        };
        String pname = "Playlist1";
        String id = "1";
        Playlist playlist = new Playlist(id, pname, song1);
        User user = new  User("1","User1");
        
        //Act
        Assertions.assertFalse(user.checkIFPlaylistExists(playlist));
    }
}
