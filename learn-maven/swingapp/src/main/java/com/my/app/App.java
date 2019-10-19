package com.my.app;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	JFrame app = new JFrame("HelloWorld");
    	app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	app.setTitle("My First HelloWorld Program");
    	JLabel label = new JLabel();
    	label.setText("This is a sample program");
    	app.setLayout(new BorderLayout());
    	app.add(label);
    	app.setSize(300, 100);
    	app.setVisible(true);
    }
}
