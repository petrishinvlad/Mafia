import React, { FC, useEffect } from 'react';
import LoginButton from './LoginButton'; 
import Input from '../input/Input';

const Auth = () => {
    const handleChange = () => {
        console.log('Here');
    };


    return (
        <>
            Welcome to London Mafia Club!
            <br/>
            <Input label="username" handleChange={handleChange}></Input>
            <br/>
            <Input label="password" handleChange={handleChange}></Input>
            <br/>
            <LoginButton label="New Test Login"></LoginButton>      
        </>
    );
};

export default Auth;