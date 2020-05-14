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

public class GUI extends JFrame { // jframe 상속

	// JPanel = new JPanel();

	// *** 각각의 패널의 초기 Visible은 false 로 설정해 화면이 겹치지 않게 한다.
	UserDAO userDAO = new UserDAO();

	GUI() {
		// 프레임 셋팅
		setTitle("부서 정보 관리 프로그램");
		setSize(600, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		getContentPane();
		//

		JPanel Mainbtn = new JPanel(new GridLayout(4, 1));
//		JPanel nullN = new JPanel();
//		JPanel nullS = new JPanel();

		JPanel insertJP = new JPanel(new BorderLayout()); // insert 번튼 클릭시 출력
		insertJP.setVisible(false);
		insertJP.setBackground(Color.WHITE);
		getContentPane().add(insertJP);

		JPanel searchJP = new JPanel(new BorderLayout()); // select 버튼 클릭시 출력
		searchJP.setVisible(false);
		searchJP.setBackground(Color.WHITE);
//		getContentPane().add(searchJP);

		JPanel showJP = new JPanel(); // show 버튼 클릭시 출력
		showJP.setVisible(false);

		// 메인에 버튼 배치
		JButton userIN = new JButton("부서정보 입력"); // 버튼 클릭시 insertJP 출력
		userIN.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("입력확인");
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
		JButton userExit = new JButton("프로그램 종료");

		Mainbtn.add(userIN);
		Mainbtn.add(userSelect);
		Mainbtn.add(userShow);
		Mainbtn.add(userExit);

		getContentPane().add(Mainbtn, BorderLayout.WEST);

		// ****** 초기화면 설정 *******
		// ****** 버튼별 기능설정 ******

		// INSERT 기능 구현 *****
		
		getContentPane().add(insertJP,BorderLayout.CENTER);
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

		JTextField nameTxt = new JTextField();
		JTextField numberTxt = new JTextField();
		JTextField ageTxt = new JTextField();
		JTextField phoneTxt = new JTextField();

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
				String name = nameTxt.getText();
				String number = numberTxt.getText();
				String age = ageTxt.getText();
				String phone = phoneTxt.getText();

				userDAO.insert(name, number, age, phone);
			}
		});
//		Main.add(insertJP);

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
		JTextField searchTxt = new JTextField("입력"); // 검색텍스트 입력
		JButton searchbtn = new JButton("검색");
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

		JButton updatebtn = new JButton("수정"); //
		updatebtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

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

		searchJP.add(searchLa, BorderLayout.WEST);
		searchJP.add(searchTxtJP, BorderLayout.CENTER);
		searchJP.add(functionJP, BorderLayout.SOUTH);

//		getContentPane().add(searchJP,BorderLayout.CENTER);
	}
}
