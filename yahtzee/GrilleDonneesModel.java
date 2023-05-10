import java.util.Arrays;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class GrilleDonneesModel extends AbstractTableModel
{
	private Controleur ctrl;

	private String[]   tabEntetes;
	private String[][] tabDonnees;

	public GrilleDonneesModel (Controleur ctrl)
	{
		this.ctrl = ctrl;

		Object[][] fiche = new Object[this.ctrl.getTableau().length-1][this.ctrl.getTableau()[0].length];

		this.tabDonnees = new String[this.ctrl.getTableau().length-1][this.ctrl.getTableau()[0].length];


		for (int i = 1 ; i < this.ctrl.getTableau().length ; i++)
			this.tabDonnees[i-1] = this.ctrl.getTableau()[i];

		this.tabEntetes = this.ctrl.getTableau()[0];

		System.out.println(this.getColumnName(2));
	}

	public int    getColumnCount()                 { return this.tabEntetes.length;      }
	public int    getRowCount   ()                 { return this.tabDonnees.length;      }
	public String getColumnName (int col)          { return this.tabEntetes[col];        }
	public Object getValueAt    (int row, int col) { return this.tabDonnees[row][col];   }
	public Class  getColumnClass(int c)            { return getValueAt(0, c).getClass(); }

	public boolean isCellEditable(int row, int col)
	{
		return false;
	}

	/*/
	public void setValueAt(Object value, int row, int col)
	{
		boolean bRet;

		if ( col == 3 )
		{
			bRet = this.ctrl.majPremiumClient ( row, (Boolean) value );
			if ( bRet )
			{
				this.tabDonnees[row][col] = value;
				this.fireTableCellUpdated(row, col);
			}
		}

	}*/
}