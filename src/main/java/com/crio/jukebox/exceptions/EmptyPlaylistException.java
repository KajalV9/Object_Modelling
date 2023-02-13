package com.crio.jukebox.exceptions;

public class EmptyPlaylistException extends RuntimeException {

    
    @Override
    public String toString(){
        return "Playlist is Empty ";
    }
    
}
