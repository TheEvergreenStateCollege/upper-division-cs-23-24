import Browser from "./browser";
import React from "react";

const Form = ()=>{
return(
    
    <form action="/api/makepost" method="post">
       
        <input className=" mx-2 mt-20 px-2 border border-cus min-w-40" type="text" placeholder="Title" name="name" id="name"/><br></br>
        <textarea className=" mx-2 mt-1 px-3 border border-cus min-w-40" placeholder="Body" name="body" id="body"/> <br></br>
        <button className="mt-2 px-2 bg-panelgray border border-cus" type="submit"> Post</button>
        
    </form>
   
)
}

const Post = () => {
    return(
    <Browser content={<Form/>}/>
    )
}

export default Post