import React, { FC } from 'react';

type InputProps = {
    label: string;
    handleChange: () => void;
};

const Input: React.FC<InputProps> = ({label, handleChange}) => {
    return (
        <>
            <label htmlFor="test search">{label}: </label>
            <input id="username" type="text" onChange={handleChange}/>
        </>
    );
};

export default Input;