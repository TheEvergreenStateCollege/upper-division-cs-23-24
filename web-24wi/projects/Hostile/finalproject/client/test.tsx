import React from "react";

function main() {

    const handleSelectItem = (item: string) => {
        console.log(item);
      };

    
        const subb = () => { 
            handleSelectItem("submit")
        }
        const canc = () => {
            handleSelectItem("cancel")
        }
    


    return (
        <div>
        {/* render a row of buttons using the map function */}
        <div className="flex flex-row">
        <div className="basis-2/5 flex flex-col">
                <div><button onClick={subb} className="mx-2 mt-8 p-2 border border-gray-700" >Sign In</button></div>
                <div><button className="mx-2 mt-8 p-2 border border-gray-700">Post</button></div>
                <div><button className="mx-2 mt-8 p-2 border border-gray-700">Discover</button></div>
            </div>
        </div>
        </div>
    );
};
export default main;