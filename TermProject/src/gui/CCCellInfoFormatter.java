package gui;

import termproject.Cell;

public class CCCellInfoFormatter implements CellInfoFormatter {

    @Override
    public String format(Cell cell) {
        return "<html><font color='black'><b>" + cell.getName() + "</b></font></html>";
    }
}
