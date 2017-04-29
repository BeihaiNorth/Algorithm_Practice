/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pso;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
/*import java.util.Timer;
ximport javax.swing.Timer;*/
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author leslie
 */
public class MainPainter extends JComponent implements ActionListener{
    
     

    public static void main(String []arg)  
    {

        //Swing setup
        MainPainter painter = new MainPainter();
        JFrame frame = new JFrame();
        frame.setSize(600, 600);
//        JLabel map = new JLabel();
//        javax.swing.ImageIcon img = new javax.swing.ImageIcon("/Users/leslie/Desktop/map.jpeg");
//        map.setIcon(img);
//        map.setBounds(0, 0, 800, 368);
//        frame.add(map);
        frame.getContentPane().add(painter);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        javax.swing.Timer swingTimer = new javax.swing.Timer(1000,painter);
        
        
        //PSO setup
        PSO pso=new PSO();  
        pso.init(30, 10, 0); //particle number, total city number, start date
        System.out.println("pso initiated");
        TimerTask timerTask = pso;
        java.util.Timer timer = new java.util.Timer(false);

        timer.scheduleAtFixedRate(timerTask, 0, 100); //period in milliseconds
        System.out.println("Timertask started");
        
        while (!isQualified(pso)) {
            System.out.println("still finding best solution...");
            continue;
        }
        System.out.println("finded.");
        timer.cancel();
    }
    
    public static boolean isQualified(PSO pso){
        int recordpbest = pso.pars[0].getFitness();
        for (int i = 1; i < pso.pcount; i++) {
            if (pso.pars[i].getFitness() != recordpbest) {
                return false;
            }
        }
        return true;
    }
    
    public void paint(Graphics g){
        
        
        
        
        g.fillOval(473, 135, 5, 5);
        g.fillOval(494, 106, 5, 5);
        g.fillOval(356, 123, 5, 5);
        g.fillOval(278, 211, 5, 5);
        g.fillOval(118, 184, 5, 5);
        g.fillOval(91, 206, 5, 5);
        g.fillOval(400, 250, 5, 5);
        g.fillOval(460, 144, 5, 5);
        g.fillOval(40, 52, 5, 5);
        g.fillOval(447, 156, 5, 5);
        
//        pso.pars
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }
    
}
