import java.io.FileNotFoundException;
import java.util.Arrays;

import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.DecompositionSolver;
import org.apache.commons.math3.linear.LUDecomposition;
import org.apache.commons.math3.linear.RealMatrix;


public class Main {
	
	
	public static void main(String [] args)
	{
		FileReader myReader=new FileReader();
		
	
		
		try {
			myReader.readFile("D:/EclipseWorkSpace/IE310Project1/input.txt");
			RealMatrix matrixA = new Array2DRowRealMatrix(myReader.getA());
			int[] M=new int[myReader.getM()];
			for (int i=0;i<myReader.getM();i++)
			{	
				M[i]=i;
			}
			
			RealMatrix matrixB= matrixA.getSubMatrix(M,myReader.getXb());
			LUDecomposition LUDecompositionImpL=new LUDecomposition(matrixB);
			RealMatrix invMatrixB= LUDecompositionImpL.getSolver().getInverse();
			System.out.println("Variables");
			System.out.println("m=" + String.valueOf(myReader.getM()));
			System.out.println("n=" + String.valueOf(myReader.getN()));
			System.out.println("A=" +  Arrays.deepToString(myReader.getA()));
			System.out.println("b=" +  Arrays.toString(myReader.getB()));
			System.out.println("Ct=" + Arrays.deepToString( myReader.getCt()));
			System.out.println("Xb=" + Arrays.toString(myReader.getXb()));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
