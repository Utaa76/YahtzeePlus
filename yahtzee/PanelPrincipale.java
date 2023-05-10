import javax.swing.*;
import java.awt.Dimension;

public class PanelPrincipale extends JPanel
{
	private JTable     tblGrille;
	private Controleur ctrl;

	public PanelPrincipale(Controleur ctrl)
	{
		this.tblGrille = new JTable( new GrilleDonneesModel(ctrl) );
		this.tblGrille.setPreferredSize(new Dimension(800,600));

		this.ctrl = ctrl;

		this.add(this.tblGrille);
	}
}
