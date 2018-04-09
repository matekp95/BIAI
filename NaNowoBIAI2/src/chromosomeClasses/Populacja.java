/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chromosomeClasses;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import static chromosomeClasses.ConstansInterface.NUMBER_OF_CHROMOSOMES;
import static chromosomeClasses.ConstansInterface.NUMBER_OF_CLASSES;
import static chromosomeClasses.ConstansInterface.NUMBER_OF_DAYS;
import static chromosomeClasses.ConstansInterface.NUMBER_OF_HOURS;
import static chromosomeClasses.ConstansInterface.NUMBER_OF_TEACHERS;
import static chromosomeClasses.ConstansInterface.NUMBER_OF_THREADS;
import java.util.Comparator;
import java.util.Optional;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.function.Predicate;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import viewCLasses.Plans;
/**
 * Class that represents all population. Holds all chromosomes
 * @author Kamil Sowa
 */
public class Populacja {
    /**
     * table of rullete wheels for every chromosoem
     */
    Float[] RuletteWheel;
    /**
     * lsit of all chromosome in population
     */
    private List<ChromosomeDAO> chromosomy=new ArrayList<ChromosomeDAO>();

    /**
     *Getter for rulette wheels
     * @return rulette wheels
     */
    public Float[] getRuletteWheel() {
        return RuletteWheel;
    }

    /**
     * Setter for rulette wheels
     * @param RuletteWheel rulette wheels
     */
    public void setRuletteWheel(Float[] RuletteWheel) {
        this.RuletteWheel = RuletteWheel;
    }

    /**
     *Getter for list of chromosome
     * @return list of chromosome
     */
    public List<ChromosomeDAO> getChromosomy() {
        return chromosomy;
    }

    /**
     *Setter for list of chromosome
     * @param chromosomy list of chromosome
     */
    public void setChromosomy(List<ChromosomeDAO> chromosomy) {
        this.chromosomy = chromosomy;
    }
         
    /**
     *Constructor
     */
    public Populacja(){
        RuletteWheel=new Float[NUMBER_OF_CHROMOSOMES];
    }
   
    {
        for (int i=0;i<NUMBER_OF_CHROMOSOMES;i++)
        {
            ChromosomeDAO chromosom=new ChromosomeDAO(i,NUMBER_OF_TEACHERS , NUMBER_OF_HOURS, NUMBER_OF_CLASSES, NUMBER_OF_DAYS);
            chromosom.createRandomMatrix();
            chromosomy.add(chromosom);
        }
    }

    /**
     *Simulate life of all chromosomes
     * @param NUMBER_OF_GENERATIONS number of generations
     */
    public void symulacja(int NUMBER_OF_GENERATIONS)
    {
        ChromosomeDAO chromosom=new ChromosomeDAO(1,NUMBER_OF_TEACHERS , NUMBER_OF_HOURS, NUMBER_OF_CLASSES,NUMBER_OF_DAYS);

        chromosom.printTeachersClasses();

        for (ChromosomeDAO ch: chromosomy)
        {
         ch.rateChromosome();
        // ch.printChromosome();
        }
        System.out.println("Srednia wartość funkcji oceny: "+sredniRating());
        System.out.println("Najlepsza funkcja oceny dla chromosomu: "+getBestChromosome().get(0).getId()+" ma funkcję oceny "+getBestChromosome().get(0).getRateOfChromosome());

        Random rand=new Random();
        for (int i=0;i<NUMBER_OF_GENERATIONS;i++){
           rateAllChromosome();
           if (setAllChromosomeProbabiltyOfExistance())   { 
                  createNewPopulationBasedOnRouletteWheel();
             }
           rateAllChromosome();
           crossingOverAllPopulation();
           mutateAllChromosome();
       }

       System.out.println();
       System.out.println("PO PRZEJSCIU GENERACJI ");
         for (ChromosomeDAO ch: chromosomy)
        {
         ch.rateChromosome();
        // ch.printChromosome();
        }
        System.out.println("Srednia wartość funkcji oceny po wszystkich generacjach:"+sredniRating());
        System.out.println("Najlepsza funkcja oceny dla chromosomu: "+getBestChromosome().get(0).getId()+" ma funkcję oceny "+getBestChromosome().get(0).getRateOfChromosome());
//         
    }
    
    /**
     *Gets list of chromosome with lowest rate
     * @return list of chromosome with lowets rate
     */
    public List<ChromosomeDAO> getBestChromosome  ()
    {
//        float minRate=chromosomy.get(0).getRateOfChromosome();
//        ArrayList<ChromosomeDAO> lista=new ArrayList<ChromosomeDAO>();
//        for (ChromosomeDAO chr : chromosomy) {
//            if (chr.getRateOfChromosome()<minRate)
//            {
//                minRate=chr.getRateOfChromosome();
//                lista.clear();
//                lista.add(chr);
//            }
//            else if (chr.getRateOfChromosome()==minRate)
//            {
//                lista.add(chr);
//            }
//            
//        }
//        return lista;
        Comparator<ChromosomeDAO> comp = (p1, p2) -> Integer.compare( p1.getRateOfChromosome(), p2.getRateOfChromosome());
        
        Optional<ChromosomeDAO> wyniki=
                chromosomy
                .stream()
                .min(comp);
        Predicate<? super ChromosomeDAO> predicate=(p)->p.getRateOfChromosome()==wyniki.get().getRateOfChromosome();
        
        
        return  chromosomy.stream().filter(predicate).collect(Collectors.toList());
        
    }
    /**
     * Mutate only 10% of population
     */
    public void mutateAllChromosome(){
        Random rand=new Random();
        for (ChromosomeDAO ch: chromosomy)
        {
            if (rand.nextInt(10)==0){
                ch.mutateChromosome();
            }
        }
    }

    /**
     *Calculates average rating of population
     * @return average rating of population
     */
    public float sredniRating()
    {
        ToIntFunction<? super ChromosomeDAO> mapper=q->q.getRateOfChromosome();
       
        return   (float) chromosomy
                .stream()
                .mapToInt(q->q.getRateOfChromosome())
                .average()
                .getAsDouble();
                
        
        
//        float suma=0.0f;
//        for (ChromosomeDAO ch: chromosomy)
//        {
//            suma+=ch.getRateOfChromosome();
//        }
//        return suma/NUMBER_OF_CHROMOSOMES;
        
        
                
        
    }
    
    /**
     *Crosses over all population
     */
    public void crossingOverAllPopulation()
    {
        // losujemy jaka czesc populacji krzyżować
        int counter = NUMBER_OF_CHROMOSOMES;
        List<ChromosomeDAO> tempArray = new ArrayList<>();
        List<ChromosomeDAO> tempArrayShow = new ArrayList<>();
        Random rand=new Random();
        int randomowa;
        int pointOfIntersection;
        
        List<Integer> listOfId = new ArrayList<>();
        for(int i=0;i<NUMBER_OF_CHROMOSOMES;i++)
        {
           listOfId.add(rand.nextInt(NUMBER_OF_CHROMOSOMES));
        }
        
        List<Callable<ArrayList<ChromosomeDAO>>> callables=new ArrayList<Callable<ArrayList<ChromosomeDAO>>>();
        
            ExecutorService executorService = Executors.newFixedThreadPool(NUMBER_OF_THREADS);
        for(int i=0;i<NUMBER_OF_CHROMOSOMES;i+=2){
            
            
            ChromosomeDAO chr1 = chromosomy.get(listOfId.get(i));
            ChromosomeDAO chr2 = chromosomy.get(listOfId.get(i+1));
            randomowa = rand.nextInt(100);
            pointOfIntersection=rand.nextInt(NUMBER_OF_CLASSES-1)+1;
            
            //tempArrayShow.addAll(crossingOver(chr1,chr2,chr1.getId(),chr2.getId(),pointOfIntersection));
            callables.add(callable(chr1,chr2,chr1.getId(),chr2.getId(),pointOfIntersection));
            
            
            if (i%NUMBER_OF_THREADS==NUMBER_OF_THREADS*2-2 || i==(NUMBER_OF_CHROMOSOMES-2)){
                try {
                    List<Future<ArrayList<ChromosomeDAO>>> futureList= executorService.invokeAll(callables);
                    for (Future<ArrayList<ChromosomeDAO>> future : futureList) {
                        tempArray.addAll(future.get());
                    }
                    callables.clear();
                    
                } catch (InterruptedException ex) {
                    Logger.getLogger(Populacja.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ExecutionException ex) {
                    Logger.getLogger(Populacja.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
           // tempArray.addAll(tempArrayShow);

            //tempArrayShow.clear();

        }
        executorService.shutdown();
        chromosomy.clear();

        chromosomy.addAll(tempArray);
        
        // sprawdzenie powtórzeń
        repairingFunction();
        
        
        for (int i = 0; i < NUMBER_OF_CHROMOSOMES; i++) {
            chromosomy.get(i).setId(i);
        }
        
    }
    
    /**
     *Rapairs all chromosomes in population
     */
    public void repairingFunction()
    {
        chromosomy.forEach((ch) -> {
            ch.repairFunction();
        });
    }

    /**
     *Crossing-over between two chromosomes
     * @param chr1 first chromosome
     * @param chr2 second chromosome
     * @param id1 id of first new
     * @param id2 id of second new
     * @param rowCountFromParent1 line of intersection
     * @return list of two new chromosomes
     */
    public ArrayList<ChromosomeDAO> crossingOver(ChromosomeDAO chr1, ChromosomeDAO chr2, Integer id1, Integer id2, Integer rowCountFromParent1)
    {
        ChromosomeDAO chrNowy = new ChromosomeDAO(id1,NUMBER_OF_TEACHERS , NUMBER_OF_HOURS, NUMBER_OF_CLASSES,NUMBER_OF_DAYS);
        ChromosomeDAO chrNowy2 = new ChromosomeDAO(id2,NUMBER_OF_TEACHERS , NUMBER_OF_HOURS, NUMBER_OF_CLASSES,NUMBER_OF_DAYS);
    
        
        for (int i=0;i<rowCountFromParent1;i++)
        {
            chrNowy.setCLassPlan(i, chr1.getMatrix().getSingleRow(i));
            chrNowy2.setCLassPlan(i, chr2.getMatrix().getSingleRow(i));
        }
        for(int i=rowCountFromParent1;i<chr1.getNumberOfCLasses();i++)
        {
            chrNowy.setCLassPlan(i, chr2.getMatrix().getSingleRow(i));
            chrNowy2.setCLassPlan(i, chr1.getMatrix().getSingleRow(i));
        }
        
        ArrayList<ChromosomeDAO> temp = new ArrayList<ChromosomeDAO>();
        temp.add(chrNowy);
        temp.add(chrNowy2);
      
    
      
      
        return temp;
    }

    /**
     * prints rulette wheel
     */
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

    /**
     * Sets chromosomes probability of existance
     * @return if probability is okej true
     */
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
           sumProbabilty+=probability;
           chr.setProbabilityOfExistance(probability);
        }
        //System.out.println("sumProb: "+sumProbabilty);
        
        for(ChromosomeDAO chr: chromosomy)
        {
           chr.setProbabilityOfExistance(chr.getProbabilityOfExistance()/sumProbabilty);
            //System.out.println(chr.getProbabilityOfExistance());
            sumProbabiltyAfter+=chr.getProbabilityOfExistance();
        }
       // System.out.println("sumProbabiltyAfter: "+sumProbabiltyAfter);
        
        
        if (sumProbabiltyAfter>0.98 && sumProbabiltyAfter<1.02)
            return true;
        else
            return false;
    }
    /**
     * Calculates rate of all chromosomes
     * @return sum of rate of all chromosomes
     */
    private int getTheSumOfRateOfAllChromosome() {
        int sum=0;
        for(ChromosomeDAO chr: chromosomy)
        {
            sum+=chr.getRateOfChromosome();
        }
        return sum;
    }
    /**
     * Finds worst chromosome
     * @return worst chromosome
     */
    private ChromosomeDAO getTheWorstChromosome() {
        int worstRate=0;
        ChromosomeDAO worstChromosome = chromosomy.get(0);
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
    
    /**
     * Creation of rulette wheel
     */
    public void createNewRuletteWheel()
    {
       
        Float sumOfProbability = 0.0f;
//        System.out.println();
//        System.out.println();
        for (int i=0;i<NUMBER_OF_CHROMOSOMES;i++)
        {
           RuletteWheel[i]=chromosomy.get(i).getProbabilityOfExistance()+sumOfProbability;
           sumOfProbability+= chromosomy.get(i).getProbabilityOfExistance();
        }
    }
    /**
     * Creates new population. Selection process
     * @return new population
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
            
            for (int j=0;j<RuletteWheel.length;j++){
                if (randomNumber<RuletteWheel[j])
                {
                   // chromosomyNewPopulation.add(chromosomy.get(j));
                    chromosomyNewPopulation.add(new ChromosomeDAO(chromosomy.get(j)));
                    break;
                }
            }
        }
//        System.out.println("choosen");
//        for (ChromosomeDAO chr:chromosomyNewPopulation){
//            System.out.println(chr.getProbabilityOfExistance()+ "  "+chr.getId());
//        }
//        System.out.println("");
        
       // printKoloRuletki();

        chromosomy=chromosomyNewPopulation;
        
//        for (int i = 0; i < NUMBER_OF_CHROMOSOMES; i++) {
//            chromosomy.get(i).setId(i);
//        }
        return chromosomyNewPopulation;
    }

    /**
     *Rates all chromosomes in population
     */
    public void rateAllChromosome(){
        for (ChromosomeDAO chr:chromosomy){
            chr.rateChromosome();
        }
    }
    private Callable<ArrayList<ChromosomeDAO>> callable(ChromosomeDAO chr1, ChromosomeDAO chr2, Integer id1, Integer id2, Integer rowCountFromParent1) {
        Callable<ArrayList<ChromosomeDAO>> task = () -> {
            return crossingOver(chr1,chr2,chr1.getId(),chr2.getId(),rowCountFromParent1);
        };
        return task;
    }
    
}

            

