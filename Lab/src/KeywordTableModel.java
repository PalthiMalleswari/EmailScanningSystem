
import javax.swing.table.AbstractTableModel;
import java.util.List;

public class KeywordTableModel extends AbstractTableModel {
    private List<Keyword> keywords;
    private String[] columnNames = {"ID", "Keyword", "Weight"};

    public KeywordTableModel(List<Keyword> keywords) {
        this.keywords = keywords;
    }

    @Override
    public int getRowCount() {
        return keywords.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Keyword keyword = keywords.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return keyword.getId();
            case 1:
                return keyword.getKeyword();
            case 2:
                return keyword.getWeight();
        }
        return null;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    public Keyword getKeywordAt(int row) {
        return keywords.get(row);
    }

    public void setKeywords(List<Keyword> keywords) {
        this.keywords = keywords;
    }
}
