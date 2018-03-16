/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nanowobiai;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import static nanowobiai.ConstansInterface.NUMBER_OF_CHROMOSOMES;
import static nanowobiai.ConstansInterface.NUMBER_OF_CLASSES;
import static nanowobiai.ConstansInterface.NUMBER_OF_HOURS;
import static nanowobiai.ConstansInterface.NUMBER_OF_TEACHERS;
/**
 *
 * @author Kamil Sowa
 */
public class Populacja {
    Float[] RuletteWheel;
     private List<ChromosomeDAO> chromosomy=new ArrayList<ChromosomeDAO>();
     
     
    public Populacja(){
        RuletteWheel=new Float[NUMBER_OF_CHROMOSOMES];
    }
   
    {
        for (int i=0;i<NUMBER_OF_CHROMOSOMES;i++)
        {
            ChromosomeDAO chromosom=new ChromosomeDAO(i,NUMBER_OF_TEACHERS , NUMBER_OF_HOURS, NUMBER_OF_CLASSES);
            chromosom.createRandomMatrix();
            chromosomy.add(chromosom);
        }
    }
    public void symulacja()
    {
        ChromosomeDAO chromosom=new ChromosomeDAO(1,NUMBER_OF_TEACHERS , NUMBER_OF_HOURS, NUMBER_OF_CLASSES);

           chromosom.printTeachersClasses();

           for (ChromosomeDAO ch: chromosomy)
           {
            ch.rateChromosome();
            ch.printChromosome();
           }
           for (int i=0;i<4;i++){
              rateAllChromosome();
              if (setAllChromosomeProbabiltyOfExistance())   { 
                     createNewPopulationBasedOnRouletteWheel();
                }
           }
            
          for (ChromosomeDAO ch: chromosomy)
           {
            ch.rateChromosome();
            ch.printChromosome();
           }
            
    }
    public void crossingOver(ChromosomeDAO chr1, ChromosomeDAO chr2)
    {
        ChromosomeDAO chrNowy=new ChromosomeDAO(1,4, 5, 4);
        for (int i=0;i<chr1.getNumberOfClasses();i++)
        {
            if (chr1.getListOfClasses().get(i).getRate()<chr2.getListOfClasses().get(i).getRate())
            {
                chrNowy.setCLassPlan(i, chr1.getMatrix().getSingleRow(i));
            }
            else
            {
                 chrNowy.setCLassPlan(i, chr2.getMatrix().getSingleRow(i));
            }
        }
       /* chrNowy.printChromosome();
        chrNowy.funkcjaOceny();
        chrNowy.printRatings();
*/
    }
    public void printKoloRuletki()
    {
        int i=0;
        System.out.println("id " +"rate "+ "prob "+" rulet");
        for(ChromosomeDAO chr: chromosomy)
        {
            
            System.out.println(chr.getId()+"  " + chr.getRateOfChromosome()+"  "+ String.format("%.2f", chr.getProbabilityOfExistance())+"   "+String.format("%.2f", RuletteWheel[i]));
            i++;
        }
    }
    public boolean setAllChromosomeProbabiltyOfExistance()
    {
        //(Wartość najgorszego osobnika-Wartość danego osobnika+1)/(suma wartości wszystkich osobników+1)
        ChromosomeDAO worstChromosome;
        worstChromosome=getTheWorstChromosome();
        int sumOfRateOfAllChromosome=getTheSumOfRateOfAllChromosome();
        float probability;
        float sumProbabilty=0;
        float sumProbabiltyAfter=0;
        for(ChromosomeDAO chr: chromosomy)
        {
           probability=(float) (worstChromosome.getRateOfChromosome()-chr.getRateOfChromosome()+1)/(sumOfRateOfAllChromosome);
           //probability=(float) chr.getRateOfChromosome()/sumOfRateOfAllChromosome;
           sumProbabilty+=probability;
           chr.setProbabilityOfExistance(probability);
        }
        System.out.println("sumProb: "+sumProbabilty);
        
        for(ChromosomeDAO chr: chromosomy)
        {
           chr.setProbabilityOfExistance(chr.getProbabilityOfExistance()/sumProbabilty);
            //System.out.println(chr.getProbabilityOfExistance());
            sumProbabiltyAfter+=chr.getProbabilityOfExistance();
        }
        System.out.println("sumProbabiltyAfter: "+sumProbabiltyAfter);
        
        
        if (sumProbabiltyAfter>0.98 && sumProbabiltyAfter<1.02)
            return true;
        else
            return false;
    }

    private int getTheSumOfRateOfAllChromosome() {
        int sum=0;
        for(ChromosomeDAO chr: chromosomy)
        {
            sum+=chr.getRateOfChromosome();
        }
        return sum;
    }

    private ChromosomeDAO getTheWorstChromosome() {
        int worstRate=0;
        ChromosomeDAO worstChromosome = null;
        for(ChromosomeDAO chr: chromosomy)
        {
            if (worstRate< chr.getRateOfChromosome())
            { 
              
                worstRate=chr.getRateOfChromosome();
               worstChromosome=chr;
            }
        }
        return worstChromosome;
        
    }
    
    public void createNewRuletteWheel()
    {
       
        Float sumOfProbability = 0.0f;
        System.out.println();
        System.out.println();
        for (int i=0;i<NUMBER_OF_CHROMOSOMES;i++)
        {
           RuletteWheel[i]=chromosomy.get(i).getProbabilityOfExistance()+sumOfProbability;
           sumOfProbability+= chromosomy.get(i).getProbabilityOfExistance();
        }
    }
    /**
     * Creates new population
     */
    public List<ChromosomeDAO> createNewPopulationBasedOnRouletteWheel()
    {
        createNewRuletteWheel();
        
        List<ChromosomeDAO> chromosomyNewPopulation=new ArrayList<ChromosomeDAO>();
        Random rand = new Random();
        Float randomNumber;
        for (int i=0;i<NUMBER_OF_CHROMOSOMES;i++)
        {
           
            randomNumber=rand.nextFloat();
            System.out.println("randomNumber "+randomNumber);
            for (int j=0;j<RuletteWheel.length;j++){
                if (randomNumber<RuletteWheel[j])
                {
                   // chromosomyNewPopulation.add(chromosomy.get(j));
                    chromosomyNewPopulation.add(new ChromosomeDAO(chromosomy.get(j)));
                    break;
                }
            }
        }
        System.out.println("choosen");
        for (ChromosomeDAO chr:chromosomyNewPopulation){
            System.out.println(chr.getProbabilityOfExistance()+ "  "+chr.getId());
        }
        System.out.println("");
        
        printKoloRuletki();
        chromosomy=chromosomyNewPopulation;
        return chromosomyNewPopulation;
    }
    public void rateAllChromosome(){
        for (ChromosomeDAO chr:chromosomy){
            chr.rateChromosome();
        }
    }
            
}
