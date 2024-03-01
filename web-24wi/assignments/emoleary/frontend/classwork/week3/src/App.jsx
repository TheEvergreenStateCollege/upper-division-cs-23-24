import { createRoot } from "react-dom/client";
import Board from './Board';


const App = () => {
  return (
    <div>
        <Board></Board>
    </div>
  );
};

const container = document.getElementById("root");
const root = createRoot(container);
root.render(<App />);
