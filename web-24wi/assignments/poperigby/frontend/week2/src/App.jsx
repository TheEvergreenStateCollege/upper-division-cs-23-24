import { createRoot } from "react-dom/client";
import { BrowserRouter, Routes, Route } from "react-router-dom"
import SearchParams from "./SearchParams";
import Details from "./Details";

const App = () => {
    return (
        < div >
            <BrowserRouter>
                <h1>Adopt me!</h1>
                <Routes>
                    <Route path="/details/:id" element={<Details />} />
                    <Route path="/" element={<SearchParams />} />
                </Routes>
            </BrowserRouter>
        </div >
    );
};

const container = document.getElementById("root");
const root = createRoot(container);
root.render(<App />);
