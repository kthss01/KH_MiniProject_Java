//package com.kh.view.admin;
//
//import java.awt.Font;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.io.EOFException;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.io.ObjectInputStream;
//import java.util.ArrayList;
//import java.util.LinkedHashMap;
//import java.util.Map;
//import java.util.Timer;
//
//import javax.swing.JButton;
//import javax.swing.JDialog;
//import javax.swing.JFrame;
//import javax.swing.JLabel;
//import javax.swing.JPanel;
//
//import com.kh.controller.admin.Bridge;
//import com.kh.model.vo.Crew;
//import com.kh.model.vo.User;
//
//public class AdminViewManager extends Thread{
//
//	private User user;
//	private JPanel mainPanel; // 메인 - 시작 page
//	private ArrayList<Crew> crew;
//	private CrewListP crewList; // 크루 리스트 page
//	private EventSettingP eventSetting; // 이벤트 설정 page
//	private PendingApprovalP pendingApproval; // 크루 승인대기 page
//	private EventEndAlertD eventAlert; // 이벤트 마감 알림 page
//	private Map<String, JPanel> panelMap; // 프레임 전환을 위하여 map 사용
//	public Bridge ts;
//
//	public AdminViewManager(User user) {
//		this.user = user;
//
//	}
//
//	// 패널 객체 생성 및 컨트롤러 이어주기
//	private void initPanel() {
//		System.out.println("avm" + ts.getSharedData());
////
////		createPanel = new CrewCreatePanel(this, controllerManager.getCrewCreateController());
////		crewPanel = new CrewPanel(this, controllerManager.getCrewController());
////		rankPanel = new CrewRankPanel(this, controllerManager.getCrewController().getCrewRankController());
//		crewList = new CrewListP(this, crew);
//		eventSetting = new EventSettingP(this);
//		pendingApproval = new PendingApprovalP(this, crew, crewList);
//		eventAlert = new EventEndAlertD();
//
//		panelMap = new LinkedHashMap<String, JPanel>();
//		// frameMap에 crew에서 쓰이는 패널들 다 넣어둠
//		panelMap.put("main", mainPanel);
//		panelMap.put("list", crewList);
//		panelMap.put("eventSetting", eventSetting);
//		panelMap.put("pendingApproval", pendingApproval);
////		panelMap.put("eventAlert", eventAlert); //패널이 아니어서 못넣음 새로운 방법 강구 프레임으로 쓰던지
//	}
//
//	// 패널 전환 메소드
//	// panelMap에서 해당 panel을 찾아 setVisible true 후 나머지는 다 false로 변경
//	public void convertPanel(String panelName) {
//
//		// 모든 frame setVisible false로 하기
//		for (String key : panelMap.keySet()) {
//			JPanel panel = panelMap.get(key);
//
//			panel.setVisible(false);
//		}
//
//		panelMap.get(panelName).setVisible(true);
//
//	}
//
//	// 특정 프레임에 패널들 다 넣어주는 메소드
//	public void addPanels(JFrame frame) {
//		for (String key : panelMap.keySet()) {
//			JPanel panel = panelMap.get(key);
//			panel.setVisible(false);
//
//			frame.getContentPane().add(panel);
//		}
//	}
//
//	private void initialize() {
//
//		mainPanel = new JPanel();
//		mainPanel.setBounds(0, 0, 360, 600);
//		mainPanel.setLayout(null);
//
//		JButton btnNewButton = new JButton("EVENT 설정");
//		btnNewButton.setFont(new Font("맑은 고딕", Font.BOLD, 30));
//		btnNewButton.setBounds(27, 83, 286, 96);
//		btnNewButton.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				convertPanel("eventSetting");
//			}
//		});
//		mainPanel.add(btnNewButton);
//
//		JButton btnNewButton_1 = new JButton("EVENT 마감");
//		btnNewButton_1.setFont(new Font("맑은 고딕", Font.BOLD, 30));
//		btnNewButton_1.setBounds(27, 189, 286, 96);
//
//		btnNewButton_1.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				if (eventSetting.getEventGoal() != 0) {
//					EventEndAlertD dialog = new EventEndAlertD(eventSetting.getEventFlag(),
//							eventSetting.getEventGoal());
//					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//					dialog.setVisible(true);
//					eventSetting.setEventFlag("");
//					eventSetting.setEventGoal(0);
//				} else {
//					System.out.println("이벤트 진행중이 아니다.");
//				}
//			}
//		}); // 알럿은 알림이기때문에 아직 구현 안됨. 수정요망
//
//		mainPanel.add(btnNewButton_1);
//
//		JButton btnNewButton_1_1 = new JButton("크루 승인 관리");
//		btnNewButton_1_1.setFont(new Font("맑은 고딕", Font.BOLD, 30));
//		btnNewButton_1_1.setBounds(27, 295, 286, 96);
//		btnNewButton_1_1.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				convertPanel("pendingApproval"); // 크루 만들기 page로
//			}
//		});
//		mainPanel.add(btnNewButton_1_1);//
//
//		JButton btnNewButton_1_1_1 = new JButton("전체 크루 명단");
//		btnNewButton_1_1_1.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				convertPanel("list");
//			}
//		});
//		btnNewButton_1_1_1.setFont(new Font("맑은 고딕", Font.BOLD, 30));
//		btnNewButton_1_1_1.setBounds(27, 401, 286, 96);
//		mainPanel.add(btnNewButton_1_1_1);
//
//		JButton btnNewButton_2 = new JButton("종료");
//		btnNewButton_2.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//			}
//		});// 이전 로그인화면 아니면 종료로 구현
//		btnNewButton_2.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
//		btnNewButton_2.setBounds(216, 512, 97, 23);
//		mainPanel.add(btnNewButton_2);
//
//		JLabel lblNewLabel = new JLabel("관리자 메뉴");
//		lblNewLabel.setFont(new Font("맑은 고딕", Font.BOLD, 24));
//		lblNewLabel.setBounds(104, 16, 128, 57);
//		mainPanel.add(lblNewLabel);
//	}
//
//	public void loadCrewList() {
//
//		crew = new ArrayList<Crew>();
//
//		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("./resources/crewList.dat"))) {
//
//			while (true) {
//				crew.add((Crew) ois.readObject());
//			}
//
//		} catch (EOFException e) {
//			System.out.println("크루 리스트 읽기 완료");
//			return;
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		}
//	}
//
//	public void run() {
//		// 테스트 용
//		User user = new User("k1","1234","김태훈", 20, 100, 100, '남', false);
//
//		JFrame frame = new JFrame();
//		loadCrewList();
//		initialize();
//		initPanel();
//		frame.setBounds(100, 100, 360, 600);
//		frame.setLayout(null);
//		this.addPanels(frame);
//		this.convertPanel("main");
//		frame.setVisible(true);
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//	}
//
//	public EventSettingP getEventSetting() {
//		return eventSetting;
//	}
//
//	public void setEventSetting(EventSettingP eventSetting) {
//		this.eventSetting = eventSetting;
//	}
//
//	
//}

package com.kh.view.admin;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.kh.controller.admin.AdminEventController;
import com.kh.model.vo.Crew;
import com.kh.view.main.Main;
import java.awt.Color;

public class AdminViewManager {

	private Main main;
	private JPanel mainPanel; // 메인 - 시작 page
	private ArrayList<Crew> crew;
	private CrewListP crewList; // 크루 리스트 page
	private EventSettingP eventSetting; // 이벤트 설정 page
	private PendingApprovalP pendingApproval; // 크루 승인대기 page
	private Map<String, JPanel> panelMap; // 프레임 전환을 위하여 map 사용
	private long sTime1 = 0;
	private AdminEventController aec;

	public AdminViewManager(Main main, AdminEventController aec) {
		this.main = main;
		this.aec = aec;
		aec.getEventDao().loadEvent();
		run();
	}

	// 패널 객체 생성 및 컨트롤러 이어주기
	private void initPanel() {
		crewList = new CrewListP(this, crew);
		eventSetting = new EventSettingP(this, aec);
		pendingApproval = new PendingApprovalP(this, crew, crewList);

		if (main != null)
			panelMap = main.getPanelMap();
		else
			panelMap = new LinkedHashMap<String, JPanel>();
		// frameMap에 crew에서 쓰이는 패널들 다 넣어둠
		panelMap.put("admin_main", mainPanel);
		panelMap.put("admin_list", crewList);
		panelMap.put("admin_eventSetting", eventSetting);
		panelMap.put("admin_pendingApproval", pendingApproval);
	}

	// 패널 전환 메소드
	// panelMap에서 해당 panel을 찾아 setVisible true 후 나머지는 다 false로 변경
	public void convertPanel(String panelName) {

		// 모든 frame setVisible false로 하기
		for (String key : panelMap.keySet()) {
			JPanel panel = panelMap.get(key);

			panel.setVisible(false);
		}

		panelMap.get(panelName).setVisible(true);

	}

	// 특정 프레임에 패널들 다 넣어주는 메소드
	public void addPanels(JFrame frame) {
		for (String key : panelMap.keySet()) {
			JPanel panel = panelMap.get(key);
			panel.setVisible(false);

			frame.getContentPane().add(panel);
		}
	}

	private void initialize() {

		mainPanel = new JPanel();
		mainPanel.setBackground(Color.WHITE);
		mainPanel.setForeground(Color.WHITE);
		mainPanel.setBounds(0, 0, 360, 600);
		mainPanel.setLayout(null);

		JButton btnNewButton = new JButton("EVENT 설정");
		btnNewButton.setFont(new Font("맑은 고딕", Font.BOLD, 30));
		btnNewButton.setBounds(27, 83, 286, 96);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				convertPanel("admin_eventSetting");
			}
		});
		mainPanel.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("EVENT 마감");
		btnNewButton_1.setFont(new Font("맑은 고딕", Font.BOLD, 30));
		btnNewButton_1.setBounds(27, 189, 286, 96);

		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				setsTime1(getEventSetting().getsTimeI()); 
//				long sTime2 = getEventSetting().getsTimeI() / 1000000;
//				long sTime3 = getEventSetting().getsTimeI() - (getEventSetting().getsTimeI() / 1000000) * 1000000;

				sTime1 = aec.getEventDao().getEvent().getEventDate();
				long sTime2 = sTime1 / 1000000;
				long sTime3 = sTime1 - (sTime2) * 1000000;
				System.out.println(sTime1);
				System.out.println(sTime2);
				System.out.println(sTime3);

				long dTimeI = System.currentTimeMillis();
				SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss", Locale.KOREA);
				String dTime = formatter.format(dTimeI);
				long dTime1 = Long.parseLong(dTime);
				long dTime2 = dTime1 / 1000000;
				long dTime3 = dTime1 - (dTime1 / 1000000) * 1000000;
				System.out.println(aec.getEventDao().getEvent().isEventStart());
				if (sTime2 != 0 && sTime2 <= dTime2) {
					if (sTime3 <= dTime3) {
						EventEndAlertD dialog = new EventEndAlertD(eventSetting, aec);
						dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
						dialog.setVisible(true);
					} else {
						if (aec.getEventDao().getEvent().isEventStart()) {
							EventEndAlertD dialog = new EventEndAlertD(eventSetting, aec);
							dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
							dialog.setVisible(true);
						} else {
							ErrorD dialog1 = new ErrorD(eventSetting, aec);
							dialog1.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
							dialog1.setVisible(true);
						}
					}

				} else {
					if (aec.getEventDao().getEvent().isEventStart()) {
						EventEndAlertD dialog = new EventEndAlertD(eventSetting, aec);
						dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
						dialog.setVisible(true);
					} else {
						ErrorD dialog1 = new ErrorD(eventSetting, aec);
						dialog1.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
						dialog1.setVisible(true);
					}
				}
			}
		}); // 알럿은 알림이기때문에 아직 구현 안됨. 수정요망

		mainPanel.add(btnNewButton_1);

		JButton btnNewButton_1_1 = new JButton("크루 승인 관리");
		btnNewButton_1_1.setFont(new Font("맑은 고딕", Font.BOLD, 30));
		btnNewButton_1_1.setBounds(27, 295, 286, 96);
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadCrewList();
				convertPanel("admin_pendingApproval"); // 크루 만들기 page로
			}
		});
		mainPanel.add(btnNewButton_1_1);//

		JButton btnNewButton_1_1_1 = new JButton("전체 크루 명단");
		btnNewButton_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadCrewList();
				convertPanel("admin_list");
			}
		});
		btnNewButton_1_1_1.setFont(new Font("맑은 고딕", Font.BOLD, 30));
		btnNewButton_1_1_1.setBounds(27, 401, 286, 96);
		mainPanel.add(btnNewButton_1_1_1);

		JButton btnNewButton_2 = new JButton("종료");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});// 이전 로그인화면 아니면 종료로 구현
		btnNewButton_2.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		btnNewButton_2.setBounds(216, 512, 97, 23);
		mainPanel.add(btnNewButton_2);

		JLabel lblNewLabel = new JLabel("관리자 메뉴");
		lblNewLabel.setFont(new Font("맑은 고딕", Font.BOLD, 24));
		lblNewLabel.setBounds(104, 16, 128, 57);
		mainPanel.add(lblNewLabel);

//		JLabel lblHome = new JLabel("Home");
//		lblHome.setHorizontalAlignment(SwingConstants.CENTER);
//		lblHome.setBounds(150, 561, 60, 29);
//		lblHome.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mouseClicked(MouseEvent e) {
//				System.out.println("메인 페이지로 이동");
//				main.convertPanel("main");
//			}
//		});
//		mainPanel.add(lblHome);
	}

	public void loadCrewList() {

		crew = new ArrayList<Crew>();

		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("./resources/crewList.dat"))) {

			while (true) {
				crew.add((Crew) ois.readObject());
			}

		} catch (EOFException e) {
			System.out.println("크루 리스트 읽기 완료");
			return;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		loadCrewList();
		initialize();
		initPanel();
	}

	public JPanel getMainPanel() {
		return mainPanel;
	}

	public EventSettingP getEventSetting() {
		return eventSetting;
	}

	public void setEventSetting(EventSettingP eventSetting) {
		this.eventSetting = eventSetting;
	}

	public long getsTime1() {
		return sTime1;
	}

	public void setsTime1(long sTime1) {
		this.sTime1 = sTime1;
	}

}