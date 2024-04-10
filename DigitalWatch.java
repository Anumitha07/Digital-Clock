import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DigitalWatch extends Frame implements Runnable {
    int hours = 0, minutes = 0, seconds = 0;
    String timeString = "";

    DigitalWatch() {
        Thread t = new Thread(this);
        t.start();
        setSize(500, 500);
        setTitle("Digital Clock");
        setVisible(true);
    }

    public void run() {
        try {
            while (true) {

                Calendar cal = Calendar.getInstance();
                hours = cal.get(Calendar.HOUR_OF_DAY);
                if (hours > 12) hours -= 12;
                minutes = cal.get(Calendar.MINUTE);
                seconds = cal.get(Calendar.SECOND);
  SimpleDateFormat formatter = new SimpleDateFormat("hh:mm:ss");
                Date date = cal.getTime();
                timeString = formatter.format(date);

                repaint();

                Thread.sleep(1000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

public void paint(Graphics g) {
    int circleRadius = 200;
    int borderCircleRadius = circleRadius + 20;
    int hourHandLength = 80;
    int minuteHandLength = 110;
    int secondHandLength = 120;
    int fontSize = 48;
    Font font = new Font("Arial", Font.BOLD, fontSize);
     g.setFont(font);
    g.setColor(Color.BLACK);
    FontMetrics fm = g.getFontMetrics();
    int textWidth = fm.stringWidth(timeString);
    int textHeight = fm.getHeight();
    int textX = 250 - textWidth / 2;
    int textY = 450 - textHeight / 2;
    int centerX = getWidth() / 2;
    int centerY = getHeight() / 2;
   Color customColor = new Color(255, 200, 100);
    g.setColor(customColor);
    g.fillRect(0, 0, getWidth(), getHeight());
    g.setColor(Color.BLACK);
    g.drawOval(centerX - borderCircleRadius, centerY - borderCircleRadius, 2 * borderCircleRadius, 2 * borderCircleRadius);
    g.setColor(Color.BLACK);
    g.drawOval(centerX - circleRadius, centerY - circleRadius, 2 * circleRadius, 2 * circleRadius);
    for (int i = 0; i < 12; i++) {
        double angle = Math.toRadians(i * 30 - 90); 
        int x1 = (int) (Math.cos(angle) * (circleRadius - 10)) + centerX; 
        int y1 = (int) (Math.sin(angle) * (circleRadius - 10)) + centerY;
        int x2 = (int) (Math.cos(angle) * (circleRadius + 10)) + centerX;
        int y2 = (int) (Math.sin(angle) * (circleRadius + 10)) + centerY;
        g.drawLine(x1, y1, x2, y2);
    }
    double hourAngle = Math.toRadians((hours % 12) * 30 - 90 + (minutes / 2)); 
    int hourHandX = (int) (Math.cos(hourAngle) * hourHandLength) + centerX;
    int hourHandY = (int) (Math.sin(hourAngle) * hourHandLength) + centerY;
    g.drawLine(centerX, centerY, hourHandX, hourHandY);
    double minuteAngle = Math.toRadians(minutes * 6 - 90); // Minute angle
    int minuteHandX = (int) (Math.cos(minuteAngle) * minuteHandLength) + centerX;
    int minuteHandY = (int) (Math.sin(minuteAngle) * minuteHandLength) + centerY;
    g.drawLine(centerX, centerY, minuteHandX, minuteHandY);
    double secondAngle = Math.toRadians(seconds * 6 - 90); // Second angle
    int secondHandX = (int) (Math.cos(secondAngle) * secondHandLength) + centerX;
    int secondHandY = (int) (Math.sin(secondAngle) * secondHandLength) + centerY;
    g.setColor(Color.RED); // Second hand color
    g.drawLine(centerX, centerY, secondHandX, secondHandY);
   g.drawString(timeString, textX, textY);
}
    public static void main(String[] args) {
        new DigitalWatch();
    }
}
