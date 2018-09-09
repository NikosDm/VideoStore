package com.nikos.model.enums;

public enum MediaType {

    VIDEOGAME, MOVIE, MUSIC_DISK;

    public static int getMediaType(MediaType mediaType){

        switch(mediaType){
            case VIDEOGAME:
                return 1;
            case MOVIE:
                return 2;
            case MUSIC_DISK:
                return 3;
            default:
                return 0;
        }
    }
}
