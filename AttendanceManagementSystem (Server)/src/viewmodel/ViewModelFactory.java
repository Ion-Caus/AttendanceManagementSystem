package viewmodel;

import model.Model;

public class ViewModelFactory {
    private ViewModelState viewModelState;

    private SchoolViewModel schoolViewModel;
    //private LoginViewModel loginViewModel;

    public ViewModelFactory(Model model) {
        this.viewModelState = new ViewModelState();

        schoolViewModel = new SchoolViewModel(model, viewModelState);
    }

    public SchoolViewModel getSchoolViewModel() {
        return schoolViewModel;
    }
}
