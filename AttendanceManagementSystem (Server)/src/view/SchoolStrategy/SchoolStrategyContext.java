package view.SchoolStrategy;

import view.SchoolViewController;
import viewModel.SchoolViewModel;

import java.util.HashMap;

public class SchoolStrategyContext
{
  private HashMap<String, SchoolStrategy> strategies;

  public SchoolStrategyContext(SchoolViewController controller, SchoolViewModel viewModel){
    strategies = new HashMap<>();
    SchoolStrategy classStrategy = new SchoolClassesStrategy(controller,viewModel);
    SchoolStrategy studentStrategy = new SchoolStudentsStrategy(controller, viewModel);


    strategies.put("Classes",classStrategy);
    strategies.put("Students",studentStrategy);

  }

  public void add(String key){
    strategies.get(key).add();
  }

  public void remove(String key){
    strategies.get(key).remove();
  }

}
