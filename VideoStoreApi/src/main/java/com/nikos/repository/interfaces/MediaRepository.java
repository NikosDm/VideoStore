package com.nikos.repository.interfaces;

import com.nikos.model.Media;

import java.util.List;
/**
 * Created by nikos on 8/4/2018.
 */
public interface MediaRepository {

    Media findMediaByMediaID(int MediaID);

    List<? extends Media> getMediaData(String WhereClause, String OrderClause);

    Media updateMediaData(Media mediaData);

    int deleteMedia(int MediaID);

    Media insertNewMediaData(Media newMediaData);
}

