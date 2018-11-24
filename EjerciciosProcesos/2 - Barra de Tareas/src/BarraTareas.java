import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Insets;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BarraTareas extends JFrame {
	
	/**
	 * Método para abrir una app con ProcesBuilder
	 */
	
	public void OpenApp(String path) {
		try {
			ProcessBuilder pb = new ProcessBuilder(path);
			pb.start();
		} catch (Exception e) {
			System.out.println("Exception "+ e);
		}
	}

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BarraTareas frame = new BarraTareas();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public BarraTareas() {
		setBackground(new Color(240, 240, 240));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 472, 162);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(148, 0, 211));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
	
		/**
		 * Button Firefox
		 */
		JButton btnFireFox = new JButton();
		btnFireFox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				OpenApp("\"C:\\Program Files\\Mozilla Firefox\\firefox.exe\"");
			}
		});
		btnFireFox.setBorderPainted(false);
		btnFireFox.setBorder(null);
		btnFireFox.setMargin(new Insets(0, 0, 0, 0));
		btnFireFox.setContentAreaFilled(false);
		btnFireFox.setFocusPainted(false);
		btnFireFox.setIcon(new ImageIcon(BarraTareas.class.getResource("/images/firefoxLogo.png")));
		btnFireFox.setSelectedIcon(new ImageIcon(BarraTareas.class.getResource("/images/firefoxLogo.png")));

		btnFireFox.setBounds(10, 11, 100, 100);
		contentPane.add(btnFireFox);
		

		/**
		 * Button Eclipse
		 */
		JButton btnEclipse = new JButton();
		btnEclipse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OpenApp("F:\\Users\\Mime\\eclipse\\java-2018-09\\eclipse\\eclipse.exe");
			}
		});
		btnEclipse.setBorderPainted(false);
		btnEclipse.setBorder(null);
		btnEclipse.setMargin(new Insets(0, 0, 0, 0));
		btnEclipse.setContentAreaFilled(false);
		btnEclipse.setFocusPainted(false);
		btnEclipse.setIcon(new ImageIcon(BarraTareas.class.getResource("/images/eclipseLogo.png")));
		btnEclipse.setSelectedIcon(new ImageIcon(BarraTareas.class.getResource("/images/eclipseLogo.png")));
		
		btnEclipse.setBounds(120, 11, 100, 100);
		contentPane.add(btnEclipse);
		
		/**
		 * Button AndroidStudio
		 */
		JButton btnAndroidStudio = new JButton();
		btnAndroidStudio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OpenApp("\"F:\\Program Files\\Android\\Android Studio\\bin\\studio64.exe\"");
			}
		});
		btnAndroidStudio.setBorderPainted(false);
		btnAndroidStudio.setBorder(null);
		btnAndroidStudio.setMargin(new Insets(0, 0, 0, 0));
		btnAndroidStudio.setContentAreaFilled(false);
		btnAndroidStudio.setFocusPainted(false);
		btnAndroidStudio.setIcon(new ImageIcon(BarraTareas.class.getResource("/images/androidStudioLogo.png")));
		btnAndroidStudio.setSelectedIcon(new ImageIcon(BarraTareas.class.getResource("/images/androidStudioLogo.png")));
		
		
		btnAndroidStudio.setBounds(230, 11, 100, 100);
		contentPane.add(btnAndroidStudio);
		
		/**
		 * Button VisualStudio
		 */
		JButton btnVisualStudio = new JButton();
		btnVisualStudio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OpenApp("\"C:\\Program Files (x86)\\Microsoft Visual Studio\\2017\\Enterprise\\Common7\\IDE\\devenv.exe\"");
			}
		});
		btnVisualStudio.setBorderPainted(false);
		btnVisualStudio.setBorder(null);
		btnVisualStudio.setMargin(new Insets(0, 0, 0, 0));
		btnVisualStudio.setContentAreaFilled(false);
		btnVisualStudio.setFocusPainted(false);
		btnVisualStudio.setIcon(new ImageIcon(BarraTareas.class.getResource("/images/visualStudioLogo.png")));
		btnVisualStudio.setSelectedIcon(new ImageIcon(BarraTareas.class.getResource("/images/visualStudioLogo.png")));
		
		btnVisualStudio.setBounds(341, 11, 100, 100);
		contentPane.add(btnVisualStudio);
	}
}
