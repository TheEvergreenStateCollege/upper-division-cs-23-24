import React, { useEffect, useRef } from "react";
import { createPortal } from "react-dom";

const Modal = ({ children }) => {
    const elRef = useRef(null);
    if (!elRef.current) {
        elRef.current = document.createElement("div");
    }

    useEffect(() => {
        const modalRoot = document.getElementById("modal");
        modalRoot.appendChild(elRef.current);
        return () => modalRoot.removeChild(elRef.current);
    }, []);

    return createPortal(<div className="bg-[rgba(0,0,0,0.9)] fixed z-10 flex justify-center items-center inset-0 empty:hidden">{children}</div>, elRef.current);
};

export default Modal;
