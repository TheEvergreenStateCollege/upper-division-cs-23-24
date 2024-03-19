import React, { useEffect, useState } from "react";
import Browser from "./browser";

const GetUsers = () => {
    const [usernames, setUsernames] = useState<{ username: string }[]>([]);

    useEffect(() => {
        // Fetch data from your API
        fetch('/api/users')
            .then(response => response.json())
            .then(data => {
                console.log(data);
                // Store usernames in state
                setUsernames(data);
            })
            .catch(error => {
                console.error('Error fetching data:', error);
            });
    }, []);

    const Display = () => {
        return (
            <div>
                <strong><h1 className="text-xl">Users</h1></strong>
                {usernames.length > 0 ? (
                    <ul>
                        {usernames.map((user, index) => (
                            <li key={index}>
                                {user.username}<br />
                            </li>
                        ))}
                    </ul>
                ) : (
                    <p>Loading...</p>
                )}
            </div>
        );
    }
    return(
        <Browser content={<Display/>}/>
    )
};

export default GetUsers;
