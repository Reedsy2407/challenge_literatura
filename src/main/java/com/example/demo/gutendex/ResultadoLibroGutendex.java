package com.example.demo.gutendex;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResultadoLibroGutendex {
    private Integer id;
    private String title;
    private List<AutorGutendex> authors;
    private List<String> languages;

    @JsonProperty("download_count")
    private Integer downloadCount;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public List<AutorGutendex> getAuthors() { return authors; }
    public void setAuthors(List<AutorGutendex> authors) { this.authors = authors; }

    public List<String> getLanguages() { return languages; }
    public void setLanguages(List<String> languages) { this.languages = languages; }

    public Integer getDownloadCount() { return downloadCount; }
    public void setDownloadCount(Integer downloadCount) { this.downloadCount = downloadCount; }
}