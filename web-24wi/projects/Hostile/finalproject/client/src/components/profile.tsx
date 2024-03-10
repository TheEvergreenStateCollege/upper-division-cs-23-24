import Browser from "./browser";
import Cookies from 'js-cookie';
import React, { useState, useEffect } from 'react';
import Modal from "./Modal";


const Contents = () => {
    const [showModal, setShowModal] = useState(false);
    const uname = Cookies.get('user')!;
    const [jsonData, setJsonData] = useState<any[]>([]);

    useEffect(() => {
        // Fetch data from your API
        fetch('/api/posts')
            .then(response => response.json())
            .then(data => {
                console.log(data);
                // Store data in state
                setJsonData(data.data); 
            })
            .catch(error => {
                console.error('Error fetching data:', error);
            });
    }, []);

    const Form = () => {
        return (
            <div> 
                <form action={'/api/updateUser'} method="post">
                    <input className=" mx-2 mt-20 px-2 border border-cus min-w-40" type="text" autoComplete="username" name="Username" id="uname" />
                    <button className="mt-2 px-2 bg-panelgray border border-cus" type="submit"> Change Username </button>
                </form>

                <form action={'/api/updatePass'} method="post">
                    <input className=" mx-2 mt-1 px-3 border border-cus min-w-40" type="password" autoComplete="new-password" name="Password" id="pword" />
                    <button className="mt-2 px-2 bg-panelgray border border-cus" type="submit"> Change Password </button>
                </form>

                <form action={'/api/delete'} method="post">
                    <button className="mt-2 px-2 bg-panelgray border border-cus">Delete Account</button>
                </form>

                <form action={'/api/logoff'} method="post">
                    <button className="mt-2 px-2 bg-panelgray border border-cus">Log Off</button>
                </form>
        </div>
        )
    }
    
    const openModal = () => {
        setShowModal(true);
    };
    console.log("showModal:", showModal);
    return (
        <div> 
            <div className="mb-4 text-2xl font-bold">
                <h1>Welcome {uname}</h1>
            </div>
            <h1 className="text-xl">Posts</h1>
            {jsonData.length > 0 ? (
                <ul>
                    {jsonData.map((item, index) => (
                        <li key={index}>
                            <strong>{item.name}</strong> <br />
                            {item.body}
                        </li>
                    ))}
                </ul>
            ) : (
                <p>Loading...</p>
            )}

            

<button
        className="bg-panelgray  active:bg-bargray font-bold uppercase text-sm px-6 py-3  shadow hover:shadow-lg border border-cus outline-none focus:outline-none mr-1 mb-1 ease-linear transition-all duration-150"
        type="button"
        onClick={() => setShowModal(true)}
      >
       Modify Account
      </button>
      {showModal ? (
        <>
            <div
            className="justify-center items-center flex overflow-x-hidden overflow-y-auto fixed inset-0 z-50 outline-none focus:outline-none"
            >
                <div className="relative w-auto my-6 mx-auto max-w-3xl">
                    <div className="border border-cus rounded-lg shadow-lg relative flex flex-col w-full bg-bargray outline-none focus:outline-none">
                    <h1 className="text-xl">Modify Account</h1>
                          <Form/>  
                        <button
                            className="mt-2 px-2 bg-panelgray border border-cus"
                            type="button"
                            onClick={() => setShowModal(false)}
                        >
                            Close
                        </button>
                    </div>
                </div>
            </div>
           
            
        
          <div className="opacity-25 fixed inset-0 z-40 bg-black"></div>
        </>
      ) : null}



            
        </div>
    );
}

const Profile = () => {
    return (
        <Browser content={<Contents />} />
    );
}


export default Profile;
