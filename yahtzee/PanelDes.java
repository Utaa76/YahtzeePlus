import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.*;
import java.io.IOException;

public class PanelDes extends JPanel implements MouseListener, ActionListener
{
	private JLabel[]   tabDes;
	private JButton    btnLancer;
	private Controleur ctrl;
	private JButton    btnRedem;

	private int        animationCount;

	public PanelDes(Controleur ctrl)
	{
		this.tabDes    = new JLabel[5];
		this.ctrl = ctrl;
		this.btnLancer = new JButton("Lancer (" + this.ctrl.getNbLancerRestant() + ")");
		this.btnRedem = new JButton("   Menu   ");
		this.setOpaque(true);
		this.setBackground(Controleur.BLEU);
		this.setLayout(new BorderLayout());

		for (int i = 0 ; i < this.tabDes.length ; i++)
			try
			{
				this.tabDes[i] = new JLabel(new ImageIcon(ImageIO.read(Controleur.class.getResourceAsStream("/images/de_vide.png"))));
			}
			catch (Exception e) {}

		JPanel panelTmp = new JPanel();
		panelTmp.setOpaque(false);
		
		for (JLabel lbl : tabDes)
		{
			try
			{
				lbl.addMouseListener(this);
				panelTmp.add(lbl);
			}
			catch (Exception e) {}
		}

		this.add(this.btnRedem,  BorderLayout.WEST);
		this.add(panelTmp,       BorderLayout.CENTER);
		this.add(this.btnLancer, BorderLayout.EAST);

		this.btnLancer.addActionListener(this);
		this.btnRedem .addActionListener(this);
	}

	public void initierDes()
	{
		for (int i = 0 ; i < this.tabDes.length ; i++)
			try
			{
				this.tabDes[i].setIcon(new ImageIcon(ImageIO.read(Controleur.class.getResourceAsStream("/images/de_vide.png"))));
			}
			catch (Exception e) {}
	}

	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == this.btnLancer)
		{
			this.animationCount = 0;
			this.animationTimer.start();

			Timer delayTimer = new Timer(800, new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					PanelDes.this.ctrl.lancer();
					PanelDes.this.majIHM(true);
				}
			});

			// this.ctrl.lancer();
			// this.majIHM(true);

			delayTimer.setRepeats(false);
			delayTimer.start();
		}

		if (e.getSource() == this.btnRedem)
		{
			//this.ctrl.reinitialiser();
			this.ctrl.fermerFenetreVue();
			new Controleur();
		}
			
	}

	private Timer animationTimer = new Timer(50, new ActionListener()
	{
		public void actionPerformed(ActionEvent e) 
		{
			if (PanelDes.this.animationCount < 10)
			{
				PanelDes.this.animationCount++;
				boolean desArretes = true;

				for (int i = 0 ; i < PanelDes.this.tabDes.length ; i++)
				{
					if (!PanelDes.this.ctrl.getDe(i).estConserver() && PanelDes.this.ctrl.getNbLancerRestant() > 0)
					{
						String image = "/images/de" + ((int)(Math.random() * 6) + 1) + ".png";

						try
						{
							PanelDes.this.tabDes[i].setIcon(new ImageIcon(ImageIO.read(Controleur.class.getResourceAsStream(image))));
						} catch (Exception ex) {}

						desArretes = false;
					}
				}

				if (desArretes) ((Timer) e.getSource()).stop();
			}
		}
	});
	
	public void mouseClicked(MouseEvent e)
	{
		for (int i = 0 ; i < this.tabDes.length ; i++)
		{
			if (e.getSource() == this.tabDes[i])
				this.ctrl.conserver(i);
		}

		this.majIHM(true);
	}

	public void mouseEntered  (MouseEvent e){}
	public void mouseExited   (MouseEvent e){}
	public void mousePressed  (MouseEvent e){}
	public void mouseReleased (MouseEvent e){}

	public void majIHM(boolean lancerDes)
	{
		if (lancerDes)
			for (int i = 0 ; i < this.tabDes.length ; i++)
				try
				{
					this.tabDes[i].setIcon(new ImageIcon(ImageIO.read(Controleur.class.getResourceAsStream(this.ctrl.getImage(i)))));
				} catch (IOException ioe) {}
		
		this.btnLancer.setText("Lancer (" + this.ctrl.getNbLancerRestant() + ")");
	}
}