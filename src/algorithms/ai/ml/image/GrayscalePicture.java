package algorithms.ai.ml.image;



import java.awt.Color;
import java.awt.FileDialog;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import algorithms.io.StdIn;
import algorithms.io.StdOut;

public final class GrayscalePicture implements ActionListener {
   private BufferedImage image;               // the rasterized image
   private JFrame frame;                      // on-screen view
   private String filename;                   // name of file
   private boolean isOriginUpperLeft = true;  // location of origin
   private final int width, height;           // width and height

  /**
    * Creates a {@code width}-by-{@code height} picture, with {@code width} columns
    * and {@code height} rows, where each pixel is black.
    *
    * @param width the width of the picture
    * @param height the height of the picture
    * @throws IllegalArgumentException if {@code width} is negative
    * @throws IllegalArgumentException if {@code height} is negative
    */
   public GrayscalePicture(int width, int height) {
       if (width  < 0) throw new IllegalArgumentException("width must be non-negative");
       if (height < 0) throw new IllegalArgumentException("height must be non-negative");
       this.width  = width;
       this.height = height;
       image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
   }

  /**
    * Creates a new grayscale picture that is a deep copy of the argument picture.
    *
    * @param  picture the picture to copy
    * @throws IllegalArgumentException if {@code picture} is {@code null}
    */
   public GrayscalePicture(GrayscalePicture picture) {
       if (picture == null) throw new IllegalArgumentException("constructor argument is null");

       width  = picture.width();
       height = picture.height();
       image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
       filename = picture.filename;
       isOriginUpperLeft = picture.isOriginUpperLeft;
       for (int col = 0; col < width(); col++)
           for (int row = 0; row < height(); row++)
               image.setRGB(col, row, picture.image.getRGB(col, row));
   }

  /**
    * Creates a grayscale picture by reading an image from a file or URL.
    *
    * @param  name the name of the file (.png, .gif, or .jpg) or URL.
    * @throws IllegalArgumentException if cannot read image
    * @throws IllegalArgumentException if {@code name} is {@code null}
    */
   public GrayscalePicture(String name) {
       if (name == null) throw new IllegalArgumentException("constructor argument is null");
       this.filename = name;
       try {
           // try to read from file in working directory
           File file = new File(name);
           if (file.isFile()) {
               image = ImageIO.read(file);
           }

           else {

               // resource relative to .class file
               URL url = getClass().getResource(name);

               // resource relative to classloader root
               if (url == null) {
                   url = getClass().getClassLoader().getResource(name);
               }
    
               // or URL from web
               if (url == null) {
                   url = new URL(name);
               }
       
               image = ImageIO.read(url);
           }

           if (image == null) {
               throw new IllegalArgumentException("could not read image: " + name);
           }

           width  = image.getWidth(null);
           height = image.getHeight(null);

           // convert to grayscale inplace
           for (int col = 0; col < width; col++) {
               for (int row = 0; row < height; row++) {
                   Color color = new Color(image.getRGB(col, row));
                   Color gray = toGray(color);
                   image.setRGB(col, row, gray.getRGB());
               }
           }
       }
       catch (IOException ioe) {
           throw new IllegalArgumentException("could not open image: " + name, ioe);
       }
   }

    // Returns a grayscale version of the given color as a Color object.
   private static Color toGray(Color color) {
       int r = color.getRed();
       int g = color.getGreen();
       int b = color.getBlue();
       int y = (int) (Math.round(0.299*r + 0.587*g + 0.114*b));
       return new Color(y, y, y);
   }

  /**
    * Returns a {@link JLabel} containing this picture, for embedding in a {@link JPanel},
    * {@link JFrame} or other GUI widget.
    *
    * @return the {@code JLabel}
    */
   public JLabel getJLabel() {
       if (image == null) return null;         // no image available
       ImageIcon icon = new ImageIcon(image);
       return new JLabel(icon);
   }

  /**
    * Sets the origin to be the upper left pixel. This is the default.
    */
   public void setOriginUpperLeft() {
       isOriginUpperLeft = true;
   }

  /**
    * Sets the origin to be the lower left pixel.
    */
   public void setOriginLowerLeft() {
       isOriginUpperLeft = false;
   }

  /**
    * Displays the picture in a window on the screen.
    */
   public void show() {

       // create the GUI for viewing the image if needed
       if (frame == null) {
           frame = new JFrame();

           JMenuBar menuBar = new JMenuBar();
           JMenu menu = new JMenu("File");
           menuBar.add(menu);
           JMenuItem menuItem1 = new JMenuItem(" Save...   ");
           menuItem1.addActionListener(this);
           // use getMenuShortcutKeyMaskEx() in Java 10 (getMenuShortcutKeyMask() deprecated)
           menuItem1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
                                    Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
           menu.add(menuItem1);
           frame.setJMenuBar(menuBar);



           frame.setContentPane(getJLabel());
           // f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
           frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
           if (filename == null) frame.setTitle(width + "-by-" + height);
           else                  frame.setTitle(filename);
           frame.setResizable(false);
           frame.pack();
           frame.setVisible(true);
       }

       // draw
       frame.repaint();
   }

  /**
    * Returns the height of the picture.
    *
    * @return the height of the picture (in pixels)
    */
   public int height() {
       return height;
   }

  /**
    * Returns the width of the picture.
    *
    * @return the width of the picture (in pixels)
    */
   public int width() {
       return width;
   }

   private void validateRowIndex(int row) {
       if (row < 0 || row >= height())
           throw new IllegalArgumentException("row index must be between 0 and " + (height() - 1) + ": " + row);
   }

   private void validateColumnIndex(int col) {
       if (col < 0 || col >= width())
           throw new IllegalArgumentException("column index must be between 0 and " + (width() - 1) + ": " + col);
   }

   private void validateGrayscaleValue(int gray) {
       if (gray < 0 || gray >= 256)
           throw new IllegalArgumentException("grayscale value must be between 0 and 255");
   }

  /**
    * Returns the grayscale value of pixel ({@code col}, {@code row}) as a {@link java.awt.Color}.
    *
    * @param col the column index
    * @param row the row index
    * @return the grayscale value of pixel ({@code col}, {@code row})
    * @throws IllegalArgumentException unless both {@code 0 <= col < width} and {@code 0 <= row < height}
    */
   public Color get(int col, int row) {
       validateColumnIndex(col);
       validateRowIndex(row);
       Color color = new Color(image.getRGB(col, row));
       return toGray(color);
   }

  /**
    * Returns the grayscale value of pixel ({@code col}, {@code row}) as an {@code int}
    * between 0 and 255.
    * Using this method can be more efficient than {@link #get(int, int)} because
    * it does not create a {@code Color} object.
    *
    * @param col the column index
    * @param row the row index
    * @return the 8-bit integer representation of the grayscale value of pixel ({@code col}, {@code row})
    * @throws IllegalArgumentException unless both {@code 0 <= col < width} and {@code 0 <= row < height}
    */
   public int getGrayscale(int col, int row) {
       validateColumnIndex(col);
       validateRowIndex(row);
       if (isOriginUpperLeft) return image.getRGB(col, row) & 0xFF;
       else                   return image.getRGB(col, height - row - 1) & 0xFF;
   }

  /**
    * Sets the color of pixel ({@code col}, {@code row}) to the given grayscale value.
    *
    * @param col the column index
    * @param row the row index
    * @param color the color (converts to grayscale if color is not a shade of gray)
    * @throws IllegalArgumentException unless both {@code 0 <= col < width} and {@code 0 <= row < height}
    * @throws IllegalArgumentException if {@code color} is {@code null}
    */
   public void set(int col, int row, Color color) {
       validateColumnIndex(col);
       validateRowIndex(row);
       if (color == null) throw new IllegalArgumentException("color argument is null");
       Color gray = toGray(color);
       image.setRGB(col, row, gray.getRGB());
   }

  /**
    * Sets the color of pixel ({@code col}, {@code row}) to the given grayscale value
    * between 0 and 255.
    *
    * @param col the column index
    * @param row the row index
    * @param gray the 8-bit integer representation of the grayscale value
    * @throws IllegalArgumentException unless both {@code 0 <= col < width} and {@code 0 <= row < height}
    */
   public void setGrayscale(int col, int row, int gray) {
       validateColumnIndex(col);
       validateRowIndex(row);
       validateGrayscaleValue(gray);
       int rgb = gray | (gray << 8) | (gray << 16);
       if (isOriginUpperLeft) image.setRGB(col, row, rgb);
       else                   image.setRGB(col, height - row - 1, rgb);
   }

  /**
    * Returns true if this picture is equal to the argument picture.
    *
    * @param other the other picture
    * @return {@code true} if this picture is the same dimension as {@code other}
    *         and if all pixels have the same color; {@code false} otherwise
    */
   @Override
public boolean equals(Object other) {
       if (other == this) return true;
       if (other == null) return false;
       if (other.getClass() != this.getClass()) return false;
       GrayscalePicture that = (GrayscalePicture) other;
       if (this.width()  != that.width())  return false;
       if (this.height() != that.height()) return false;
       for (int col = 0; col < width(); col++)
           for (int row = 0; row < height(); row++)
               if (this.getGrayscale(col, row) != that.getGrayscale(col, row)) return false;
       return true;
   }

  /**
    * Returns a string representation of this picture.
    * The result is a <code>width</code>-by-<code>height</code> matrix of pixels,
    * where the grayscale value of a pixel is an integer between 0 and 255.
    *
    * @return a string representation of this picture
    */
   @Override
public String toString() {
       StringBuilder sb = new StringBuilder();
       sb.append(width +"-by-" + height + " grayscale picture (grayscale values given in hex)\n");
       for (int row = 0; row < height; row++) {
           for (int col = 0; col < width; col++) {
               int gray = 0;
               if (isOriginUpperLeft) gray = 0xFF & image.getRGB(col, row);
               else                   gray = 0xFF & image.getRGB(col, height - row - 1);
               sb.append(String.format("%3d ", gray));
           }
           sb.append("\n");
       }
       return sb.toString().trim();
   }

   /**
    * This operation is not supported because pictures are mutable.
    *
    * @return does not return a value
    * @throws UnsupportedOperationException if called
    */
   @Override
public int hashCode() {
       throw new UnsupportedOperationException("hashCode() is not supported because pictures are mutable");
   }

  /**
    * Saves the picture to a file in either PNG or JPEG format.
    * The filetype extension must be either .png or .jpg.
    *
    * @param name the name of the file
    * @throws IllegalArgumentException if {@code name} is {@code null}
    */
   public void save(String name) {
       if (name == null) throw new IllegalArgumentException("argument to save() is null");
       save(new File(name));
       filename = name;
   }

  /**
    * Saves the picture to a file in a PNG or JPEG image format.
    *
    * @param  file the file
    * @throws IllegalArgumentException if {@code file} is {@code null}
    */
   public void save(File file) {
       if (file == null) throw new IllegalArgumentException("argument to save() is null");
       filename = file.getName();
       if (frame != null) frame.setTitle(filename);
       String suffix = filename.substring(filename.lastIndexOf('.') + 1);
       if ("jpg".equalsIgnoreCase(suffix) || "png".equalsIgnoreCase(suffix)) {
           try {
               ImageIO.write(image, suffix, file);
           }
           catch (IOException e) {
               e.printStackTrace();
           }
       }
       else {
           System.out.println("Error: filename must end in .jpg or .png");
       }
   }

  /**
    * Opens a save dialog box when the user selects "Save As" from the menu.
    */
   @Override
   public void actionPerformed(ActionEvent e) {
       FileDialog chooser = new FileDialog(frame,
                            "Use a .png or .jpg extension", FileDialog.SAVE);
       chooser.setVisible(true);
       if (chooser.getFile() != null) {
           save(chooser.getDirectory() + File.separator + chooser.getFile());
       }
   }

  /**
    * Unit tests this {@code Picture} data type.
    * Reads a picture specified by the command-line argument,
    * and shows it in a window on the screen.
    *
    * @param args the command-line arguments
    */
   public static void main(String[] args) {
	   
       GrayscalePicture picture = new GrayscalePicture(args[0]);
       StdOut.printf("%d-by-%d\n", picture.width(), picture.height());
       GrayscalePicture copy = new GrayscalePicture(picture);
       picture.show();
       copy.show();
       while (!StdIn.isEmpty()) {
           int row = StdIn.readInt();
           int col = StdIn.readInt();
           int gray = StdIn.readInt();
           picture.setGrayscale(row, col, gray);
           StdOut.println(picture.get(row, col));
           StdOut.println(picture.getGrayscale(row, col));
       }
   }

}
