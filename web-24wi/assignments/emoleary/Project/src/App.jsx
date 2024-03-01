import { createRoot } from "react-dom/client";
import BuildingZone from "./BuildingZone.jsx";
import Command_Terminal from "./Command_Terminal.jsx";
import GeneratedResume from "./GeneratedResume.jsx";

const App = () => {
  return (
    <div>
        <p>HELLO It me</p>
        <BuildingZone></BuildingZone>
        <GeneratedResume></GeneratedResume>
        <Command_Terminal></Command_Terminal>
    </div>
  );
};

const container = document.getElementById("root");
const root = createRoot(container);
root.render(<App />);
