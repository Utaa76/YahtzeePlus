import javax.swing.*;

import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.*;
import java.util.Arrays;

public class PanelFiche extends JPanel implements MouseListener
{
	public static final Color GRIS_CLAIR = new Color(230,230,230);

	private Controleur ctrl;
	private JLabel[]   tabLblTitre;
	private JLabel[]   tabLblScore;

	public PanelFiche(Controleur ctrl)
	{
		this.ctrl = ctrl;

		this.setLayout(new GridLayout(16, 2, 5, 3));
		this.setOpaque(true);
		this.setBackground(Controleur.BLEU);
		
		this.tabLblTitre = new JLabel[31];
		this.tabLblScore = new JLabel[31];

		for (int i = 0 ; i < this.tabLblTitre.length ; i++)
		{
			this.tabLblTitre[i] = new JLabel(this.ctrl.getTitreAnnonce(i), SwingConstants.CENTER);
			this.tabLblTitre[i].setOpaque(true);
			this.tabLblTitre[i].setBackground(Color.white);

			if (this.ctrl.getScoreAnnonce(i) == -1)
				this.tabLblScore[i] = new JLabel("", SwingConstants.CENTER);
			else
				this.tabLblScore[i] = new JLabel("" + this.ctrl.getScoreAnnonce(i), SwingConstants.CENTER);

			this.tabLblScore[i].setOpaque(true);
			this.tabLblScore[i].setBackground(Color.white);
		}

		for (JLabel lbl : this.tabLblTitre)
		{
			if (lbl.getText().startsWith("Prime"))
			{
				Font texte       = lbl.getFont();
				Font texteItalic = texte.deriveFont(Font.ITALIC, 12.0f);
				lbl.setFont(texteItalic);
				lbl.setBackground(PanelFiche.GRIS_CLAIR);
			}
			else
			{
				Font texte       = lbl.getFont();
				Font texteGros   = texte.deriveFont(Font.BOLD, 14.0f);
				lbl.setFont(texteGros);
			}

			lbl.addMouseListener(this);
		}

		for (JLabel lbl : this.tabLblScore)
		{
			Font score = lbl.getFont();
			Font scoreAmeliore = score.deriveFont(Font.BOLD, 20.0f);
			lbl.setFont(scoreAmeliore);

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

		for (int i = 0 ; i < this.tabLblTitre.length ; i++)
		{
			if (this.tabLblTitre[i].getText().startsWith("Prime"))
			{
				this.tabLblTitre[i].setBackground(GRIS_CLAIR);
				//this.tabLblScore[i].setBackground(GRIS_CLAIR);
			}
		}
	}

	public void mouseEntered  (MouseEvent e)
	{
		for (int i = 0 ; i < this.tabLblScore.length ; i++)
		{
			if (e.getSource() == this.tabLblScore[i] && !this.tabLblTitre[i].getText().startsWith("Prime") && this.tabLblScore[i].getText() == "")
			{
				this.setCursor(new Cursor(Cursor.HAND_CURSOR));

				int scoreSiClick = this.ctrl.getScoreSelonAnnonce(i);

				if (scoreSiClick > -1)
				{
					this.tabLblScore[i].setText("" + scoreSiClick);
					this.tabLblScore[i].setForeground(Color.lightGray);
				}
			}
		
			if (e.getSource() == this.tabLblTitre[i])
			{
				this.ctrl.majExplication(this.ctrl.getExplication(i));
			}
		}
	}
	public void mouseExited   (MouseEvent e)
	{
		if (Arrays.asList(this.tabLblScore).contains((JLabel)e.getSource()))
		{
			this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			this.majIHM();
		}

		if (Arrays.asList(this.tabLblTitre).contains((JLabel)e.getSource()))
		{
			this.ctrl.majExplication("Pointez une annonce pour avoir l'explication de celle-ci.");
		}
	}
	public void mousePressed  (MouseEvent e){}
	public void mouseReleased (MouseEvent e){}

	public void mouseClicked  (MouseEvent e)
	{
		for (int i = 0 ; i < this.tabLblScore.length ; i++)
		{
			if (e.getSource() == this.tabLblScore[i])
			{
				this.ctrl.placer(i);
				this.tabLblScore[i].setForeground(Color.black);
			}
		}

		/*for (String[] s : this.ctrl.getTableau())
			System.out.println(Arrays.toString(s));*/
			
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

			if (this.tabLblScore[i].getText() != "")
			{
				this.tabLblScore[i].setBackground(PanelFiche.GRIS_CLAIR);
				this.tabLblTitre[i].setBackground(PanelFiche.GRIS_CLAIR);
			}
		}
	}
}