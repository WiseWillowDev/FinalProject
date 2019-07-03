package com.tts.ToDo.Services;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tts.ToDo.Models.ToDoItem;
import com.tts.ToDo.Repos.ToDoItemRepo;

@Service
public class ToDoItemService {
	
//	add if statements in the html if there is no list
//	complete, or not today, tomarrow, this week and future
//	create the sorting function that handels it all
	@Autowired
	private ToDoItemRepo toDoItemRepo;
	
	public List<ToDoItem> findAll(){
		return toDoItemRepo.findAll();
	}
	
	public void save(ToDoItem toDoItem) {
		toDoItemRepo.save(toDoItem);
	}
	
	public String today() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date today = Calendar.getInstance().getTime();
		
		String updated = sdf.format(today);
		return updated;
	}
	
	public Date dateToday() {
		Date today = Calendar.getInstance().getTime();
		
		return today;
	}
	
	public List<ToDoItem> getTodayItems(){
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		List<ToDoItem> items = toDoItemRepo.findAll();
		List<ToDoItem> todaysItems = new ArrayList<>();
		for(int i = 0; i < items.size(); i++) {
			String updated = sdf.format(items.get(i).getDeadline());
			if(updated.equalsIgnoreCase(today()) && !items.get(i).isStatus()) {
			
				todaysItems.add(items.get(i));
			} 

			

		}
		
		return todaysItems;
	}
	
	public List<ToDoItem> getTodayFinishedItems(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		List<ToDoItem> items = toDoItemRepo.findAll();
		List<ToDoItem> todaysItems = new ArrayList<>();
		for(int i = 0; i < items.size(); i++) {
			String updated = sdf.format(items.get(i).getDeadline());
			if(updated.equalsIgnoreCase(today()) && items.get(i).isStatus()) {
			
				todaysItems.add(items.get(i));
			} 

			

		}
		
		return todaysItems;
	}
	
	public List<ToDoItem> getFutureItems(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		List<ToDoItem> items = toDoItemRepo.findAll();
		List<ToDoItem> todaysItems = new ArrayList<>();
		for(int i = 0; i < items.size(); i++) {
			
			if(items.get(i).getDeadline().compareTo(dateToday()) > 0 && !items.get(i).isStatus()) {
				todaysItems.add(items.get(i));
			}

		}
		
		return todaysItems;
	}
	
	public List<ToDoItem> getFinishedItems() {
		List<ToDoItem> items = toDoItemRepo.findAll();

		List<ToDoItem> finishedItems = new ArrayList<>();
		for(int i = 0; i < items.size(); i++) {
			
			if(items.get(i).isStatus()) {
				finishedItems.add(items.get(i));
			}

		}
		return finishedItems;
	}

	public ToDoItem findById(long id) {
		return toDoItemRepo.findById(id).orElse(null);
	}
	
	public void changeStatus(ToDoItem item) {
		if(item.isStatus()) {
			item.setStatus(false);
		} else if(!item.isStatus()) {
			item.setStatus(true);
		}
	}
	
	public List<ToDoItem> getDueTomarrow() {
		List<ToDoItem> items = toDoItemRepo.findAll();
		List<ToDoItem> dueTomar = new ArrayList<>();
 		int noOfDays = 1; //i.e two weeks
		Calendar calendar = Calendar.getInstance();
		Date date = calendar.getTime();
		calendar.setTime(date);      
		System.out.println("---------------------");
		System.out.println(date);
		calendar.add(Calendar.DAY_OF_YEAR, noOfDays);
		
		System.out.println(calendar);
		System.out.println(calendar.getTime());
		for(int i = 0; i < items.size(); i ++) {
			if(items.get(i).getDeadline().compareTo(calendar.getTime()) == 0) {
				System.out.println("Did it");
				dueTomar.add(items.get(i));
			}
		}
		return dueTomar;
		
//		 Date date = Calendar.getInstance().getTime();
//		 Calendar c = Calendar.getInstance();
//		 c.setTime(date);
//		 c.add(Calendar.WEEK_OF_MONTH, 2);
//		 date = c.getTime();
	}
	
	

}
