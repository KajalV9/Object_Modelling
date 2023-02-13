package com.crio.jukebox.entities;

import java.util.List;

public class Song extends BaseEntity {


    private final String sname;
    private final String genre;
    private final String album_name;
    private final String album_artist_name;
    List<String> side_artist_name;

    public Song(String sname, String genre, String album_name, String album_artist_name,
            List<String> side_artist_name) {
        this.sname = sname;
        this.genre = genre;
        this.album_name = album_name;
        this.album_artist_name = album_artist_name;
        this.side_artist_name = side_artist_name;
    }



    public Song(String id, String sname, String genre, String album_name, String album_artist_name,
            List<String> side_artist_name) {
        this.id = id;
        this.sname = sname;
        this.genre = genre;
        this.album_name = album_name;
        this.album_artist_name = album_artist_name;
        this.side_artist_name = side_artist_name;
    }



    public Song(String id, String sname, String genre, String album_name,
            String album_artist_name) {
        this.id = id;
        this.sname = sname;
        this.genre = genre;
        this.album_name = album_name;
        this.album_artist_name = album_artist_name;
    }



    public String getSname() {
        return sname;
    }

    public String getGenre() {
        return genre;
    }

    public String getAlbum_name() {
        return album_name;
    }

    public String getAlbum_artist_name() {
        return album_artist_name;
    }

    public List<String> getSide_artist_name() {
        return side_artist_name;
    }



    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Song other = (Song) obj;
        if (album_artist_name == null) {
            if (other.album_artist_name != null)
                return false;
        } else if (!album_artist_name.equals(other.album_artist_name))
            return false;
        if (album_name == null) {
            if (other.album_name != null)
                return false;
        } else if (!album_name.equals(other.album_name))
            return false;
        if (genre == null) {
            if (other.genre != null)
                return false;
        } else if (!genre.equals(other.genre))
            return false;
        if (side_artist_name == null) {
            if (other.side_artist_name != null)
                return false;
        } else if (!side_artist_name.equals(other.side_artist_name))
            return false;
        if (sname == null) {
            if (other.sname != null)
                return false;
        } else if (!sname.equals(other.sname))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((album_artist_name == null) ? 0 : album_artist_name.hashCode());
        result = prime * result + ((album_name == null) ? 0 : album_name.hashCode());
        result = prime * result + ((genre == null) ? 0 : genre.hashCode());
        result = prime * result + ((side_artist_name == null) ? 0 : side_artist_name.hashCode());
        result = prime * result + ((sname == null) ? 0 : sname.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "Song [album_artist_name=" + album_artist_name + ", album_name=" + album_name
                + ", genre=" + genre + ", side_artist_name=" + side_artist_name + ", sname=" + sname
                + "]";
    }



}
