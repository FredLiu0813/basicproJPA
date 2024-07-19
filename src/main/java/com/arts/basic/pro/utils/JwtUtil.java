package com.arts.basic.pro.utils;

import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;
import java.util.Map;

@Slf4j
public class JwtUtil {

	public static final String KEY = "022bdc63c3c5a45879ee6581508b9d03adfec4a4658c0ab3d722e50c91a351c42c231cf43bb8f86998202bd301ec52239a74fc0c9a9aeccce604743367c9646b";

	public static final long AVAILABLE_MILL = 4 * 60 * 60 * 1000L;

	public static final String ISSUER = "ARTS";

	public static SecretKey generalKey() {
		byte[] encodedKey = Base64.decodeBase64(KEY);
		SecretKeySpec key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
		System.out.println(key.toString());
		return key;
	}

	/**
	 * 创建jwt
     */
	public static String createJWT(Map<String, Object> claims) {
		try {
			// 指定签名的时候使用的签名算法，也就是header那部分，jjwt已经将这部分内容封装好了。
			SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

			// 生成JWT的时间
			long nowMillis = System.currentTimeMillis();
			Date now = new Date(nowMillis);

			// 创建payload的私有声明（根据特定的业务需要添加，如果要拿这个做验证，一般是需要和jwt的接收方提前沟通好验证方式的）
			// 创建payload的私有声明（根据特定的业务需要添加，如果要拿这个做验证，一般是需要和jwt的接收方提前沟通好验证方式的）
			//            claims.put("uid", "123456");
			//            claims.put("user_name", "admin");
			//            claims.put("nick_name", "X-rapido");

			// 生成签名的时候使用的秘钥secret，切记这个秘钥不能外露哦。它就是你服务端的私钥，在任何场景都不应该流露出去。
			// 一旦客户端得知这个secret, 那就意味着客户端是可以自我签发jwt了。
			SecretKey key = generalKey();

			// 下面就是在为payload添加各种标准声明和私有声明了
			JwtBuilder builder = Jwts.builder() // 这里其实就是new一个JwtBuilder，设置jwt的body
					.setClaims(claims) // 如果有私有声明，一定要先设置这个自己创建的私有的声明，这个是给builder的claim赋值，一旦写在标准的声明赋值之后，就是覆盖了那些标准的声明的
					.setId(Identities.uuid2()) // 设置jti(JWT ID)：是JWT的唯一标识，根据业务需要，这个可以设置为一个不重复的值，主要用来作为一次性token,从而回避重放攻击。
					.setIssuedAt(now) // iat: jwt的签发时间
					.setIssuer(ISSUER) // issuer：jwt签发人
					// TODO token主体目前是随机串
					.setSubject(ISSUER + Identities.randomBase62(8)) // sub(Subject)：代表这个JWT的主体，即它的所有人，这个是一个json格式的字符串，可以存放什么userid，roldid之类的，作为什么用户的唯一标志。
					.signWith(signatureAlgorithm, key); // 设置签名使用的签名算法和签名使用的秘钥

			// 设置过期时间
			if (AVAILABLE_MILL >= 0) {
				//
				long expMillis = nowMillis + AVAILABLE_MILL;
				Date exp = new Date(expMillis);
				builder.setExpiration(exp);
			}
			return builder.compact();
		} catch (Exception e) {
			log.error("生成token失败：{}", e.getMessage());
			throw new RuntimeException("TOKEN 生成失败");
		}
	}

	/**
	 * 解密jwt
	 *
	 * @param jwt
	 * @return
	 */
	public static Claims parseJWT(String jwt) {
		try {
			SecretKey key = generalKey(); //签名秘钥，和生成的签名的秘钥一模一样
			return Jwts.parser() //得到DefaultJwtParser
					.setSigningKey(key) //设置签名的秘钥
					.parseClaimsJws(jwt).getBody();
		} catch (ExpiredJwtException e) {
			log.error("TOKEN已过期:{}", jwt);
			//            throw new RuntimeException("TOKEN 已过期");
			return null;
		} catch (Exception e) {
			log.error("解析token失败:{}, {}", jwt, e.getMessage());
			//            throw new RuntimeException("TOKEN 解析失败");
			return null;
		}
	}

	public static <T> T getJwtInfo(String jwt, String key) {
		return (T) parseJWT(jwt).get(key);
	}

	public static Claims parseJWT() {
		String jwt = getCurrentHeaderToken();
		try {
			SecretKey key = generalKey(); //签名秘钥，和生成的签名的秘钥一模一样
			return Jwts.parser() //得到DefaultJwtParser
					.setSigningKey(key) //设置签名的秘钥
					.parseClaimsJws(jwt).getBody();
		} catch (ExpiredJwtException e) {
			log.error("TOKEN已过期:{}", jwt);
			throw new RuntimeException("TOKEN 已过期");
		} catch (Exception e) {
			log.error("解析token失败:{}, {}", jwt, e.getMessage());
			throw new RuntimeException("TOKEN 解析失败");
		}
	}

	public static <T> T getJwtInfo(String key) {
		return (T) parseJWT().get(key);
	}

	private static String getCurrentHeaderToken() throws IllegalStateException {
		ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		if (attrs == null) {
			throw new IllegalStateException("当前线程中不存在 Request 上下文");
		}
		try {
			String token = attrs.getRequest().getHeader("token");
			log.info("token:{}", token);
			return token;
		} catch (Exception e) {
			log.error("获取token失败：{}", e.getMessage());
		}
		return null;
	}

}
