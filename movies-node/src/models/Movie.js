import mongoose from "mongoose";
import mongoosePaginate from "mongoose-paginate-v2";

const movieSchema = new mongoose.Schema({
  id: { type: String },
  title: { type: String, required: true },
  author: { type: String, required: true },
  genre: { type: String, required: true },
});

movieSchema.plugin(mongoosePaginate);

const movies = mongoose.model("movies", movieSchema);

export default movies;
