import React, { useState, useEffect } from 'react';
import Browser from './browser';
import Window from './windowFeed';
import Desktop from '../Desktop';

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

  // return (
  //   <div className="flex flex-wrap justify-center ">
  //     {jsonData.map((post, index) => (
  //       <div key={index} className=" px-2">
  //         <div className={`bg-gradient-to-l ${index % 2 === 0 ? 'from-cyan-500 to-blue-500 ' : ' from-purple-500 to-pink-500'} hover:bg-gradient-to-r border-black border-2 text-center text-black font-bold p-4 mb-4`}>
  //           <h2 className="text-2xl">{post.name}</h2>
  //           <h2 className='text-xs '>by {post.belongsTo.username}</h2>
  //           <p className='text-m'>{post.body}</p>
  //         </div>
  //       </div>
  //     ))}
  //   </div>
  // );
  return (
    <div>
      {jsonData.map((post, index) => (
        <div key={index} className="pr-8 pb-8 relative ">
          {/* <div className="flex flex-wrap justify-center bg-bargray hover:bg-panelgray border-black border-2 text-white font-bold px-2 py-4 m-2 w-full"> */}
          <div className={`flex-wrap bg-gradient-to-l ${index % 2 === 0 ? 'from-cyan-500 to-blue-500 ' : ' from-purple-500 to-pink-500'} hover:bg-gradient-to-r border-black border-2 text-center text-black font-bold p-4 mb-4`}>
            <h2 className='text-2xl'>{post.name} </h2>  <br/>
            <h2 className='text-sm'>  by {post.belongsTo.username}</h2>
            <p className='text-md'>{post.body}</p>
          </div>
        </div>
      ))}
    </div>
  );
}


const Content = () => {
  return(
  <Desktop content={<Window content={<BuildContent/>}/>}/>
  // <Browser content={<BuildContent/>}/>
  )
};

export default Content;
