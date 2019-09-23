package com.mitashgaurh.musiclibrary.java.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "track")
public class Track implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "albumId")
    @NotNull
    private Integer albumId;

    @Column(name = "songId")
    @NotNull
    private Integer songId;

    public Track() {
    }

    public Track(@NotNull Integer albumId, @NotNull Integer songId) {
        this.albumId = albumId;
        this.songId = songId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAlbumId() {
        return albumId;
    }

    public void setAlbumId(Integer albumId) {
        this.albumId = albumId;
    }

    public Integer getSongId() {
        return songId;
    }

    public void setSongId(Integer songId) {
        this.songId = songId;
    }
}
