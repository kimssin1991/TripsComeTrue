package Main;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import DAO.ClientDAO;
import DTO.ClientDTO;

public class ClientMain extends JPanel {

	String[] name1 = { "��Ű����", "��  ��", "days", "���۳�¥", "���ᳯ¥", "spot", "info" };
//	String[] name2 = { "ID", "ȸ���̸�", "��Ű����", "��û��¥" };

	Object ob1[][] = new Object[0][7];
	Object ob2[][] = new Object[0][4];

	DefaultTableModel dt1 = new DefaultTableModel(ob1, name1);
//	DefaultTableModel dt2 = new DefaultTableModel(ob2, name2);
	JTable jt1 = new JTable(dt1);
//	JTable jt2 = new JTable(dt2);
	JScrollPane jsp1 = new JScrollPane(jt1);
//	JScrollPane jsp2 = new JScrollPane(jt2);
//	JPanel p2 = new JPanel();
	JTabbedPane t = new JTabbedPane();

	String[] searchTrip = { "���� ���ݼ�", "���� ���ݼ�", "�� �� �� ¥" };
	JComboBox comTripSearch = new JComboBox(searchTrip);

	JButton btn1 = new JButton("�˻�");
	JButton btn3 = new JButton("�������");
	JButton btn4 = new JButton("��û");
	JButton btn2 = new JButton("��ü����");

	JLabel title = new JLabel("��Ű�� ��û ����");

	JTextField jtextfield = new JTextField(20);
	
	ImageIcon[] images = { new ImageIcon("1.jpg"), new ImageIcon("2.jpg"),new ImageIcon("3.jpg"),
	         new ImageIcon("4.jpg"), new ImageIcon("5.jpg"),new ImageIcon("6.jpg"),
	         new ImageIcon("7.jpg"), new ImageIcon("9.jpg"),
	         new ImageIcon("10.jpg"), new ImageIcon("11.jpg"),new ImageIcon("12.jpg"),
	         new ImageIcon("13.jpg"), new ImageIcon("14.jpg"),new ImageIcon("15.jpg"),
	         new ImageIcon("16.jpg"), new ImageIcon("17.jpg"),new ImageIcon("18.jpg"),
	         new ImageIcon("19.jpg"), new ImageIcon("20.jpg"),new ImageIcon("21.jpg"),
	         new ImageIcon("22.jpg"), new ImageIcon("23.jpg"),new ImageIcon("24.jpg"),
	         new ImageIcon("25.jpg"), new ImageIcon("26.jpg"),new ImageIcon("27.jpg"),
	         new ImageIcon("28.jpg"), new ImageIcon("29.jpg"),new ImageIcon("30.jpg")
	         };
	JLabel jl = new JLabel(images[0]);
	JScrollPane jsp2 = new JScrollPane(jl);

	static String UserID;
	String User;

	public ClientMain(String UserID) throws Exception {

		User = UserID;
		ClientDAO dao = new ClientDAO(User);

		title.setFont(new Font("���ʷҵ���", Font.BOLD, 18));

		jt1.getColumn("��Ű����").setPreferredWidth(400);
		jt1.getColumn("��  ��").setPreferredWidth(70);
		jt1.getColumn("spot").setPreferredWidth(40);
		jt1.getColumn("info").setPreferredWidth(150);
		jsp2.getVerticalScrollBar().setUnitIncrement(16);

		JPanel jp = new JPanel();
		JPanel jp2 = new JPanel();
		JPanel jp1 = new JPanel();

		jp1.add(comTripSearch);
		jp1.add(jtextfield);
		jp1.add(btn1);
		jp1.add(btn2);

		jp.setBorder(new TitledBorder("������ ����"));
		jp.setLayout(new BorderLayout());
		jp.add("Center", jsp1);
		jp.add("North", jp1);
		jp.add("South", btn4);
		
		jp.setBorder(new TitledBorder("������ ����"));
		jp2.setLayout(new BorderLayout());
		jp2.add(jsp2);
		

		setLayout(new GridLayout(1, 2));
		add(jp);
		add(jp2);

		setVisible(true);
		dao.select(dt1);

		dao.tableCellCenter(jt1);
		dao.tableCellRight(jt1);

		comTripSearch.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JComboBox cb = (JComboBox) e.getSource();
				if (cb == comTripSearch) {
					switch (cb.getSelectedIndex()) {
					case 0:
						dao.radioAsc(dt1);
						break;
					case 1:
						dao.radioDesc(dt1);
						break;
					case 2:
						dao.startDate(dt1);
						break;
					}
				}
			}
		});

		jt1.addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseMoved(MouseEvent e) {
				String msg = getToolTipText(e);
				jt1.setToolTipText(msg);
			}

			public String getToolTipText(MouseEvent e) {
				JTable t = (JTable) e.getSource();
				Point p = e.getPoint();
				int row = t.rowAtPoint(p);
				int column = t.columnAtPoint(p);
				return t.getModel().getValueAt(row, column).toString();
			}
		});

		btn1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Object obj = e.getSource();
				if (obj == btn1) {
					if (jtextfield.getText().trim().length() > 0) {
						dao.getUserSearch(dt1, jtextfield.getText());
					} else
						JOptionPane.showMessageDialog(null, "�˻�� �Է����ּ���");
				}
			}
		});

		btn2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Object obj = e.getSource();
				if (obj == btn2)
					dao.select(dt1);
			}
		});

		btn4.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {
	            Object obj = e.getSource();
	            if (obj == btn4) {
	               int row = jt1.getSelectedRow();
	               String data = (String) jt1.getModel().getValueAt(row, 0);
	               if (dao.nameCheck(data)) {
	                  JOptionPane.showMessageDialog(null, "�ߺ���û");
	               } else {
	                  int result = JOptionPane.showConfirmDialog(null, "��û�Ͻðڽ��ϱ�?", "Confirm",
	                        JOptionPane.YES_NO_OPTION);
	                  if (result == JOptionPane.YES_OPTION) {
	                     JOptionPane.showMessageDialog(null, "��û�Ϸ�");
	                     dao.book(data);
	                     try {
							dao.updatePoint(data, User);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
	                  }
	               }
	            }
	         }
	      });
		
		jt1.addMouseListener(new MouseListener() {

	         @Override
	         public void mouseReleased(MouseEvent e) {
	            // TODO Auto-generated method stub

	         }

	         @Override
	         public void mousePressed(MouseEvent e) {
	            // TODO Auto-generated method stub

	         }

	         @Override
	         public void mouseExited(MouseEvent e) {
	            // TODO Auto-generated method stub

	         }

	         @Override
	         public void mouseEntered(MouseEvent e) {
	            // TODO Auto-generated method stub

	         }

	         @Override
	         public void mouseClicked(MouseEvent e) {
	            jt1 = (JTable) e.getSource();
	            int row = jt1.getSelectedRow();
	    		String coffeeno = jt1.getValueAt(row, 0).toString();

	            if (coffeeno.equals("������ Ÿ�� ������ ������ ����!")) {
		               jl.setIcon(images[0]);
		            } else if (coffeeno.equals("�︪�� ���� ��Ű�� �������")) {
		            	jl.setIcon(images[1]);
		            } else if (coffeeno.equals("'KTX ȫ�� & ��굵 & ��ݵ� & ���ʵ� ��������")) {
		            	jl.setIcon(images[2]);
		            } else if (coffeeno.equals("���ֵ� ���������� �����̾� ��������")) {
		            	jl.setIcon(images[3]);
		            } else if (coffeeno.equals("���� ������ ���� �������� ��Ƽ����+��������+�������ٴٺ�ä��+�ȸ�Ŀ�ǰŸ�+������")) {
		            	jl.setIcon(images[4]);
		            } else if (coffeeno.equals("VAN Ÿ�� ������ �뿵,���� 1��2�� (��������, �ұԸ� ���ӿ��� ��õ)")) {
		            	jl.setIcon(images[5]);
		            } else if (coffeeno.equals("[���� �߰�] ���� ������ �Ŷ��� �߰� ���� Ž�� (���־߰�����)")) {
		            	jl.setIcon(images[6]);
		            } else if (coffeeno.equals("[��â������] [��â �ý����� 1ź] õ����� ������ ����������~����ɻ�����(����)")) {
		            	jl.setIcon(images[7]);
		            } else if (coffeeno.equals("[��õ�ù�����] õ���� ��� ��ɵ�(������� 1��2��)")) {
		            	jl.setIcon(images[8]);
		            } else if (coffeeno.equals("��07��22�� ���Ȯ�������� ��ȭ���Ÿ�+�󺥴� ����")) {
		            	jl.setIcon(images[9]);
		            } else if (coffeeno.equals("[��ȭ+����ŷ] �а� ���� �������� ��� �� 4��")) {
		            	jl.setIcon(images[10]);
		            } else if (coffeeno.equals("��Ƽ�ư� ����+�ž����� 5��")) {
		            	jl.setIcon(images[11]);
		            } else if (coffeeno.equals("����+���ֵ� �Ͼ��� ��Ű�� 3�� [Ư�� �󸶴� ȣ�� 3��]")) {
		            	jl.setIcon(images[12]);
		            } else if (coffeeno.equals("[����] ������ �ູ 4ź \"��õ\"����")) {
		            	jl.setIcon(images[13]);
		            } else if (coffeeno.equals("��Ư�� ���� ����Ʈ��ŷ/ ���ѷ� ���� / ���� Ư�� ����ȣ�� (1��2��) - ȣ���������� 3������")) {
		            	jl.setIcon(images[14]);
		            } else if (coffeeno.equals("�泲 �Ծ� ��ī�̺�CC")) {
		            	jl.setIcon(images[15]);
		            } else if (coffeeno.equals("��5�� 19�� �� ���Ȯ��!�ڰ�ȭ ����� + ������ + ������� ī�� + ǳ������ + ����ü�� (����)")) {
		            	jl.setIcon(images[16]);
		            } else if (coffeeno.equals("[�������] �︪�� �θ�ƽ ��Ϲ� �������� 2��3�� (��������Ʈ ���Ǻ� ����/����ī48�ð�)")) {
		            	jl.setIcon(images[17]);
		            } else if (coffeeno.equals("[�泲-1��2��] �������� ��� Ž��� �ٴ�ȭ�Ÿ� �о�Ƹ���õ���� ǥ���")) {
		            	jl.setIcon(images[18]);
		            } else if (coffeeno.equals("��09��23�� ���Ȯ����ȭõ ������� Ʈ��ŷ+��ȭ�Ǵ� (25�ξ�������)")) {
		            	jl.setIcon(images[19]);
		            } else if (coffeeno.equals("[����Ư��] Ǫ���ٴ� ��âû������� ���� ���Ͽ���")) {
		            	jl.setIcon(images[20]);
		            } else if (coffeeno.equals("Ⱦ����Ƽ����1�ڽ� Ⱦ��ȣ����+�����𻧸���+�ܹ��Ÿ����ü��+ǳ��������")) {
		            	jl.setIcon(images[21]);
		            } else if (coffeeno.equals("[LUXURY~ ������ ����1��] ������ ��Ʈ���� + �ϴø��� Ʈ���͸��� + ��ũ���� ȣ�ڼ��� (ȣ������, ��¡�����, ����Ұ��) (1��2��)")) {
		            	jl.setIcon(images[22]);
		            } else if (coffeeno.equals("��5�� 29�� �� ���Ȯ��!�ھȵ� ��ȸ���� + ������ + ������� + �ο�� + �ȵ����� �Թ� (����)")) {
		            	jl.setIcon(images[23]);
		            } else if (coffeeno.equals("��5�� 19�� �� ���Ȯ��!�ھ�� ��õ���޾縲 + ���Ǻ�ġ + ���޾� + ������ ������ (����)")) {
		            	jl.setIcon(images[24]);
		            } else if (coffeeno.equals("[���ִ��Ͽ���-��������EVENT] ������ �����ø� �����ϴ� 1�Ϲ�������")) {
		            	jl.setIcon(images[25]);
		            } else if (coffeeno.equals("[����Ư��] ���� �ź� ź�� 200�ֳ� �ѱ��� ��Ƽ�ư� ���� ����")) {
		            	jl.setIcon(images[26]);
		            } else if (coffeeno.equals("��07��23�� ���Ȯ���ڰ�â ��ä�ɹ翩�� �п����� ��â���� (24�ξ�������)")) {
		            	jl.setIcon(images[27]);
		            } else if (coffeeno.equals("5/19 ���Ȯ�� : ����Ư��] ����BIG3 ����ɾ綼���� , ������, �ȸ���Ŀ�ǰŸ�����")) {
		            	jl.setIcon(images[28]);
		            }
	         }
	      });
		
	}

}