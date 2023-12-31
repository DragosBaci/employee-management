package employeeManagementFinal.employeeManagement.controller;

import employeeManagementFinal.employeeManagement.DAO.request.LoginRequest;
import employeeManagementFinal.employeeManagement.DAO.request.RegisterRequest;
import employeeManagementFinal.employeeManagement.DAO.response.JwtAuthenticationResponse;
import employeeManagementFinal.employeeManagement.service.AuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<JwtAuthenticationResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authenticationService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<JwtAuthenticationResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authenticationService.login(request));
    }

    @PostMapping("/logout")
    public void logout(HttpServletRequest request) {
        authenticationService.logout(request);
    }
}





