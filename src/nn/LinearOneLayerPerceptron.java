package nn;

import static nn.algebra.Alg.*;

/**
 * Created by Kolia on 30.10.2016.
 */
public class LinearOneLayerPerceptron implements NeuralNetwork{

    int inputLayerSize;
    int outputLayerSize;
    double[][] theta = new double[outputLayerSize][inputLayerSize];

    public LinearOneLayerPerceptron(double[][] theta) {
        this.theta = theta;
        inputLayerSize = theta[0].length;
        outputLayerSize = theta.length;
    }

    private double[] hypotesis(double[] features) {
        return mult(theta, features);
    }

    private double[][] hypotesis(double[][] features) {
        //FIXME transpose
        return mult(features, theta);
    }

    @Override
    public double[] predict(double[] features) {
        return sigmoid(hypotesis(features));
    }

    public double[][] predict(double[][] features) {
        return sigmoid(hypotesis(features));
    }

    public int getInputLayerSize() {
        return inputLayerSize;
    }

    public void setInputLayerSize(int inputLayerSize) {
        this.inputLayerSize = inputLayerSize;
    }

    public int getOutputLayerSize() {
        return outputLayerSize;
    }

    public void setOutputLayerSize(int outputLayerSize) {
        this.outputLayerSize = outputLayerSize;
    }

    public double[][] getTheta() {
        return theta;
    }

    public void setTheta(double[][] theta) {
        this.theta = theta;
    }
}
