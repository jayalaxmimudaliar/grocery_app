package com.example.myapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

import com.example.myapp.Adapter.BestDealAdapter;
import com.example.myapp.Adapter.CategoryAdapter;
import com.example.myapp.Domain.CategoryDomain;
import com.example.myapp.Domain.ItemsDomain;
import com.example.myapp.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    private RecyclerView.Adapter catAdapter,bestDealAdapter;
    private RecyclerView recyclerViewCat,recyclerViewBestDeal;
ImageView setting;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setting=findViewById(R.id.setting);

        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this, logout.class);
                startActivity(intent);
            }
        });

        initRecyclerviewCat();
        initLocation();
        initRecyclerViewBestDeal();
    }

    private void initLocation() {
        String[] items = new String[]{
                "United States",
                "Canada",
                "United Kingdom",
                "Australia",
                "France",
                "Germany",
                "Japan",
                "China",
                "Brazil",
                "India",
                "Russia",
                "Italy",
                "Spain",
                "South Korea",
                "Mexico",
                "Indonesia",
                "Netherlands",
                "Turkey",
                "Saudi Arabia",
                "Switzerland"};

        final Spinner locationSpin = findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        locationSpin.setAdapter(adapter);
    }


    private void initRecyclerviewCat() {
        ArrayList<CategoryDomain> items=new ArrayList<>();
        items.add(new CategoryDomain("cat1","Vegetable"));
        items.add(new CategoryDomain("cat2","Fruits"));
        items.add(new CategoryDomain("cat3","Dairy"));
        items.add(new CategoryDomain("cat4","Drinks"));
        items.add(new CategoryDomain("cat5","Grain"));

        recyclerViewCat=findViewById(R.id.catview);
        recyclerViewCat.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));

        catAdapter=new CategoryAdapter(items);
        recyclerViewCat.setAdapter(catAdapter);
    }

    private void initRecyclerViewBestDeal()
    {
        recyclerViewBestDeal=findViewById(R.id.bestview);
        recyclerViewBestDeal.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        bestDealAdapter=new BestDealAdapter(getData());
        recyclerViewBestDeal.setAdapter(bestDealAdapter);
    }

    public ArrayList<ItemsDomain> getData(){
        ArrayList<ItemsDomain> items=new ArrayList<>();
        items.add(new ItemsDomain("orange","Fresh Orange",6.2,"Juicy, vibrant, and bursting with vitamin C, \nour oranges are the perfect blend of sweetness\n and tanginess, adding a refreshing zest to your day!",4.2));
        items.add(new ItemsDomain("apple","Fresh Apple",6.5,"Fresh, crisp, and naturally sweet, our apples are hand-picked to perfection. Packed with essential nutrients and bursting with flavor, they're the perfect healthy snack or addition to your favorite recipes.",4.6));
        items.add(new ItemsDomain("berry","Fresh Berry",5.1,"Indulge in the juicy goodness of our hand-selected berries. Bursting with vibrant color and natural sweetness, our berries are packed with antioxidants and essential vitamins. Whether enjoyed fresh, blended into smoothies, or topping your favorite desserts, they're sure to add a deliciously nutritious touch to your day.",4.9));
        items.add(new ItemsDomain("pineaplle","Fresh Pineapple",6,"Tropical delight awaits with our premium pineapples. Freshly picked and bursting with tangy sweetness, these golden beauties are packed with vitamins and enzymes to support your well-being. Enjoy them sliced for a refreshing snack, grilled for a tantalizing twist, or blended into tropical smoothies for a taste of paradise.",4.5));
        items.add(new ItemsDomain("strawberry","Fresh Strawberry",12,"Indulge in the sweet sensation of our succulent strawberries. Hand-picked at peak ripeness, these vibrant berries are bursting with flavor and packed with antioxidants. Perfect for snacking, baking, or adding a pop of color to your salads and desserts. Satisfy your cravings with nature's candy today!",4));


        return items;
    }


}