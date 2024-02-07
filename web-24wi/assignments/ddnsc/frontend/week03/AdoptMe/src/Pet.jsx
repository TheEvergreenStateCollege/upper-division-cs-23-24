import { Link } from "react-router-dom";

const Pet = (props) => {
  const { name, animal, breed, images, location, id } = props;

  let hero = "https://pets-images.dev-apis.com/pets/none.jpg";
  if (images.length) {
    hero = images[0];
  }

  return (
    <Link to={`/details/${id}`} className="relative block">
      <div className="image-container">
        <img src={hero} alt={name} />
      </div>
      <div className="to transparent absolute bottom-0 left-0 bg-gradient-to-tr from-white pr-2 pt-2">
        <h1 className={"ml-4"}>{name}</h1>
        <h2 className={"ml-4"}>{`${animal} — ${breed} — ${location}`}</h2>
      </div>
    </Link>
  );
};

export default Pet;
