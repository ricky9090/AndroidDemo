package rickyxe.demo.mvp;

import rickyxe.demo.mvp.base.BaseLifecyclePresenterImpl;

public interface MvpContract {

    interface View {
        void showData(String data);
    }

    public abstract class Presenter extends BaseLifecyclePresenterImpl {
        abstract void loadData();
    }
}
