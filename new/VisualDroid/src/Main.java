import javax.swing.*;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.net.MalformedURLException;
import java.net.URL;
import javax.imageio.ImageIO;
import java.io.IOException;

public class Main implements  ActionListener{

    int batteryLevel = 70;
    String statusValue = "";
    boolean hoverStatus = false;
    int droidPositionXDefault = 50;
    int droidPositionYDefault = 500;
    int droidPositionX = droidPositionXDefault;
    int droidPositionY = droidPositionYDefault;
    boolean winCondition = false;
    boolean hitMine = false;

    JPanel titlePanel, buttonPanelA, buttonPanelB, gamePanel;
    JLabel batteryLabel, batteryLabelValue, hoverLabel, hoverLabelValue, statusLabel, statusLabelValue, droidLabel, landscapeLabel;
    JButton bRecharge, bHover, resetButton, leftButton, rightButton, upButton, downButton;
    ImageIcon droidImageGrounded = null;
    ImageIcon droidImageHover = null;

//    ImageIcon droidImageGrounded = new ImageIcon("X:\\VisualDroid\\new\\VisualDroid\\src\\img\\droidGrounded.gif");
//    ImageIcon droidImageHover = new ImageIcon("X:\\VisualDroid\\new\\VisualDroid\\src\\img\\droidHover.gif");
//    Image droidImageGrounded = null;
//    Image droidImageHover = null;

    public JPanel createContentPane () {

        // Master JPanel for GUI
        JPanel totalGUI = new JPanel();
        totalGUI.setLayout(null);

        // TitlePanel
        titlePanel = new JPanel();
        titlePanel.setLayout(null);
        titlePanel.setLocation(150, 10);
        titlePanel.setSize(400, 50);
        titlePanel.setBorder(BorderFactory.createLineBorder(Color.black));
        totalGUI.add(titlePanel);

        batteryLabel = new JLabel("Battery Level");
        batteryLabel.setLocation(0, 0);
        batteryLabel.setSize(120, 30);
        batteryLabel.setHorizontalAlignment(0);
        titlePanel.add(batteryLabel);

        batteryLabelValue = new JLabel("" + batteryLevel);
        batteryLabelValue.setLocation(0, 0);
        batteryLabelValue.setSize(120, 70);
        batteryLabelValue.setHorizontalAlignment(0);
        titlePanel.add(batteryLabelValue);

        hoverLabel = new JLabel("Hover Status");
        hoverLabel.setLocation(130, 0);
        hoverLabel.setSize(120, 30);
        hoverLabel.setHorizontalAlignment(0);
        titlePanel.add(hoverLabel);

        hoverLabelValue = new JLabel("" + hoverStatus);
        hoverLabelValue.setLocation(130, 0);
        hoverLabelValue.setSize(120, 70);
        hoverLabelValue.setHorizontalAlignment(0);
        titlePanel.add(hoverLabelValue);

        statusLabel = new JLabel("Status");
        statusLabel.setLocation(260, 0);
        statusLabel.setSize(120, 30);
        statusLabel.setHorizontalAlignment(0);
        titlePanel.add(statusLabel);

        statusLabelValue = new JLabel("" + statusValue);
        statusLabelValue.setLocation(260, 0);
        statusLabelValue.setSize(120, 70);
        statusLabelValue.setHorizontalAlignment(0);
        titlePanel.add(statusLabelValue);

        // ButtonPanelA
        buttonPanelA = new JPanel();
        buttonPanelA.setLayout(null);
        buttonPanelA.setLocation(50, 650);
        buttonPanelA.setSize(300, 70);
        totalGUI.add(buttonPanelA);

        bRecharge = new JButton("Recharge battery");
        bRecharge.setLocation(0, 0);
        bRecharge.setSize(150, 30);
        bRecharge.addActionListener(e -> actionPerformed(e));
        buttonPanelA.add(bRecharge);

        bHover = new JButton("Hover ON/OFF");
        bHover.setLocation(170, 0);
        bHover.setSize(120, 30);
        bHover.addActionListener(this);
        buttonPanelA.add(bHover);

        resetButton = new JButton("Reset");
        resetButton.setLocation(0, 40);
        resetButton.setSize(290, 30);
        resetButton.addActionListener(this);
        buttonPanelA.add(resetButton);

        // ButtonPanelB
        buttonPanelB = new JPanel();
        buttonPanelB.setLayout(null);
        buttonPanelB.setLocation(450, 650);
        buttonPanelB.setSize(300, 70);
        totalGUI.add(buttonPanelB);

        upButton = new JButton("Up");
        upButton.setLocation(90, 0);
        upButton.setSize(80, 30);
        upButton.addActionListener(this);
        buttonPanelB.add(upButton);

        leftButton = new JButton("Left");
        leftButton.setLocation(0, 40);
        leftButton.setSize(80, 30);
        leftButton.addActionListener(this);
        buttonPanelB.add(leftButton);

        downButton = new JButton("Down");
        downButton.setLocation(90, 40);
        downButton.setSize(80, 30);
        downButton.addActionListener(this);
        buttonPanelB.add(downButton);

        rightButton = new JButton("Right");
        rightButton.setLocation(180, 40);
        rightButton.setSize(80, 30);
        rightButton.addActionListener(this);
        buttonPanelB.add(rightButton);

        // Game panel
        gamePanel = new JPanel();
        gamePanel.setLayout(null);
        gamePanel.setLocation(0, 0);
        gamePanel.setSize(750, 600);
        gamePanel.setBorder(BorderFactory.createLineBorder(Color.black));
        totalGUI.add(gamePanel);

        Image landscapeImage = null;
        URL droidGroundedImageURL = null;
        URL droidHoverImageURL = null;

        try {
            URL landscapeImageURL = new URL("http://www.mfphoto.co.uk/VisualDroid/img/landscape2.jpg");
            landscapeImage = ImageIO.read(landscapeImageURL);
            droidHoverImageURL = new URL("http://www.mfphoto.co.uk/VisualDroid/img/droidHover.gif");
            droidImageHover = new ImageIcon(droidHoverImageURL);
            droidGroundedImageURL = new URL("http://www.mfphoto.co.uk/VisualDroid/img/droidGrounded.gif");
            droidImageGrounded = new ImageIcon(droidGroundedImageURL);

        } catch (IOException e) {
            e.printStackTrace();
        }

        droidLabel = new JLabel(new ImageIcon(droidGroundedImageURL));
        droidLabel = new JLabel(new ImageIcon(droidHoverImageURL));
        droidLabel.setLocation(droidPositionX, droidPositionY);
        droidLabel.setSize(50, 50);
        droidLabel.createImage(50, 50);
        droidLabel.setIcon(droidImageGrounded);
        gamePanel.add(droidLabel);

        landscapeLabel = new JLabel(new ImageIcon(landscapeImage));
        landscapeLabel.setLocation(0, 80);
        landscapeLabel.setSize(750, 600);
        landscapeLabel.createImage(750, 600);
        gamePanel.add(landscapeLabel);

        totalGUI.setOpaque(true);
        return totalGUI;
    }

    // This is the new ActionPerformed Method. It catches any events with an ActionListener attached.
    // Using an if statement, we can determine which button was pressed and change the appropriate values in our GUI.

    public void actionPerformed(ActionEvent e) {
        Main gameArea = new Main();
        statusLabelValue.setText("");
        if(e.getSource() == bRecharge) {
            batteryLevel = 100;
            batteryLabelValue.setText(""+batteryLevel);
        }
        else if(e.getSource() == bHover) {
            hoverStatus = !hoverStatus;
            hoverLabelValue.setText(""+hoverStatus);
            if (hoverStatus) {
                droidLabel.setIcon(droidImageHover);
            } else {
                droidLabel.setIcon(droidImageGrounded);
                }
        }
        else if(e.getSource() == resetButton)        {
            batteryLevel = 50;
            batteryLabelValue.setText(""+batteryLevel);
            hoverStatus = false;
            hoverLabelValue.setText(""+hoverStatus);
            droidPositionX = droidPositionXDefault;
            droidPositionY = droidPositionYDefault;
            droidLabel.setLocation(droidPositionX, droidPositionY);
        }
        else if(e.getSource() == upButton) {
            if (droidPositionY < 350) {
                statusLabelValue.setText("Can't move that way");
            } else {
                droidPositionY = droidPositionY - 50;
                droidLabel.setLocation(droidPositionX, droidPositionY);
                batteryLevel = batteryLevel - 30;
                batteryLabelValue.setText("" + batteryLevel);
            }
        }
        else if(e.getSource() == downButton) {
            if (droidPositionY >= 500) {
                statusLabelValue.setText("Can't move that way");
            } else {
                droidPositionY = droidPositionY + 50;
                droidLabel.setLocation(droidPositionX, droidPositionY);
                batteryLevel = batteryLevel - 30;
                batteryLabelValue.setText("" + batteryLevel);
            }
        }
        else if(e.getSource() == leftButton) {
            droidPositionX = droidPositionX - 50;
            droidLabel.setLocation(droidPositionX, droidPositionY);
            batteryLevel = batteryLevel - 30;
            batteryLabelValue.setText(""+batteryLevel);
        }
        else if(e.getSource() == rightButton) {
            droidPositionX = droidPositionX + 50;
            droidLabel.setLocation(droidPositionX, droidPositionY);
            batteryLevel = batteryLevel - 30;
            batteryLabelValue.setText(""+batteryLevel);
        }
    }

    private void createAndShowGUI() {
            JFrame.setDefaultLookAndFeelDecorated(false);
            JFrame frame = new JFrame("Visual Droid");

            //Create and set up the content pane.
            Main demo = new Main();
            frame.setContentPane(demo.createContentPane());
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(750, 800);
            frame.setVisible(true);
            frame.setResizable(false);
    }

    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        Main visualDroid = new Main();
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    visualDroid.createAndShowGUI();

                }

            });
    }
}