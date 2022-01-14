package ru.vsu.baryshev;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static java.lang.System.out;
import static java.lang.System.setOut;

public class InputArgs {


    public static String[] fileToStringArray(String path) throws IOException {  // Считывание данных из файла в строковый массив(построчно)
        // Каждая строка массива = строке файла, и содержит информацию о отельном планшете

        File file = new File(path); // Создается File, после по нему создается сканнер
        Scanner scn = new Scanner(file);
        String[] array = new String[1];
        if (file.length() == 0) { // Проверка на пустоту файла
            out.println("File if empty");
            return null;
        } else {

            String line = scn.nextLine(); // Сканнер считывает первую строку
            List<String> list = new ArrayList<>(); // Создается список строк(это удобно , поскольку:
            // а) данные нужно занести в массив, длину которого мы не знаем, а список такую проблему не ставит
            // б) получив список с нужными значениями, не будет необходимости создавать новый сканнер и проходиться по файлу вновь(если бы мы в одном прогоне посчитали бы число строк, по нему создали бы массив и еще раз прошлись по файлу)
            list.add(line);  // Строка заносится в список

            while (scn.hasNextLine()) {    // Первый прогон, занесение данных в список
                line = scn.nextLine();
                list.add(line);
            }

            String[] helpArray = new String[list.size()]; // Создается строковый массив по длине списка

            for (int i = 0; i < list.size(); i++) {   // Из списка в массив передаются данные
                helpArray[i] = list.get(i);
            }
            array=helpArray;
        }
        return array;
    }

    public static Cell[][] StringArrayToCellArray(String[] array) {
        List<List<String>> BigList = new ArrayList<>();
        int rowCount = 0;

        for (int i = 0; i < array.length; i++) {
            List<String> helpList = Arrays.asList(array[i].split(" "));
            BigList.add(helpList);
            rowCount++;
        }

        int colCount = BigList.get(0).size();

        Cell[][] answer = new Cell[rowCount][colCount];
        for (int i = 0; i < BigList.size(); i++) {
            out.println();
            for (int j = 0; j < BigList.get(0).size(); j++) {
                if (BigList.get(i).get(j).length() == 1) {
                    String str = BigList.get(i).get(j);
                    switch (str){
                        case "w": {
                            Cell cell = new Cell(Cell.CellStates.WALL, Cell.Color.GRAY);
                            out.print("w ");
                            answer[i][j] = cell;
                            break;
                        }
                        case "f": {
                            Cell cell = new Cell(Cell.CellStates.FREE, Cell.Color.WHITE);
                            answer[i][j] = cell;
                            out.print("f ");
                            break;
                        }

                    }

                }else if (BigList.get(i).get(j).length() == 2){
                    switch (BigList.get(i).get(j)){
                        case "bb":{
                            Cell cell = new Cell(Cell.CellStates.BALL, Cell.Color.BLUE);
                            out.print("bb ");
                            answer[i][j] = cell;
                            break;
                        }
                        case "br":{
                            Cell cell = new Cell(Cell.CellStates.BALL, Cell.Color.RED);
                            out.print("bb ");
                            answer[i][j] = cell;
                            break;
                        }
                        case "bg":{
                            Cell cell = new Cell(Cell.CellStates.BALL, Cell.Color.GREEN);
                            out.print("bb ");
                            answer[i][j] = cell;
                            break;
                        }
                        case "by":{
                            Cell cell = new Cell(Cell.CellStates.BALL, Cell.Color.YELLOW);
                            out.print("bb ");
                            answer[i][j] = cell;
                            break;
                        }
                        case "gb":{
                            Cell cell = new Cell(Cell.CellStates.GATE, Cell.Color.BLUE);
                            out.print("bb ");
                            answer[i][j] = cell;
                            break;
                        }
                        case "gr":{
                            Cell cell = new Cell(Cell.CellStates.GATE, Cell.Color.RED);
                            out.print("bb ");
                            answer[i][j] = cell;
                            break;
                        }
                        case "gg":{
                            Cell cell = new Cell(Cell.CellStates.GATE, Cell.Color.GREEN);
                            out.print("bb ");
                            answer[i][j] = cell;
                            break;
                        }
                        case "gy":{
                            Cell cell = new Cell(Cell.CellStates.GATE, Cell.Color.YELLOW);
                            out.print("bb ");
                            answer[i][j] = cell;
                            break;
                        }

                    }
//                    String[] arr = BigList.get(i).get(j).split(",");
//                    switch (arr[0]){
//                        case "b" : {
//                            switch (arr[1]){
//                                case  "b":{
//                                    Cell cell = new Cell(Cell.CellStates.BALL, Cell.Color.BLUE);
//                                    out.print("bb ");
//                                    answer[i][j] = cell;
//                                    break;
//                                }
//                                case  "y":{
//                                    Cell cell = new Cell(Cell.CellStates.BALL, Cell.Color.YELLOW);
//                                    out.print("by");
//                                    answer[i][j] = cell;
//                                    break;
//                                }
//                                case  "g":{
//                                    Cell cell = new Cell(Cell.CellStates.BALL, Cell.Color.GREEN);
//                                    out.print("bg ");
//                                    answer[i][j] = cell;
//                                    break;
//                                }
//                                case  "r":{
//                                    Cell cell = new Cell(Cell.CellStates.BALL, Cell.Color.RED);
//                                    out.print("br ");
//                                    answer[i][j] = cell;
//                                    break;
//                                }
//                            }
//                        }
//                        case "g":{
//                            switch (arr[1]){
//                                case  "b":{
//                                    Cell cell = new Cell(Cell.CellStates.GATE, Cell.Color.BLUE);
//                                    out.print("gb ");
//                                    answer[i][j] = cell;
//                                    break;
//                                }
//                                case  "y":{
//                                    Cell cell = new Cell(Cell.CellStates.GATE, Cell.Color.YELLOW);
//                                    out.print("gy ");
//                                    answer[i][j] = cell;
//                                    break;
//                                }
//                                case  "g":{
//                                    Cell cell = new Cell(Cell.CellStates.GATE, Cell.Color.GREEN);
//                                    out.print("gg ");
//                                    answer[i][j] = cell;
//                                    break;
//                                }
//                                case  "r":{
//                                    Cell cell = new Cell(Cell.CellStates.GATE, Cell.Color.RED);
//                                    out.print("gr ");
//                                    answer[i][j] = cell;
//                                    break;
//                                }
//                            }
//                        }
//                    }
                }

                }
            }
//        for (int i = 0; i < rowCount; i++) {
//            for (int j = 0; j < colCount; j++) {
//                out.println((answer[i][j].getState()));
//            }
//        }
            return answer;

        }


    }
