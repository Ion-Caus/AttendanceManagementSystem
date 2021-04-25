package viewmodel;

import model.Model;

public class ViewModelFactory {
    private ViewModelState viewModelState;

    private ScheduleViewModel scheduleViewModel;
    //private LoginViewModel loginViewModel;

    public ViewModelFactory(Model model) {
        this.viewModelState = new ViewModelState();

        scheduleViewModel = new ScheduleViewModel(model, viewModelState);
    }

    public ScheduleViewModel getScheduleViewModel() {
        return scheduleViewModel;
    }
}
