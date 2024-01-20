import { createRoot } from "react-dom/client";
import Pet from "./Pet.jsx";
import SearchParams from "./SearchParams.jsx";

const App = () => {
  return (
    <div>
      <h1>Adopt me!</h1>
    <SearchParams />
      <Pet
        name="Our Lady Taco Supreme"
        animal="absolute dog"
        breed="super-mutt"
      />
    </div>
  );
};

const container = document.getElementById("root");
const root = createRoot(container);
root.render(<App />);
