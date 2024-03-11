import React, { useEffect, useRef } from "react";

const FormModal: React.FC<{ showModal: boolean, setShowModal: React.Dispatch<React.SetStateAction<boolean>>,
  children: React.ReactNode }> = ({ showModal, setShowModal,
                                                     children }) => {
  const elRef = useRef<HTMLDivElement>(document.createElement("div"));

  useEffect(() => {
    const modalRoot = document.getElementById("modal");

    if (!modalRoot || !elRef.current) return;

    modalRoot.appendChild(elRef.current);

    return () => {
      if (modalRoot && elRef.current) {
        modalRoot.removeChild(elRef.current);
      }
    };
  }, []);

  const closeHandler = () => {
    setShowModal(false);
  };

  return showModal ? (
      <>
        <div className="justify-center items-center flex overflow-x-hidden overflow-y-auto fixed inset-0 z-50 outline-none focus:outline-none">
          <div className="relative w-auto my-6 mx-auto max-w-3xl">
            <div className="border border-cus rounded-lg shadow-lg relative flex flex-col w-full bg-bargray outline-none focus:outline-none">
              <div className="p-4">
                {children}
              </div>
              <button
                  className="mt-2 px-2 bg-panelgray border border-cus"
                  type="button"
                  onClick={closeHandler}
              >
                Close
              </button>
            </div>
          </div>
        </div>

        <div className="opacity-25 fixed inset-0 z-40 bg-black"></div>
      </>
  ) : null;
};

export default FormModal;
