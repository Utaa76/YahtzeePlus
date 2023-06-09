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

	public PanelDes(Controleur ctrl)
	{
		this.tabDes    = new JLabel[5];
		this.ctrl = ctrl;
		this.btnLancer = new JButton("Lancer (" + this.ctrl.getNbLancerRestant() + ")");
		this.btnRedem = new JButton("   Menu   ");
		this.setOpaque(true);
		this.setBackground(new Color(20,50,150));
		this.setLayout(new BorderLayout());


		for (int i = 0 ; i < this.tabDes.length ; i++)
			try
			{
				this.tabDes[i] = new JLabel(new ImageIcon(ImageIO.read(Controleur.class.getResourceAsStream(this.ctrl.getImage(i)))/*this.ctrl.getImage(i)*/));
			} catch (IOException ioe) {}
		JPanel panelTmp = new JPanel();
		panelTmp.setOpaque(false);
		
		for (JLabel lbl : tabDes)
		{
			lbl.addMouseListener(this);
			panelTmp.add(lbl);
		}

		this.add(this.btnRedem,  BorderLayout.WEST);
		this.add(panelTmp,       BorderLayout.CENTER);
		this.add(this.btnLancer, BorderLayout.EAST);

		this.btnLancer.addActionListener(this);
		this.btnRedem .addActionListener(this);
	}

	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == this.btnLancer)
			this.ctrl.lancer();

		if (e.getSource() == this.btnRedem)
		{
			//this.ctrl.reinitialiser();
			this.ctrl.fermerFenetreVue();
			new Controleur();
		}
			
		this.majIHM();
	}
	
	public void mouseClicked(MouseEvent e)
	{
		for (int i = 0 ; i < this.tabDes.length ; i++)
		{
			if (e.getSource() == this.tabDes[i])
				this.ctrl.conserver(i);
		}

		this.majIHM();
	}

	public void mouseEntered  (MouseEvent e){}
	public void mouseExited   (MouseEvent e){}
	public void mousePressed  (MouseEvent e){}
	public void mouseReleased (MouseEvent e){}

	public void majIHM()
	{
		for (int i = 0 ; i < this.tabDes.length ; i++)
			try
			{
				this.tabDes[i].setIcon(new ImageIcon(ImageIO.read(Controleur.class.getResourceAsStream(this.ctrl.getImage(i)))));
			} catch (IOException ioe) {}
			
		this.btnLancer.setText("Lancer (" + this.ctrl.getNbLancerRestant() + ")");
	}
}