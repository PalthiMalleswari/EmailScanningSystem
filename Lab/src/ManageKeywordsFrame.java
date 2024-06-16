
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ManageKeywordsFrame extends JFrame {
    private JTable keywordTable;
    private KeywordTableModel keywordTableModel;
    private KeywordDAO keywordDAO;

    public ManageKeywordsFrame() {
        setTitle("Manage Keywords");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        keywordDAO = new KeywordDAO();
        keywordTableModel = new KeywordTableModel(keywordDAO.getKeywords());

        keywordTable = new JTable(keywordTableModel);
        add(new JScrollPane(keywordTable), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 4));

        JButton addButton = new JButton("Add");
        JButton updateButton = new JButton("Update");
        JButton deleteButton = new JButton("Delete");
        JButton refreshButton = new JButton("Refresh");

        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(refreshButton);

        add(buttonPanel, BorderLayout.SOUTH);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addKeyword();
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateKeyword();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteKeyword();
            }
        });

        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                refreshTable();
            }
        });

        setVisible(true);
    }

    private void addKeyword() {
        JTextField keywordField = new JTextField();
        JTextField weightField = new JTextField();
        Object[] message = {
                "Keyword:", keywordField,
                "Weight:", weightField
        };

        int option = JOptionPane.showConfirmDialog(this, message, "Add Keyword", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            String keyword = keywordField.getText();
            double weight;
            try {
                weight = Double.parseDouble(weightField.getText());
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Invalid weight value.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            keywordDAO.addKeyword(keyword, weight);
            refreshTable();
        }
    }

    private void updateKeyword() {
        int selectedRow = keywordTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a keyword to update.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Keyword selectedKeyword = keywordTableModel.getKeywordAt(selectedRow);

        JTextField keywordField = new JTextField(selectedKeyword.getKeyword());
        JTextField weightField = new JTextField(String.valueOf(selectedKeyword.getWeight()));
        Object[] message = {
                "Keyword:", keywordField,
                "Weight:", weightField
        };

        int option = JOptionPane.showConfirmDialog(this, message, "Update Keyword", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            String keyword = keywordField.getText();
            double weight;
            try {
                weight = Double.parseDouble(weightField.getText());
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Invalid weight value.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            keywordDAO.updateKeyword(selectedKeyword.getId(), keyword, weight);
            refreshTable();
        }
    }

    private void deleteKeyword() {
        int selectedRow = keywordTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a keyword to delete.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Keyword selectedKeyword = keywordTableModel.getKeywordAt(selectedRow);
        int option = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this keyword?", "Delete Keyword", JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION) {
            keywordDAO.deleteKeyword(selectedKeyword.getId());
            refreshTable();
        }
    }

    private void refreshTable() {
        keywordTableModel.setKeywords(keywordDAO.getKeywords());
        keywordTableModel.fireTableDataChanged();
    }
}
