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
		this.setBackground(Controleur.BLEU);

		this.ensBtn = new ArrayList<>();

		this.ensBtn.add(new JButton("Nouvelle partie"));
		this.ensBtn.add(new JButton("Comment jouer"));
		this.ensBtn.add(new JButton("Statistiques"));

		JPanel  panelTmp = new JPanel(new GridLayout(this.ensBtn.size(), 1, 0, 100));
		panelTmp.setOpaque(false);

		this.ensBtn.get(0).setBackground(new Color(158, 217, 255));
		this.ensBtn.get(1).setBackground(Color.white);
		this.ensBtn.get(2).setBackground(Color.lightGray);

		for (JButton btn : this.ensBtn)
		{
			btn.setFont(btn.getFont().deriveFont(Font.BOLD, 25.0f));
			btn.addActionListener(this);

			panelTmp.add(btn);
		}

		JPanel[] panelVide = new JPanel[4];

		for (int cpt = 0 ; cpt < 4 ; cpt++)
		{
			panelVide[cpt] = new JPanel();
			panelVide[cpt].setBackground(Controleur.BLEU);
			//panelVide.setOpaque(false);
		}

		this.add(panelVide[0], BorderLayout.NORTH);
		this.add(panelVide[1], BorderLayout.EAST);
		this.add(panelVide[2], BorderLayout.WEST);
		this.add(panelVide[3], BorderLayout.SOUTH);


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
			this.ctrl.getExplication();
		}

		if (e.getSource() == this.ensBtn.get(2))
		{
			this.ctrl.getStats();
		}
	}
}