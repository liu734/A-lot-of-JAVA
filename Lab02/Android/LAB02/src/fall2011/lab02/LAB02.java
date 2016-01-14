package fall2011.lab02;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class LAB02 extends Activity {

 
 
 int textColor = Color.RED;
 static String text = "CS180 First Android Lab";
 
 
 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT,ViewGroup.LayoutParams.FILL_PARENT));
        
        TextView title = new TextView(this);
        title.setText("Welcome to Android Development");
        title.setTextSize(30f);
        
        Button b = new Button(this);
        b.setText("You can toggle the background color by pressing this button.");
        b.setOnClickListener(new OnClickListener(){
         boolean black = true;
   @Override
   public void onClick(View arg0) {
    if(black){
     layout.setBackgroundColor(Color.WHITE);
     black=!black;
    }else{
     layout.setBackgroundColor(Color.BLACK);
     black=!black;
    }
   }
        });
        
        
        final AnimateText canvasView = new AnimateText(this);
        canvasView.setColor(textColor);
        
        layout.addView(title);
        layout.addView(b);
        layout.addView(canvasView);
        
        setContentView(layout);
        

    }
    
    
    
}