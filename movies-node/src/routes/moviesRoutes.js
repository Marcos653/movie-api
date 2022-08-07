import express from "express";
import MovieController from "../controllers/moviesController";

const router = express.Router();

router
  /**
   * @swagger
   * /movies:
   *   get:
   *     description: Get all books
   *     responses:
   *       200:
   *         description: Success
   *
   */
  .get("/movies", MovieController.getMovie)
  .get("/movies/find", MovieController.getMovieBySearch)
  .get("/movie/:id", MovieController.getMovieById)
  .post("/movies/", MovieController.registerMovie)
  .put("/movies/:id", MovieController.updateMovie)
  .delete("/movies/:id", MovieController.deleteMovie);

export default router;
