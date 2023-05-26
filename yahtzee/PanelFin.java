import javax.swing.*;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.*;

public class PanelFin extends JPanel implements ActionListener
{
	private JButton    btnRejouer;
	private Controleur ctrl;

	public PanelFin(Controleur ctrl, Dimension dim)
	{
		JLabel lblScore;
		
		this.ctrl = ctrl;
		this.setPreferredSize(dim);
		this.setLayout(new GridLayout(2,1));
		this.setOpaque(true);
		this.setBackground(Controleur.BLEU);

		this.btnRejouer = new JButton("REJOUER");
		this.btnRejouer.setPreferredSize(new Dimension(150,40));
		this.btnRejouer.setFont(this.btnRejouer.getFont().deriveFont(Font.BOLD, 16.0f));

		lblScore = new JLabel("PARTIE TERMINÉE", SwingConstants.CENTER);
		lblScore.setForeground(Color.white);
		lblScore.setFont(lblScore.getFont().deriveFont(Font.BOLD, 22.0f));

		JPanel panelRejouer = new JPanel(new FlowLayout(FlowLayout.CENTER));
		panelRejouer.setOpaque(true);
		panelRejouer.setBackground(Controleur.BLEU);
		panelRejouer.add(this.btnRejouer);

		this.add(lblScore);
		this.add(panelRejouer);

		this.btnRejouer.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == this.btnRejouer)
		{
			this.ctrl.fermerFenetre();
			new Controleur();
		}
	}
}
