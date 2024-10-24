package com.klinnovations.test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import com.klinnovations.entity.Student;
import com.klinnovations.exception.StudentNotFoundException;
import com.klinnovations.repository.StudentRepository;
import com.klinnovations.service.StudentServiceImpl;
import java.util.Optional;


 class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentServiceImpl studentService; // Use the concrete implementation

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
     void testGetStudentById_Success() {
        // Arrange
        Integer studentId = 1;
        Student student = new Student(); // Assume Student has a no-arg constructor
        student.setId(studentId);
        
        when(studentRepository.findById(studentId)).thenReturn(Optional.of(student));

        // Act
        Student result = studentService.getStudentById(studentId);

        // Assert
        assertNotNull(result, "The returned student should not be null");
        assertEquals(studentId, result.getId(), "The student ID should match the expected ID");
    }

    @Test
     void testGetStudentById_NotFound() {
        // Arrange
        Integer studentId = 2;
        
        when(studentRepository.findById(studentId)).thenReturn(Optional.empty());

        // Act & Assert
        StudentNotFoundException exception = assertThrows(StudentNotFoundException.class, () -> {
            studentService.getStudentById(studentId);
        }, "Expected StudentNotFoundException to be thrown");

        assertEquals("Student not found with id: " + studentId, exception.getMessage(),
                "The exception message should match the expected message");
    }
}