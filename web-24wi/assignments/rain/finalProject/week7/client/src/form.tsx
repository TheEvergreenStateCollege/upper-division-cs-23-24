import { Button } from "./components/Button"
const Form = () => {

//change button class to custom
return (
    <form action="POST /user">
        <label htmlFor="uname">User Name:</label>
        <input className=" mx-2 mt-20 px-2 border border-cus min-w-40" type="text" autoComplete="username" name="Username" id="uname"/><br></br>
        <label htmlFor="pword">Password:</label>
        <input className=" mx-2 mt-1 px-3 border border-cus min-w-40" type="password" autoComplete="new-password" name="Password" id="pword"/> <br></br>
        <button className="mt-2 px-2 bg-panelgray border border-cus" type="submit"> Register  </button>
        
        
    </form>
)
}

export default Form