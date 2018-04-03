/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chromosomeClasses;

/**
 *Represents matrix
 * @author Kamil Sowa
 */
public class Matrix {
    /**
     * Fields of matrix
     */
    private Integer[][] table;
    /**
     * number of rows
     */
    private int rows;
    /**
     * number of columns
     */
    private int columns;

    /**
     *Constructor
     * @param rows rows 
     * @param columns columns
     */
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

    /**
     *Constructor that copy
     * @param matrix matrix
     * @param rows rows
     * @param columns columns
     */
    public Matrix(Matrix matrix,int rows,int columns) {
        this(rows,columns);
        
        for (int i=0;i<rows;i++){
            for (int j=0;j<columns;j++){
                table[i][j]=matrix.getField(i, j);
            }
        }
        /*int i=0,j=0;
        for (Integer[] row: table){
            for (Integer field: row){
               field=matrix.getField(i, j);
               j++;
            }
            j=0
            i++;
        }*/
    }

    /**
     * Get single column of matrix
     * @param number number of column
     * @return column
     */
    public Integer[] getSingleColumn(int number)
    {
        Integer[] tabliczka=new Integer[rows];
        for (int i=0;i<rows;i++){
            tabliczka[i]=table[i][number];
        }
        return tabliczka;
    }

    /**
     *Get single row of matrix
     * @param number number of row
     * @return row
     */
    public Integer[] getSingleRow(int number)
    {
        return table[number];
    }

    /**
     *Prionts matrix
     */
    public void matrixPrint(){
        System.out.print("HOURS \n");
        
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

    /**
     *prints matrix
     * @param nmbHours prints number of hours
     */
    public void matrixPrint(int nmbHours){
        System.out.print("HOURS \n");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (table[i][j]==null)
                {
                    System.out.print("n ");
                }
                else{
                    System.out.print(table[i][j]+" ");
                }
                if (j%nmbHours==nmbHours-1)
                {
                    System.out.print("  ");
                }
            }
            System.out.println("");
        }
        
    }
  
    /**
     *Setter of field
     * @param number number
     * @param row number of row
     * @param column number of column
     */
    public void setField(Integer number,Integer row, Integer column){
        table[row][column]=number;
    }

    /**
     *Getter of field
     * @param row number of row
     * @param column column
     * @return field value
     */
    public Integer getField(Integer row, Integer column){
        return table[row][column];
    }

    /**
     * Getter of table
     * @return table 
     */
    public Integer[][] getTable() {
        return table;
    }

    /**
     * Setter of table
     * @param table table 
     */
    public void setTable(Integer[][] table) {
        this.table = table;
    }

    /**
     *Getter of rows
     * @return number of rows
     */
    public int getRows() {
        return rows;
    }

    /**
     *Setter of rows
     * @param rows number of rows
     */
    public void setRows(int rows) {
        this.rows = rows;
    }

    /**
     *Getter of columns
     * @return number of columns
     */
    public int getColumns() {
        return columns;
    }

    /**
     *Setter of columns
     * @param columns numberof columns
     */
    public void setColumns(int columns) {
        this.columns = columns;
    }
}
