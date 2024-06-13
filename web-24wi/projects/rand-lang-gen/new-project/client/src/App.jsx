import { Route, Routes } from 'react-router-dom'
import './style.css'
import WordSearch from './WordSearch'
import Login from './Login'
import Register from './Register'


function App() {

  return (
      <div className='bg-gradient-to-l from-[#5C258D] to-[#4389A2] h-screen w-screen ml-200 flex justify-center items-center'>
        <Routes>
          <Route path='login' element={ <Login/>}/>
          <Route path='wordsearch' element={ <WordSearch/>}/>
          <Route path='register' element={ <Register/>}/>
        </Routes>
      </div>
  )
}

export default App
