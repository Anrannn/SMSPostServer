package ltd.nanoda;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtUtil {
    private static final long EXPIRE = 60000*10;
    public static final SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256);//密钥，动态生成的密钥

    public static String generate() {
        Date nowDate = new Date();
        //过期时间,设定为一分钟
        Date expireDate = new Date(System.currentTimeMillis() + EXPIRE);
        //头部信息,可有可无
        Map<String, Object> header = new HashMap<>(2);
        header.put("typ", "jwt");


        return Jwts.builder().setHeader(header)
                 .setIssuer("nanoda.ltd") //发送方
                .setIssuedAt(nowDate)//当前时间
                .setExpiration(expireDate) //过期时间
                .signWith(key)//签名算法和key
                .compact();
    }

    public static boolean verify(String token){
         try {
             Jwts.parserBuilder()
                     .setSigningKey(key)
                     .build()
                     .parseClaimsJws(token);
             return true;
         }catch (JwtException e){
             e.printStackTrace();
             return false;
         }
    }

    /**
     * 获取payload 部分内容（即要传的信息）
     * 使用方法：如获取userId：getClaim(token).get("userId");
     * @param token
     * @return
     */
    public static Claims getClaim(String token) {
        Claims claims = null;
        try {
            claims = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return claims;
    }

    /**
     *
     * @param token
     * @return jwt签发时间
     */
    public static Date getIssuedAt(String token){
        return getClaim(token).getIssuedAt();
    }

    /**
     *
     * @param token
     * @return jwt失效时间
     */
    public static Date getExpiration(String token){
        return getClaim(token).getExpiration();
    }

    /**
     *
     * 验证token过期
     * @param token
     * @return true:过期 false:没过期
     */
    public static boolean isExpired(String token){
        try {
            Date expiration = getExpiration(token);
            return expiration.before(new Date());
        }catch (ExpiredJwtException e){
            return true;
        }
    }







}
