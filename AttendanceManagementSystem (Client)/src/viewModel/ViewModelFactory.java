package viewModel;

import model.Model;

public class ViewModelFactory
{
  private ViewModelState viewModelState;

  private SchoolViewModel schoolViewModel;
  private ScheduleViewModel scheduleViewModel;

  private InfoViewModel infoViewModel;
  private StudentListViewModel studentListViewModel;
  private AddStudentViewModel addStudentViewModel;
  private AddClassViewModel addClassViewModel;
  private AddTeacherViewModel addTeacherViewModel;
  private AddGradeViewModel addGradeViewModel;
  private LoginViewModel loginViewModel;
  private ClassStudentListViewModel classStudentListViewModel;
  private AddLessonViewModel addLessonViewModel;
  private ManageViewModel manageViewModel;


  public ViewModelFactory(Model model)
  {
    this.viewModelState = new ViewModelState();

    loginViewModel = new LoginViewModel(model, viewModelState);

    schoolViewModel = new SchoolViewModel(model, viewModelState);
    scheduleViewModel = new ScheduleViewModel(model, viewModelState);

    infoViewModel = new InfoViewModel(model, viewModelState);
    studentListViewModel = new StudentListViewModel(model, viewModelState);
    addStudentViewModel = new AddStudentViewModel(model);
    addClassViewModel = new AddClassViewModel(model);
    addTeacherViewModel = new AddTeacherViewModel(model);
    classStudentListViewModel = new ClassStudentListViewModel(model, viewModelState);
    addLessonViewModel = new AddLessonViewModel(model, viewModelState);
    addGradeViewModel = new AddGradeViewModel(model,viewModelState);
    manageViewModel = new ManageViewModel(model,viewModelState);
  }

  public LoginViewModel getLoginViewModel() {
    return loginViewModel;
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

  public AddStudentViewModel getAddStudentViewModel(){
    return addStudentViewModel;
  }

  public AddClassViewModel getAddClassViewModel() {
    return addClassViewModel;
    }

  public AddTeacherViewModel getAddTeacherViewModel() {
    return addTeacherViewModel;
  }

  public ClassStudentListViewModel getClassStudentListViewModel() {
    return classStudentListViewModel;
  }

  public AddLessonViewModel getAddLessonViewModel() {
    return addLessonViewModel;
  }

  public AddGradeViewModel getAddGradeViewModel() {
    return addGradeViewModel;
  }

  public ManageViewModel getManageViewModel() {
    return manageViewModel;
  }
}
