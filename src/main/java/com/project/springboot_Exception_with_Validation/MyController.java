package com.project.springboot_Exception_with_Validation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;

@Validated//for method level validation we need to use this
@RestController
public class MyController 
{
//PLEASE USE STARTER VALIDATOR IN POM.XML FILE THEN RUN
	
@Autowired
EmployeeRepository erepo;
	
@RequestMapping("/test")
public String test()
{
	return "Exception with Validation";
}

@RequestMapping("/save")
public String save(@Valid @RequestBody Employee employee)
{
	//without @Valid it gives bean validation error ConstraintViolationException
	//with @Valid MethodArgumentNotValidException in postman
	//https://www.baeldung.com/spring-boot-bean-validation
	erepo.save(employee);
	return "Data is saved";
}
@RequestMapping("/all")
public List<Employee> allData()
{
	return erepo.findAll();
}
@RequestMapping("/delete/{id}")
public String delete(@PathVariable int id)
{
	erepo.deleteById(id);
	return "Data is deleted";
}
@RequestMapping("/{city}")
public List<Employee> byCity(@PathVariable @Size(min=3,message="city must be more than 3 letter")String city)
{
	return erepo.findByCity(city);
}

}
