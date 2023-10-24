package employeeManagementFinal.employeeManagement.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {
    String extractUserName(String token);

    String generateToken(UserDetails userDetails);

    void deleteToken(String token);

    boolean isTokenValid(String token, UserDetails userDetails);
}
