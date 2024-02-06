import { useState, useEffect } from 'react';
import Pet from './Pet.jsx'

const ANIMALS = ["bird", "cat", "dog", "rabbit", "reptile"];

const SearchParams = () => {
  const [pets, setPets] = useState([]);
  const [location, setLocation] = useState("");
  const [animal, setAnimal] = useState("");
  const [breed, setBreed] = useState("");
  const BREEDS = [];

  useEffect(() => {
    requestPets();
  }, []); 

  async function requestPets() {
    const res = await fetch(
      `http://pets-v2.dev-apis.com/pets?animal=${animal}&location=${location}&breed=${breed}`
    );
    const json = await res.json();

    setPets(json.pets);
  }

  return (    
    <div>
      <form
        onSubmit={(e) => {
            e.preventDefault()
            requestPets()
          }}
        >
          
        <label htmlFor="location">
          Location
          <input 
              onChange={(e) => setLocation(e.target.value)}
              id="Location"
              value={location} 
              placeholder="location">
            </input>
        </label>

        <label htmlFor="animal">
          Animal
          <select
            id="animal"
            value={animal}
            onChange={(e) => {
              setAnimal(e.target.value)
              setBreed("")
            }}
          >
              {ANIMALS.map((animal) => (
                    <option key={animal}>{animal}</option>
                  )
                )
              }
          </select>
          </label>
        
        <label htmlFor="breed">
          Breed
          <select
            id="breed"
            disabled={BREEDS.length===0}
            value={breed}
            onChange={(e) => setBreed(e.target.value)
          }
          >
              {BREEDS.map((breed) => (
                    <option key={breed}>{breed}</option>
                  )
                )
              }
              
          </select>
              </label>
      </form>

      {pets.map((pet) => (
        <Pet name={pet.name} value={pet.value} key={pet.id}></Pet> 
      ))
      }
    </div>
  );
};

export default SearchParams;
