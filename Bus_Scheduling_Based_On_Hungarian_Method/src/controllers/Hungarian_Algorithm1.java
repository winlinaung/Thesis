package controllers;
import static java.lang.Math.*;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;
import javax.swing.JFrame;
import javax.swing.text.TableView.TableRow;

import application.SceneManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableViewBuilder;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class Hungarian_Algorithm1{
	static double[][] TableOne;
	static double[][] TableTwo;
	static double[][] array1;
	static double[][] array2;
	static double[][] array11;
	static double[][] array22;
	static double[][] SuitableTable;
	static String[][] SuitableBasedCity;
	static String BasedCityOne;
	static String BasedCityTwo;
	static String DeperatureDate2;
	static String[][] SuitableDeperatureDate;
	static int step1=1;
	static double[][] cost;
	static int[][] mask;
	static int[] rowCover;
	static int[] colCover ;
	static int[] zero_RC;
	static int[][] path ;
	ShowDataController h1 = new ShowDataController();
	@FXML
	static SceneManager sceneManager;
	static ObservableList<DataBeam1> data;
	ObservableList<DataBeam2> data1;
	
	public Hungarian_Algorithm1(ObservableList<DataBeam1> data) {
		this.data = data;
//		System.out.println("This is real date"+data.get(0).getTextFieldDeperatureDate());
	}
	
	public  void generateSuitableLayoverTime//Generates User input two 2-D array. 
	 (double[][] array)  
	 {
		array1 = new double[array.length][array[0].length];
	  for (int i=0; i<array.length; i++) 
	  { 
		  BasedCityOne = data.get(i).getTextFieldResidenceCity1();
	   for (int j=0; j<array[i].length; j++) 
	     {
		   String data1[] = data.get(i).getTextFieldBusDeperatureTime1().split(":");
		   String data2[] = data.get(i).getTextFieldBusArrivalTime1().split(":");
		   if(j == 0)
		   array1[i][j] =  Double.valueOf(data1[0] + "." +data1[1]);
		   if(j==1)
		   array1[i][j] = Double.valueOf(data2[0] + "." +data2[1]);
		  } 
	  }    
	  array2 = new double[array.length][array[0].length];
	  for (int i=0; i<array.length; i++) 
	  { 
		  BasedCityTwo =data.get(i).getTextFieldResidenceCity2();
		  String data1[] = data.get(i).getTextFieldBusDeperatureTime2().split(":"); //h1.TableColBusDeperatureTime2.getCellData(i);
		  String data2[] = data.get(i).getTextFieldBusArrivalTime2().split(":");
	   for (int j=0; j<array[i].length; j++) 
	     {
		   if(j == 0)
			   array2[i][j] = Double.valueOf(data1[0] + "." +data1[1]);
		   if(j == 1)
			   array2[i][j] =Double.valueOf(data2[0] + "." +data2[1]);
		  } 
	  } 
	 } 
	 public  double[][] calculateTableOne(double leastLayoverTime) throws ParseException{ //Generate Idle time table between Departure Time and Arrival Time
		 TableOne = new double[array1.length][array1.length];
		 array11 = new double[array1.length][array1[0].length]; // accept data from array1(User Input deperature and arrival time
		 array22 = new double[array2.length][array2[0].length];// accept data from array2(User Input deperature and arrival time
		SuitableDeperatureDate = new String[array1.length][array1.length];
		 for(int i = 0; i < array1.length ; i++ ) {
			 for(int j=0; j< array1[0].length; j++) {
				 int Int_array1 =(int)array1[i][j];//Convert Hour to Number Format
				 double Double_array1 = array1[i][j] - Int_array1;
				 Double_array1 = Double_array1/.6;
				 array11[i][j] = Int_array1 + Double_array1;
				 
				 int Int_array2 = (int)array2[i][j];
				 double Double_array2 = array2[i][j] - Int_array2;
				 Double_array2 = Double_array2/.6;
				 array22[i][j] = Int_array2 + Double_array2;
				 
			 }
			 }
		 for(int i = 0; i < array1.length ; i++ ) {
			 for(int j=0; j< array1.length; j++) {
				 if(array22[j][0] < array11[i][1] || array22[j][0] == array11[i][1])
				 {
					 
					 TableOne[i][j] = array22[j][0]+24-array11[i][1];

				 }
				 else
				 {
					 TableOne[i][j] = array22[j][0] - array11[i][1];

				 }
				 if(TableOne[i][j] + 96<= leastLayoverTime) {
					 TableOne[i][j] += 120;

			 }
			 else if(TableOne[i][j] + 72 <= leastLayoverTime) {
				 TableOne[i][j] += 96;
			 }
			 else if(TableOne[i][j] + 48 <= leastLayoverTime) {
				 TableOne[i][j] += 72;
			 }
			 else if(TableOne[i][j] + 24 <= leastLayoverTime) {
				 TableOne[i][j] += 48;
			 }
			 else if(TableOne[i][j] <= leastLayoverTime){
				 TableOne[i][j] += 24;
			 }
			 
		 }
		 }
		return TableOne;
	 }
	 public  double[][] calculateTableTwo(double leastLayoverTime) throws ParseException{//Generate Idle time table between Departure Time and Arrival Time
		 TableTwo = new double[array2.length][array2.length];
		 array11 = new double[array1.length][array1[0].length];// accept data from array1(User Input deperature and arrival time
		 array22 = new double[array2.length][array2[0].length];// accept data from array2(User Input deperature and arrival time
		 SuitableDeperatureDate = new String[array1.length][array1.length];
		 for(int i = 0; i < array1.length ; i++ ) {
			 for(int j=0; j< array1[0].length; j++) {
				 int Int_array1 =(int)array1[i][j];//Convert Hour to Number Format
				 double Double_array1 = array1[i][j] - Int_array1;
				 Double_array1 = Double_array1/.6;
				 array11[i][j] = Int_array1 + Double_array1;
				 int Int_array2 =(int)array2[i][j];
				 double Double_array2 = array2[i][j] - Int_array2;
				 Double_array2 = Double_array2/.6;
				 array22[i][j] = Int_array2 + Double_array2;
			 }
			 }
		 for(int i = 0; i < array2.length ; i++ ) {
			 for(int j=0; j< array2.length; j++) {
				 		 
				 if(array11[i][0] < array22[j][1] || array11[i][0] == array22[j][1] ) {
				 TableTwo[i][j] = array11[i][0]+24 - array22[j][1];

				 }
				 else {
					 TableTwo[i][j] = array11[i][0] - array22[j][1];

				 }
				 if(TableTwo[i][j] + 96<= leastLayoverTime) {
					 TableTwo[i][j] += 120;

			 }
			 else if(TableTwo[i][j] + 72 <= leastLayoverTime) {
				 TableTwo[i][j] += 96;
			 }
			 else if(TableOne[i][j] + 48 <= leastLayoverTime) {
				 TableTwo[i][j] += 72;
			 }
			 else if(TableTwo[i][j] + 24 <= leastLayoverTime) {
				 TableTwo[i][j] += 48;
			 }
			 else if(TableTwo[i][j] <= leastLayoverTime){
				 TableTwo[i][j] += 24;
			 }
			}
			 }
		 
		 return TableTwo;
	 }
	 public  double[][] SelectSuitableIdleTime(double[][] TableOne,double[][] TableTwo){//Select Suitable Idle Time And Select whcih city the bus is based
		 SuitableTable = new double[TableOne.length][TableOne[0].length];
		 SuitableBasedCity = new String[TableOne.length][TableOne[0].length];
		 for(int i = 0;i<TableOne.length;i++) {
			 for(int j = 0;j<TableOne[0].length;j++) {
				 if(TableOne[i][j] < TableTwo[i][j]) {
					 SuitableTable[i][j] = TableOne[i][j];
					 SuitableBasedCity[i][j] = BasedCityOne;
				 }
				 else if(TableTwo[i][j] < TableOne[i][j]) {
					 SuitableTable[i][j] = TableTwo[i][j];
					 SuitableBasedCity[i][j] = BasedCityTwo;
				 }
				 else if(TableOne[i][j] == TableTwo[i][j]) {
					 SuitableTable[i][j] = TableOne[i][j];
					 SuitableBasedCity[i][j] = BasedCityOne;
				 }
				 int Int_SuitableTable = (int)SuitableTable[i][j];//Convert Number to Hour format
				 double Double_SuitableTable = SuitableTable[i][j] - Int_SuitableTable;
				 Double_SuitableTable = Double_SuitableTable * .6;
				 SuitableTable[i][j] = Int_SuitableTable + Double_SuitableTable;
			 }
		 }
		 return SuitableTable;
	 }

	//*******************************************//
	//METHODS THAT PERFORM ARRAY-PROCESSING TASKS//
	//*******************************************//
//
//	public static void generateRandomArray	//Generates random 2-D array.
//	(double[][] array, String randomMethod)	
//	{
//		Random generator = new Random();
//		for (int i=0; i<array.length; i++)
//		{
//			for (int j=0; j<array[i].length; j++)
//			{
//				if (randomMethod.equals("random"))
//					{array[i][j] = generator.nextDouble();}
//				if (randomMethod.equals("gaussian"))
//				{
//						array[i][j] = generator.nextGaussian()/4;		//range length to 1.
//						if (array[i][j] > 0.5) {array[i][j] = 0.5;}		//eliminate outliers.
//						if (array[i][j] < -0.5) {array[i][j] = -0.5;}	//eliminate outliers.
//						array[i][j] = array[i][j] + 0.5;				//make elements positive.
//				}
//			}
//		}
//	}
	public static double findLargest		//Finds the largest element in a 2D array.
	(double[][] array)
	{
		double largest = Double.NEGATIVE_INFINITY;
		for (int i=0; i<array.length; i++)
		{
			for (int j=0; j<array[i].length; j++)
			{
				if (array[i][j] > largest)
				{
					largest = array[i][j];
				}
			}
		}
			
		return largest;
	}
	public static double[][] transpose		//Transposes a double[][] array.
	(double[][] array)	
	{
		double[][] transposedArray = new double[array[0].length][array.length];
		for (int i=0; i<transposedArray.length; i++)
		{
			for (int j=0; j<transposedArray[i].length; j++)
			{transposedArray[i][j] = array[j][i];}
		}
		return transposedArray;
	}
	public static double[][] copyOf			//Copies all elements of an array to a new array.
	(double[][] original)	
	{
		double[][] copy = new double[original.length][original[0].length];
		for (int i=0; i<original.length; i++)
		{
			//Need to do it this way, otherwise it copies only memory location
			System.arraycopy(original[i], 0, copy[i], 0, original[i].length);
		}

		return copy;
	}
	public static double[][] copyToSquare	//Creates a copy of an array, made square by padding the right or bottom.
	(double[][] original, double padValue)
	{
		int rows = original.length;
		int cols = original[0].length;	//Assume we're given a rectangular array.
		double[][] result = null;

		if (rows == cols)	//The matrix is already square.
		{
			result = copyOf(original);
		}
		else if (rows > cols)	//Pad on some extra columns on the right.
		{	
			result = new double[rows][rows];
			for (int i=0; i<rows; i++)
			{
				for (int j=0; j<rows; j++)
				{
					if (j >= cols)	//Use the padValue to fill the right columns.
					{
						result[i][j] = padValue;
					}
					else
					{
						result[i][j] = original[i][j];
					}
				}
			}
		}
		else
		{	// rows < cols; Pad on some extra rows at the bottom.
			result = new double[cols][cols];
			for (int i=0; i<cols; i++)
			{
				for (int j=0; j<cols; j++)
				{
					if (i >= rows)	//Use the padValue to fill the bottom rows.
					{
						result[i][j] = padValue;
					}
					else
					{
						result[i][j] = original[i][j];
					}
				}
			}
		}

		return result;
	}

	//**********************************//
	//METHODS OF THE HUNGARIAN ALGORITHM//
	//**********************************//
	public static int Step1() {
		step1 = 1;
		return step1;
	}
	//Core of the algorithm; takes required inputs and returns the assignments
	public static int[][] hgAlgorithmAssignments(double[][] array, String sumType) throws ParseException
	{
		//This variable is used to pad a rectangular array (so it will be picked all last [cost] or first [profit])
		//and will not interfere with final assignments.  Also, it is used to flip the relationship between weights
		//when "max" defines it as a profit matrix instead of a cost matrix.  Double.MAX_VALUE is not ideal, since arithmetic
		//needs to be performed and overflow may occur.
		double maxWeightPlusOne = findLargest(array) + 1;

		cost = copyToSquare(array, maxWeightPlusOne);	//Create the cost matrix

		if (sumType.equalsIgnoreCase("max"))	//Then array is a profit array.  Must flip the values because the algorithm finds lowest.
		{
			for (int i=0; i<cost.length; i++)		//Generate profit by subtracting from some value larger than everything.
			{
				for (int j=0; j<cost[i].length; j++)
				{
					cost[i][j] = (maxWeightPlusOne - cost[i][j]);
				}
			}
		}

		mask = new int[cost.length][cost[0].length];	//The mask array.
		rowCover = new int[cost.length];					//The row covering vector.
		colCover = new int[cost[0].length];				//The column covering vector.
		zero_RC = new int[2];								//Position of last zero from Step 4.
		path = new int[cost.length * cost[0].length + 2][2];
									
		boolean done = false;
		while (done == false)	//main execution loop
		{
			switch (step1)
			{
				case 1:
					hg_step1();
					break;
				case 2:
					hg_step2();
					break;
				case 3:
					hg_step3();
					break;
				case 4:
					hg_step4();
					break;
				case 5:
					hg_step5();
					break;
				case 6:
					hg_step6();
					break;
				case 7:
					done=true;
					break;
			}
		}//end while
//		array1 = new double[array.length][2];
//		array2 = new double[array.length][2];
		int[][] assignments = new int[array.length][2];	//Create the returned array.
		int assignmentCount = 0;	//In a input matrix taller than it is wide, the first
									//assignments column will have to skip some numbers, so
									//the index will not always match the first column ([0])
		for (int i=0; i<mask.length; i++)
		{
			for (int j=0; j<mask[i].length; j++)
			{
				if (i < array.length && j < array[0].length && mask[i][j] == 1)
				{

					assignments[assignmentCount][0] = i;
					assignments[assignmentCount][1] = j;
					
					assignmentCount++;
					// Calculate Deperature Date of the system and at least the bus must return to their based city
					double suitableDrivingTime1;
					double suitableDrivingTime2;
					
					if(array1[assignments[i][0]][0] > array1[assignments[i][0]][1]) {
						 suitableDrivingTime1 = array1[assignments[i][0]][1]+24 - array1[assignments[i][0]][0];
					}
					else
					{
						 suitableDrivingTime1 = array1[assignments[i][0]][1] - array1[assignments[i][0]][0];
					}
					if(array2[assignments[i][1]][0] > array2[assignments[i][0]][1]) {
						 suitableDrivingTime2 = array2[assignments[i][1]][1]+24 - array2[assignments[i][1]][0];
					}
					else
					{
						 suitableDrivingTime2 = array2[assignments[i][1]][1] - array2[assignments[i][1]][0];
					}
					double suitableDeperatureTime1 = array1[assignments[i][0]][0] + suitableDrivingTime1 + SuitableTable[assignments[i][0]][assignments[i][1]] ;
					double suitableDeperatureTime2 = array2[assignments[i][1]][0] + suitableDrivingTime2 + SuitableTable[assignments[i][0]][assignments[i][1]] ;
					if(SuitableBasedCity[assignments[i][0]][assignments[i][1]].equals(BasedCityOne) &&  suitableDeperatureTime1 > 120.0|| SuitableBasedCity[assignments[i][0]][assignments[i][1]].equals(BasedCityTwo) && suitableDeperatureTime2 > 120.0 ) {
						 DeperatureDate2 = String.valueOf(data.get(i).getTextFieldDeperatureDate());
						 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
						Calendar c = Calendar.getInstance();
						c.setTime(sdf.parse(DeperatureDate2));
						c.add(Calendar.DATE, 5);  // number of days to add
						DeperatureDate2 = sdf.format(c.getTime());  // dt is now the new date
						SuitableDeperatureDate[i][j] = DeperatureDate2;
				}
					else if(SuitableBasedCity[assignments[i][0]][assignments[i][1]].equals(BasedCityOne) &&  suitableDeperatureTime1 > 96.0|| SuitableBasedCity[assignments[i][0]][assignments[i][1]].equals(BasedCityTwo) && suitableDeperatureTime2 > 96.0 ) {
						 DeperatureDate2 = String.valueOf(data.get(i).getTextFieldDeperatureDate());
						 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
						Calendar c = Calendar.getInstance();
						c.setTime(sdf.parse(DeperatureDate2));
						c.add(Calendar.DATE, 4);  // number of days to add
						DeperatureDate2 = sdf.format(c.getTime());  // dt is now the new date
						SuitableDeperatureDate[i][j] = DeperatureDate2;
				}
					else if(SuitableBasedCity[assignments[i][0]][assignments[i][1]].equals(BasedCityOne) &&  suitableDeperatureTime1 > 72.0|| SuitableBasedCity[assignments[i][0]][assignments[i][1]].equals(BasedCityTwo) && suitableDeperatureTime2 > 72.0 ) {
						 DeperatureDate2 = String.valueOf(data.get(i).getTextFieldDeperatureDate());
						 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
						Calendar c = Calendar.getInstance();
						c.setTime(sdf.parse(DeperatureDate2));
						c.add(Calendar.DATE, 3);  // number of days to add
						DeperatureDate2 = sdf.format(c.getTime());  // dt is now the new date
						SuitableDeperatureDate[i][j] = DeperatureDate2;
				}
					else if(SuitableBasedCity[assignments[i][0]][assignments[i][1]].equals(BasedCityOne) &&  suitableDeperatureTime1 > 48.0 || SuitableBasedCity[assignments[i][0]][assignments[i][1]].equals(BasedCityTwo) && suitableDeperatureTime2 > 48.0 ) {
						 DeperatureDate2 = String.valueOf(data.get(i).getTextFieldDeperatureDate());
						 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
						Calendar c = Calendar.getInstance();
						c.setTime(sdf.parse(DeperatureDate2));
						c.add(Calendar.DATE, 2);  // number of days to add
						DeperatureDate2 = sdf.format(c.getTime());  // dt is now the new date
						SuitableDeperatureDate[i][j] = DeperatureDate2;
				}
					else if(SuitableBasedCity[assignments[i][0]][assignments[i][1]].equals(BasedCityOne) &&  suitableDeperatureTime1 > 24.00 || SuitableBasedCity[assignments[i][0]][assignments[i][1]].equals(BasedCityTwo) && suitableDeperatureTime2 > 24.00 ) {
						 DeperatureDate2 = String.valueOf(data.get(i).getTextFieldDeperatureDate());
						 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
						Calendar c = Calendar.getInstance();
						c.setTime(sdf.parse(DeperatureDate2));
						c.add(Calendar.DATE, 1);  // number of days to add
						DeperatureDate2 = sdf.format(c.getTime());  // dt is now the new date
						SuitableDeperatureDate[i][j] = DeperatureDate2;
				}
				
				
				else {
					DeperatureDate2 = String.valueOf(data.get(i).getTextFieldDeperatureDate());
					SuitableDeperatureDate[i][j] = DeperatureDate2;
				}
				}
			}
		}

		return assignments;
	}
	//Calls hgAlgorithmAssignments and getAssignmentSum to compute the
	//minimum cost or maximum profit possible.
//	public static double hgAlgorithm(double[][] array, String sumType)
//	{
//		return getAssignmentSum(array, hgAlgorithmAssignments(array, sumType));
//	}
//	public static double getAssignmentSum(double[][] array, int[][] assignments) {
//		//Returns the min/max sum (cost/profit of the assignment) given the
//		//original input matrix and an assignment array (from hgAlgorithmAssignments)
//		double sum = 0; 
//		for (int i=0; i<assignments.length; i++)
//		{
//			sum = sum + array[assignments[i][0]][assignments[i][1]];
//		}
//		return sum;
//	}
	public static double[][] hg_step1()
	{
		//What STEP 1 does:
		//For each row of the cost matrix, find the smallest element
		//and subtract it from from every other element in its row. 
		
		double minval;
		double[][] cost1 = new double[cost.length][cost[0].length];
		for (int i=0; i<cost.length; i++)	
		{									
			minval=cost[i][0]*10;
			for (int j=0; j<cost[i].length; j++)	//1st inner loop finds min val in row.
			{
				cost[i][j] = cost[i][j]*10;
				if (minval>cost[i][j])
				{
					minval=cost[i][j];
				}
			}
			
			for (int j=0; j<cost[i].length; j++)	//2nd inner loop subtracts it.
			{
				cost[i][j]=cost[i][j]  - minval;
				cost[i][j] = cost[i][j] / 10;
				cost1[i][j] = cost[i][j];
			}
		}
		
		step1=2;
		return cost1;
	}
	public static int[][] hg_step2()
	{
		//What STEP 2 does:
		//Marks uncovered zeros as starred and covers their row and column.

		for (int i=0; i<cost.length; i++)
		{
			for (int j=0; j<cost[i].length; j++)
			{
				if ((cost[i][j]==0) && (colCover[j]==0) && (rowCover[i]==0))
				{
					mask[i][j]=1;
					colCover[j]=1;
					rowCover[i]=1;
				}
			}
		}

		clearCovers();	//Reset cover vectors.

		step1=3;
		return mask;
	}
	public static int[] hg_step3()
	{
		//What STEP 3 does:
		//Cover columns of starred zeros. Check if all columns are covered.

		for (int i=0; i<mask.length; i++)	//Cover columns of starred zeros.
		{
			for (int j=0; j<mask[i].length; j++)
			{
				if (mask[i][j] == 1)
				{
					colCover[j]=1;
				}
			}
		}

		int count=0;						
		for (int j=0; j<colCover.length; j++)	//Check if all columns are covered.
		{
			count=count+colCover[j];
		}

		if (count>=mask.length)	//Should be cost.length but ok, because mask has same dimensions.	
		{
			step1=7;

		}
		else
		{
			step1=4;
		}
		return colCover;

	}
	public static int[] hg_step4()
	{
		//What STEP 4 does:
		//Find an uncovered zero in cost and prime it (if none go to step 6). Check for star in same row:
		//if yes, cover the row and uncover the star's column. Repeat until no uncovered zeros are left
		//and go to step 6. If not, save location of primed zero and go to step 5.

		int[] row_col = new int[2];	//Holds row and col of uncovered zero.
		boolean done = false;
		while (done == false)
		{
			row_col = findUncoveredZero(row_col);
			if (row_col[0] == -1)
			{
				done = true;
				step1 = 6;
				
			}
			else
			{
				mask[row_col[0]][row_col[1]] = 2;	//Prime the found uncovered zero.

				boolean starInRow = false;
				for (int j=0; j<mask[row_col[0]].length; j++)
				{
					if (mask[row_col[0]][j]==1)		//If there is a star in the same row...
					{
						starInRow = true;
						row_col[1] = j;		//remember its column.
					}
				}

				if (starInRow==true)	
				{
					rowCover[row_col[0]] = 1;	//Cover the star's row.
					colCover[row_col[1]] = 0;	//Uncover its column.
					
				}
				else
				{
					zero_RC[0] = row_col[0];	//Save row of primed zero.
					zero_RC[1] = row_col[1];	//Save column of primed zero.
					done = true;
					step1 = 5;
				}
			}
		}
		return row_col;
	
	}
	public static int[] findUncoveredZero	//Aux 1 for hg_step4.
	(int[] row_col)
	{
		row_col[0] = -1;	//Just a check value. Not a real index.
		row_col[1] = 0;

		int i = 0; boolean done = false;
		while (done == false)
		{
			int j = 0;
			while (j < cost[i].length)
			{
				if (cost[i][j]==0 && rowCover[i]==0 && colCover[j]==0)
				{
					row_col[0] = i;
					row_col[1] = j;
					done = true;
				}
				j = j+1;
			}//end inner while
			i=i+1;
			if (i >= cost.length)
			{
				done = true;
			}
		}//end outer while

		return row_col;
	}
	public static int[][] hg_step5()
	{
		//What STEP 5 does:	
		//Construct series of alternating primes and stars. Start with prime from step 4.
		//Take star in the same column. Next take prime in the same row as the star. Finish
		//at a prime with no star in its column. Unstar all stars and star the primes of the
		//series. Erasy any other primes. Reset covers. Go to step 3.

		int count = 0;										//Counts rows of the path matrix.
		
		//int[][] path = new int[(mask[0].length + 2)][2];	//Path matrix (stores row abn nd col).
		path[count][0] = zero_RC[0];						//Row of last prime.
		path[count][1] = zero_RC[1];						//Column of last prime.

		boolean done = false;
		while (done == false)
		{ 
			int r = findStarInCol(path[count][1]);
			if (r>=0)
			{
				count = count+1;
				path[count][0] = r;					//Row of starred zero.
				path[count][1] = path[count-1][1];	//Column of starred zero.
			}
			else
			{
				done = true;
			}
			
			if (done == false)
			{
				int c = findPrimeInRow(mask, path[count][0]);
				count = count+1;
				path[count][0] = path[count-1][0];	//Row of primed zero.
				path[count][1] = c;					//Col of primed zero.
			}
		}//end while

		convertPath(count);
		clearCovers();
		erasePrimes();

		step1 = 3;
		return mask;
		
	}
	public static int findStarInCol			//Aux 1 for hg_step5.
	(int col)
	{
		int r = -1;	//Again this is a check value.
		for (int i=0; i<mask.length; i++)
		{
			if (mask[i][col]==1)
			{
				r = i;
			}
		}

		return r;
	}
	public static int findPrimeInRow		//Aux 2 for hg_step5.
	(int[][] mask, int row)
	{
		int c = -1;
		for (int j=0; j<mask[row].length; j++)
		{
			if (mask[row][j]==2)
			{
				c = j;
			}
		}
		
		return c;
	}
	public static void convertPath			//Aux 3 for hg_step5.
	(int count)
	{
		for (int i=0; i<=count; i++)
		{
			if (mask[path[i][0]][path[i][1]]==1)
			{
				mask[path[i][0]][path[i][1]] = 0;
			}
			else
			{
				mask[path[i][0]][path[i][1]] = 1;
			}
		}
	}
	public static void erasePrimes			//Aux 4 for hg_step5.
	()
	{
		for (int i=0; i<mask.length; i++)
		{
			for (int j=0; j<mask[i].length; j++)
			{
				if (mask[i][j]==2)
				{
					mask[i][j] = 0;
				}
			}
		}
	}
	public static void clearCovers			//Aux 5 for hg_step5 (and not only).
	()
	{
		for (int i=0; i<rowCover.length; i++)
		{
			rowCover[i] = 0;
		}
		for (int j=0; j<colCover.length; j++)
		{
			colCover[j] = 0;
		}
	}
	public static double[][] hg_step6()
	{
		//What STEP 6 does:
		//Find smallest uncovered value in cost: a. Add it to every element of covered rows
		//b. Subtract it from every element of uncovered columns. Go to step 4.

		double minval = findSmallest(cost, rowCover, colCover);

		for (int i=0; i<rowCover.length; i++)
		{
			for (int j=0; j<colCover.length; j++)
			{
				if (rowCover[i]==1)
				{
					cost[i][j] = cost[i][j] + minval;
				}
				if (colCover[j]==0)
				{
					cost[i][j] = cost[i][j] - minval;
				}
			}
		}
			
		step1 = 4;
		return cost;
	}
	public static double findSmallest		//Aux 1 for hg_step6.
	(double[][] cost, int[] rowCover, int[] colCover)
	{
		double minval = Double.POSITIVE_INFINITY;	//There cannot be a larger cost than this.
		for (int i=0; i<cost.length; i++)		//Now find the smallest uncovered value.
		{
			for (int j=0; j<cost[i].length; j++)
			{
				if (rowCover[i]==0 && colCover[j]==0 && (minval > cost[i][j]))
				{
					minval = cost[i][j];
				}
			}
		}
		
		return minval;
	}

	public static void set(double [][] arr, int i, int j, double v) {arr[i][j] = v;}
	

}