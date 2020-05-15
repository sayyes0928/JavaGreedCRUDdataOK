package AppCRUD;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import user.UserDAO;
import user.UserDTO;

public class GUI extends JFrame { // jframe ���

	// JPanel = new JPanel();

	// *** ������ �г��� �ʱ� Visible�� false �� ������ ȭ���� ��ġ�� �ʰ� �Ѵ�.
	UserDAO userDAO = new UserDAO();
	JTable table;

	GUI() {
		// ������ ����
		setTitle("�μ� ���� ���� ���α׷�");
		setSize(700, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		// d

		JPanel login = new JPanel();
		JPanel Mainbtn = new JPanel(new GridLayout(4, 1));

		JPanel insertJP = new JPanel(new BorderLayout()); // insert ��ư Ŭ���� ���
		insertJP.setBounds(140, 0, 500, 390);
		insertJP.setVisible(false);
		insertJP.setBackground(Color.WHITE);

		JPanel searchJP = new JPanel(new BorderLayout()); // select ��ư Ŭ���� ���
		searchJP.setBounds(140, 0, 500, 390);
		searchJP.setVisible(false);
		searchJP.setBackground(Color.WHITE);

		JPanel showJP = new JPanel(new BorderLayout()); // show ��ư Ŭ���� ���
		showJP.setSize(300, 300);
		showJP.setVisible(false);
		showJP.setBackground(Color.WHITE);

		// ���ο� ��ư ��ġ
		JButton userIN = new JButton("�μ����� �Է�"); // ��ư Ŭ���� insertJP ���
		userIN.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				insertJP.setVisible(true);
				searchJP.setVisible(false);
				showJP.setVisible(false);

			}
		});

		JButton userSelect = new JButton("�μ����� ��ȸ");
		userSelect.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				searchJP.setVisible(true);
				insertJP.setVisible(false);
				showJP.setVisible(false);
			}
		});
		JButton userShow = new JButton("�μ����� ��ü��ȸ");
		userShow.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				searchJP.setVisible(false);
				insertJP.setVisible(false);
				showJP.setVisible(true);

				DefaultTableModel model = (DefaultTableModel) table.getModel();
				model.setRowCount(0);
				showUser();

			}
//				
		});

		JButton userExit = new JButton("���α׷� ����");
		userExit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		Mainbtn.add(userIN);
		Mainbtn.add(userSelect);
		Mainbtn.add(userShow);
		Mainbtn.add(userExit);

		add(Mainbtn, BorderLayout.WEST);

		// ****** �ʱ�ȭ�� ���� *******
		// ****** ��ư�� ��ɼ��� ******

		// INSERT ��� ���� *****

		add(insertJP, BorderLayout.CENTER);
		JPanel insertLa = new JPanel(new GridLayout(5, 1));

		JLabel name = new JLabel("������ : ");
		JLabel number = new JLabel("���� ��ȣ : ");
		JLabel age = new JLabel("���� : ");
		JLabel phone = new JLabel("��ȭ��ȣ : ");

		insertLa.add(name);
		insertLa.add(number);
		insertLa.add(age);
		insertLa.add(phone);

		JPanel insertTxtJP = new JPanel(new GridLayout(5, 1));
		JTextField nameTxt;
		nameTxt = new JTextField(null);
		JTextField numberTxt;
		numberTxt = new JTextField(null);
		JTextField ageTxt;
		ageTxt = new JTextField(null);
		JTextField phoneTxt;
		phoneTxt = new JTextField(null);

		JPanel submitJP = new JPanel(new BorderLayout());
		JButton submitbtn = new JButton("����"); // �Է¹��� ������ DB�� ���� �� ��ư
		submitJP.add(submitbtn, BorderLayout.EAST);

		insertTxtJP.add(nameTxt);
		insertTxtJP.add(numberTxt);
		insertTxtJP.add(ageTxt);
		insertTxtJP.add(phoneTxt);
		insertTxtJP.add(submitJP);

		insertJP.add(insertLa, BorderLayout.WEST);
		insertJP.add(insertTxtJP, BorderLayout.CENTER);

		submitbtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String name = null;
				String number = null;
				String age = null;
				String phone = null;

				name = nameTxt.getText();
				number = numberTxt.getText();
				age = ageTxt.getText();
				phone = phoneTxt.getText();
				if (name.trim().length() == 0 || number.trim().length() == 0 || age.trim().length() == 0
						|| phone.trim().length() == 0) {
					JOptionPane.showMessageDialog(null, "�Է��� �ȵ� ������ �ֽ��ϴ�.");
				} else {
					int idcheck = userDAO.idCheck(number);
					if (idcheck == 0) {
						userDAO.insert(name, number, age, phone);
						JOptionPane.showMessageDialog(null, "�Է��Ͻ� ������ ���������� ����Ǿ����ϴ�.");
						nameTxt.setText("");
						numberTxt.setText("");
						ageTxt.setText("");
						phoneTxt.setText("");
					} else {
						JOptionPane.showMessageDialog(null, "�ߺ��Ǵ� ������ȣ�� �����մϴ�");
					}

				}
			}
		});

		// SELECT ��� ���� *****

		JPanel searchLa = new JPanel(new GridLayout(5, 1));

		JLabel nameCall = new JLabel("������ : ");
		JLabel numberCall = new JLabel("���� ��ȣ : ");
		JLabel ageCall = new JLabel("���� : ");
		JLabel phoneCall = new JLabel("��ȭ��ȣ : ");

		searchLa.add(nameCall);
		searchLa.add(numberCall);
		searchLa.add(ageCall);
		searchLa.add(phoneCall);

		JPanel searchTxtJP = new JPanel(new GridLayout(5, 1));

		JTextField nameCallTxt = new JTextField();
		JTextField numberCallTxt = new JTextField();
		JTextField ageCallTxt = new JTextField();
		JTextField phoneCallTxt = new JTextField();

		JPanel functionJP = new JPanel(new BorderLayout());
		JTextField searchTxt = new JTextField("������ȣ �Է�"); // �˻��ؽ�Ʈ �Է�
		JButton searchbtn = new JButton("�˻�");
		searchbtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int idcheck = userDAO.idCheck(searchTxt.getText());
				if (idcheck != 0) {
					userDAO.search(searchTxt.getText());
					nameCallTxt.setText(userDAO.userDTO.getName());
					numberCallTxt.setText(userDAO.userDTO.getNumber());
					ageCallTxt.setText(userDAO.userDTO.getAge());
					phoneCallTxt.setText(userDAO.userDTO.getPhone());
				} else {
					JOptionPane.showMessageDialog(null, "�Է��Ͻ� ������ �����ϴ�.");
				}

			}
		});

		JButton updatebtn = new JButton("����"); //
		updatebtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (!numberCallTxt.getText().equals(userDAO.userDTO.getNumber())) {
					JOptionPane.showMessageDialog(null, "������ȣ�� ���� �� �� �����ϴ�.");
				} else {
					userDAO.update(nameCallTxt.getText(), numberCallTxt.getText(), ageCallTxt.getText(),
							phoneCallTxt.getText());
					JOptionPane.showMessageDialog(null, "������ ���������� �����Ǿ����ϴ�.");
					nameCallTxt.setText("");
					numberCallTxt.setText("");
					ageCallTxt.setText("");
					phoneCallTxt.setText("");
					searchTxt.setText("");
				}
			}

		});
		JButton deletebtn = new JButton("����"); //
		deletebtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				userDAO.delete(numberCallTxt.getText());
			}
		});

		JPanel funBtn = new JPanel();

		funBtn.add(searchbtn);
		funBtn.add(updatebtn);
		funBtn.add(deletebtn);

		functionJP.add(funBtn, BorderLayout.EAST);
		functionJP.add(searchTxt);

		searchTxtJP.add(nameCallTxt);
		searchTxtJP.add(numberCallTxt);
		searchTxtJP.add(ageCallTxt);
		searchTxtJP.add(phoneCallTxt);
		searchTxtJP.add(functionJP);

		searchJP.add(searchLa, BorderLayout.WEST);
		searchJP.add(searchTxtJP, BorderLayout.CENTER);

		add(searchJP, BorderLayout.CENTER);

		// SHOW ��� ���� ****

		table = new JTable();
		table.setVisible(true);
		table.setModel(new DefaultTableModel(new Object[][] {

		}, new String[] { "�̸�", "������ȣ", "����", "��ȭ��ȣ" }));
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		table.setModel(model);
		showJP.add(new JScrollPane(table), BorderLayout.CENTER);
		add(showJP, BorderLayout.CENTER);
	}

	public void showUser() {

		ArrayList<UserDTO> list = userDAO.showArray();
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		Object[] row = new Object[4];
		for (int i = 0; i < list.size(); i++) {
			row[0] = list.get(i).getName();
			row[1] = list.get(i).getNumber();
			row[2] = list.get(i).getAge();
			row[3] = list.get(i).getPhone();

			model.addRow(row);
		}
	}

}
