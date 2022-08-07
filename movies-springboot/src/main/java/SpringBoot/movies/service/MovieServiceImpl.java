package SpringBoot.movies.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import SpringBoot.movies.model.Movie;
import SpringBoot.movies.repository.MovieRepository;
import SpringBoot.movies.shared.MovieDTO;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieRepository repo;

    @Override
    public List<MovieDTO> getAllMovie() {
        List<Movie> movies = repo.findAll();
        return movies.stream()
            .map(m -> new ModelMapper().map(m, MovieDTO.class))
            .collect(Collectors.toList());
    }
    @Override
    public MovieDTO register(MovieDTO movie) {
        return salveMovie(movie);
    }

    @Override
    public Optional<MovieDTO> getById(Long id) {
        Optional<Movie> movie = repo.findById(id);

        if(movie.isPresent()){
            return Optional.of(new ModelMapper()
                .map(movie.get(), MovieDTO.class));
        }

        return Optional.empty();
    }

    @Override
    public void deleteMovie(Long id) {
        repo.deleteById(id);
        
    }

    @Override
    public MovieDTO updateMovie(Long id, MovieDTO newMovie) {
        newMovie.setId(id);
        return salveMovie(newMovie);
    }

    @Override
    public MovieDTO partialUpdateMovie(Long id, String author, String title, String genre) {
        ModelMapper mapper = new ModelMapper();
        Optional<Movie> movie = repo.findById(id);

        if(movie.isPresent()){

            if(author != null){
                movie.get().setAuthor(author);
                repo.save(movie.get());
            }
            if(title != null){
                movie.get().setTitle(title);
                repo.save(movie.get());
            }
            if(genre != null){
                movie.get().setGenre(genre);
                repo.save(movie.get());
            }

            return mapper.map(movie.get(), MovieDTO.class);

        }

        return null;
    } 
    

    private MovieDTO salveMovie(MovieDTO movie) {
        ModelMapper mapper = new ModelMapper();
        Movie movie_entity = mapper.map(movie, Movie.class);
        movie_entity = repo.save(movie_entity);
        return mapper.map(movie_entity, MovieDTO.class);
    }
    @Override
    public Optional<MovieDTO> getBySearch(String search) {
        Optional<Movie> movie = repo.findByTitle(search);

        if(movie.isPresent()){
            return Optional.of(new ModelMapper()
                .map(movie.get(), MovieDTO.class));
        }

        return Optional.empty();
    }

}
