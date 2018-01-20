package algorithms.taskallocation;

import java.util.ArrayList;

import java.util.List;

public class MinimaxResult {
	
	private double m[][] = {{1,2,3,24,5},{6,7,8,9,10},{11,12,13,14,15},{16,17,18,19,20},{21,22,23,4,25}};
	private List<Element> listE = new ArrayList<Element>();
	private ArrayList<Element> arrayE = new ArrayList<Element>();
	
	public void sumLine(){
		Element e,e1;
		
		for (int c = 0; c < m.length; c++) {
			e = new Element(1000,1000,1000);
			for (int l = 0; l < m.length; l++) {
				e1 = new Element(l, c, m[l][c]);
				e = eMin(e,e1);
			}
			listE.add(e);
	}
	
		System.out.println(" Result ");
		for (Element el : listE) {
			System.out.println("["+el.getPosX()+","+el.getPosY()+"] = "+el.getValue());
		}
	
		
	}
	
	public Element eMin(Element e1, Element e2){
		Element eMin = e1;
		if(e2.getValue() < e1.getValue()){
			eMin = e2;
		}
		return eMin;
	}

	public int length(){
		return m.length;
	}

	public double[][] getM() {
		return m;
	}
	public void setM(double[][] m) {
		this.m = m;
	}
	
	public static void main(String []args){
		MinimaxResult mr = new MinimaxResult();
		
		System.out.println("Taille = "+mr.length());
		mr.sumLine();
		
	}
}

class Element{
	
	private int posX;
	private int posY;
	private double value;
	
	public Element() {
		super();
	}
	public Element(int posX, int posY, double value) {
		super();
		this.posX = posX;
		this.posY = posY;
		this.value = value;
	}
	
	public int getPosX() {
		return posX;
	}
	public void setPosX(int posX) {
		this.posX = posX;
	}
	public int getPosY() {
		return posY;
	}
	public void setPosY(int posY) {
		this.posY = posY;
	}
	public double getValue() {
		return value;
	}
	public void setValue(double value) {
		this.value = value;
	}
}
