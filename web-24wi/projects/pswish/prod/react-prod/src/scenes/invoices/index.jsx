import React, { useState, useEffect } from "react";
import { Box, useTheme } from "@mui/material";
import { DataGrid } from "@mui/x-data-grid";
import { tokens } from "../../theme";
import Header from "../../components/Header";
// import { Trip, tripData } from "../../scenes/trip/"; Not working yet

const TripLog = () => {
  const theme = useTheme();
  const colors = tokens(theme.palette.mode);
  const [tripData, setTripData] = useState([]);

  // Async function to fecth the trip data from the postgresql server
  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await fetch(
          "https://www.pswish-corp.arcology.builders/trip",
        );
        const data = await response.json();
        console.log("Fetched trip data:", data); // Log the fetched data
        setTripData(data);
      } catch (error) {
        console.error("Error fetching trip data:", error);
      }
    };

    fetchData();
  }, []); // Empty dependency array to make sure this effect runs only once

  const columns = [
    { field: "id", headerName: "ID" },
    { field: "date", headerName: "Date", flex: 1 },
    { field: "duration", headerName: "Duration", flex: 1 },
    { field: "distance", headerName: "Distance", flex: 1 },
    { field: "orig", headerName: "Origination", flex: 1 },
    { field: "dest", headerName: "Destination", flex: 1 },
  ];

  const formattedTripData = tripData.map((row) => ({
    id: row[0],
    date: row[2],
    duration: row[3],
    distance: row[8],
    orig: row[4],
    dest: row[5],
  }));

  return (
    <Box m="20px">
      <Header title="TRIPS" subtitle="List of Trips" />
      <Box
        m="40px 0 0 0"
        height="75vh"
        sx={{
          "& .MuiDataGrid-root": {
            border: "none",
          },
          "& .MuiDatagrid-cell": {
            borderBottom: "none",
          },
          "& .name-column--cell": {
            color: colors.greenAccent[300],
          },
          "& .MuiDataGrid-columnHeaders": {
            backgroundColor: colors.blueAccent[700],
            brderBottom: "none",
          },
          "& .MuiDataGrid-virtualScroller": {
            backgroundColor: colors.primary[400],
          },
          "& .MuiDataGrid-footerContainer": {
            borderTop: "none",
            backgroundColor: colors.blueAccent[700],
          },
          "& .MuiCheckBox-root": {
            color: `${colors.greenAccent[200]} !important`,
          },
        }}
      >
        <DataGrid
          checkboxSelection
          rows={formattedTripData}
          columns={columns}
        />
      </Box>
    </Box>
  );
};

export default TripLog;
