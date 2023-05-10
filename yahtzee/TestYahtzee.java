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
		boolean bPlacement = false;

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
						System.out.println("Dans quelle case ? ([R]etour)");
						placement = Clavier.lireString();
						if (placement.equals("R")) break;
						try
						{
							Integer.parseInt(placement);
							erreur = false;
						} catch (Exception e) {erreur = true;}

					} while (!y.placer(Integer.parseInt(placement)) || placement.equals("R"));
					if (!placement.equals("R")) y.lancer();
				}
				case 'Q' -> partieEnCours = false;
				default -> System.out.println("Erreur : Mauvaise commande");
			}
		}
	}
}
