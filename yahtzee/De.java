public class De
{
	public static final int[] ENS_NUMEROS = {1, 2, 3, 4, 5, 6};

	private int     id;
	private int     numero;
	private boolean estConserve;

	public De(int id)
	{
		this.id     = id;
		this.numero = 0;
	}

	public int getNumero()
	{
		return this.numero;
	}

	public void setNumero(int numero)
	{
		this.numero = numero;
	}

	public void lancer()
	{
		if (!estConserve)
			this.numero = (int)(Math.random() * 6 + 1);
	}

	public void setConserver(boolean conserve)
	{
		this.estConserve = conserve;
	}

	public boolean estConserver()
	{
		return this.estConserve;
	}

	public String toString()
	{
		String sRet = "";

		if (this.id > 0)
			sRet = "    " + this.id + "    \n";
		else
			sRet = "         \n";

		switch (numero)
		{
			case 1 ->
			{
				sRet += " ------- " + "\n" +
				        "|       |" + "\n" +
				        "|   *   |" + "\n" +
				        "|       |" + "\n" +
				        " ------- " + "\n";
			}
			case 2 ->
			{
				sRet += " ------- " + "\n" +
				        "|     * |" + "\n" +
				        "|       |" + "\n" +
				        "| *     |" + "\n" +
				        " ------- " + "\n";
			}
			case 3 ->
			{
				sRet += " ------- " + "\n" +
				        "|     * |" + "\n" +
				        "|   *   |" + "\n" +
				        "| *     |" + "\n" +
				        " ------- " + "\n";
			}
			case 4 ->
			{
				sRet += " ------- " + "\n" +
				        "| *   * |" + "\n" +
				        "|       |" + "\n" +
				        "| *   * |" + "\n" +
				        " ------- " + "\n";
			}
			case 5 ->
			{
				sRet += " ------- " + "\n" +
				        "| *   * |" + "\n" +
				        "|   *   |" + "\n" +
				        "| *   * |" + "\n" +
				        " ------- " + "\n";
			}
			case 6 ->
			{
				sRet += " ------- " + "\n" +
				        "| *   * |" + "\n" +
				        "| *   * |" + "\n" +
				        "| *   * |" + "\n" +
				        " ------- " + "\n";
			}
		}

		if (this.estConserve)
			sRet += "    ^    ";
		else
			sRet += "         ";

		return sRet;
	}
}
