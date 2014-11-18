import java.util.Arrays;

import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.RealMatrix;



public class PhaseIni {
	Writer writer=new Writer();
	int[] basicVariables;

	public RealMatrix Create(int n, int m, double[][] A,double[] b,double[][] ct)
	{
	basicVariables=new int [m];
		//Create Simplex Tableu 
	double [][] Dummy=new double[m+1][n+2];
	// Add Z column
	Dummy[0][0]=1;
	for (int i=1;i<=m;i++)
	{
		Dummy[i][0]=0;
	}
	//Add (-1).C Transpose for Phase 1
	for (int i=1;i<=n;i++)
	{
		if (ct[0][i-1]!=0){
			Dummy[0][i]=(-1)*(ct[0][i-1]);
			
		}else
		{
			Dummy[0][i]=(ct[0][i-1]);
		}
		
	}
	
	//Add A Matrix
	for (int i=1;i<=m;i++)
	{
		for (int j=1;j<=n;j++)
		{
			Dummy[i][j]=A[i-1][j-1];
		}
		
	}
	//Add RHS
	Dummy[0][n+1]=0; // RHS of Row0
	for(int i=1;i<=m;i++)
	{
		Dummy[i][n+1]=b[i-1];
	}
		
	// Convert Dummy into RealMatrix 			
	RealMatrix Phase1 = new Array2DRowRealMatrix(Dummy);
	basicVars(A,Phase1,m,n);
	for (int i=0;i<m;i++)
	{
		Phase1=rowOperations(basicVariables[i],Phase1,m,n);
	}
    // write Matrix Phase1
	writer.open("out1ini.txt");
	writer.printMatrix(Phase1);
	writer.close();
	
	return Phase1;
	}
	
	public void basicVars(double[][] A,RealMatrix Phase0,int m,int n)
	{
		//Find basic variables
		
		//Create Identity Matrix 
		double [][] identityMatrix=new double[m][m];
	    
		for(int i = 0; i < m; i++)
		{
	
		      for(int j = 0; j < m; j++)
		      {
		    	  identityMatrix[i][j] = (i == j) ? 1 : 0;
		    	  
		      }
		}
		
		RealMatrix realIdentity= new Array2DRowRealMatrix(identityMatrix);
		RealMatrix realA=new Array2DRowRealMatrix(A);
		
		//Subtarct Columns of Identity Matrix on A to get
				//which variables are basic
	
		
		for(int i=0;i<m;i++)
		{
		for(int j=0;j<n;j++)
			{
			if (realA.getColumnVector(j).equals(realIdentity.getColumnVector(i)))		
			{
				basicVariables[i]=j;	
			}
			}	
				
		}
		
	
	
	}	
		
public RealMatrix rowOperations(int basicVar,RealMatrix Phase0,int m,int n)
	{
	    
		// in first table seek basic variable column
		RealMatrix colOfBVar = Phase0.getColumnMatrix(basicVar+1);	
		// after finding variable column remove Row0 form that column
		RealMatrix colOfBVar_woRow0=colOfBVar.getSubMatrix(1, m, 0, 0);
		// also take how many time we need to subtract
		RealMatrix colOfBVar_Row0Header=colOfBVar.getSubMatrix(0,1,0,0);
		// to find 1 with binary search on that column we need array
		double[] arrayOfcolOfBVar_woRow0=colOfBVar_woRow0.getColumn(0);
		int locationOf1=Arrays.binarySearch(arrayOfcolOfBVar_woRow0,1);
		// After finding location of 1 on Basic Variable Column 
		// set a subtracter which will be subtracted from Phase0 to obtain
		// Phase1
        
		double [][] howManytimes=colOfBVar_Row0Header.getData();
		if (locationOf1>0){
		for (double i=0;i<howManytimes[0][0];i++)
		{
		RealMatrix subtracter=Phase0.getRowMatrix(locationOf1+1);
		//Subtract Founded from Phase0
		RealMatrix Row0_Complete=Phase0.getRowMatrix(0);
		Row0_Complete=Row0_Complete.subtract(subtracter);
		//double[] dummy=subtracter.getRow(0);
		Phase0.setRowMatrix(0, Row0_Complete);
		}
		}		
	    return Phase0;
		
	}
		
}
	
	
	


