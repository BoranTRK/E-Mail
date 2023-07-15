package email.frames;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JProgressBar;
import javax.swing.JMenuBar;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.SystemColor;
import java.awt.Toolkit;

public class DashboardFrame extends JDialog {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DashboardFrame frame = new DashboardFrame();
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
	public DashboardFrame() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Boran\\Desktop\\mail_favicon.png"));
		setTitle("Dashboard\r\n");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 578, 311);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 0, 0));
		panel.setBounds(21, 11, 154, 75);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JProgressBar progressBar = new JProgressBar();
		progressBar.setValue(35);
		progressBar.setMinimum(30);
		progressBar.setBounds(0, 61, 154, 24);
		panel.add(progressBar);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(new Color(64, 128, 128));
		menuBar.setBounds(0, 0, 154, 22);
		panel.add(menuBar);
		
		JLabel lblGonderilenMailSayisi = new JLabel("Gönderilen Mail Sayısı");
		lblGonderilenMailSayisi.setForeground(new Color(255, 255, 255));
		menuBar.add(lblGonderilenMailSayisi);
		
		JLabel lblSayi = new JLabel("4");
		lblSayi.setHorizontalAlignment(SwingConstants.CENTER);
		lblSayi.setBounds(43, 11, 68, 53);
		panel.add(lblSayi);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBackground(new Color(255, 128, 0));
		panel_1.setBounds(213, 11, 154, 75);
		contentPane.add(panel_1);
		
		JProgressBar progressBar_1 = new JProgressBar();
		progressBar_1.setValue(40);
		progressBar_1.setMinimum(30);
		progressBar_1.setBounds(0, 61, 154, 24);
		panel_1.add(progressBar_1);
		
		JMenuBar menuBar_1 = new JMenuBar();
		menuBar_1.setBackground(new Color(64, 128, 128));
		menuBar_1.setBounds(0, 0, 154, 22);
		panel_1.add(menuBar_1);
		
		JLabel lblSpamSayisi = new JLabel("Spama Atılan Mail Sayısı");
		lblSpamSayisi.setForeground(new Color(255, 255, 255));
		menuBar_1.add(lblSpamSayisi);
		
		JLabel lblSayi_1 = new JLabel("2");
		lblSayi_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblSayi_1.setBounds(43, 11, 68, 53);
		panel_1.add(lblSayi_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBackground(new Color(255, 255, 0));
		panel_2.setBounds(398, 11, 154, 75);
		contentPane.add(panel_2);
		
		JProgressBar progressBar_2 = new JProgressBar();
		progressBar_2.setValue(35);
		progressBar_2.setMinimum(30);
		progressBar_2.setBounds(0, 61, 154, 24);
		panel_2.add(progressBar_2);
		
		JMenuBar menuBar_2 = new JMenuBar();
		menuBar_2.setBackground(new Color(64, 128, 128));
		menuBar_2.setBounds(0, 0, 154, 22);
		panel_2.add(menuBar_2);
		
		JLabel lblYildizliSayisi = new JLabel("Yıldızlanan Mail Sayısı\r\n");
		lblYildizliSayisi.setForeground(new Color(255, 255, 255));
		menuBar_2.add(lblYildizliSayisi);
		
		JLabel lblSayi_2 = new JLabel("1");
		lblSayi_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblSayi_2.setBounds(43, 11, 68, 53);
		panel_2.add(lblSayi_2);
		
		JPanel panel_2_1 = new JPanel();
		panel_2_1.setLayout(null);
		panel_2_1.setBackground(new Color(0, 128, 255));
		panel_2_1.setBounds(21, 125, 154, 75);
		contentPane.add(panel_2_1);
		
		JProgressBar progressBar_2_1 = new JProgressBar();
		progressBar_2_1.setValue(35);
		progressBar_2_1.setMinimum(30);
		progressBar_2_1.setBounds(0, 61, 154, 24);
		panel_2_1.add(progressBar_2_1);
		
		JMenuBar menuBar_2_1 = new JMenuBar();
		menuBar_2_1.setBackground(new Color(64, 128, 128));
		menuBar_2_1.setBounds(0, 0, 154, 22);
		panel_2_1.add(menuBar_2_1);
		
		JLabel lblSilinenMail = new JLabel("Silinen Mail Sayısı");
		lblSilinenMail.setForeground(new Color(255, 255, 255));
		menuBar_2_1.add(lblSilinenMail);
		
		JLabel lblSayi_2_1 = new JLabel("1");
		lblSayi_2_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblSayi_2_1.setBounds(43, 11, 68, 53);
		panel_2_1.add(lblSayi_2_1);
		
		JPanel panel_2_1_1 = new JPanel();
		panel_2_1_1.setLayout(null);
		panel_2_1_1.setBackground(new Color(128, 128, 255));
		panel_2_1_1.setBounds(213, 125, 154, 75);
		contentPane.add(panel_2_1_1);
		
		JProgressBar progressBar_2_1_1 = new JProgressBar();
		progressBar_2_1_1.setValue(45);
		progressBar_2_1_1.setMinimum(30);
		progressBar_2_1_1.setBounds(0, 61, 154, 24);
		panel_2_1_1.add(progressBar_2_1_1);
		
		JMenuBar menuBar_2_1_1 = new JMenuBar();
		menuBar_2_1_1.setBackground(new Color(64, 128, 128));
		menuBar_2_1_1.setBounds(0, 0, 154, 22);
		panel_2_1_1.add(menuBar_2_1_1);
		
		JLabel lblKullaniciSayisi = new JLabel("Kullanıcı Sayısı");
		lblKullaniciSayisi.setForeground(new Color(255, 255, 255));
		menuBar_2_1_1.add(lblKullaniciSayisi);
		
		JLabel lblSayi_2_1_1 = new JLabel("5");
		lblSayi_2_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblSayi_2_1_1.setBounds(43, 11, 68, 53);
		panel_2_1_1.add(lblSayi_2_1_1);
		
		JPanel panel_2_1_2 = new JPanel();
		panel_2_1_2.setLayout(null);
		panel_2_1_2.setBackground(new Color(128, 255, 0));
		panel_2_1_2.setBounds(398, 125, 154, 75);
		contentPane.add(panel_2_1_2);
		
		JProgressBar progressBar_2_1_2 = new JProgressBar();
		progressBar_2_1_2.setValue(45);
		progressBar_2_1_2.setMinimum(30);
		progressBar_2_1_2.setBounds(0, 61, 154, 24);
		panel_2_1_2.add(progressBar_2_1_2);
		
		JMenuBar menuBar_2_1_2 = new JMenuBar();
		menuBar_2_1_2.setBackground(new Color(64, 128, 128));
		menuBar_2_1_2.setBounds(0, 0, 154, 22);
		panel_2_1_2.add(menuBar_2_1_2);
		
		JLabel lblKullanimOrani = new JLabel("Kullanım Oranı");
		lblKullanimOrani.setForeground(new Color(255, 255, 255));
		menuBar_2_1_2.add(lblKullanimOrani);
		
		JLabel lblSayi_2_1_2 = new JLabel("%45");
		lblSayi_2_1_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblSayi_2_1_2.setBounds(43, 11, 68, 53);
		panel_2_1_2.add(lblSayi_2_1_2);
	}
}
