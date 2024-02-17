const fetchTrip = async ({ queryKey }) => {
    const trip = queryKey[1];
  
    if (!trip) return [];
  
    const tripData = await fetch(
      `https://www.pswish-corp.arcology.builders/trip`
    );
  
    if (!tripData.ok)
      throw new Error(`details not found in tripData, fetch not ok`);
  
    return tripData.json();
  };
  
  export default fetchTrip;