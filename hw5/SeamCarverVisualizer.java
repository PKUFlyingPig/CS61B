/******************************************************************************
 *  *  Compilation:  javac SeamCarverVisualizer.java
 *  *  Execution:    java SeamCarverVisualizer input.png seamsToRemove [horizontal | yN]
 *  *  Dependencies: SeamCarver.java
 *  *                
 *  *
 *  *  Read image from file specified as command line argument. Use SeamCarver
 *  *  to remove number of rows or columns specified as command line arguments.
 *  *  Show the sequence of seams being removed.
 *  *
 *  *  % java SeamCarverVisualizer HJoceanSmall.png 150 y
 *  *
 *  ******************************************************************************/

import edu.princeton.cs.algs4.Picture;
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
import javax.swing.KeyStroke;

public class SeamCarverVisualizer {
    JFrame frame;

    public void visualizeHorizontalCarve(SeamCarver sc, int N) {
        for (int i = 0; i < N; i++) {
            int[] minSeam = sc.findHorizontalSeam();
            Picture p = sc.picture();            
            paintHorizontalSeam(p, minSeam);
            show(p);
            sc.removeHorizontalSeam(minSeam);
        }

        show(sc.picture());
    }

    public void visualizeVerticalCarve(SeamCarver sc, int N) {
        for (int i = 0; i < N; i++) {
            int[] minSeam = sc.findVerticalSeam();
            Picture p = sc.picture();
            paintVerticalSeam(p, minSeam);
            show(p);
            sc.removeVerticalSeam(minSeam);
        }

        show(sc.picture());
        sc.picture().save("output.png");
    }

    private void paintHorizontalSeam(Picture p, int[] seam) {

        for (int i = 0; i < seam.length; i++) {
            p.set(i, seam[i], new Color(255, 0, 0));
        }
    }

    private void paintVerticalSeam(Picture p, int[] seam) {
        for (int i = 0; i < seam.length; i++) {
            p.set(seam[i], i, new Color(255, 0, 0));
        }
    }

    public void show(Picture img) {
        if (frame == null) {
            frame = new JFrame();

            JMenuBar menuBar = new JMenuBar();
            JMenu menu = new JMenu("File");
            menuBar.add(menu);
            JMenuItem menuItem1 = new JMenuItem(" Save...   ");
            menuItem1.addActionListener(img);
            menuItem1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
                                     Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
            menu.add(menuItem1);
            frame.setJMenuBar(menuBar);

            frame.setContentPane(img.getJLabel());
            // f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setTitle("Output");
            frame.setResizable(false);
            frame.pack();
            frame.setVisible(true);
         }

         // draw
         frame.setContentPane(img.getJLabel());
         frame.revalidate();
         frame.repaint();
    }

    public static void main(String[] args) {
        if (args.length <= 1) {
            System.out.println("Usage: SeamCarver [filename] [numPixels] [horizontal | yN]");
            return;
        }

        Picture samplePicture = new Picture(args[0]);
        SeamCarver sc = new SeamCarver(samplePicture);
        int N = Integer.parseInt(args[1]);

        SeamCarverVisualizer scv = new SeamCarverVisualizer();
        if (args[2].equals("y")) {
            scv.visualizeHorizontalCarve(sc, N);
        } else {
            scv.visualizeVerticalCarve(sc, N);
        }
    }
}
