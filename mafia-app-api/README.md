``` mermaid
--- 
title: Mafia Bot model 
---
classDiagram 
class MafiaBotGame { 
    - id : UUID 
    - mafiaJudge : MafiaBotUser 
    - gameStatus : MafiaBotGameStatus 
    - players : Set~MafiaBotPlayer~(Eager, Cascade ALL) 
    - gameTime : LocalDateTime 
    - bestMove : String 
    - playerComments : String 
    - judgeComments : String 
} 
class MafiaBotGameRole { 
    <<enumeration>> 
    CIVILIAN, 
    MAFIA, 
    HERIFF, 
    GODFATHER 
} 
class MafiaBotGameStatus { 
    <<enumeration>> 
    GAME_ANNOUNCED, 
    GAME_STARTED, 
    GAME_POSTPONED, 
    GAME_FINISHED, 
    GAME_PAUSED, 
    GAME_CANCELLED, 
    GAME_RESULT_CANCELLED 
} 
class MafiaBotPlayer { 
    - id : UUID 
    - MafiaBotUser player 
    - MafiaBotGame game 
    - int position 
    - MafiaBotGameRole role 
    - double points 
    - String comments 
} 
class MafiaBotUser { 
    - UUID id 
    - Set~MafiaBotUserRole~ mafiaBotUserRoles 
    - Set~MafiaBotPlayer~ mafiaPlayers 
    - String nickname 
    - String firstname 
    - String lastname 
    - String location 
    - String swipealPlayerId(?????) 
} 
class MafiaBotUserRole {
    - id: UUID
    - user: MafiaBotUser
    - role: MafiaBotUserRoleValue
} 
class MafiaBotUserRoleValue {
    <<enumeration>>
    ADMIN,
    JUDGE,
    PLAYER
}
MafiaBotGame --* "9..10"  MafiaBotPlayer: Contains
MafiaBotUserRole --> MafiaBotUserRoleValue : Uses
MafiaBotPlayer --> MafiaBotGameRole : Uses
MafiaBotGame --> MafiaBotGameStatus : Uses
MafiaBotUser --* MafiaBotUserRole : Contains
MafiaBotUser --* "0..*"  MafiaBotPlayer: Contains
```