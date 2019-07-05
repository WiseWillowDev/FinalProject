package com.tts.ToDo.Controllers;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import com.tts.ToDo.Models.ToDoItem;
import com.tts.ToDo.Services.ToDoItemService;

public class ToDoItemControllerTest {

	@Mock
	ToDoItemService toDoItemService;
	
	@Mock
	Model model;
	
	ToDoItemController controller;
	
	@Before 
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		
		controller = new ToDoItemController(toDoItemService);
	}
	
	@Test
	public void testMockMVC() throws Exception {
		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
		
		mockMvc.perform(get("/")).andExpect(status().isOk()).andExpect(view().name("WebPages/Index"));
		
	}
	
	@Test
	public void getIndexPage() throws Exception {
		
		List<ToDoItem> items = new ArrayList<>();
		items.add(new ToDoItem());
		ToDoItem item = new ToDoItem();
		item.setId(1L);
		items.add(item);
		
		
		when(toDoItemService.getTodayItems()).thenReturn(items);
		
		ArgumentCaptor<List<ToDoItem>> argumentCaptor = ArgumentCaptor.forClass(List.class);
		
		String viewName = controller.IndexPageWithTheToDoList(model, items.get(1));
		
		assertEquals("WebPages/Index",viewName);
		verify(toDoItemService, times(1)).getTodayItems();
		verify(model,times(1)).addAttribute(eq("Items"), argumentCaptor.capture());
		List<ToDoItem> setInController = argumentCaptor.getValue();
		assertEquals(2, setInController.size());
	}
	
	@Test
	public void gettingNewItemPage() throws Exception {
		ToDoItem toDoItem = new ToDoItem();
		String viewName = controller.creatingANewItem(model, toDoItem);
		
		assertEquals("WebPages/NewItem", viewName);
		
	}
	
	@Test
	public void getCompletedPage() throws Exception {
		
		String viewName = controller.completedMapping(model);
		
		assertEquals("WebPages/completed" , viewName);
		
	}
	
	@Test 
	public void savingAnItem() throws Exception {
		ToDoItem toDoItem = new ToDoItem();
		String viewName = controller.routingBackToTheWelcome(model, toDoItem);
		assertEquals("WebPages/Index" , viewName);
	}
	
	@Test
	public void deleteMapping() throws Exception {
		ToDoItem toDoItem = new ToDoItem();
		toDoItem.setId(1L);
		
		String viewName = controller.DeleteByIDIndexPage(1L, model, toDoItem);
		
		assertEquals("WebPages/Index", viewName);
	}
	
	
	@Test
	public void FinishedEditingReturnsIndex() throws Exception {
		ToDoItem toDoItem = new ToDoItem();
		String viewName = controller.saveingEditAndReturningIndexPage(1L, model, toDoItem);
		assertEquals("WebPages/Index", viewName);
	}
	
	@Test
	public void clickedTheEditButtonReturnEditPage() throws Exception {
		ToDoItem toDoItem = new ToDoItem();
		String viewName = controller.EditingAnItem(1L, model, toDoItem);
		assertEquals("WebPages/edit", viewName);
	}
	
	@Test
	public void CheckingATaskOffAListThatWasIncomplete() throws Exception {
		
		ToDoItem toDoItem = new ToDoItem();
		toDoItem.setId(1L);
		when(toDoItemService.findById(1L)).thenReturn(toDoItem);
		String viewName = controller.completeingATask(1L, model, toDoItem);
		
		
		
		assertEquals("WebPages/completed",viewName);
	}
	
	@Test
	public void CheckingATaskThatWasComplete() throws Exception {
		ToDoItem toDoItem = new ToDoItem();
		toDoItem.setId(1L);
		toDoItem.setStatus(true);
		
		when(toDoItemService.findById(1L)).thenReturn(toDoItem);
		String viewName = controller.completeingATask(1L, model, toDoItem);
		
		assertEquals("WebPages/Index",viewName);
	}

}
