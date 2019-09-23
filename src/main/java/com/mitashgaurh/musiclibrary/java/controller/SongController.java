package com.mitashgaurh.musiclibrary.java.controller;

import com.mitashgaurh.musiclibrary.java.dto.SongDto;
import com.mitashgaurh.musiclibrary.java.service.SongService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SuppressWarnings("SpringJavaAutowiredFieldsWarningInspection")
@Controller
@RequestMapping("/api/song/")
public class SongController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SongController.class);

    @Autowired
    private SongService songService;

    @RequestMapping(value = {"v1"}, method = RequestMethod.POST)
    public @ResponseBody
    SongDto addSongToAlbum(@RequestHeader Integer albumId, @RequestBody SongDto songDto) {
        LOGGER.info("Add song called");
        return songService.addSongToAlbum(albumId, songDto);
    }

    @RequestMapping(value = {"v1"}, method = RequestMethod.PUT)
    public @ResponseBody
    SongDto updateSong(@RequestBody SongDto songDto) {
        LOGGER.info("Update song called");
        return songService.updateSong(songDto);
    }

    @RequestMapping(value = {"v1"}, method = RequestMethod.GET)
    public @ResponseBody
    List<SongDto> fetchSongList() {
        LOGGER.info("Fetch song list called");
        return songService.getSongList();
    }

    @RequestMapping(value = {"v1"}, method = RequestMethod.DELETE)
    public @ResponseBody
    void deleteSong(@RequestBody Integer songId) {
        LOGGER.info("Delete song called");
        songService.deleteSong(songId);
    }
}
