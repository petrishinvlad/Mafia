import React, { FC, useState } from 'react';
import { Player } from '../../models/Player';
import { DataGrid, GridApi, GridColDef, GridRowsProp } from '@mui/x-data-grid';
import Button from '@mui/material/Button';
import PlayerInput from './PlayerInput';

const PlayerLadder = () => {
    const [players, setPlayers] = useState<Player[]>([]);

    const addPlayer = (player: Player) => {
        setPlayers([...players, {
            id: players.length + 1, 
            firstname: player.firstname,
            lastname: player.lastname,
            nickname: player.nickname,
        }]);
    };

    const cols: GridColDef[] = [
        { field: "firstname", headerName: "First Name", width: 350},
        { field: "lastname", headerName: "Last Name", width: 350},
        { field: "nickname", headerName: "Nickname", width: 350},
        {
            field: "action",
            headerName: "Action",
            sortable: false,
            width: 450,
            renderCell: (params) => {
              const onClick = (e: { stopPropagation: () => void; }) => {
                e.stopPropagation();
                const api: GridApi = params.api;
                const playersWithoutRemovedItem = players.filter(player => player.id !== params.id);
                playersWithoutRemovedItem.forEach((player, index) => {
                    player.id = index + 1;
                });
                setPlayers(playersWithoutRemovedItem);
              };
        
              return <Button onClick={onClick}>Remove Row</Button>;
            }
          },
    ];

    const playerInputProps = { addPlayer };

    return (
        <>
            New Player Ladder
            <PlayerInput {...playerInputProps}></PlayerInput>
            <DataGrid rows={players} columns={cols}></DataGrid>
        </>
    );
}

export default PlayerLadder;