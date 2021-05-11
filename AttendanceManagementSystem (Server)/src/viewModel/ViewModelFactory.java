package viewModel;

import model.Model;

public class ViewModelFactory {
    private ViewModelState viewModelState;

    private SchoolViewModel schoolViewModel;
    private ScheduleViewModel scheduleViewModel;

    private InfoViewModel infoViewModel;
    //private LoginViewModel loginViewModel;

    public ViewModelFactory(Model model) {
        this.viewModelState = new ViewModelState();

        schoolViewModel = new SchoolViewModel(model, viewModelState);
        scheduleViewModel = new ScheduleViewModel(model, viewModelState);

        infoViewModel = new InfoViewModel(model);
    }

    public SchoolViewModel getSchoolViewModel() {
        return schoolViewModel;
    }

    public ScheduleViewModel getScheduleViewModel() {
        return scheduleViewModel;
    }

  public InfoViewModel getInfoViewModel()
  {
      return infoViewModel;
  }
}
