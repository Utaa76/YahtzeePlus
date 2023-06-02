import javax.swing.*;
import java.awt.event.*;
import java.awt.BorderLayout;
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

		this.setSize(600, 200);
		this.setTitle("Statistiques");
		this.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width  - getSize().width) / 2, (Toolkit.getDefaultToolkit().getScreenSize().height - getSize().height) / 2);
		
		this.setLayout(new BorderLayout());

		this.btnMenu = new JButton("MENU");

		tabIntitule[0] = new JLabel("Meilleur score"             , SwingConstants.RIGHT);
		tabIntitule[1] = new JLabel("Nombre de parties terminées", SwingConstants.RIGHT);

		tabStats[0] = new JLabel(this.ctrl.getStat(1, 1), SwingConstants.CENTER);
		tabStats[1] = new JLabel(this.ctrl.getStat(0, 1), SwingConstants.CENTER);

		JPanel panelTmp = new JPanel(new GridLayout(2,2));
		for (int i = 0 ; i < 4 ; i++)
		{
			if (i%2 == 0) panelTmp.add(tabIntitule[i/2]);
			if (i%2 == 1) panelTmp.add(tabStats[i/2]);
		}

		this.add(this.btnMenu, BorderLayout.NORTH);
		this.add(panelTmp);

		this.btnMenu.addActionListener(this);

		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void actionPerformed(ActionEvent e)
	{
		this.ctrl.fermerFenetreStats();
		new Controleur();
	}
}