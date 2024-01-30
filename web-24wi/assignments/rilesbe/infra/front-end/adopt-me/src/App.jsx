import { createRoot } from 'react-dom/client'
import Pet from "./Pet";

const App = () => {
  return (
    <div>
      <h1>Adopt Me!</h1>
      <Pet name="Giles Murray" animal="cat" color="black"/>
      <Pet name="Collin" animal="dog" color="white"/>
      <Pet name="Muffin" animal="cat" color="black and white"/>

    </div>
  )
};

const container = document.getElementById("root");
const root = createRoot(container);
root.render(<App />);