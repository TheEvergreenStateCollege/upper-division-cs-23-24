import Browser from "../src/components/browser";
import Form from "../src/components/form";
import React from "react";

const Login = () => {
    return(
    <Browser content= {<Form message={"Login"} urlTo={"signin"}></Form>}></Browser>
    // <Browser content="test"></Browser>
    )
}

export default Login