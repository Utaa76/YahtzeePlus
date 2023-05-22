import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Toolkit;

import javax.swing.*;

public class FramePrincipale extends JFrame
{
	private PanelDes   panelD;
	private PanelFiche panelF;
	private Controleur ctrl;

	public FramePrincipale(Controleur ctrl)
	{
		this.ctrl   = ctrl;
		this.setSize(700, 850);

		this.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width  - getSize().width) / 2, (Toolkit.getDefaultToolkit().getScreenSize().height - getSize().height) / 2);
		this.setName("YahtzeePlus");
		this.setLayout(new BorderLayout());

		this.panelD = new PanelDes  (ctrl);
		this.panelF = new PanelFiche(ctrl);

		this.add(this.panelD, BorderLayout.NORTH);
		this.add(this.panelF, BorderLayout.CENTER);

		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//this.pack();
	}

	public void majIHM()
	{
		this.panelD.majIHM();
		this.panelF.majIHM();
	}
}
