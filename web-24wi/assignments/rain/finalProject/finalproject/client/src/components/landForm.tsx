import Browser from "./browser";
import Landing from "./landing";
import Desktop from "../Desktop";

//get internet ads to fill site with

const Landform = () => {
    return(
    <Browser content= {<Landing/>}/>
    // <Browser content="test"></Browser>
    )
}

export default Landform