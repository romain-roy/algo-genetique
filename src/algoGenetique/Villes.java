package algoGenetique;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Villes {

	private static List<Ville> coords;

	public static double min;
	public static double max;

	public static void lireVilles(String fichier) {
		coords = new ArrayList<Ville>();
		min = 0;
		max = 0;
		try {
			InputStream ips = new FileInputStream(fichier);
			InputStreamReader ipsr = new InputStreamReader(ips);
			BufferedReader br = new BufferedReader(ipsr);
			String ligne;
			while ((ligne = br.readLine()) != null) {
				int distToCut = ligne.indexOf(";");
				coords.add(new Ville(Double.parseDouble(ligne.substring(0, distToCut)), Double.parseDouble(ligne.substring(distToCut + 1, ligne.length()))));
				if (Double.parseDouble(ligne.substring(0,distToCut)) < min)
					min = Double.parseDouble(ligne.substring(0,distToCut));
				if (Double.parseDouble(ligne.substring(0,distToCut)) > max)
					max = Double.parseDouble(ligne.substring(0,distToCut));
			}
			System.out.println(coords);
			br.close();
		} catch (Exception e) {
			System.out.println(e.toString() + " : Erreur lors de l'ouverture du fichier des villes");
		}
		System.out.println(coords);
	}

	public static Ville getVille(int index) {
		return coords.get(index);
	}

	public static List<Ville> getVilles() {
		return coords;
	}

	public static double distance(Ville ville1, Ville ville2) {
		return Math.sqrt(Math.pow(ville2.getCoordX() - ville1.getCoordX(), 2) + Math.pow(ville2.getCoordY() - ville1.getCoordY(), 2));
	}

	public static double getMin(){
		return min;
	}

	public static double getMax(){
		return max;
	}

}
