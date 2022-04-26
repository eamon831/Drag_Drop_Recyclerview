package com.example.dragdroprecyclerview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.View;
import android.widget.TextView;

import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;
import com.woxthebox.draglistview.DragListView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView mainlist,copylist;

    List<String> wordList= new ArrayList<>();
    List<String> textview= new ArrayList<>();

    adapter a;
    adapterb aa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainlist=findViewById(R.id.mainlist);
        copylist=findViewById(R.id.copylist);
        //mainlist.setBackgroundDrawable(getResources().getDrawable(R.drawable.shape));
       // copylist.setBackgroundDrawable(getResources().getDrawable(R.drawable.shape));

        mainlist.setLayoutManager(new LinearLayoutManager(this));

        FlexboxLayoutManager layoutManager = new FlexboxLayoutManager(this);
        layoutManager.setFlexDirection(FlexDirection.ROW);
        ///layoutManager.setJustifyContent(JustifyContent.FLEX_END);
        copylist.setLayoutManager(layoutManager);


        copylist.setOnDragListener(new MyDragListener());
        add(100);
        //textview.add("word105");
        textview.add("Hello");
        textview.add("World");

        a=new adapter(wordList,MainActivity.this);
        aa=new adapterb(MainActivity.this, textview);
        mainlist.setAdapter(a);
        copylist.setAdapter(aa);
        ItemTouchHelper itemTouchHelper=new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(copylist);


    }

    class MyDragListener implements View.OnDragListener {
        Drawable enterShape = getResources().getDrawable(
                R.drawable.shape_droptarget);
        Drawable normalShape = getResources().getDrawable(R.drawable.shape);

        @Override
        public boolean onDrag(View v, DragEvent event) {
            int action = event.getAction();
            switch (event.getAction()) {
                case DragEvent.ACTION_DRAG_STARTED:
                    // do nothing
                    break;
                case DragEvent.ACTION_DRAG_ENTERED:
                    //Toast.makeText(MainActivity.this, "Drag_entered", Toast.LENGTH_SHORT).show();
                    v.setBackgroundDrawable(enterShape);


                    break;
                case DragEvent.ACTION_DRAG_EXITED:
                    //Toast.makeText(MainActivity.this, "Exit drag", Toast.LENGTH_SHORT).show();

                    v.setBackgroundDrawable(normalShape);
                    break;
                case DragEvent.ACTION_DROP:
                    // Dropped, reassign View to ViewGroup

                    TextView t;
                    View view = (View) event.getLocalState();
                    t=view.findViewById(R.id.itemTextView);
                    String temp=t.getText().toString();
                    String r=temp;

                    textview.add(temp);
                    //temp=all(textview);
                    //TextView container = (TextView) v;
                   // container.setText(temp);
                    //view.setVisibility(View.VISIBLE);

                    wordList.remove(new String(r));
                    mainlist.setAdapter(null);
                    a=new adapter(wordList,MainActivity.this);
                    aa=new adapterb(MainActivity.this, textview);
                    copylist.setAdapter(aa);
                    mainlist.setAdapter(a);


                    break;
                case DragEvent.ACTION_DRAG_ENDED:
                    v.setBackgroundDrawable(normalShape);
                default:
                    break;
            }
            return true;
        }
    }


    ItemTouchHelper.SimpleCallback simpleCallback=new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP|ItemTouchHelper.DOWN|
            ItemTouchHelper.START|ItemTouchHelper.END,0) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            int formposition=viewHolder.getAdapterPosition();
            int toposition=target.getAdapterPosition();
            Collections.swap(wordList,formposition,toposition);
            recyclerView.getAdapter().notifyItemMoved(formposition,toposition);
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

        }
    };




    private void add(int n) {
        for(int i=1;i<=n;i++)
        {
            wordList.add("word"+i);
        }
    }
}