package kadai10;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class Ex10 {
  private JFrame frame;
  private JScrollPane imgScrollPane;
  private JScrollPane scrollPane2;
  private JTextField textField;
  private JTextArea textArea;
  private ImagePanel imagePanel;


  /**
   * Launch the application.
   */
  public static void main(String[] args) {
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        try {
          Ex10 window = new Ex10();
          window.frame.setVisible(true);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    });
  }

  /**
   * Create the application.
   */
  public Ex10() {
    initialize();
  }

  /**
   * Initialize the contents of the frame.
   */
  private void initialize() {
    frame = new JFrame();
    frame.setBounds(100, 100, 736, 788);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    JPanel panel = new JPanel();
    panel.setPreferredSize(new Dimension(120, 0));
    frame.getContentPane().add(panel, BorderLayout.WEST);
    JLabel lblNewLabel = new JLabel("File Name");
    panel.add(lblNewLabel);
    
    textField = new JTextField();
    panel.add(textField);
    textField.setColumns(10);
    
    
    imgScrollPane = new JScrollPane();
    frame.getContentPane().add(imgScrollPane, BorderLayout.CENTER);
    imagePanel = new ImagePanel();
    imgScrollPane.setViewportView(imagePanel);
    
    scrollPane2 = new JScrollPane();
    frame.getContentPane().add(scrollPane2, BorderLayout.SOUTH);
    
    textArea = new JTextArea();
    scrollPane2.setViewportView(textArea);
    
    JButton btnNewButton = new JButton("Load");
    btnNewButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
          // Get the file name from the JTextField
          String fileName = textField.getText();
          
          // Print the current directory to check where the program is looking for the file 
          System.out.println("Current directory: " + System.getProperty("user.dir"));
          
          // Try to load the image
          BufferedImage img;
          try {
              // Attempt to read the image from the given file path
              img = ImageIO.read(new File(fileName));
              if (img != null) {
                  imagePanel.setImage(img); // Set the image to the panel if read successfully
              } else {
                  textArea.append("Error: Image is null (possibly file format issue).\n");
              }
          } catch (IOException e1) {
              // Print the error to the console and show it in the JTextArea
              e1.printStackTrace();
              textArea.append("Error: " + e1.toString() + "\n");
          }
      }
  });

    panel.add(btnNewButton);
    
    JButton btnGray = new JButton("Grayscale");
    btnGray.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        BufferedImage grayImage = imagePanel.convertToGray();
        if (grayImage != null) {
            imagePanel.setImage(grayImage);
            textArea.append("Image converted to grayscale.\n");
        } else {
            textArea.append("No image loaded to convert.\n");
        }
      
    }
});
    panel.add(btnGray);
  }
}
