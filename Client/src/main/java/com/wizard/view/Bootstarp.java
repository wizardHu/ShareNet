package com.wizard.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import org.apache.log4j.PropertyConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.wizard.common.SpringUtil;

public class Bootstarp extends JFrame {

	private final Logger logger = LoggerFactory.getLogger(Bootstarp.class);
	private JPanel contentPane;
	private JPanel panel_left;
	private JPanel panel_url;
	private JTextField text_url;
	private JButton button_down;
	private JPanel panel_center;
	private JPanel panel_right;
	private JLabel lblNewLabel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Bootstarp frame = new Bootstarp();
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
	public Bootstarp() {
		
		initView();
		initLog();
		initSpring();
	}

	private void initView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		panel_left = new JPanel();
		panel_left.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "\u6211\u7684\u4E0B\u8F7D", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPane.add(panel_left, BorderLayout.WEST);
		panel_left.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		panel_url = new JPanel();
		panel_left.add(panel_url);
		
		text_url = new JTextField();
		panel_url.add(text_url);
		text_url.setPreferredSize(new Dimension(200, 30));
		
		button_down = new JButton("下载");
		panel_url.add(button_down);
		button_down.setPreferredSize(new Dimension(60, 30));
		
		panel_center = new JPanel();
		panel_center.setBorder(new TitledBorder(null, "IP\u5217\u8868", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPane.add(panel_center, BorderLayout.CENTER);
		
		panel_right = new JPanel();
		panel_right.setBorder(new TitledBorder(null, "\u8FDC\u7A0B\u4E0B\u8F7D", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPane.add(panel_right, BorderLayout.EAST);
		
		lblNewLabel = new JLabel("New label");
		lblNewLabel.setPreferredSize(new Dimension(260, 30));
		panel_right.add(lblNewLabel);
		setLocationRelativeTo(null); 
		setResizable(false);
		
	}
	
	private void initLog(){
		PropertyConfigurator.configure("conf/log4j.properties");
	}
	
	/**
	 * 启动spring
	 */
	private void initSpring() {
		
		ApplicationContext ctx;
		try {
			ctx = new FileSystemXmlApplicationContext("/conf/spring.xml");
		} catch (BeansException e) {

			logger.error("initSpring() error: ", e);

			throw e;
		}
		logger.info("Spring容器启动完成！");
		SpringUtil.setSpringContext(ctx);
	}

}
