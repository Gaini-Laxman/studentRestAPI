package com.klinnovations;



import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import static org.assertj.core.api.Assertions.assertThat;
import com.klinnovations.service.StudentService;

@SpringBootTest
class StudentdetailsApplicationTests {

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    void contextLoads() {
        // Assert that the application context loads and is not null
        assertThat(applicationContext).isNotNull();
        
        // Optionally, assert that a specific bean is present
        assertThat(applicationContext.getBean(StudentService.class)).isNotNull();
    }
}

