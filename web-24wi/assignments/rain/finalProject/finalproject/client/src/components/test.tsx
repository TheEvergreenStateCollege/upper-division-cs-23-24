import Browser from "./browser";

const Test = () => {
    return(
        <div>
            <h1>test</h1>
        </div>
    )
};

const Filled = () => {
    return (
        <Browser content={<Test/>}></Browser> 
    )
}


export default Filled;