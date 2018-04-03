
import chromosomeClasses.ChromosomeDAO;
import static chromosomeClasses.ConstansInterface.NUMBER_OF_CLASSES;
import static chromosomeClasses.ConstansInterface.NUMBER_OF_DAYS;
import static chromosomeClasses.ConstansInterface.NUMBER_OF_HOURS;
import static chromosomeClasses.ConstansInterface.NUMBER_OF_TEACHERS;
import org.junit.Test;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Kamil Sowa
 */
public class testChromoseDAO {
    
    /**
     *
     */
    @Test
    public void testRate()
    {
        ChromosomeDAO ch=new ChromosomeDAO(1,NUMBER_OF_TEACHERS , NUMBER_OF_HOURS, NUMBER_OF_CLASSES,NUMBER_OF_DAYS);

        //chromosom.printTeachersClasses();
         ch.createRandomMatrix();
         ch.rateChromosome();
         ch.printChromosome();
     
    }
}
