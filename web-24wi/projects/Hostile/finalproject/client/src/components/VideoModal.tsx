import React from "react";
import ReactModal from "react-modal";

interface VideoModalProps {
    isOpen: boolean;
    onRequestClose: () => void;
    contentLabel: string;
    videoId: string;
}

const VideoModal: React.FC<VideoModalProps> = ({
                                                   isOpen,
                                                   onRequestClose,
                                                   contentLabel,
                                                   videoId,
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
            maxWidth: "600px",
            height: "350px",
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
            <iframe
                width="560"
                height="315"
                src={`https://www.youtube.com/embed/${videoId}?autoplay=1`}
                title="YouTube Video"
                allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture"
                allowFullScreen
            ></iframe>
        </ReactModal>
    );
};

export default VideoModal;
