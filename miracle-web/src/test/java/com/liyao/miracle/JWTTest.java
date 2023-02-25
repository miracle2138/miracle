package com.liyao.miracle;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Instant;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author 栗垚
 * @Date 2022/12/31
 */
@Slf4j
@SpringBootTest
public class JWTTest {

    @Test
    public void test1() {
        Algorithm alg = Algorithm.HMAC256("MY_SECRETE");
        String token = JWT.create()
                .withIssuer("ly")
                .withIssuedAt(Instant.now())
                .withExpiresAt(Instant.now().plusSeconds(3600))
                .withClaim("userId", 10086)
                .sign(alg);
        log.info("token: {}", token);
    }

    @Test
    public void test2() {
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJseSIsImV4cCI6MTY3MjQ2MzA5OCwiaWF0IjoxNjcyNDU5NDk4LCJ1c2VySWQiOjEwMDg2fQ._cchq_2tZbI0j4uGzY5tEolBPHhubJryNkzss2NwJU8";
        Algorithm alg = Algorithm.HMAC256("MY_SECRETE");
        JWTVerifier verifier = JWT.require(alg).build();
        DecodedJWT decodedJWT = verifier.verify(token);
        log.info("header: {}", decodedJWT.getHeader());
        log.info("payload: {}", decodedJWT.getPayload());
        log.info("sig: {}", decodedJWT.getSignature());

        log.info("issuer : {}", decodedJWT.getIssuer());
        log.info("expiresAt : {}", decodedJWT.getExpiresAt());
        log.info("userId : {}", decodedJWT.getClaim("userId").asLong());
    }
}
