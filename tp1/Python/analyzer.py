import math

#  Classe utilisee pour faire des statistiques elementaires
#  sur une sequence d'operations.
class Analyzer:
    # Constructeur de la classe analyse
    # Complexite en temps/espace, pire et meilleur cas : O(1)  
    def __init__(self):
        self.cost = [];
        self.cumulative_cost = [];
        self.cumulative_square = 0.;

    # Ajoute un cout, une valeur a l'analyse.
    # Complexite en temps/espace, pire cas : O(size)
    # Complexite en temps/espace, meilleur cas : O(1)
    # Complexite amortie : O(1)
    # @param x est la valeur que l'on souhaite ajouter a l'analyse.
    def append(self, x):
        self.cost.append(x)
        
        self.cumulative_cost.append( self.cumulative_cost[len(self.cumulative_cost)-1]+x if len(self.cumulative_cost)>0 else x)
        self.cumulative_square = self.cumulative_square+x*x

    # Renvoie la somme des couts enregistres dans cette analyse.
    # Complexite en temps/espace, meilleur cas : O(1)
    # @returns la somme des couts enregistres dans cette analyse.
    def get_total_cost(self):
        return self.cumulative_cost[len(self.cumulative_cost)-1]

    # Renvoie le cout amorti d'une operation.
    # Complexite en temps/espace, meilleur cas : O(1)
    # @param pos est l'indice de l'operation pour laquelle on veut connaitre le cout amorti.
    # @returns le cout amorti d'une operation.    
    def get_amortized_cost(self, pos):
        return self.cumulative_cost[pos]/pos if pos>0 else self.cumulative_cost[pos] 

    # Renvoie la moyenne des couts de toutes les operations enregistrees dans l'analyse.
    # Complexite en temps/espace, meilleur cas : O(1)
    # @returns la moyenne des couts de toutes les operations enregistrees dans l'analyse.
    def get_average_cost(self):
        if len(self.cumulative_cost) == 0:
            raise Exception('List is empty')
        return self.cumulative_cost[len(self.cumulative_cost)-1]/len(self.cumulative_cost);

    # Renvoie la variance des couts de toutes les operations enregistrees dans l'analyse.
    # Complexite en temps/espace, meilleur cas : O(1)
    # @returns la variance des couts de toutes les operations enregistrees dans l'analyse.
    def get_variance(self):
        mean = self.get_average_cost()
        mean_square = mean*mean
        return self.cumulative_square - mean_square

    # Renvoie l'ecart-type des couts de toutes les operations enregistrees dans l'analyse.
    # Complexite en temps/espace, meilleur cas : O(1)
    # @returns l'ecart-type des couts de toutes les operations enregistrees dans l'analyse.
    def get_standard_deviation(self):
        return math.sqrt(self.get_variance())

    # Sauvegarde la liste des couts et des couts amortis dans un fichier.
    # Complexite en temps, meilleur/pire cas : O(size)
    # @param path est le chemin du fichier dans lequel la sauvegarde est faite.
    def save_values(self, path):
        f = open(path, 'w')
        for i in range(len(self.cost)):
            f.write(str(i)+" "+str(self.cost[i])+"  "+str(self.get_amortized_cost(i))+"\n")
        f.close()
