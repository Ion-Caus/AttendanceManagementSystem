package viewModel;

import model.Model;

public class ViewModelFactory {
    private ViewModelState viewModelState;

    private SchoolViewModel schoolViewModel;
    private ScheduleViewModel scheduleViewModel;
    //private LoginViewModel loginViewModel;

    public ViewModelFactory(Model model) {
        this.viewModelState = new ViewModelState();

        schoolViewModel = new SchoolViewModel(model, viewModelState);
        scheduleViewModel = new ScheduleViewModel(model, viewModelState);
    }

    public SchoolViewModel getSchoolViewModel() {
        return schoolViewModel;
    }

    public ScheduleViewModel getScheduleViewModel() {
        return scheduleViewModel;
    }
}
