/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clock;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.Toolkit;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
import java.awt.geom.Arc2D;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

/**
 *
 * @author L440
 */

enum TimeMeasure{
    seconds, minutes, hours
}

public class Clock extends JFrame implements Runnable{
    long durationInMillis;
    int seconds = 0;
    int minutes = 0;
    int hours = 0;
    int millis = 0;
    
    final int delay = 1000;
    
    int diameter = 500;
    
    Image imgHours = null;
    String hoursPath = "C:\\Users\\L440\\Documents\\NetBeansProjects\\Clock\\img\\1.jpg";
    
    Image imgMinutes = null;
    String minutesPath = "C:\\Users\\L440\\Documents\\NetBeansProjects\\Clock\\img\\3.jpg";
    
    Image imgSeconds = null;
    String secondsPath = "C:\\Users\\L440\\Documents\\NetBeansProjects\\Clock\\img\\2.jpg";
    
    public Clock(){
        super();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setResizable(false);
        this.setSize(540, 540);
        this.setLocationByPlatform(true);
        this.setVisible(true);
        imgHours = Toolkit.getDefaultToolkit().getImage(
                                        secondsPath);
        imgMinutes = Toolkit.getDefaultToolkit().getImage(
                                        minutesPath);
        imgSeconds = Toolkit.getDefaultToolkit().getImage(
                                        hoursPath);
        show();
        this.run();
    }
    
    public void paint(Graphics g){
        Graphics2D g2D = (Graphics2D) g;
        g.drawOval(20, 20, diameter, diameter);
        g.setColor(new Color(250,250,250));
        g.fillRect(0, 0, diameter+40, diameter+40);
        Graphics2D g2 = (Graphics2D) g;
        
        /*g.setColor(Color.red);
        
        g.fillArc(20,20, diameter, diameter, 
                (int)(90-hours*30), 
                360-(360/12));
        
        g.setColor(Color.yellow);
        g.fillArc(20+(diameter*1/3)/2,20+(diameter*1/3)/2, diameter*2/3, diameter*2/3, 
                (int) (90-minutes*6), 
                360-(360/12));
        
        g.setColor(Color.blue);
        g.fillArc(20+(diameter*2/3)/2,20+(diameter*2/3)/2, diameter/3, diameter/3, 
                (int) (90-seconds*6), 
                360-(360/12));*/
        
        Arc2D secondsA = new java.awt.geom.Arc2D.Float(20+(diameter*2/3)/2,20+(diameter*2/3)/2, diameter/3, diameter/3, 
                                                    (int) (90-seconds*6)+15, 
                                                    360-(360/12), Arc2D.PIE);
        Arc2D minutesA = new java.awt.geom.Arc2D.Float(20+(diameter*1/3)/2,20+(diameter*1/3)/2, diameter*2/3, diameter*2/3, 
                                                        (int) (90-minutes*6)+15, 
                                                        360-(360/12), Arc2D.PIE);
        Arc2D hoursA = new java.awt.geom.Arc2D.Float(20,20, diameter, diameter, 
                                                    (int)(90-hours*30)+15, 
                                                    360-(360/12), Arc2D.PIE);
        
        g.setColor(new Color(255,255,255));
        g.fillOval(20,20, diameter, diameter);
        drawNum(g2, (90-hours*30), hours,diameter-50,20+diameter/2, 20+diameter/2);
        setArchImg(hoursA, g2, imgHours);
        
        g.setColor(new Color(255,255,255));
        g.fillOval(20+(diameter*1/3)/2,20+(diameter*1/3)/2, diameter*2/3, diameter*2/3);
        drawNum(g2,(90-minutes*6), minutes,diameter*2/3-50,20+diameter/2,20+diameter/2);
        setArchImg(minutesA, g2, imgMinutes);
        
        g.setColor(new Color(255,255,255));
        g.fillOval(20+(diameter*2/3)/2,20+(diameter*2/3)/2, diameter/3, diameter/3);
        drawNum(g2, (90-seconds*6), seconds,diameter/3-50,20+diameter/2,20+diameter/2);
        setArchImg(secondsA, g2, imgSeconds);
    }
    
    public void update(Graphics g){
        g.setColor(new Color(250,250,250));
        g.fillRect(0, 0, diameter+40, diameter+40);
        Graphics2D g2 = (Graphics2D) g;
        
        /*g.setColor(Color.red);
        
        g.fillArc(20,20, diameter, diameter, 
                (int)(90-hours*30), 
                360-(360/12));
        
        g.setColor(Color.yellow);
        g.fillArc(20+(diameter*1/3)/2,20+(diameter*1/3)/2, diameter*2/3, diameter*2/3, 
                (int) (90-minutes*6), 
                360-(360/12));
        
        g.setColor(Color.blue);
        g.fillArc(20+(diameter*2/3)/2,20+(diameter*2/3)/2, diameter/3, diameter/3, 
                (int) (90-seconds*6), 
                360-(360/12));*/
        
        Arc2D secondsA = new java.awt.geom.Arc2D.Float(20+(diameter*2/3)/2,20+(diameter*2/3)/2, diameter/3, diameter/3, 
                                                    (int) (90-seconds*6), 
                                                    360-(360/60), Arc2D.PIE);
        Arc2D minutesA = new java.awt.geom.Arc2D.Float(20+(diameter*1/3)/2,20+(diameter*1/3)/2, diameter*2/3, diameter*2/3, 
                                                        (int) (90-minutes*6), 
                                                        360-(360/60), Arc2D.PIE);
        Arc2D hoursA = new java.awt.geom.Arc2D.Float(20,20, diameter, diameter, 
                                                    (int)(90-hours*30), 
                                                    360-(360/12), Arc2D.PIE);
        
        g.setColor(new Color(255,255,255));
        g.fillOval(20,20, diameter, diameter);
        setClockNums(g2, 12,diameter-50,20+diameter/2, 20+diameter/2);
        setArchImg(hoursA, g2, imgHours);
        
        g.setColor(new Color(255,255,255));
        g.fillOval(20+(diameter*1/3)/2,20+(diameter*1/3)/2, diameter*2/3, diameter*2/3);
        setClockNums(g2, 60,diameter*2/3-50,20+diameter/2,20+diameter/2);
        setArchImg(minutesA, g2, imgMinutes);
        
        g.setColor(new Color(255,255,255));
        g.fillOval(20+(diameter*2/3)/2,20+(diameter*2/3)/2, diameter/3, diameter/3);
        setClockNums(g2, 60,diameter/3-50,20+diameter/2,20+diameter/2);
        setArchImg(secondsA, g2, imgSeconds);
        
        
    }
    
    void drawNum(Graphics2D g2, int angle, int num, int diameter, int centerX, int centerY){
        g2.setColor(Color.BLACK);
        g2.setClip(0, 0, 550, 550);
        
        FontRenderContext frc = g2.getFontRenderContext();
        Font f = new Font("Helvetica", 10, diameter/10);
        
        int x = (int)(Math.cos(Math.toRadians(360-angle))*diameter/2)+centerX;
        int y = (int)(Math.sin(Math.toRadians(360-angle))*diameter/2)+centerY;

        TextLayout textTl = new TextLayout(num+"", f, frc);
        textTl.draw(g2, x,y);
        
    }
    
    void setClockNums(Graphics2D g2, int num, int diameter, int centerX, int centerY){
        g2.setColor(Color.BLACK);
        g2.setClip(0, 0, 550, 550);
        
        FontRenderContext frc = g2.getFontRenderContext();
        Font f = new Font("Helvetica", 10, diameter/15);
        
        int grad = 90;
        for (int i = 1; i <= num; i++) {
            grad -= 360/num;
            
            if(grad==0)
                grad = 360;
            
            System.out.println("grad: "+grad);
            int x = (int)(Math.cos(Math.toRadians(360-grad))*diameter/2)+centerX;
            int y = (int)(Math.sin(Math.toRadians(360-grad))*diameter/2)+centerY;
            System.out.println("x: "+x+" y: "+y+" i: "+i);
            
            TextLayout textTl = new TextLayout(i+"", f, frc);
            textTl.draw(g2, x,y);
        }
    }
    
    void setClockNums2(Graphics2D g2, int num, int width, int height){
        g2.setColor(Color.BLACK);
        AffineTransform orig = g2.getTransform();
        orig.translate(0,0);
        g2.setClip(0, 0, 550, 550);
        FontRenderContext frc = g2.getFontRenderContext();
        Font f = new Font("Helvetica", 10, 50);
        
        int grad = 90;
        for (int i = 1; i <= num; i++) {
            grad -= 360/num;
            
            if(grad==0)
                grad = 360;
            System.out.println("grad: "+grad);
            int x = (int)(Math.cos(Math.toRadians(360-grad))*width/2)+width/2;
            int y = (int)(Math.sin(Math.toRadians(360-grad))*width/2)+width/2;
            System.out.println("x: "+x+" y: "+y+" i: "+i);
            
            g2.rotate(Math.toRadians(grad));
            g2.transform(orig);
            TextLayout textTl = new TextLayout(i+"", f, frc);
            textTl.draw(g2, x,y);
        }
    }
    
    void setArchImg(Arc2D arch, Graphics2D g2, Image img){
        AffineTransform transform = g2.getTransform();
        transform.translate(0,0);
        g2.transform(transform);
        g2.setClip((Shape)arch);
        g2.drawImage(img, 0, 0, diameter+50, diameter+50, this);
    }
    
    
    public static void main(String[] args) throws InterruptedException {
        
        new Clock();
    }

    @Override
    public void run() {
        while(true){
            durationInMillis = System.currentTimeMillis();
            millis = (int) durationInMillis % 1000;
            seconds = (int)(durationInMillis / 1000) % 60;
            minutes = (int) (durationInMillis / (1000 * 60)) % 60;
            hours = (int) (durationInMillis / (1000 * 60 * 60)+7) %12 ;
            try {
                Thread.sleep(delay);
            } catch (InterruptedException ex) {
                Logger.getLogger(Clock.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            /*
            seconds = (actualAngle * 360/totalSeconds)/1000;
            
            if(actualAngle==-1)
                actualAngle=360;
            else
                actualAngle-=1;
            
            if(actualAngle==90 && minutes!=0)
                minutes +=1;
            
            if(minutes==60){
                minutes = 0;
                hours +=1;
            }
            
            if(hours == 12)
                hours = 0;
            */
            repaint();
            
        }
    }
    
}
