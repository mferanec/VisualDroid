import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;


class backImage extends JComponent {

    Image background;

    public backImage(Image background) {
        this.background = background;

    }

    //Overriding the paintComponent method
    @Override
    public void paintComponent(Graphics g) {

        g.drawImage(background, 0, 0, null);  // Drawing image using drawImage method

    }
}


public class Main extends JFrame {

    public Main() throws IOException {

        this.setSize(800, 800);
        this.setTitle("Visual Droid");
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        BufferedImage bf = ImageIO.read(new File("C:\\IntelliJ\\IdeaProjects\\Visual Droid\\src\\img\\Background.jpg"));
        this.setContentPane(new backImage(bf));


        JButton bUp = new JButton("Up", new ImageIcon("C:\\IntelliJ\\IdeaProjects\\Visual Droid\\src\\img\\bUp.jpg"));
        bUp.setBounds(350, 700, 85, 30);
        this.add(bUp);
        JButton bDown = new JButton("Down", new ImageIcon("C:\\IntelliJ\\IdeaProjects\\Visual Droid\\src\\img\\bDown.jpg"));
        bDown.setBounds(350, 730, 85, 30);
        this.add(bDown);
        JButton bLeft = new JButton("Left", new ImageIcon("C:\\IntelliJ\\IdeaProjects\\Visual Droid\\src\\img\\bLeft.jpg"));
        bLeft.setBounds(275, 715, 85, 30);
        this.add(bLeft);
        JButton bRight = new JButton("Right", new ImageIcon("C:\\IntelliJ\\IdeaProjects\\Visual Droid\\src\\img\\bRight.jpg"));
        bRight.setBounds(435, 715, 85, 30);
        this.add(bRight);

    }




    public static void main(String[] args) throws IOException {

        Main f = new Main();
        f.setVisible(true);

    }

}