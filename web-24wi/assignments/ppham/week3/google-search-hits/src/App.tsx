import { useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import './App.css'
import SearchHit from './SearchHit'
 
interface AppProps {
  searchHits: Array<string>
}

function App(props: AppProps) {
  const [count, setCount] = useState(0)

  return (
    <>
      {props.searchHits.map((hit: string) => 
        <SearchHit hit={hit} />
      )}
    </>
  )
}

export default App
