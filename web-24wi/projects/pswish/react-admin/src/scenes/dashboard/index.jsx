import  { Box } from '@mui/material';
import Header from '../../componants/Header';

const Dashboard = () => {
    return ( <Box m="20"> 
        <Box display="flex" justify-content="space" alignItems="center">
            <Header title="DASHBOARD" subtitle="Welcome to your dashboard" />
        </Box>
    </Box>
    );
};
export default Dashboard;