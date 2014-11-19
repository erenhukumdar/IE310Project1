
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.RealVector;


public class SimplexOperation {
	public RealMatrix simplexTable;	
	public boolean isMaximization;
	public int m,n;
	public double  [] artificialIgnore=null;
	
	public SimplexOperation(int n, int m, double[][] A,double[][] RHS,double[][] ct,boolean isMaximization)
	{
		double[][] a= new double[m+1][n+1];
		a=ArrayUtils.addAll(A,ct);
		a= ArrayUtils.addAll(a, RHS);
		simplexTable=new Array2DRowRealMatrix(a);
		this.isMaximization=isMaximization;
		this.m=m;
		this.n=n;
	}
	public SimplexOperation(int n,int m,RealMatrix initMatrix,boolean isMaximization)
	{
		this.m=m;
		this.n=n;
		this.isMaximization=isMaximization;
		// there can be some bugs dueto the Z included into matrix. If there is a bug due to the z we should change write op.
		//simplexTable=initMatrix;
		simplexTable= initMatrix.getSubMatrix(0,m,1,n+1);
		System.out.println("HEREEEEEE");
		
		
	}
	public SimplexOperation() {
		// TODO Auto-generated constructor stub
	}
	public void setSimplexOperation(int n,int m,RealMatrix phase,boolean isMaximization)
	{
		//this function is temporary for Gorkem's work. One can use for setting phase1 matrix.
		this.m=m;
		this.n=n;
		this.isMaximization=isMaximization;
		// there can be some bugs dueto the Z included into matrix. If there is a bug due to the z we should change write op.
		//simplexTable=initMatrix;
		simplexTable=phase;
		
	}
	
	
	
	public void rowOperation(int destinationRow,int operandRow,int coeff)
	{
		
		RealVector myFirstRow= simplexTable.getRowVector(destinationRow);
		RealVector mySecondRow= simplexTable.getRowVector(operandRow);
		mySecondRow.mapMultiplyToSelf(coeff); 
		myFirstRow.add(mySecondRow);
		simplexTable.setRowVector(destinationRow, myFirstRow);
		
	}
	
	public int findPivotColumn()
	{
		int selectedVariable;
		
		RealVector zeroRow= simplexTable.getRowVector(0);
		zeroRow = zeroRow.getSubVector(0, n);
		
		if(artificialIgnore!=null){
			System.out.println("Ignoring Artificial Variables");
			for(int i=0;i<artificialIgnore.length;i++){
				if(artificialIgnore[i]!=0){
					zeroRow.setEntry(i,(isMaximization ? 1:-1));
				}
			}
		}
		
		
		
		if (isMaximization)
		{
			double minValue= zeroRow.getMinValue();
			if (minValue>=0)
			{
				return -1;
			}
			else
			{
				selectedVariable=zeroRow.getMinIndex();				
			}
		}
		else
		{
			double maxValue= zeroRow.getMaxValue();
			if (maxValue<0)
			{
				return -1;
			}
			else
			{
				selectedVariable=zeroRow.getMaxIndex();				
			}
		}
		return selectedVariable;
			
		
	}
	public int findPivotRow(int pivotIndex)
	{
		RealVector pivotValues = simplexTable.getColumnVector(pivotIndex);
		RealVector myRHS = simplexTable.getColumnVector(simplexTable.getColumnDimension()-1);
		myRHS=myRHS.ebeDivide(pivotValues);
		//we need a sorted and unsorted arrays
		double[] arrayOfRHS=myRHS.toArray();
		double minValue=Double.MAX_VALUE;
		int location=1;
		for (int i=1;i<arrayOfRHS.length;i++)
		{
			if ((arrayOfRHS[i]<minValue) && (arrayOfRHS[i]>=0))
			{
				minValue=arrayOfRHS[i];
				location=i;
			}
			
			
		}
		
		return location;

	}
	
	public RealMatrix iterateSimplexPlan(int pivotRow,int pivotColumn)
	{
		double pivotValue=simplexTable.getEntry(pivotRow, pivotColumn);
		RealVector pivotRowValues = simplexTable.getRowVector(pivotRow);
		//divides all pivot row value with pivot value
		pivotRowValues=pivotRowValues.mapDivide(pivotValue);
		simplexTable.setRowVector(pivotRow, pivotRowValues);
		
		
		for (int i=0;i<=m;i++)
		{
			double myCoeff;
			if (i!=pivotRow)
			{
			
				//we are getting pivot value and pivot row vector
				 pivotRowValues = simplexTable.getRowVector(pivotRow);
			
				//now we start to gaussian row operations
				//gets row vector and divide with coefficient which makes pivot column zero
				RealVector myRowVector=simplexTable.getRowVector(i);
				myCoeff=simplexTable.getEntry(i, pivotColumn);
				pivotRowValues=pivotRowValues.mapMultiply(myCoeff);
				myRowVector=myRowVector.subtract(pivotRowValues);
				//assign again the main matrix
				simplexTable.setRowVector(i, myRowVector);
			}
			
		}
		return simplexTable;
	}
	public void write(String output)
	{
		

		Writer writer=new Writer();
		writer.open(output);
		writer.printMatrix(simplexTable);
		writer.close();
		
	}
	

	public void writeConsole()
	{
		

		Writer writer=new Writer();
		writer.printConsoleMatrix(simplexTable);
		
	}

	
	

}
