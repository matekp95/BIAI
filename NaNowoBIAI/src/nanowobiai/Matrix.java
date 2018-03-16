/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nanowobiai;

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
            j=0;
            i++;
        }*/
    }
    public Integer[] getSingleRow(int number)
    {
        return table[number];
    }
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
    public void setField(Integer number,Integer row, Integer column){
        table[row][column]=number;
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
