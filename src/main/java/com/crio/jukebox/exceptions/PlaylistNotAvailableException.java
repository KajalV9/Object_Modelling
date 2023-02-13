package com.crio.jukebox.exceptions;

public class PlaylistNotAvailableException extends RuntimeException {
    public PlaylistNotAvailableException() {
        super();
    }

    public PlaylistNotAvailableException(String message){
        
        super(message);
    }
}
