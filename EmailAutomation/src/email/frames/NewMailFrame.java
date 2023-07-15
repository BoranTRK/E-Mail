package email.frames;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import email.entities.Mail;
import email.entities.User;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.print.attribute.standard.DateTimeAtCompleted;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import java.awt.SystemColor;
import java.awt.Toolkit;

public class NewMailFrame extends JDialog {

	private JPanel contentPane;
	private JTextField txtTo;
	private JTextField txtSubject;
	private Connection c=null;
	private Statement s = null;
	private static User user;
	private static Mail mail;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NewMailFrame frame = new NewMailFrame();
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
	public NewMailFrame() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Boran\\Desktop\\mail.png"));
		setTitle("Create a new mail");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 353, 354);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTo = new JLabel("To");
		lblTo.setBounds(10, 11, 46, 14);
		contentPane.add(lblTo);
		
		txtTo = new JTextField();
		txtTo.setBackground(SystemColor.info);
		txtTo.setBounds(70, 8, 257, 20);
		contentPane.add(txtTo);
		txtTo.setColumns(10);
		
		JLabel lblSubject = new JLabel("Subject");
		lblSubject.setBounds(10, 44, 46, 14);
		contentPane.add(lblSubject);
		
		txtSubject = new JTextField();
		txtSubject.setBackground(SystemColor.info);
		txtSubject.setBounds(70, 41, 257, 20);
		contentPane.add(txtSubject);
		txtSubject.setColumns(10);
		
		JTextArea txtTextArea = new JTextArea();
		txtTextArea.setBackground(SystemColor.info);
		txtTextArea.setBounds(10, 89, 317, 181);
		contentPane.add(txtTextArea);
		
		JButton btnSend = new JButton("Send!");
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mail = new Mail();
				String to = txtTo.getText();
				String subject = txtSubject.getText();
				String textArea = txtTextArea.getText();
				try {
					getConnection();
					String sql = "INSERT INTO public.mails(mail_id, mail_sender, mail_to, mail_subject, "
							+ "mail_text, mail_type, mail_receiving_time, mail_read_receipt)\r\n"
							+ "	VALUES (nextval('m_id'), '"+user.getUser_email()+"', '"+to+"', "
									+ "'"+subject+"', '"+textArea+"', '"+null+"', '"+LocalDateTime.now()+"', '"+false+"');";
					int inserted = s.executeUpdate(sql);
					if (inserted>0) {
						JOptionPane.showMessageDialog(null, "You Have Successfully Sent Your Mail", 
								"Information Message", JOptionPane.INFORMATION_MESSAGE);
					}
					else JOptionPane.showMessageDialog(null, "There was a problem sending the mail", "Message", JOptionPane.ERROR_MESSAGE);
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, e2.getMessage());
				}
			}
		});
		btnSend.setForeground(new Color(255, 255, 255));
		btnSend.setBackground(new Color(0, 128, 128));
		btnSend.setBounds(10, 281, 89, 23);
		contentPane.add(btnSend);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtTo.setText("");
				txtSubject.setText("");
				txtTextArea.setText("");
				JOptionPane.showMessageDialog(null, "All Fields Cleared!","FIELD CLEAR INFORMATION", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		btnClear.setForeground(new Color(255, 255, 255));
		btnClear.setBackground(new Color(255, 0, 0));
		btnClear.setBounds(238, 282, 89, 23);
		contentPane.add(btnClear);
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
}
