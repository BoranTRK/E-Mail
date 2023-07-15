package email.frames;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import email.entities.User;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.JPasswordField;
import java.awt.SystemColor;
import javax.swing.SwingConstants;
import java.awt.Toolkit;

public class LogInFrame extends JFrame {

	private JPanel contentPane;
	private JTextField txtEmail;
	private Connection c=null;
	private Statement s=null;
	private ResultSet rs;
	private static User user;
	private JPasswordField txtPassword;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LogInFrame frame = new LogInFrame();
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
	public LogInFrame() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Boran\\Desktop\\mail_favicon.png"));
		setTitle("Welcome Page - E-Mail System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 560, 320);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblWelcomeText = new JLabel("Welcome to the our new email system!");
		lblWelcomeText.setBounds(20, 11, 230, 14);
		contentPane.add(lblWelcomeText);
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.info);
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(20, 36, 265, 214);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton btnLogIn = new JButton("Log In");
		btnLogIn.setBackground(new Color(107, 142, 35));
		btnLogIn.setForeground(Color.WHITE);
		btnLogIn.setBounds(10, 130, 194, 23);
		panel.add(btnLogIn);
		
		txtPassword = new JPasswordField();
		txtPassword.setForeground(new Color(0, 0, 0));
		txtPassword.setBounds(10, 92, 165, 20);
		panel.add(txtPassword);
		
		JButton btnSignUp = new JButton("Sign Up!");
		btnSignUp.setBounds(10, 164, 194, 23);
		panel.add(btnSignUp);
		btnSignUp.setForeground(new Color(255, 255, 255));
		btnSignUp.setBackground(new Color(128, 0, 0));
		
		JLabel lblEmail = new JLabel("E-Mail");
		lblEmail.setBounds(10, 11, 46, 14);
		panel.add(lblEmail);
		lblEmail.setBackground(new Color(0, 0, 0));
		lblEmail.setForeground(new Color(0, 0, 0));
		
		txtEmail = new JTextField();
		txtEmail.setBounds(10, 36, 168, 20);
		panel.add(txtEmail);
		txtEmail.setForeground(new Color(0, 0, 0));
		txtEmail.setBackground(new Color(255, 255, 255));
		txtEmail.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(10, 67, 67, 14);
		panel.add(lblPassword);
		lblPassword.setForeground(new Color(0, 0, 0));
		
		JLabel lblIcon = new JLabel("");
		Image img = new ImageIcon(this.getClass().getResource("/mail.png")).getImage();
		lblIcon.setIcon(new ImageIcon(img));
		lblIcon.setBounds(365, 24, 230, 214);
		contentPane.add(lblIcon);
		
		
		btnSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SignUpFrame signUpFrame = new SignUpFrame();
				signUpFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				signUpFrame.setVisible(true);
			}
		});
		btnLogIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				logIn();
			}
		});
	}
	
	public void getConnection() {
		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/GorselMailProjesi", "postgres", "12345");
			s = c.createStatement();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public void logIn() {
		user = new User();
		String email = txtEmail.getText();
		user.setUser_email(email);
		String password = txtPassword.getText();
		try {
			getConnection();
			String sql = "SELECT user_email, user_password FROM public.users "
					+ "WHERE user_email='"+email+"' AND user_password = '"+password+"';";
			rs = s.executeQuery(sql);
			if (rs.next()) {
				MainContactFrame mainContactFrame = new MainContactFrame();
				mainContactFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				mainContactFrame.setVisible(true);
				dispose();
			}
			else {
				JOptionPane.showMessageDialog(null, "Email or password did not match! \nPlease try again!"
						,"User Error", JOptionPane.ERROR_MESSAGE);
			}
		} catch (Exception e2) {
			JOptionPane.showMessageDialog(null, e2.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
		}
	}
}
