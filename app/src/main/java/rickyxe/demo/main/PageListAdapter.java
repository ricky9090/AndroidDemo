package rickyxe.demo.main;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import rickyxe.demo.R;
import rickyxe.demo.bgwork.BackgroundWorkActivity;
import rickyxe.demo.bgwork2.BackgroundWorkActivity2;
import rickyxe.demo.common.DemoPage;
import rickyxe.demo.constraintdemo.ConstraintDemoActivity;
import rickyxe.demo.contactpick.ContactPickActivity;
import rickyxe.demo.dialogexample.DialogTestActivity;
import rickyxe.demo.lifecycledemo.LifecycleDemoActivity;
import rickyxe.demo.mvp.demo1.MvpDemoActivity;
import rickyxe.demo.mvp.demo2.MvpDemoActivity2;
import rickyxe.demo.recyclerdemo.RecyclerDemoActivity;
import rickyxe.demo.reduxdemo.a.ReduxDemoActivity;
import rickyxe.demo.reduxdemo.b.ReduxDemoTwoActivity;

public class PageListAdapter extends RecyclerView.Adapter<PageListAdapter.PageItemHolder> {

    private Context mContext;

    private final List<DemoPage> pageList = new ArrayList<>();

    public PageListAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public PageItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.layout_page_item, parent, false);
        return new PageItemHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PageItemHolder holder, int position) {
        if (pageList.size() <= position) {
            return;
        }

        final DemoPage item = pageList.get(position);
        if (item == null) {
            return;
        }

        String numStr = (position + 1) + "";
        holder.pageNumber.setText(numStr);
        holder.pageTitle.setText(item.title);
        holder.pageDesc.setText(item.desc);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (item.pageClazz != null) {
                    Intent intent = new Intent();
                    intent.setClass(mContext, item.pageClazz);
                    mContext.startActivity(intent);
                } else {
                    item.openPage(mContext);
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return pageList.size();
    }

    static class PageItemHolder extends RecyclerView.ViewHolder {

        TextView pageNumber;
        TextView pageTitle;
        TextView pageDesc;

        public PageItemHolder(@NonNull View itemView) {
            super(itemView);

            pageNumber = itemView.findViewById(R.id.page_item_number);
            pageTitle = itemView.findViewById(R.id.page_item_title);
            pageDesc = itemView.findViewById(R.id.page_item_desc);
        }
    }

    public void buildPageList() {
        pageList.clear();
        pageList.add(new DemoPage("后台任务", "使用Handler Messenger通信", BackgroundWorkActivity.class));
        pageList.add(new DemoPage("后台任务2", "使用Messenger通信", BackgroundWorkActivity2.class));
        pageList.add(new DemoPage("弹窗测试", "弹窗测试", DialogTestActivity.class));
        pageList.add(new DemoPage("获取联系人", "打开系统选择页面获取联系人", ContactPickActivity.class));
        pageList.add(new DemoPage("Lifecycle", "使用Lifecycle组件", LifecycleDemoActivity.class));
        pageList.add(new DemoPage("ConstraintLayout", "使用ConstraintLayout布局", ConstraintDemoActivity.class));
        pageList.add(new DemoPage("MVP", "MVP模式结合Lifecycle组件", MvpDemoActivity.class));
        pageList.add(new DemoPage("MVP 2", "MVP+OKHttp+Retrofit", MvpDemoActivity2.class));
        pageList.add(new DemoPage("RecyclerView", "RecyclerView嵌套测试", RecyclerDemoActivity.class));
        pageList.add(new DemoPage("Redux", "模拟Redux状态管理", ReduxDemoActivity.class));
        pageList.add(new DemoPage("Redux", "模拟Redux状态管理，Activity不继承基类", ReduxDemoTwoActivity.class));

    }
}
