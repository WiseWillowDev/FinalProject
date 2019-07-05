package com.tts.ToDo.Repos;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.tts.ToDo.Models.ToDoItem;


@RunWith(SpringRunner.class)
@DataJpaTest
public class ToDoItemRepoTest {

	@Autowired
	ToDoItemRepo repo;
	
	@Before
	public void setUp() throws Exception {
		
	}

	@Test
	public void findAll() throws Exception {
		List<ToDoItem> items = repo.findAll();
		
		assertEquals(items.size(), 0);
	}
	
}
