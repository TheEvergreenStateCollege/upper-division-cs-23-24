import React, { useEffect, useState } from 'react';
import { Typography } from "@mui/material";

const Time = () => {
  const [tripData, setTripData] = useState([]);

  // Async function to fecth the trip data from the postgresql server
  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await fetch('https://www.pswish-corp.arcology.builders/trip');
        const data = await response.json();
        setTripData(data);
      } catch (error) {
        console.error('Error fetching trip data:', error);
      }
    };

    fetchData();
  }, []); // Empty dependency array to make sure this effect runs only once


  // Numbers function
  const extractNumbers = (str) => {
    const numericValue = parseFloat(str);
    if (!isNaN(numericValue)) {
      return numericValue; // Not used in current data but might be in the near future
    } else {
      const match = str.match(/\d+(\.\d+)?/);
      if (match) {
        return parseFloat(match[0]);
      } else {
        return NaN;
      }
    }
  };

  // Calculate total time
  const calcTotalTime = () => {
    let totalTime = 0;
    tripData.forEach(trip => {
      const time = trip[3]; // Can't use -1 in js so this might be wrong eventually
      const numericTime = extractNumbers(time);


      // Iterate through the converted string
      if (!isNaN(numericTime)) {
        totalTime += numericTime;
      }
    });
    return totalTime;
  };

  // Render total time
  return (
    <div>
      <Typography>Total Time: {calcTotalTime()} mins</Typography>
    </div>
  );
};

export default Time;
