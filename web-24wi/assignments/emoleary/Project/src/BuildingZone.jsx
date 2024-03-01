import { useState, useEffect } from "react";
import Entry from "./Entry";

const BuildingZone = () => {
    const [allEntries, setAllEntries] = useState([]);

   useEffect(() => {
        if(allEntries.length===0) {
                setAllEntries([
                            ['Skills','C', 'C#', 'C++'],
                            ['Experience','CEO']
                ])
        }
   })

        const mapEntries = allEntries.map((entry, index) =>
                    <Entry key={index} section={entry}></Entry>
        )
  return (
    <>
        <h1>Hello</h1>
        {mapEntries}
    </>
  );
};

export default BuildingZone;