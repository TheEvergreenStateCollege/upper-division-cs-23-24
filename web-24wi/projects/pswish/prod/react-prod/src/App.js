import { useState, useEffect } from "react";
import { ColorModeContext, useMode } from "./theme";
import { CssBaseline, ThemeProvider } from "@mui/material";
import { Routes, Route } from "react-router-dom";
import Topbar from "./scenes/global/Topbar";
import Sidebar from "./scenes/global/Sidebar";
import Dashboard from "./scenes/dashboard/index";
import Team from "./scenes/team";
import Invoices from "./scenes/invoices";
import Contacts from "./scenes/contacts";
import Form from "./scenes/form";
import Calendar from "./scenes/calendar";
import FAQ from "./scenes/faq";
import Line from "./scenes/line";
import Pie from "./scenes/pie";
import Geography from "./scenes/geography";
import Trip from "./scenes/trip";
import Time from "./scenes/total_time";
import Skeleton from '@material-ui/lab/Skeleton';

function App() {
  const [theme, colorMode] = useMode();
  const [isSidebar, setIsSidebar] = useState(true);
  const [ isLoading, setIsLoading ] = useState(true);

    // Simulating fetching data
    useEffect(() => {
      const timer = setTimeout(() => {
        setIsLoading(false); // Setting loading to false after data is "fetched"
      }, 2000); // this is a hardcoded delay, might want to actually await the fetch
  
      return () => clearTimeout(timer); // Cleanup the timer
    }, []); // Empty dependency array means this runs once on mount

  return (
    // Setting up color context to be able to use it everywhere
    <ColorModeContext.Provider value={colorMode}>
      <ThemeProvider theme={theme}>
        <CssBaseline />
        <div className="app">
          <Sidebar isSidebar={isSidebar} />
          <main className="content">
            <Topbar setIsSidebar={setIsSidebar} />
            {isLoading ? (
              <Skeleton variant="rect" width={210} height={118} />
              ) : (
            <Routes>
              <Route path="/" element={<Dashboard />} />
              <Route path="/team" element={<Team />} />
              <Route path="/contacts" element={<Contacts />} />
              <Route path="/invoices" element={<Invoices />} />
              <Route path="/form" element={<Form />} />
              <Route path="/calendar" element={<Calendar />} />/
              <Route path="/faq" element={<FAQ />} />
              <Route path="/trip" element={<Trip />} />
              <Route path="/time" element={<Time />} />
              <Route path="/pie" element={<Pie />} />
              <Route path="/line" element={<Line />} />
              <Route path="/geography" element={<Geography />} />
            </Routes>
            )}
          </main>
        </div>
      </ThemeProvider>
    </ColorModeContext.Provider>
  );
}

export default App;
