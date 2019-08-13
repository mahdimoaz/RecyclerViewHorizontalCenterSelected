**RecyclerViewHorizontalCenterSelected**

Learn how to create a custom horizontal Ceneter Selected recyclerview with text in Android studio.

I will show how to create a horizontal list view of text which is scrollable along the x-axis in Android Studio as shown below. Also, display a toast message on item selected center.

If you are a newbie to RecyclerView class, I will partially highlight some of the advantages of it to a listview. RecyclerView is an advancement of listView. listView does not directly support the horizontal listing of elements, unlike RecyclerView for which you can easily implement both vertical and horizontal listing of items on your android app.

Contain the vertical adapter class and the list of text added to the vertical recycler view as shown below.

**MainActivity.java**
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
        
        // getResources().getDimension(R.dimen.width_item_amount_with_padding) is width size item + padding
        // R.drawable.rectangle_round_full_gray is background item
        //R.drawable.rectangle_round_red is background item selected
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

Add **RecyclerViewHorizontalCustom** to **activity_main.xml**


<ir.karsu.horizontalcenterselected.RecyclerViewHorizontalCustom
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:id="@+id/rv_Custom"
        android:layout_gravity="center"
        android:layout_margin="16dp"
        />
   

License
---------------------
Copyright 2019 Mahdi SabbaghZadeh

