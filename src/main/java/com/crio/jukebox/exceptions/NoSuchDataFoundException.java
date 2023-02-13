package com.crio.jukebox.exceptions;

public class NoSuchDataFoundException extends RuntimeException{
    @Override
    public String toString() {
        return "No such Data Found!";
    }

}
