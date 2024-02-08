import { ColorModeContext, useMode } from "./theme"
import { CssBaseline, ThemeProvider } from "@mui/material";
import { Routes, Route } from "react-router-dom";
import Topbar from "./scenes/global/Topbar";
// import MySidebar from "./scenes/global/Sidebar";
import Dashboard from "./scenes/dashboard/index";
// import Team from "./scenes/Team";
// import Invoices from "./scenes/Invoices";
// import Contacts from "./scenes/Contacts";
// import Bar from "./scenes/Bar";
// import Form from "./scenes/Form";
// import Line from "./scenes/Line";
// import Pie from "./scenes/Pie";
// import FAQ from "./scenes/faq";
// import Geography from "./scenes/geography";
// import Calendar from "./scenes/Calendar";

function App() {
  const [theme, colorMode] = useMode();

  return (
    // Setting up color context to be able to use it everywhere
  <ColorModeContext.Provider value={colorMode}>
    <ThemeProvider theme={theme}>
      <CssBaseline />
    <div className="app">
      <main className="content">
        <Topbar />
        <Routes>
          <Route path="/" element={<Dashboard />} />
          {/* <Route path="/team" element={<Team />} /> */}
          {/* <Route path="/invoices" element={<Invoices />} /> */}
          {/* <Route path="/form" element={<Form />} /> */}
          {/* <Route path="/bar" element={<Bar />} /> */}
          {/* <Route path="/pie" element={<Pie />} /> */}
          {/* <Route path="/line" element={<Line />} /> */}
          {/* <Route path="/faq" element={<FAQ />} /> */}
          {/* <Route path="/geography" element={<Geography />} /> */}
          {/* <Route path="/calendar" element={<Calendar />} />/ */}


        </Routes>
      </main>
    </div>
    </ThemeProvider>
  </ColorModeContext.Provider>
  );
}

export default App;
