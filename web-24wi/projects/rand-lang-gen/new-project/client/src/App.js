import axios from 'axios';
import './App.css';

const apiCall = () => {
  axios.get('http://localhost:8080').then(() => {
    console.log("data")
  })
}

function App() {
  return (
    <div className="App">
      <header className="App-header">
       <button onClick={apiCall}>Make API Call</button>
       </header>
    </div>
  );
}

export default App;
