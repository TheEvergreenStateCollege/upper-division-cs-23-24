import Browser from "./browser";
import Cookies from 'js-cookie';
import React, { useState, useEffect } from 'react';

const Contents = () => {
    const uname = Cookies.get('user')!;
    const [jsonData, setJsonData] = useState<any[]>([]);

    useEffect(() => {
        // Fetch data from your API
        fetch('http://localhost:3001/api/posts')
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
        <div>
            <h1>Welcome {uname}</h1>
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

            <div>
                {jsonData.length > 0 ? (
                    <ul>
                        {jsonData.map((item, index) => (
                            <li key={index}>
                                <strong>Post Name:</strong> {item.name}<br />
                                <strong>Post Body:</strong> {item.body}
                            </li>
                        ))}
                    </ul>
                ) : (
                    <p>Loading...</p>
                )}
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
