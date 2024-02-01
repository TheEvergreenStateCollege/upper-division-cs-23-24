//import { createRoot } from 'react-dom/client'
import SearchParams from './SearchParams';

const App = () => {
  return (
    <StrictMode>
    <div>
      <h1>Adopt Me!</h1>
      <SearchParams/>
    </div>
    </StrictMode>
  );
};

export default App
//const container = document.getElementById("root");
//const root = createRoot(container);
//root.render(<App />);

