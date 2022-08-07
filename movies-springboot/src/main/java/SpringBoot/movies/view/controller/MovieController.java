package SpringBoot.movies.view.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import SpringBoot.movies.service.MovieService;
import SpringBoot.movies.shared.MovieDTO;
import SpringBoot.movies.view.model.MovieRequest;
import SpringBoot.movies.view.model.MovieResponse;
import SpringBoot.movies.view.model.MovieUpdate;

@RestController
@RequestMapping("api/movie")
public class MovieController {

    @Autowired
    private MovieService service;


    @GetMapping
    public ResponseEntity<List<MovieResponse>> getMovie(){
        List<MovieDTO> movieDTOs = service.getAllMovie();

        if(movieDTOs.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        ModelMapper mapper = new ModelMapper();
        List<MovieResponse> MovieResponses = movieDTOs.stream()
            .map(c -> mapper.map(c, MovieResponse.class))
            .collect(Collectors.toList());

        return new ResponseEntity<>(MovieResponses, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<MovieResponse> getById(@PathVariable Long id){

        Optional<MovieDTO> Movie = service.getById(id);

        if(Movie.isPresent()){
            return new ResponseEntity<>(new ModelMapper().map(Movie.get(), MovieResponse.class),HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/filter")
    public ResponseEntity<MovieResponse> getBySearch(@RequestParam String search){
        
        Optional<MovieDTO> Movie = service.getBySearch(search);
        if(Movie.isPresent()){
            return new ResponseEntity<>(new ModelMapper().map(Movie.get(), MovieResponse.class),HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<MovieRequest> createMovie(@RequestBody @Valid MovieRequest Movie){
        ModelMapper mapper = new ModelMapper();
        MovieDTO MovieDTO = mapper
            .map(Movie, MovieDTO.class);
        MovieDTO = service.register(MovieDTO);
        return new ResponseEntity<>(mapper.map(MovieDTO, MovieRequest.class), HttpStatus.CREATED);
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity<MovieUpdate> updateMovie(@RequestBody @Valid MovieUpdate newMovie, @PathVariable Long id){
        ModelMapper mapper = new ModelMapper();
        MovieDTO movieDTO = mapper
            .map(newMovie, MovieDTO.class);

        if(movieDTO.getAuthor() != null){
            movieDTO = service.partialUpdateMovie(id, newMovie.getAuthor(), newMovie.getTitle(), newMovie.getGenre()); 
        }

        if(movieDTO.getTitle() != null){
            movieDTO = service.partialUpdateMovie(id, newMovie.getAuthor(), newMovie.getTitle(), newMovie.getGenre()); 
        }

        if(movieDTO.getGenre() != null){
            movieDTO = service.partialUpdateMovie(id, newMovie.getAuthor(), newMovie.getTitle(), newMovie.getGenre()); 
        }
        
        return new ResponseEntity<>(mapper.map(movieDTO, MovieUpdate.class), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteMovie(@PathVariable Long id){
        service.deleteMovie(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
}
