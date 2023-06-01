import java.awt.Color;

import javax.swing.JFrame;

public class Controleur
{
	public static final Color BLEU = new Color(20,50,150);

	private Yahtzee         metier;
	private FramePrincipale vue;
	private FrameMenu       menu;
	private FrameStats      stats;

	public Controleur()
	{
		this.menu   = new FrameMenu(this);
		this.metier = new Yahtzee();
	}

	public String getImage(int i)
	{
		De[] ensDe = this.metier.getTabDes();

		String sRet = "./images/de" + ensDe[i].getNumero();
		if (ensDe[i].estConserver()) sRet += "_s";

		return sRet + ".png";
	}

	public String[][] getTableau()
	{
		return this.metier.getTableau();
	}

	public int getNbLancerRestant()
	{
		return Math.abs(3-this.metier.getNbLancer());
	}

	public void conserver(int i)
	{
		this.metier.conserver(i+1);
	}

	public void fermerFenetreVue()
	{
		this.vue.dispose();
	}

	public void lancer()
	{
		this.metier.lancer();
	}

	public String getTitreAnnonce(int i)
	{
		return this.metier.getTitreAnnonce(i);
	}

	public int getScoreAnnonce(int i)
	{
		return this.metier.getScoreAnnonce(i);
	}

	public void commencer()
	{
		this.vue = new FramePrincipale(this);
		this.menu.dispose();
	}

	public boolean placer(int indice)
	{
		boolean bRet = false;
		switch (indice)
		{
			case 0, 1, 2, 3, 4, 5                -> bRet = this.metier.placer(indice+1);
			case 7, 8, 9, 10, 11, 12, 13, 14, 15 -> bRet = this.metier.placer(indice);
			case 18, 19                          -> bRet = this.metier.placer(indice-2);
			case 22, 23, 24, 25                  -> bRet = this.metier.placer(indice-4);
		}

		return bRet;
	}

	public int getScoreSelonAnnonce(int indice)
	{
		int total = 0;
		switch (indice)
		{
			case 0, 1, 2, 3, 4, 5                -> total = this.metier.getScoreSelonAnnonce(indice+1);
			case 7, 8, 9, 10, 11, 12, 13, 14, 15 -> total = this.metier.getScoreSelonAnnonce(indice);
			case 18, 19                          -> total = this.metier.getScoreSelonAnnonce(indice-2);
			case 22, 23, 24, 25                  -> total = this.metier.getScoreSelonAnnonce(indice-4);
		}

		return total;
	}

	public void majIHM()
	{
		this.vue.majIHM();
	}

	public boolean partieTerminee()
	{
		return this.metier.partieTerminee();
	}

	public int getScoreTotal()
	{
		return this.metier.calculScore();
	}

	public String getExplication(int indice)
	{
		return this.metier.getExplication(indice+1);
	}

	public void majExplication(String text)
	{
		this.vue.majExplication(text);
	}

	public void reinitialiser()
	{
		this.vue.dispose();
		this.metier = new Yahtzee();
		this.vue = new FramePrincipale(this);
	}

	public void getStats()
	{
		this.menu.dispose();
		this.stats = new FrameStats(this);
	}

	public void fermerFenetreStats()
	{
		this.stats.dispose();
	}

	public static void main(String[] args)
	{
		System.out.println("(C) Yanis VERDIER");
		new Controleur();
	}
}

/*
 * Chose à rajouter :
 * 	Gestion de fin de partie en IHM
 * 	Statistiques (nb parties jouées, score maximal, etc) (annonce lorsqu'on inscrit un nouveau score max ?)
 * 	Mettre le Full avec ses primes
 * 	Faire un menu de démarrage (Bouton Commencer, Règles, etc)
 */