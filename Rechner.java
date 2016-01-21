package me.yannisgerlach.rechner;

public class Rechner {

	public float alteleistung = 0;//watt
	public float neueleistung = 0;//watt
	public float stromkosten = 0; //cent pro kwh
	public float nutzdauer = 0;//nutzdauer pro tag in h
	public float kosten = 0;//umrüstkosten in cent 
	public int monate = 0;
	
	
	public float rechnen()
	{
		float diferenz = alteleistung - neueleistung;//wieviel wird gespart an energie?
		float kaufen = kosten / stromkosten;//wieviel kann man kaufen?
		float verbrauchprotag = (diferenz * nutzdauer) / 1000 ; // wie viel wird pro tag verbraucht?
		float wielangereichtdas = kaufen / verbrauchprotag;//wielange reicht das "gekaufte"?
		    
		monate = (int) wielangereichtdas / 30;
	       
	    return wielangereichtdas;
	}
}
