# JWT 简介

全称是Json Web Token。是一种基于客户端的身份认证机制。 通俗来讲就是将身份信息&签名下发到客户端，客户端之后的请求将身份信息带上来。 内容是json格式的，加签名防篡改。 <br/>
整个token的格式为: header.payload.hash。每一段都要base64编码。

## header

header部分固定两个字段。alg和typ

```json
{
  "alg": "HS256",
  //ALGORITHM ,默认算法 哈希256
  "typ": "JWT"
  //TOKEN TYPE ,token类型
}
```

## payload

payload官方对了以下7个字段：

- iss (issuer)：签发人
- exp (expiration time)：过期时间
- sub (subject)：主题
- aud (audience)：受众
- nbf (Not Before)：生效时间
- iat (Issued At)：签发时间
- jti (JWT ID)：编号 当然用户可以自定义

## hash

将header以及payload使用hash算法加签名

```
HMACSHA256(
  base64UrlEncode(header) + "." +
  base64UrlEncode(payload),
  secret)
```

注意这里为了防止篡改，使用了带key的hash算法。key只是服务端知道。

# Java auth0使用

Java的JWT库还是很多的，这里介绍下auth0的方法

生成了如下的token：

```java
@Test
public void test1(){
        Algorithm alg=Algorithm.HMAC256("MY_SECRETE");
        String token=JWT.create()
        .withIssuer("ly")
        .withIssuedAt(Instant.now())
        .withExpiresAt(Instant.now().plusSeconds(3600))
        .withClaim("userId",10086)
        .sign(alg);
        log.info("token: {}",token);
        }
```

```
eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJseSIsImV4cCI6MTY3MjQ2MjQ5MiwiaWF0IjoxNjcyNDU4ODkyLCJ1c2VySWQiOjEwMDg2fQ.4nZucl_a0GiAidjN5TtVk-Hw-DxuBpKwSzzXlDK2Slc
```

我们可以在jwt的官网进行反解：https://jwt.io/

可以使用如下代码反解token：

```java
    @Test
public void test2(){
        String token="eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJseSIsImV4cCI6MTY3MjQ2MzA5OCwiaWF0IjoxNjcyNDU5NDk4LCJ1c2VySWQiOjEwMDg2fQ._cchq_2tZbI0j4uGzY5tEolBPHhubJryNkzss2NwJU8";
        Algorithm alg=Algorithm.HMAC256("MY_SECRETE");
        JWTVerifier verifier=JWT.require(alg).build();
        DecodedJWT decodedJWT=verifier.verify(token);
        log.info("header: {}",decodedJWT.getHeader());
        log.info("payload: {}",decodedJWT.getPayload());
        log.info("sig: {}",decodedJWT.getSignature());

        log.info("issuer : {}",decodedJWT.getIssuer());
        log.info("expiresAt : {}",decodedJWT.getExpiresAt());
        log.info("userId : {}",decodedJWT.getClaim("userId").asLong());
        }
```