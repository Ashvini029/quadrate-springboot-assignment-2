package com.example.RecepieApplication.controller;

import javax.persistence.MappedSuperclass;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.RecepieApplication.service.Service;
import com.example.RecepieApplication.models.*; 

import java.util.*;

@Controller
@RequestMapping("/")
public class MainController {
	
	@Autowired
	Service service;
	
	@RequestMapping
	public String getAllRecord( Model model) {
		
		List<RecEntity> list=service.getAllRecord();
		model.addAttribute("recipes",list);
		
		
		
		
		return "recipe-list";
		
	}
	
	@RequestMapping(path = {"/add"})
	public String showRecipeById(Model model)
							throws ReflectiveOperationException{
		
			model.addAttribute("recipe",new RecEntity());
		
		return "add-recipe";
	}
	
	@RequestMapping(path = "/createRecipe", method = RequestMethod.POST)
	public String createOrUpdateEmployee(RecEntity recipe) 
	{
		System.out.println("createOrUpdateRecipe ");
		
		service.createOrUpdateEmployee(recipe);
		
		return "redirect:/";
	}
	
	
	@RequestMapping(path = {"/edit/{id}"})
	public String showRecipeById(Model model, @PathVariable("id") Optional<Long> id)
							throws ReflectiveOperationException{
		
		System.out.println("showRecipeById" +id);
		
		
			RecEntity entity = service.getRecipeById(id.get());
			model.addAttribute("recipe",entity);
		
		
		return "view-recipe";
	}
	
	
	
	
	

}
