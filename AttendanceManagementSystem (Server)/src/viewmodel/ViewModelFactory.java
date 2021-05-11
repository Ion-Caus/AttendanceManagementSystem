package viewModel;

import model.Model;

public class ViewModelFactory {
    private viewModel.ViewModelState viewModelState;

    private viewModel.SchoolViewModel schoolViewModel;
    private viewModel.ScheduleViewModel scheduleViewModel;
    private InfoViewModel infoViewModel;
    //private LoginViewModel loginViewModel;

    public ViewModelFactory(Model model) {
        this.viewModelState = new viewModel.ViewModelState();

        schoolViewModel = new viewModel.SchoolViewModel(model, viewModelState);
        scheduleViewModel = new viewModel.ScheduleViewModel(model, viewModelState);
        infoViewModel = new InfoViewModel(model);
    }

    public viewModel.SchoolViewModel getSchoolViewModel() {
        return schoolViewModel;
    }

    public viewModel.ScheduleViewModel getScheduleViewModel() {
        return scheduleViewModel;
    }

    public InfoViewModel getInfoViewModel()
    {
        return infoViewModel;
    }
}

