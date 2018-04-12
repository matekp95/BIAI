/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package viewCLasses;

/**
 *Plan of single class
 * @author Kamil Sowa
 */
public class Plan {
    /**
     * plan of class
     */
    private String[][] table;
    /**
     * number of rows, hours
     */
    private int rows;
    /**
     * number of columns , days
     */
    private int columns;

    /**
     *Constructor
     * @param rows rows
     * @param columns columns
     */
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

    /**
     *Prints class plan
     */
    public void matrixPrint(){
        float hourToWrite=10;
        for (String[] row: table){
            
            System.out.printf("%.0f:00 ", hourToWrite);
            hourToWrite+=2;
            for (String field: row){
                if (field==null)
                {
                    System.out.print("- ");
                }
                else
                    System.out.print(field+" ");
            }
            System.out.println("");
        }
    }

    /**
     *Set class plan field
     * @param number teacher name
     * @param row row
     * @param column column
     */
    public void setField(String number,Integer row, Integer column){
        table[row][column]= number;
    }

    /**
     *Get class plan Field
     * @param row row
     * @param column column
     * @return
     */
    public String getField(Integer row, Integer column){
        return table[row][column];
    }

    /**
     *Get table
     * @return table
     */
    public String[][] getTable() {
        return table;
    }

    /**
     *Sets table
     * @param table table
     */
    public void setTable(String[][] table) {
        this.table = table;
    }

    /**
     *Gets number of rows
     * @return number of rows
     */
    public int getRows() {
        return rows;
    }

    /**
     *Sets number of rows
     * @param rows number of rows
     */
    public void setRows(int rows) {
        this.rows = rows;
    }

    /**
     *gets number of columns
     * @return number of columns
     */
    public int getColumns() {
        return columns;
    }

    /**
     *Sets number of columns
     * @param columns number of columns
     */
    public void setColumns(int columns) {
        this.columns = columns;
    }
    
}
