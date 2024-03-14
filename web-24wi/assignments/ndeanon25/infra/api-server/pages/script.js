const button = document.getElementById("submit");
const username = document.getElementById("input-username");
const password = document.getElementById("input-password");

console.log(`username ${username}`);
console.log(`password ${password}`);
button.addEventListener("click", async (event) => {
  event.preventDefault();
  // add a fetch here


  async function loginUser(username, password) {
    const response = await fetch('http://ndCorp.arcology.builders/signin' , {
      method: 'POST',
      headers: {
        'Content-Type' : 'application/json'
      },
      body:JSON.stringify({username, password})
    }); 
    
    const data = await response.json();
    console.log(`Response ${JSON.stringify(jsonBody)}`);
    return data;
    }

    async function login(){
        const username = 'theUsername';
        const password = 'thePassword';
        try{
            const result = await loginUser(username,password);
            console.log(result);
        } catch(error) {
            console.error('Error:',error);
        }
    }

    login();
  });