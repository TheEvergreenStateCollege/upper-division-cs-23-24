import { Box, useTheme } from "@mui/material";
import Header from "../../components/Header";
import Accordion from "@mui/material/Accordion";
import AccordionSummary from "@mui/material/AccordionSummary";
import AccordionDetails from "@mui/material/AccordionDetails";
import Typography from "@mui/material/Typography";
import ExpandMoreIcon from "@mui/icons-material/ExpandMore";
import { tokens } from "../../theme";

const FAQ = () => {
  const theme = useTheme();
  const colors = tokens(theme.palette.mode);
  return (
    <Box m="20px">
      <Header title="FAQ" subtitle="Full Calendar Interactive Page" />

      <Accordion>
        <AccordionSummary expandIcon={<ExpandMoreIcon />}>
          <Typography color={colors.greenAccent[500]} variant="h5">
            What is a Full Calendar?
          </Typography>
        </AccordionSummary>
        <AccordionDetails>
          <Typography>
            A Full Calendar is a JavaScript library that allows you to display
            events on a web page. It can be used to display events on a
            calendar, a task list, a timeline, or any other type of calendar.
          </Typography>
        </AccordionDetails>
      </Accordion>

      <Accordion>
        <AccordionSummary expandIcon={<ExpandMoreIcon />}>
          <Typography color={colors.greenAccent[500]} variant="h5">
            How did I use a Full Calendar?
          </Typography>
        </AccordionSummary>
        <AccordionDetails>
          <Typography>
            I used a Full Calendar to display events on a web page. It is a very
            nice tool
          </Typography>
        </AccordionDetails>
      </Accordion>

      <Accordion>
        <AccordionSummary expandIcon={<ExpandMoreIcon />}>
          <Typography color={colors.greenAccent[500]} variant="h5">
            How did you creat this dashboard?
          </Typography>
        </AccordionSummary>
        <AccordionDetails>
          <Typography>
            To create a dashboard, you will need to watch Ed Roh on youtube.
          </Typography>
        </AccordionDetails>
      </Accordion>

      <Accordion>
        <AccordionSummary expandIcon={<ExpandMoreIcon />}>
          <Typography color={colors.greenAccent[500]} variant="h5">
            What is lorem ipsum?
          </Typography>
        </AccordionSummary>
        <AccordionDetails>
          <Typography>
            lorem ipsum is a common word used in computer science. Lorem ipsum
            dolor sit amet, consectetur adip
          </Typography>
        </AccordionDetails>
      </Accordion>

      <Accordion>
        <AccordionSummary expandIcon={<ExpandMoreIcon />}>
          <Typography color={colors.greenAccent[500]} variant="h5">
            What time is it in Hawaii?
          </Typography>
        </AccordionSummary>
        <AccordionDetails>
          <Typography>Hawaii is 2-3 hours behidn PST</Typography>
        </AccordionDetails>
      </Accordion>

      <Accordion>
        <AccordionSummary expandIcon={<ExpandMoreIcon />}>
          <Typography color={colors.greenAccent[500]} variant="h5">
            Whhen is spring break?
          </Typography>
        </AccordionSummary>
        <AccordionDetails>
          <Typography>Spring break is in spring.</Typography>
        </AccordionDetails>
      </Accordion>
    </Box>
  );
};

export default FAQ;
