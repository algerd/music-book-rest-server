
package ru.javafx.utils;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;

public class ImageUtil {
    
    public static final String DIR_IMAGE = "src/main/resources/static/images/";
    public static final int WIDTH_COVER = 300;
    public static final int HEIGTH_COVER = 300;
    
    public static boolean writeImage(byte[] imageInByte, String imageFormat, File file) {      
        try {
            InputStream inputStream = new ByteArrayInputStream(imageInByte);
            BufferedImage bImageFromConvert = ImageIO.read(inputStream); 
            
            // resize image. Надо вынести отсюда, чтобы регулировать размерами извне
            // сделать проверку(ограничение) передаваемого изображения, чтобы не преобразовывать гигантские картинки
            bImageFromConvert = resizeImage(bImageFromConvert, WIDTH_COVER, HEIGTH_COVER, true);
            
            ImageIO.write(bImageFromConvert, imageFormat, file);
            return true;
        } catch (IOException ex) {
            ex.printStackTrace();
            return false;
        }       
    }
    
    public static File createImageFile(String nameEntity, Long id, String imageFormat) {      
        //String nameEntity = entity.getClass().getSimpleName().toLowerCase();
        String dirImage = DIR_IMAGE + nameEntity +"/"; 
        File file = new File(dirImage + id + "." + imageFormat); 
        return file; 
    }
    
    public static BufferedImage resizeImage(BufferedImage originalImage, int scaledWidth, int scaledHeight, boolean preserveRatio) {       
        double imageHeight = originalImage.getHeight();
        double imageWidth = originalImage.getWidth();       
        if ((int) imageHeight == scaledHeight && (int) imageWidth == scaledWidth) {
            return originalImage;
        }              
        if (preserveRatio) {           
            if (imageHeight/scaledHeight > imageWidth/scaledWidth) {
                scaledWidth = (int) (scaledHeight * imageWidth / imageHeight);
            } else {
                scaledHeight = (int) (scaledWidth * imageHeight / imageWidth);
            }        
        }                       
        BufferedImage resizedImage = new BufferedImage(scaledWidth, scaledHeight, originalImage.getType());       
        Graphics2D g2d = resizedImage.createGraphics();
        g2d.drawImage(originalImage, 0, 0, scaledWidth, scaledHeight, null);
        g2d.dispose();    
        return resizedImage;
    }
}
