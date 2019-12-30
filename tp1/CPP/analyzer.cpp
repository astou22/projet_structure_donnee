#include "analyzer.hpp"
#include <fstream>


void Analyzer::append(const double & x){
  cost.push_back(x);    
  cumulative_cost.push_back( (cumulative_cost.size()) ? cumulative_cost.back()+x : x);
  cumulative_square += x*x;
}

long double Analyzer::get_average_cost(){
  if(cumulative_cost.empty())
    throw std::runtime_error("List is empty");
  return cumulative_cost.back()/cumulative_cost.size();
}

long double Analyzer::get_variance(){
  long double mean, mean_square;
  mean = get_average_cost();
  mean_square = mean * mean;
  return cumulative_square - mean_square;
}

void Analyzer::save_values(const std::string & path){
  std::ofstream f;
    size_t i;
    f.open(path.c_str());
    for (i = 0; i < cost.size(); ++i)
      f<<i<<" "<<cost.at(i)<<" "<<get_amortized_cost(i)<<std::endl;
    f.close();
}

void Analyzer::plot_values(){
  size_t i;
  for (i = 0; i < cost.size(); ++i)
    std::cout<<i<<" "<<cost.at(i)<<" "<<get_amortized_cost(i)<<std::endl;
}
