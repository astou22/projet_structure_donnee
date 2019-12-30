import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

/**
    Classe utilisée pour faire des statistiques élémentaires
    sur une séquence d'opérations.
 */
public class Analyzer {

    /**
        Constructeur de la classe analyse
        Complexité en temps/espace, pire et meilleur cas : O(1)
     */
    public Analyzer() {
        cost = new ArrayList<Double>();
        cumulative_cost = new ArrayList<BigDecimal>();
        cumulative_square = new BigDecimal(0.);
    }

    /**
        Ajoute un coût, une valeur à l'analyse.
        Complexité en temps/espace, pire cas : O(size)
        Complexité en temps/espace, meilleur cas : O(1)
        Complexité amortie : O(1)
        @param x est la valeur que l'on souhaite ajouter à l'analyse.
     */
    void append(double  x){
        BigDecimal x_big = new BigDecimal(x);
        cost.add(x);
        cumulative_cost.add( (!cumulative_cost.isEmpty()) ? cumulative_cost.get(cumulative_cost.size()-1).add(x_big) : x_big);
	    BigDecimal x_square = x_big.multiply(x_big);
        cumulative_square = cumulative_square.add(x_square);
    }

    /**
        Renvoie la somme des coûts enregistrés dans cette analyse.
        Complexité en temps/espace, meilleur cas : O(1)
        @returns la somme des coûts enregistrés dans cette analyse.
     */
    BigDecimal get_total_cost(){
        return cumulative_cost.get(cumulative_cost.size()-1);
    }

    /**
        Renvoie le coût amorti d'une opération.
        Complexité en temps/espace, meilleur cas : O(1)
        @param pos est l'indice de l'opération pour laquelle on veut connaître le coût amorti.
        @returns le coût amorti d'une opération.
     */
    BigDecimal get_amortized_cost(int pos){
        return (pos > 0)? cumulative_cost.get(pos).divide(new BigDecimal(pos), RoundingMode.HALF_UP) : cumulative_cost.get(pos);
    }

    /**
        Renvoie la moyenne des coûts de toutes les opérations enregistrées dans l'analyse.
        Complexité en temps/espace, meilleur cas : O(1)
        @returns la moyenne des coûts de toutes les opérations enregistrées dans l'analyse.
     */
    BigDecimal get_average_cost(){
        if(cumulative_cost.isEmpty())
            throw new RuntimeException("List is empty");
        return cumulative_cost.get(cumulative_cost.size()-1).divide(new BigDecimal(cumulative_cost.size()));
    }

    /**
        Renvoie la variance des coûts de toutes les opérations enregistrées dans l'analyse.
        Complexité en temps/espace, meilleur cas : O(1)
        @returns la variance des coûts de toutes les opérations enregistrées dans l'analyse.
     */
    BigDecimal get_variance(){
        BigDecimal mean, mean_square;
        mean = get_average_cost();
        mean_square = mean.multiply(mean);
        if(cumulative_square.compareTo(mean_square) < 0)
            throw new RuntimeException("Error: mean of squares is less than square of mean: "+mean+" "+cumulative_square);
        return cumulative_square.subtract(mean_square);
    }

    /**
        Renvoie l'écart-type des coûts de toutes les opérations enregistrées dans l'analyse.
        Complexité en temps/espace, meilleur cas : O(1)
        @returns l'écart-type des coûts de toutes les opérations enregistrées dans l'analyse.
     */
    BigDecimal get_standard_deviation(){
        return new BigDecimal(Math.sqrt(get_variance().doubleValue()));
    }

    /**
        Sauvegarde la liste des coûts et des coûts amortis dans un fichier.
        Complexité en temps, meilleur/pire cas : O(size)
        @param path est le chemin du fichier dans lequel la sauvegarde est faite.
     */
    void save_values(String path){
        int i;
        File file = new File(path);
        FileWriter fr = null;
        try {
            fr = new FileWriter(file);
            for (i = 0; i < cost.size(); ++i){
                fr.write(i+" "+cost.get(i)+" "+get_amortized_cost(i)+"\n");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            //close resources
            try {
                fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
        Affiche la liste des coûts et des coûts amortis sur la sortie standard.
        Complexité en temps, meilleur/pire cas : O(size)
     */
    void plot_values(){
        int i;
        for (i = 0; i < cost.size(); ++i){
            System.out.println(i+" "+cost.get(i)+" "+get_amortized_cost(i));
        }
    }


    // Coût de chaque opération. Peut représenter du temps ou une autre mesure.
    private ArrayList<Double> cost;
    // Coût cumulatif. La case i contient la somme des coûts des i premières opérations.
    // Permet de calculer le coût amorti d'une opération.
    private ArrayList<BigDecimal> cumulative_cost;
    // Carré du coût cumulatif. Sert à calculer la variance. On ne garde que la dernière valeur.
    private BigDecimal cumulative_square;


}
