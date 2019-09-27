import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		Villes.lireVilles("src//villes.txt");
		int nbIndividus = 150;
		
		JFrame fen = new JFrame();
		Panneau panneau = new Panneau();
		
		JLabel longueur = new JLabel("");
		JLabel temps = new JLabel("");
		JMenuBar barre = new JMenuBar();
		barre.add(longueur);
		barre.add(temps);
		fen.setJMenuBar(barre);
		
		fen.setExtendedState(JFrame.MAXIMIZED_BOTH);
		fen.setContentPane(panneau);
		fen.setTitle("Algorithme génétique");
		fen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fen.setVisible(true);
		
		long startTime = System.currentTimeMillis();
		
		Population p = new Population(nbIndividus);
		System.out.println("Population initiale : " + p.getPopulation().get(0).perfDuTrajet());
		
		int i = 0;
		while((System.currentTimeMillis() - startTime)/1000 < 600){
			i++;
			p = p.reproduire();
			panneau.récupérerMeilleurIndiv(p);
			System.out.println("Génération n°" + i + " : " + p.getPopulation().get(0).perfDuTrajet());
			//Thread.sleep(1000);
			longueur.setText("Longueur du chemin : " + p.getPopulation().get(0).perfDuTrajet() + "         ");
			temps.setText("Fonctionne depuis " + (System.currentTimeMillis() - startTime)/1000 + " secondes");
		}
	}
}
