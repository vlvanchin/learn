package com.my.isms;

import java.awt.Color;
import java.awt.Container;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.JButton;
import javax.swing.JFrame;

public class ISmsSender {

	public static void main(String[] args) {
		try {
			final URI ismsURL = new URI("http://www.isms.com.my/isms_send.php?un=vlvanchin&pwd=xxxx&dstno=xxxx&msg=Hi..&type=1&sendid=xxxx");
			
			JFrame frame = new JFrame("iSMS");
			frame.setSize(200, 200);
			frame.setVisible(true);
			frame.setResizable(true);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			Container contentPane;
			contentPane = frame.getContentPane();
			
			JButton sendButton = new JButton("send Isms");
			sendButton.setForeground(Color.RED);
			sendButton.setBounds(0,0,200,200);
			sendButton.addActionListener(new ActionListener() {			
				@Override
				public void actionPerformed(ActionEvent e) {
					if (Desktop.isDesktopSupported()) {
						Desktop desktop = Desktop.getDesktop();
						try {
							desktop.browse(ismsURL);
						} catch (Exception xe) {
							
						}
					}
				}
			});
			frame.add(sendButton);
		} catch (URISyntaxException e) {
			
		}
	}

}
