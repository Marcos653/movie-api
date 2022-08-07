package SpringBoot.movies.service;

import java.util.List;
import java.util.Optional;

import SpringBoot.movies.shared.MovieDTO;

public interface MovieService {
    List<MovieDTO> getAllMovie();
    MovieDTO register(MovieDTO movie);
    Optional<MovieDTO> getById(Long id);
    Optional<MovieDTO> getBySearch(String search);
    void deleteMovie(Long id);
    MovieDTO updateMovie(Long id, MovieDTO newMovie);
    MovieDTO partialUpdateMovie(Long id, String author, String title, String genre);
}
