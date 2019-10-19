package com.angel.main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.angel.entities.Definition;
import com.angel.entities.Employee;
import com.angel.input.InputJsonReader;
import com.angel.output.OutputCsvWriter;

public class Main {

	public static void main(String[] args) {
		
		try {
		
		Definition definition = InputJsonReader.InputJsonDef(args[1]);
		List<Employee> employees = InputJsonReader.InputJsonData(args[0], definition);
		List<Employee> topEmployees = getTopEmployees(employees, definition);
		
		OutputCsvWriter.writeAsCsvFile(topEmployees);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static List<Employee> getTopEmployees(List<Employee> allEmployees, Definition definition){
		
		List<Employee> topEmployees = new ArrayList<>();
		allEmployees.sort(Comparator.comparingDouble(Employee::getScore).reversed());
		double topPerformersThreshold = definition.getTopPerformersThreshold();
        double employeesCount = allEmployees.size(); 
        int numberOfTopEmployees = (int)((topPerformersThreshold / 100.0) * employeesCount);
        
        if(numberOfTopEmployees == 0 && employeesCount > 0) {
        	numberOfTopEmployees = 1;
        }
        
        for(int i = 0; i < numberOfTopEmployees; i++) {
        	Employee currentEmployee = allEmployees.get(i);
        	if(currentEmployee.getSalesPeriod() <= definition.getPeriodLimit()) {
        	topEmployees.add(currentEmployee);	
        	}
        }
		
		return topEmployees;
	}

}
