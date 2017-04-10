package sample;


import classes.Parser;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;

import classes.CustomImage;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class Controller {

    final static int WIDTH = 28;
    final static int HEIGHT = 28;

    @FXML
    ImageView imageView;

    @FXML
    public void initialize(){
        byte[][] pixels = new byte[HEIGHT][WIDTH];
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                pixels[i][j] = (byte) i;
            }
        }



        byte[][] trainingData;
        try {
            trainingData = Parser.parseX(new RandomAccessFile(new File("E:/java/den/ml/HanWritenDigetsRecognition../../MNIST/t10k-images.idx3-ubyte"),"rw"));
            CustomImage customImg = new CustomImage(trainingData[0]);
            imageView.setImage(customImg.getWritableImage());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
