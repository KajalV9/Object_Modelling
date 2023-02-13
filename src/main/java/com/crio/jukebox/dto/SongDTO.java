package com.crio.jukebox.dto;

public class SongDTO {

    private final String sId;
    private final String sname;
    private final String album_name;
    private final String album_artist_name;

    public SongDTO(String sId, String sname, String album_name, String album_artist_name) {
        this.sId = sId;
        this.sname = sname;
        this.album_name = album_name;
        this.album_artist_name = album_artist_name;
    }



    
    @Override
    public String toString() {
        return "SongDTO [sId = " + sId + ",  sname = " + sname + 
                 ", album_name = " + album_name + ", album_artist_name =" + album_artist_name  + "]";
    }




    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((album_artist_name == null) ? 0 : album_artist_name.hashCode());
        result = prime * result + ((album_name == null) ? 0 : album_name.hashCode());
        result = prime * result + ((sname == null) ? 0 : sname.hashCode());
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
        SongDTO other = (SongDTO) obj;
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
        if (sname == null) {
            if (other.sname != null)
                return false;
        } else if (!sname.equals(other.sname))
            return false;
        return true;
    }

}
