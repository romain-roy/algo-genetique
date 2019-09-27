import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Population {

	private ArrayList<Individu> population;

	public Population(int nbIndividu) {
		this.population = new ArrayList<Individu>();
		for (int i = 0; i < nbIndividu; i++) {
			this.population.add(new Individu());
		}
	}
	
	public Population() {
		this.population = new ArrayList<Individu>();
	}

	public Individu meilleurIndividu(){
		Collections.sort(this.population);
		return this.population.get(0);
	}
	
	public ArrayList<Individu> getPopulation() {
		return population;
	}
	
	/*private int rouletteAléatoire(){
		int S1 = 0;
		for(Individu i : this.population){
			S1 += i.perfDuTrajet();
		}
		Random randGen = new Random();
		int r = randGen.nextInt(S1);
		int S2 = 0;
		int i=0;
		while(r > S2){
			S2 += population.get(i).perfDuTrajet();
			i++;
		}
	}*/

	public Population reproduire() {
		Random randGen = new Random();
		Population enfants = new Population();

		//ellitisme : Les 6 meilleurs sont ajoutés d'office dans la nouvelle génération
		Collections.sort(this.population);
		for(int i=0 ; i<6 ; i++){
			enfants.population.add(this.population.get(i));
		}
		
		//croisement : On gÃ©nÃ¨re les autres avec des croisements entres les individus
		while (enfants.population.size() < this.population.size()) {
			enfants.population
					.addAll(Individu.croisement(this.population.get(randGen.nextInt(this.population.size() / 5)),
							this.population.get(randGen.nextInt(this.population.size() / 5))));
			
		}
		System.out.println(" -> nbIndiv = " + enfants.population.size());
		return enfants;
	}
}