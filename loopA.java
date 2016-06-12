
//run main program from BouncingBallSimulator
public class loopA implements Runnable{
    private BouncingBallSimulator B;
    loopA(BouncingBallSimulator B) {
        this.B = B;
    }
    public void run() {
        while (true) {
            B.step(0.005); //increment time
            B.show(); //draws balls
        }
    }
}
