package zw.co.mikezowa;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RandomRectangleGUI {

    private JFrame frame;
    private JButton colorButton;
    private JButton sizeButton;
    private final RandomRectDrawPanel rectDrawPanel = new RandomRectDrawPanel();


    public static void main (String[] args){
        RandomRectangleGUI gui = new RandomRectangleGUI();
        gui.createFrame();

    }

    public void createFrame(){
        frame = new JFrame("RectanglePlayFrame");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setSize(500,500);


        colorButton = new JButton("Click me for a random colour");
        sizeButton = new JButton("Click me for a random size");

        frame.getContentPane().add(BorderLayout.PAGE_START, colorButton);
        frame.getContentPane().add(BorderLayout.PAGE_END, sizeButton);
        frame.getContentPane().add(BorderLayout.CENTER, rectDrawPanel);

        colorButton.addActionListener(new RandomColorListener());
        sizeButton.addActionListener(new RandomSizeListener());
        rectDrawPanel.repaint();
        frame.setVisible(true);

    }

    class RandomRectDrawPanel extends JPanel{

        private Color backgroundColor;
        private int height = 50;
        private int width = 80;
        private int xAxisPosition = 50;
        private int yAxisPosition = 50;

        @Override
        public void paintComponent (Graphics g){
            super.paintComponent(g);

            g.setColor(backgroundColor);
            g.fillRect(xAxisPosition,yAxisPosition,width,height);
        }

        public void randomColorGenerator(){
            int redColorValue = randomNumberGenerator();
            int greenColorValue = randomNumberGenerator();
            int blueColorValue = randomNumberGenerator();
            backgroundColor = new Color(redColorValue,greenColorValue,blueColorValue);

        }

        public void randomSizeGenerator(){
            int displace = 5;
            height = (int)(Math.random()*getHeight());
            width = (int)(Math.random()*getWidth());

            int temp;
            if ((yAxisPosition + height) > getHeight()){  // this to keep all of the height of the rectangle inside the draw panel
                temp = getHeight() - (yAxisPosition + height);
                height = height + temp - displace;  // temp is a negative number
            }
            if (height < 5) height = 5;//minimum height

            if ((xAxisPosition + width) > getWidth()){  // this to keep all of the width of the rectangle inside the draw panel
                temp = getWidth() - (xAxisPosition + width);
                width = width + temp - displace;  // temp is a negative number
            }
            if (width < 5) width = 5; //minimum width
        }
    }


    public class RandomColorListener implements ActionListener {


        public void actionPerformed(ActionEvent recColor) {
            rectDrawPanel.randomColorGenerator();
            repaintPanel(rectDrawPanel);
        }
    }

    public class RandomSizeListener implements ActionListener {

        public void actionPerformed(ActionEvent recSize) {
            rectDrawPanel.randomSizeGenerator();
            repaintPanel(rectDrawPanel);
        }
    }

    private static int randomNumberGenerator(){
       return  (int)(Math.random()*255);
    }

    private static void repaintPanel(JPanel jPanel){
        jPanel.repaint();
    }

}