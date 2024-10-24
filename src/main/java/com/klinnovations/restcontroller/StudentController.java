package com.klinnovations.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.klinnovations.entity.Student;
import com.klinnovations.service.StudentService;

@RestController
@RequestMapping("/api/students")
//@CrossOrigin(origins = "http://localhost:4200")
public class StudentController {

	@Autowired
	private StudentService studentService;

	@PostMapping("/cteatestudent")
	public ResponseEntity<Student> createStudent(@RequestBody Student student) {
		Student createStudent = studentService.createStudent(student);
		return new ResponseEntity<>(createStudent, HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Student> getStudentById(@PathVariable("id") Integer id) {
		Student student = studentService.getStudentById(id);
		return new ResponseEntity<Student>(student, HttpStatus.OK);

	}

	@GetMapping("/allstudent")
	public ResponseEntity<List<Student>> getAllStudents() {
		List<Student> student = studentService.getAllStudents();
		return new ResponseEntity<List<Student>>(student, HttpStatus.OK);
	}

	@PutMapping("/{id}")

	public ResponseEntity<Student> updateStudent(@PathVariable("id") Integer id, @RequestBody Student student) {
		Student updateStudent = studentService.updateStudent(id, student);

		return new ResponseEntity<Student>(updateStudent, HttpStatus.OK);

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Student> deleteStudent(@PathVariable Integer id) {

		studentService.deleteStudent(id);
		return new ResponseEntity<Student>(HttpStatus.NO_CONTENT);
	}

}
