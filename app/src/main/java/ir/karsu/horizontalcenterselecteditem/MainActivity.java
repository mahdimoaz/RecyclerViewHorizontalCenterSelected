package ir.karsu.horizontalcenterselecteditem;

import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.ArrayList;
import java.util.List;

import ir.karsu.horizontalcenterselected.ItemListener;
import ir.karsu.horizontalcenterselected.RecyclerViewHorizontalCustom;

public class MainActivity extends AppCompatActivity implements ItemListener {
    RecyclerViewHorizontalCustom recyclerViewHorizontalCustom;
    LinearLayoutManager layoutManager;
    List<String> amountList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        amountList = new ArrayList<>();
        fillList();
        recyclerViewHorizontalCustom = (RecyclerViewHorizontalCustom) findViewById(R.id.rv_Custom);
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewHorizontalCustom.setLayoutManager(layoutManager);
        recyclerViewHorizontalCustom.setHasFixedSize(true);
        recyclerViewHorizontalCustom.setAmountList(amountList);
        recyclerViewHorizontalCustom.init(MainActivity.this, MainActivity.this, R.layout.item_amount, getResources().getDimension(R.dimen.width_item_amount_with_padding), R.drawable.rectangle_round_full_gray, R.drawable.rectangle_round_red);
            // recyclerViewHorizontalCustom.scrollToX(5);


    }

    private void fillList() {
        for (int i = 1; i <= 20; i++)
            amountList.add(String.valueOf(i));

    }

    @Override
    public void getValue(String amount, int pos) {
        Toast.makeText(this, amount, Toast.LENGTH_SHORT).show();
    }
}
