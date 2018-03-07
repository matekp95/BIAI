/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biai_console;

/**
 *
 * @author Kamil Sowa
 */
public class Matrix {
    private Integer[][] table;
    private int rows;
    private int columns;
    public Matrix(int rows,int columns){
        table=new Integer[rows][columns];
        for (Integer[] row: table){
            for (Integer field: row){
               field=0;
            }
        }
        this.columns=columns;
        this.rows=rows;
    }
    public void matrixPrint(){
        for (Integer[] row: table){
            for (Integer field: row){
                if (field==null)
                {
                    System.out.print("n ");
                }
                else{
                    System.out.print(field+" ");
                }
                
            }
            System.out.println("");
        }
    }
    public void setField(Integer number,Integer row, Integer column){
        table[row][column]=number;
        int pla=1;
    }
    public Integer getField(Integer row, Integer column){
        return table[row][column];
    }

    public Integer[][] getTable() {
        return table;
    }

    public void setTable(Integer[][] table) {
        this.table = table;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getColumns() {
        return columns;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }
}
