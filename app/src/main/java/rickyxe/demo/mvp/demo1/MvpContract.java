package rickyxe.demo.mvp.demo1;

import rickyxe.demo.mvp.base.BaseLifecyclePresenterImpl;

public interface MvpContract {

    interface View {
        void showData(String data);
    }

    abstract class Presenter extends BaseLifecyclePresenterImpl {
        abstract void loadData();
    }
}
