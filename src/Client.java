import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import Main.ClientMain;
import Main.ConfirmMain;
import Main.MyPageFrame;

public class Client extends JFrame implements ActionListener {
	ClientMain client;
	ConfirmMain confirm;
	MyPageFrame frame;
	static String UserID;

	public Client(String tfUser_ID) throws Exception {
		UserID = tfUser_ID;
		
		client = new ClientMain(UserID);
		confirm = new ConfirmMain(UserID);
		frame = new MyPageFrame(UserID);

		JTabbedPane pane = new JTabbedPane();
		pane.addTab("������", client);
		pane.addTab("��û����", confirm);
		pane.addTab("����������", frame);

		getContentPane().add("Center", pane);
		setSize(1000, 800);
		setVisible(true);

		
	}

	public static void main(String[] args) throws Exception {
		new Client(UserID);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		dispose();
		
	}

}