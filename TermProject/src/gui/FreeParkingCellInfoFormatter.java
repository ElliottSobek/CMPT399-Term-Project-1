package edu.ncsu.monopoly.gui;

import termproject.Cell;

public class FreeParkingCellInfoFormatter implements CellInfoFormatter {
    
    public static final String FP_CELL_LABEL = "<html><b>Free Parking</b></html>";
    
    @Override
    public String format(Cell cell) {
        return FP_CELL_LABEL;
    }
}
