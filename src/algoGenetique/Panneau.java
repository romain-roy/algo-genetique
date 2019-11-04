package algoGenetique;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public class Panneau extends JPanel {

	private static final long serialVersionUID = 1L;
	private Individu indiv;

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		int taille = (int)this.getHeight();

		for (Ville v : Villes.getVilles())
			g.fillOval((int)(v.getCoordX() * taille) - 2, (int) (-v.getCoordY() * taille + taille - 2), 5, 5);

		g.setColor(new Color(255, 0, 0));
		if (indiv != null) {
			for (int i = 0; i < indiv.getListeVilles().size() - 1; i++) {
				g.drawLine((int) (indiv.getListeVilles().get(i).getCoordX() * taille),
						(int) (-indiv.getListeVilles().get(i).getCoordY() * taille + taille),
						(int) (indiv.getListeVilles().get(i + 1).getCoordX() * taille),
						(int) (-indiv.getListeVilles().get(i + 1).getCoordY() * taille + taille));
			}
			g.drawLine((int) (indiv.getListeVilles().get(indiv.getListeVilles().size()-1).getCoordX() * taille),
					(int) (-indiv.getListeVilles().get(indiv.getListeVilles().size()-1).getCoordY() * taille + taille),
					(int) (indiv.getListeVilles().get(0).getCoordX() * taille),
					(int) (-indiv.getListeVilles().get(0).getCoordY() * taille + taille));
		}
	}

	public void recupMeilleurIndiv(Population p) {
		this.indiv = p.meilleurIndividu();
		repaint();
	}
}
