package com.klinnovations.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.klinnovations.entity.Student;


@Service
public interface StudentService {
	
	Student createStudent(Student student);
	
	Student getStudentById(Integer id);
	
	List<Student> getAllStudents();
	
	Student updateStudent(Integer id, Student student);
	
	void deleteStudent(Integer id);

}
