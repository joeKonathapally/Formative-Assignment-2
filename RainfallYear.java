package org.com1027.formative2.css2ht.extra;



import org.com1027.formative2.css2ht.extra.Month;


public class RainfallYear {
	

	

	
	private int year;
	private double[] rainfallMonths;
	
	public RainfallYear(int year,double[] db){
		this.year=year;
		this.rainfallMonths=db;
		
	}
	
	public double calculateMeanRainfall(){
		double mean=0;
		for(double monthlyRainfall: rainfallMonths)
		{
			mean = mean+monthlyRainfall;
		}
		mean=mean/12;
		return mean;
	}
	
	public double getRainfallMonth(Month month){
		return rainfallMonths[month.getNumber()];
	}
	
	public int getYear(){
		return this.year;
	}
	
	
	
	
}
