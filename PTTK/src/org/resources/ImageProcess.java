/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.resources;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import javax.imageio.ImageIO;

/**
 *
 * @author kunbo
 */
public class ImageProcess {

    public static Image resize(Image originalImage, int targetWidth, int targetHeight) {
        Image resultingImage = originalImage.getScaledInstance(targetWidth, targetHeight, Image.SCALE_SMOOTH);
        return resultingImage;
    }

    public static byte[] toByteArray(Image img, String type){
        try {
            BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_RGB);

            Graphics2D g = bimage.createGraphics();
            g.drawImage(img, 0, 0, null);
            g.dispose();

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(bimage, type, baos);

            byte[] imageInByte;
            imageInByte = baos.toByteArray();

            return imageInByte;
        } catch (IOException e) {
            return null;
        }
    }

    public static Image createImageFromByteArray(byte[] data, String type) throws IOException {
        try {
            ByteArrayInputStream bis = new ByteArrayInputStream(data);
            BufferedImage bImage = ImageIO.read(bis);
            Image img = bImage.getScaledInstance(bImage.getWidth(), bImage.getHeight(), Image.SCALE_SMOOTH);
            return img;
        } catch (javax.imageio.IIOException | java.lang.NullPointerException e) {
            return null;
        }
    }

    public static Image createImageFromBlob(Blob b) {
        try {
            byte[] imagebytes = b.getBytes(0, (int) b.length());
            return createImageFromByteArray(imagebytes, "jpg");
        } catch (SQLException | IOException e) {
            return null;
        }
    }

    public static byte[] BlobToByteArray(Blob b) throws SQLException {
        int myblobLength = (int) b.length();
        byte[] myblobAsBytes = b.getBytes(1, myblobLength);
        return myblobAsBytes;
    }

    public static Image readImage(String URL) {
        try {
            File f = new File(URL);
            Image img = ImageIO.read(f);
            return img;
        } catch (IOException e) {
            System.out.println("Không timg thấy ảnh");
            return null;
        }
    }
}
