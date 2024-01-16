import { useState } from "react";

const ANIMALS = ["bird", "cat", "rabbit", "reptile"];

const SearchParams = () => {
    const [location, updateLocation] = useState("");
    const [animal, updateAnimal] = useState("");
    return (
        <div className="search-params">
            <form>
                <label htmlFor="location">
                    Location
                    <input
                        id="location"
                        value={location}
                        placeholder="Location"
                        onChange={(e) => updateLocation(e.target.value)}
                    />
                </label>
                <label htmlFor="animal">
                    Animal
                    <select
                        id="animal"
                        value={animal}
                        onChange={(e) => {
                            updateAnimal(e.target.value);
                            updateBreed("");
                        }}
                        onBlur={(e) => {
                            updateAnimal(e.target.value);
                            updateBreed("");
                        }}
                    >
                        {ANIMALS.map((animal) => (
                            <option key={animal} value={animal}>
                                {animal}
                            </option>
                        ))}
                    </select>
                </label>
                <button>Submit</button>
            </form>
        </div>
    );
}

export default SearchParams;
