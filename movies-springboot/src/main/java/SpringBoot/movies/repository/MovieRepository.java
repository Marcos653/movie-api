package SpringBoot.movies.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import SpringBoot.movies.model.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    // @Query("select u from Movies u where lower(u.title) like lower(concat('%', ?1,'%'))")
    Optional<Movie> findByTitle(String title);
    
}
