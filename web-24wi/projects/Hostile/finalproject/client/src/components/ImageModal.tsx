import React from "react";
import ReactModal from "react-modal";

interface ImageModalProps {
    isOpen: boolean;
    onRequestClose: () => void;
    contentLabel: string;
    imageUrl: string;
}

const ImageModal: React.FC<ImageModalProps> = ({
                                                   isOpen,
                                                   onRequestClose,
                                                   contentLabel,
                                                   imageUrl,
                                               }) => {
    const modalStyles: ReactModal.Styles = {
        overlay: {
            display: "flex",
            alignItems: "center",
            justifyContent: "center",
            backgroundColor: "rgba(0, 0, 0, 0.5)",
        },
        content: {
            position: "absolute",
            top: "50%",
            left: "50%",
            transform: "translate(-50%, -50%)",
            padding: "20px",
            border: "none",
            borderRadius: "8px",
            outline: "none",
            backgroundColor: "white",
            overflow: "visible",
            width: "80%",
            maxWidth: "500px",
            height: "300px",
            boxSizing: "border-box",
        },
    };

    return (
        <ReactModal
            isOpen={isOpen}
            onRequestClose={onRequestClose}
            contentLabel={contentLabel}
            style={modalStyles}
        >
            <img src={imageUrl} alt="Modal Image" style={{ width: "100%" }} />
        </ReactModal>
    );
};

export default ImageModal;