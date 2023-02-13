package com.crio.jukebox.services;

import java.util.List;
import com.crio.jukebox.dto.PlaylistDTO;
import com.crio.jukebox.dto.SongDTO;
import com.crio.jukebox.entities.ActionEnum;
import com.crio.jukebox.entities.Playlist;
import com.crio.jukebox.entities.Song;
import com.crio.jukebox.entities.User;
import com.crio.jukebox.exceptions.EmptyPlaylistException;
import com.crio.jukebox.exceptions.PlaylistNotAvailableException;
import com.crio.jukebox.exceptions.SongNotAvailableException;
import com.crio.jukebox.exceptions.UserNotFoundException;
import com.crio.jukebox.repositories.IPlaylistRepository;
import com.crio.jukebox.repositories.ISongRepository;
import com.crio.jukebox.repositories.IUserRepository;

public class PlaylistService implements IPlaylistService {

        private final IUserRepository userRepository;
        private final ISongRepository songRepository;
        private final IPlaylistRepository playlistRepository;

        private static Playlist currplaylist;
        private static int currSongIndex;
        private static String playlistId;


        public PlaylistService(IUserRepository userRepository, ISongRepository songRepository,
                        IPlaylistRepository playlistRepository) {
                this.userRepository = userRepository;
                this.songRepository = songRepository;
                this.playlistRepository = playlistRepository;
        }


        public User verifyUser(String userId) {
                User user = userRepository.findById(userId)
                                .orElseThrow(() -> new UserNotFoundException(
                                                "User with id " + userId + "not found!"));
                return user;
        }

        public Playlist verifyPlaylist(String playlistId) {
                Playlist playlist = playlistRepository.findById(playlistId)
                                .orElseThrow(() -> new PlaylistNotAvailableException(
                                                "Playlist with id" + playlistId + "not found"));
                return playlist;
        }

        public List<Song> verifySong(List<String> songId) {

                // System.out.println("Inside verifying Song method");
                List<Song> songlist = songRepository.getListSongsByID(songId);
                if (songlist.isEmpty()) {
                        throw new SongNotAvailableException(
                                        "Some Requested Songs Not Available. Please try again.");
                }
                return songlist;
        }


        @Override
        public Playlist createPlaylist(String userId, String playlistname, List<String> songId)
                        throws SongNotAvailableException {

                final List<Song> song = verifySong(songId);
                User user = verifyUser(userId);
                Playlist playlist = playlistRepository.save(new Playlist(playlistname, song));
                user.addPlaylist(playlist);
                // userRepository.save(user);
                return playlist;
        }

        @Override
        public String deletePlaylist(String userId, String playlistId)
                        throws PlaylistNotAvailableException {


                User user = verifyUser(userId);
                Playlist playlist = verifyPlaylist(playlistId);
                boolean check = user.deletePlaylist(playlist);
                if (check) {
                        playlistRepository.deleteById(playlistId);
                        playlistRepository.save(playlist);
                        userRepository.save(user);
                        return "Delete Successful";
                } else
                        throw new PlaylistNotAvailableException("Playlist Not Found");


        }

        @Override
        public SongDTO playPlaylist(String userId, String playlistID)
                        throws EmptyPlaylistException {
                User user = verifyUser(userId);
                Playlist playlist = verifyPlaylist(playlistID);
                playlistId = playlistID;
                Playlist uplaylist = user.getPlaylist(playlist);
                Song song = user.getFirstSong(uplaylist);
                currplaylist = uplaylist;
                currSongIndex = 0;
                playlistRepository.save(currplaylist);
                userRepository.save(user);
                return new SongDTO(song.getId(), song.getSname(), song.getAlbum_name(),
                                song.getAlbum_artist_name());


        }

        @Override
        public SongDTO playSelectedSong(String userId, String songId)
                        throws SongNotAvailableException {
                verifyUser(userId);
                Playlist playlist = verifyPlaylist(playlistId);
                boolean check = playlistRepository.getSelectedSong(playlist.getId(), songId);
                if (check) {
                        Song song = songRepository.getSongbyId(songId);
                        return new SongDTO(song.getId(), song.getSname(), song.getAlbum_name(),
                                        song.getAlbum_artist_name());

                }

                else {
                        throw new SongNotAvailableException(
                                        "Song Not Found in the current active playlist.");

                }

        }



        @Override
        public PlaylistDTO modifyPlaylistAdd(String userId, String playlistId, List<String> songId)
                        throws SongNotAvailableException {

                // System.out.println("Inside modifyPlaylistAdd method");

                User user = verifyUser(userId);
                Playlist playlist = verifyPlaylist(playlistId);
                List<Song> song = verifySong(songId);
                List<String> allsongid = playlistRepository.addSong(playlistId, song);
                playlistRepository.save(playlist);
                return new PlaylistDTO(user.getId(), playlist.getName(), allsongid);
        }



        @Override
        public PlaylistDTO modifyPlaylistDelete(String userId, String playlistId,
                        List<String> songId) throws SongNotAvailableException {
                User user = verifyUser(userId);
                Playlist playlist = verifyPlaylist(playlistId);
                List<Song> song = verifySong(songId);
                List<String> allsongid = playlistRepository.removeSong(playlistId, song);
                playlistRepository.save(playlist);
                return new PlaylistDTO(user.getId(), playlist.getName(), allsongid);
        }


        @Override
        public SongDTO playSong(String userId, ActionEnum action) {
                User user = verifyUser(userId);
                if (ActionEnum.BACK.equals(action)) {
                        if (currSongIndex == 0) {
                                currSongIndex = user.getSongIDList(currplaylist).size() - 1;

                        } else
                                currSongIndex--;
                } else {
                        if (currSongIndex == user.getSongIDList(currplaylist).size() - 1) {
                                currSongIndex = 0;
                        } else
                                currSongIndex++;
                }

                String songIndex = String.valueOf(currSongIndex);
                Song song = user.getSong(currplaylist, songIndex);
                return new SongDTO(song.getId(), song.getSname(), song.getAlbum_name(),
                                song.getAlbum_artist_name());
        }



}
