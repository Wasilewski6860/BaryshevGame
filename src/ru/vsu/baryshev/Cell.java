package ru.vsu.baryshev;

public class Cell {
    private boolean isWall = false;
    private boolean isGate = false;
    private boolean isBall = false;
    private boolean isFree = false;
//    private boolean isBallBlue = false;
//    private boolean isBallRed = false;
//    private boolean isBallGreen = false;
//    private boolean isBallYellow = false;
//    private boolean isGateYellow = false;
//    private boolean isGateRed = false;
//    private boolean isGateBlue = false;
//    private boolean isGateGreen = false;
    private boolean isFilledGate = false;


    public enum CellStates {
        WALL,
        BALL,
        GATE,
        FREE,
        GATE_WITH_BALL
    }

    public enum Color {
        BLUE,
        WHITE,
        GRAY,
        RED,
        GREEN,
        YELLOW,
    }

    private CellStates state;
    private Color color;

    public Cell(CellStates state, Color color) {
        this.color = color;
        this.state = state;
    }

    public CellStates getState() {
        return state;
    }

    public void setState(CellStates state) {
        this.state = state;
    }
    public void setColor(Color color){
        this.color=color;
    }

    public Color getColor() {
        return color;
    }

    public boolean isBarrierHere() {
        return isWall;
    }

    public boolean checkForGateWithBall() {
        return isBall && isGate;
    }

//    public boolean isBallBlue() {
//        return isBallBlue;
//    }
//
//    public boolean isBallGreen() {
//        return isBallGreen;
//    }
//
//    public boolean isBallRed() {
//        return isBallRed;
//    }
//
//    public boolean isBallYellow() {
//        return isBallYellow;
//    }
//
//    public void setYellowBall() {
//        isBallYellow = true;
//    }
//
//    public void setBlueBall() {
//        isBallBlue = true;
//    }
//
//    public void setRedBall() {
//        isBallRed = true;
//    }
//
//    public void setGreenBall() {
//        isBallGreen = true;
//    }
//
//    public void resetRedBall() {
//        isBallRed = false;
//    }
//
//    public void resetBlueBall() {
//        isBallBlue = false;
//    }
//
//    public void resetYellowBall() {
//        isBallYellow = false;
//    }
//
//    public void resetGreenBall() {
//        isBallGreen = false;
//    }
//
//    public void setYellowGate() {
//        isGateYellow = true;
//    }
//
//    public void setBlueGate() {
//        isGateBlue = true;
//    }
//
//    public void setRedGate() {
//        isGateRed = true;
//    }
//
//    public void setGreenGate() {
//        isGateGreen = true;
//    }
//
//    public void resetRedGate() {
//        isGateRed = false;
//    }
//
//    public void resetBlueGate() {
//        isGateBlue = false;
//    }
//
//    public void resetYellowGate() {
//        isGateYellow = false;
//    }
//
//    public void resetGreenGate() {
//        isGateGreen = false;
//    }

    public boolean isballhere() {
        return isBall;
    }

    public boolean isGate() {
        return isGate;
    }

    public boolean isGate2(Cell cell,Color color){
        if (cell.getState()==CellStates.GATE && cell.getColor()==color) return true; else return false;
    }
    public boolean isWall2(Cell cell) {
        if (cell.getState() == CellStates.WALL)
            return true;
        else return false;
    }
    public boolean isBall2(Cell cell){
        if (cell.getState() == CellStates.BALL) return true; else  return false;
    }

    public void setGate() {
        isGate = true;
    }

    public boolean isFilledGate() {
        return isFilledGate;
    }

    public void fillTheGate() {
    isFilledGate=true;
}
    public void setBall() throws IllegalCellException {
        if(isWall || isGate) throw new IllegalCellException("Please, reput ball");
        isBall = true;
    }

    public void resetBall(){
        isBall = false;
    }
    public static Cell setBall2(Cell cell, Color color){
        cell.setState(CellStates.BALL);
        cell.setColor(color);
        return cell;
    }
    public static Cell resetBall2(Cell cell){
        cell.setState(CellStates.FREE);
        cell.setColor(Color.WHITE);
        return cell;
    }
    public static Cell setFilledGate(Cell cell){
        cell.setState(CellStates.GATE_WITH_BALL);
        return cell;
    }

    public void setBarrier() throws IllegalCellException {
        if(isWall || isGate || isBall) throw new IllegalCellException("please,reset wall");
        isBall = true;
    }

    public void resetBarrier(){
        isWall = false;
    }

    public static class IllegalCellException extends Exception {
        public IllegalCellException(String errorMessage) {
            super(errorMessage);
        }
    }
}
