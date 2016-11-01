package chongz.leak;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;
import io.realm.Realm;


public class DetailDBActivity extends AppCompatActivity {

    public static final String TAG = DetailDBActivity.class.getSimpleName();
    @BindView(R.id.textView)
    TextView textView;
    @BindView(R.id.sendEvtButton)
    Button sendEvtButton;

    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_db);
        ButterKnife.bind(this);

        realm = Realm.getDefaultInstance();




    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }

    @OnClick(R.id.sendEvtButton)
    public void onClick() {
        EventBus.getDefault().postSticky(new FirstEvent("zc"));
    }
}
