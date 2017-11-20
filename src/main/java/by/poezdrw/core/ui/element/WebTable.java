package by.poezdrw.core.ui.element;

import by.poezdrw.exception.WrongElementException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Wrapper Class for WebElement representing table of standard design
 * May do not work with special kinds of non-standard tables (without <tr> tags and so on)
 */
public class WebTable {

    private static final String TABLE_ROW = "tr";
    private static final String TABLE_COLUMN = "td";
    private static final String TABLE_HEADER = "th";
    private static final String TABLE_HEAD = "thead";

    private WebElement webTable;

    public WebTable(WebElement webTable) {
        this.webTable = webTable;
    }

    public WebElement getWebTable() {
        return webTable;
    }

    public void setWebTable(WebElement webTable) {
        this.webTable = webTable;
    }

        /**
         * Method to count number of rows in table including header row
         * @return number of rows in table
         */
    public int getRowCount() throws WrongElementException {
        List<WebElement> tableRows = webTable.findElements(By.tagName(TABLE_ROW));
        if (tableRows.isEmpty()) {
            throw new WrongElementException("Wrong element (not WebTable) found! Table doesn't contain <tr> tags");
        }
        return tableRows.size();
    }

    /**
     * Method to count number of columns in table (counting by last row)
     * @return number of columns in table. May be 0 value if table doesn't contain <td> tag
     */
    public int getColumnCount() throws WrongElementException {
        List<WebElement> tableRows = webTable.findElements(By.tagName(TABLE_ROW));
        if (!tableRows.isEmpty()) {
            WebElement lastRow = tableRows.get(tableRows.size() - 1);
            List<WebElement> tableCols = lastRow.findElements(By.tagName(TABLE_HEADER));  // for non-standard tables, where 1-st column is <th>
            tableCols.addAll(lastRow.findElements(By.tagName(TABLE_COLUMN)));

            return tableCols.size();
        }
        throw new WrongElementException("Wrong element (not Table) found! Table doesn't contain <td> tags");
    }

    /**
     * Read and return cell data from table. Works also for header that is inside <tr> tag
     * @param rowIndex starting from 1
     * @param colIndex starting from 1
     * @return text from cell
     */
     public String getCellData(int rowIndex, int colIndex) throws WrongElementException {
         if ((rowIndex > 0 && rowIndex <= getRowCount()) && (colIndex > 0 && colIndex <= getColumnCount())) {
             List<WebElement> tableRows = webTable.findElements(By.tagName(TABLE_ROW));
             WebElement currentRow = tableRows.get(rowIndex - 1);
             List<WebElement> tableCols = currentRow.findElements(By.tagName(TABLE_HEADER));  // for non-standard tables, where 1-st column is <th>
             tableCols.addAll(currentRow.findElements(By.tagName(TABLE_COLUMN)));
             WebElement cell = tableCols.get(colIndex - 1);
             return cell.getText();
         }
         else {
             throw new WrongElementException("Wrong input data for getCellData method of Table! Table doesn't contain " + rowIndex +
                     " rowIndex or/and " + colIndex + " colIndex");
         }
     }


}
