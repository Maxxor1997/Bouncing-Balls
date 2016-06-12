import edu.princeton.cs.algs4.*;
import java.awt.*;

/*This is the main program. Running this starts the infinte loops and 
initializes the drawing. Open BouncingBallsFinal.maxpat to communicate with
this program.
Note: You may have to download the algs4 library from the COS 226 website
Bug: If you click the create ball button in quick succession, you may create
a ball on top of another one,causing them to stick together and display
unintended behavior.
*/
public class BouncingBallSimulator {
    
    private Queue<Ball> balls; //all the balls currently in simulation
    private double sidelength; //sidelength of universe
    
    public BouncingBallSimulator(Queue<Ball> balls, double sidelength) {
        this.balls = balls;
        this.sidelength = sidelength;
        StdDraw.setYscale(0, sidelength);    
        StdDraw.setXscale(0, sidelength);  
    }
    //add new ball
    public void addBall(Ball b) {
        balls.enqueue(b); 
    }
    //remove all balls
    public void removeAll() {
        Queue<Ball> newballs = new Queue<Ball>();
        balls = newballs;
    }
    //move time by one delta
    public void step(double delta) {
        for (Ball B : balls) {
            //check collisions with all balls not itself
            for (Ball C : balls) {
                if (!B.equals(C))
                    B.checkBallCollision(C, 0);
            }
            //check frame collisions and update position 
            B.checkTBCollision(sidelength);
            B.checkLRCollision(sidelength);
            B.setXPos(B.getXPos() + B.getXVel() * delta);
            B.setYPos(B.getYPos() + B.getYVel() * delta);
        }
    }
    //draw all balls in universe
    public void show() {
        StdDraw.picture(sidelength/2, sidelength/2, "space2.jpg");
        for (Ball B : balls) {
            StdDraw.setPenColor(B.getColor());
            StdDraw.filledCircle(B.getXPos(), B.getYPos(), B.getRadius());
        }
        StdDraw.show(10);
    }
    
    public static void main (String[] args) {
        
        /*Color C = new Color(0, 255, 255);
        Color D = new Color(255, 0, 255);
        Color E = new Color(255, 255, 0);
        Ball A3 = new Ball(D, 3, 67, 69, 100, 50);
        Ball A2 = new Ball(C, 3, 100, 100, 0, 0);
        Ball A = new Ball(D, 3, 45, 70, 100, 100);
        Ball A4 = new Ball(D, 3, 50, 50, 50, 50);
        Ball A5 = new Ball(C, 3, 20, 20, 25, 25);
        */
        Queue<Ball> balls = new Queue<Ball>();
        //balls.enqueue(A);
        //balls.enqueue(A2);
        //balls.enqueue(A3);
        //balls.enqueue(A4);
        //balls.enqueue(A5);
        BouncingBallSimulator B = new BouncingBallSimulator(balls, 100);
        loopA asd = new loopA(B);
        loopB asdf = new loopB(B);
        new Thread(asd).start();
        new Thread(asdf).start();
        /*
        StdDraw.setXscale(0, 50);
        StdDraw.setYscale(0, 50);
        Color C = new Color(255, 255, 255);
        StdDraw.setPenColor(C);
        StdDraw.filledCircle(25, 25, 10);
        */
    }
    
    
}