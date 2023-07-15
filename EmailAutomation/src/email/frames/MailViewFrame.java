package email.frames;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import email.entities.Mail;
import email.entities.User;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.SystemColor;
import java.awt.Toolkit;

public class MailViewFrame extends JDialog {

	private JPanel contentPane;
	private Mail mail;
	private JTextField txtTo;
	private JTextField txtSubject;
	private JTextField txtText;
	private Connection c=null;
	private Statement s = null;
	private ResultSet rs = null;
	private JLabel lblInformation;
	private User user;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MailViewFrame frame = new MailViewFrame();
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
	public MailViewFrame() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Boran\\Desktop\\mail_favicon.png"));
		setTitle(user.getUser_email()+"'s mail");
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				showMailById();
			}
		});
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 479, 300);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTo = new JLabel("To");
		lblTo.setBounds(10, 47, 46, 14);
		contentPane.add(lblTo);
		
		JLabel lblSubject = new JLabel("Subject");
		lblSubject.setBounds(10, 72, 46, 14);
		contentPane.add(lblSubject);
		
		JLabel lblMailText = new JLabel("Text");
		lblMailText.setBounds(10, 97, 46, 14);
		contentPane.add(lblMailText);
		
		txtTo = new JTextField();
		txtTo.setBackground(SystemColor.info);
		txtTo.setEditable(false);
		txtTo.setBounds(66, 44, 230, 20);
		contentPane.add(txtTo);
		txtTo.setColumns(10);
		
		txtSubject = new JTextField();
		txtSubject.setBackground(SystemColor.info);
		txtSubject.setEditable(false);
		txtSubject.setBounds(66, 69, 230, 20);
		contentPane.add(txtSubject);
		txtSubject.setColumns(10);
		
		txtText = new JTextField();
		txtText.setBackground(SystemColor.info);
		txtText.setEditable(false);
		txtText.setBounds(10, 122, 414, 128);
		contentPane.add(txtText);
		txtText.setColumns(10);
		
		lblInformation = new JLabel("");
		lblInformation.setBounds(10, 11, 443, 14);
		contentPane.add(lblInformation);
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
	
	public void showMailById() {
		try {
			getConnection();
			String sql = "SELECT mail_to, mail_sender, mail_subject, mail_text, mail_receiving_time "
					+ "FROM public.mails WHERE mail_id = '"+mail.getMail_id()+"';";
			rs = s.executeQuery(sql);
			while(rs.next()) {
				String mail_to = rs.getString("mail_to");
				String mail_sender = rs.getString("mail_sender");
				String mail_subject = rs.getString("mail_subject");
				String mail_text = rs.getString("mail_text");
				String mail_receiving_time = rs.getString("mail_receiving_time");
				
				lblInformation.setText("This e-mail was sent by '"+mail_sender+"' at '"+mail_receiving_time+"'");
										
				txtTo.setText(mail_to);
				txtSubject.setText(mail_subject);
				txtText.setText(mail_text);
			}
		} catch (Exception e2) {
			JOptionPane.showMessageDialog(null, e2.getMessage());
		}
	}
}
