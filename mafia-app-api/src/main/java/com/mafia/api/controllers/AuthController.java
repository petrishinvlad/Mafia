package com.mafia.api.controllers;

import java.util.concurrent.Semaphore;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600, allowedHeaders = "*")
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    // @GetMapping("/test")
    // public ResponseEntity<?> testEndpoint() {
        // NewGamePollRequest newGamePollRequest = NewGamePollRequest.builder()
        //                                         .chat_id(null)
        //                                         .is_anonymous(false)
        //                                         .address(null)
        //                                         .gameDate(null)
        //                                         .gameTime(null)
        //                                         .gameDay(null)
        //                                         .options(null)
        //                                         .build();
        // messenger.sendPoll(null);
    //     var polemicaResponse = polemica.polemicaGameStats("221645");
    //     return ResponseEntity.ok("qweads");
    // }
    
    // @GetMapping("/signin")
	// public ResponseEntity<?> authenticateUser(@RequestParam String username, 
	// 										  @RequestParam String password) {
		// try {
		// 	SigninResult signinResult = userAuthService.signin(username, password);
		// 	AuthCustomResponse response = new AuthCustomResponse(signinResult);
			// return ResponseEntity.ok(response.toString());
		// } catch (Exception ex) {
		// 	return ResponseEntity.internalServerError().body(new AuthErrorResponse(/* "Internal Server Error")*/ ex.getMessage()));
		// }
	// }
}
