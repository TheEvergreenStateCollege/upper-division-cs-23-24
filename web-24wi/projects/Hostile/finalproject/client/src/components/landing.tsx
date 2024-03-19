
const Landing = () => {
    return (
        <div className="mt-10 ">
            <a href="/login">
                <button className=" mt-2 px-2 shadow-lg border-2 border-cus bg-panelgray">Login</button>
            </a> 
            <a href="/entry">
                <button className=" mt-2 px-2 shadow-lg border-2 border-cus bg-panelgray">Register</button>
            </a>
        </div>
    )
}

export default Landing;