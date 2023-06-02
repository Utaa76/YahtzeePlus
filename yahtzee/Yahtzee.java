// Retirer les numéros des lignes des primes ? (change bcp de choses pour les conditions)
// Vérifier si la prime des +63pts marche tjrs

//import utilitaire.AffichageLigne;
import java.util.*;

public class Yahtzee
{
	private De[]       ensDe;
	private int        nbLancers;
	private String[][] fiche = { {""  , "Titre",                       "Valeur" ,       "Points obtenus", "Description"},
	                             {"1" , "Un",                          "Total des 1",   " "             , "Total des dés dont le score est 1"},
	                             {"2" , "Deux",                        "Total des 2",   " "             , "Total des dés dont le score est 2"},
	                             {"3" , "Trois",                       "Total des 3",   " "             , "Total des dés dont le score est 3"},
	                             {"4" , "Quatre",                      "Total des 4",   " "             , "Total des dés dont le score est 4"},
	                             {"5" , "Cinq",                        "Total des 5",   " "             , "Total des dés dont le score est 5"},
	                             {"6" , "Six",                         "Total des 6",   " "             , "Total des dés dont le score est 6"},
	                             {" " , "Prime - Scores Unitaires >63","35",            " "             , "PRIME de 35 points bonus\nObtenue si la somme des scores unitaires est supérieure ou égale à 63\n(soit 3 dés identiques par unité)"},
	                             {"7" , "Chance",                      "Total des dés", " "             , "Case bonus, aucune contraite"},
	                             {"8" , "Brelan",                      "Total des dés", " "             , "Trois dés identiques"},
	                             {"9" , "Carré",                       "Total des dés", " "             , "Quatre dés identiques"},
	                             {"10", "2 paires",                    "Total des dés", " "             , "Deux fois deux dés identiques"},
	                             {"11", "Minimum",                     "Total des dés", " "             , "Total des dés inférieur au maximum"},
	                             {"12", "Maximum",                     "Total des dés", " "             , "Total des dés supérieur au minimum"},
	                             {"13", "Pair",                        "Total des dés", " "             , "Chaque dé doit avoir un score pair"},
	                             {"14", "Impair",                      "Total des dés", " "             , "Chaque dé doit avoir un score impair"},
	                             {"15", "Twenty",                      "30",            " "             , "Total des dés égal à 20"},
	                             {"16", "Full",                        "25",            " "             , "Trois dés identiques, les deux restants identiques entre eux"},
	                             {" " , "Prime 2ème full",             "10",            " "             , "PRIME de 10 points\nObtenue si l'on place à nouveau un full dans la grille"},
	                             {" " , "Prime full du 1er coup",      "20",            " "             , "PRIME de 20 points\nObtenue si l'on place un full obtenue dès le premier lancer"},
	                             {"17", "Petite suite",                "30",            " "             , "Score de 4 dés se suivant\n(ex. : 1, 2 , 3, 4)"},
	                             {"18", "Grande suite",                "40",            " "             , "Score des 5 dés se suivant\n(ex. : 1, 2, 3, 4, 5)"},
	                             {" " , "Prime 2ème Gde suite",        "10",            " "             , "PRIME de 10 points\nObtenue si l'on place à nouveau une grande suite dans la grille"},
	                             {" " , "Prime Gde suite du 1er coup", "20",            " "             , "PRIME de 20 points\nObtenue si l'on place une grande suite dès le premier lancer"},
	                             {"19", "Minor",                       "40",            " "             , "Total des dés inférieur ou égal à 10"},
	                             {"20", "Major",                       "30",            " "             , "Total des dés supérieur ou égal à 25"},
	                             {"21", "Yahtzee",                     "50",            " "             , "Tous les dés identiques"},
	                             {" " , "Prime Yahtzee de 6",          "10",            " "             , "PRIME de 10 points bonus\nObtenue si l'on place un Yahtzee avec des dés de 6"},
	                             {" " , "Prime 2eme Yahtzee",          "100",           " "             , "PRIME de 100 points bonus\nObtenue si l'on place un deuxième Yahtzee"},
	                             {" " , "Prime 3eme Yahtzee",          "150",           " "             , "PRIME de 150 points bonus\nObtenue si l'on place un troisième Yahtzee"},
	                             {" " , "Prime Yahtzee 1er Coup",      "75",            " "             , "PRIME de 75 points bonus\nObtenue si l'on place un Yahtzee dès le premier lancer"},
	                             {" " , "Prime Yahtzee 2eme Coup",     "50",            " "             , "PRIME de 50 points bonus\nObtenue si l'on place un Yahtzee dès le deuxième lancer"}, };

	public Yahtzee()
	{
		this.ensDe = new De[5];
		this.nbLancers = 1;
		for (int i = 0 ; i < this.ensDe.length ; i++)
			this.ensDe[i] = new De(i+1);
	}

	public boolean lancer()
	{
		if (this.nbLancers >= 3) return false;
			
		for (De de : this.ensDe)
			de.lancer();

		this.nbLancers++;
		return true;
	}

	public boolean placer(int indice)
	{
		if (this.fiche[indice][3] != " " && indice >= 1 && indice <= 6) return false;
		if (indice < 1 || indice > 21) return false;
		
		int total = 0;
		switch (indice)
		{
			case 1 -> total = this.calculUnite(1);
			case 2 -> total = this.calculUnite(2);
			case 3 -> total = this.calculUnite(3);
			case 4 -> total = this.calculUnite(4);
			case 5 -> total = this.calculUnite(5);
			case 6 -> total = this.calculUnite(6);
			case 7, 8, 9, 10, 11, 12, 13, 14 ->
			{
				if (this.fiche[indice+1][3] != " ") return false;

				if (this.verifierCondition(indice+1))
					total = this.totalDes();
				else
					total = 0;
			}
			case 15, 16 ->
			{
				if (this.fiche[indice+1][3] != " ") return false;

				if (this.verifierCondition(indice+1))
					total = Integer.parseInt(this.fiche[indice+1][2]);
				else
					total = 0;
			}
			case 17, 18 ->
			{
				if (this.fiche[indice+3][3] != " ") return false;

				if (this.verifierCondition(indice+3))
					total = Integer.parseInt(this.fiche[indice+3][2]);
				else
					total = 0;
			}
			default ->
			{
				if (this.fiche[indice+5][3] != " ") return false;

				if (this.verifierCondition(indice+5))
					total = Integer.parseInt(this.fiche[indice+5][2]);
				else
					total = 0;
			}
		}

		for (De de : this.ensDe)
			de.setConserver(false);
		
		this.appliquerPrime();
		
		this.nbLancers = 0;
		this.lancer();
		
		if (indice <= 6)
			this.fiche[indice]  [3] = "" + total;
		
		if (indice >  6 && indice <= 16)
			this.fiche[indice+1][3] = "" + total;
		
		if (indice > 16 && indice <= 18)
			this.fiche[indice+3][3] = "" + total;

		if (indice >= 19)
			this.fiche[indice+5][3] = "" + total;

		if (this.verifierCondition(7))
			this.fiche[7][3] = this.fiche[7][2];

		return true;
	}

	public int getScoreSelonAnnonce(int indice)
	{
		int total = 0;
		switch (indice)
		{
			case 1 -> total = this.calculUnite(1);
			case 2 -> total = this.calculUnite(2);
			case 3 -> total = this.calculUnite(3);
			case 4 -> total = this.calculUnite(4);
			case 5 -> total = this.calculUnite(5);
			case 6 -> total = this.calculUnite(6);
			case 7, 8, 9, 10, 11, 12, 13, 14 ->
			{
				if (this.fiche[indice+1][3] != " ") return -1;

				if (this.verifierCondition(indice+1))
					total = this.totalDes();
				else
					total = 0;
			}
			case 15, 16 ->
			{
				if (this.fiche[indice+1][3] != " ") return -1;

				if (this.verifierCondition(indice+1))
					total = Integer.parseInt(this.fiche[indice+1][2]);
				else
					total = 0;
			}
			case 17, 18 ->
			{
				if (this.fiche[indice+3][3] != " ") return -1;

				if (this.verifierCondition(indice+3))
					total = Integer.parseInt(this.fiche[indice+3][2]);
				else
					total = 0;
			}
			default ->
			{
				if (this.fiche[indice+5][3] != " ") return -1;

				if (this.verifierCondition(indice+5))
					total = Integer.parseInt(this.fiche[indice+5][2]);
				else
					total = 0;
			}
		}

		return total;
	}

	private int calculUnite(int numero)
	{
		int total = 0;
		for (De de : this.ensDe)
			if (de.getNumero() == numero)
				total += numero;

		return total;
	}

	public void conserver(int de)
	{
		if (de >= 1 && de <= 5)
			this.ensDe[de-1].setConserver(!this.ensDe[de-1].estConserver());
	}

	public int calculScore()
	{
		int total = 0;
		int val = 0;
		boolean test;
		for (int lig = 0 ; lig < this.fiche.length ; lig++)
		{
			try
			{
				val = Integer.parseInt(this.fiche[lig][3]);
				test = false;
			} catch(Exception e) {test = true;}

			if (test) total += 0;
			else      total += val;
		}

		return total;
	}

	public boolean verifierCondition(int lig)
	{
		boolean bRet = false;
		if (lig < 0 || lig > 31) return false;

		switch (lig)
		{
			// Prime 63pts
			case 7 ->
			{
				int total = 0;
				for (int i = 0 ; i < 6 ; i++)
				{
					try
					{
						total += Integer.parseInt(this.fiche[i+1][3]);
					}
					catch (Exception e) {}
				}
				bRet = total >= 63;
			}

			// Chance
			case 8 -> bRet = true;

			case 9 -> // Brelan
			{
				if (this.max(this.nbSimilaire()) >= 3) bRet = true;
			}

			case 10 -> // Carré
			{
				if (this.max(this.nbSimilaire()) >= 4) bRet = true;
			}

			case 11 -> // 2 paires
			{
				int num1, num2;
				num1 = num2 = -1;

				for (int i = 0 ; i < this.nbSimilaire().length ; i++ )
				{
					if (this.nbSimilaire()[i] >= 2 && num1 < 0 ) num1 = i;
					if (this.nbSimilaire()[i] >= 2 && i != num1) num2 = i;
				}

				if (num1 > -1 && num2 > -1)
					bRet = num1 > -1 && num2 > -1 || this.max(this.nbSimilaire()) >= 4;
				else
					bRet = false;
			}

			case 12 -> // Minimum
			{
				if (!this.fiche[13][3].equals(" "))
					bRet = this.totalDes() < Integer.parseInt(this.fiche[13][3]);
				else
					bRet = true;
			}

			case 13 -> // Maximum
			{
				if (!this.fiche[12][3].equals(" "))
					bRet = this.totalDes() > Integer.parseInt(this.fiche[12][3]);
				else
					bRet = true;
			}

			case 14 -> // Pair
			{
				for (De de : this.ensDe)
					if (de.getNumero()%2 == 1) return false;
				bRet = true;
			}

			case 15 -> // Impair
			{
				for (De de : this.ensDe)
					if (de.getNumero()%2 == 0) return false;
				bRet = true;
			}

			// Twenty
			case 16 -> bRet = this.totalDes() == 20;
			
			case 17 -> // Full
			{
				int num1, num2;
				num1 = num2 = -1;

				for (int i = 0 ; i < this.nbSimilaire().length ; i++ )
				{
					if (this.nbSimilaire()[i] >= 3 && i != num2) num1 = i; // 0 2 1 3 0 0
					if (this.nbSimilaire()[i] >= 2 && i != num1) num2 = i;
				}

				bRet = num2 > -1 && num1 > -1;
			}

			case 18 -> // Prime 2eme full
			{
				int num1, num2;
				num1 = num2 = -1;

				if (this.fiche[17][3] != " ")
					for (int i = 0 ; i < this.nbSimilaire().length ; i++ )
					{
						if (this.nbSimilaire()[i] >= 3 && i != num2) num1 = i; // 0 2 1 3 0 0
						if (this.nbSimilaire()[i] >= 2 && i != num1) num2 = i;
					}

				bRet = num2 > -1 && num1 > -1;
			}
			
			case 19 -> // Prime full du 1er coup
			{
				int num1, num2;
				num1 = num2 = -1;

				if (this.nbLancers < 2)
					for (int i = 0 ; i < this.nbSimilaire().length ; i++ )
					{
						if (this.nbSimilaire()[i] >= 3 && i != num2)  num1 = i; // 0 2 1 3 0 0
						if (this.nbSimilaire()[i] >= 2 && i != num1) num2 = i;
					}

				bRet = num2 > -1 && num1 > -1;
			}

			case 20 -> // Petite suite
			{
				int[] tab = this.nbSimilaire();
				bRet = (tab[0] >= 1 && tab[1] >= 1 && tab[2] >= 1 && tab[3] >= 1 && tab[4] >= 0 && tab[5] >= 0) || (tab[0] >= 0 && tab[1] >= 1 && tab[2] >= 1 && tab[3] >= 1 && tab[4] >= 1 && tab[5] >= 0) || (tab[0] >= 0 && tab[1] >= 0 && tab[2] >= 1 && tab[3] >= 1 && tab[4] >= 1 && tab[5] >= 1);
			}
			
			case 21 -> // Grande suite
			{
				int[] tab = this.nbSimilaire();
				bRet = (tab[0] >= 1 && tab[1] >= 1 && tab[2] >= 1 && tab[3] >= 1 && tab[4] >= 1 && tab[5] >= 0) || (tab[0] >= 0 && tab[1] >= 1 && tab[2] >= 1 && tab[3] >= 1 && tab[4] >= 1 && tab[5] >= 1);
			}

			case 22 -> // Prime 2eme Gde suite
			{
				if (this.fiche[21][3] !=  " ")
				{
					int[] tab = this.nbSimilaire();
					bRet = (tab[0] >= 1 && tab[1] >= 1 && tab[2] >= 1 && tab[3] >= 1 && tab[4] >= 1 && tab[5] >= 0) || (tab[0] >= 0 && tab[1] >= 1 && tab[2] >= 1 && tab[3] >= 1 && tab[4] >= 1 && tab[5] >= 1);
				}
			}

			case 23 -> // Prime Gde suite 1er coup
			{
				if (this.nbLancers < 2)
				{
					int[] tab = this.nbSimilaire();
					bRet = (tab[0] >= 1 && tab[1] >= 1 && tab[2] >= 1 && tab[3] >= 1 && tab[4] >= 1 && tab[5] >= 0) || (tab[0] >= 0 && tab[1] >= 1 && tab[2] >= 1 && tab[3] >= 1 && tab[4] >= 1 && tab[5] >= 1);
				}
			}

			case 24 -> // Minor
			{
				bRet = this.totalDes() <= 10;
			}

			case 25 -> // Major
			{
				bRet = this.totalDes() >= 25;
			}

			case 26 -> // Yahtzee
			{
				bRet = this.max(this.nbSimilaire()) == 5;
			}

			case 27 -> // Prime yahtzee de 6
			{
				bRet = this.nbSimilaire()[5] == 5;
			}

			case 28 -> // Prime 2e Yahtzee
			{
				bRet = this.max(this.nbSimilaire()) == 5 && this.fiche[26][3] != " ";
			}

			case 29 -> // Prime 3e Yahtzee
			{
				bRet = this.max(this.nbSimilaire()) == 5 && this.fiche[28][3] != " ";
			}

			case 30 -> // Prime Yahtzee 1e Coup
			{
				bRet =  this.max(this.nbSimilaire()) == 5 && this.nbLancers == 1;
			}

			case 31 -> // Prime Yahtzee 2e Coup
			{
				bRet =  this.max(this.nbSimilaire()) == 5 && this.nbLancers == 2;
			}
		}
		
		return bRet;
	}

	private void appliquerPrime()
	{
		for (int lig = this.fiche.length - 1 ; lig >= 0 ; lig--)
			if (this.fiche[lig][1].length() > 5 && this.fiche[lig][1].startsWith("Prime"))
				if (this.verifierCondition(lig))
					this.fiche[lig][3] = this.fiche[lig][2];
	}

	private int[] nbSimilaire()
	{
		int[] ensSimilaire = {0, 0, 0, 0, 0, 0};
		for (De de : this.ensDe)
		{
			ensSimilaire[de.getNumero()-1]++;
		}

		return ensSimilaire;
	}

	private int totalDes()
	{
		int total = 0;
		for (De de : this.ensDe)
			total += de.getNumero();

		return total;
	}

	private int max(int[] tab)
	{
		int max = 0;
		for (int i : tab)
			if (i > max) max = i;

		return max;
	}

	public String[][] getTableau     ()      {return this.fiche;        }

	public int        getNbLancer    ()      {return this.nbLancers;    }

	public De[]       getTabDes      ()      {return this.ensDe;        }

	public String     getTitreAnnonce(int i) {return this.fiche[i+1][1];}

	public int        getScoreAnnonce(int i)
	{
		try
		{
			return Integer.parseInt(this.fiche[i+1][3]);
		}
		catch (Exception e) {}

		return -1;
	}

	public boolean partieTerminee()
	{
		for (int i = 1 ; i < this.fiche.length ; i++)
			if (!this.fiche[i][1].startsWith("Prime") && this.fiche[i][3].equals(" "))
				return false;

		return true;
	}

	public String getExplication(int indice)
	{
		return this.fiche[indice][4];
	}

	public String toString()
	{
		String sRet = "VERSION CUI NON OPERATIONNELLE";

		for (int lig = 0 ; lig < this.fiche.length ; lig++)
		{
			if (this.fiche[lig][1].length() > 5 && this.fiche[lig][1].substring(0,5).equals("Prime"))
				sRet += String.format("%2s | %28s | %-13s | %13s\n",  this.fiche[lig][0], this.fiche[lig][1], this.fiche[lig][2], this.fiche[lig][3]);
			else
				sRet += String.format("%2s | %-28s | %-13s | %13s\n", this.fiche[lig][0], this.fiche[lig][1], this.fiche[lig][2], this.fiche[lig][3]);
		}
		
		sRet += "___|______________________________|_______________|_______________" + "\n" +
		        "   | Total                        | " + String.format("%13s", this.calculScore()) + " |               \n";

		//sRet += "\n" + AffichageLigne.afficher(this.ensDe);

		sRet += "Lancer n°" + this.nbLancers + "     |     Total des dés : " + this.totalDes() + "\n" +
		        "__________________________________________________________________";
		return sRet;
	}
}