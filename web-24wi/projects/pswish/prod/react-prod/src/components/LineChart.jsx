import React, { useEffect, useState } from "react";
import { ResponsiveLine } from "@nivo/line";
import { useTheme } from "@mui/material";
import { tokens } from "../theme";
import fetchTrip from "../components/fetchTrip";

const LineChart = ({ isDashboard = false }) => {
  const [chartData, setChartData] = useState([]);
  const theme = useTheme();
  const colors = tokens(theme.palette.mode);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const data = await fetchTrip();
        if (Array.isArray(data)) {
          const sortedData = data.sort(
            (a, b) => new Date(a[2]) - new Date(b[2]),
          );

          let currentDate = null;
          let sumMiles = 0;
          const aggregatedData = [];

          sortedData.forEach((item) => {
            const date = new Date(item[2]);
            const miles = parseInt(item[8]);

            if (
              currentDate === null ||
              (date - currentDate) / (1000 * 3600 * 24) >= 7
            ) {
              if (currentDate !== null) {
                aggregatedData.push({
                  x: currentDate.toDateString(),
                  y: sumMiles,
                });
              }
              currentDate = date;
              sumMiles = 0;
            }

            sumMiles += miles;
          });

          if (currentDate !== null) {
            aggregatedData.push({ x: currentDate.toDateString(), y: sumMiles });
          }
          setChartData([{ id: "Miles Traveled", data: aggregatedData }]);
        } else {
          console.error(
            "Invalid data received from fectTrip, it is not an array",
            data,
          );
        }
      } catch (error) {
        console.error("Error fetching trip data:", error);
      }
    };
    fetchData();
  }, []);

  if (chartData.length === 0) {
    return <div>Loading...</div>;
  }

  return (
    <ResponsiveLine
      data={chartData}
      theme={{
        axis: {
          domain: {
            line: {
              stroke: colors.grey[100],
            },
          },
          legend: {
            text: {
              fill: colors.grey[100],
            },
          },
          ticks: {
            line: {
              stroke: colors.grey[100],
              strokeWidth: 1,
            },
            text: {
              fill: colors.grey[100],
            },
          },
        },
        legends: {
          text: {
            fill: colors.grey[100],
          },
        },
        tooltip: {
          container: {
            color: colors.grey[500],
          },
        },
      }}
      colors={{ scheme: "nivo" }}
      margin={{ top: 50, right: 110, bottom: 50, left: 60 }}
      xScale={{ type: "point" }}
      yScale={{
        type: "linear",
        min: "auto",
        max: "auto",
        stacked: true,
        reverse: false,
      }}
      yFormat=" >-.2f"
      curve="catmullRom"
      axisTop={null}
      axisRight={null}
      axisBottom={{
        tickSize: 5,
        tickPadding: 5,
        tickRotation: 0,
        legend: isDashboard ? undefined : "transportation",
        legendOffset: 36,
        legendPosition: "middle",
      }}
      axisLeft={{
        tickSize: 5,
        tickPadding: 5,
        tickValues: 5,
        tickRotation: 0,
        legend: isDashboard ? undefined : "count",
        legendOffset: -40,
        legendPosition: "middle",
      }}
      enableGridX={false}
      enableGridY={false}
      pointSize={10}
      pointColor={{ theme: "background" }}
      pointBorderWidth={2}
      pointBorderColor={{ from: "serieColor" }}
      pointLabelYOffset={-12}
      useMesh={true}
      legends={[
        {
          anchor: "bottom-right",
          direction: "column",
          justify: false,
          translateX: 100,
          translateY: 0,
          itemsSpacing: 0,
          itemDirection: "left-to-right",
          itemWidth: 80,
          itemHeight: 20,
          itemOpacity: 0.75,
          symbolSize: 12,
          symbolShape: "circle",
          symbolBorderColor: "rgba(0, 0, 0, .5)",
          effects: [
            {
              on: "hover",
              style: {
                itemBackground: "rgba(0, 0, 0, .03)",
                itemOpacity: 1,
              },
            },
          ],
        },
      ]}
    />
  );
};

export default LineChart;
