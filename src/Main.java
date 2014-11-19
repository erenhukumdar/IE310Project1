import java.io.FileNotFoundException;
import java.util.Arrays;

import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.RealMatrix;

/**
 * IE 310 Assignment II
 * Group Name: 'Noname'
 * GroupID: 4
 * 
 * @author Eren Hukumdar
 * @author Gorkem Karadeniz
 * @author Sertac Onal

 * See. README.txt if any questions occur
 *
 * 
 * This Program takes an 'input.txt' and returns:
 * an 'out1ini.txt' (which has the initial phase 1 tableau)
 * an 'out1opt.txt' (which has the optimal phase 1 tableau)
 * an 'out2ini.txt' (which has the initial phase 2 tableau)
 * an 'out2opt.txt' (which has the optimal phase 2 tableau)
 * 
 * If there is no input.txt Program will throw FileNotFoundException
 * 
 */


public class Main {


	public static void main(String [] args)
	{
		//This is for Reading the file
		FileReader myReader=new FileReader();
		try {
			myReader.readFile("input.txt");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("'input.txt NOT FOUND'");
			return;
		}

		
		int n=myReader.getN();
		int m=myReader.getM();
		double b [] = myReader.getB();
		double[][] ct_1=myReader.getCt_1();
		double[][] ct_2=myReader.getCt_2();
		double[][] A= myReader.getA();
		boolean isMaximization = myReader.getIsMaximization();

	//	SimplexOperation 
		
		
//		System.out.println("Variables");
//		System.out.println("n=" + String.valueOf(n));
//		System.out.println("m=" + String.valueOf(m));
//		System.out.println("Ct1=" + Arrays.deepToString(ct_1 ));
//		System.out.println("Ct2=" + Arrays.deepToString(ct_2));
//		System.out.println("A=" +  Arrays.deepToString(A));
//		System.out.println("b=" +  Arrays.toString(b));		
//		System.out.println("--------------------------------------");
		
		
		//RealMatrix matrixA = new Array2DRowRealMatrix(myReader.getA());
		
		double arr []= new double[m+1];
		arr[0]=0.0;
		for(int i=1;i<m+1;i++){
			arr[i]=b[i-1];
		}
		RealMatrix arr_t=(new Array2DRowRealMatrix(arr)).transpose();
		RealMatrix rhs_real = new Array2DRowRealMatrix(arr);
		
		
		
		RealMatrix ct1 = new Array2DRowRealMatrix(myReader.getCt_1());
		RealMatrix ct2 = new Array2DRowRealMatrix(myReader.getCt_2());

		double rhsArr [][]= rhs_real.getData();
		System.out.println("RHS=" +  Arrays.deepToString(rhsArr));
		
		
		// Question 2 Phase1 Initial
		RealMatrix Phase1in;
		PhaseIni phase1in=new PhaseIni();
		Phase1in=phase1in.Create(n,m,A,b,ct_1);
		
		
		// After initilization
		SimplexOperation phase1=new SimplexOperation(n, m, Phase1in, isMaximization);
		RealMatrix phase2in=Phase1in;
		while(phase1.findPivotColumn()!=-1){
			System.out.println("\n");
			phase1.writeConsole();
			phase2in=phase1.iterateSimplexPlan(phase1.findPivotRow(phase1.findPivotColumn()), phase1.findPivotColumn());
		}
		System.out.println("\nPhase 1 Optimal\n");
		phase1.writeConsole();
		phase1.write("out1opt.txt");
		
		//Phase2 init operation 
				RealMatrix newInit;
				PhaseIni phase2Init=new PhaseIni();
				phase2Init.basicVars(phase1.getA(), phase1.getPhaseIniVersion(), m, n);
				
		
		
		//Replace the the row 0 with new one for phase 2 !!! 
//		for(int i=0;i<ct_2[0].length;i++){
//			phase2in.setEntry(0, i, -1*ct_2[0][i]);
//		}
		
		
		SimplexOperation phase2=new SimplexOperation();
		//I have create new solid operation to create new rew zero
		phase2.setSimplexOperation(n, m, phase2in, isMaximization);
		phase2.createNewRowZero(phase2Init.getBasicVariables());		
		phase2.artificialIgnore=ct_1[0];
		phase2.write("out2ini.txt");
		System.out.println("\nPhase2 Initial\n");
		phase2.writeConsole();
		while(phase2.findPivotColumn()!=-1){
			System.out.println("\n");
			phase2.writeConsole();
			phase2.iterateSimplexPlan(phase1.findPivotRow(phase1.findPivotColumn()), phase1.findPivotColumn());
		}
		System.out.println("\nPhase 2 Optimal\n");
		phase1.writeConsole();
		phase1.write("out2opt.txt");
		


	}
	
//	//Prints the Matrix to given Printwriter's file using tabs and new lines
//	//It is possible to format and only show two digits after .
//	public static void printMatrix(PrintWriter writer, RealMatrix matrix){
//		double [][] arr2d=matrix.getData();
//			int numColumns=arr2d[0].length;
//			int numRows=arr2d.length;
//			
//		   for(int i = 0; i < numRows; i++)
//		   {
//		      for(int j = 0; j < numColumns; j++)
//		      {
//		    
//		         //writer.printf("%f\t",arr2d[i][j]);
//		         //System.out.printf("%f\t",arr2d[i][j]);
//		    	  writer.print(arr2d[i][j]+" ");
//		         //System.out.print(arr2d[i][j]+"\t");
//
//		      }
//		      //System.out.println();
//		      writer.println();
//		   }
//		   //System.out.println();
//		   writer.println();
//		
//		
//	}
	
}
