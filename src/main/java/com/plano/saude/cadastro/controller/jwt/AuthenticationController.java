package com.plano.saude.cadastro.controller.jwt;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {

	@Autowired
    private TokenService tokenService; 

	@Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/authenticate")
    public ResponseEntity<TokenResponse> generateToken(@RequestBody TokenRequest tokenRequest) {

        var authenticationToken = new UsernamePasswordAuthenticationToken(
        							tokenRequest.username(),
        							tokenRequest.password()
        						);

        var authentication = authenticationManager.authenticate(authenticationToken);

        var token = tokenService.generateToken(authentication);

        return ResponseEntity.ok(new TokenResponse(token));
    }
}