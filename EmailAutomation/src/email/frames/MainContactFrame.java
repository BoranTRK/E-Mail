package email.frames;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import email.entities.Mail;
import email.entities.User;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import java.util.Iterator;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.RowFilter;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.JTabbedPane;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.awt.SystemColor;
import java.awt.Toolkit;

public class MainContactFrame extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private User user;
	private DefaultTableModel dtm = null;
	private Connection c = null;
	private Statement s = null;
	private ResultSet rs;
	private JTextField txtSearchArea;
	private PreparedStatement ps=null;
	private Mail mail;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					MainContactFrame frame = new MainContactFrame();
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	/**
	 * Create the frame.
	 * 
	 */
	public MainContactFrame() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Boran\\Desktop\\mail_favicon.png"));
		setTitle("E-Mail System - Main Page");
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				getMails();
			}
		});
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 793, 330);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblWelcomeSign = new JLabel("");
		lblWelcomeSign.setBounds(10, 11, 414, 14);
		contentPane.add(lblWelcomeSign);
		lblWelcomeSign.setText("Welcome to the system "+ user.getUser_email());
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(150, 60, 617, 220);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setBackground(SystemColor.info);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				getConnection();
				String id = (String) dtm.getValueAt(table.getSelectedRow(), 0);
				mail.setMail_id(id);
				try {					
					MailViewFrame mvf = new MailViewFrame();
					mvf.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
					mvf.setVisible(true);
					updateReadReceipt();
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		});
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Mail ID","Mail Sender","Mail To" ,"Subject", "Text", "Mail Type", "Receiving Time","Read Receipt"
			}
		));
		scrollPane.setViewportView(table);
		
		JButton btnCreateMail = new JButton("Send Mail!");
		btnCreateMail.setBackground(SystemColor.info);
		btnCreateMail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NewMailFrame mail = new NewMailFrame();
				mail.setVisible(true);
			}
		});
		btnCreateMail.setBounds(10, 257, 130, 23);
		contentPane.add(btnCreateMail);
		
		JButton btnSpam = new JButton("S");
		btnSpam.setBackground(SystemColor.info);
		btnSpam.setToolTipText("Spam");
		btnSpam.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateMailType(btnSpam.getToolTipText());
			}
		});
		btnSpam.setBounds(10, 189, 62, 23);
		contentPane.add(btnSpam);
		
		JButton btnTrash = new JButton("T");
		btnTrash.setBackground(SystemColor.info);
		btnTrash.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateMailType(btnTrash.getToolTipText());
			}
		});
		btnTrash.setToolTipText("Trash");
		btnTrash.setBounds(78, 189, 62, 23);
		contentPane.add(btnTrash);
		
		JButton btnStarred = new JButton("ST");
		btnStarred.setBackground(SystemColor.info);
		btnStarred.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateMailType(btnStarred.getToolTipText());
			}
		});
		btnStarred.setToolTipText("Starred");
		btnStarred.setBounds(10, 223, 62, 23);
		contentPane.add(btnStarred);
		
		txtSearchArea = new JTextField();
		txtSearchArea.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String searcheadText = txtSearchArea.getText();
				searchMail(searcheadText);
			}
		});
		txtSearchArea.setBounds(150, 36, 617, 20);
		contentPane.add(txtSearchArea);
		txtSearchArea.setColumns(10);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBackground(SystemColor.info);
		tabbedPane.setBounds(10, 27, 130, 124);
		contentPane.add(tabbedPane);
		
		JList<?> listOfMailTypes = new JList();
		listOfMailTypes.setBackground(SystemColor.info);
		tabbedPane.addTab("New tab", null, listOfMailTypes, null);
		listOfMailTypes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String mailType = listOfMailTypes.getSelectedValue().toString();
				if (mailType == "Sent") {
					clearTable();
					getSentMails();
				}
				else if(mailType == "All Mails") {
					clearTable();
					getMails();
				}
				else {
					clearTable();
					getMailsByType(mailType);	
				}
			}
		});
		listOfMailTypes.setVisibleRowCount(5);
		listOfMailTypes.setModel(new AbstractListModel() {
			String[] values = new String[] {"", "All Mails", "Starred", "Spam", "Trash", "Sent"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		
		JButton btnDeleteMail = new JButton("D");
		btnDeleteMail.setBackground(SystemColor.info);
		btnDeleteMail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dtm = (DefaultTableModel)table.getModel();
				String getMailId = (String) dtm.getValueAt(table.getSelectedRow(), 0);
				deleteMail(getMailId);
			}
		});
		btnDeleteMail.setToolTipText("Delete");
		btnDeleteMail.setBounds(78, 223, 62, 23);
		contentPane.add(btnDeleteMail);
		
		JButton btnDashboard = new JButton("Dashboard");
		btnDashboard.setBackground(SystemColor.info);
		btnDashboard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DashboardFrame dashboard = new DashboardFrame();
				dashboard.setVisible(true);
				dashboard.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			}
		});
		btnDashboard.setBounds(10, 155, 130, 23);
		contentPane.add(btnDashboard);
	}
	
	public void getConnection() {
		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/GorselMailProjesi", "postgres", "12345");
			s = c.createStatement();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
	
	public void getMails() {
		try {
			getConnection();
			String sql = "SELECT mail_id,mail_sender, mail_to, "
					+ "mail_subject, mail_text, mail_type,"
					+ " mail_receiving_time, mail_read_receipt FROM public.mails WHERE mail_to = '"+user.getUser_email()+"';";
			rs = s.executeQuery(sql);
			while(rs.next()) {
				String mail_id = String.valueOf(rs.getInt("mail_id"));
				String mail_sender =rs.getString("mail_sender");
				String mail_to = rs.getString("mail_to");
				String mail_subject = rs.getString("mail_subject");
				String mail_text = rs.getString("mail_text");
				String mail_type = rs.getString("mail_type");
				String mail_receiving_time = rs.getString("mail_receiving_time");
				String mail_read_receipt = rs.getString("mail_read_receipt");
				
				String datas[] = {mail_id,mail_sender,mail_to,mail_subject,mail_text,mail_type,mail_receiving_time,mail_read_receipt};
				dtm = (DefaultTableModel)table.getModel();
				dtm.addRow(datas);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
	
	public void searchMail(String text) {
		dtm = (DefaultTableModel)table.getModel();
		TableRowSorter<DefaultTableModel> tableRowSorter = new TableRowSorter<>(dtm);
		table.setRowSorter(tableRowSorter);
		tableRowSorter.setRowFilter(RowFilter.regexFilter(text));
	}
	
	public void updateMailType(String buttonName) {
		dtm = (DefaultTableModel)table.getModel();
		String getId = (String) dtm.getValueAt(table.getSelectedRow(), 0);
		try {
			getConnection();
			String sql = "UPDATE public.mails SET mail_type='"+buttonName+"' WHERE mail_id = '"+getId+"';";
			PreparedStatement presta = c.prepareStatement(sql);
			int executed = presta.executeUpdate();
			if (executed>0) {
				JOptionPane.showMessageDialog(null, "Passed");
				dtm.setRowCount(0);
				getMails();
				}
			else JOptionPane.showMessageDialog(null, "Failed");
		} catch (Exception e2) {
			JOptionPane.showMessageDialog(null,e2.getMessage());
		}
	}
	
	public void getMailsByType(String type) {

		try {
			getConnection();
			String sql = "SELECT * FROM public.mails WHERE mail_type = '"+type+"';";
			rs = s.executeQuery(sql);
			while(rs.next()) {
				String mail_id = String.valueOf(rs.getInt("mail_id"));
				String mail_sender =rs.getString("mail_sender");
				String mail_to = rs.getString("mail_to");
				String mail_subject = rs.getString("mail_subject");
				String mail_text = rs.getString("mail_text");
				String mail_type = rs.getString("mail_type");
				String mail_receiving_time = rs.getString("mail_receiving_time");
				String mail_read_receipt = rs.getString("mail_read_receipt");
				
				String datas[] = {mail_id,mail_sender,mail_to,mail_subject,mail_text,mail_type,mail_receiving_time,mail_read_receipt};
				dtm = (DefaultTableModel)table.getModel();
				dtm.addRow(datas);
			}
		} catch (Exception e2) {
			JOptionPane.showMessageDialog(null, e2.getMessage());
		}
	}
	
	public void getSentMails() {
		try {
			getConnection();
			String sql = "SELECT mail_id,mail_sender, mail_to, "
					+ "mail_subject, mail_text, mail_type,"
					+ " mail_receiving_time, mail_read_receipt FROM public.mails WHERE mail_sender = '"+user.getUser_email()+"';";
			rs = s.executeQuery(sql);
			while(rs.next()) {
				String mail_id = String.valueOf(rs.getInt("mail_id"));
				String mail_sender =rs.getString("mail_sender");
				String mail_to = rs.getString("mail_to");
				String mail_subject = rs.getString("mail_subject");
				String mail_text = rs.getString("mail_text");
				String mail_type = rs.getString("mail_type");
				String mail_receiving_time = rs.getString("mail_receiving_time");
				String mail_read_receipt = rs.getString("mail_read_receipt");
				
				String datas[] = {mail_id,mail_sender,mail_to,mail_subject,mail_text,mail_type,mail_receiving_time,mail_read_receipt};
				dtm = (DefaultTableModel)table.getModel();
				dtm.addRow(datas);
			}			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
	
	public void deleteMail(String deletedMailId) {
		try {
			getConnection();
			String sql = "DELETE FROM public.mails Where mail_id = '"+deletedMailId+"'";
			ps = c.prepareStatement(sql);
			int returnedValue = ps.executeUpdate();
			if (returnedValue>0) {
				JOptionPane.showMessageDialog(null, "Mail Has Been Deleted");
			}
			else JOptionPane.showMessageDialog(null, "Failed to Delete Mail","Information", JOptionPane.ERROR_MESSAGE);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
	
	public void clearTable() {
		dtm = (DefaultTableModel)table.getModel();
		dtm.setRowCount(0);
	}
	
	public void updateReadReceipt() {
		dtm = (DefaultTableModel)table.getModel();
		String getId = (String) dtm.getValueAt(table.getSelectedRow(), 0);
		try {
			getConnection();
			String sql = "UPDATE public.mails SET mail_read_receipt= 'True' WHERE mail_id = '"+getId+"';";
			PreparedStatement presta = c.prepareStatement(sql);
			int executed = presta.executeUpdate();
		} catch (Exception e2) {
			JOptionPane.showMessageDialog(null,e2.getMessage());
		}
	}
}
