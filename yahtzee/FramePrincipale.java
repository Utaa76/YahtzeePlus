import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.List;
import java.awt.Panel;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.*;

public class FramePrincipale extends JFrame
{
	private PanelDes   panelD;
	private PanelFiche panelF;
	private PanelBas   panelB;
	private Controleur ctrl;

	public FramePrincipale(Controleur ctrl)
	{
		this.ctrl   = ctrl;
		this.setSize(700, 900);

		this.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width  - getSize().width) / 2, (Toolkit.getDefaultToolkit().getScreenSize().height - getSize().height) / 2);
		this.setTitle("YahtzeePlus");
		this.setLayout(new BorderLayout());

		this.panelD = new PanelDes  (ctrl);
		this.panelF = new PanelFiche(ctrl);
		this.panelB = new PanelBas  (ctrl);

		this.add(this.panelD, BorderLayout.NORTH );
		this.add(this.panelF, BorderLayout.CENTER);
		this.add(this.panelB, BorderLayout.SOUTH);

		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void majIHM()
	{
		if (!this.ctrl.partieTerminee())
		{
			this.panelD.majIHM();
			this.panelF.majIHM();
			this.panelB.majScore();
		}
		else
		{			
			this.ctrl.incrementerNbPartie();
			this.panelD.majIHM();
			this.panelF.majIHM();
			this.panelB.majScore();
			
			if (this.ctrl.getScoreTotal() > this.ctrl.getBestScore())
			{
				this.ctrl.remplacerStat(1, this.ctrl.getScoreTotal());
				System.out.println("Nouveau record !");
			}

			String[] options = {"Rejouer", "Menu"};
			int choix = JOptionPane.showOptionDialog(null, "La partie est terminée", "Fin de partie", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
			
			if (choix == JOptionPane.YES_OPTION)
			{
				this.ctrl.reinitialiser();
			}
			else if  (choix == JOptionPane.NO_OPTION)
			{
				this.dispose();
				new Controleur();
			}
			else
			{
				this.dispose();
			}
		}

		SwingUtilities.updateComponentTreeUI(this);
	}

	public void majExplication(String text)
	{
		this.panelB.majExplication(text);
	}
}
