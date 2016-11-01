package chongz.leak.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import chongz.leak.R;

public class RecycleViewActivity extends AppCompatActivity {

    @BindView(R.id.recycle_view)
    RecyclerView recycleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle_view);
        ButterKnife.bind(this);

        recycleView.setLayoutManager(new LinearLayoutManager(this));
        recycleView.setAdapter(new RecycleAdapter(this));
    }
}
