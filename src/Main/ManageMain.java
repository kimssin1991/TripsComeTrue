package Main;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.AbstractTableModel;

import DAO.ManageDAO;
import DTO.ManageDTO;

public class ManageMain extends JPanel implements ActionListener {
	
	JTextField tfId, tfcname, tfTripNum, tfTripName, tfTripRv;

	JButton bTripUpdate, bTripDelete, bSearch, bClear;
	JComboBox comTripSearch;
	JTextField tfTripSearch;
	JTable tableTrip;
	ManageDAO dao;
	TripTableModel tmTrip;
	String searchTrip[];
	JLabel forimg;
    ImageIcon img1;
    JPanel imgp;

	public ManageMain() {
		newObject();
		addLayout();
		setStyle();
		eventProc();
		try {
			dao = new ManageDAO();
			System.out.println("DB ���� ����");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "DB ���� ����: " + e.getMessage());
		}
	}

	void newObject() {
				
		tfId = new JTextField(15);
		tfcname = new JTextField(15);
		tfTripNum = new JTextField(15);
		tfTripName = new JTextField(15);
		tfTripRv = new JTextField(15);
		
		bTripUpdate = new JButton("�� 	��");
		bTripDelete = new JButton("��	��");
		bSearch = new JButton("��    ��");
		bClear = new JButton("ȭ�� ����");

		searchTrip = new String[] { "�� ID", "��Ű�� �̸�" };
		comTripSearch = new JComboBox(searchTrip);
		tfTripSearch = new JTextField(15);

		tmTrip = new TripTableModel();
		tableTrip = new JTable(tmTrip);
		
		img1 = new ImageIcon("manage.png");
		forimg = new JLabel(img1);
	}

	void addLayout() {
		// west ���� : ���� �Է� �� ����
		JPanel pWest = new JPanel();
		// ���� �����Է��ϴ� �κ�
		JPanel pWestNorth = new JPanel();
		JPanel pWestNorthUp = new JPanel();
		pWestNorthUp.setLayout(new GridLayout(10, 2));

		pWestNorthUp.add(new JLabel("    ��     ��            I  D "));
		pWestNorthUp.add(tfId);
		pWestNorthUp.add(new JLabel("    ��     ��           ��    �� "));
		pWestNorthUp.add(tfcname); 
		pWestNorthUp.add(new JLabel("    ��   Ű   ��       ��    ȣ "));
		pWestNorthUp.add(tfTripNum);
		pWestNorthUp.add(new JLabel("    ��   Ű   ��       ��    �� "));
		pWestNorthUp.add(tfTripName);
		pWestNorthUp.add(new JLabel("    ��    û    ��   ¥ "));
		pWestNorthUp.add(tfTripRv);

		pWestNorth.setBorder(new TitledBorder(" ��û���� �Է� "));

		pWestNorth.setLayout(new BorderLayout());
		pWestNorth.add("North", pWestNorthUp);
		pWestNorth.add("South", forimg);
		
		
		JPanel pWestSouth = new JPanel();

		JPanel pWestSouthDown = new JPanel();
		pWestSouthDown.setLayout(new GridLayout(1, 2));
		pWestSouthDown.add(bTripUpdate);
		pWestSouthDown.add(bTripDelete);

		pWestSouth.setLayout(new GridLayout(1, 1));
		pWestSouth.add(pWestSouthDown);

		pWest.setLayout(new BorderLayout());
		pWest.add("Center", pWestNorth);
		pWest.add("South", pWestSouth);

		// east ���� : ��û���� �˻��κ�
		JPanel pEast = new JPanel();
		JPanel pEastNorth = new JPanel();
		pEastNorth.add(comTripSearch);
		pEastNorth.add(tfTripSearch);
		pEastNorth.add(bSearch);
		pEastNorth.setBorder(new TitledBorder(" ��û���� �󼼰˻� "));

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

	void setStyle() {
		
		tfId.setEditable(false);
		tfcname.setEditable(false);
		tfTripNum.setEditable(true);
		tfTripName.setEditable(false);
		tfTripRv.setEditable(false);
	}

	void eventProc() {
		bTripUpdate.addActionListener(this);
		bTripDelete.addActionListener(this);
		tfTripSearch.addActionListener(this);
		

		
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
		Object o = ev.getSource();

		if (o == bTripUpdate) {
			update();
		} else if (o == bTripDelete) {
			delete();
		} else if (o == tfTripSearch) {
			search();
		}
	};
	
	void select() {
		int row = tableTrip.getSelectedRow();
		int col = 2;
		int vNum = (int) tableTrip.getValueAt(row, col);// row�� 1�� �÷��� ���� ����(1�� �÷��� PK�ϱ�)
		ManageDTO vo = new ManageDTO();
		try {
			vo = dao.selectByPk(vNum);
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "��Ű�� �˻� ���� : " + ex.getMessage());
		}

		tfId.setText(vo.getId());
		tfcname.setText(vo.getCname());
		tfTripNum.setText(String.valueOf(vo.getTripNum()));
		tfTripName.setText(vo.getTripName());
		tfTripRv.setText(vo.getTripRv());
	}

	void delete() {
		String number = tfTripNum.getText();

		ManageDTO vo = new ManageDTO();
		vo.setTripNum(Integer.parseInt(number));

		try {
			dao.deleteRV(vo);
			JOptionPane.showMessageDialog(null, "���� ����");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "���� ����" + e.getMessage());
		}
		clearScreen();
	}

	void update() {
		int row = tableTrip.getSelectedRow();
		int col = 2;
		int vNum = (int) tableTrip.getValueAt(row, col);
		String tripnum = tfTripNum.getText();
		String tripname = tfTripName.getText();

		
		ManageDTO vo = new ManageDTO();
		
		vo.setTripNum(Integer.parseInt(tripnum));
		vo.setTripName(tripname);


		try {
			dao.updateRV(vo, vNum);
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
			ArrayList list = dao.selectTripRV(sel, text);
			tmTrip.data = list;
			tableTrip.setModel(tmTrip);
			tmTrip.fireTableDataChanged(); //�������̺� ������ ����� �����ʿ��� ����

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "�˻� ���� : " + e.getMessage());
		}
	}

	void clearScreen() {
		tfId.setText("");
		tfcname.setText("");
		tfTripNum.setText("");
		tfTripName.setText("");
	}

	class TripTableModel extends AbstractTableModel {

		ArrayList data = new ArrayList();
		String[] columnNames = { "��  ��  I  D", "��  ��  ��  ��", "�� Ű �� �� ȣ", "�� Ű �� �� ��", "��  û  ��  ¥"};

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
