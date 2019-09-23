package com.mitashgaurh.musiclibrary.java.dao;

import com.mitashgaurh.musiclibrary.java.entities.Track;

import java.util.List;

public interface TrackDao {

    void createTrack(Track track);

    void deleteTrack(Track track);

    List<Track> getTracksByAlbum(List<Integer> albumIds);
}
