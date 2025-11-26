package jwt.web.token.Security.configuration;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtils {

    private final SecretKey key= Keys.hmacShaKeyFor("mysecretkeymysecretkeymysecretkey12".getBytes());
    private final long EXPIRATIONtIME=1000*60*60;

    public String generateToken(String userName,String role){
        Map<String,Object> claim=new HashMap<>();
        claim.put("role",role);

        return Jwts.builder()
                .claims(claim)
                .subject(userName)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis()+EXPIRATIONtIME))
                .signWith(key)
                .compact();
    }

    public String extractUserName(String token){
      return  extractAllClaims(token).getSubject();
    }
    public String extractRole(String token){
        return extractAllClaims(token).get("role",String.class);
    }
    public boolean validateToken(String token){
        return extractAllClaims(token).getExpiration().before(new Date());
    }

    public Claims extractAllClaims(String token){
        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}
