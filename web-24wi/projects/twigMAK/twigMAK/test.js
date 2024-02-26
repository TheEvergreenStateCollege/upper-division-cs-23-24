theString = "arrangement = opposite "
theString = theString.replaceAll(" = ",':["');
theString = theString.replaceAll("/tab",'"],');

console.log(theString);