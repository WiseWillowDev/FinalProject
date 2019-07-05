package com.tts.ToDo.Services;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.tts.ToDo.Models.ToDoItem;
import com.tts.ToDo.Repos.ToDoItemRepo;

public class ToDoItemServiceTest {

	ToDoItemService service;
	
	@Mock
	ToDoItemRepo repo;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		
		service = new ToDoItemService(repo);
	}
	
	@Test
	public void findAll() throws Exception {
		ToDoItem toDoItem = new ToDoItem();
		List<ToDoItem> AllTheItems = new ArrayList<>();
		
		AllTheItems.add(toDoItem);
		
		when(service.findAll()).thenReturn(AllTheItems);
		
		List<ToDoItem> items = service.findAll();
		
		assertEquals(items.size(), 1);
		verify(repo, times(1)).findAll();
	}
}
