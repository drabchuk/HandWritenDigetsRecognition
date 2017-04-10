package nn;

import static nn.algebra.Alg.*;

/**
 * Created by Kolia on 30.10.2016.
 */
public class LinearPerceptronTrainer implements Trainer {
    LinearOneLayerPerceptron nn;
    TrainingData td;

    public LinearPerceptronTrainer(NeuralNetwork network, TrainingData trainingData) {
        this.nn = (LinearOneLayerPerceptron) network;
        this.td = trainingData;
    }

    @Override
    public void train() {
        final double ALPHA = -0.2;
        double[][] grad;
        double[][] nextTheta;
        System.out.println("1: " + this.cost());
        double prevCost = 100;
        for (int i = 0; i < 25; i++) {
            grad = this.grad();
            double[][] theta = nn.getTheta();
            nextTheta = sum(theta, dotMult(ALPHA, grad));
            nn.setTheta(nextTheta);
            double cost = this.cost();
            if (cost > prevCost) {
                System.out.println("UP");
            }
            System.out.println(i + 2);
            System.out.println(cost);
            prevCost = cost;
        }
    }

    public double[][] grad() {
        double[][] x = td.x;
        double[][] y = td.y;
        double[][] h = nn.predict(x);
        double[][] res = new double[nn.outputLayerSize][nn.outputLayerSize];

        //double[][] first = dotMult(y, sub(1.0, h));
        //double[][] second = dotMult(sub(1.0, y), h);
        //double[][] third = sum (first, second);
        //double[][] fourth = mult(x, "transpose", third);
        return dotDiv(
                mult(x, "transpose",
                        sub (
                                h
                                , y
                        )
                )
                , td.length()
        );
        /*return dotDiv(
                mult(x, "transpose",
                        sum (
                                dotMult(y, sub(1.0, h))
                                , dotMult(sub(1.0, y), h)
                        )
                )
                , td.length()
        );*/

        /*return dotDiv(
                mult(x, "transpose",
                        sum (
                            dotMult(y, dotDiv(1.0, h))
                            , dotMult(sub(1.0, y), dotDiv(1.0, sub(1.0, h)))
                        )
                )
                , td.length()
                );*/

    }

    public double cost() {
        double[][] x = td.x;
        double[][] y = td.y;
        double[][] h = nn.predict(x);
        return -(sum(sum(
                        sum(
                                dotMult(y, log(h))
                                , dotMult(sub(1.0, y), log (sub(1.0, h))))
                            )
                    )
                ) / td.length();
    }

}
