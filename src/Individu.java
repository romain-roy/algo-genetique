import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Individu implements Comparable<Individu>, Cloneable {

	private List<Ville> listeVilles;

	public Individu() {
		this.listeVilles = new ArrayList<Ville>(Villes.getVilles());
		Collections.shuffle(this.listeVilles);
		this.villeZeroEnPremier();
	}

	public double perfDuTrajet() {
		double aRenvoyer = 0.0;
		for (int i = 0; i < this.listeVilles.size() - 1; i++) {
			aRenvoyer += Villes.distance(this.listeVilles.get(i), this.listeVilles.get(i + 1));
		}
		aRenvoyer += Villes.distance(this.listeVilles.get(this.listeVilles.size() - 1), this.listeVilles.get(0));
		return aRenvoyer;
	}

	@Override
	public int compareTo(Individu ind) {
		if (this.perfDuTrajet() < ind.perfDuTrajet()) {
			return -1;
		} else if (this.perfDuTrajet() > ind.perfDuTrajet()) {
			return 1;
		} else {
			return 0;
		}
	}

	public static List<Individu> croisement(Individu individu1, Individu individu2) {
		// génération aléatoire des deux points de coupe
		Random randGen = new Random();
		int pointDeCoupe1;
		int pointDeCoupe2;
		do {
			pointDeCoupe1 = randGen.nextInt(individu1.listeVilles.size());
			pointDeCoupe2 = randGen.nextInt(individu1.listeVilles.size());
		} while (pointDeCoupe1 >= pointDeCoupe2);

		// remplacement des villes entre les deux points de coupe
		Individu enfant1 = individu1.clone();
		Individu enfant2 = individu2.clone();
		for (int i = pointDeCoupe1; i <= pointDeCoupe2; i++) {
			enfant1.listeVilles.set(i, individu2.listeVilles.get(i));
			enfant2.listeVilles.set(i, individu1.listeVilles.get(i));
		}

		// Ajout des villes manquantes + Suppression des villes en double
		Ville tampon1 = null;
		Ville tampon2 = null;
		for (int i = 0; i < individu1.listeVilles.size(); i++) {
			if (!(enfant1.listeVilles.contains(Villes.getVille(i)))) {
				tampon1 = Villes.getVille(i);
				for (int j = 0; j < individu1.listeVilles.size(); j++) {
					if (!(enfant2.listeVilles.contains(Villes.getVille(j)))) {
						tampon2 = Villes.getVille(j);
						j = individu1.listeVilles.size();
					}
				}
				enfant1.listeVilles.set(enfant1.listeVilles.indexOf(tampon2), tampon1);
				enfant2.listeVilles.set(enfant2.listeVilles.indexOf(tampon1), tampon2);
			}
		}

		// mutation aléatoire
		if (randGen.nextInt(100) < 50) {
			
			int point1;
			int point2;
			do {
				point1 = randGen.nextInt(individu1.listeVilles.size());
				point2 = randGen.nextInt(individu1.listeVilles.size());
			} while (point1 >= point2);
			
			//On reflète le chemin entre deux points
			for (int i=0 ; i<(point2-point1)/2 ; i++) {
				Collections.swap(enfant1.listeVilles, point1 + i, point2 - i);
				Collections.swap(enfant2.listeVilles, point1 + i, point2 - i);
			}
		}
		
		
		ArrayList<Individu> enfants = new ArrayList<Individu>();
		enfants.add(enfant1);
		enfants.add(enfant2);
		return enfants;
	}

	public Individu clone() {
		Individu clone = null;
		try {
			clone = (Individu) super.clone();
			clone.listeVilles = new ArrayList<Ville>(this.listeVilles);
		} catch (Exception e) {
			System.out.println("Erreur : " + e.getMessage());
		}
		return clone;
	}

	public List<Ville> getListeVilles() {
		return listeVilles;
	}

	public void setListeVilles(List<Ville> listeVilles) {
		this.listeVilles = listeVilles;
	}

	private void villeZeroEnPremier() {
		Ville tampon = this.listeVilles.get(0);
		int index = this.listeVilles.indexOf(Villes.getVille(0));
		this.listeVilles.set(index, tampon);
		this.listeVilles.set(0, Villes.getVille(0));
	}
}