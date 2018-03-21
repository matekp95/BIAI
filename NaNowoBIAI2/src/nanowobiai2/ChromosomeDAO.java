/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nanowobiai2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import static nanowobiai2.ConstansInterface.ANSI_RED;
import static nanowobiai2.ConstansInterface.ANSI_RESET;

/**
 *
 * @author Kamil Sowa
 */
public class ChromosomeDAO {

    private Integer id;
    private Matrix matrix;
    private Random rand = new Random();
    private List<CLass> listOfClasses;
    private int rateOfChromosome = 0;
    private float probabilityOfExistance;
    private Integer numberOfTeachers;
    private Integer numberOfHours;
    private Integer numberOfCLasses;
    private Integer numberOfDays;
    private Integer numberOfColumnsInChromosome;
    private DAO dao;

    public Integer getNumberOfDays() {
        return numberOfDays;
    }

    public void setNumberOfDays(Integer numberOfDays) {
        this.numberOfDays = numberOfDays;
    }

    public Integer getNumberOfColumnsInChromosome() {
        return numberOfColumnsInChromosome;
    }

    public void setNumberOfColumnsInChromosome(Integer numberOfColumnsInChromosome) {
        this.numberOfColumnsInChromosome = numberOfColumnsInChromosome;
    }

    
    public Integer getNumberOfTeachers() {
        return numberOfTeachers;
    }

    public void setNumberOfTeachers(Integer numberOfTeachers) {
        this.numberOfTeachers = numberOfTeachers;
    }

    public Integer getNumberOfCLasses() {
        return numberOfCLasses;
    }

    public void setNumberofCLasses(Integer numberOfCLasses) {
        this.numberOfCLasses = numberOfCLasses;
    }

    
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


    public DAO getDao() {
        return dao;
    }

    public void setDao(DAO dao) {
        this.dao = dao;
    }
  

    public ChromosomeDAO(Integer id, Integer numberofTeachers, Integer numberOfHours, Integer numberOfClasses,Integer numberOfDays) {
        this.numberOfTeachers = numberofTeachers;
        this.numberOfHours = numberOfHours;
        this.numberOfCLasses = numberOfClasses;
        this.numberOfDays=numberOfDays;
        this.numberOfColumnsInChromosome=numberOfDays*numberOfHours;
        this.id = id;

        DAO dao = new DAO();
        this.listOfClasses = dao.getClasses();
        matrix = new Matrix(numberOfClasses, numberOfColumnsInChromosome);
    }

    public ChromosomeDAO(ChromosomeDAO chromosom) {
        this.numberOfTeachers = chromosom.getNumberOfTeachers();
        this.numberOfHours = chromosom.getNumberOfHours();
        this.numberOfCLasses = chromosom.getNumberOfCLasses();
        this.numberOfDays=chromosom.getNumberOfDays();
        this.numberOfColumnsInChromosome=chromosom.getNumberOfColumnsInChromosome();
        this.id = chromosom.getId();

        DAO dao = new DAO();
        this.listOfClasses = dao.getClasses();
        matrix = new Matrix(chromosom.getMatrix(), this.numberOfCLasses, this.numberOfColumnsInChromosome);
    }

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

    private boolean findTeacherAtHour(int numberOfTeacher, int hour) {

        for (int cl = 0; cl < numberOfCLasses; cl++) {
            if (matrix.getField(cl, hour) != null && matrix.getField(cl, hour) == numberOfTeacher) {
                return true;
            }
        }
        return false;
    }

    public ChromosomeDAO repairFunction() {
        for (int i = 0; i < this.getNumberOfCLasses(); i++) {
            for (int j = 0; j < this.numberOfColumnsInChromosome; j++) {
                if (matrix.getField(i, j) != null && findTeacherAtHour(matrix.getField(i, j), j)) {
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

    public ChromosomeDAO mutationFunction()
    {
        Random rand = new Random();
        int switchFunc = rand.nextInt(numberOfColumnsInChromosome); // losujemy od 0 do 4
        int whichColumn;
        System.out.println("MUTACJA NUMER " + switchFunc);
        this.printChromosome();
        
        for(int i=0;i<switchFunc+1;i++)
        {
        whichColumn=rand.nextInt(numberOfColumnsInChromosome-1); // losowanie 0-3, bo zmieniac sie bedzie i-ta oraz i+1 kolumna, wiec jakby
        // wypadło na ostatnia kolumne to lipa bedzie.
        switchColumn(whichColumn,whichColumn+1);
        }

        System.out.println("PO MUTACJI ");
        this.printChromosome();
        return this;
    }
      
    private void switchColumn(int firstColumn, int secondColumn) {
        Integer tempValue = 0;
        
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
    
    public void printChromosome() {

        System.out.println("chromosome id: " + id);
        matrix.matrixPrint(numberOfHours);
        printRating();
    }

    void printTeachersClasses() {

        if (!listOfClasses.isEmpty()) {
            for (CLass t : listOfClasses) {
                t.print();
            }
        }
        System.out.println();

    }

    public void rateDay(int CLassNumber, int startingDayPoint) {

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
    }

    public void rateChromosome() {
        rateOfChromosome=0;
        for (CLass klasa : listOfClasses) {
            for (int i=0;i<numberOfDays;i++)
            {
                rateDay(klasa.getId(),i*numberOfHours);
            }
            
        }
    }

    public void setCLassPlan(int classId, Integer[] classes) {
        for (int i = 0; i < numberOfColumnsInChromosome; i++) {
            matrix.setField(classes[i], classId, i);
        }
    }

    public Matrix getMatrix() {
        return matrix;
    }

    public void setMatrix(Matrix matrix) {
        this.matrix = matrix;
    }



    public Integer getNumberOfHours() {
        return numberOfHours;
    }

    public void setNumberOfHours(Integer numberOfHours) {
        this.numberOfHours = numberOfHours;
    }

    public void printRatings() {
        for (int i = 0; i < 4; i++) {
            System.out.println(i + ": " + listOfClasses.get(i).getRate());
        }
    }

    public void printRating() {
        System.out.println(ANSI_RED + "Rating: " + this.rateOfChromosome + ANSI_RESET);
    }


}
