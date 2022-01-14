package ru.vsu.baryshev;

public class GameState {

    private final Cell[][] gameField;

    public GameState(Cell[][] board) {
        this.gameField = board;
    }

    public Cell[][] getGameField() {
        return gameField;
    }


    public enum Directions {
        UP,
        LEFT,
        RIGHT,
        DOWN
    }
    public static class IllegalBoardException extends Exception {
        public IllegalBoardException(String errorMessage) {
            super(errorMessage);
        }
    }

    public static class IllegalPointPosException extends Exception {
        public IllegalPointPosException(String errorMessage) {
            super(errorMessage);
        }
    }
//    private Cell getCell(Point Point) throws IllegalPointPosException {
//        if (Point.x > 0 && Point.x < gameField[0].length && Point.y > 0 && Point.y < gameField.length)
//            return gameField[Point.y][Point.x];
//        else throw new IllegalPointPosException("point out of board");
//    }

    private int getBallX(Cell.Color color) throws IllegalBoardException {

        for (int y = 0; y < gameField.length; y++) {
            for (int x = 0; x < gameField[0].length; x++) {
                switch (color){
                    case GREEN:{
                        if (gameField[y][x].isballhere() && gameField[y][x].getState().equals(Cell.Color.GREEN)) return   x;
                    }
                    case RED:{
                        if (gameField[y][x].isballhere() && gameField[y][x].getState().equals(Cell.Color.RED)) return   x;
                    }case BLUE:{
                        if (gameField[y][x].isballhere() && gameField[y][x].getState().equals(Cell.Color.BLUE)) return x;
                    }case YELLOW:{
                        if (gameField[y][x].isballhere() && gameField[y][x].getState().equals(Cell.Color.YELLOW)) return x;
                    }
                }
            }
        }
        throw new IllegalBoardException("There aren't required ball");
    }
    private int getBallY(Cell.Color color) throws IllegalBoardException {

        for (int y = 0; y < gameField.length; y++) {
            for (int x = 0; x < gameField[0].length; x++) {
                switch (color){
                    case GREEN:{
                        if (gameField[y][x].isballhere() && gameField[y][x].getState().equals(Cell.Color.GREEN)) return   y;
                    }
                    case RED:{
                        if (gameField[y][x].isballhere() && gameField[y][x].getState().equals(Cell.Color.RED)) return   y;
                    }case BLUE:{
                        if (gameField[y][x].isballhere() && gameField[y][x].getState().equals(Cell.Color.BLUE)) return y;
                    }case YELLOW:{
                        if (gameField[y][x].isballhere() && gameField[y][x].getState().equals(Cell.Color.YELLOW)) return y;
                    }
                }
            }
        }
        throw new IllegalBoardException("There aren't required ball");
    }

    private static int[] movePointInDir(int[] coordinates, Directions dir) {
//        switch (dir) {
//            case UP : coordinates[0]--;
//            case DOWN : coordinates[0]++;
//            case LEFT : coordinates[1]++;
//            case RIGHT : coordinates[1]--;
//        }
        if (dir==Directions.UP){
            coordinates[0]--;
        }else if (dir==Directions.DOWN){
            coordinates[0]++;
        }else if (dir==Directions.LEFT){
            coordinates[1]--;
        }else if (dir==Directions.RIGHT){
            coordinates[1]++;
        }else System.out.println("movePointInDir:something went wrong");
        return coordinates;
    }

    public static boolean checkForWin(Cell[][] array) {
        for (Cell[] row : array) {
            for (Cell cell : row) {
                if (cell.getState()== Cell.CellStates.GATE) return false;
            }
        }
        return true;
    }

//    public void moveBall(Directions direction
//                         //  , int x, int y
//                         ) throws IllegalPointPosException, Cell.IllegalCellException, IllegalBoardException {
//        int firstX = getBallX(Cell.Color.BLUE);
//        int firstY = getBallY(Cell.Color.BLUE);
//
//
//        Point secondBallPoint = new Point(firstBallPoint);
//        movePointInDir(secondBallPoint, direction);
//         if ((!getCell(secondBallPoint).isBarrierHere() || ! getCell(secondBallPoint).isballhere())){
//            getCell(secondBallPoint).setBall();
//            getCell(firstBallPoint).resetBall();
//    }else if (getCell(secondBallPoint).isGate()){
//            getCell(firstBallPoint).resetBall();
//            getCell(secondBallPoint).setState(Cell.CellStates.GATE_WITH_BALL);
//        }
//
//    }
    public static Cell[][] newMoving(Directions dir,int row,int col,Cell[][] cellArray){

        Cell currentCell=cellArray[row][col];
        Cell helpCell = new Cell(Cell.CellStates.FREE, Cell.Color.WHITE);
        int[] arr2 = {row,col};
        int[] arr=movePointInDir(arr2,dir);
        helpCell=cellArray[arr[0]][arr[1]];
        System.out.println("Basic coordinates "+row+" "+col);
        System.out.println("Bas.Coord.In Array: rows "+arr2[0]+"col "+arr2[1]);
        System.out.println(arr[0]+" "+ arr[1]);

        if (helpCell.isWall2(helpCell)==false && helpCell.isBall2(helpCell)==false &&currentCell.getState()== Cell.CellStates.BALL&& helpCell.getState() != Cell.CellStates.GATE){

            System.out.println("Current Color "+ currentCell.getColor());
            System.out.println("New Color "+ helpCell.getColor());
            System.out.println("Check For gate of the same color "+helpCell.isGate2(helpCell,currentCell.getColor()));
            Cell.setBall2(helpCell, currentCell.getColor());
            System.out.println("ball moved into " + dir);
            Cell.resetBall2(currentCell);
            cellArray[row][col]=currentCell;
            cellArray[arr[0]][arr[1]]=helpCell;
            System.out.println(" ");
            return cellArray;

        }else
            if (helpCell.isGate2(helpCell,currentCell.getColor())     && currentCell.isBall2(currentCell)){

            helpCell.setState(Cell.CellStates.GATE_WITH_BALL);

            System.out.println("We are going to gate!");
            currentCell.setState(Cell.CellStates.FREE);
            cellArray[row][col]=currentCell;
            cellArray[arr[0]][arr[1]]=helpCell;

                System.out.println(" ");
            return cellArray;

        }else {
                System.out.println("");
                System.out.println("ball don't moved into " + dir);

                System.out.println("You can't move");
                return cellArray;
            }
    }


}
