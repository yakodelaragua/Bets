package domain;

import java.util.Vector;

import javax.swing.table.AbstractTableModel;


public class UserAdapter extends AbstractTableModel{
	private String[] columnNames = {"Event", "Question", "Event date", "Bet(€)"};
	private Object[][] bets;
	public UserAdapter(User user) {
		Vector<Bet> apuestas = user.getApuestas();
		bets = new Object[apuestas.size()][columnNames.length];
		for(int i = 0; i < apuestas.size(); i++) {
			bets[i][0] = apuestas.get(i).getEventoAsociado();
			bets[i][1] = apuestas.get(i).getPreguntaAsociada();
			bets[i][2] = apuestas.get(i).getEventoAsociado().getEventDate();
			bets[i][3] = apuestas.get(i).getCantidad();
		}
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		return bets.length;
	}

	@Override
	public Object getValueAt(int row, int column) {
		return bets[row][column];
	}
	
	public String getColumnName(int column) {
		return columnNames[column];
	}

}
