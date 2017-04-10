package nn;

import classes.Parser;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import static nn.algebra.Alg.*;

/**
 * Created by Kolia on 30.10.2016.
 */
public class TestRunner {

    public static void main(String[] args) {
        byte[][] imgs;
        byte[] labels;
        byte[][] test_imgs;
        byte[] test_labels;
        System.out.println("Program started");
        try {
            System.out.println("MNIST database loading...");
            imgs = Parser.parseX(new RandomAccessFile(new File("MNIST/train-images.idx3-ubyte"),"rw"));
            labels = Parser.parseY(new RandomAccessFile(new File("MNIST/train-labels.idx1-ubyte"),"rw"));
            System.out.println("Training set loaded");
            test_imgs = Parser.parseX(new RandomAccessFile(new File("MNIST/t10k-images.idx3-ubyte"),"rw"));
            test_labels = Parser.parseY(new RandomAccessFile(new File("MNIST/t10k-labels.idx1-ubyte"),"rw"));
            System.out.println("Test set loaded");
            TrainingData trainingData = FeatureMapping.map(imgs, labels);


            NeuralNetwork nn = new LinearOneLayerPerceptron(rand(imgs[0].length + 1, 10));

            Trainer coach = new LinearPerceptronTrainer(nn, trainingData);





//            double j = coach.cost();

//            System.out.println(j);

//            double[][] grad = coach.grad();

//            for (int i = 0; i < grad.length; i++) {
//                System.out.println();
//                for (int k = 0; k < grad[0].length; k++) {
//                    System.out.print(grad[i][k] + " ; ");
//                }
//            }

            coach.train();

            TestSetValidation.test(nn, test_imgs, test_labels);

            double[] result = nn.predict(trainingData.x[0]);
            for (int i = 0; i < 10; i++) {
                System.out.println(result[i]);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
