package employeeManagementFinal.employeeManagement;

import employeeManagementFinal.employeeManagement.converters.EmployeeIdListConverter;
import employeeManagementFinal.employeeManagement.converters.SubDepartmentIdListConverter;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class EmployeeManagementApplication {

	@Bean
	public ModelMapper modelMapper() {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.addConverter(new EmployeeIdListConverter());
		modelMapper.addConverter(new SubDepartmentIdListConverter());
		return modelMapper;
	}
	public static void main(String[] args) {
		SpringApplication.run(EmployeeManagementApplication.class, args);
	}

}
