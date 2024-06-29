import { Button, TextField } from '@mui/material';
import React, { FC, useReducer } from 'react';
import { Player } from '../../models/Player';

type PlayerInputProps = {
    addPlayer: (player: Player) => void;
};

const PlayerInput: FC<PlayerInputProps> = ({addPlayer})  => {
    const [player, dispatchPlayer] = useReducer(
        (state: any, action: { type: string; payload: string; }) => {
            if (action.type === "SET_FIRSTNAME") {
                return {...state, firstname: action.payload};
            } else if (action.type === "SET_LASTNAME") {
                return {...state, lastname: action.payload};
            } else if (action.type === "SET_NICKNAME") {
                return {...state, nickname: action.payload};
            } else {
                throw new Error("Wrong reducer command");
            }
        },
        {});
    
    const clickAddPlayerBtn = (player: Player) => {
        if (addPlayer) {
            console.log(player);
            addPlayer(player);
        };
    };
    return (
        <div>
            Enter the player details:
            <div>
                <TextField 
                    id="first-name" 
                    variant="outlined" 
                    label="First Name" 
                    margin="normal"
                    onChange={(event: React.ChangeEvent<HTMLInputElement>) => {
                        dispatchPlayer({
                            type: "SET_FIRSTNAME",
                            payload: event.target.value,
                        });
                      }}
                ></TextField>
            </div>
            <div>
                <TextField 
                    id="last-name" 
                    variant="outlined" 
                    label="Last Name" 
                    margin="normal"
                    onChange={(event: React.ChangeEvent<HTMLInputElement>) => {
                        dispatchPlayer({
                            type: "SET_LASTNAME",
                            payload: event.target.value,
                        });
                      }}
                ></TextField>
            </div>
            <div>
                <TextField 
                    id="nick-name" 
                    variant="outlined" 
                    label="Nick Name" 
                    margin="normal"
                    onChange={(event: React.ChangeEvent<HTMLInputElement>) => {
                        dispatchPlayer({
                            type: "SET_NICKNAME",
                            payload: event.target.value,
                        });
                      }}
                ></TextField>
            </div>
            <Button 
                variant="contained" 
                onClick={() => clickAddPlayerBtn(player)}>
                    Add player to the list
            </Button>
        </div>
    );
}

export default PlayerInput;