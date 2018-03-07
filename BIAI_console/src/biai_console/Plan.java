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
public class Plan {
    private String[][] table;
    private int rows;
    private int columns;
    public Plan(int rows,int columns){
        table=new String[rows][columns];
        for (String[] row: table){
            for (String field: row){
               field=null;
            }
        }
        this.columns=columns;
        this.rows=rows;
    }
    public void matrixPrint(){
        for (String[] row: table){
            for (String field: row){
                System.out.print(field+" ");
            }
            System.out.println("");
        }
    }
    public void setField(String number,Integer row, Integer column){
        table[row][column]= number;
    }
    public String getField(Integer row, Integer column){
        return table[row][column];
    }

    public String[][] getTable() {
        return table;
    }

    public void setTable(String[][] table) {
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
