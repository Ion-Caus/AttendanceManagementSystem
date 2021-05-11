package viewModel;

import model.Model;

public class ViewModelFactory
{
  private ViewModelState viewModelState;

  private SchoolViewModel schoolViewModel;
  private ScheduleViewModel scheduleViewModel;

  private InfoViewModel infoViewModel;
  private StudentListViewModel studentListViewModel;
  //private LoginViewModel loginViewModel;

  public ViewModelFactory(Model model)
  {
    this.viewModelState = new ViewModelState();

    schoolViewModel = new SchoolViewModel(model, viewModelState);
    scheduleViewModel = new ScheduleViewModel(model, viewModelState);

    infoViewModel = new InfoViewModel(model);
    studentListViewModel = new StudentListViewModel(model);
  }

  public SchoolViewModel getSchoolViewModel()
  {
    return schoolViewModel;
  }

  public ScheduleViewModel getScheduleViewModel()
  {
    return scheduleViewModel;
  }

  public InfoViewModel getInfoViewModel()
  {
    return infoViewModel;
  }

  public StudentListViewModel getStudentListViewModel()
  {
    return studentListViewModel;
  }
}
