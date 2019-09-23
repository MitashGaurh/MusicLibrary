package com.mitashgaurh.musiclibrary.java.service;

import com.mitashgaurh.musiclibrary.java.dto.SongDto;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Validated
public interface SongService {

    List<SongDto> getSongList();

    SongDto addSongToAlbum(@NotNull @Valid Integer albumId, @NotNull @Valid SongDto songDto);

    SongDto updateSong(@NotNull @Valid SongDto songDto);

    void deleteSong(@NotNull Integer songId);
}
