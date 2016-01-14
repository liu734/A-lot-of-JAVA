package fall2011.lab02;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.ImageView;

class AnimateText extends ImageView{

	int voff = 200;
	int vof = 30;
	int color = Color.BLUE;
	
	public AnimateText(Context context, AttributeSet attrs) {
		super(context);
		setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT,ViewGroup.LayoutParams.FILL_PARENT));
        new Thread(new Runnable(){
			@Override
			public void run() {
				try {Thread.sleep(1000);}catch (InterruptedException e) {e.printStackTrace();}
				while(true){
					try {Thread.sleep(32);}catch (InterruptedException e) {e.printStackTrace();}
					AnimateText.this.postInvalidate();
				}
			}
        }).start();
	}
	
	public AnimateText(Context context) {
		super(context);
		setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT,ViewGroup.LayoutParams.FILL_PARENT));
        new Thread(new Runnable(){
			@Override
			public void run() {
				try {Thread.sleep(1000);}catch (InterruptedException e) {e.printStackTrace();}
				while(true){
					try {Thread.sleep(32);}catch (InterruptedException e) {e.printStackTrace();}
					AnimateText.this.postInvalidate();
				}
			}
        }).start();
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		// create a path
		Path circle = new Path();
		circle.addCircle(getWidth()/2, getHeight()/2, 200, Direction.CW);

		// set the color and font size
		Paint paint = new Paint();
		paint.setColor(color);
		paint.setTextSize(50);
		paint.setAntiAlias(true);

		// draw the text along the circle
		canvas.drawTextOnPath(LAB02.text, circle, voff, vof, paint);
		voff+=2;
		//vof+=2;
		
		if(voff>900){
			voff=0;
			vof=30;
		}
		
	}
	
	public void setColor(int c){
		color = c;
	}
	
	
}
