package model.bean.militares;

public abstract class Militar {
	
	protected int id;
	
	public abstract String toString();
	public abstract int valorAtaque(); // Retorna um valor sorteado somado com o valor extra de cada especializacao
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
}
