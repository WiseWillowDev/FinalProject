package com.tts.ToDo.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.tts.ToDo.Models.ToDoItem;
import com.tts.ToDo.Services.ToDoItemService;

@Controller
public class ToDoItemController {
	
	@Autowired
	private ToDoItemService toDoItemService;
	
	
	@GetMapping("/")
	public String IndexPageWithTheToDoList(Model model) {
		model.addAttribute("time", toDoItemService.today());
		model.addAttribute("Items", toDoItemService.getTodayItems());
		model.addAttribute("tomarrow", toDoItemService.getDueTomarrow());

		model.addAttribute("future" , toDoItemService.getFutureItems());
		

		return "WebPages/Index";
	}
	
	@GetMapping("/NewItem")
	public String creatingANewItem(Model model , ToDoItem toDoItem) {
		
		return "WebPages/NewItem";
	}
	
	@PostMapping("/NewItemCreated")
	public String routingBackToTheWelcome(Model model, ToDoItem toDoItem) {
		toDoItemService.save(toDoItem);
		model.addAttribute("time", toDoItemService.today());

		
		model.addAttribute("Items", toDoItemService.getTodayItems());
		
		
		model.addAttribute("future" , toDoItemService.getFutureItems());

		return "WebPages/Index";
	}
	
	@PutMapping("/completed/{id}")
	public String completeingATask(@PathVariable long id, Model model, ToDoItem toDoItem) {
	ToDoItem item = toDoItemService.findById(id);
	toDoItemService.changeStatus(item);
	toDoItemService.save(item);
	if(!item.isStatus()) {
		model.addAttribute("time", toDoItemService.today());
		model.addAttribute("Items", toDoItemService.getFinishedItems());

		

		return "WebPages/completed";
	} else {
	
	
	model.addAttribute("time", toDoItemService.today());

	
	model.addAttribute("Items", toDoItemService.getTodayItems());
	
	
	model.addAttribute("future" , toDoItemService.getFutureItems());

	return "WebPages/Index";
	}
	}

	@GetMapping("/completed")
	public String completedMapping(Model model) {
		model.addAttribute("time", toDoItemService.today());
		model.addAttribute("Items", toDoItemService.getFinishedItems());

		

		return "WebPages/completed";
	}
}
