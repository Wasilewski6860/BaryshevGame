package ru.vsu.baryshev;

public class Cell {
    //Класс отдельной клетки

    public enum CellStates { //Состояния клетки
        WALL,
        BALL,
        GATE,
        FREE,
        GATE_WITH_BALL
    }

    public enum Color { // Цвета, которые могут принимать объекты
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

    //Геттеры и сеттеры для содержимого и цвета ячейки
    public CellStates getState() {
        return state;
    }

    public void setState(CellStates state) {
        this.state = state;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    //Проверка передаваемой ячейки на то, содержит ли она ворота соотв.цвета
    public boolean isGate2(Cell cell, Color color) {
        if (cell.getState() == CellStates.GATE && cell.getColor() == color) return true;
        else return false;
    }


//ПРоверка на наличие мяча в ячейке
    public boolean isBall(Cell cell) {
        if (cell.getState() == CellStates.BALL) return true;
        else return false;
    }

//Установка мяча соотв.цвета в ячейку
    public static Cell setBall(Cell cell, Color color) {
        cell.setState(CellStates.BALL);
        cell.setColor(color);
        return cell;
    }
//Удаление мяча из ячейки
    public static Cell resetBall(Cell cell) {
        cell.setState(CellStates.FREE);
        cell.setColor(Color.WHITE);
        return cell;
    }

}
