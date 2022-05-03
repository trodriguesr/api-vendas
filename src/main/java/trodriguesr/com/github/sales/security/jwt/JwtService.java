package trodriguesr.com.github.sales.security.jwt;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import trodriguesr.com.github.sales.models.entities.AuthenticationUser;

@Service
public class JwtService {
	
	@Value("${security.jwt.expiration}")
    private String expiration;

    @Value("${security.jwt.subscription-key}")
    private String subscriptionKey;

    public String generatingToken( AuthenticationUser authUser ){
        long expString = Long.valueOf(expiration);
        LocalDateTime expirationDateAndTime = LocalDateTime.now().plusMinutes(expString);
        Instant instant = expirationDateAndTime.atZone(ZoneId.systemDefault()).toInstant();
        Date date = Date.from(instant);

        return Jwts
                .builder()
                .setSubject(authUser.getLogin())
                .setExpiration(date)
                .signWith( SignatureAlgorithm.HS512, subscriptionKey )
                .compact();
    }

    private Claims getClaims( String token ) throws ExpiredJwtException {
        return Jwts
                 .parser()
                 .setSigningKey(subscriptionKey)
                 .parseClaimsJws(token)
                 .getBody();
    }

    public boolean validToken( String token ){
        try{
            Claims claims = getClaims(token);
            Date expirationDate = claims.getExpiration();
            LocalDateTime date =
                    expirationDate.toInstant()
                            .atZone(ZoneId.systemDefault()).toLocalDateTime();
            return !LocalDateTime.now().isAfter(date);
        }catch (Exception e){
            return false;
        }
    }

    public String getUserLogin(String token) throws ExpiredJwtException{
        return (String) getClaims(token).getSubject();
    }
}
