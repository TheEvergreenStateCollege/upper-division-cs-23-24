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

    return (
        <div > 
            
                <div className=" mb-4 text-2xl font-bold"> <h1>Welcome {uname}</h1></div>
           
                <div className="flex flex-col" >
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
                </div>
                <div className="flex flex-col">
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

        </div>
    );
}

const Profile = () => {
    return (
        <Browser content={<Contents />} />
    );
}

export default Profile;
