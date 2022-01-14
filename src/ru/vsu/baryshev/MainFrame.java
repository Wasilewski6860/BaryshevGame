package ru.vsu.baryshev;

import ru.vsu.baryshev.util.JTableUtils;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class MainFrame extends JFrame {
    private JPanel panelMain;
    private JTable table1;
    private JButton a1Button;
    private JButton a5Button;
    private JButton a3Button;
    private JButton a2Button;
    private JButton a4Button;
    private JButton UPButton;
    private JButton LEFTButton;
    private JButton RIGHTButton;
    private JButton DOWNButton;
    private JTextField textField1;
    private JButton stateColorButton;
    private JButton coordinateButton;
    private JButton paramsOfArrayButton;
    public int selRow = 0;
    public int selCol = 0;
    Cell[][] array = new Cell[1][1];
    GameState gameState = new GameState(array);


    public MainFrame() {
        this.setTitle("Table");
        this.setContentPane(panelMain);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();

        a1Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    //Рабочий массив
                    array = InputArgs.StringArrayToCellArray(InputArgs.fileToStringArray("levels/level01.txt"));
                    gameState=new GameState(array);

                    for (int i=0;i < array.length;i++){
                        System.out.println(Arrays.toString(array[i]));
                    }

                    BoardTableModel model = new BoardTableModel(array);
                    table1.setModel(model);
                    table1.setFocusable(false);
                    table1.setRowSelectionAllowed(false);
                    table1.setEnabled(true);
                    table1.setRowHeight(30);
                    // show grid
                    table1.setShowGrid(true);
                    table1.setGridColor(Color.BLACK);
//                    JTableUtils.writeArrayToJTable(table1,array,"");
                    table1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                    for (int i = 0; i < table1.getColumnCount(); i++) {
                        table1.setDefaultRenderer(table1.getColumnClass(i), new testRenderer());
                        table1.getColumnModel().getColumn(i).setResizable(false);
                    }
                    table1.repaint();


                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        a2Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    //Рабочий массив
                    array = InputArgs.StringArrayToCellArray(InputArgs.fileToStringArray("levels/level02.txt"));
                    gameState=new GameState(array);

                    for (int i=0;i < array.length;i++){
                        System.out.println(Arrays.toString(array[i]));
                    }

                    BoardTableModel model = new BoardTableModel(array);
                    table1.setModel(model);
                    table1.setFocusable(false);
                    table1.setRowSelectionAllowed(false);
                    table1.setEnabled(true);
                    table1.setRowHeight(30);
                    // show grid
                    table1.setShowGrid(true);
                    table1.setGridColor(Color.BLACK);
//                    JTableUtils.writeArrayToJTable(table1,array,"");
                    table1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                    for (int i = 0; i < table1.getColumnCount(); i++) {
                        table1.setDefaultRenderer(table1.getColumnClass(i), new testRenderer());
                        table1.getColumnModel().getColumn(i).setResizable(false);
                    }
                    table1.repaint();


                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher( new KeyEventDispatcher() {
            @Override
            public boolean dispatchKeyEvent(KeyEvent e) {
                if (e.getID() == KeyEvent.KEY_PRESSED) {
                    switch (KeyEvent.getKeyText(e.getKeyCode())) {
                        case "W" : {
                            while (array[selRow][selCol].getState() ==Cell.CellStates.BALL ||array[selRow][selCol].getState() !=Cell.CellStates.WALL) {
                                try {
                                    TimeUnit.MILLISECONDS.sleep(100);
                                    array = GameState.newMoving(GameState.Directions.UP, selRow, selCol, array);
                                    repaint();
                                    selRow--;
                                    boolean check =GameState.checkForWin(array);
                                    if (check){
                                        DefaultTableModel model = (DefaultTableModel) table1.getModel();
                                        model.setRowCount(0);
                                        textField1.setText("You win!");
                                    }
                                } catch (InterruptedException ex) {
                                    ex.printStackTrace();
                                }

                            }
                        }
                        case "A" : {
                            while (array[selRow][selCol].getState() ==Cell.CellStates.BALL ||array[selRow][selCol].getState() !=Cell.CellStates.WALL) {
                                try {
                                    array = GameState.newMoving(GameState.Directions.LEFT, selRow, selCol, array);
                                    repaint();
                                    selCol--;
                                    TimeUnit.MILLISECONDS.sleep(200);

                                } catch (InterruptedException ex) {
                                    ex.printStackTrace();
                                }
                            }

                        }
                        case "S" : {
                            while (array[selRow][selCol].getState() ==Cell.CellStates.BALL ||array[selRow][selCol].getState() !=Cell.CellStates.WALL) {
                                try {
                                    TimeUnit.MILLISECONDS.sleep(200);
                                    array = GameState.newMoving(GameState.Directions.DOWN, selRow, selCol, array);
                                    repaint();
                                    selRow++;
                                    boolean check =GameState.checkForWin(array);
                                    if (check){
                                        DefaultTableModel model = (DefaultTableModel) table1.getModel();
                                        model.setRowCount(0);
                                        textField1.setText("You win!");
                                    }
                                } catch (InterruptedException ex) {
                                    ex.printStackTrace();
                                }
                            }

                        }
                        case "D" : {
                            while (array[selRow][selCol].getState() ==Cell.CellStates.BALL ||array[selRow][selCol].getState() !=Cell.CellStates.WALL) {
                                try {
                                    array = GameState.newMoving(GameState.Directions.RIGHT, selRow, selCol, array);
                                    repaint();
                                    selCol--;
                                    TimeUnit.MILLISECONDS.sleep(200);
                                    boolean check =GameState.checkForWin(array);
                                    if (check){
                                        DefaultTableModel model = (DefaultTableModel) table1.getModel();
                                        model.setRowCount(0);
                                        textField1.setText("You win!");
                                    }
                                } catch (InterruptedException ex) {
                                    ex.printStackTrace();
                                }
                            }

                        }
                    }
                    boolean check =GameState.checkForWin(array);
                    if (check){
                        DefaultTableModel model = (DefaultTableModel) table1.getModel();
                        model.setRowCount(0);
                        textField1.setText("You win!");
                    }

                }
                // if game not finished we need further reaction on buttons
                return false;
            }
        });

        UPButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            while (array[selRow][selCol].getState() ==Cell.CellStates.BALL ||array[selRow][selCol].getState() !=Cell.CellStates.WALL) {
                try {
                    TimeUnit.MILLISECONDS.sleep(100);
                    array = GameState.newMoving(GameState.Directions.UP, selRow, selCol, array);
                    repaint();
                    selRow--;
                    boolean check =GameState.checkForWin(array);
                    if (check){
                        DefaultTableModel model = (DefaultTableModel) table1.getModel();
                        model.setRowCount(0);
                        textField1.setText("You win!");
                    }
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }

            }
                boolean check =GameState.checkForWin(array);
                if (check){
                    DefaultTableModel model = (DefaultTableModel) table1.getModel();
                    model.setRowCount(0);
                    textField1.setText("You win!");
                }


            }
        });

        DOWNButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                while (array[selRow][selCol].getState() ==Cell.CellStates.BALL ||array[selRow][selCol].getState() !=Cell.CellStates.WALL) {
                    try {
                        TimeUnit.MILLISECONDS.sleep(200);
                        array = GameState.newMoving(GameState.Directions.DOWN, selRow, selCol, array);
                        repaint();
                        selRow++;
                        boolean check =GameState.checkForWin(array);
                        if (check){
                            DefaultTableModel model = (DefaultTableModel) table1.getModel();
                            model.setRowCount(0);
                            textField1.setText("You win!");
                        }
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
                boolean check =GameState.checkForWin(array);
                if (check){
                    DefaultTableModel model = (DefaultTableModel) table1.getModel();
                    model.setRowCount(0);
                    textField1.setText("You win!");
                }

            }
        });

        RIGHTButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                while (array[selRow][selCol].getState() ==Cell.CellStates.BALL ||array[selRow][selCol].getState() !=Cell.CellStates.WALL) {
                    try {
                        array = GameState.newMoving(GameState.Directions.RIGHT, selRow, selCol, array);
                        repaint();
                        selCol--;
                        TimeUnit.MILLISECONDS.sleep(200);
                        boolean check =GameState.checkForWin(array);
                        if (check){
                            DefaultTableModel model = (DefaultTableModel) table1.getModel();
                            model.setRowCount(0);
                            textField1.setText("You win!");
                        }
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
                boolean check =GameState.checkForWin(array);
                if (check){
                    DefaultTableModel model = (DefaultTableModel) table1.getModel();
                    model.setRowCount(0);
                    textField1.setText("You win!");
                }
            }
        });

        LEFTButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                while (array[selRow][selCol].getState() ==Cell.CellStates.BALL ||array[selRow][selCol].getState() !=Cell.CellStates.WALL) {
                    try {
                        array = GameState.newMoving(GameState.Directions.LEFT, selRow, selCol, array);
                        repaint();
                        selCol--;
                        TimeUnit.MILLISECONDS.sleep(200);

                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
                boolean check =GameState.checkForWin(array);
                if (check){
                    DefaultTableModel model = (DefaultTableModel) table1.getModel();
                    model.setRowCount(0);
                    textField1.setText("You win!");
                }

            }

        });

        stateColorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textField1.setText(" "+array[selRow][selCol].getColor() +" "+array[selRow][selCol].getState());
            }
        });
        paramsOfArrayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textField1.setText("Длина = "+array[0].length+" Ширина= "+array.length);
            }
        });
        coordinateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textField1.setText(selRow+", "+selCol);
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

    public void repaint(){

        table1.setRowHeight(30);
        table1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        for (int i = 0; i < table1.getColumnCount(); i++) {
            table1.setDefaultRenderer(table1.getColumnClass(i), new testRenderer());
            table1.getColumnModel().getColumn(i).setResizable(false);
        }
        table1.repaint();

    }
    public void test(GameState.Directions dir,int whatWeWantToChange,int howWeWantToChange){
        if (array[selRow][selCol].getState() ==Cell.CellStates.BALL ||array[selRow][selCol].getState() !=Cell.CellStates.WALL){
            array = GameState.newMoving(GameState.Directions.LEFT, selRow, selCol, array);
            repaint();
            whatWeWantToChange=whatWeWantToChange+howWeWantToChange;
            test(dir, whatWeWantToChange, howWeWantToChange);
        }else {
            return;
        }
    }

    private static class BoardTableModel extends DefaultTableModel {
        Object[][] dataEntries;
        @Override
        public boolean isCellEditable(int row,int column){
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
