// Working on creating a good fetch method for all my componants, this works only in LineChart so far.
const fetchTrip = async () => {
  
    const tripData = await fetch(
      `https://www.pswish-corp.arcology.builders/trip`
    );
  
    if (!tripData.ok)
      throw new Error(`details not found in tripData, fetch not ok`);
  
    return tripData.json();
  };
  
  export default fetchTrip;