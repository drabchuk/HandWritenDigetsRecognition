package nn;

import com.sun.org.apache.xpath.internal.SourceTree;

/**
 * Created by Denis on 03.11.2016.
 */
public abstract class TestSetValidation {

    public static double test(NeuralNetwork nn, byte[][] img, byte[] label) {
        TrainingData testData = FeatureMapping.map(img, label);
        System.out.println("TEST");
        System.out.println("TEST SET LENGTH : " + testData.length());
        double[][] featuredX = testData.x;
        double[] prediction_dist;
        byte[] prediction = new byte[testData.length()];
        for (int i = 0; i < testData.length(); i++) {
            prediction_dist = nn.predict(featuredX[i]);
            prediction[i] = FeatureMapping.mapInverse(prediction_dist);
        }
        //calc errors
        int errors = 0;
        for (int i = 0; i < testData.length(); i++) {
            if (prediction[i] != label[i]) {
                errors++;
            }
        }
        System.out.println("TOTAL ERRORS : " + errors);
        double accuracy = (double) errors / (double) testData.length();
        System.out.println("TOTAL ACCURACY : " + accuracy);
        return accuracy;
    }

}
