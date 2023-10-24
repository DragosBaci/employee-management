package employeeManagementFinal.employeeManagement.service;

import employeeManagementFinal.employeeManagement.DAO.request.SignInRequest;
import employeeManagementFinal.employeeManagement.DAO.request.SignUpRequest;
import employeeManagementFinal.employeeManagement.DAO.response.JwtAuthenticationResponse;
import employeeManagementFinal.employeeManagement.entity.Department;
import employeeManagementFinal.employeeManagement.entity.Employee;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public interface AuthenticationService {
    JwtAuthenticationResponse signup(SignUpRequest request);

    JwtAuthenticationResponse signin(SignInRequest request);

    void signout(HttpServletRequest request);
}
