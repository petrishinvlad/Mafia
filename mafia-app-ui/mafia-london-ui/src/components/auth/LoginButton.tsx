import React, { FC, useEffect, useState } from 'react';

type LoginButtonProps = {
    label: string;
}

const LoginButton: React.FC<LoginButtonProps> = ({label}) => {
    const [data, setData] = useState('');

    const login = async() => {
        try {
            const res = await fetch('http://localhost:8080/api/v1/game/test', 
                                    { 
                                        // mode: 'cors',     
                                        // credentials: "same-origin", // include, *same-origin, omit
                                        headers: {
                                            // "Accept": "application/json",
                                            // "Content-Type": "application/json",
                                            // "Access-Control-Allow-Origin": "*",
                                            // "Access-Control-Allow-Headers": "Origin, X-Requested-With, Content-Type, Accept",
                                            // "Access-Control-Allow-Methods": "GET, POST, PATCH, PUT, DELETE, OPTIONS",
                                        },
                        });
            const data = await res.json();
            console.log(data);
            setData(data);
        } catch(e) {
            console.error('!!!!');
            console.error(e);
        }
    };

    return (
        <button onClick={() => login()}>{label}</button> 
    );
};

export default LoginButton;