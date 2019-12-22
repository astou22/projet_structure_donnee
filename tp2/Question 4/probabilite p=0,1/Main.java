import java.util.Random;

public class Main {

    public static void main(String[] args) {
        int i;
        // Tableau dynamique.
        ArrayListProxy<Integer> a = new ArrayListProxy<Integer>();
        // Analyse du temps pris par les opérations.
        Analyzer time_analysis = new Analyzer();
        // Analyse du nombre de copies faites par les opérations.
        Analyzer copy_analysis = new Analyzer();
	// Analyse de l'espace mémoire inutilisé.
	Analyzer memory_analysis = new Analyzer();
        long before, after;
        Random r=new Random(11929795);
        // Booléen permettant de savoir si une allocation a été effectuée.
        boolean memory_allocation;

        Double p;
        for(i = 0; i < 12000 ; i++){
            p=r.nextDouble();
            before = System.nanoTime();
        	if(p<0.1) {
                   // Ajout d'un élément et mesure du temps pris par l'opération.
                    memory_allocation = a.append(i);
        	     }
        	else {
        		// suppression des éléments et mesure du temps pris par l'opération
               memory_allocation = a.pop_back();
               }
            after = System.nanoTime();

                   // Enregistrement du temps pris par l'opération
                   time_analysis.append(after - before);
                   // Enregistrement du nombre de copies efféctuées par l'opération.
                   // S'il y a eu réallocation de mémoire, il a fallu recopier tout le tableau.
                   copy_analysis.append( (memory_allocation == true)? i: 1);
	              // Enregistrement de l'espace mémoire non-utilisé.
	                memory_analysis.append( a.capacity() - a.size() );
        }

        // Affichage de quelques statistiques sur l'expérience.
        System.err.println("Total cost : "+time_analysis.get_total_cost());
        System.err.println("Average cost : "+time_analysis.get_average_cost());
        System.err.println("Variance :"+time_analysis.get_variance());
        System.err.println("Standard deviation :"+time_analysis.get_standard_deviation());

        // Sauvegarde les données de l'expérience: temps et nombre de copies effectuées par opération.
        time_analysis.save_values("../plots/dynamic_array_time_javap=0,1.plot");
        copy_analysis.save_values("../plots/dynamic_array_copy_javap=0,1.plot");
	memory_analysis.save_values("../plots/dynamic_array_memory_javaP=0,1.plot");
    }
}
