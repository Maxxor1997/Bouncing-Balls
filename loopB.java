import java.net.*;
import java.awt.*;

//run main program from BouncingBallSimulator
public class loopB implements Runnable{
    private BouncingBallSimulator B; //main object
    private double speedx; //initial x velocity of ball
    private double speedy; //initial y velocity of ball
    loopB(BouncingBallSimulator B) {
        this.B = B;
        speedx = 60;
        speedy = 80;
    }
    public void run() {
        try {
            int port = 4111;
            
            // Create a socket to listen on the port.
            DatagramSocket dsocket = new DatagramSocket(port);
            
            // Create a buffer to read datagrams into. If a
            // packet is larger than this buffer, the
            // excess will simply be discarded!
            byte[] buffer = new byte[2048];
            
            // Create a packet to receive data into the buffer
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            
            // Now loop forever, waiting to receive packets and printing them.
            while (true) {
                // Wait to receive a datagram
                dsocket.receive(packet);
                // Convert the contents to a string, and display them
                String msg = new String(buffer, 0, packet.getLength());
                msg = msg.substring(0,3);
                
                //controls for initial speed
                if (msg.equals("one")) {
                    speedx = 30;
                    speedy = 40;
                }
                else if (msg.equals("two")) {
                    speedx = 60;
                    speedy = 80;
                }
                else if (msg.equals("thr")) {
                    speedx = 90;
                    speedy = 120;
                }
                else if (msg.equals("fou")) {
                    speedx = 120;
                    speedy = 160;
                }
                //controls for color of ball
                else if (msg.equals("yel")) {
                    Color Y = new Color(255, 255, 0);
                    Ball ball = new Ball(Y, 3, speedx, speedy, 20, 20);
                    B.addBall(ball);
                }
                else if (msg.equals("blu")) {
                    Color Y = new Color(0, 255, 255);
                    Ball ball = new Ball(Y, 3, speedx, speedy, 20, 20);
                    B.addBall(ball);
                }
                else if (msg.equals("pur")) {
                    Color Y = new Color(255, 0, 255);
                    Ball ball = new Ball(Y, 3, speedx, speedy, 20, 20);
                    B.addBall(ball);
                }
                else if (msg.equals("cle")) {
                    B.removeAll();
                }
               
                // Reset the length of the packet before reusing it.
                packet.setLength(buffer.length);
            }
        } catch (Exception e) {
            System.err.println(e);
        }
    }
    
    
    
    
}

