import javax.swing.*;

public class FramePrincipale extends JFrame
{
	private PanelPrincipale panelP;
	private Controleur      ctrl;

	public FramePrincipale(Controleur ctrl)
	{
		this.ctrl   = ctrl;
		this.setSize(1200, 800);
		this.setName("YahtzeePlus");

		this.panelP = new PanelPrincipale(ctrl);

		this.add(panelP);

		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public String getImage(De de)
	{
		return this.ctrl.getImage(de);
	}
}
