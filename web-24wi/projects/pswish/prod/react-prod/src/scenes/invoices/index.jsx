import React, { useState, useEffect} from "react";
import { Box, useTheme, CircularProgress } from "@mui/material";
import { DataGrid } from "@mui/x-data-grid";
import { tokens } from "../../theme";
// import { mockDataInvoices } from "../../data/mockData";
import Header from "../../components/Header";
// import { Trip, tripData } from "../../scenes/trip/";

const TripLog = ( {tripData }) => {
  const theme = useTheme();
  const colors = tokens(theme.palette.mode);
  // const { tripData } = Trip();
  console.log('Triplog data:', tripData);
  
  const [isLoading, setIsLoading] = useState(true);

  useEffect(() => {
    if (tripData.length > 0) {
      setIsLoading(false);
    }
  }, [tripData]);

  const columns = [
    { field: "id", headerName: "ID"},
    { field: "date", headerName: "Date", flex: 1},
    { field: "duration", headerName: "Duration", flex: 1},
    { field: "distance", headerName: "Distance", flex: 1},
    { field: "orig", headerName: "Origination", flex: 1},
    { field: "dest", headerName: "Destination", flex: 1}
  ];
  // const Orincolumns = [
  //   { field: "id", headerName: "ID" },
  //   {
  //     field: "name",
  //     headerName: "Name",
  //     flex: 1,
  //     cellClassName: "name-column--cell",
  //   },
  //   {
  //     field: "phone",
  //     headerName: "Phone Number",
  //     flex: 1,
  //   },
  //   {
  //     field: "email",
  //     headerName: "Email",
  //     flex: 1,
  //   },
  //   {
  //     field: "cost",
  //     headerName: "Cost",
  //     flex: 1,
  //     renderCell: (params) => (
  //       <Typography color={colors.greenAccent[500]}>
  //         ${params.row.cost}
  //       </Typography>
  //     ),
  //   },
  //   {
  //     field: "date",
  //     headerName: "Date",
  //     flex: 1,
  //   },
  // ];

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
      >{isLoading ? (
        <Box display="flex" justifyContent="center" alignItems="center" height="100%">
        <CircularProgress />
        </Box>
        ) : (
        <DataGrid checkboxSelection rows={tripData} columns={columns} />
        )}
      </Box>
    </Box>
  );
};

export default TripLog;
