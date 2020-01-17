package co.jp.ths.api.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

public class CertGenerate {
	public static int addWaterMark(String srcImgPath, String tarImgPath, Color markContentColor,
			Font font, List<Map<String, String>> contentMap) {

        try {
        	File srcImgFile = new File(srcImgPath); // Get File
            Image srcImg = ImageIO.read(srcImgFile); // Transfer file to picture
            int srcImgWidth = srcImg.getWidth(null); // Get width
            int srcImgHeight = srcImg.getHeight(null);// Get height
            BufferedImage bufImg = new BufferedImage(srcImgWidth, srcImgHeight, BufferedImage.TYPE_INT_RGB);
            Graphics2D g = bufImg.createGraphics();
            g.drawImage(srcImg, 0, 0, srcImgWidth, srcImgHeight, null);
            g.setColor(markContentColor); // Set word color
            g.setFont(font);              // Set font
            // Set water mark content
            for(Map<String, String> map: contentMap) {
            	g.drawString(map.get("word"), Integer.valueOf(map.get("x")), Integer.valueOf(map.get("y")));
            }
            g.dispose();  
            // Output picture   
            FileOutputStream outImgStream = new FileOutputStream(tarImgPath);  
            ImageIO.write(bufImg, "png", outImgStream);
            outImgStream.flush();  
            outImgStream.close();  
            return 1;

        } catch (Exception e) {
        	return 0;
        }
    }
}
