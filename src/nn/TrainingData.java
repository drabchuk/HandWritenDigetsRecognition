package nn;

/**
 * Created by Kolia on 30.10.2016.
 */
public class TrainingData {

    public double[][] x;
    public double[][] y;

    public TrainingData(double[][] x, double[][] y) {
        this.x = x;
        this.y = y;
    }

    public int length() {
        return y.length;
    }
}
