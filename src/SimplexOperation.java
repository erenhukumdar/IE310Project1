
import java.util.Arrays;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.RealVector;


public class SimplexOperation {
	public RealMatrix simplexTable;	
	public boolean isMaximization;
	public int m,n;
	
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
	
	
	
	public void rowOperation(int destinationRow,int operandRow,int coeff)
	{
		
		RealVector myFirstRow= simplexTable.getRowVector(destinationRow);
		RealVector mySecondRow= simplexTable.getRowVector(operandRow);
		mySecondRow.mapMultiplyToSelf(coeff); 
		myFirstRow.add(mySecondRow);
		simplexTable.setRowVector(destinationRow, myFirstRow);
		
	}
	
	public int findPivot()
	{
		int selectedVariable;
		
		RealVector zeroRow= simplexTable.getRowVector(0);
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
	public double findSmallestRatio(int pivotIndex)
	{
		RealVector pivotValues = simplexTable.getColumnVector(pivotIndex);
		RealVector myRHS = simplexTable.getColumnVector(simplexTable.getColumnDimension()-1);
		myRHS.ebeDivide(pivotValues);
		//we need a sorted and unsorted arrays
		double[] arrayOfRHS=myRHS.toArray();
		
		double[] arrayOfRHSSorted=myRHS.toArray();
		Arrays.sort(arrayOfRHSSorted);
		int index=0;
		double minValue=arrayOfRHSSorted[index];
		while (minValue<0)
		{
			index++;
			minValue=arrayOfRHSSorted[index];			
		}
		return Arrays.binarySearch(arrayOfRHS, minValue);

	}
	
	public void iterateSimplexPlan(int pivotRow,int pivotColumn)
	{
		double pivotValue=simplexTable.getEntry(pivotRow, pivotColumn);

		for (int i=0;i<=m;i++)
		{
			double myCoeff;
			if (i!=pivotRow)
			{
				//we are getting pivot value and pivot row vector
				RealVector pivotRowValues = simplexTable.getRowVector(pivotRow);
				//divides all pivot row value with pivot value
				pivotRowValues.mapDivide(pivotValue);
				//now we start to gaussian row operations
				//gets row vector and divide with coefficient which makes pivot column zero
				RealVector myRowVector=simplexTable.getRowVector(i);
				myCoeff=simplexTable.getEntry(i, pivotColumn);
				myRowVector.subtract(pivotRowValues.mapMultiply(myCoeff));
				//assign again the main matrix
				simplexTable.setRowVector(i, myRowVector);
			}
			
		}
		
	}
	

}
