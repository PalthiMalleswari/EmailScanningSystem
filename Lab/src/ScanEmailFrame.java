
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ScanEmailFrame extends JFrame {
    private JTextArea emailTextArea;
    private JButton scanButton;
    private JLabel resultLabel;

    public ScanEmailFrame() {
        setTitle("Scan Email");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());

        // Email content input
        emailTextArea = new JTextArea();
        add(new JScrollPane(emailTextArea), BorderLayout.CENTER);

        // Scan button
        scanButton = new JButton("Scan");
        add(scanButton, BorderLayout.SOUTH);

        // Result label
        resultLabel = new JLabel("Suspicion Percentage: ");
        add(resultLabel, BorderLayout.NORTH);

        scanButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String emailContent = emailTextArea.getText();
                KeywordDAO keywordDAO = new KeywordDAO();
                SuspicionCalculator suspicionCalculator = new SuspicionCalculator(keywordDAO);
                double suspicionPercentage = suspicionCalculator.calculateSuspicionPercent(emailContent);
                String formattedPercentage = String.format("%.2f", suspicionPercentage);
                resultLabel.setText("Suspicion Percentage: " + formattedPercentage + "%");
            }
        });

        setVisible(true);
    }
}
