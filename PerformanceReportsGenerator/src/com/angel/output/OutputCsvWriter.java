package com.angel.output;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.angel.entities.Definition;
import com.angel.entities.Employee;

public class OutputCsvWriter {

	public static void writeAsCsvFile(List<Employee> employeeList) throws IOException {
		
		File newCsv = new File("result.csv");
		
        if(newCsv.createNewFile()){
            System.out.println("result.csv File Created in Project root directory");    
        }
        else 
        	System.out.println("File result.csv already exists in the project root directory");
        
        try (FileWriter fileWriter = new FileWriter(newCsv)) {
            fileWriter.append("Name, Score\r\n");
            
            for(int i = 0; i < employeeList.size(); i++) {
            	fileWriter.append(employeeList.get(i).getName() + " , ");
            	fileWriter.append(Double.toString(employeeList.get(i).getScore()) + "\r\n");
            }
            
            fileWriter.flush();  
            fileWriter.close();
        }
    }
}
