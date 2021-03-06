package ru.vsu.baryshev;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

//Переопределяем рендерер
class testRenderer extends DefaultTableCellRenderer {
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                   boolean hasFocus, int rowIndex, int vColIndex) {

        if (value == null) return this;
        Cell cell = (Cell) value;

        //Сначала проходимся по состояниям ячеек, после по цветам
        switch (cell.getState()) {

            // свободная ячейка по умолчанию белая
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

                break;
            }

            //Стена по умолчанию серая
            case WALL: {
                setBackground(Color.GRAY);
                break;
            }

            //Красим ворота, выбраны более светлые цвета
            case GATE: {

                switch (cell.getColor()) {
                    case BLUE: {
                        setBackground(new Color(56, 146, 176));
                        break;
                    }
                    case YELLOW: {
                        setBackground(new Color(174, 176, 56));
                        break;
                    }
                    case GREEN: {
                        setBackground(new Color(56, 176, 104));
                        break;
                    }
                    case RED: {
                        setBackground(new Color(176, 56, 56));
                        break;
                    }
                }

                break;
            }

            //Заполненные ворота светло серые
            case GATE_WITH_BALL: {

                setBackground(Color.LIGHT_GRAY);
                break;
            }
        }


        return this;
    }
}
