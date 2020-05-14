package AppCRUD;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import user.UserDAO;

public class GUI extends JFrame { // jframe ���

	// JPanel = new JPanel();

	// *** ������ �г��� �ʱ� Visible�� false �� ������ ȭ���� ��ġ�� �ʰ� �Ѵ�.
	UserDAO userDAO = new UserDAO();

	GUI() {
		// ������ ����
		setTitle("�μ� ���� ���� ���α׷�");
		setSize(600, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		getContentPane();
		//

		JPanel Mainbtn = new JPanel(new GridLayout(4, 1));
//		JPanel nullN = new JPanel();
//		JPanel nullS = new JPanel();

		JPanel insertJP = new JPanel(new BorderLayout()); // insert ��ư Ŭ���� ���
		insertJP.setVisible(false);
		insertJP.setBackground(Color.WHITE);
		getContentPane().add(insertJP);

		JPanel searchJP = new JPanel(new BorderLayout()); // select ��ư Ŭ���� ���
		searchJP.setVisible(false);
		searchJP.setBackground(Color.WHITE);
//		getContentPane().add(searchJP);

		JPanel showJP = new JPanel(); // show ��ư Ŭ���� ���
		showJP.setVisible(false);

		// ���ο� ��ư ��ġ
		JButton userIN = new JButton("�μ����� �Է�"); // ��ư Ŭ���� insertJP ���
		userIN.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("�Է�Ȯ��");
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
		JButton userExit = new JButton("���α׷� ����");

		Mainbtn.add(userIN);
		Mainbtn.add(userSelect);
		Mainbtn.add(userShow);
		Mainbtn.add(userExit);

		getContentPane().add(Mainbtn, BorderLayout.WEST);

		// ****** �ʱ�ȭ�� ���� *******
		// ****** ��ư�� ��ɼ��� ******

		// INSERT ��� ���� *****
		
		getContentPane().add(insertJP,BorderLayout.CENTER);
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

		JTextField nameTxt = new JTextField();
		JTextField numberTxt = new JTextField();
		JTextField ageTxt = new JTextField();
		JTextField phoneTxt = new JTextField();

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
				String name = nameTxt.getText();
				String number = numberTxt.getText();
				String age = ageTxt.getText();
				String phone = phoneTxt.getText();

				userDAO.insert(name, number, age, phone);
			}
		});
//		Main.add(insertJP);

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
		JTextField searchTxt = new JTextField("�Է�"); // �˻��ؽ�Ʈ �Է�
		JButton searchbtn = new JButton("�˻�");
		searchbtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				userDAO.search(searchTxt.getText());
				nameCallTxt.setText(userDAO.userDTO.getName());
				numberCallTxt.setText(userDAO.userDTO.getNumber());
				ageCallTxt.setText(userDAO.userDTO.getAge());
				phoneCallTxt.setText(userDAO.userDTO.getPhone());
			}
		});

		JButton updatebtn = new JButton("����"); //
		updatebtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

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

		searchJP.add(searchLa, BorderLayout.WEST);
		searchJP.add(searchTxtJP, BorderLayout.CENTER);
		searchJP.add(functionJP, BorderLayout.SOUTH);

//		getContentPane().add(searchJP,BorderLayout.CENTER);
	}
}
