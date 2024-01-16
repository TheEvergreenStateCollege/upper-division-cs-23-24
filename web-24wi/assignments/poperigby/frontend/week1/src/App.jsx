import { createRoot } from "react-dom/client";
import Pet from "./Pet";
import SearchParams from "./SearchParams";

const App = () => {
    return (
        < div >
            <h1> Adopt Me!</h1>
            <Pet name="Rigby" animal="Dog" breed="Mutt" />
            <Pet name="Mia" animal="Dog" breed="Border Collie" />
            <Pet name="Ruby" animal="Ferret" breed="Cinnamon" />
            <Pet name="Ruby" animal="Ferret" breed="Cinnamon" />
            <Pet name="Pauley" animal="Cat" breed="Black" />
            <Pet name="Opah" animal="Cat" breed="Orange Tabby" />
            <SearchParams />
        </div >
    );
};

const container = document.getElementById("root");
const root = createRoot(container);
root.render(<App />);
