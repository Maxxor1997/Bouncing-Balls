import java.awt.*;
import edu.princeton.cs.algs4.*;
import java.net.*;

//run main program from BouncingBallSimulator
public class Ball {
    
    private Color color; //color of ball
    private double radius; //radius of ball
    private double xVel; //initial x velocity
    private double yVel; //initial y velocity
    private double xPos; //initial x position
    private double yPos; //initial y position
    
    public Ball(Color color, double radius, double xVel, double yVel
                    , double xPos, double yPos) {
        this.color = color;
        this.radius = radius;
        this.xVel = xVel;
        this.yVel = yVel;
        this.xPos = xPos;
        this.yPos = yPos;
    }
    
    public Color getColor() {
        return color;
    }
    
    public double getRadius() {
        return radius;
    }
    
    //mass is proportional to r^3 for a sphere
    public double getMass() {
        return radius;
    }
    
    public double getXVel() {
        return xVel;
    }
    public double getYVel() {
        return yVel;
    }
    public double getXPos() {
        return xPos;
    }
    public double getYPos() {
        return yPos;
    }
    
    public void setXPos(double xPos) {
        this.xPos = xPos;
    }
    
    public void setYPos(double yPos) {
        this.yPos = yPos;
    }
    
    public void setXVel(double xVel) {
        this.xVel = xVel;
    }
    
    public void setYVel(double yVel) {
        this.yVel = yVel;
    }
    //check collision with top and bottom of frame
    public void checkTBCollision(double height) {
        if (yPos - radius <= 0) {
            yVel = -1 * yVel;
            yPos = radius;
            sendSound(impactWall(this.color));
        }
        else if (yPos + radius >= height) {
            yVel = -1 * yVel;
            yPos = height - radius;
            sendSound(impactWall(this.color));
        }
    }
    //check collision with left and right of frame
    public void checkLRCollision(double width) {
        if (xPos - radius <= 0) {
            xVel = -1 * xVel;
            xPos = radius;
            sendSound(impactWall(this.color));
        }
        else if (xPos + radius >= width) {
            xVel = -1 * xVel;
            xPos = width - radius;
            sendSound(impactWall(this.color));
        }
    }
    
    public void checkBallCollision(Ball other, double delta) {
        RectHV a = new RectHV(xPos - radius, yPos - radius, xPos + radius
                                  , yPos + radius);
        double r = other.getRadius();
        RectHV b = new RectHV(other.getXPos() - r, other.getYPos() - r
                                  , other.getXPos() + r, other.getYPos() + r);
        if (a.intersects(b)) {
            //update velocity according to physical laws
            sendSound(colorComb(this.color, other.getColor()));
            double xTemp = xVel;
            double yTemp = yVel;
            xVel = other.getXVel();
            yVel = other.getYVel();
            other.setXVel(xTemp);
            other.setYVel(yTemp);
            
            //update position by delta, to avoid balls getting caught in
            //each other
            xPos = xPos + xVel * delta;
            yPos = yPos + yVel * delta;
            other.setXPos(other.getXPos() + delta * other.getXVel());
            other.setYPos(other.getYPos() + delta * other.getYVel());
        }
    }
    //determines which signal to send to max depending on color
    //I send strings of 'a's because max only reads the length of the message
    public String impactWall(Color a) {
        Color first = new Color(0, 255, 255); //blue
        Color second = new Color(255, 0, 255);  //purple
        Color third = new Color(255, 255, 0);  //yellow
        if (a.equals(first))
            return "aaaaaaa";
        else if (a.equals(second))
            return "aaaaaaaa";
        else if (a.equals(third))
            return "aaaaaaaaa";
        return "";
    }
    //sends signal to max depending on color combination
    public String colorComb(Color a, Color b) {
        Color first = new Color(0, 255, 255); //blue
        Color second = new Color(255, 0, 255); //purple
        Color third = new Color(255, 255, 0); //yellow
        if (a.equals(first) && b.equals(first))
            return "a";
        else if (a.equals(first) && b.equals(second) || a.equals(second) && b.equals(first))
            return "aa";
        else if (a.equals(first) && b.equals(third) || a.equals(third) && b.equals(first))
            return "aaa";
        else if (a.equals(second) && b.equals(second))
            return "aaaa";
        else if (a.equals(second) && b.equals(third) || a.equals(third) && b.equals(second))
            return "aaaaa";
        else if (a.equals(third) && b.equals(third))
            return "aaaaaa";
        return "";
    }
    //send signal to max
    public void sendSound(String sig) {
        try {  
            int port = 4123;
            byte[] message = sig.getBytes();
            // Get the internet address of the specified host
            InetAddress address = InetAddress.getByName("localhost");
            // Initialize a datagram packet with data and address
            DatagramPacket packet = new DatagramPacket(message, message.length,
                                                       address, port);
            // Create a datagram socket, send the packet through it, close it.
            DatagramSocket dsocket = new DatagramSocket();
            dsocket.send(packet);
            dsocket.close();
        } catch (Exception e) {
            System.err.println(e);
        }
    }
    
    public static void main (String[] args) {
        
        
    }
    
    
}