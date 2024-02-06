import { createRoot } from "react-dom/client";
import { BrowserRouter, Routes, Route, Link } from "react-router-dom";
import { QueryClient, QueryClientProvider } from "@tanstack/react-query";
import Details from "./Details";
import SearchParams from "./SearchParams";

const queryClient= new QueryClient({
  defaultOptions: {
    quieries: { 
      staleTime: Infinity,
      cacheTime: Infinity,
    }
  }
});


const App = () => {
  return (
    <div>
      <BrowserRouter> {/* BrowserRouter establishes context */}
        
        <QueryClientProvider client={queryClient}> {/* Provides conent */} 
        <header> {/*If header is outside of BrowserRouter, it can cause a useRef error*/ }
          <Link to="/">Adopt Me!</Link>
        </header>
       
        <Routes>
          <Route path="/details/:id" element={<Details />} />
          {/*:id indicates a variable*/}
          <Route path="/" element={<SearchParams />} />
        {/**/}
        </Routes>
      
        </QueryClientProvider>
      </BrowserRouter>
    </div>
  );
};

const container = document.getElementById("root");
const root = createRoot(container);
root.render(<App />);
