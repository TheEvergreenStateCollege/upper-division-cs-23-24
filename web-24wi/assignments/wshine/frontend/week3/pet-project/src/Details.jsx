import { useParams, useNavigate } from "react-router-dom";
import { useQuery } from "@tanstack/react-query";
import fetchPet from "./fetchPet";
import Carousel from "./Carousel";
import ErrorBoundary from "./ErrorBoundary";
import { useState, useContext } from "react";
import Modal from "./Modal";
import AdoptedPetContext from "./AdoptedPetContext";

const Details = () => {
    const navigate = useNavigate();
    const [, setAdoptedPet] = useContext(AdoptedPetContext);

    const { id } = useParams();
    const [showModal, setShowModal] = useState(false);
    console.log(id);
    const results = useQuery(["details", id], fetchPet);
    console.log(results);
    if (results.isLoading) {
        return (
            <div className="loading-pane">
                <h2 className="loader">🌀</h2>
            </div>
        );
    }
    const pet = results.data.pets[0];
    return (
        <div className="details">
            <Carousel images={pet.images} />
            <div>
                <h1 className="text-center text-[#333] text-6xl mx-0 my-[5px]">{pet.name}</h1>
                <h2 className="text-center mt-[5px] mb-5 mx-0">{`${pet.animal} - ${pet.breed} - ${pet.city}, - ${pet.state}`}</h2>
                <button 
                    className="bg-[#ad343e] text-[white] text-lg block cursor-pointer mx-auto my-0 px-[25px] py-[5px] rounded-[5px] border-[#333] border-[solid]"
                    onClick={() => setShowModal(true)}>
                    Adopt {pet.name}
                </button>
                <p className="leading-normal px-[15px] py-0">{pet.description}</p>
                {
                    showModal ? (
                        <Modal>
                            <div className="bg-[white] max-w-[500px] text-center p-[15px] rounded-[30px]">
                                <h1 className="text-center text-[#333] text-6xl mx-0 my-[5px]">Would you like to adopt {pet.name}?</h1>
                                <div className="">
                                    <button
                                        className="bg-[#ad343e] text-[white] text-lg block cursor-pointer mx-auto my-0 px-[25px] py-[5px] rounded-[5px] border-[#333] border-[solid]"
                                        onClick={() => {
                                            setAdoptedPet(pet);
                                            navigate("/");
                                        }}
                                    >
                                        Yes
                                    </button>
                                    <button className="bg-[#ad343e] text-[white] text-lg block cursor-pointer mx-auto my-0 px-[25px] py-[5px] rounded-[5px] border-[#333] border-[solid]" onClick={() => setShowModal(false)}>No</button>
                                </div>
                            </div>
                        </Modal>
                    ) : null
                }
            </div>
        </div>
    );
};

export default function DetailsErrorBoundary(props) {
    return (
        <ErrorBoundary>
            <Details {...props} />
        </ErrorBoundary>
    );
}
