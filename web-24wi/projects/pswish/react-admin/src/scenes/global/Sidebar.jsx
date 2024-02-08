import { useState } from "react";
import { Sidebar, Menu, MenuItem } from "react-pro-sidebar";
// import "react-pro-sidebar/dist/css/styles.css"; //Going to need to surce this
import { Box, IconButton, Typography, useTheme } from "@mui/material";
// import { Link } from "react-router-dom";
import { tokens } from "../../theme";
// import HomeOutlinedIcon from "@mui/icons-material/HomeOutlined"
// import PersonOutlinedIcon from "@mui/icons-material/PersonOutlined";
// import ContactsOutlinedIcon from "@mui/icons-material/ContactOutlined";
// import ReceiptOutlinedIcon from "@mui/icons-material/ReceiptOutlined";
// import PeopleAltOutlinedIcon from "@mui/icons-material/PeopleOutlined";
// import CalendarOutlinedIcon from "@mui/icons-material/CalendarOutlined";
// import HelpOutlinedIcon from "@mui/icons-material/HelpOutlined";
// import BarChartOutlinedIcon from "@mui/icons-material/BarChartOutlined";
// import PieChartOutlineOutlineIcon from "@mui/icons-material/PieChartOutlineOutlined";
// import TimelineOutlinedIcon from "@mui/icons-material/TimelineOutlined";
import MenuOutlinedIcon from "@mui/icons-material/MenuOutlined";
// import MapOutlineIcon from "@mui/icons-material/MapOutlined";

const MySidebar = () => {
    const theme = useTheme();
    const colors = tokens(theme.palette.mode);
    const [isCollapsed, setIsCollapsed] = useState(false);
    // const [selected, setSelected] = useState("Dashboard")

    return (
        <Box 
        sx={{
            "& .pro-sidebar-inner": {
                background: `${colors.primary[400]} !important`
            },
            "& .pro-icon-wrapper": {
                backgroundColor: "transparent !important",
            },
            "& .pro-inner-item": {
                padding: "5px 35px 5px 20px !important",
            },
            "& .pro-inner-item:hover": {
                color: "#868dfb !important",
            },
            "& .pro-inner-item:active": {
                color: "#687ofa !important",    
            },
        }}
        >
            <Sidebar collpased={isCollapsed}>
                <Menu iconShape="square">
                    {/* LOG AND MENU ICON */}
                <MenuItem  
                    onClick={() => setIsCollapsed(!isCollapsed)}
                    icon={isCollapsed ? <MenuOutlinedIcon /> : undefined}
                    style={{
                        margin: "10px 0 20 px 0",
                        color: colors.grey[100],
                  }}
                  >
                {!isCollapsed && (
                <Box
                    display="flex"
                    justifyContent="space-between"
                    alignItems="center"
                    ml="15px"
                >
                    <Typography vairant="h3" color={colors.grey[100]}>
                        ADMINIS
                    </Typography>
                    <IconButton onClick={() => setIsCollapsed(!isCollapsed)}>
                        <MenuOutlinedIcon />
                    </IconButton>
                    </Box>
                    )}
                </MenuItem>
                
                {/* USER */}
                {!isCollapsed && (
                    <Box mb="25px">
                        <Box display="flex" justifyContent="center" alignItems="center">
                        <img
                        alt="profile-user"
                        width="100px"
                        height="100px"
                        src={`../../assets/user.png`}
                        style={{ cursor: 'pointer', borderRadius: '50%' }}
                        />
                    </Box>

                    <Box textAlign="center">
                        <Typography variant="h2" color={colors.grey[100]} fontWeight="bold" sx ={{ m: "10px 0 0 0"}}> pswish </Typography>
                        <Typography> VP Fancy Admin</Typography>
                    </Box>
                </Box>
                )}
                
                </Menu>
            </Sidebar>
        </Box>
        );
    };

export default MySidebar;

