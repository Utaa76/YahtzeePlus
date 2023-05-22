public class Controleur
{
	private Yahtzee         metier;
	private FramePrincipale vue;

	public Controleur()
	{
		this.metier = new Yahtzee();
		this.vue    = new FramePrincipale(this);
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

	public void fermerFenetre()
	{
		this.vue.dispose();
	}

	public void placer(int indice)
	{
		switch (indice)
		{
			case 0, 1, 2, 3, 4, 5                -> this.metier.placer(indice+1);
			case 7, 8, 9, 10, 11, 12, 13, 14, 15 -> this.metier.placer(indice);
			case 18, 19                          -> this.metier.placer(indice-2);
			case 22, 23, 24, 25                  -> this.metier.placer(indice-4);
		}
	}

	public void majIHM()
	{
		this.vue.majIHM();
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
 * 	Score total
 * 	Description de l'annonce quand on passe sur son titre
 * 	Mode pointage du curseur sur les scores
 * 	Affichage des scores possibles dans chaque ligne de score (tous comme sur jeugratuit.com ou quand on passe la souris)
 * 	Statistiques (nb parties jouées, score maximal, etc) (annonce lorsqu'on inscrit un nouveau score max ?)
 */