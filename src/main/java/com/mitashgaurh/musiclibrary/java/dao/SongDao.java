package com.mitashgaurh.musiclibrary.java.dao;

import com.mitashgaurh.musiclibrary.java.entities.Song;

import java.util.List;

public interface SongDao {

    Song addSong(Song song);

    Song updateSong(Song song);

    void deleteSong(Song song);

    Song findSongById(Integer songId);

    List<Song> getSongs();

    List<Song> getSongsByCriteria(List<Integer> ids);
}
