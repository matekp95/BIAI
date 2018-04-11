/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chromosomeClasses;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import static chromosomeClasses.ConstansInterface.ANSI_RED;
import static chromosomeClasses.ConstansInterface.ANSI_RESET;

/**
 * Class that represents chromosome. Rows represents classes. Columns represents day and hour
 * @author Kamil Sowa
 */
public class ChromosomeDAO {

    /**
     * Id of chromosome
     */
    private Integer id;
     /**
     * Rows represents classes. Columns represents day and hour. Field in matrix holds letter coresponding to teacher
     */
    private Matrix matrix;
    /**
     * Randomizer
     */
    private Random rand = new Random();
    /**
     * List of Classes
     */
    private List<CLass> listOfClasses;
    /**
     * Rate of Chromosome
     */
    private int rateOfChromosome = 0;
    /**
     * Probability that this chromosome will be alive after selection
     */
    private float probabilityOfExistance;
    /**
     * Number of teachers
     */
    private Integer numberOfTeachers;
     /**
     * Number of hours
     */
    private Integer numberOfHours;
    /**
     * Number of classes
     */
    private Integer numberOfCLasses;
    /**
     * Number of days
     */
    private Integer numberOfDays;
    /**
     * Number of columns i chromosome
     */
    private Integer numberOfColumnsInChromosome;
    /**
     * data acces class 
     */
    private DAO dao;
    /**
     * rate of rows
     */
    private Integer[] tableOfRateRows;

    /**
     * Getter for tableOfRateRows
     * @return table of rates
     */
    public Integer[] getTableOfRateRows() {
        return tableOfRateRows;
    }

    /**
     * Getter for tableOfRateRows
     * @param i number of row
     * @return rate of row (class)
     */
    public Integer getTableOfRateRows(Integer i) {
        return tableOfRateRows[i];
    }

    /**
     *Setter for tableOfRateRows
     * @param tableOfRateRows rate of rows
     */
    public void setTableOfRateRows(Integer[] tableOfRateRows) {
        this.tableOfRateRows = tableOfRateRows;
    }

    /**
     * 
     * Getter of number of days
     * @return  number of days
     */
    public Integer getNumberOfDays() {
        return numberOfDays;
    }

    /**
     * Setter of number of days
     * @param numberOfDays number of days
     */
    public void setNumberOfDays(Integer numberOfDays) {
        this.numberOfDays = numberOfDays;
    }

    /**
     * Getter of number of columns
     * @return number of columns
     */ 
    public Integer getNumberOfColumnsInChromosome() {
        return numberOfColumnsInChromosome;
    }

    /**
     * Setter of number of columns
     * @param numberOfColumnsInChromosome number of columns
     */
    public void setNumberOfColumnsInChromosome(Integer numberOfColumnsInChromosome) {
        this.numberOfColumnsInChromosome = numberOfColumnsInChromosome;
    }

    /**
     * Getter of number of teachers
     * @return number of teachers
     */
    public Integer getNumberOfTeachers() {
        return numberOfTeachers;
    }

    /**
     *Setter of number of teachers
     * @param numberOfTeachers number of teachers
     */
    public void setNumberOfTeachers(Integer numberOfTeachers) {
        this.numberOfTeachers = numberOfTeachers;
    }

    /**
     * Getter of number of classe
     * @return number of classe
     */
    public Integer getNumberOfCLasses() {
        return numberOfCLasses;
    }

    /**
     *Setter of number of classe
     * @param numberOfCLasses number of classe
     */
    public void setNumberofCLasses(Integer numberOfCLasses) {
        this.numberOfCLasses = numberOfCLasses;
    }

    /**
     * Getter of id
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * Setter of id
     * @param id id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * gette for prob of existance
     * @return prob of existance
     */
    public float getProbabilityOfExistance() {
        return probabilityOfExistance;
    }

    /**
     *Setter for prob of existance
     * @param probabilityOfExistance prob of existance
     */
    public void setProbabilityOfExistance(float probabilityOfExistance) {
        this.probabilityOfExistance = probabilityOfExistance;
    }

    /**
     * Getter for rate
     * @return  rate
     */
    public int getRateOfChromosome() {
        return rateOfChromosome;
    }

    /**
     *  Setter for rate
     * @param rateOfChromosome rate
     */
    public void setRateOfChromosome(int rateOfChromosome) {
        this.rateOfChromosome = rateOfChromosome;
    }

    /**
     * Get random object
     * @return
     */
    public Random getRand() {
        return rand;
    }

    /**
     * Set random object
     * @param rand
     */
    public void setRand(Random rand) {
        this.rand = rand;
    }

    /**
     * Getter for list of classes
     * @return  list of classes
     */
    public List<CLass> getListOfClasses() {
        return listOfClasses;
    }

    /**
     * Setter for list of classes
     * @param listOfClasses  list of classes
     */
    public void setListOfClasses(List<CLass> listOfClasses) {
        this.listOfClasses = listOfClasses;
    }

    /**
     * Getter for dao
     * @return v
     */
    public DAO getDao() {
        return dao;
    }

    /**
     *  Setter for dao
     * @param dao dao
     */
    public void setDao(DAO dao) {
        this.dao = dao;
    }
  
    /**
     *Constructor
     * @param id id 
     * @param numberofTeachers numb od teachers
     * @param numberOfHours numb of hours
     * @param numberOfClasses numb of classe
     * @param numberOfDays numb of days
     */
    public ChromosomeDAO(Integer id, Integer numberofTeachers, Integer numberOfHours, Integer numberOfClasses,Integer numberOfDays) {
        this.numberOfTeachers = numberofTeachers;
        this.numberOfHours = numberOfHours;
        this.numberOfCLasses = numberOfClasses;
        this.numberOfDays=numberOfDays;
        this.numberOfColumnsInChromosome=numberOfDays*numberOfHours;
        this.id = id;
        tableOfRateRows=new Integer[numberOfCLasses];

        DAO dao = new DAO();
        this.listOfClasses = dao.getClasses();
        matrix = new Matrix(numberOfClasses, numberOfColumnsInChromosome);
    }

    /**
     *Constructor copy
     * @param chromosom chromosom
     */
    public ChromosomeDAO(ChromosomeDAO chromosom) {
        this.numberOfTeachers = chromosom.getNumberOfTeachers();
        this.numberOfHours = chromosom.getNumberOfHours();
        this.numberOfCLasses = chromosom.getNumberOfCLasses();
        this.numberOfDays=chromosom.getNumberOfDays();
        this.numberOfColumnsInChromosome=chromosom.getNumberOfColumnsInChromosome();
        this.id = chromosom.getId();
        tableOfRateRows=new Integer[numberOfCLasses];

        DAO dao = new DAO();
        this.listOfClasses = dao.getClasses();
        matrix = new Matrix(chromosom.getMatrix(), this.numberOfCLasses, this.numberOfColumnsInChromosome);
    }

    /**
     * Creates random matrix 
     */
    public void createRandomMatrix() {

        int randHour;
        // int counter;
        for (int cl = 0; cl < listOfClasses.size(); cl++) {

            for (int i = 0; i < listOfClasses.get(cl).getListOfTaughtClasses().size(); i++) {

                boolean flag = true;
                int counter = 0;

                int teacher = listOfClasses.get(cl).getListOfTaughtClasses().get(i);

                randHour = rand.nextInt(matrix.getColumns());
                findTeacherAtHour(teacher, randHour);

                while (matrix.getField(cl, randHour) != null || findTeacherAtHour(teacher, randHour)) {
                    counter++;

                    randHour = rand.nextInt(matrix.getColumns());
                    if (counter == 10) {
                        flag = false;
                        for (int k = 0; k < matrix.getColumns(); k++) {
                            matrix.setField(null, cl, k);

                        }

                        counter = 0;
                    }
                }
                if (!flag) {
                    int a = 10;
                    flag = true;
                    i = -1;
                    continue;
                } else {
                    matrix.setField(teacher, cl, randHour);
                }

            }
        }
        int cos = 2;

    }
    /**
     * Check if teacher already has lessons that day that hour 
     * @param numberOfTeacher number of teacher
     * @param hour number of hour
     * @return 
     */
    private boolean findTeacherAtHour(int numberOfTeacher, int hour) {

        for (int cl = 0; cl < numberOfCLasses; cl++) {
            if (matrix.getField(cl, hour) != null && matrix.getField(cl, hour) == numberOfTeacher) {
                return true;
            }
        }
        return false;
    }
    /**
     * Check if teacher already has lessons that day that hour
     * @param numberOfTeacher number of teacher
     * @param hour number of hour
     * @param startClass class that is being checked
     * @return 
     */
     private boolean findTeacherAtHour(int numberOfTeacher, int hour, int startClass) {

        for (int cl = 0; cl < numberOfCLasses; cl++) {
            if (cl==startClass)
            {
                
            }
            else if (matrix.getField(cl, hour) != null && matrix.getField(cl, hour) == numberOfTeacher) {
                return true;
            }
        }
        return false;
    }

    /**
     * Reapairs chromosome. If there is teacher who has two or more lessons in one day. Function fixes chromosome
     * @return fixed chromosome
     */
    public ChromosomeDAO repairFunction() {
        for (int i = 0; i < this.getNumberOfCLasses(); i++) {
            for (int j = 0; j < this.numberOfColumnsInChromosome; j++) {
                if (matrix.getField(i, j) != null && findTeacherAtHour(matrix.getField(i, j), j,i)) {
                    // jezeli znalazł powtórzenie w kolumnie, to zerujemy cały wiersz
                    for (int s = 0; s < matrix.getColumns(); s++) {
                        matrix.setField(null, i, s);
                    }
                    for (int z = 0; z < listOfClasses.get(i).getListOfTaughtClasses().size(); z++) {

                        boolean flag = true;
                        int counter = 0;

                        int teacher = listOfClasses.get(i).getListOfTaughtClasses().get(z);

                        int randHour = rand.nextInt(matrix.getColumns());
                        findTeacherAtHour(teacher, randHour);

                        while (matrix.getField(i, randHour) != null || findTeacherAtHour(teacher, randHour)) {
                            counter++;

                            randHour = rand.nextInt(matrix.getColumns());
                            if (counter == 10) {
                                flag = false;
                                for (int k = 0; k < matrix.getColumns(); k++) {
                                    matrix.setField(null, i, k);

                                }

                                counter = 0;
                            }
                        }
                        if (!flag) {
                            int a = 10;
                            flag = true;
                            z = -1;
                            continue;
                        } else {
                            matrix.setField(teacher, i, randHour);
                        }

                    }
                    break;

                }

            }

        }

        return this;
    }
    
    /**
     * Mutate chromosome
     * @return mutated chromosome
     */
    public ChromosomeDAO mutateChromosome()
    {
      //  System.out.println("Przed mutacją");
      //  this.printChromosome();
        for (int i=0;i<numberOfDays;i++)
            {
                mutateDay(i*numberOfHours,numberOfHours);
            }
       // System.out.println("Po mutacji");
      //  this.rateChromosome();
       // this.printChromosome();
        return this;
    }

    /**
     * Mutate day
     * @param startingDayPoint start of day
     * @param endDayPoint end of day
     */
    public void mutateDay(int startingDayPoint,int endDayPoint)
    {
        Random rand = new Random();
        int switchFunc = rand.nextInt(numberOfHours); // losujemy ile kolumn chcemy zmienic
        int whichColumn,randomColumn;

        for(int i=0;i<switchFunc+1;i++)
        {
            whichColumn=rand.nextInt(numberOfHours-1)+startingDayPoint; // losowanie 0-3, bo zmieniac sie bedzie i-ta oraz i+1 kolumna, wiec jakby
            // wypadło na ostatnia kolumne to lipa bedzie.
            randomColumn=rand.nextInt(numberOfHours-1)+startingDayPoint;
            if(whichColumn == randomColumn)randomColumn+=1;
            switchColumn(whichColumn,randomColumn);
        }
    }
     /**
      * 
      * @param firstColumn
      * @param secondColumn 
      */
    private void switchColumn(int firstColumn, int secondColumn) {
        Integer tempValue = 0;
       // System.out.println("Kolumna: "+firstColumn+" z: "+secondColumn);
        for (int i = 0; i < this.numberOfCLasses; i++) {            
            for (int j = 0; j < this.numberOfColumnsInChromosome; j++) {      
                if(j == firstColumn)
                {
                    tempValue=this.matrix.getField(i, secondColumn);
                    this.matrix.setField(this.matrix.getField(i, firstColumn), i, secondColumn);
                    this.matrix.setField(tempValue, i, firstColumn);
                }
            }
        }
    }
    
    /**
     *Prinst chromosome
     */
    public void printChromosome() {

       // System.out.println("chromosome id: " + id);
     //   matrix.matrixPrint(numberOfHours);
        printRating();
    }
    /**
     * prints teachers tought by class
     */
    void printTeachersClasses() {
        int i=0;
        if (!listOfClasses.isEmpty()) {
            for (CLass t : listOfClasses) {
               System.out.print("Klasa "+i+++" ma zajęcia z nauczycielami");
               t.print();
               System.out.println();
            }
        }
        

    }

    /**
     * Rate day
     * @param CLassNumber number of class
     * @param startingDayPoint column that starts a day
     * @return
     */
    public int rateDay(int CLassNumber, int startingDayPoint) {

        // 2. czy sa przerwy miedzy zajeciami    -1p.
        // 3. jak dluga przerwa miedzy zajeciami -2p.
        int counter = 0;
        boolean flag1 = false;
        boolean flagaPoPrzedmiocieNull = false;
        int numberOfNulls = 0;
        for (int i = startingDayPoint; i < startingDayPoint+numberOfHours; i++) {
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
                    counter += numberOfNulls;
                    numberOfNulls = 0;
                    flagaPoPrzedmiocieNull = false;
                }
                else
                    numberOfNulls++;
            }
        }
        listOfClasses.get(CLassNumber).setRate(counter);
        rateOfChromosome += counter;
        return counter;
    }

    /**
     *Rate all days in chromosome
     */
    public void rateChromosome() {
        rateOfChromosome=0;
        int j=0;
        int sumRateOfClass=0;
        for (CLass klasa : listOfClasses) {
            for (int i=0;i<numberOfDays;i++)
            {
               sumRateOfClass+= rateDay(klasa.getId(),i*numberOfHours);
            }
            tableOfRateRows[j]=sumRateOfClass;
            sumRateOfClass=0;
            j++;
        }
    }

    /**
     * sets plan of class
     * @param classId class id
     * @param classes list of classes
     */
    public void setCLassPlan(int classId, Integer[] classes) {
        for (int i = 0; i < numberOfColumnsInChromosome; i++) {
            matrix.setField(classes[i], classId, i);
        }
    }

    /**
     * sets plan of teacher for specific hour
     * @param hourId  hour id
     * @param classes classes
     */
    public void setHour(int hourId, Integer[] classes) {
        for (int i = 0; i < numberOfCLasses; i++) {
            matrix.setField(classes[i], i, hourId);
        }
    }

    /**
     *Getter for matrix
     * @return matrix in chromosome
     */
    public Matrix getMatrix() {
        return matrix;
    }

    /**
     *
     * Setter of matrix
     * @param matrix matrix
     */
    public void setMatrix(Matrix matrix) {
        this.matrix = matrix;
    }

    /**
     *Getter for number of hours
     * @return number of hours
     */
    public Integer getNumberOfHours() {
        return numberOfHours;
    }

    /**
     *Setter for number of hours
     * @param numberOfHours number of hours
     */
    public void setNumberOfHours(Integer numberOfHours) {
        this.numberOfHours = numberOfHours;
    }

    /**
     *prints rating of all chromosome
     */
    public void printRatings() {
        for (int i = 0; i < 4; i++) {
            System.out.println(i + ": " + listOfClasses.get(i).getRate());
        }
    }

    /**
     *prints rating
     */
    public void printRating() {
        System.out.println(ANSI_RED + "Rating: " + this.rateOfChromosome + ANSI_RESET);
    }


}
