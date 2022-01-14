package ru.vsu.baryshev;

public class Game { // Класс игры, сожержащий основную логику


    public enum Directions {
        UP,
        LEFT,
        RIGHT,
        DOWN
    }

    private static int[] movePointInDir(int[] coordinates, Directions dir) { // Метод по перемещению точки(точка представлена в виде int[]) в соотв.направлении(Будет использоваться для перемещения объекта ячейки).

        if (dir == Directions.UP) {
            coordinates[0]--;
        } else if (dir == Directions.DOWN) {
            coordinates[0]++;
        } else if (dir == Directions.LEFT) {
            coordinates[1]--;
        } else if (dir == Directions.RIGHT) {
            coordinates[1]++;
        } else return null;
        return coordinates;
    }

    public static boolean checkForWin(Cell[][] array) { // Проверка на окончание игры
        for (Cell[] row : array) {
            for (Cell cell : row) {
                if (cell.getState() == Cell.CellStates.GATE) return false; // Если еще остались незаполненные ворота, игра продолжается
            }
        }
        return true;
    }
    public static boolean isRoadFree(Cell cell){    //Проверка на возможность перемещения
        if(cell.getState()!= Cell.CellStates.WALL && cell.getState() != Cell.CellStates.BALL &&
                cell.getState() != Cell.CellStates.GATE_WITH_BALL && cell.getState() != Cell.CellStates.GATE) return true;
        else return false;
    }


    public static Cell[][] moving(Directions dir, int row, int col, Cell[][] cellArray) { // Метод, перемещающий мяч по Cell[][]. Метод получает координаты мяча, возвращает массив, чтобы в дальнейшем его можно было отрисовывать.

        Cell startCell = cellArray[row][col];// Ячейка, из которой осуществляется движение
        int[] startCoordinates = {row, col}; // Начальные координаты заносятся в массив
        int[] endCoordinates = movePointInDir(startCoordinates, dir); // Ищутся координаты конечной точки, укда преемещается объект
        Cell finishCell = cellArray[endCoordinates[0]][endCoordinates[1]];// По вышесозданным координатам из исходного Cell[][] берется ячейка, куда будет двигаться объект

        if (isRoadFree(finishCell) && startCell.getState() == Cell.CellStates.BALL) { //

            Cell.setBall(finishCell, startCell.getColor());
            Cell.resetBall(startCell);
            cellArray[row][col] = startCell;
            cellArray[endCoordinates[0]][endCoordinates[1]] = finishCell;
            return cellArray;

        } else if (finishCell.isGate2(finishCell, startCell.getColor()) && startCell.isBall(startCell)) {

            finishCell.setState(Cell.CellStates.GATE_WITH_BALL);
            startCell.setState(Cell.CellStates.FREE);
            cellArray[row][col] = startCell;
            cellArray[endCoordinates[0]][endCoordinates[1]] = finishCell;

            return cellArray;

        } else {
            return cellArray;
        }
    }


}
