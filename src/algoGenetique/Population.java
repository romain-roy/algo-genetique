package algoGenetique;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Population {

	private ArrayList<Individu> population;

	public Population(int nbIndividu) {
		this.population = new ArrayList<Individu>();
		for (int i = 0; i < nbIndividu; i++)
			this.population.add(new Individu());
	}

	public Population() {
		this.population = new ArrayList<Individu>();
	}

	public Individu meilleurIndividu() {
		Collections.sort(this.population);
		return this.population.get(0);
	}

	public ArrayList<Individu> getPopulation() {
		return population;
	}

	public Population reproduire() {
		Random randGen = new Random();
		Population enfants = new Population();

		// ellitisme : Les 6 meilleurs sont ajoutes d'office dans la nouvelle generation
		
		Collections.sort(this.population);
		for (int i=0 ; i<6 ; i++)
			enfants.population.add(this.population.get(i));

		// croisement : On genere les autres avec des croisements entre les individus
		
		while (enfants.population.size() < this.population.size()) {
			enfants.population.addAll(Individu.croisement(this.population.get(randGen.nextInt(this.population.size() / 5)),
					this.population.get(randGen.nextInt(this.population.size() / 5))));
		}
		System.out.println(" -> nbIndiv = " + enfants.population.size());
		return enfants;
	}
}
