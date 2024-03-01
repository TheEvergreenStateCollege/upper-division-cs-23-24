import { useParams } from "react-router-dom";
import { useQuery } from "@tanstack/react-query";

import Carousel  from "./Carousel";
import fetchPet from './fetchPet.js';


const Details = () => {
  const { id } = useParams();
  const results = useQuery(["details", id], fetchPet); // Runs a fetchPet query if data not in cache
  
    if (results.isLoading) {
    return (
    <div className="loading-pane">
      <h2 className="loader">🐶</h2>
    </div>
  ) 
} return (
  <Carousel images={pet.images}></Carousel>
)
};

export default Details;
