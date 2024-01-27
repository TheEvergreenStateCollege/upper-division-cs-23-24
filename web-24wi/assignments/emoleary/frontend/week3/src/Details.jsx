import { useParams } from "react-router-dom";
import { useQuery } from "@tanstack/react-query";
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
} return <div><h2>{id}</h2></div>
};

export default Details;
