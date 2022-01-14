package ru.vsu.baryshev;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

int[][] array = {{1,2,3,4,5,6,7},{1+7,2+7,3+7,4+7,5+7,6+7,7+7}, {8+7,9+7,1+7,11+7,12+7,13+7,14+7},{15+7,16+7,17+7,18+7,19+7,20+7,21+7},{15+7+7,16+7+7,17+7+7,18+7+7,19+7+7,20+7+7,21+7+7}};
        System.out.println(array.length);
        System.out.println(array[0].length);

for (int i=0;i< array.length;i++){
    System.out.println();
    for (int j=0;j<array[0].length;j++){
        System.out.print(array[i][j]+" ");
    }
}
        String str = "bg";
        System.out.println("bg length= "+str.length());

        java.awt.EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }
}
