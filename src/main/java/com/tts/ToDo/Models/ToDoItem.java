package com.tts.ToDo.Models;



import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class ToDoItem {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String title;
	private String description;
	private String creator;
	private boolean status = false;
	
//	@DateTimeFormat(pattern="yyyy-mm-dd")
	private Date deadline;

	public ToDoItem() {}
	
	
	public ToDoItem(String title, String creator, boolean status, Date deadline, String description) {
		this.title = title;
		this.description = description;
		this.creator = creator;
		this.status = status;
		this.deadline = deadline;
	}
	
	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}
	

	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getCreator() {
		return creator;
	}


	public void setCreator(String creator) {
		this.creator = creator;
	}


	public boolean isStatus() {
		return status;
	}


	public void setStatus(boolean status) {
		this.status = status;
	}


	public Date getDeadline() {
		return deadline;
	}


	public void setDeadline(Date today) {
		this.deadline = today;
	}
	
	
	
}
