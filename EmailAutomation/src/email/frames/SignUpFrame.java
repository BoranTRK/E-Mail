package email.frames;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import java.awt.Toolkit;

public class SignUpFrame extends JFrame {

	private JPanel contentPane;
	private JTextField txtName;
	private JTextField txtEmail;
	private JTextField txtSurname;
	private JTextField txtDateOfBirth;
	private JPasswordField txtPasswordField;
	private JTextField txtPhoneNumber;
	private Connection c = null;
	private Statement s = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SignUpFrame frame = new SignUpFrame();
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
	public SignUpFrame() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Boran\\Desktop\\signUp2.png"));
		setTitle("Sign Up For Student E-Mail System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 698, 372);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		Image img = new ImageIcon(this.getClass().getResource("/signup.png")).getImage();
	
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.info);
		panel.setBounds(10, 37, 662, 285);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblIcon = new JLabel("");
		lblIcon.setBounds(396, 33, 284, 217);
		panel.add(lblIcon);
		lblIcon.setIcon(new ImageIcon(img));
		
		JButton btnSignUp = new JButton("Sign Up!");
		btnSignUp.setBounds(115, 251, 253, 23);
		panel.add(btnSignUp);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(115, 18, 46, 14);
		panel.add(lblName);
		
		txtName = new JTextField();
		txtName.setBounds(115, 34, 86, 20);
		panel.add(txtName);
		txtName.setColumns(10);
		
		JLabel lblSurname = new JLabel("Surname");
		lblSurname.setBounds(211, 18, 74, 14);
		panel.add(lblSurname);
		
		txtSurname = new JTextField();
		txtSurname.setBounds(211, 32, 86, 20);
		panel.add(txtSurname);
		txtSurname.setColumns(10);
		
		JLabel lblEmail = new JLabel("E-Mail");
		lblEmail.setBounds(115, 65, 46, 14);
		panel.add(lblEmail);
		
		txtEmail = new JTextField();
		txtEmail.setBounds(115, 78, 181, 20);
		panel.add(txtEmail);
		txtEmail.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(115, 109, 74, 14);
		panel.add(lblPassword);
		
		txtPasswordField = new JPasswordField();
		txtPasswordField.setBounds(115, 123, 182, 20);
		panel.add(txtPasswordField);
		
		JLabel lblGender = new JLabel("Gender");
		lblGender.setBounds(115, 154, 46, 14);
		panel.add(lblGender);
		
		JComboBox genderComboBox = new JComboBox();
		genderComboBox.setBounds(115, 169, 126, 22);
		panel.add(genderComboBox);
		genderComboBox.setModel(new DefaultComboBoxModel(new String[] {"", "Male", "Female", "Dont want to specify"}));
		
		JLabel lblDateOfBirth = new JLabel("Date Of Birth");
		lblDateOfBirth.setBounds(251, 157, 86, 14);
		panel.add(lblDateOfBirth);
		
		txtDateOfBirth = new JTextField();
		txtDateOfBirth.setBounds(251, 171, 117, 20);
		panel.add(txtDateOfBirth);
		txtDateOfBirth.setColumns(10);
		
		JLabel lblPhoneNumber = new JLabel("Phone Number");
		lblPhoneNumber.setBounds(115, 202, 86, 14);
		panel.add(lblPhoneNumber);
		
		txtPhoneNumber = new JTextField();
		txtPhoneNumber.setBounds(115, 215, 117, 20);
		panel.add(txtPhoneNumber);
		txtPhoneNumber.setColumns(10);
		
		JLabel lblIconRight = new JLabel("");
		lblIconRight.setBounds(10, 0, 110, 134);
		Image img2 = new ImageIcon(this.getClass().getResource("/signUp2.png")).getImage();
		lblIconRight.setIcon(new ImageIcon(img2));
		panel.add(lblIconRight);
		
		JLabel lblExplanation = new JLabel("If you don't have any email address, you can sign up here!");
		lblExplanation.setBounds(10, 11, 404, 14);
		contentPane.add(lblExplanation);
		btnSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String user_name = txtName.getText();
				String user_surname = txtSurname.getText();
				String user_mail="";
				String user_password = txtPasswordField.getText();
				String user_gender;
				if (genderComboBox.getSelectedIndex() == 1) {
					user_gender = "Male";
				}
				else if (genderComboBox.getSelectedIndex() == 2) {
					user_gender = "Female";
				}
				else user_gender = "Dont wan to specify";
				String user_phone_number = txtPhoneNumber.getText();
				String user_date_of_birth = txtDateOfBirth.getText();
				try {
					if (txtEmail.getText().contains("@") && txtEmail.getText().contains(".com")) {
						user_mail = txtEmail.getText();
						Class.forName("org.postgresql.Driver");
						c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/GorselMailProjesi", "postgres", "12345");
						s = c.createStatement();
						String sql = "INSERT INTO public.users(user_id, user_name, user_surname, user_email, user_password, user_gender, user_phone_number, user_date_of_birth)\r\n"
								+ "	VALUES (nextval('u_id'), '"+user_name+"', '"+user_surname+"'"
										+ ", '"+user_mail+"', '"+user_password+"'"
												+ ",'"+user_gender+"' , '"+user_phone_number+"', '"+user_date_of_birth+"');";
						int inserted = s.executeUpdate(sql);
						if (inserted>0) {
							JOptionPane.showMessageDialog(null, "You Have Successfully Registered", 
									"Information Message", JOptionPane.INFORMATION_MESSAGE);
						}
					}
					else {JOptionPane.showMessageDialog(null, "Your Email Address Does Not Contain @ or .com");
						  JOptionPane.showMessageDialog(null, "Registration Failed", 
							"Message", JOptionPane.ERROR_MESSAGE);
					}
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, e2.getMessage());
				}
			}
		});
	}
}
