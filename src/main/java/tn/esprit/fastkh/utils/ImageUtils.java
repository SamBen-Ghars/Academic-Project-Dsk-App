package tn.esprit.fastkh.utils;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritablePixelFormat;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

public class ImageUtils {
    public static byte[] imageToBytes(ImageView imageView) {
        Image image = imageView.getImage();
        int width = (int) image.getWidth();
        int height = (int) image.getHeight();
        byte[] buffer = new byte[width * height * 4];

        PixelReader pixelReader = image.getPixelReader();
        WritablePixelFormat<ByteBuffer> pixelFormat = WritablePixelFormat.getByteBgraInstance();
        pixelReader.getPixels(0, 0, width, height, pixelFormat, buffer, 0, width * 4);

        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            outputStream.write(buffer);
            return outputStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
