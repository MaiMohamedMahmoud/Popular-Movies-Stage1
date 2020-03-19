
package com.marscode.pwn.aflamk.Models;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VideoResponse {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("results")
    @Expose
    private List<Videos> videos = null;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Videos> getVideos() {
        return videos;
    }

    public void setVideos(List<Videos> results) {
        this.videos = results;
    }

}
