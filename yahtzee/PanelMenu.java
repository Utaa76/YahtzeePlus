import javax.swing.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.*;
import java.util.ArrayList;
import java.awt.BorderLayout;

public class PanelMenu extends JPanel implements ActionListener
{
	private ArrayList<JButton> ensBtn;
	private Controleur         ctrl;
	private FrameMenu          parent;

	public PanelMenu(Controleur ctrl, FrameMenu parent)
	{
		this.ctrl = ctrl;
		this.setLayout(new BorderLayout(30,30));

		this.ensBtn = new ArrayList<>();

		this.ensBtn.add(new JButton("Nouvelle partie"));
		this.ensBtn.add(new JButton("Comment jouer"));
		this.ensBtn.add(new JButton("Statistiques"));

		JPanel  panelTmp = new JPanel(new GridLayout(this.ensBtn.size(), 1, 0, 100));

		this.ensBtn.get(0).setBackground(Color.green);
		this.ensBtn.get(1).setBackground(Color.orange);
		this.ensBtn.get(2).setBackground(Color.red);

		for (JButton btn : this.ensBtn)
		{
			btn.setFont(btn.getFont().deriveFont(Font.BOLD, 25.0f));
			btn.addActionListener(this);

			panelTmp.add(btn);
		}

		this.add(new JPanel(), BorderLayout.NORTH);
		this.add(new JPanel(), BorderLayout.EAST);
		this.add(new JPanel(), BorderLayout.WEST);
		this.add(new JPanel(), BorderLayout.SOUTH);


		this.add(panelTmp, BorderLayout.CENTER);
	}

	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == this.ensBtn.get(0))
		{
			this.ctrl.commencer();
		}

		if (e.getSource() == this.ensBtn.get(1))
		{
			this.ctrl.incrementerNbPartie();
		}

		if (e.getSource() == this.ensBtn.get(2))
		{
			this.ctrl.getStats();
		}
	}
}