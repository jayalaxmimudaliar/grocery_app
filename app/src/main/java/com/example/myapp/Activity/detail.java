package com.example.myapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.myapp.Adapter.SimilarAdapter;
import com.example.myapp.Domain.ItemsDomain;
import com.example.myapp.R;

public class detail extends AppCompatActivity {
private ItemsDomain object;
private ImageView backBtn,itemImg;
private Button addBtn;
private TextView priceKgTxt,titleTxt,descriptionTxt,ratingTxt;
private RatingBar ratingBar;
private TextView plusTxt,minusTxt,totalTxt,weightTxt;
private int weight=1;
private RecyclerView.Adapter similarAdapter;
private RecyclerView recyclerViewSimilar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        getBundles();
        initView();
        setVariables();
        initSimilarList();

    }

    private void initSimilarList() {
        recyclerViewSimilar=findViewById(R.id.similarView);
        recyclerViewSimilar.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        similarAdapter=new SimilarAdapter(new MainActivity().getData());
        recyclerViewSimilar.setAdapter(similarAdapter);
    }

    private void setVariables() {

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        int drawableResourceId=getResources().getIdentifier(object.getImgPath(),"drawable",detail.this.getPackageName());

        Glide.with(detail.this)
                .load(drawableResourceId)
                .into(itemImg);

        priceKgTxt.setText(object.getPrice()+"$/kg");
        titleTxt.setText(object.getTitle());
        descriptionTxt.setText(object.getDescription());
        ratingTxt.setText("("+object.getRate()+")");
        ratingBar.setRating((float) object.getRate());
        totalTxt.setText((weight*object.getPrice())+"$");

        plusTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                weight=weight+1;
                weightTxt.setText(weight+"kg");
                totalTxt.setText((weight*object.getPrice())+"$");
            }
        });

        minusTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(weight>1) {
                    weight = weight - 1;
                    weightTxt.setText(weight + "kg");
                    totalTxt.setText((weight*object.getPrice())+"$");

                }
            }
        });


    }

    private void initView() {
        backBtn=findViewById(R.id.backBtn);
        itemImg=findViewById(R.id.img);
        priceKgTxt=findViewById(R.id.priceKgTxt);
        titleTxt=findViewById(R.id.titleTxt);
        descriptionTxt=findViewById(R.id.description);
        ratingBar=findViewById(R.id.ratingBar);
        ratingTxt=findViewById(R.id.ratingTxt);
        plusTxt=findViewById(R.id.plusBtn);
        minusTxt=findViewById(R.id.minusBtn);
        totalTxt=findViewById(R.id.totalTxt);
        weightTxt=findViewById(R.id.weightTxt);
        addBtn=findViewById(R.id.addBtn);
    }

    private void getBundles() {
        object =(ItemsDomain) getIntent().getSerializableExtra("object");
    }
}