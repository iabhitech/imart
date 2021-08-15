/*
 * Super Market Management System
 * Designed By Abhineet Verma  * 
 */
package com.iabhitech.imart.pojo;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.krysalis.barcode4j.impl.code128.Code128Bean;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;

/**
 *
 * @author Abhineet Verma
 */
public class Barcode_IMG_Generator {
    
    public static void createImage(String imageName, String myString) {
        try {
            Code128Bean code128 = new Code128Bean();
            code128.setHeight(15f);
            
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            BitmapCanvasProvider canvas = new BitmapCanvasProvider(baos, "image/x-png", 300, BufferedImage.TYPE_BYTE_BINARY, false, 0);
            
            code128.generateBarcode(canvas, myString);
            canvas.finish();
            
            String userdir = System.getProperty("user.dir");
            FileOutputStream fos = new FileOutputStream(userdir + "\\assets\\barcode\\" + imageName);
            fos.write(baos.toByteArray());
            fos.flush();
            fos.close();
        } catch (Exception ex) {
            System.out.println("Exception in Barcode generator" + ex.getMessage());
            Logger.getLogger(Barcode_IMG_Generator.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
