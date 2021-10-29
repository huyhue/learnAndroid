package fpt.edu.recycleviewmvvmlivedata;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class UserViewModel extends ViewModel {

    private MutableLiveData<List<User>> listMutableLiveData;
    private List<User> list;

    public UserViewModel() {
        listMutableLiveData = new MutableLiveData<>();
        initData();
    }

    private void initData() {
        list = new ArrayList<>();
        list.add(new User("huy","hue"));
        listMutableLiveData.setValue(list);
    }

    public MutableLiveData<List<User>> getListMutableLiveData() {
        return listMutableLiveData;
    }

    public void addUser(User user) {
        list.add(user);
        listMutableLiveData.setValue(list);
    }
}
