package com.mitashgaurh.musiclibrary.java.dao;

import com.mitashgaurh.musiclibrary.java.entities.Album;

import java.util.List;

public interface AlbumDao {

    Album createAlbum(Album album);

    Album updateAlbum(Album album);

    void deleteAlbum(Album album);

    Album findAlbumById(Integer albumId);

    List<Album> getAlbums();
}
