import React, { useState, useEffect } from 'react';

function Content() {
  const [jsonData, setJsonData] = useState([]);
  const userIds = [1, 2, 3]; // Example user IDs, adjust as needed

  useEffect(() => {
    // Construct the query parameter for user IDs
    const userIdsQueryParam = `userIds=${userIds.join(',')}`;

    // Fetch data from your API with user IDs query parameter
    fetch(`http://localhost:3001/api/posts?${userIdsQueryParam}`)
      .then(response => response.json())
      .then(data => {
        console.log(data);
        // Store data in state
        setJsonData(data.data);
      })
      .catch(error => {
        console.error('Error fetching data:', error);
      });
  }, [userIds]); // Depend on userIds so if they change, re-fetch the data

  // Render your component with jsonData
  return (
    <div>
      {/* Render posts or handle jsonData as needed */}
    </div>
  );
}

