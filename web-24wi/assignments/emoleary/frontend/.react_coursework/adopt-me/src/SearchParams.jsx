import { useState } from 'react';

const ANIMALS = ['rug', 'doggo', 'abolute dog', 'ABSOLUT dog', 'fish or bird', 'not fish', 'not bird', 'other']
const BREEDS = ["slug"]


const SearchParams = () => {  

  const [location, setLocation] = useState("Seattle, WA");
  const[animal, setAnimal] = useState("")  
  const[breed, setBreed] = useState("")  

  return (    
    <div>
      <form>
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
    </div>
  );
};

export default SearchParams;
