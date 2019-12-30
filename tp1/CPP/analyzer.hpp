#ifndef __ANALYZER_HPP__
#define __ANALYZER_HPP__

#include<cmath>
#include <vector>
#include <iostream>

/**
   Classe utilisée pour faire des statistiques élémentaires
   sur une séquence d'opérations.
*/
class Analyzer{
public:
  /**
   Constructeur de la classe analyse
   Complexité en temps/espace, pire et meilleur cas : O(1)  
 */
  Analyzer():cumulative_square(0){}
  
  /**
     Ajoute un coût, une valeur à l'analyse.
     Complexité en temps/espace, pire cas : O(size)
     Complexité en temps/espace, meilleur cas : O(1)
     Complexité amortie : O(1)
     @param x est la valeur que l'on souhaite ajouter à l'analyse.
  */
  void append(const double & x);

  /**
     Renvoie la somme des coûts enregistrés dans cette analyse.
     Complexité en temps/espace, meilleur cas : O(1)
     @returns la somme des coûts enregistrés dans cette analyse.
  */
  long double get_total_cost(){
    return cumulative_cost.back();
  }

  /**
     Renvoie le coût amorti d'une opération.
     Complexité en temps/espace, meilleur cas : O(1)
     @param pos est l'indice de l'opération pour laquelle on veut connaître le coût amorti.
     @returns le coût amorti d'une opération.
  */
  long double get_amortized_cost(size_t pos){
    return (pos)? cumulative_cost.at(pos)/pos : cumulative_cost.at(pos); 
  }

  /**
     Renvoie la moyenne des coûts de toutes les opérations enregistrées dans l'analyse.
     Complexité en temps/espace, meilleur cas : O(1)
     @returns la moyenne des coûts de toutes les opérations enregistrées dans l'analyse.
  */
  long double get_average_cost();

  /**
     Renvoie la variance des coûts de toutes les opérations enregistrées dans l'analyse.
     Complexité en temps/espace, meilleur cas : O(1)
     @returns la variance des coûts de toutes les opérations enregistrées dans l'analyse.
  */
  long double get_variance();

  /**
     Renvoie l'écart-type des coûts de toutes les opérations enregistrées dans l'analyse.
     Complexité en temps/espace, meilleur cas : O(1)
     @returns l'écart-type des coûts de toutes les opérations enregistrées dans l'analyse.
  */
  long double get_standard_deviation(){
    return std::sqrt(get_variance());
  }

  /**
     Sauvegarde la liste des coûts et des coûts amortis dans un fichier.
     Complexité en temps, meilleur/pire cas : O(size)
     @param path est le chemin du fichier dans lequel la sauvegarde est faite.
  */
  void save_values(const std::string & path);
  /**
     Affiche la liste des coûts et des coûts amortis sur la sortie standard.
     Complexité en temps, meilleur/pire cas : O(size)
  */
  void plot_values();

private:
  // Coût de chaque opération. Peut représenter du temps ou une autre mesure.
  std::vector<double> cost;
  // Coût cumulatif. La case i contient la somme des coûts des i premières opérations.
  // Permet de calculer le coût amorti d'une opération.
  std::vector<long double> cumulative_cost;
  // Carré du coût cumulatif. Sert à calculer la variance. On ne garde que la dernière valeur.
  long double cumulative_square;
};


#endif
