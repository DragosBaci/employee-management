package employeeManagementFinal.employeeManagement.service.serviceImplementation;

import employeeManagementFinal.employeeManagement.DAO.request.LoginRequest;
import employeeManagementFinal.employeeManagement.DAO.request.RegisterRequest;
import employeeManagementFinal.employeeManagement.DAO.response.JwtAuthenticationResponse;
import employeeManagementFinal.employeeManagement.entity.Role;
import employeeManagementFinal.employeeManagement.entity.User;
import employeeManagementFinal.employeeManagement.repository.UserRepository;
import employeeManagementFinal.employeeManagement.service.AuthenticationService;
import employeeManagementFinal.employeeManagement.service.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    @Override
    public JwtAuthenticationResponse register(RegisterRequest request) {
        var user = User.builder().firstName(request.getFirstName()).lastName(request.getLastName())
                .email(request.getEmail()).password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER).build();
        userRepository.save(user);
        var jwt = jwtService.generateToken(user);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }

    @Override
    public JwtAuthenticationResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password."));
        var jwt = jwtService.generateToken(user);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }

    @Override
    public void logout(HttpServletRequest request) {
        String token = extractTokenFromHeader(request);

        if (token != null) {

            jwtService.deleteToken(token);
        }

        // Optionally, you can also clear the user's authentication
        SecurityContextHolder.clearContext();
    }

    private String extractTokenFromHeader(HttpServletRequest request) {
        // Extract the token from the "Authorization" header
        String authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            return authorizationHeader.substring(7);
        }

        return null;
    }
}


