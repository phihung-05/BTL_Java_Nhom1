/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;


public class ClockDao extends Thread {
    JLabel lblClock;

    public ClockDao(JLabel lblClock) {
        this.lblClock = lblClock;
    }

    @Override
    public void run() {
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
        while (true) {
            Date now = new Date();
            String s = sdf.format(now);
            lblClock.setText(s);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(ClockDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
