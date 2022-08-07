import movies from "../models/Movie.js";

class MovieController {

  static getMovie = (req, res) => {
    const myCustomLabels = {
      totalDocs: "count",
      docs: "results",
      limit: "perPage",
      page: "currentPage",
      nextPage: "next",
      prevPage: "previous",
      totalPages: "pageCount",
      pagingCounter: "slNo",
    };

    const options = {
      page: 1,
      limit: 10,
      customLabels: myCustomLabels,
    };
    movies.paginate({}, options, function (err, movies) {
      res.status(200).json(movies);
    });
  };

  static getMovieById = (req, res) => {
    const { id } = req.params;

    movies.findById(id).exec((err, movies) => {
      if (err) {
        res
          .status(400)
          .send({ message: `${err.message} - ID does not location` });
      } else {
        res.status(200).send(movies);
      }
    });
  };

  static registerMovie = (req, res) => {
    let movie = new movies(req.body);

    movie.save((err) => {
      if (err) {
        res
          .status(500)
          .send({ message: `${err.message} - failed in register Movie` });
      } else {
        res.status(201).send(movie.toJSON());
      }
    });
  };

  static updateMovie = (req, res) => {
    const { id } = req.params;

    movies.findByIdAndUpdate(id, { $set: req.body }, (err) => {
      if (!err) {
        res.status(200).send({ message: "Movie has been updated" });
      } else {
        res.status(500).send({ message: err.message });
      }
    });
  };

  static deleteMovie = (req, res) => {
    const { id } = req.params;

    movies.findByIdAndDelete(id, (err) => {
      if (!err) {
        res.status(200).send({ message: "Movie has been delete" });
      } else {
        res.status(500).send({ message: err.message });
      }
    });
  };

  static getMovieBySearch = (req, res) => {
    const { search } = req.query;

    movies.find(
      {
        $or: [
          { title: { $regex: ".*" + search + ".*" } },
          { author: { $regex: ".*" + search + ".*" } },
          { genre: { $regex: ".*" + search + ".*" } },
        ],
      },
      {},
      (err, movies) => {
        res.status(200).send(movies);
      }
    );
  };
}

export default MovieController;
