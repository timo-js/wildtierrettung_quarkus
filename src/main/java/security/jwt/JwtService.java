package security.jwt;

import io.smallrye.jwt.build.Jwt;
import jakarta.inject.Singleton;

@Singleton
public class JwtService
{
	public String generateJwt(String username, String roles) {
		return Jwt.issuer("wildtierrettung")
			.subject("wildtierrettung")
			.upn(username)
			.groups(roles)
			.expiresAt(System.currentTimeMillis() + 3600)
			.sign();
	}
}
