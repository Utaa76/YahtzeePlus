import javax.swing.*;
import java.awt.Toolkit;
import java.awt.BorderLayout;
import java.awt.event.*;

public class FrameExplication extends JFrame implements ActionListener
{
	private Controleur ctrl;
	private JButton    btnMenu;

	public FrameExplication(Controleur ctrl)
	{
		this.ctrl = ctrl;
		this.setSize(700, 500);
		this.setTitle("Règles du jeu");
		this.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width  - getSize().width) / 2, (Toolkit.getDefaultToolkit().getScreenSize().height - getSize().height) / 2);
		this.setLayout(new BorderLayout());

		JLabel lbl   = new JLabel("<html><h1> LE YAHTZEE </h1>Le Yahtzee peut se jouer seul ou en groupe. Mais pour plus de plaisir, il est préférable de le pratiquer en famille ou entre amis.<br>Il s'agit ici d'une version évoluée avec quelques combinaisons en plus (Minor, Maximum, Twenty, Prime du Yahtzee, ...)<br>Chacun des joueurs doit lancer trois fois ces dés lors d'un tour. Le tour doit permettre de réaliser une figure, soit ce sera 5 dés identiques, soit il devra y avoir une paire ou un triplet pour engranger des points, etc. Dans le cas contraire, c'est-à -dire lorsque ces dés ne représentent aucune figure identique, le joueur peut valider une annonce à 0 points et ne marque alors aucun point lors de son tour.<br>A noter que le joueur aura le libre choix de jeter un, quelques-uns ou tous les dés à son deuxième et troisième lancer s'il le souhaite. La figure présentée sera notée. Le gagnant sera celui qui recueillera la plus grande somme de points.<br>Sur cette version, vous ne pouvez jouer qu'à un seul joueur mais rien ne vous empêche de jouer ensemble en lançant plusieurs parties.<br>Le jeu est fini uniquement lorsque la fiche de score est entièrement remplie.<br><br><h1>Fonctionnement</h1>Pour y jouer, il suffit de cliquer sur \"Nouvelle partie\". Cela ouvrira une nouvelle fenêtre où vous trouverez la liste des combinaisons à réaliser, les différentes primes associées, les dés en haut, les explications de chaque figure au passage du titre de celle-ci ainsi que votre score total. Le premier lancer se fait automatiquement. Si vous souhaitez conserver un dé pour le prochain lancer, vous n'avez qu'à cliquer dessus. Pour valider votre score, vous n'avez qu'à cliquer dans la case à côté de la figure demandée. Au passage de la souris, le jeu vous indiquera votre score si vous cliquez dans la case.</html>");
		this.btnMenu = new JButton("Menu");

		this.add(lbl,          BorderLayout.CENTER);
		this.add(this.btnMenu, BorderLayout.NORTH);

		this.btnMenu.addActionListener(this);

		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void actionPerformed(ActionEvent e)
	{
		this.ctrl.fermerFenetreExplication();
		new Controleur();
	}
}
