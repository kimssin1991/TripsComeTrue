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
			System.out.println("DB 연결 성공");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "DB 연결 실패: " + e.getMessage());
		}
	}

	void newObject() {
				
		tfId = new JTextField(15);
		tfcname = new JTextField(15);
		tfTripNum = new JTextField(15);
		tfTripName = new JTextField(15);
		tfTripRv = new JTextField(15);
		
		bTripUpdate = new JButton("수 	정");
		bTripDelete = new JButton("삭	제");
		bSearch = new JButton("검    색");
		bClear = new JButton("화면 비우기");

		searchTrip = new String[] { "고객 ID", "패키지 이름" };
		comTripSearch = new JComboBox(searchTrip);
		tfTripSearch = new JTextField(15);

		tmTrip = new TripTableModel();
		tableTrip = new JTable(tmTrip);
		
		img1 = new ImageIcon("manage.png");
		forimg = new JLabel(img1);
	}

	void addLayout() {
		// west 영역 : 비디오 입력 및 수정
		JPanel pWest = new JPanel();
		// 비디오 정보입력하는 부분
		JPanel pWestNorth = new JPanel();
		JPanel pWestNorthUp = new JPanel();
		pWestNorthUp.setLayout(new GridLayout(10, 2));

		pWestNorthUp.add(new JLabel("    고     객            I  D "));
		pWestNorthUp.add(tfId);
		pWestNorthUp.add(new JLabel("    고     객           이    름 "));
		pWestNorthUp.add(tfcname); 
		pWestNorthUp.add(new JLabel("    패   키   지       번    호 "));
		pWestNorthUp.add(tfTripNum);
		pWestNorthUp.add(new JLabel("    패   키   지       이    름 "));
		pWestNorthUp.add(tfTripName);
		pWestNorthUp.add(new JLabel("    신    청    날   짜 "));
		pWestNorthUp.add(tfTripRv);

		pWestNorth.setBorder(new TitledBorder(" 신청내역 입력 "));

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

		// east 영역 : 신청내역 검색부분
		JPanel pEast = new JPanel();
		JPanel pEastNorth = new JPanel();
		pEastNorth.add(comTripSearch);
		pEastNorth.add(tfTripSearch);
		pEastNorth.add(bSearch);
		pEastNorth.setBorder(new TitledBorder(" 신청내역 상세검색 "));

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

		// 전체 붙이기
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
		int vNum = (int) tableTrip.getValueAt(row, col);// row행 1번 컬럼의 값을 저장(1번 컬럼이 PK니까)
		ManageDTO vo = new ManageDTO();
		try {
			vo = dao.selectByPk(vNum);
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "패키지 검색 실패 : " + ex.getMessage());
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
			JOptionPane.showMessageDialog(null, "삭제 성공");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "삭제 실패" + e.getMessage());
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
			JOptionPane.showMessageDialog(null, "수정 성공");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "수정 실패" + e.getMessage());
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
			tmTrip.fireTableDataChanged(); //제이테이블 데이터 변경시 리스너에게 전달

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "검색 실패 : " + e.getMessage());
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
		String[] columnNames = { "고  객  I  D", "고  객  이  름", "패 키 지 번 호", "패 키 지 이 름", "신  청  날  짜"};

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

		// 2. 지정된 컬럼명으로 변환하기
//				기본적으로 A, B, C, D 라는 이름으로 컬럼명이 지정된다
		public String getColumnName(int col) {
			return columnNames[col];
		}
	}
}
