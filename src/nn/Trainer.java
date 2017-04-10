package nn;

/**
 * Created by Kolia on 30.10.2016.
 */
public interface Trainer {

    void train();
    double cost();
    double[][] grad();

}
