/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biai_console;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Kamil Sowa
 */
public class ChromosomeDAO {
    private Matrix matrix;
    private Random rand=new Random();
    private List<Teacher> listOfTeachers;
    private Integer numberOfClasses;
    private Integer numberOfHours;
    private Integer numberofTeachers;
    private DAO dao;

    public ChromosomeDAO(Integer numberOfClasses, Integer numberOfHours, Integer numberofTeachers) {
        this.numberOfClasses = numberOfClasses;
        this.numberOfHours = numberOfHours;
        this.numberofTeachers = numberofTeachers;
       
        this.listOfTeachers=DAO.getInstancja().getTeachers();
    }
  
   
    
    void createRandomMatrix(){
        matrix=new Matrix(numberofTeachers,numberOfHours);
        int randHour;
        
        for (int teacher=0;teacher<listOfTeachers.size();teacher++){
            
            for (int i=0;i<listOfTeachers.get(teacher).getListOfTaughtClasses().size();i++){
                
                
                int cLass=listOfTeachers.get(teacher).getListOfTaughtClasses().get(i);
                randHour=rand.nextInt(matrix.getColumns());
                 findClassAtHour(cLass, randHour);
                while ( matrix.getField(teacher, randHour) != null  ||  findClassAtHour(cLass, randHour))  {
                    randHour=rand.nextInt(matrix.getColumns());
                }
                
                matrix.setField(cLass, teacher, randHour);
                
            }
        }
        int cos=2;
       
    }
    private boolean findClassAtHour(int numberOfClass,int hour){
        
        for (int teacher=0;teacher<numberofTeachers;teacher++){
            if (matrix.getField(teacher, hour)!=null && matrix.getField(teacher, hour)==numberOfClass)
                return true;
        }
        return false;
    }
    void printChromosome() {
     
         matrix.matrixPrint();
        
    }
    void printTeachersClasses(){
        
        if (!listOfTeachers.isEmpty()) {
            for (Teacher t: listOfTeachers){
                t.print();
            }
        }
        System.out.println();
        
        
        
    }
    public Matrix getMatrix() {
        return matrix;
    }
    public void setMatrix(Matrix matrix) {
        this.matrix = matrix;
    }
    public Integer getNumberOfClasses() {
        return numberOfClasses;
    }
    public void setNumberOfClasses(Integer numberOfClasses) {
        this.numberOfClasses = numberOfClasses;
    }
    public Integer getNumberofTeachers() {
        return numberofTeachers;
    }
    public void setNumberofTeachers(Integer numberofTeachers) {
        this.numberofTeachers = numberofTeachers;
    }
    public Integer getNumberOfHours() {
        return matrix.getColumns();
    }
    public void setNumberOfHours(Integer numberOfHours) {
        this.numberOfHours = numberOfHours;
    }
    
}
