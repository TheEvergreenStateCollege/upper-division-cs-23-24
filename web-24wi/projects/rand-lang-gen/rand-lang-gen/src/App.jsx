import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import DefSearch from './DefSearch'
import WordSearch from './WordSearch'



import { BrowserRouter, Route, Routes } from 'react-router-dom'
import './App.css'
import { useEffect, useState } from 'react'
import Welcome from './Welcome'
import Login from './Login'

function App() {
  const [loggedIn, setLoggedIn] = useState(false)
  const [email, setEmail] = useState('')

  return (
    <div className="bg bg-yellow-300">
    <div className="App">
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<Welcome email={email} loggedIn={loggedIn} setLoggedIn={setLoggedIn} />} />
          <Route path="/login" element={<Login setLoggedIn={setLoggedIn} setEmail={setEmail} />} />
        </Routes>
      </BrowserRouter>
    </div>
    </div>
  )
}

export default App
