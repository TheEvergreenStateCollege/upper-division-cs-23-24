//initialize variables
let time;
let militaryHour;
let stdHour = 0;
let ampm;
let refresh = 1000; //every 1 minute

function getTime() {
    const d = new Date(); //get current date from computer
    militaryHour = d.getHours();  //get hour from date (military format)

    //convert current military hour to standard hour
    if (militaryHour > 12) {
        stdHour = militaryHour - 12;  //to get 1 to 11
    }
    else if (militaryHour < 1) {
        stdHour = militaryHour + 12;  //to show 12 if less than 1
    }
    else {
        stdHour = militaryHour;   //when military time is standard time
    }

    //AMPM converter
    if (militaryHour >= 12) {
        ampm = 'PM';
    }
    else if (militaryHour < 12) {
        ampm = 'AM';
    }

    //get current time in minutes
    let mins = d.getMinutes();
    mins = mins/100;  //convert minutes to decimal

    //assemble hour and minutes
    time = stdHour + mins;

    //output time to 2 decimals on html
    document.getElementById("time").innerHTML = time.toFixed(2) + " " + ampm;

    //run clock function
    runTime();
}

//run time continuosly to create clock
function runTime() {
    time = setTimeout('getTime()', refresh);
}