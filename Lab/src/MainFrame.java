
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {
    private JButton scanEmailButton;
    private JButton manageKeywordsButton;

    public MainFrame() {
        setTitle("Email Scanning System");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());

        // Creating buttons
        scanEmailButton = new JButton("Scan Email");
        manageKeywordsButton = new JButton("Manage Keywords");

        // Adding buttons to the frame
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 2));
        buttonPanel.add(scanEmailButton);
        buttonPanel.add(manageKeywordsButton);

        add(buttonPanel, BorderLayout.SOUTH);

        // Adding event listeners
        scanEmailButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ScanEmailFrame();
            }
        });

        manageKeywordsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ManageKeywordsFrame();
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        new MainFrame();
    }
}
