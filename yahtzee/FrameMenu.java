import java.awt.Toolkit;

import javax.swing.*;

public class FrameMenu extends JFrame
{
	private PanelMenu  panelM;
	private Controleur ctrl;

	public FrameMenu(Controleur ctrl)
	{
		this.setSize(1000, 700);

		this.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width  - getSize().width) / 2, (Toolkit.getDefaultToolkit().getScreenSize().height - getSize().height) / 2);
		this.setTitle("YahtzeePlus");

		this.panelM = new PanelMenu(ctrl, this);

		this.add(this.panelM);

		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
