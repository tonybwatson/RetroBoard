package com.retroBoard.retroBoard.exception;

public class RetroNotFoundException extends RuntimeException {

    public RetroNotFoundException(int id) {
        super("Could not find retro " + id);
    }


}
