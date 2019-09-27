public class Ville {
	
	private double coordX;
	private double coordY;
	
	public Ville(double coordX, double coordY){
		this.coordX = coordX;
		this.coordY = coordY;
	}

	@Override
	public String toString() {
		return "Ville [coordX=" + coordX + ", coordY=" + coordY + "]";
	}

	public double getCoordX() {
		return coordX;
	}

	public double getCoordY() {
		return coordY;
	}
	
	public void setCoordX(double coordX) {
		this.coordX = coordX;
	}
	
	public void setCoordY(double coordY) {
		this.coordY = coordY;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ville other = (Ville) obj;
		if(this.coordX != other.coordX || this.coordY != other.coordY)
			return false;
		return true;
	}
}
