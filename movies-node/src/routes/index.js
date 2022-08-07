import express from "express";
import movies from "./moviesRoutes.js";

import swaggerUI from "swagger-ui-express"
import swaggerDocument from "../../swagger.json" assert {type: "json"};

const routes = (app) => {
  app.use("/swagger", swaggerUI.serve, swaggerUI.setup(swaggerDocument));
  app.use(express.json(), movies);
};

export default routes;
