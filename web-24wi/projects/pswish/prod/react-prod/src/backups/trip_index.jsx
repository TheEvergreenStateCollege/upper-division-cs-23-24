//This is a working version of the index in the trips.

import React, { useState, useEffect } from 'react';
import { Typography } from "@mui/material"
import axios from 'axios';


const Trip = ({ tripData }) => {
    const [trippData, setTripData] = useState([]);
  
    const fetchData = () => {
      axios.get('https://www.pswish-corp.arcology.builders/trip')
        .then(response => {
          setTripData(response.data);
          })
          .catch(error => {
            console.error('Error fetching trip data:', error);
            setTimeout(fetchData, 5000);  // 5 second retry
          if (setTripData){
            console.log("Set Trip Data", setTripData);
          }
      });
    };
    useEffect(() => {
      fetchData();
          },[]);

    console.log('Trip data in the Trip file:', trippData);
  
    const extractNumbers = (str) => {
      const numericValue = parseFloat(str);
      if (!isNaN(numericValue)) {
        return numericValue; // Not used in current data but might be in the near future
      } else {
        const match = str.match(/(\d+(\.\d+)?)/);
      if (match) {
        return parseFloat(match[0]);
          } else {
            return NaN;
          }
        }
      };
      
    
    // Calculate total distance
    const calculateTotalDistance = () => {
      let totalDistance = 0;
      trippData.forEach(trip => {
        const distance = trip[8]; // Can't use -1 in js so this might be wrong eventually
        const numericDistance = extractNumbers(distance);
        
        // Iterate through the converted srting 
        if (!isNaN(numericDistance)) {
          totalDistance += numericDistance;
        }
      });
      return totalDistance;
    };
  
    // Render total distance
    return (
      <div>
        <Typography>Total Distance: {calculateTotalDistance()} miles</Typography>
      </div>
    );
  };
  
  export default Trip;