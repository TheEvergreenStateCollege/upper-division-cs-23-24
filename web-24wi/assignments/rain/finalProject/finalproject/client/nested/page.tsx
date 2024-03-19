import Browser from "../src/components/browser";
import Form from "../src/components/form";
import React from "react";

const Register = () => {
    return(
    <Browser content= {<Form message={"Register"} urlTo={"/user"}></Form>}></Browser>
    // <Browser content="test"></Browser>
    )
}

export default Register