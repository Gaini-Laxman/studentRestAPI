package com.klinnovations.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.klinnovations.entity.Student;
import com.klinnovations.exception.StudentNotFoundException;
import com.klinnovations.repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentRepository studentRepository;

	@Override
	public Student createStudent(Student student) {

		return studentRepository.save(student);
	}

	@Override
	public Student getStudentById(Integer id) {
	    Optional<Student> student = studentRepository.findById(id);
	    if (student.isPresent()) {
	        return student.get();
	    } else {
	        throw new StudentNotFoundException("Student not found with id: " + id);
	    }
	}

	@Override
	public List<Student> getAllStudents() {
		
		return studentRepository.findAll();
	}

	@Override
	public Student updateStudent(Integer id, Student student) {
		var existingStudent = getStudentById(id);
		existingStudent.setFname(student.getFname());
		existingStudent.setLname(student.getLname());
		existingStudent.setEmail(student.getEmail());
		existingStudent.setContactNum(student.getContactNum());
		existingStudent.setAmount(student.getAmount());

		return studentRepository.save(existingStudent);
	}

	@Override
	public void deleteStudent(Integer id) {
		var existingStudent = getStudentById(id);
		studentRepository.delete(existingStudent);

	}

}
