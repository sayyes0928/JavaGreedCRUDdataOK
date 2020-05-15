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

public class GUI extends JFrame { // jframe 상속

	// JPanel = new JPanel();

	// *** 각각의 패널의 초기 Visible은 false 로 설정해 화면이 겹치지 않게 한다.
	UserDAO userDAO = new UserDAO();
	JTable table;

	GUI() {
		// 프레임 셋팅
		setTitle("부서 정보 관리 프로그램");
		setSize(700, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		// d

		JPanel login = new JPanel();
		JPanel Mainbtn = new JPanel(new GridLayout(4, 1));

		JPanel insertJP = new JPanel(new BorderLayout()); // insert 번튼 클릭시 출력
		insertJP.setBounds(140, 0, 500, 390);
		insertJP.setVisible(false);
		insertJP.setBackground(Color.WHITE);

		JPanel searchJP = new JPanel(new BorderLayout()); // select 버튼 클릭시 출력
		searchJP.setBounds(140, 0, 500, 390);
		searchJP.setVisible(false);
		searchJP.setBackground(Color.WHITE);

		JPanel showJP = new JPanel(new BorderLayout()); // show 버튼 클릭시 출력
		showJP.setSize(300, 300);
		showJP.setVisible(false);
		showJP.setBackground(Color.WHITE);

		// 메인에 버튼 배치
		JButton userIN = new JButton("부서정보 입력"); // 버튼 클릭시 insertJP 출력
		userIN.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				insertJP.setVisible(true);
				searchJP.setVisible(false);
				showJP.setVisible(false);

			}
		});

		JButton userSelect = new JButton("부서정보 조회");
		userSelect.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				searchJP.setVisible(true);
				insertJP.setVisible(false);
				showJP.setVisible(false);
			}
		});
		JButton userShow = new JButton("부서정보 전체조회");
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

		JButton userExit = new JButton("프로그램 종료");
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

		// ****** 초기화면 설정 *******
		// ****** 버튼별 기능설정 ******

		// INSERT 기능 구현 *****

		add(insertJP, BorderLayout.CENTER);
		JPanel insertLa = new JPanel(new GridLayout(5, 1));

		JLabel name = new JLabel("직원명 : ");
		JLabel number = new JLabel("직원 번호 : ");
		JLabel age = new JLabel("나이 : ");
		JLabel phone = new JLabel("전화번호 : ");

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
		JButton submitbtn = new JButton("전송"); // 입력받은 정보를 DB로 전송 할 버튼
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
					JOptionPane.showMessageDialog(null, "입력이 안된 내용이 있습니다.");
				} else {
					int idcheck = userDAO.idCheck(number);
					if (idcheck == 0) {
						userDAO.insert(name, number, age, phone);
						JOptionPane.showMessageDialog(null, "입력하신 정보가 성공적으로 저장되었습니다.");
						nameTxt.setText("");
						numberTxt.setText("");
						ageTxt.setText("");
						phoneTxt.setText("");
					} else {
						JOptionPane.showMessageDialog(null, "중복되는 직원번호가 존재합니다");
					}

				}
			}
		});

		// SELECT 기능 구현 *****

		JPanel searchLa = new JPanel(new GridLayout(5, 1));

		JLabel nameCall = new JLabel("직원명 : ");
		JLabel numberCall = new JLabel("직원 번호 : ");
		JLabel ageCall = new JLabel("나이 : ");
		JLabel phoneCall = new JLabel("전화번호 : ");

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
		JTextField searchTxt = new JTextField("직원번호 입력"); // 검색텍스트 입력
		JButton searchbtn = new JButton("검색");
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
					JOptionPane.showMessageDialog(null, "입력하신 정보가 없습니다.");
				}

			}
		});

		JButton updatebtn = new JButton("수정"); //
		updatebtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (!numberCallTxt.getText().equals(userDAO.userDTO.getNumber())) {
					JOptionPane.showMessageDialog(null, "직원번호는 변경 할 수 없습니다.");
				} else {
					userDAO.update(nameCallTxt.getText(), numberCallTxt.getText(), ageCallTxt.getText(),
							phoneCallTxt.getText());
					JOptionPane.showMessageDialog(null, "정보가 성공적으로 수정되었습니다.");
					nameCallTxt.setText("");
					numberCallTxt.setText("");
					ageCallTxt.setText("");
					phoneCallTxt.setText("");
					searchTxt.setText("");
				}
			}

		});
		JButton deletebtn = new JButton("삭제"); //
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

		// SHOW 기능 구현 ****

		table = new JTable();
		table.setVisible(true);
		table.setModel(new DefaultTableModel(new Object[][] {

		}, new String[] { "이름", "직원번호", "나이", "전화번호" }));
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
