import { Box, IconButton, useTheme } from "@mui/material";
import { useContext } from "react";
import { ColorModeContext, tokens } from "../../theme";
import InputBase from "@mui/material/InputBase";
import LightModeOutlinedIcon from "@mui/icons-material/LightModeOutlined";
import DarkModeOutlinedIcon from "@mui/icons-material/LightModeOutlined";
import NotificationsOutlinedIcon from "@mui/icons-material/LightModeOutlined";
import SettingsOutlinedIcon from "@mui/icons-material/LightModeOutlined";
import PersonOutlinedIcon from "@mui/icons-material/LightModeOutlined";
import SearchIcon from "@mui/icons-material/LightModeOutlined";



const Topbar = () => {
    const theme = useTheme();
    const colors = tokens(theme.palette.mode);
    const colorMode = useContext(ColorModeContext);

    return (<Box display="flex" justifyContent="space-between" p={2}>
        {/* SEARCH BAR */}
        <Box 
        display="flex" 
        backgroundColor={colors.primary[400]} 
        borderRadius="3px"
        >
        <InputBase sx = {{ml: 2, flex: 1}} placeholder="Search" />
        <IconButton type="button" sx={{p: 1}}>
            <SearchIcon />
        </IconButton>
        </Box>
        
        {/* Right handside Icons */}
        <Box display="flex">
        <IconButton onClick={colorMode.toggleColormode}>
            {theme.palette.mode == "dark"? (
                <DarkModeOutlinedIcon />
            ) : (
                <LightModeOutlinedIcon />
            )}
            <LightModeOutlinedIcon />
        </IconButton>
        <IconButton>
            <NotificationsOutlinedIcon />
        </IconButton>
        <IconButton>
            <SettingsOutlinedIcon />
        </IconButton>
        <IconButton>
            <PersonOutlinedIcon />
        </IconButton>
        </Box>
    </Box>)
}

export default Topbar;