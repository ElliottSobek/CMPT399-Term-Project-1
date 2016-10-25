package gui;

import termproject.Cell;

public class GoCellInfoFormatter implements CellInfoFormatter {

    public static final String GO_CELL_LABEL = "<html><b>Go</b></html>";

    @Override
    public String format(Cell cell) {
        return GO_CELL_LABEL;
    }
}
