import iut.algo.Clavier;

public class TestYahtzee
{
	public static void main(String[] args)
	{
		Yahtzee y = new Yahtzee();
		String placement = "";
		char saisie = ' ';
		boolean erreur = false;
		int deConserve = 0;
		boolean partieEnCours = true;
		boolean bPlacement;

		while (partieEnCours)
		{
			System.out.println(y.toString());
			System.out.println("[R]elancer\n[C}onserver un dé\n[P]lacer le score obtenu\n[Q]uitter");
			try
			{
				saisie = Clavier.lire_char();
			}
			catch (Exception e) {}
			
			switch (saisie)
			{
				case 'R' -> y.lancer();
				case 'C' ->
				{
					erreur = false;;
					do
					{
						System.out.println("Conserver quel dé ?");
						placement = Clavier.lireString();
						try
						{
							deConserve = Integer.parseInt(placement);
							erreur = false;
						} catch (Exception e) {erreur = true;}
					} while (erreur);
					y.conserver(deConserve);
				}
				case 'P' ->
				{
					erreur = false;
					do
					{
						System.out.println("Dans quelle case ?");
						placement = Clavier.lireString();
						try
						{
							Integer.parseInt(placement);
							erreur = false;
						} catch (Exception e) {erreur = true;}
						bPlacement = y.placer(Integer.parseInt(placement));
						System.out.println("bplacement = " + bPlacement);

						if (!bPlacement) System.out.println("Vous ne pouvez pas.");
					} while (erreur || !bPlacement);
					y.lancer();
				}
				case 'Q' -> partieEnCours = false;
				default -> System.out.println("Erreur : Mauvaise commande");
			}
		}
	}
}
