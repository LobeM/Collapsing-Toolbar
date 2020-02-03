package com.musonda.toolbarani;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.palette.graphics.Palette;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.musonda.toolbarani.adapters.DessertAdapter;

public class MainActivity extends AppCompatActivity {

    private CollapsingToolbarLayout mCollapsingToolbarLayout;

    private boolean appBarExpanded = true;
    private Menu collapsedMenu;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Toolbar toolbar = findViewById(R.id.anim_toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() == null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        AppBarLayout appBarLayout = findViewById(R.id.appbar);

        mCollapsingToolbarLayout = findViewById(R.id.collapsing_toolbar);
        mCollapsingToolbarLayout.setTitle(getString(R.string.android_desserts));

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.header);

        Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onGenerated(@Nullable Palette palette) {
                assert palette != null;
                int vibrantColor = palette.getVibrantColor(R.color.primary_500);
                mCollapsingToolbarLayout.setContentScrimColor(vibrantColor);
                mCollapsingToolbarLayout.setStatusBarScrimColor(R.color.black_trans80);
            }
        });

        RecyclerView recyclerView = findViewById(R.id.scrollableview);
        // Use when your list is constant for better performance
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        DessertAdapter dessertAdapter = new DessertAdapter(this);
        recyclerView.setAdapter(dessertAdapter);

        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int i) {
                //  Vertical offset == 0 indicates appBar is fully expanded
                if (Math.abs(i) > 200) {
                    appBarExpanded = false;
                    invalidateOptionsMenu();
                } else {
                    appBarExpanded = true;
                    invalidateOptionsMenu();
                }
            }
        });
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        if (collapsedMenu != null &&
                (!appBarExpanded || collapsedMenu.size() != 1)){
            //  Collapsed
            collapsedMenu.add("Add")
                    .setIcon(R.drawable.ic_add)
                    .setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        }
        //  Expanded

        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        collapsedMenu = menu;
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            case R.id.action_settings:
                return true;
        }
        if (item.getTitle() == "Add") {
            Toast.makeText(this, "Clicked Add", Toast.LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected(item);
    }
}
