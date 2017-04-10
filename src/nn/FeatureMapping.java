package nn;

/**
 * Created by Kolia on 30.10.2016.
 */
public abstract class FeatureMapping {

    public static TrainingData map (byte[][] pixels, byte[] label) {
        int length = pixels.length;
        int resolution = pixels[0].length;
        double[][] x = new double[length][resolution + 1];
        double[][] y = new double[length][10];
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < resolution; j++) {
                byte px = pixels[i][j];
                if (px >= 0) {
                    x[i][j] = (double) px / 256.0;
                } else {
                    x[i][j] = ((double) px + 256.0) / 256.0;
                }
            }
            x[i][resolution] = 1;
            y[i][label[i]] = 1.0;
        }
        return new TrainingData(x, y);
    }

    public static byte mapInverse(double[] label) {
        int id = 0;
        double max = 0.0, cur;
        for (int i = 0; i < 10; i++) {
            cur = label[i];
            if (cur > max) {
                max = cur;
                id = i;
            }
        }
        return (byte) id;
    }

}
