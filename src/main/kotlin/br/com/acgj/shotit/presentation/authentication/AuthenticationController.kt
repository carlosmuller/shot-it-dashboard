package br.com.acgj.shotit.presentation.authentication

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


data class SuccessAuthentication(val token: String)

@RequestMapping("/api/auth")
@RestController
class AuthenticationController(private val service: AuthenticationService) {

    @PostMapping("/sign-in")
    fun handleSignIn(@RequestBody request: SignInRequest): ResponseEntity<SuccessAuthentication> {
        val token = service.authenticate(request)
        val response = SuccessAuthentication(token)

        return ResponseEntity.ok().body(response)
    }

    @PostMapping("/sign-up")
    fun handleSignUp(@RequestBody request: SignUpRequest): ResponseEntity<Any>{
        service.register(request)
        return ResponseEntity.ok().build()
    }
}