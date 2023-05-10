public class Controleur
{
	private Yahtzee         metier;
	private FramePrincipale vue;

	public Controleur()
	{
		this.metier = new Yahtzee();
		this.vue    = new FramePrincipale(this);
	}

	public String getImage(De de)
	{
		String sRet = "./images/de" + de.getNumero();

		if (de.estConserver()) sRet += "_s";

		return sRet;
	}

	public String[][] getTableau()
	{
		return this.metier.getTableau();
	}

	public static void main(String[] args)
	{
		new Controleur();
	}
}
