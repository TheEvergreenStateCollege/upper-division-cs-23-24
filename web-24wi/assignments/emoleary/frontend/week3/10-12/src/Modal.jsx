import React, { useEffect, useRef } from "react";
import { createPortal } from "react-dom";

// if it has no children, it won't render itself
const Modal = ({ children }) => {
    const elRef = useRef(null); // ref - a locker for the one div we want (divs rerender)
                                                        // this can reference the div created before root in the index.html file
    if (!elRef.current) {
        elRef.current = document.createdElement("div"); // document. calls are slow
    }
    
    useEffect(() => {
        const modalRoot = document.getElementById("modal");
        modalRoot.appendChild(elRef.current);
        
        // including the return is the equivalnet of componentWillUnmount
        return () => modalRoot.removeChild(elRef.current);
    }, []);

    return createPortal(<div>{children}</div>, elRef.current);
}

export default Modal;
