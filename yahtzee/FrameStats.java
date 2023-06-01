import javax.swing.*;
import java.awt.event.*;
import java.io.File;
import java.util.*;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;

public class FrameStats extends JFrame implements ActionListener
{
	private JButton    btnMenu;
	private Controleur ctrl;

	public FrameStats(Controleur ctrl)
	{
		this.ctrl = ctrl;

		JLabel[] tabIntitule = new JLabel[2];
		JLabel[] tabStats    = new JLabel[2];

		this.setSize(800, 500);
		this.setTitle("Statistiques");
		this.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width  - getSize().width) / 2, (Toolkit.getDefaultToolkit().getScreenSize().height - getSize().height) / 2);
		
		this.setLayout(new GridLayout(3,3, 0, 100));

		this.btnMenu = new JButton("MENU");

		tabIntitule[0] = new JLabel("Meilleur score"             , SwingConstants.CENTER);
		tabIntitule[1] = new JLabel("Nombre de parties terminées", SwingConstants.CENTER);

		tabStats[0] = new JLabel("" + this.getStat(1), SwingConstants.CENTER);
		tabStats[1] = new JLabel("" + this.getStat(0), SwingConstants.CENTER);

		this.add(this.btnMenu);
		this.add(new JLabel());
		this.add(new JLabel());

		for (int i = 0 ; i < 6 ; i++)
		{
			if (i%3 == 0) this.add(tabIntitule[i/3]);
			if (i%3 == 1) this.add(tabStats[i/3]);
			if (i%3 == 2) this.add(new JLabel());
		}

		this.btnMenu.addActionListener(this);

		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void actionPerformed(ActionEvent e)
	{
		this.ctrl.fermerFenetreStats();
		new Controleur();
	}

	public int getStat(int indice)
	{
		ArrayList<String> ensLigne = new ArrayList<>();

		try
		{
			Scanner sc = new Scanner(new File("./stats.data"));

			while (sc.hasNextLine())
				ensLigne.add(sc.nextLine());

			if (indice < 0 || indice >= ensLigne.size()) return -1;
			
			String[] ligneChoisie = ensLigne.get(indice).split("\t");

			/*for (String mot : ligneChoisie)
				System.out.println(mot);*/

			return Integer.parseInt(ligneChoisie[1]);
		}
		catch (Exception e) {}

		return -2;
	}
}