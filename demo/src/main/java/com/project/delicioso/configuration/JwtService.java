package com.project.delicioso.configuration;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService { // Class which manipulate the JWT Token

    @Value("${security.jwt.secret-key}")
    private String secretKey;

    // private static final String SECRET_KEY = "DA8B363A6C8F7E17843ACB014FF217AA68564441BD3AC671AC805152AD677C4E"; // generated online

    @Value("${security.jwt.expiration-time}")
    private Long jwtExpiration;

    // Method to extract the user email
    public String extractUserEmail(String jwtToken) {
        return extractClaim(jwtToken, Claims::getSubject); // Subject should be username/userEmail of the user
    }

    // Method to extract a single claim that we pass
    public <T> T extractClaim(String jwtToken, Function<Claims, T> claimsResolver) {
        final  Claims claims = extractAllClaims(jwtToken);
        return claimsResolver.apply(claims);
    }

    // Method to generate a token without extraClaims (generate a token from userDetails)
    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    // Method that will help to generate a token
    public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) { // This map of string object will contain the claims/extra claims that we want to add
        return buildToken(extraClaims, userDetails, jwtExpiration);
    }

    public long getExpirationTime() {
        return jwtExpiration;
    }

    public String buildToken(Map<String, Object> extraClaims, UserDetails userDetails, long expiration) {
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername()) // it should be my username/userEmail
                .setIssuedAt(new Date(System.currentTimeMillis())) // when this claim was created + help to calculate expiration date or to check if the token is still valid
                .setExpiration(new Date(System.currentTimeMillis() + expiration)) // how long the token should be valid = valid for 24h + 1000ms
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact(); // method that will generate and return the token
    }

    // Method that validate a token
    public boolean isTokenValid(String jwtToken, UserDetails userDetails) { // userDetails because we want to validate if this token belong to this userDetails
        final String username = extractUserEmail(jwtToken);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(jwtToken);
    }

    // Method to check if this token expired
    private boolean isTokenExpired(String jwtToken) {
        return extractExpiration(jwtToken).before(new Date());
    }

    // Method to return the expiration date
    private Date extractExpiration(String jwtToken) {
        return extractClaim(jwtToken, Claims::getExpiration);
    }

    // Method to extract all claims
    private Claims extractAllClaims(String jwtToken) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignInKey()) // when we try to create/generate/decode a token we need to use signIn key
                .build()
                .parseClaimsJws(jwtToken)
                .getBody(); // within it we can get all the claims that we have within this token (jwtToken)
    }

    // Method to retrieve the signIn key
    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes); // hmacShaKeyFor -> SignIn algorithm
    }
}
