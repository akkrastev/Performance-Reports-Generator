package com.angel.input;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.angel.entities.Definition;
import com.angel.entities.Employee;

public class InputJsonReader {

	public static JSONParser jsonParser = new JSONParser();
	
	public static List<Employee> InputJsonData(String filePath, Definition definition) {
		
		List<Employee> employees = null;
		try {
			FileReader readerEmpoyees = new FileReader(filePath);
			JSONArray jsonEmployees = (JSONArray) jsonParser.parse(readerEmpoyees);
				
			employees = new ArrayList<Employee>();
			    
			for(int i = 0; i < jsonEmployees.size(); i++) {
			    	
			    JSONObject jsonObject = (JSONObject) jsonEmployees.get(i);
			    	
			    String name = (String) jsonObject.get("name");
			    int totalSales = Integer.parseInt(jsonObject.get("totalSales").toString());
			    int salesPeriod = Integer.parseInt(jsonObject.get("salesPeriod").toString());
			    double experienceMultiplier = Double.parseDouble(jsonObject.get("experienceMultiplier").toString());
			        
			    Employee currentEmployee = new Employee(name, totalSales, salesPeriod, experienceMultiplier);     
			    currentEmployee.calculateScore(definition);
			    employees.add(currentEmployee);
			                  
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		   
		return employees;	
	}
	
	public static Definition InputJsonDef(String filePath) {
		
		Definition definition = null;
		try {
			FileReader readerDefinition = new FileReader(filePath);
			JSONObject jsonDefinition = (JSONObject) jsonParser.parse(readerDefinition);
				
			int topPerformersThreshold = Integer.parseInt(jsonDefinition.get("topPerformersThreshold").toString());
			boolean useExperienceMultiplier = Boolean.parseBoolean(jsonDefinition.get("useExperienceMultiplier").toString());
			int periodLimit = Integer.parseInt(jsonDefinition.get("periodLimit").toString());
			definition = new Definition(topPerformersThreshold, useExperienceMultiplier, periodLimit);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return definition;
	}
}
