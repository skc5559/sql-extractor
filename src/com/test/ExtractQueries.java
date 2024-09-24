package com.test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExtractQueries {
	
	public static void main(String[] args) {
		
		String logFilePath = "resource/detail.txt";
		List<String> sqlQueries = extractSQLQueries(logFilePath);
		System.out.println("Total SQL Queries" + sqlQueries.size());
		final String FILEPATHPREFIX = "input/sqlquery_";
		String filepath=null;
		for (int i=0 ; i<sqlQueries.size();i++) 
        {
			filepath = FILEPATHPREFIX + i +".txt";
			BufferedWriter writer =null;
			try 
			{
				writer = new BufferedWriter(new FileWriter(filepath));
	            writer.write(sqlQueries.get(i));
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
			finally {
				if(writer!=null) {
					try {
						writer.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			}
        }
		
	}
	
	 private static List<String> extractSQLQueries(String filePath) {
	        List<String> queries = new ArrayList<>();
	       // String[] lines = logContent.split("\r\n");
	        String line;
	        StringBuilder query = new StringBuilder();
	        boolean insideQuery = false;
	        String tempLine = null;
	        
	        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
	            
	            while ((line = br.readLine()) != null) {
	                if (line.toUpperCase().startsWith("SELECT ")) {
	                    insideQuery = true;
	                    query.setLength(0);  // Clear the buffer
	                }
	                
	                if (line.trim().endsWith("----------") && insideQuery) {
	                    insideQuery = false;
	                    queries.add(query.toString().trim());
	                }
	                if (insideQuery) 
	                {
	                	System.out.println(line);
	                	query.append(line);	
	                	query.append(" ");
	                }
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        
	        System.out.println("Query::::::" + queries);
	        return queries;
	    }

}
