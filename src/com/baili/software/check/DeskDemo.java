package com.baili.software.check;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
/**
 * @author Hardneedl
 */
final class DeskDemo extends JFrame {
    public String getTitle() {return "DeskDemo";}
    static private final Dimension size = new Dimension(600,400);
    public Dimension getPreferredSize() {return size;}
    public Dimension getMaximumSize() {return size;}
    public Dimension getMinimumSize() {return size;}
    public Dimension getSize(){return size;}
    private class DemoShowAction extends AbstractAction{
        private JFileChooser fileChooser;
        private DemoShowAction() {
            super("show desk support");
            fileChooser=new JFileChooser(".");
        }
 
        public void actionPerformed(ActionEvent e) {
            if(JFileChooser.APPROVE_OPTION==fileChooser.showOpenDialog((Component) e.getSource())){
                if(!Desktop.isDesktopSupported()){
                    JOptionPane.showMessageDialog(null, "你的运行环境不支持 Desktop", "Desktop运行环境检查", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                File file=fileChooser.getSelectedFile();
                if(file!=null){
                    try {
                        Desktop.getDesktop().open(file);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        }
    }
    DeskDemo() throws HeadlessException {
        attachListeners();
        doLay();
    }
    private void attachListeners(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
 
    private void doLay(){
        Container container = getContentPane();
        container.add(new JButton(new DemoShowAction()),BorderLayout.NORTH);
        pack();
        setVisible(true);
    }
    public static void main(String[] args) {
        System.setProperty("swing.defaultlaf","com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        SwingUtilities.invokeLater(
            new Runnable(){
                public void run() {
                    new DeskDemo();
                }
            }
        );
    }
}
