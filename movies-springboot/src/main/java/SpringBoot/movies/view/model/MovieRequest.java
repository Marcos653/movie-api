package SpringBoot.movies.view.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class MovieRequest {
    @NotNull @NotEmpty
    private String title;
    @NotNull @NotEmpty
    private String author;
    @NotNull @NotEmpty
    private String genre;


    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public String getGenre() {
        return genre;
    }
    public void setGenre(String genre) {
        this.genre = genre;
    }
    
}
