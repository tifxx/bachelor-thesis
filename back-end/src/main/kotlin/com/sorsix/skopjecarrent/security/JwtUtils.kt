package com.sorsix.skopjecarrent.security

import com.sorsix.skopjecarrent.model.User
import io.jsonwebtoken.*
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseCookie
import org.springframework.stereotype.Component
import org.springframework.web.util.WebUtils
import java.util.*
import javax.servlet.http.HttpServletRequest
import org.springframework.security.core.Authentication



@Component
class JwtUtils {
    private val jwtSecret: String = "bezKoderSecretKey"
    private val jwtExpirationMs = 86400000

    fun generateJwtToken(authentication: Authentication): String {
        val userPrincipal = authentication.principal as User
        return Jwts.builder()
            .setSubject(userPrincipal.username)
            .setIssuedAt(Date())
            .setExpiration(Date(Date().time + jwtExpirationMs))
            .signWith(SignatureAlgorithm.HS512, jwtSecret)
            .compact()
    }

    fun getUserNameFromJwtToken(token: String?): String {
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).body.subject
    }

    fun validateJwtToken(authToken: String?): Boolean {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken)
            return true
        } catch (e: SignatureException) {
            logger.error("Invalid JWT signature: {}", e.message)
        } catch (e: MalformedJwtException) {
            logger.error("Invalid JWT token: {}", e.message)
        } catch (e: UnsupportedJwtException) {
            logger.error("JWT token is unsupported: {}", e.message)
        } catch (e: IllegalArgumentException) {
            logger.error("JWT claims string is empty: {}", e.message)
        } catch (e: Exception) {
            logger.error("JWT token is expired: {}", e.message)
        }
        return false
    }


    companion object {
        private val logger = LoggerFactory.getLogger(JwtUtils::class.java)
    }
}