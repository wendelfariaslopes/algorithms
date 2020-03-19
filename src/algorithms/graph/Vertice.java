package algorithms.graph;

public class Vertice {
	
	private int info;
	private Vertice dir;
	private Vertice esq;
	
	public int getInfo() {
		return info;
	}
	public void setInfo(int info) {
		this.info = info;
	}
	public Vertice getDir() {
		return dir;
	}
	public void setDir(Vertice dir) {
		this.dir = dir;
	}
	public Vertice getEsq() {
		return esq;
	}
	public void setEsq(Vertice esq) {
		this.esq = esq;
	}
}
