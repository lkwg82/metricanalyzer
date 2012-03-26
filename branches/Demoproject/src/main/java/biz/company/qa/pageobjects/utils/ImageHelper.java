/*
 * (C) 2001 - 2011 company.biz AG
 */
package biz.company.qa.pageobjects.utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 
 */
public class ImageHelper {
    private final static Logger log = LoggerFactory.getLogger(ImageHelper.class);

    /**
     * @param file
     * @throws IOException
     */
    public void addMetaDataToImage(final File file, final String title, final String message) throws IOException {
        // add metadata to image file
        final BufferedImage img = ImageIO.read(file);

        int infoHeight = 100;

        if (img.getType() == 0) {
            log.info("file: " + file);
            log.info("file-size: " + file.length());
            log.info("width: " + img.getWidth());
            log.info("height: " + img.getHeight());
        } else {
            BufferedImage tallerImage = new BufferedImage(img.getWidth(), img.getHeight() + infoHeight, img.getType());

            // copy to area with space above
            for (int x = 0; x < img.getWidth(); x++) {
                for (int y = 0; y < img.getHeight(); y++) {
                    tallerImage.setRGB(x, y + infoHeight, img.getRGB(x, y));
                }
            }

            final Graphics2D g2 = tallerImage.createGraphics();
            // background
            g2.setColor(new Color(45, 36, 48, 200));
            g2.fillRoundRect(5, 10, tallerImage.getWidth() - 15, 80, 20, 20);

            // draw the message
            g2.setColor(Color.white);
            g2.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
            g2.drawString(title, 10, 30);
            g2.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
            g2.drawString(message, 50, 50);
            ImageIO.write(tallerImage, "png", file);
            g2.dispose();
        }
    }
}
