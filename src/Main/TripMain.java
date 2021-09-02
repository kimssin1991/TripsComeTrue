package Main;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.AbstractTableModel;

import DAO.TripDAO;
import DTO.TripDTO;

public class TripMain extends JPanel implements ActionListener {
	JTextField tfTripNum, tfTripName, tfTripPay, tfTripDays, tfTripStartDate, tfTripFinishDate;
	JTextArea taTripInfo, taTripSpot;

	JButton bTripInsert, bTripUpdate, bTripDelete, bSearch, bClear;
	JComboBox comTripSearch;
	JTextField tfTripSearch;
	JTable tableTrip;
	JScrollPane sp1, sp2;
	TripDAO dao;
	TripTableModel tmTrip;
	String searchTrip[];

	public TripMain() {
		newObject();
		addLayout();
		eventProc();
		connection();
	}
	
	void connection() {
		try {
			dao = new TripDAO();
			System.out.println("DB ���� ����");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "DB ���� ����: " + e.getMessage());
		}
	}

	void newObject() {
		tfTripNum = new JTextField(15);
		tfTripName = new JTextField(15);
		tfTripPay = new JTextField(15);
		tfTripDays = new JTextField(15);
		tfTripStartDate = new JTextField(15);
		tfTripFinishDate = new JTextField(15);

		taTripInfo = new JTextArea(15, 3);
		taTripSpot = new JTextArea(15, 3);

		bTripInsert = new JButton("��	��");
		bTripUpdate = new JButton("�� 	��");
		bTripDelete = new JButton("��	��");
		bSearch = new JButton("��    ��");
		bClear = new JButton("ȭ�� ����");

		searchTrip = new String[] { "��Ű�� �̸�", "��Ű�� ��ȣ", "�� �� �� ¥" };
		comTripSearch = new JComboBox(searchTrip);
		tfTripSearch = new JTextField(15);

		tmTrip = new TripTableModel();
		tableTrip = new JTable(tmTrip);
	}

	void addLayout() {
		// west ���� : ���� �Է� �� ����
		JPanel pWest = new JPanel();
		// ���� �����Է��ϴ� �κ�
		JPanel pWestNorth = new JPanel();
		JPanel pWestNorthUp = new JPanel();
		pWestNorthUp.setLayout(new GridLayout(6, 2));

		pWestNorthUp.add(new JLabel("                       �� Ű ��     �� ȣ "));
		pWestNorthUp.add(tfTripNum);
		pWestNorthUp.add(new JLabel("                       �� Ű ��     �� �� "));
		pWestNorthUp.add(tfTripName);
		pWestNorthUp.add(new JLabel("                       ��                     �� "));
		pWestNorthUp.add(tfTripPay);
		pWestNorthUp.add(new JLabel("                       ��                     �� "));
		pWestNorthUp.add(tfTripDays);
		pWestNorthUp.add(new JLabel("                       ��    ��    ��    ¥ "));
		pWestNorthUp.add(tfTripStartDate);
		pWestNorthUp.add(new JLabel("                       ��    ��    ��    ¥ "));
		pWestNorthUp.add(tfTripFinishDate);

		JPanel pWestNorthCenter = new JPanel();
		pWestNorthCenter.setLayout(new BorderLayout());
		pWestNorthCenter.add("Center", taTripSpot);
		sp1 = new JScrollPane(taTripSpot);
		pWestNorthCenter.add(sp1);
		pWestNorthCenter.setBorder(new TitledBorder("�� �� ��"));

		JPanel pWestNorthDown = new JPanel();
		pWestNorthDown.setLayout(new BorderLayout());
		pWestNorthDown.add("Center", taTripInfo);
		sp2 = new JScrollPane(taTripInfo);
		pWestNorthDown.add(sp2);
		pWestNorthDown.setBorder(new TitledBorder("��        ��"));

		pWestNorth.setBorder(new TitledBorder(" ��Ű�� ���� �Է� "));

		pWestNorth.setLayout(new BorderLayout());
		pWestNorth.add("North", pWestNorthUp);
		pWestNorth.add("Center", pWestNorthCenter);
		pWestNorth.add("South", pWestNorthDown);

		JPanel pWestSouth = new JPanel();

		JPanel pWestSouthDown = new JPanel();
		pWestSouthDown.setLayout(new GridLayout(1, 3));
		pWestSouthDown.add(bTripInsert);
		pWestSouthDown.add(bTripUpdate);
		pWestSouthDown.add(bTripDelete);

		pWestSouth.setLayout(new GridLayout(1, 1));
		pWestSouth.add(pWestSouthDown);

		pWest.setLayout(new BorderLayout());
		pWest.add("Center", pWestNorth);
		pWest.add("South", pWestSouth);

		// east ���� : ���� �˻��κ�
		JPanel pEast = new JPanel();
		JPanel pEastNorth = new JPanel();
		pEastNorth.add(comTripSearch);
		pEastNorth.add(tfTripSearch);
		pEastNorth.add(bSearch);
		pEastNorth.setBorder(new TitledBorder(" ��Ű�� �˻� "));

		JPanel pEastCenter = new JPanel();
		pEastCenter.setLayout(new BorderLayout());
		pEastCenter.add("Center", new JScrollPane(tableTrip));

		JPanel pEastSouth = new JPanel();
		pEastSouth.setLayout(new BorderLayout());
		pEastSouth.add("South", bClear);

		pEast.setLayout(new BorderLayout());
		pEast.add("North", pEastNorth);
		pEast.add("Center", pEastCenter);
		pEast.add("South", pEastSouth);

		// ��ü ���̱�
		setLayout(new GridLayout(1, 2));
		add(pWest);
		add(pEast);
	}


	void eventProc() {
		bSearch.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				search();
			}
		});

		bClear.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				clearScreen();
			}
		});

		tableTrip.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				select();
			}
		});
	}

	public void actionPerformed(ActionEvent ev) {
		bTripInsert.addActionListener(this);
		bTripUpdate.addActionListener(this);
		bTripDelete.addActionListener(this);
		tfTripSearch.addActionListener(this);
		
		Object o = ev.getSource();

		if (o == bTripInsert) {
			insert();
		} else if (o == bTripUpdate) {
			update();
		} else if (o == bTripDelete) {
			delete();
		} else if (o == tfTripSearch) {
			search();
		}
	};

	void insert() {
		String number = tfTripNum.getText();
		String name = tfTripName.getText();
		String pay = tfTripPay.getText();
		String days = tfTripDays.getText();
		String startdate = tfTripStartDate.getText();
		String finishdate = tfTripFinishDate.getText();
		String info = taTripInfo.getText();
		String spot = taTripSpot.getText();

		TripDTO vo = new TripDTO();
		vo.setTripNum(Integer.parseInt(number));
		vo.setTripName(name);
		vo.setTripPay(pay);
		vo.setTripDays(days);
		vo.setTripStartDate(startdate);
		vo.setTripFinishDate(finishdate);
		vo.setTripInfo(info);
		vo.setTripSpot(spot);
		try {
			dao.insertTrip(vo);
			JOptionPane.showMessageDialog(null, "��� ����");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "��� ����" + e.getMessage());
		}
		clearScreen();
	}

	void delete() {
		String number = tfTripNum.getText();

		TripDTO vo = new TripDTO();
		vo.setTripNum(Integer.parseInt(number));

		try {
			dao.deleteTrip(vo);
			JOptionPane.showMessageDialog(null, "���� ����");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "���� ����" + e.getMessage());
		}
		clearScreen();
	}

	void update() {
		String number = tfTripNum.getText();
		String name = tfTripName.getText();
		String pay = tfTripPay.getText();
		String days = tfTripDays.getText();
		String startdate = tfTripStartDate.getText();
		String finishdate = tfTripFinishDate.getText();
		String info = taTripInfo.getText();
		String spot = taTripSpot.getText();

		TripDTO vo = new TripDTO();
		vo.setTripNum(Integer.parseInt(number));
		vo.setTripName(name);
		vo.setTripPay(pay);
		vo.setTripDays(days);
		vo.setTripStartDate(startdate);
		vo.setTripFinishDate(finishdate);
		vo.setTripInfo(info);
		vo.setTripSpot(spot);
		try {
			dao.updateTrip(vo);
			JOptionPane.showMessageDialog(null, "���� ����");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "���� ����" + e.getMessage());
		}
		clearScreen();
	}

	void search() {
		int sel = comTripSearch.getSelectedIndex();
		String text = tfTripSearch.getText();

		try {
			ArrayList list = dao.selectTrip(sel, text);
			tmTrip.data = list;
			tableTrip.setModel(tmTrip);
			tmTrip.fireTableDataChanged();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "�˻� ���� : " + e.getMessage());
		}
	}
	
	void select() {
		int row = tableTrip.getSelectedRow();
		int col = 0;
		int vNum = (Integer) tableTrip.getValueAt(row, col);// row�� 1�� �÷��� ���� ����(1�� �÷��� PK�ϱ�)
		TripDTO vo = new TripDTO();
		try {
			vo = dao.selectByPk(vNum);
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "��Ű�� �˻� ���� : " + ex.getMessage());
		}

		// VO�� ������ ���� ���� ȭ�鿡 ���
		tfTripNum.setText(String.valueOf(vo.getTripNum()));
		tfTripName.setText(vo.getTripName());
		tfTripPay.setText(vo.getTripPay());
		tfTripDays.setText(vo.getTripDays());
		tfTripStartDate.setText(vo.getTripStartDate());
		tfTripFinishDate.setText(vo.getTripFinishDate());
		taTripInfo.setText(vo.getTripInfo());
		taTripSpot.setText(vo.getTripSpot());
	}

	void clearScreen() {
		tfTripNum.setText("");
		tfTripName.setText("");
		tfTripPay.setText("");
		tfTripDays.setText("");
		tfTripStartDate.setText("");
		tfTripFinishDate.setText("");
		taTripInfo.setText("");
		taTripSpot.setText("");

	}

	class TripTableModel extends AbstractTableModel {

		ArrayList data = new ArrayList();
		String[] columnNames = { "��Ű�� ��ȣ", "��Ű�� �̸�", "��	��", "��	��", "��� ��¥", "���� ��¥" };

		// 1. �⺻���� TabelModel �����
		// �Ʒ� �� �Լ��� TabelModel �������̽��� �߻��Լ��ε�
		// AbstractTabelModel���� �������� �ʾұ⿡...
		// �ݵ�� ����� ���� �ʼ�!!!!

		public int getColumnCount() {
			return columnNames.length;
		}

		public int getRowCount() {
			return data.size();
		}

		public Object getValueAt(int row, int col) {
			ArrayList temp = (ArrayList) data.get(row);
			return temp.get(col);
		}

		// 2. ������ �÷������� ��ȯ�ϱ�
//				�⺻������ A, B, C, D ��� �̸����� �÷����� �����ȴ�
		public String getColumnName(int col) {
			return columnNames[col];
		}
	}
}
