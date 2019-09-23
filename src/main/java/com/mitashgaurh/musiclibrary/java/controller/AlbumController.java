package com.mitashgaurh.musiclibrary.java.controller;

import com.mitashgaurh.musiclibrary.java.dto.AlbumDto;
import com.mitashgaurh.musiclibrary.java.service.AlbumService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@SuppressWarnings("SpringJavaAutowiredFieldsWarningInspection")
@Controller
@RequestMapping("/api/album")
public class AlbumController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AlbumController.class);

    @Autowired
    private AlbumService albumService;

    @RequestMapping(value = {"v1"}, method = RequestMethod.POST)
    public @ResponseBody
    AlbumDto createAlbum(@RequestBody AlbumDto albumDto) {
        LOGGER.info("Create album called");
        return albumService.createAlbum(albumDto);
    }

    @RequestMapping(value = {"v1"}, method = RequestMethod.PUT)
    public @ResponseBody
    AlbumDto updateAlbum(@RequestBody AlbumDto albumDto) {
        LOGGER.info("Update album called");
        return albumService.updateAlbum(albumDto);
    }

    @RequestMapping(value = {"v1"}, method = RequestMethod.GET)
    public @ResponseBody
    List<AlbumDto> fetchAlbums() {
        LOGGER.info("Fetch album list called");
        return albumService.getAlbums();
    }

    @RequestMapping(value = {"v1"}, method = RequestMethod.DELETE)
    public @ResponseBody
    void deleteAlbum(@RequestBody Integer albumId) {
        LOGGER.info("Delete album called");
        albumService.deleteAlbum(albumId);
    }
}
