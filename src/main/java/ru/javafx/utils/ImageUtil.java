
package ru.javafx.utils;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;

public class ImageUtil {
    
    public static final String DIR_IMAGE = "src/main/resources/static/images/";
    
    public static boolean writeImage(byte[] imageInByte, String imageFormat, File file) {      
        try {
            InputStream inputStream = new ByteArrayInputStream(imageInByte);
            BufferedImage bImageFromConvert = ImageIO.read(inputStream);          
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
}
