import React, { useState, useEffect } from 'react';
import axios from 'axios';
import Trip from '../scenes/trip';
import Triplog from '../scenes/invoices';

const TripContainer = () => {
  const [tripData, setTripData] = useState([]);
  console.log("Trip data from the Trip Container:", tripData);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await axios.get('https://www.pswish-corp.arcology.builders/trip');
        setTripData(response.data);
      } catch (error) {
        console.error("Error fetching trip data from arcology:", error);
      }
    };
    fetchData();
  }, [axios]);

  return (
    <>
      <Trip tripData={tripData} setTripData={setTripData} />
      <Triplog tripData={tripData} />
    </>
  );
};

export default TripContainer;
