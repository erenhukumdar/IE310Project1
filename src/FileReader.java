import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * IE 310 Assignment II
 * Group Name: 'Noname'
 * GroupID: 4
 * 
 * @author Eren Hukumdar
 * @author Gorkem Karadeniz
 * @author Sertac Onal
 * 
 * Simple file reader class that reads lines and stores matrix values into appropriate variables
 */
public class FileReader {

	private boolean isMaximization;
	private int m;
	private int n;
	private double[][] ct_1;
	private double[][] ct_2;
	private double[][] A;
	private double[] b;



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



	public double[][] getCt_1() {
		return ct_1;
	}



	public void setCt(double[][] ct) {
		this.ct_1 = ct;
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

	public void readFile(String filePath) throws FileNotFoundException
	{
		Scanner fileIn = new Scanner(new File(filePath));

		//Pass the 1st explanation line (type)
		fileIn.nextLine();
		//Read variable n //codes for n
		String typeLine = fileIn.nextLine();
		isMaximization=(typeLine.equals("maximization") ? true : false );
		//Pass the empty line
		fileIn.nextLine();

		//Pass the 2nd explanation line (n)
		fileIn.nextLine();
		//Read variable n //codes for n
		String myLine = fileIn.nextLine();
		n=Integer.parseInt(myLine);
		//Pass the empty line
		fileIn.nextLine();

		//Pass the 3rd explanation line (m)
		fileIn.nextLine();
		//Read variable m //for m
		myLine = fileIn.nextLine();
		m=Integer.parseInt(myLine);
		//Pass the empty line
		fileIn.nextLine();

		//Pass the 4th explanation line (C_T_1)
		fileIn.nextLine();
		//for c transpose
		myLine = fileIn.nextLine();
		String[] rowValues=myLine.split(" ");
		ct_1=new double[1][n];
		for(int i=0;i<rowValues.length;i++)
		{
			ct_1[0][i]=Double.parseDouble(rowValues[i]);					
		}
		//Pass the empty line
		fileIn.nextLine();

		//Pass the 5th explanation line (C_T_2)
		fileIn.nextLine();
		//for c transpose
		myLine = fileIn.nextLine();
		String[] rowValues2=myLine.split(" ");
		ct_2=new double[1][n];
		for(int i=0;i<rowValues2.length;i++)
		{
			ct_2[0][i]=Double.parseDouble(rowValues2[i]);					
		}
		//Pass the empty line
		fileIn.nextLine();

		//Pass the 6th explanation line (A)
		fileIn.nextLine();
		//for A
		int counter=0;
		A=new double[m][n];
		myLine = fileIn.nextLine();
		while (myLine.length()!=0)
		{
			rowValues2=myLine.split(" ");

			for(int i=0;i<rowValues2.length;i++)
			{
				A[counter][i]=Double.parseDouble(rowValues2[i]);					
			}
			counter++;
			myLine = fileIn.nextLine();
		}
		//empty line has already been passed in the previous while loop

		//Pass the 6th explanation line (b)
		fileIn.nextLine();

		//for b
		counter=0;
		b=new double[m];
		myLine = fileIn.nextLine();
		while (myLine.length()!=0)
		{
			b[counter]=Double.parseDouble(myLine);					
			counter++;
			//End of file check
			if(!fileIn.hasNext()){
				break;
			}
			myLine = fileIn.nextLine();
		}
		//empty line has already been passed in the previous while


	}



	public double[][] getCt_2() {
		return ct_2;
	}



	public void setCt_2(double[][] ct_2) {
		this.ct_2 = ct_2;
	}



	public boolean getIsMaximization() {
		return isMaximization;
	}



	public void setIsMaximization(boolean isMaximization) {
		this.isMaximization = isMaximization;
	}




}
