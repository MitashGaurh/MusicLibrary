package com.mitashgaurh.musiclibrary.java.dto;

import javax.validation.constraints.NotNull;
import java.util.List;

public class AlbumDto {

    private Integer id;

    @NotNull
    private String title;

    @NotNull
    private Integer releaseYear;

    @NotNull
    private Integer trackCount;

    @NotNull
    private Integer totalDuration;

    @NotNull
    private List<SongDto> songList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(Integer releaseYear) {
        this.releaseYear = releaseYear;
    }

    public Integer getTrackCount() {
        return trackCount;
    }

    public void setTrackCount(Integer trackCount) {
        this.trackCount = trackCount;
    }

    public Integer getTotalDuration() {
        return totalDuration;
    }

    public void setTotalDuration(Integer totalDuration) {
        this.totalDuration = totalDuration;
    }

    public List<SongDto> getSongList() {
        return songList;
    }

    public void setSongList(List<SongDto> songList) {
        this.songList = songList;
    }
}
