import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class FileReader {
	
	private int m;
    private int n;
	private double[][] ct;
	private double[][] A;
	private double[] b;
	private int[] xb; 
	


	public int getM() {
		return m;
	}



	public void setM(int m) {
		this.m = m;
	}



	public int getN() {
		return n;
	}



	public void setN(int n) {
		this.n = n;
	}



	public double[][] getCt() {
		return ct;
	}



	public void setCt(double[][] ct) {
		this.ct = ct;
	}



	public double[][] getA() {
		return A;
	}



	public void setA(double[][] a) {
		A = a;
	}



	public double[] getB() {
		return b;
	}



	public void setB(double[] b) {
		this.b = b;
	}



	public int[] getXb() {
		return xb;
	}



	public void setXb(int[] xb) {
		this.xb = xb;
	}



	public void readFile(String filePath) throws FileNotFoundException
	{
		Scanner fileIn = new Scanner(new File(filePath));
		String myLine = fileIn.nextLine();
		while (fileIn.hasNextLine() ==true)
		{
			String marker=myLine;
			if (marker.length()==49)
			{
				//codes for n
				myLine = fileIn.nextLine();
				n=Integer.parseInt(myLine);
			}
			else if (marker.length()==76)
			{
				//for m
				myLine = fileIn.nextLine();
				m=Integer.parseInt(myLine);
				
			}
			else if (marker.length()==53)
			{
				//for c transpose
				myLine = fileIn.nextLine();
				String[] rowValues=myLine.split(" ");
				ct=new double[1][n];
				for(int i=0;i<rowValues.length;i++)
				{
				     ct[0][i]=Double.parseDouble(rowValues[i]);					
				}
				
			}
			else if (marker.length()==38) 
			{
				//for A
				
				int counter=0;
				A=new double[m][n];
				myLine = fileIn.nextLine();
				while (myLine.length()!=0)
				{
					
					
					String[] rowValues=myLine.split(" ");
					
					for(int i=0;i<rowValues.length;i++)
					{
						A[counter][i]=Double.parseDouble(rowValues[i]);					
					}
					counter++;
					myLine = fileIn.nextLine();
				}
				
				
				
			}
			else if (marker.length()==40)
			{
				
				//for b
				int counter=0;
				b=new double[m];
				myLine = fileIn.nextLine();
				while (myLine.length()!=0)
				{
					
					
					
					b[counter]=Double.parseDouble(myLine);					
					counter++;
					myLine = fileIn.nextLine();
				}
				
			}
			else if (marker.length()==75)
			{
				//for Xb
				
				myLine = fileIn.nextLine();
				String[] rowValues=myLine.split(" ");
				xb=new int[m];
				for(int i=0;i<rowValues.length;i++)
				{
				     xb[i]=Integer.parseInt(rowValues[i]);					
				}
				
			}
			else
			{
				myLine = fileIn.nextLine();
				
			}
				
		}
		
			
			
	}
	


}
