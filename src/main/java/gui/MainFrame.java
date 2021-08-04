package gui;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;

public class MainFrame extends JFrame {
   private GamePanel gamePanel = new GamePanel();

   private static final String defaultFileName = "gameOfLife.gol";

   public MainFrame() {
      super("Game of Life");

      setLayout(new BorderLayout());
      add(gamePanel, BorderLayout.CENTER);

      MenuItem openItem = new MenuItem("Open");
      MenuItem saveItem = new MenuItem("Save");

      Menu fileMenu = new Menu("File");
      fileMenu.add(openItem);
      fileMenu.add(saveItem);

      MenuBar menuBar = new MenuBar();
      menuBar.add(fileMenu);

      setMenuBar(menuBar);

      JFileChooser fileChooser = new JFileChooser();
      var filter = new FileNameExtensionFilter("Game of Life Files", "gol");
      fileChooser.addChoosableFileFilter(filter);
      fileChooser.setFileFilter(filter);

      openItem.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {

            int userOption = fileChooser.showOpenDialog(MainFrame.this); // Passing ref to mainFrame

            if (userOption == JFileChooser.APPROVE_OPTION) {
               File selectedFile = fileChooser.getSelectedFile();
               gamePanel.load(selectedFile);
            }
         }
      });

      saveItem.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            fileChooser.setSelectedFile(new File(defaultFileName));
            int userOption = fileChooser.showSaveDialog(MainFrame.this); // Passing ref to mainFrame

            if (userOption == JFileChooser.APPROVE_OPTION) {
               File selectedFile = fileChooser.getSelectedFile();
               gamePanel.save(selectedFile);
            }
         }
      });

      addKeyListener(new KeyAdapter() {
         @Override
         public void keyPressed(KeyEvent e) {

            if (e.getKeyChar() == 'f') {
               gamePanel.randomize();
            }
            else if (e.getKeyChar() == 'c') {
               gamePanel.clear();
            }
            else if (e.getKeyChar() == 'q') {
               gamePanel.next();
            }
         }
      });

      setSize(800, 600);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setVisible(true);
   }
}
