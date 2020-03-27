
package com.marscode.pwn.aflamk.Models;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "movies_table")
public class Movies {

    @SerializedName("popularity")
    @Expose
    @ColumnInfo(name = "popularity")
    @Nullable
    private Double popularity;
    @SerializedName("vote_count")
    @Expose
    @ColumnInfo(name = "vote_count")
    @Nullable
    private Integer voteCount;
    @SerializedName("video")
    @Expose
    @ColumnInfo(name = "video")
    @Nullable
    private Boolean video;
    @SerializedName("poster_path")
    @Expose
    @ColumnInfo(name = "poster_path")
    @Nullable
    private String posterPath;
    @SerializedName("id")
    @Expose
    @PrimaryKey(autoGenerate = true)
    private Integer id;
    @SerializedName("adult")
    @Expose
    @ColumnInfo(name = "adult")
    @Nullable
    private Boolean adult;
    @SerializedName("backdrop_path")
    @Expose
    @ColumnInfo(name = "backdrop_path")
    @Nullable
    private String backdropPath;
    @SerializedName("original_language")
    @Expose
    @ColumnInfo(name = "original_language")
    @Nullable
    private String originalLanguage;
    @SerializedName("original_title")
    @Expose
    @ColumnInfo(name = "original_title")
    @Nullable
    private String originalTitle;
//    @SerializedName("genre_ids")
//    @Expose
//    private List<Integer> genreIds = null;
    @SerializedName("title")
    @Expose
    @ColumnInfo(name = "title")
    @Nullable
    private String title;
    @SerializedName("vote_average")
    @Expose
    @ColumnInfo(name = "vote_average")
    @Nullable
    private Double voteAverage;
    @SerializedName("overview")
    @Expose
    @ColumnInfo(name = "overview")
    @Nullable
    private String overview;
    @SerializedName("release_date")
    @Expose
    @ColumnInfo(name = "release_date")
    @Nullable
    private String releaseDate;

    public Double getPopularity() {
        return popularity;
    }

    public void setPopularity(Double popularity) {
        this.popularity = popularity;
    }

    public Integer getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(Integer voteCount) {
        this.voteCount = voteCount;
    }

    public Boolean getVideo() {
        return video;
    }

    public void setVideo(Boolean video) {
        this.video = video;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getAdult() {
        return adult;
    }

    public void setAdult(Boolean adult) {
        this.adult = adult;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

//    public List<Integer> getGenreIds() {
//        return genreIds;
//    }
//
//    public void setGenreIds(List<Integer> genreIds) {
//        this.genreIds = genreIds;
//    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(Double voteAverage) {
        this.voteAverage = voteAverage;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

}
