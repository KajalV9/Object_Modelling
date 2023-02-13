package com.crio.jukebox.services;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import com.crio.jukebox.dto.SongDTO;
import com.crio.jukebox.entities.Playlist;
import com.crio.jukebox.entities.Song;
import com.crio.jukebox.entities.User;
import com.crio.jukebox.exceptions.PlaylistNotAvailableException;
import com.crio.jukebox.exceptions.SongNotAvailableException;
import com.crio.jukebox.exceptions.UserNotFoundException;
import com.crio.jukebox.repositories.IPlaylistRepository;
import com.crio.jukebox.repositories.ISongRepository;
import com.crio.jukebox.repositories.IUserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@DisplayName("PlaylistServiceTest")
@ExtendWith(MockitoExtension.class)
public class PlaylistServiceTest {


    @Mock
    private ISongRepository songRepositoryMock;
    

    @Mock
    private IUserRepository userRepositoryMock;

    @Mock
    private IPlaylistRepository playlistRepositoryMock;

    @InjectMocks
    private PlaylistService playlistService;



  



    @Test
    @DisplayName("create method Should Throw SongNotFoundException If No Songs Found in Repository for Given ID")
    public void create_ShouldThrowSongNotFoundException() {
        // Arrange

        List<String> songid = new ArrayList<>();
        songid.add("5");
        songid.add("50");
        songid.add("52");



        when(songRepositoryMock.getListSongsByID(songid)).thenReturn(Collections.emptyList());

        // Act and Assert
        Assertions.assertThrows(SongNotAvailableException.class,
                () -> playlistService.createPlaylist("2", "Playlist1", songid));
        // verify(songRepositoryMock,times(1)).getSongsByID(ArgumentMatchers.anyListOf(String.class));

        verify(songRepositoryMock, times(1))
                .getListSongsByID((ArrayList<String>) ArgumentMatchers.<String>anyList());

    }



    @Test
    @DisplayName("create method Should Throw UserNotFound If No USer Found in Repository for Given ID")
    public void create_ShouldThrowUserNotFoundException() {

        // Arrange
        final List<String> songid = new ArrayList<String>() {
            {
                add("1");
                add("2");
            }
        };
        List<String> list = Arrays.asList(new String("SideArtist1"), new String("SideArtist2"));
        List<Song> songs = new ArrayList<Song>() {
            {
                add(new Song("1", "Song1", "Genre1", "Album1", "Artist1", list));
                add(new Song("2", "Song2", "Genre2", "Album2", "Artist2", list));
            }
        };

        when(songRepositoryMock.getListSongsByID(songid)).thenReturn(songs);
        when(userRepositoryMock.findById(anyString())).thenReturn(Optional.empty());

        // Act and Assert
        Assertions.assertThrows(UserNotFoundException.class,
                () -> playlistService.createPlaylist("1", "Playlist1", songid));
        verify(userRepositoryMock, times(1)).findById(anyString());

    }

    @Test
    @DisplayName("create method Should Create Playlist with List of Songs Id")
    public void create_ShouldCreatePlaylist_GivenListSongsID() {
        // Arrange
        final List<String> songid = new ArrayList<String>() {
            {
                add("1");
                add("2");
            }
        };

        List<String> list = Arrays.asList(new String("SideArtist1"), new String("SideArtist2"));
        List<Song> songs = new ArrayList<Song>() {
            {
                add(new Song("1", "Song1", "Genre1", "Album1", "Artist1", list));
                add(new Song("2", "Song2", "Genre2", "Album2", "Artist2", list));
            }
        };


        Playlist expectedPlaylist = new Playlist("1", "Playlist1", songs);
        User user = new User("1", "User1");

        when(songRepositoryMock.getListSongsByID(songid)).thenReturn(songs);
        when(playlistRepositoryMock.save(any(Playlist.class))).thenReturn(expectedPlaylist);
        when(userRepositoryMock.findById(user.getId())).thenReturn(Optional.of(user));


        // Act
        Playlist actuaPlaylist = playlistService.createPlaylist("1", "Playlist1", songid);

        // Assert

        Assertions.assertEquals(expectedPlaylist, actuaPlaylist);

        verify(songRepositoryMock, times(1))
                .getListSongsByID((ArrayList<String>) ArgumentMatchers.<String>anyList());
    }


    @Test
    @DisplayName("modifyPlaylistAdd method should Throw User Not Found Exception if No User Found")
    public void modifyPlaylistAdd_shouldThrowUserNotFoundException() {
        // Arrange
        final List<String> songid = new ArrayList<String>() {
            {
                add("1");
                add("2");
            }
        };
        when(userRepositoryMock.findById(anyString())).thenReturn(Optional.empty());
        // Act and Assert

        Assertions.assertThrows(UserNotFoundException.class,
                () -> playlistService.modifyPlaylistAdd("1", "2", songid));
        verify(userRepositoryMock, times(1)).findById(anyString());

    }

    @Test
    @DisplayName("modifyPlaylistAdd method should Throw Playlist Not Available Exception if No Playlist Found")
    public void modifyPlaylistAdd_shouldThrowPlaylistNotAvailableException() {
        // Arrange
        final List<String> songid = new ArrayList<String>() {
            {
                add("1");
                add("2");
            }
        };
        User user = new User("1", "User1");
        when(userRepositoryMock.findById(user.getId())).thenReturn(Optional.of(user));
        when(playlistRepositoryMock.findById(anyString())).thenReturn(Optional.empty());
        Assertions.assertThrows(PlaylistNotAvailableException.class,
                () -> playlistService.modifyPlaylistAdd("1", "4", songid));
        verify(playlistRepositoryMock, times(1)).findById(anyString());
    }



    @Test
    @DisplayName("create method Should Throw SongNotFoundException If No Songs Found in Repository for Given ID")
    public void modifyPlaylistAdd_shouldThrowSongNotAvailableException() {
        // Arrange

        List<String> songid = new ArrayList<>();
        songid.add("5");
        songid.add("50");
        songid.add("52");

        List<String> list = Arrays.asList(new String("SideArtist1"), new String("SideArtist2"));
        List<Song> songs = new ArrayList<Song>() {
            {
                add(new Song("1", "Song1", "Genre1", "Album1", "Artist1", list));
                add(new Song("2", "Song2", "Genre2", "Album2", "Artist2", list));
            }
        };

        User user = new User("1", "User1");
        Playlist playlist = new Playlist("1", "Playlist1", songs);

        when(userRepositoryMock.findById(user.getId())).thenReturn(Optional.of(user));
        when(playlistRepositoryMock.findById(playlist.getId())).thenReturn(Optional.of(playlist));
        when(songRepositoryMock.getListSongsByID(songid)).thenReturn(Collections.emptyList());

        // Act and Assert
        Assertions.assertThrows(SongNotAvailableException.class,
                () -> playlistService.modifyPlaylistAdd("1", "1", songid));
        // verify(songRepositoryMock,times(1)).getSongsByID(ArgumentMatchers.anyListOf(String.class));

        verify(songRepositoryMock, times(1)).getListSongsByID((ArrayList<String>) ArgumentMatchers.<String>anyList());

    }



    @Test
    @DisplayName("playPlaylist method Should return current Song for given user id and playlist id")
    public void playPlaylist_shouldReturnSong() {
        // Arrange


        List<String> list = Arrays.asList(new String("SideArtist1"), new String("SideArtist2"));
        List<Song> songs = new ArrayList<Song>() {
            {
                add(new Song("1", "Song1", "Genre1", "Album1", "Artist1", list));
                add(new Song("2", "Song2", "Genre2", "Album2", "Artist2", list));
            }
        };

        Playlist playlist = new Playlist("1", "Playlist1", songs);
        User user = new User("1", "User1");
        user.addPlaylist(playlist);
        Song expectedsong = user.getFirstSong(playlist);
        SongDTO expectedSongDTO = new SongDTO(expectedsong.getId(),expectedsong.getSname(), expectedsong.getAlbum_name(), expectedsong.getAlbum_artist_name());

        when(userRepositoryMock.findById(user.getId())).thenReturn(Optional.of(user));
        when(playlistRepositoryMock.findById(playlist.getId())).thenReturn(Optional.of(playlist));

        // Act
        SongDTO actualSongDTO = playlistService.playPlaylist(user.getId(), playlist.getId());

        // Assert
        Assertions.assertEquals(expectedSongDTO,actualSongDTO);
        verify(userRepositoryMock, times(1)).findById(anyString());
        verify(playlistRepositoryMock, times(1)).findById(anyString());
    }


    @Test
    @DisplayName("playPlaylist method should Throw User Not Found Exception if No User Found")
    public void playPlaylist_shouldThrowUserNotFoundException() {
        // Arrange
        final List<String> songid = new ArrayList<String>() {
            {
                add("1");
                add("2");
            }
        };
        when(userRepositoryMock.findById(anyString())).thenReturn(Optional.empty());
        // Act and Assert

        Assertions.assertThrows(UserNotFoundException.class,
                () -> playlistService.modifyPlaylistAdd("1", "2", songid));
        verify(userRepositoryMock, times(1)).findById(anyString());

    }


    @Test
    @DisplayName("playPlaylist method should Throw Playlist Not Found Exception if No User Found")
    public void playPlaylist_shouldThrowPlaylistNotFoundException() {
        // Arrange
        final List<String> songid = new ArrayList<String>() {
            {
                add("1");
                add("2");
            }
        };
        User user = new User("1", "User1");

        when(userRepositoryMock.findById(user.getId())).thenReturn(Optional.of(user));
        when(playlistRepositoryMock.findById(anyString())).thenReturn(Optional.empty());
        // Act and Assert

        Assertions.assertThrows(PlaylistNotAvailableException.class,
                () -> playlistService.modifyPlaylistAdd("1", "2", songid));
        verify(playlistRepositoryMock, times(1)).findById(anyString());

    }


    // @Test
    // @DisplayName("playPlaylist method Should return selected Song for given user id and song id")
    // public void playSelectedSong_shouldReturnSelectedSong() {
    //     // Arrange


    //     List<String> list = Arrays.asList(new String("SideArtist1"), new String("SideArtist2"));
    //     List<Song> songs = new ArrayList<Song>() {
    //         {
    //             add(new Song("1", "Song1", "Genre1", "Album1", "Artist1", list));
    //             add(new Song("2", "Song2", "Genre2", "Album2", "Artist2", list));
    //         }
    //     };

    //     Playlist playlist = new Playlist("1", "Playlist1", songs);
    //     User user = new User("1", "User1");
    //     user.addPlaylist(playlist);
    //     List<String> songsIds = user.getSongIDList(playlist);
    //     Song s = user.getSelectedSong(playlist, songsIds.get(1));
    //     SongDTO expectedsong = new SongDTO(s.getSname(),s.getAlbum_name(),s.getAlbum_artist_name());
    //     // List<Song> songsFromRepository = songRepositoryMock.getSongsByID(songsIds);

    //     when(userRepositoryMock.findById(user.getId())).thenReturn(Optional.of(user));
    //     when(playlistRepositoryMock.save(any(Playlist.class))).thenReturn(playlist);
    //     // when(songRepositoryMock.findById(songsIds.get(1))).thenReturn(Optional.of(songs.get(1)));
    //     when(playlistRepositoryMock.findById(playlist.getId())).thenReturn(Optional.of(playlist));

    //     // Act
    //     SongDTO actualSong = playlistService.playSelectedSong("1", "2");

    //     // Assert
    //     Assertions.assertEquals(expectedsong, actualSong);
    //     verify(userRepositoryMock, times(1)).findById(anyString());
    //     verify(songRepositoryMock, times(1)).findById(anyString());
    //     verify(playlistRepositoryMock, times(1)).findById(anyString());


    // }
}
