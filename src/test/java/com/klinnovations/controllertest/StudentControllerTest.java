package com.klinnovations.controllertest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import com.klinnovations.entity.Student;
import com.klinnovations.restcontroller.StudentController;
import com.klinnovations.service.StudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

 class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private StudentService studentService;

    @InjectMocks
    private StudentController studentController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(studentController).build();
    }

    @Test
     void testCreateStudent() throws Exception {
        Student student = new Student(1, "John", "Doe", "john.doe@example.com", 1234567890L, 1000.0);
        when(studentService.createStudent(any(Student.class))).thenReturn(student);

        mockMvc.perform(post("/api/students/cteatestudent")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"fname\":\"John\", \"lname\":\"Doe\", \"email\":\"john.doe@example.com\", \"contactNum\":1234567890, \"amount\":1000.0}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.fname").value("John"))
                .andExpect(jsonPath("$.lname").value("Doe"))
                .andExpect(jsonPath("$.email").value("john.doe@example.com"))
                .andExpect(jsonPath("$.contactNum").value(1234567890))
                .andExpect(jsonPath("$.amount").value(1000.0))
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
     void testGetStudentById() throws Exception {
        Student student = new Student(1, "John", "Doe", "john.doe@example.com", 1234567890L, 1000.0);

        when(studentService.getStudentById(1)).thenReturn(student);

        mockMvc.perform(get("/api/students/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.fname").value("John"))
                .andExpect(jsonPath("$.lname").value("Doe"))
                .andExpect(jsonPath("$.email").value("john.doe@example.com"))
                .andExpect(jsonPath("$.contactNum").value(1234567890))
                .andExpect(jsonPath("$.amount").value(1000.0))
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void testGetAllStudents() throws Exception {
        Student student1 = new Student(1, "John", "Doe", "john.doe@example.com", 1234567890L, 1000.0);
        Student student2 = new Student(2, "Jane", "Doe", "jane.doe@example.com", 9876543210L, 1500.0);

        List<Student> students = Arrays.asList(student1, student2);
        when(studentService.getAllStudents()).thenReturn(students);

        mockMvc.perform(get("/api/students/allstudent"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].fname").value("John"))
                .andExpect(jsonPath("$[0].lname").value("Doe"))
                .andExpect(jsonPath("$[1].fname").value("Jane"))
                .andExpect(jsonPath("$[1].lname").value("Doe"))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[1].id").value(2));
    }

    @Test
     void testUpdateStudent() throws Exception {
        Student updatedStudent = new Student(1, "John Updated", "Doe", "john.updated@example.com", 1234567890L, 1100.0);

        when(studentService.updateStudent(eq(1), any(Student.class))).thenReturn(updatedStudent);

        mockMvc.perform(put("/api/students/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"fname\":\"John Updated\", \"lname\":\"Doe\", \"email\":\"john.updated@example.com\", \"contactNum\":1234567890, \"amount\":1100.0}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.fname").value("John Updated"))
                .andExpect(jsonPath("$.lname").value("Doe"))
                .andExpect(jsonPath("$.email").value("john.updated@example.com"))
                .andExpect(jsonPath("$.contactNum").value(1234567890))
                .andExpect(jsonPath("$.amount").value(1100.0))
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
     void testDeleteStudent() throws Exception {
        doNothing().when(studentService).deleteStudent(1);

        mockMvc.perform(delete("/api/students/1"))
                .andExpect(status().isNoContent());

        verify(studentService, times(1)).deleteStudent(1);
    }
}
