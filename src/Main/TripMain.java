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
			System.out.println("DB 연결 성공");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "DB 연결 실패: " + e.getMessage());
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

		bTripInsert = new JButton("등	록");
		bTripUpdate = new JButton("수 	정");
		bTripDelete = new JButton("삭	제");
		bSearch = new JButton("검    색");
		bClear = new JButton("화면 비우기");

		searchTrip = new String[] { "패키지 이름", "패키지 번호", "출 발 날 짜" };
		comTripSearch = new JComboBox(searchTrip);
		tfTripSearch = new JTextField(15);

		tmTrip = new TripTableModel();
		tableTrip = new JTable(tmTrip);
	}

	void addLayout() {
		// west 영역 : 비디오 입력 및 수정
		JPanel pWest = new JPanel();
		// 비디오 정보입력하는 부분
		JPanel pWestNorth = new JPanel();
		JPanel pWestNorthUp = new JPanel();
		pWestNorthUp.setLayout(new GridLayout(6, 2));

		pWestNorthUp.add(new JLabel("                       패 키 지     번 호 "));
		pWestNorthUp.add(tfTripNum);
		pWestNorthUp.add(new JLabel("                       패 키 지     이 름 "));
		pWestNorthUp.add(tfTripName);
		pWestNorthUp.add(new JLabel("                       가                     격 "));
		pWestNorthUp.add(tfTripPay);
		pWestNorthUp.add(new JLabel("                       기                     간 "));
		pWestNorthUp.add(tfTripDays);
		pWestNorthUp.add(new JLabel("                       출    발    날    짜 "));
		pWestNorthUp.add(tfTripStartDate);
		pWestNorthUp.add(new JLabel("                       도    착    날    짜 "));
		pWestNorthUp.add(tfTripFinishDate);

		JPanel pWestNorthCenter = new JPanel();
		pWestNorthCenter.setLayout(new BorderLayout());
		pWestNorthCenter.add("Center", taTripSpot);
		sp1 = new JScrollPane(taTripSpot);
		pWestNorthCenter.add(sp1);
		pWestNorthCenter.setBorder(new TitledBorder("여 행 지"));

		JPanel pWestNorthDown = new JPanel();
		pWestNorthDown.setLayout(new BorderLayout());
		pWestNorthDown.add("Center", taTripInfo);
		sp2 = new JScrollPane(taTripInfo);
		pWestNorthDown.add(sp2);
		pWestNorthDown.setBorder(new TitledBorder("설        명"));

		pWestNorth.setBorder(new TitledBorder(" 패키지 정보 입력 "));

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

		// east 영역 : 비디오 검색부분
		JPanel pEast = new JPanel();
		JPanel pEastNorth = new JPanel();
		pEastNorth.add(comTripSearch);
		pEastNorth.add(tfTripSearch);
		pEastNorth.add(bSearch);
		pEastNorth.setBorder(new TitledBorder(" 패키지 검색 "));

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
			JOptionPane.showMessageDialog(null, "등록 성공");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "등록 실패" + e.getMessage());
		}
		clearScreen();
	}

	void delete() {
		String number = tfTripNum.getText();

		TripDTO vo = new TripDTO();
		vo.setTripNum(Integer.parseInt(number));

		try {
			dao.deleteTrip(vo);
			JOptionPane.showMessageDialog(null, "삭제 성공");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "삭제 실패" + e.getMessage());
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
			ArrayList list = dao.selectTrip(sel, text);
			tmTrip.data = list;
			tableTrip.setModel(tmTrip);
			tmTrip.fireTableDataChanged();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "검색 실패 : " + e.getMessage());
		}
	}
	
	void select() {
		int row = tableTrip.getSelectedRow();
		int col = 0;
		int vNum = (Integer) tableTrip.getValueAt(row, col);// row행 1번 컬럼의 값을 저장(1번 컬럼이 PK니까)
		TripDTO vo = new TripDTO();
		try {
			vo = dao.selectByPk(vNum);
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "패키지 검색 실패 : " + ex.getMessage());
		}

		// VO의 각각의 값을 왼쪽 화면에 출력
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
		String[] columnNames = { "패키지 번호", "패키지 이름", "가	격", "기	간", "출발 날짜", "도착 날짜" };

		// 1. 기본적인 TabelModel 만들기
		// 아래 세 함수는 TabelModel 인터페이스의 추상함수인데
		// AbstractTabelModel에서 구현되지 않았기에...
		// 반드시 사용자 구현 필수!!!!

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
