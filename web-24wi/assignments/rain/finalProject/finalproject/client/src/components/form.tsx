//import { Button } from "./components/Button"
import React from "react"
interface FormProps {
    message: string;
    urlTo: string
}

const Form = ({message, urlTo}: {message:any, urlTo:any}) => {
//const message = props
//change button class to custom


return (
    <form className="text-black" action={urlTo} method="post">
        <label htmlFor="uname">User Name:</label>
        <input className=" mx-2 mt-20 px-2 border border-cus min-w-40" type="text" autoComplete="username" name="Username" id="uname"/><br></br>
        <label htmlFor="pword">Password:</label>
        <input className=" mx-2 mt-1 px-3 border border-cus min-w-40" type="password" autoComplete="new-password" name="Password" id="pword"/> <br></br>
        <button className="mt-2 px-2 bg-panelgray border border-cus" type="submit"> {message} </button>
        
    </form>
    
)
}

export default Form