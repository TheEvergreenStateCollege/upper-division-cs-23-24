import Browser from "./browser";
import Cookies from 'js-cookie';
import React, { useState, useEffect } from 'react';
import FormModal from "./FormModal";

const Contents = () => {
    const [showModal, setShowModal] = useState(false);
    const uname = Cookies.get('user')!;
    const [jsonData, setJsonData] = useState<any[]>([]);

    useEffect(() => {
        fetch('/api/posts')
            .then(response => response.json())
            .then(data => {
                console.log(data);
                setJsonData(data.data);
            })
            .catch(error => {
                console.error('Error fetching data:', error);
            });
    }, []);

    const Form = () => {
        return (
            <div >
                <form action={'/api/updateUser'} method="post">
                    <input className=" mx-2 mt-20 px-2 border border-cus min-w-40"
                           type="text" autoComplete="username"
                           name="Username"
                           id="uname" />
                    <button className="mt-2 px-2 bg-panelgray border border-cus"
                            type="submit"> Change Username </button>
                </form>

                <form action={'/api/updatePass'} method="post">
                    <input className=" mx-2 mt-1 px-3 border border-cus min-w-40"
                           type="password"
                           autoComplete="new-password"
                           name="Password" id="pword" />
                    <button className="mt-2 px-2 bg-panelgray border border-cus"
                            type="submit"> Change Password </button>
                </form>

                <form action={'/api/delete'} method="post">
                    <button className="mt-2 px-2 bg-panelgray border border-cus">Delete Account</button>
                </form>

                <form action={'/api/logoff'} method="post">
                    <button className="mt-2 px-2 bg-panelgray border border-cus">Log Off</button>
                </form>
            </div>
        );
    }

    const openModal = () => {
        setShowModal(true);
    };

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
                className="bg-panelgray active:bg-bargray font-bold uppercase text-sm px-6 py-3 shadow hover:shadow-lg
                border border-cus outline-none focus:outline-none mr-1 mb-1 ease-linear transition-all duration-150"
                type="button"
                onClick={openModal}
            >
                Modify Account
            </button>

            <FormModal showModal={showModal} setShowModal={setShowModal}>
                <h1 className="text-xl">Modify Account</h1>
                <Form />
            </FormModal>
        </div>
    );
}

const Profile = () => {
    return (
        <Browser content={<Contents />} />
    );
}

export default Profile;
