import javax.swing.*;

import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.*;
import java.util.Arrays;

public class PanelFiche extends JPanel implements MouseListener
{
	private Controleur ctrl;
	private JLabel[]   tabLblTitre;
	private JLabel[]   tabLblScore;

	public PanelFiche(Controleur ctrl)
	{
		this.ctrl = ctrl;

		this.setLayout(new GridLayout(16, 2, 5, 2));
		this.setOpaque(true);
		this.setBackground(new Color(20,50,150));
		
		this.tabLblTitre = new JLabel[31];
		this.tabLblScore = new JLabel[31];

		for (int i = 0 ; i < this.tabLblTitre.length ; i++)
		{
			this.tabLblTitre[i] = new JLabel(this.ctrl.getTitreAnnonce(i));

			if (this.ctrl.getScoreAnnonce(i) == -1)
				this.tabLblScore[i] = new JLabel("", SwingConstants.CENTER);
			else
				this.tabLblScore[i] = new JLabel("" + this.ctrl.getScoreAnnonce(i), SwingConstants.CENTER);

			//this.tabLblTitre[i].setBorder(BorderFactory.createLineBorder(Color.black));
			//this.tabLblScore[i].setBorder(BorderFactory.createLineBorder(Color.black));
		}

		for (JLabel lbl : this.tabLblTitre)
		{
			if (lbl.getText().startsWith("Prime"))
			{
				Font texte       = lbl.getFont();
				Font texteItalic = texte.deriveFont(Font.ITALIC, 12.0f);
				lbl.setFont(texteItalic);
			}
			else
			{
				Font texte       = lbl.getFont();
				Font texteGros   = texte.deriveFont(Font.BOLD, 14.0f);
				lbl.setFont(texteGros);
			}

			lbl.setOpaque(true);
			lbl.setBackground(Color.white);
		}

		for (JLabel lbl : this.tabLblScore)
		{
			Font score = lbl.getFont();
			Font scoreAmeliore = score.deriveFont(Font.BOLD, 20.0f);
			lbl.setFont(scoreAmeliore);

			lbl.setOpaque(true);
			lbl.setBackground(Color.white);

			lbl.addMouseListener(this);
		}

		for (int i = 0 ; i < this.tabLblTitre.length/2+1 ; i++)
		{
			this.add(this.tabLblTitre[i]);
			this.add(this.tabLblScore[i]);

			if (i < 15)
			{
				this.add(this.tabLblTitre[i+16]);
				this.add(this.tabLblScore[i+16]);
			}
		}
	}

	public void mouseEntered  (MouseEvent e){}
	public void mouseExited   (MouseEvent e){}
	public void mousePressed  (MouseEvent e){}
	public void mouseReleased (MouseEvent e){}

	public void mouseClicked  (MouseEvent e)
	{
		for (int i = 0 ; i < this.tabLblScore.length ; i++)
		{
			if (e.getSource() == this.tabLblScore[i])
			{
				this.ctrl.placer(i);
			}
		}

		for (String[] s : this.ctrl.getTableau())
			System.out.println(Arrays.toString(s));
			
		this.ctrl.majIHM();
	}

	public void majIHM()
	{
		for (int i = 0 ; i < this.tabLblScore.length ; i++)
		{
			if (this.ctrl.getScoreAnnonce(i) == -1)
				this.tabLblScore[i].setText("");
			else
				this.tabLblScore[i].setText("" + this.ctrl.getScoreAnnonce(i));
		}
	}
}