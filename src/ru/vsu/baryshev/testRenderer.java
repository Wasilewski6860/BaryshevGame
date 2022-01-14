package ru.vsu.baryshev;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

//class testRenderer extends DefaultTableCellRenderer {
//    public Component getTableCellRendererComponent(JTable table,
//                                                   Object value,
//                                                   boolean isSelected,
//                                                   boolean hasFocus,
//                                                   int row,
//                                                   int column) {
//        Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
//        Cell c1 = (Cell) value;
//
//        cell.setBackground(Color.RED);
//        return cell;
//    }
class testRenderer extends DefaultTableCellRenderer {
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                   boolean hasFocus, int rowIndex, int vColIndex) {

        if (value==null) return this;
        Cell cell = (Cell) value;

//        System.out.println(cell.getState());

        switch (cell.getState()) {
            case FREE: {
                setBackground(Color.WHITE);
                break;
            }
            case BALL: {
                switch (cell.getColor()) {
                    case BLUE: {
                        setBackground(Color.BLUE);
                        break;
                    }
                    case YELLOW: {
                        setBackground(Color.YELLOW);
                        break;
                    }
                    case GREEN: {
                        setBackground(Color.GREEN);
                        break;
                    }
                    case RED: {
                        setBackground(Color.RED);
                        break;
                    }
                }
            }
            case WALL: {
                setBackground(Color.GRAY);
                break;
            }
            case GATE:{
                switch (cell.getColor()){
                    case BLUE: {
                        setBackground(Color.BLUE);
                        break;
                    }
                    case YELLOW: {
                        setBackground(Color.YELLOW);
                        break;
                    }
                    case GREEN: {
                        setBackground(Color.GREEN);
                        break;
                    }
                    case RED: {
                        setBackground(Color.RED);
                        break;
                    }
                }
            }
            case GATE_WITH_BALL:{
                setBackground(Color.LIGHT_GRAY);
                break;
            }
        }


        return this;
    }
}
