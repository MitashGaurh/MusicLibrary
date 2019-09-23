package com.mitashgaurh.musiclibrary.java.service;

import com.mitashgaurh.musiclibrary.java.dto.AlbumDto;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

public interface AlbumService {

    List<AlbumDto> getAlbums();

    AlbumDto createAlbum(@NotNull @Valid AlbumDto albumDto);

    AlbumDto updateAlbum(@NotNull @Valid AlbumDto albumDto);

    void deleteAlbum(@NotNull Integer songId);
}
