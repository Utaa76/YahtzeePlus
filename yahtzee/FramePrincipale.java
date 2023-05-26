import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Panel;
import java.awt.Toolkit;

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
			PanelFin panelFin = new PanelFin(ctrl, this.panelD.getSize());
			this.remove(this.panelD);
			this.add(panelFin, BorderLayout.NORTH);
		}
		SwingUtilities.updateComponentTreeUI(this);
	}

	public void majExplication(String text)
	{
		this.panelB.majExplication(text);
	}
}
