import React from 'react';

interface WindowButtonsProps {
    onMinimize: () => void;
    onMaximize: () => void;
    onClose: () => void;
}

const WindowButtons: React.FC<WindowButtonsProps> = ({ onMinimize, onMaximize, onClose }) => {
    return (
        <>
            <div className="window-buttons top-0 right-0 w-1/2 ">
                <div className={" flex justify-end p-1 "}>
                    <button className="bg-panelgray text-xs pr-1 pl-2 border border-cus" onClick={onMinimize}>
                        _
                    </button>
                    <button className="bg-panelgray text-xs pr-1 pl-1 border border-cus" onClick={onMaximize}>
                        □
                    </button>
                    <button className="bg-panelgray text-xs ml-1 pr-2 pl-2  border border-cus" onClick={onClose}>
                        x
                    </button>
                </div>
            </div>
        </>
    );
};

export default WindowButtons;
