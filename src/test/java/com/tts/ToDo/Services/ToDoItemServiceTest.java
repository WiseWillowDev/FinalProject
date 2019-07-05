package com.tts.ToDo.Services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.tts.ToDo.Models.ToDoItem;
import com.tts.ToDo.Repos.ToDoItemRepo;

@RunWith(SpringRunner.class)
public class ToDoItemServiceTest {

	 @TestConfiguration
	    static class EmployeeServiceImplTestContextConfiguration {
	  
	        @Bean
	        public ToDoItemService employeeService() {
	            return new ToDoItemService();
	        }
	    }
	 
	    @Autowired
	    private ToDoItemService toDoItemService;
	 
	    @MockBean
	    private ToDoItemRepo toDoItemRepo;
	    
	    @Before
	    public void setUp() {
	        ToDoItem one = new ToDoItem();
	        one.setCreator("Richard");
	        one.setId(1);
//	        toDoItemService.save(one);
	        Mockito.when(toDoItemService.findById(1))
	          .thenReturn(one);
	    }
	
	    @Test
	    public void whenValidName_thenEmployeeShouldBeFound() {
	        String name = "Richard";
	        ToDoItem found = toDoItemService.findById(1);
	      
	         assertThat(found.getCreator())
	          .isEqualTo(name);
	     }

}
