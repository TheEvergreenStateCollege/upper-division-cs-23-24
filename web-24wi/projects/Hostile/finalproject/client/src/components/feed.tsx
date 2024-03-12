import React, { useState, useEffect } from 'react';
import Browser from './browser';

function BuildContent() {
  const [jsonData, setJsonData] = useState([]);

  useEffect(() => {
    fetch('http://localhost:5000/allposts')
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
    <div className="flex flex-wrap justify-center ">
      {jsonData.map((post, index) => (
        <div key={index} className="w-1/3 px-2">
          <div className={`bg-gradient-to-l ${index % 2 === 0 ? 'from-cyan-500 to-blue-500 ' : ' from-purple-500 to-pink-500'} hover:bg-gradient-to-r border-black border-2 text-center text-black font-bold p-4 mb-4`}>
            <h2 className="text-2xl">{post.name}</h2>
            <h2 className='text-xs '>by {post.belongsTo.username}</h2>
            <p className='text-m'>{post.body}</p>
          </div>
        </div>
      ))}
    </div>
  );
}

const Content = () => {
  return <Browser content={<BuildContent />} />;
};

export default Content;
