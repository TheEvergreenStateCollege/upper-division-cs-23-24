import React, { useState, useEffect } from 'react';
import Window from './window';
import Desktop from '../Desktop';
import Browser from './browser';

function BuildContent() {
  const [jsonData, setJsonData] = useState([]);

  useEffect(() => {
    fetch('/allposts')
      .then(response => response.json())
      .then(data => {
        console.log(data);
        setJsonData(data);
      })
      .catch(error => {
        console.error('Error fetching data:', error);
      });
  }, []);

  return (
    <div>
      {jsonData.map((post, index) => (
        <div className="flex-wrap">
        <div key={index} className=" pr-8 pb-8 relative flex-wrap overflow-auto justify-center">
          <div
            className="flex-wrap justify-center bg-bargray hover:bg-panelgray border-black border-2 text-white font-bold px-2 py-4 m-2 w-full"
          >
            <h2 className='text-xl'>{post.name} </h2>  <h2>  by {post.belongsTo.username}</h2>
            <p>{post.body}</p>
          </div>
        </div>
        </div>
      ))}
    </div>
  );
}
const Content = () => {
  return(
    <Browser content={<BuildContent />} /> 
  )
}

export default Content;

