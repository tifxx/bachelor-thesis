package com.sorsix.skopjecarrent.api

import com.sorsix.skopjecarrent.model.User
import com.sorsix.skopjecarrent.model.enum.ERole
import com.sorsix.skopjecarrent.repository.UserRepository
import com.sorsix.skopjecarrent.security.*
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@CrossOrigin(origins = ["*"], maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
class AuthController(
    val authenticationManager: AuthenticationManager,
    val userRepository: UserRepository,
    val encoder: PasswordEncoder,
    val jwtUtils: JwtUtils
) {

    @PostMapping("/login")
    fun authenticateUser(@RequestBody loginRequest: LoginRequest): ResponseEntity<*> {
        try{
            val authentication: Authentication = authenticationManager.authenticate(
                UsernamePasswordAuthenticationToken(loginRequest.email, loginRequest.password)
            )
            SecurityContextHolder.getContext().authentication = authentication
            val jwt = jwtUtils.generateJwtToken(authentication)
            val userDetails = authentication.principal as User
            val role: String = userDetails.role.toString()

            return ResponseEntity.ok(
                JwtResponse(
                    jwt,
                    userDetails.id,
                    userDetails.email,
                    userDetails.name,
                    userDetails.surname,
                    role
                )
            )
        }catch (ex: Exception){
            return ResponseEntity.badRequest().body(ex.message)
        }
    }

    @PostMapping("/signin")
    fun registerUser(@Valid @RequestBody signUpRequest: RegisterRequest): ResponseEntity<*> {

        if (signUpRequest.email?.let { userRepository.existsByEmail(it) } == true) {
            return ResponseEntity
                .badRequest()
                .body(MessageResponse("Error: Email is already taken!"))
        }
        if(signUpRequest.password != signUpRequest.confirmPassword){
            return ResponseEntity
                .badRequest()
                .body(MessageResponse("Passwords do not match"))
        }


        val user = User(
            0L,
            name = signUpRequest.name,
            surname = signUpRequest.surname,
            email = signUpRequest.email,
            password = encoder.passwordEncoderBean().encode(signUpRequest.password),
            role =  ERole.valueOf(signUpRequest.role),
        )

        userRepository.save<User>(user)
        return ResponseEntity.ok(MessageResponse("User registered successfully!"))
    }
}