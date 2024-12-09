package kadai10;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;
import java.awt.image.BufferedImage;

public class ImagePanel extends JPanel{
  public BufferedImage image;
  
  ImagePanel() {
    super();
  }
  
  public void setImage(BufferedImage image) { 
    this.image = image;
    this.setPreferredSize(new Dimension(this.image.getWidth(), this.image.getHeight()));
    repaint();
  }
  public BufferedImage convertToGray() {
    if (image == null) {
        return null; // No image to convert
    }

    // Create a new grayscale image
    BufferedImage grayImage = new BufferedImage(
        image.getWidth(),
        image.getHeight(),
        BufferedImage.TYPE_BYTE_GRAY
    );

    Graphics g = grayImage.getGraphics();
    g.drawImage(image, 0, 0, null); // Draw the original image onto the grayscale image
    g.dispose(); // Release resources

    return grayImage;
}
  @Override
  protected void paintComponent(Graphics g) { 
    super.paintComponent(g);
    g.drawImage(image, 0, 0, null);
  }
}
