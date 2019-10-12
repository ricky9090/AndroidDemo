package rickyxe.demo.mvp.demo2;

import rickyxe.demo.mvp.base.BaseLifecyclePresenterImpl;

public interface MvpContract2 {

    interface View {
        void showUserInfoData(UserApiResult result);
    }

    abstract class Presenter extends BaseLifecyclePresenterImpl {
        abstract void loadData(String userName);
    }
}
