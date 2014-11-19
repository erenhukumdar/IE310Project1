

import java.io.PrintWriter;
import org.apache.commons.math3.linear.RealMatrix;

/**
 * IE 310 Assignment I
 * Group Name: 'Noname'
 * GroupID: 4
 * See. README.txt if any questions occur
 * 
 * @author Eren Hukumdar
 * @author Sertaç Onal
 * @author Gorkem Karadeniz
 * 
 * This Program takes an 'input.txt' and returns an 'output.txt' (which has the answers)
 * 
 * If there is no input.txt Program will throw FileNotFoundException
 * 
 */


public class Writer {
	//This is for writing to the file
	PrintWriter writer;
public void open(String file)
{
	//Open File 'output.txt'
	try {
		writer = new PrintWriter(file, "UTF-8");
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return;
	}
}
	
	public void printMatrix(RealMatrix matrix){
	
		

		//Prints the Matrix to given Printwriter's file using tabs and new lines
		//It is possible to format and only show two digits after .
		
		double [][] arr2d=matrix.getData();
			int numColumns=arr2d[0].length;
			int numRows=arr2d.length;
				
		   for(int i = 0; i < numRows; i++)
		   {
			   if(i==0)
			   {
				   writer.print("Row\t\tZ\t\t");   
				   for (int k=0;k<numColumns-1;k++)
				   {
				   writer.print("x"+(k+1)+"\t\t");	   
				   }
				   writer.print("RHS");
				   writer.println();
				   writer.print("--------------------");  //added for Row Z
				  for (int k=0;k<numColumns-1;k++)
				  {
					  writer.print("----------------");  //added for per x
				  }
				  	writer.print("------------------"); //added for RHS
				  	writer.println();
			   }   
		      for(int j = 0; j < numColumns; j++)
		      {
		    
		         //writer.printf("%f\t",arr2d[i][j]);
		         //System.out.printf("%f\t",arr2d[i][j]);
		    	  
		    	  if (j==0)
		    	  {
		    		 if(i==0)
		    		 {
		    			 writer.print(i+"\t\t1.00\t\t");
		    		 }else
		    		 {
		    			 writer.print(i+"\t\t0.00\t\t");
		    		 }
		    	  }
		    	  //writer.print(arr2d[i][j]+"  ");
		    	  writer.printf("%.2f\t\t",arr2d[i][j]);
		         //System.out.print(arr2d[i][j]+"\t");
		      }
		     if (i==0)
		     {
		    	 writer.println();
				  writer.print("--------------------");  //added for Row Z
				  for (int k=0;k<numColumns-1;k++)
				  {
				  writer.print("----------------");  //added for per x
				  }
				  writer.print("------------------"); //added for RHS
		     }
		      //System.out.println();
		      writer.println();
		   }
		   //System.out.println();	   
			  writer.print("--------------------");  //added for Row Z
			  for (int k=0;k<numColumns-1;k++)
			  {
			  writer.print("----------------");  //added for per x
			  }
			  writer.print("------------------"); //added for RHS
			  writer.println();
	
	}
	
	
	public void printConsoleMatrix(RealMatrix matrix){
	
		

		//Prints the Matrix to given Printwriter's file using tabs and new lines
		//It is possible to format and only show two digits after .
		
		double [][] arr2d=matrix.getData();
			int numColumns=arr2d[0].length;
			int numRows=arr2d.length;
				
		   for(int i = 0; i < numRows; i++)
		   {
			   if(i==0)
			   {
				   System.out.print("Row\t\tZ\t\t");   
				   for (int k=0;k<numColumns-1;k++)
				   {
				   System.out.print("x"+(k+1)+"\t\t");	   
				   }
				   System.out.print("RHS");
				   System.out.println();
				   System.out.print("--------------------");  //added for Row Z
				  for (int k=0;k<numColumns-1;k++)
				  {
					  System.out.print("----------------");  //added for per x
				  }
				  	System.out.print("------------------"); //added for RHS
				  	System.out.println();
			   }   
		      for(int j = 0; j < numColumns; j++)
		      {
		    
		         //System.out.printf("%f\t",arr2d[i][j]);
		         //System.out.printf("%f\t",arr2d[i][j]);
		    	  
		    	  if (j==0)
		    	  {
		    		 if(i==0)
		    		 {
		    			 System.out.print(i+"\t\t1.00\t\t");
		    		 }else
		    		 {
		    			 System.out.print(i+"\t\t0.00\t\t");
		    		 }
		    	  }
		    	  //System.out.print(arr2d[i][j]+"  ");
		    	  System.out.printf("%.2f\t\t",arr2d[i][j]);
		         //System.out.print(arr2d[i][j]+"\t");
		      }
		     if (i==0)
		     {
		    	 System.out.println();
				  System.out.print("--------------------");  //added for Row Z
				  for (int k=0;k<numColumns-1;k++)
				  {
				  System.out.print("----------------");  //added for per x
				  }
				  System.out.print("------------------"); //added for RHS
		     }
		      //System.out.println();
		      System.out.println();
		   }
		   //System.out.println();	   
			  System.out.print("--------------------");  //added for Row Z
			  for (int k=0;k<numColumns-1;k++)
			  {
			  System.out.print("----------------");  //added for per x
			  }
			  System.out.print("------------------"); //added for RHS
			  System.out.println();
	
	}
	
	
	public void println(String text)
	{
		writer.println(text);
	}
	
	
	public void close()
	{
		writer.close();
	}
	
	
}

