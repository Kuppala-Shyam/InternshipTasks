package com.example.User.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * Utility class for handling JSON Web Tokens (JWT).
 */
@Component
public class JwtUtil {
	
	/**
     * JWT token validity period in seconds (5 hours).
     */
    public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60; // token expire date
    
    /**
     * Secret key for JWT generation and validation.
     */
    private String secret = "afafasfafafasfasfasfafacasdasfasxASFACASDFACASDFASFASFDAFASFASDAADSCSDFADCVSGCFVADXCcadwavfsfarvf"; // secret code

    /**
     * Retrieve username from JWT token.
     * 
     * @param token The JWT token.
     * @return The username extracted from the token.
     */
    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    } // user to get name

    /**
     * Retrieve expiration date from JWT token.
     * 
     * @param token The JWT token.
     * @return The expiration date extracted from the token.
     */
    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    } // get Expiration Date from token
    
    /**
     * Retrieve a specific claim from JWT token.
     * 
     * @param token          The JWT token.
     * @param claimsResolver A function to resolve the specific claim from Claims.
     * @return The claim extracted from the token.
     */
    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {     // i d k
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    /**
     * Retrieve all claims from JWT token.
     * 
     * @param token The JWT token.
     * @return All claims extracted from the token.
     */

    private Claims getAllClaimsFromToken(String token) {                                    // i d k
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    /**
     * Check if the JWT token has expired.
     * 
     * @param token The JWT token.
     * @return True if the token has expired, otherwise false.
     */
    private Boolean isTokenExpired(String token) {                                      // checking expire
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    /**
     * Generate a JWT token for a user.
     * 
     * @param userDetails The UserDetails object containing user details.
     * @return The generated JWT token.
     */

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();                           // geenrate token
        return doGenerateToken(claims, userDetails.getUsername());
    }
    
    /**
     * Generate a JWT token with specific claims.
     * 
     * @param claims  The claims to be included in the token.
     * @param subject The subject of the token (usually username).
     * @return The generated JWT token.
     *


    * While creating the token -
     * 1. Define claims of the token, like Issuer, Expiration, Subject, and the ID
     * 2. Sign the JWT using the HS512 algorithm and secret key
     * 3. According to JWS Compact Serialization(https://tools.ietf.org/html/draft-ietf-jose-json-web-signature-41#section-3.1)
        compaction of the JWT to a URL-safe string
     */
    private String doGenerateToken(Map<String, Object> claims, String subject) {
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
                .signWith(SignatureAlgorithm.HS512, secret).compact();
    }

    /**
     * Validate a JWT token.
     * 
     * @param token       The JWT token to be validated.
     * @param userDetails The UserDetails object containing user details.
     * @return True if the token is valid, otherwise false.
     */
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}
