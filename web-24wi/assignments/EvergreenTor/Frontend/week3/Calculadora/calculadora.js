//Credits to Brian Holt from FrontendMasters
//followed along his video for the js part 


let buffer ='0';
const screen = document.querySelector('.screen');


function buttonClick(value){
    if(isNaN(parseInt(value)))
        handleSymbol(value);
    else{
        handleNumber(value);
    }
    rerender();
}

function handleNumber(number){
    if(buffer=== '0'){
       buffer = number;
    }
    else{
        buffer += number;
    }
}

function handleSymbol(symbol){
    console.log(symbol);
}


function init() {
    document.querySelector('calc-buttons')
    .addEventListener("clicks", function(event) {
    buttonClick(event.target.innerText);
    })
}

function rerender(){
    screen.innerText = buffer; 
}

init();

