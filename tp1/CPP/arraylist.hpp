#ifndef __ARRAYLIST_HPP
#define __ARRAYLIST_HPP

#include<iostream>
#include<vector>

/**
   Cette classe est un proxy pour les vecteurs, c'est à dire les tableaux dynamiques en C++.
   On utilise cette classe afin d'avoir le contrôle sur la gestion de la mémoire.
*/
template <typename P>
class ArrayList{
public:
  /**
     Constructeur de la classe des tableaux dynamiques.
     Complexité en temps/espace, pire et meilleur cas : O(1)  
  */
  ArrayList(){
    data.reserve(4);
  }

  /**
     Ajoute une valeur dans le tableau.
     Complexité en temps/espace, pire cas : O(size)
     Complexité en temps/espace, meilleur cas : O(1)
     Complexité amortie : O(1)
     @param x est la valeur que l'on souhaite ajouter.
     @returns true si le tableau a été agrandit, false sinon
  */
  bool append(P x){
    bool memory_allocation = false;
    if( do_we_need_to_enlarge_capacity() ){
      memory_allocation = true;
      enlarge_capacity();
    }
    data.push_back(x);
    return memory_allocation;
  }

  /**
     Supprime la dernière valeur du tableau.
     Complexité en temps, pire cas : O(size)
     Complexité en temps, meilleur cas : O(1)
     Complexité amortie : O(1)
     @returns true si le tableau a été réduit, false sinon
  */
  bool pop_back(){
    bool memory_reduction = false;
    if(!data.empty()){
      if( do_we_need_to_reduce_capacity() ){
	memory_reduction = true;
	reduce_capacity();
      }
      data.pop_back();
    }
    return memory_reduction;
  }
  
  /**
     Renvoie la valeur située à la position donnée par l'utilisateur.
     Complexité en temps/espace, pire cas : O(1)
     @param pos est la l'indice de la case on l'utilisateur veut connaître la valeur.
     @returns la valeur située à la position donnée par l'utilisateur.
  */
  P get(const int pos){
    return data[pos];
  }

  /**
     Renvoie le nombre d'éléments stockés dans le tableau.
     Complexité en temps/espace, pire cas : O(1)
     @returns le nombre d'éléments stockés dans le tableau.
  */
  const size_t size(){ return data.size(); }

  /**
     Renvoie la capacité de stockage du tableau.
     Complexité en temps/espace, pire cas : O(1)
     @returns le nombre d'éléments stockés dans le tableau.
  */
  const size_t capacity(){ return data.capacity(); }
  
private:
  // Vecteur contenant les données.
  std::vector<P> data;

  /**
     Cette fonction détermine la règle selon laquelle un espace mémoire plus grand sera alloué ou non.
     @returns true si le tableau doit être agrandi, false sinon.
  */
  bool do_we_need_to_enlarge_capacity(){
    return data.size() >= (data.capacity() * 3)/4;
  }

  /**
     Cette fonction augmente la capacité du tableau.
  */
  void enlarge_capacity(){
    data.reserve( data.capacity() *2 );
  }

  /**
     Cette fonction détermine la règle selon laquelle un espace mémoire plus petit sera alloué ou non.
     @returns true si le tableau doit être réduit, false sinon.
  */
  bool do_we_need_to_reduce_capacity(){
    return data.size() <= data.capacity()/4 && data.size() >4;
  }
  
  /**
     Cette fonction reduit la capacité du tableau.
  */
  void reduce_capacity(){
    data.reserve( data.capacity() /2 );
  }
  
};


#endif
