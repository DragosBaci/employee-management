package employeeManagementFinal.employeeManagement.service;

import employeeManagementFinal.employeeManagement.DAO.request.LoginRequest;
import employeeManagementFinal.employeeManagement.DAO.request.RegisterRequest;
import employeeManagementFinal.employeeManagement.DAO.response.JwtAuthenticationResponse;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public interface AuthenticationService {
    JwtAuthenticationResponse register(RegisterRequest request);

    JwtAuthenticationResponse login(LoginRequest request);

    void logout(HttpServletRequest request);
}
