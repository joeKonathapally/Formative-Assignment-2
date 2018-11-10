package org.com1027.formative2.css2ht.extra;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Rainfall {
	
	
	private ArrayList<RainfallYear> list = new ArrayList<RainfallYear>();
	public Rainfall(String source) {
		
		BufferedReader br=null;
		File in = new File(source);
		String buffer="";
		try {
			
			br= new BufferedReader(new FileReader(in));
			
			while((buffer = br.readLine())!=null)
			{
				int year=0;
				double[] rainfall=new double[12];
				String[] data = buffer.split(",");
				for(int i=0;i<data.length;i++) {
					if(i<1)
					{
						year = Integer.parseInt(data[i]);
					}
					else
					{
						rainfall[i-1]=Double.parseDouble(data[i]);
					}
				}
				list.add(new RainfallYear(year,rainfall));
				
			}
			
			
			
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		finally {
			if(buffer!=null)
			{
				try {
					br.close();
				}
				catch(IOException e) {
					e.printStackTrace();
				}
			}
			
		}
		
		
	}
	
	public Rainfall(RainfallYear[] db) {
		
		for(RainfallYear x: db)
		{
			this.list.add(x);
		}
		
	}
	
	
	
	public double calculateHighestMeanAnnualRainfall() {
		double maxRainfall=0;
		for(RainfallYear x:list)
		{
			if(x.calculateMeanRainfall()>maxRainfall)
			{
				maxRainfall=x.calculateMeanRainfall();
			}
			
		}
		return maxRainfall;	
	}
	
	public double calculateLowestMeanAnnualRainfall() {
		double minRainfall= list.get(0).calculateMeanRainfall();
		for(RainfallYear x: list)
		{
			if(x.calculateMeanRainfall()<minRainfall)
			{
				minRainfall=x.calculateMeanRainfall();
			}
		}
		return minRainfall;
	}
	
	public double calculateMeanRainfallMonth(Month month) {
		double totalRainfall=0;
		
		for(RainfallYear x:list) {
			totalRainfall = totalRainfall +x.getRainfallMonth(month);
		}
		totalRainfall=totalRainfall/list.size();
		return totalRainfall;
	}
	public double calculateMeanRainfallYear(int year) {
		RainfallYear r1=null;
		for(RainfallYear x:list)
		{
			if(year==x.getYear())
			{
				r1=x;
			}
		}
		double annualRainfall=0;
		for(Month month:Month.values())
		{
			annualRainfall=annualRainfall+r1.getRainfallMonth(month);
		}
		annualRainfall = annualRainfall/12;
		return annualRainfall;
	}
	
	public int[] getYears(){
		int[] years;
		ArrayList<Integer> yearss = new ArrayList<Integer>();
		for(RainfallYear x: list)
		{
			yearss.add(x.getYear());
		}
		years = new int[yearss.size()];
		int i=0;
		for(int x: yearss)
		{
			years[i]=x;
			i += 1;
		}
		return years;
		
	}
	
	public double calculateMeanPrecipitationTwoYears(int year1, int year2)
	{
		if(year1<year2)
		{
			int range = year2-year1 +1;
			double totalAnnualRainfall=0;
			for(RainfallYear x : list)
			{
				if(x.getYear()>=year1 && x.getYear()<=year2)
				{
					totalAnnualRainfall = totalAnnualRainfall + x.calculateMeanRainfall();
				}
			}
			totalAnnualRainfall = totalAnnualRainfall/range;
			return totalAnnualRainfall;
		}
		else if(year1>year2)
		{
			int range = year1-year2 +1;
			double totalAnnualRainfall=0;
			for(RainfallYear x : list)
			{
				if(x.getYear()>=year2 && x.getYear()<=year1)
				{
					totalAnnualRainfall = totalAnnualRainfall + x.calculateMeanRainfall();
				}
			}
			totalAnnualRainfall = totalAnnualRainfall/range;
			return totalAnnualRainfall;
		}
		else
		{
			double totalAnnualRainfall=0;
			for(RainfallYear x: list)
			{
				totalAnnualRainfall = x.calculateMeanRainfall();
			}
			return totalAnnualRainfall;
		}
			
	}
	
	public double calculateMinMonthlyPrecipitation(int year) {
		
		RainfallYear r1=null;
		for(RainfallYear x: list)
		{
			if(x.getYear()==year)
			{
				r1=x;
			}
		}
		double lowestValue=r1.getRainfallMonth(Month.JANUARY);
		for(Month month: Month.values())
		{
			if(r1.getRainfallMonth(month)<lowestValue)
			{
				lowestValue = r1.getRainfallMonth(month);
			}
		}
		return lowestValue;		
	}
	
	public void representDataAsHistogram() {
		for(RainfallYear x: list) {
			System.out.print(x.getYear()+".");
			for(int i=0;i<=((int)(x.calculateMeanRainfall()/10))*2;i++)
			{
				System.out.print("*");
			}
			System.out.println();
		}
	}
	

	
	
	
	
	
	
	

}
