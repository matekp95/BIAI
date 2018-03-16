/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nanowobiai;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import static nanowobiai.ConstansInterface.ANSI_RED;
import static nanowobiai.ConstansInterface.ANSI_RESET;

/**
 *
 * @author Kamil Sowa
 */
public class ChromosomeDAO {
    private Integer id;
    private Matrix matrix;
    private Random rand=new Random();
    private List<CLass> listOfClasses;
    private int rateOfChromosome=0;
    private float probabilityOfExistance;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public float getProbabilityOfExistance() {
        return probabilityOfExistance;
    }

    public void setProbabilityOfExistance(float probabilityOfExistance) {
        this.probabilityOfExistance = probabilityOfExistance;
    }

    public int getRateOfChromosome() {
        return rateOfChromosome;
    }

    public void setRateOfChromosome(int rateOfChromosome) {
        this.rateOfChromosome = rateOfChromosome;
    }

    public Random getRand() {
        return rand;
    }

    public void setRand(Random rand) {
        this.rand = rand;
    }

    public List<CLass> getListOfClasses() {
        return listOfClasses;
    }

    public void setListOfClasses(List<CLass> listOfClasses) {
        this.listOfClasses = listOfClasses;
    }

    public Integer getNumberOfTeachers() {
        return numberOfTeachers;
    }

    public void setNumberOfTeachers(Integer numberOfTeachers) {
        this.numberOfTeachers = numberOfTeachers;
    }

    public Integer getNumberofCLasses() {
        return numberofCLasses;
    }

    public void setNumberofCLasses(Integer numberofCLasses) {
        this.numberofCLasses = numberofCLasses;
    }

    public DAO getDao() {
        return dao;
    }

    public void setDao(DAO dao) {
        this.dao = dao;
    }
    private Integer numberOfTeachers;
    private Integer numberOfHours;
    private Integer numberofCLasses;
    private DAO dao;

    public ChromosomeDAO(Integer id ,Integer numberofTeachers, Integer numberOfHours, Integer numberOfClasses) {
        this.numberOfTeachers = numberofTeachers;
        this.numberOfHours = numberOfHours;
        this.numberofCLasses = numberOfClasses;
        this.id=id;
       
        DAO dao=new DAO();
        this.listOfClasses=dao.getClasses();
        matrix=new Matrix(numberOfClasses,numberOfHours);
    }
    public ChromosomeDAO(ChromosomeDAO chromosom) {
        this.numberOfTeachers = chromosom.getNumberOfTeachers();
        this.numberOfHours = chromosom.getNumberOfHours();
        this.numberofCLasses = chromosom.getNumberofCLasses();
        this.id=chromosom.getId();
       
        DAO dao=new DAO();
        this.listOfClasses=dao.getClasses();
        matrix=new Matrix(chromosom.getMatrix(),this.numberofCLasses,this.numberOfHours);
    }
  
    
    void createRandomMatrix(){
        
        int randHour;
        
        for (int cl=0;cl<listOfClasses.size();cl++){
            
            for (int i=0;i<listOfClasses.get(cl).getListOfTaughtClasses().size();i++){
                
                
                int cLass=listOfClasses.get(cl).getListOfTaughtClasses().get(i);
                randHour=rand.nextInt(matrix.getColumns());
                 findTeacherAtHour(cLass, randHour);
                while ( matrix.getField(cl, randHour) != null  ||  findTeacherAtHour(cLass, randHour))  {
                    randHour=rand.nextInt(matrix.getColumns());
                }
                
                matrix.setField(cLass, cl, randHour);
                
            }
        }
        int cos=2;
       
    }
    private boolean findTeacherAtHour(int numberOfClass,int hour){
        
        for (int cl=0;cl<numberofCLasses;cl++){
            if (matrix.getField(cl, hour)!=null && matrix.getField(cl, hour)==numberOfClass)
                return true;
        }
        return false;
    }
            

    void printChromosome() {
     
        System.out.println("chromosome id: "+id);
        matrix.matrixPrint();
        printRating();
    }
    void printTeachersClasses(){
        
        if (!listOfClasses.isEmpty()) {
            for (CLass t: listOfClasses){
                t.print();
            }
        }
        System.out.println();
        
        
        
    }
    
    public void rateDay(int CLassNumber)
    {
    
        // 2. czy sa przerwy miedzy zajeciami    -1p.
        // 3. jak dluga przerwa miedzy zajeciami -2p.
        int counter=0;
        boolean flag1=false;
        boolean flagaPoPrzedmiocieNull=false;
        int numberOfNulls=0;
       for(int i=0;i<5;i++)
       {
            if (matrix.getField(CLassNumber, i) == null) {
            } else {
                flag1 = true;
            }
            if (flag1) {
                if (matrix.getField(CLassNumber, i) == null) {
                    flagaPoPrzedmiocieNull = true;
                    flag1 = false;
                }
            }
            if (flagaPoPrzedmiocieNull) {
                if (matrix.getField(CLassNumber, i) != null) {
                    counter+=numberOfNulls;
                    numberOfNulls=0;
                    flagaPoPrzedmiocieNull = false;
                }
                numberOfNulls++;
            }
        }
        listOfClasses.get(CLassNumber).setRate(counter);
        rateOfChromosome+=counter;
    }
    
    
    
   public void rateChromosome()
   {
       for (CLass klasa:listOfClasses)
       {
           rateDay(klasa.getId());
       }
   }
   public void setCLassPlan(int classId,Integer[] classes)
   {
       for (int i=0;i<5;i++)
       {
           matrix.setField(classes[i] , classId, i);
       }
   }
    

    public Matrix getMatrix() {
        return matrix;
    }

    public void setMatrix(Matrix matrix) {
        this.matrix = matrix;
    }

    public Integer getNumberOfClasses() {
        return numberOfTeachers;
    }

    public void setNumberOfClasses(Integer numberOfClasses) {
        this.numberOfTeachers = numberOfClasses;
    }

    public Integer getNumberofTeachers() {
        return numberofCLasses;
    }

    public void setNumberofTeachers(Integer numberofTeachers) {
        this.numberofCLasses = numberofTeachers;
    }

    public Integer getNumberOfHours() {
        return matrix.getColumns();
    }

    public void setNumberOfHours(Integer numberOfHours) {
        this.numberOfHours = numberOfHours;
    }
    public void printRatings()
    {
        for (int i=0;i<4;i++)
        {
            System.out.println(i+": "+ listOfClasses.get(i).getRate() );
        }
    }
    public void printRating()
    {
        System.out.println(ANSI_RED+ "Rating: "+this.rateOfChromosome +ANSI_RESET);
    }
    
}
