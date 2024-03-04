import React from 'react';

interface WindowButtonsProps {
    onMinimize: () => void;
    onExpand: () => void;
    onClose: () => void;
}

const WindowButtons = () => {
    return (
        <div className="window-buttons top-0 right-0 w-1/2 ">

            <div className={" flex justify-end p-1 "}>

                
                <button
                    className="bg-panelgray text-xs pr-1 pl-2 border border-cus">
                    _
                    </button>
                <button className=" bg-panelgray text-xs pr-1 pl-1 border border-cus">
                    □
                </button>
                <button className="bg-panelgray text-xs ml-1 pr-2 pl-2 border border-cus"
>
                    x
                </button>
            </div>
        </div>
    );
};

export default WindowButtons;