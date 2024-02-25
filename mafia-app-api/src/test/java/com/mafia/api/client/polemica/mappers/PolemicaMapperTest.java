package com.mafia.api.client.polemica.mappers;

import org.junit.Test;
import org.mapstruct.factory.Mappers;

import com.mafia.api.client.polemica.models.PolemicaPlayer;
import com.mafia.api.models.GameParticipant;

public class PolemicaMapperTest {
    private final PolemicaMapper mapper = Mappers.getMapper(PolemicaMapper.class);

    private static final String POLEMICA_PLAYER_ID = "testid";
    private static final String POLEMICA_PLAYER_USERNAME = "username";


    @Test
    public void mapFromPolemicaPlayerToGameParticipant() {
        final PolemicaPlayer polemicaPlayer = PolemicaPlayer.builder()
                                                //   .id(POLEMICA_PLAYER_ID)
                                                  .username(POLEMICA_PLAYER_USERNAME)
                                              .build();
        final GameParticipant gameParticipant = mapper.polemicaPlayerToParticipant(polemicaPlayer);
        
        

    }
}
