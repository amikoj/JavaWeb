package cn.enjoytoday;

import java.applet.Applet;
import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 
 * @author hfcai
 *java applet
 */
public class HelloApplet extends Applet implements Runnable{
	
	
	private int fontsize=8;
	private Thread thread;
	private boolean stopFlag=true;
	private Button controlButton=new Button("Begin show");
	
 @Override
	public void init() {
		
		controlButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (stopFlag) {
					start();
				}else {
					stop();
				}
			}
		});
		
		setBackground(Color.WHITE);
		add(controlButton);
		
		
		
	}
	
 @Override
 public void start(){
	 thread=new Thread(this);
	 stopFlag=false;
	 fontsize=8;
	 controlButton.setLabel("stop show");
	 thread.start();	 
 }
	
	
 @Override
public void stop() {

	 stopFlag=true;
	 controlButton.setLabel("Begin show");
}
 
 
 @Override
public void paint(Graphics g) {
	super.paint(g);
   g.setFont(new Font("newFont", Font.BOLD, fontsize));
   g.drawString("Hello", 30, 80);
}
	
	
	

	@Override
	public void run() {
		while (!stopFlag) {
		
			repaint();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (fontsize++>40) {
				fontsize=8;
			}
			
		}
		
	}
	

}
