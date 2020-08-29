package com.example.RecepieApplication.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;
import com.example.RecepieApplication.models.*; 

@org.springframework.stereotype.Service
public class Service {
	
	@Autowired
	Repo repository;
	
	public List<RecEntity> getAllRecord(){
		
		List<RecEntity> result=(List<RecEntity>) repository.findAll();
		
		if(result.size() > 0) {
		
			return result;
		}else {
			return new ArrayList<RecEntity>();
		}
	}
	
public RecEntity getRecipeById(Long id) throws ReflectiveOperationException{
		
		System.out.println("getRecipeById");
		
		Optional<RecEntity> recipe = repository.findById(id);
		
		
			return recipe.get();
		
	}
	
	public RecEntity createOrUpdateEmployee(RecEntity entity) 
	{
		System.out.println("createOrUpdateEmployee");
		// Create new entry 
		if(entity.getId()  == null) 
		{
			entity = repository.save(entity);
			
			return entity;
		} 
		else 
		{
			// update existing entry 
			Optional<RecEntity> recipe = repository.findById(entity.getId());
			
			if(recipe.isPresent()) 
			{
				RecEntity newEntity = recipe.get();
				newEntity.setName(entity.getName());
				newEntity.setCategory(entity.getCategory());
				newEntity.setIngredient(entity.getIngredient());
				newEntity.setDirection(entity.getDirection());

				newEntity = repository.save(newEntity);
				
				return newEntity;
			} else {
				entity = repository.save(entity);
				
				return entity;
			}
		}
	}
	
	

}
