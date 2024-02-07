import { ColorModeContext, useMode } from "./theme"
import { CssBaseline, ThemeProvider } from "@mui/material";
import Topbar from "./scenes/global/Topbar";

function App() {
  const [theme, colorMode] = useMode();

  return (
    // Setting up color context to be able to use it everywhere
  <ColorModeContext.Provider value={colorMode}>
    <ThemeProvider theme={theme}>
      <CssBaseline />
    <div className="app">
      <main className="content"></main>
    </div>
    </ThemeProvider>
  </ColorModeContext.Provider>
  );
}

export default App;
