package ru.vsu.baryshev;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.Objects;

public class MainFrame extends JFrame {
    private JPanel panelMain;
    private JTable table1;
    private JButton firstLevelButton;
    private JButton fifthLevelButton;
    private JButton thirdLevelButton;
    private JButton secondLevelButton;
    private JButton forthLevelButton;
    private JTextField textField1;
    private JButton stateColorButton;
    private JButton coordinateButton;
    private JButton paramsOfArrayButton;
    public int selRow = 0;
    public int selCol = 0;
    Cell[][] array = new Cell[1][1];



    public MainFrame() {
        this.setTitle("Table");
        this.setContentPane(panelMain);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();

        //Обработчики событий для кнопок выбора уровня
        firstLevelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    //Рабочий массив
                    array = InputArgs.StringArrayToCellArray(Objects.requireNonNull(InputArgs.fileToStringArray("levels/level01.txt")));
                repaint();


                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        secondLevelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    //Рабочий массив
                    array = InputArgs.StringArrayToCellArray(Objects.requireNonNull(InputArgs.fileToStringArray("levels/level02.txt")));
                    repaint();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        thirdLevelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    //Рабочий массив
                    array = InputArgs.StringArrayToCellArray(Objects.requireNonNull(InputArgs.fileToStringArray("levels/level03.txt")));
                    repaint();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        forthLevelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    //Рабочий массив
                    array = InputArgs.StringArrayToCellArray(Objects.requireNonNull(InputArgs.fileToStringArray("levels/level04.txt")));

                    repaint();

                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        fifthLevelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    //Рабочий массив
                    array = InputArgs.StringArrayToCellArray(Objects.requireNonNull(InputArgs.fileToStringArray("levels/level05.txt")));




                    repaint();


                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });


        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new KeyEventDispatcher() {
            @Override
            public boolean dispatchKeyEvent(KeyEvent e) {
                if (e.getID() == KeyEvent.KEY_PRESSED) {
                    switch (KeyEvent.getKeyText(e.getKeyCode())) {
                        case "W": {
                            while (array[selRow][selCol].getState() == Cell.CellStates.BALL || array[selRow][selCol].getState() != Cell.CellStates.WALL) {
                                    array = Game.moving(Game.Directions.UP, selRow, selCol, array);
                                    repaint();
                                    selRow--;
                                    textField1.setText(selRow+" "+selCol);
                            }
                        }
                        case "A": {
                            while (array[selRow][selCol].getState() == Cell.CellStates.BALL || array[selRow][selCol].getState() != Cell.CellStates.WALL) {
                                    array = Game.moving(Game.Directions.LEFT, selRow, selCol, array);
                                    repaint();
                                    selCol--;
                                textField1.setText(selRow+" "+selCol);
                            }

                        }
                        case "S": {
                            while (array[selRow][selCol].getState() == Cell.CellStates.BALL && array[selRow][selCol].getState() != Cell.CellStates.WALL) {

                                    array = Game.moving(Game.Directions.DOWN, selRow, selCol, array);
                                    repaint();
                                    selRow++;
                                textField1.setText(selRow+" "+selCol);

                            }

                        }
                        case "D": {
                            while (array[selRow][selCol].getState() == Cell.CellStates.BALL || array[selRow][selCol].getState() != Cell.CellStates.WALL) {

                                    array = Game.moving(Game.Directions.RIGHT, selRow, selCol, array);
                                    repaint();
                                    selCol++;
                                textField1.setText(selRow+" "+selCol);
                            }
                        }
                    }
                    boolean check = Game.checkForWin(array);
                    if (check) {
                        DefaultTableModel model = (DefaultTableModel) table1.getModel();
                        model.setRowCount(0);
                        textField1.setText("You win!");
                    }
                }
                return false;
            }
        });




        table1.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                selRow = table1.rowAtPoint(e.getPoint());
                selCol = table1.columnAtPoint(e.getPoint());

            }
        });


    }

    public void repaint() {
        BoardTableModel model = new BoardTableModel(array);
        table1.setModel(model);
        table1.setFocusable(false);
        table1.setRowSelectionAllowed(false);
        table1.setEnabled(true);
        table1.setRowHeight(30);

        table1.setShowGrid(true);
        table1.setGridColor(Color.BLACK);

        table1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        for (int i = 0; i < table1.getColumnCount(); i++) {
            table1.setDefaultRenderer(table1.getColumnClass(i), new testRenderer());
            table1.getColumnModel().getColumn(i).setResizable(false);
        }
        table1.repaint();

    }

    public void test(Game.Directions dir, int whatWeWantToChange, int howWeWantToChange) {
        if (array[selRow][selCol].getState() == Cell.CellStates.BALL || array[selRow][selCol].getState() != Cell.CellStates.WALL) {
            array = Game.moving(Game.Directions.LEFT, selRow, selCol, array);
            repaint();
            whatWeWantToChange = whatWeWantToChange + howWeWantToChange;
            test(dir, whatWeWantToChange, howWeWantToChange);
        } else {
            return;
        }
    }

    private static class BoardTableModel extends DefaultTableModel {
        Object[][] dataEntries;

        @Override
        public boolean isCellEditable(int row, int column) {
            return true;
        }

        public BoardTableModel(Object[][] dataEntries) {

            this.dataEntries = dataEntries;

            setRowCount(dataEntries.length);
            setColumnCount(dataEntries[0].length);


            for (int i = 0; i < dataEntries.length; i++) {
                for (int j = 0; j < dataEntries[0].length; j++) {
                    setValueAt(dataEntries[i][j], i, j);
                }
            }
        }

    }



}
