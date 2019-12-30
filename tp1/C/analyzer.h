#ifndef __ANALYZER_H__
#define __ANALYZER_H__

#include <stddef.h>

/**
   Structure utilisée pour faire des statistiques élémentaires
   sur une séquence d'opérations.
*/
typedef struct analyzer_s{
  // Coût de chaque opération. Peut représenter du temps ou une autre mesure.
  double * cost;
  // Coût cumulatif. La case i contient la somme des coûts des i premières opérations.
  // Permet de calculer le coût amorti d'une opération.
  long double * cumulative_cost;
  // Carré du coût cumulatif. Sert à calculer la variance. On ne garde que la dernière valeur.
  long double cumulative_square;
  // Capacité de stockage des tableaux
  size_t capacity;
  // Nombre d'éléments dans chaque tableaux.
  size_t size;   
} analyzer_t;

/**
   Fonction d'initialisation d'une analyse.
   Complexité en temps/espace, pire et meilleur cas : O(1)  
   @return Un pointeur sur une structure analyzer_t nouvellement allouée.
 */
analyzer_t * analyzer_create();

/**
   Fonction de libération de la mémoire occupée par une analyse.
   Complexité en temps/espace, pire et meilleur cas : O(1)  
   @param a est un pointeur vers l'espace mémoire que la fonction va libérer.
 */
void analyzer_destroy(analyzer_t * a);

/**
   Ajoute un coût, une valeur à l'analyse.
   Complexité en temps/espace, pire cas : O(size)
   Complexité en temps/espace, meilleur cas : O(1)
   Complexité amortie : O(1)
   @param a est l'analyse à laquelle on souhaite ajouter une valeur.
   @param cost est la valeur que l'on souhaite ajouter.
*/
void analyzer_append(analyzer_t * a, double cost);

/**
   Renvoie la somme des coûts enregistrés dans cette analyse.
   Complexité en temps/espace, pire cas : O(1)
   @param a est une analyse.
   @returns la somme des coûts enregistrés dans cette analyse.
*/
long double get_total_cost(analyzer_t * a);

/**
   Renvoie le coût amorti d'une opération.
   Complexité en temps/espace, pire cas : O(1)
   @param a est une analyse.
   @param pos est l'indice de l'opération pour laquelle on veut connaître le coût amorti.
   @returns le coût amorti d'une opération.
*/
long double get_amortized_cost(analyzer_t * a, size_t pos);

/**
   Renvoie la moyenne des coûts de toutes les opérations enregistrées dans l'analyse.
   Complexité en temps/espace, pire cas : O(1)
   @param a est une analyse.
   @returns la moyenne des coûts de toutes les opérations enregistrées dans l'analyse.
*/
long double get_average_cost(analyzer_t * a);

/**
   Renvoie la variance des coûts de toutes les opérations enregistrées dans l'analyse.
   Complexité en temps/espace, pire cas : O(1)
   @param a est une analyse.
   @returns la variance des coûts de toutes les opérations enregistrées dans l'analyse.
*/
long double get_variance(analyzer_t * a);

/**
   Renvoie l'écart-type des coûts de toutes les opérations enregistrées dans l'analyse.
   Complexité en temps/espace, pire cas : O(1)
   @param a est une analyse.
   @returns l'écart-type des coûts de toutes les opérations enregistrées dans l'analyse.
*/
long double get_standard_deviation(analyzer_t * a);

/**
   Sauvegarde la liste des coûts et des coûts amortis dans un fichier.
   Complexité en temps, meilleur/pire cas : O(size)
   @param a est l'analyse que l'on souhaite sauvegarder.
   @param path est le chemin du fichier dans lequel la sauvegarde est faite.
*/
void save_values(analyzer_t * a, char * path);

/**
   Affiche la liste des coûts et des coûts amortis sur la sortie standard.
   Complexité en temps, meilleur/pire cas : O(size)
   @param a est l'analyse que l'on souhaite sauvegarder.
*/
void plot_values(analyzer_t * a);

#endif 
