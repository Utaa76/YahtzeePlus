import javax.swing.*;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

public class PanelBas extends JPanel
{
	private Controleur ctrl;
	private JLabel     lblScore;
	private JLabel     lblExplication;

	public PanelBas(Controleur ctrl)
	{
		this.ctrl = ctrl;
		this.setLayout(new GridLayout(1,2,5,2));
		this.setOpaque(true);
		this.setBackground(Controleur.BLEU);

		this.lblScore       = new JLabel("Score total : " + this.ctrl.getScoreTotal(), SwingConstants.CENTER);
		this.lblScore.setOpaque(true);
		this.lblScore.setBackground(new Color(158, 217, 255));
		this.lblScore.setFont(this.lblScore.getFont().deriveFont(Font.BOLD, 22.0f));


		this.lblExplication = new JLabel("<html>Pointez une annonce pour avoir l'explication de celle-ci.</html>");
		this.lblExplication.setPreferredSize(new Dimension(0,90));
		this.lblExplication.setOpaque(true);
		this.lblExplication.setBackground(Color.white);

		this.add(this.lblExplication);
		this.add(this.lblScore);
	}

	public void majScore()
	{
		this.lblScore.setText("Score total : " + this.ctrl.getScoreTotal());
	}

	public void majExplication(String text)
	{
		String explication = "<html>";

		for (char c : text.toCharArray())
		{
			if (c == '\n')
				explication += "<br>";
			else
				explication += c;
		}

		explication += "</html>";

		this.lblExplication.setText(explication);
	}
}
