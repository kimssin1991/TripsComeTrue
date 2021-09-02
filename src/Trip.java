import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import Main.AdminClientMain;
import Main.ManageMain;
import Main.TripMain;

public class Trip extends JFrame implements ActionListener{
	TripMain trip;
	ManageMain manage;
	AdminClientMain client;

	public Trip() {
		trip = new TripMain();
		manage = new ManageMain();
		client = new AdminClientMain();

		JTabbedPane pane = new JTabbedPane();
		pane.addTab("��Ű�� ����", trip);
		pane.addTab("�� ����", client);
		pane.addTab("��û����", manage);

		getContentPane().add("Center", pane);
		setSize(1000, 800);
		setVisible(true);

	}

	public static void main(String[] args) {
		new Trip();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		dispose();
	}

}
