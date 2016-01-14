package cs.purdue.lab10;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Main extends Activity {
	private static final int CAMERA_PIC_REQUEST = 2500;
	ImageView im;
	Bitmap bitmap;
	TextView text;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		im = (ImageView) findViewById(R.id.ImageView01);
		text = (TextView) findViewById(R.id.txt);
		
		text.setText(ImageManipulation.user_id);

		Button b =  (Button) findViewById(R.id.b1);
		b.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent cameraIntent = new Intent(
						android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
				startActivityForResult(cameraIntent, CAMERA_PIC_REQUEST);
			}
		});
		
		Button invert =  (Button) findViewById(R.id.invert);
		invert.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				if(bitmap!=null)
				im.setImageBitmap(ImageManipulation.invert(bitmap));
			}
		});
		
		Button leftShift =  (Button) findViewById(R.id.left);
		leftShift.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				if(bitmap!=null)
				im.setImageBitmap(ImageManipulation.leftShift(bitmap));
			}
		});
		
		Button rightShift =  (Button) findViewById(R.id.right);
		rightShift.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				if(bitmap!=null)
				im.setImageBitmap(ImageManipulation.rightShift(bitmap));
			}
		});
		
		Button grey =  (Button) findViewById(R.id.grey);
		grey.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				if(bitmap!=null)
				im.setImageBitmap(ImageManipulation.greyScale(bitmap));
			}
		});
		
	}
	
	public void onResume(){
		super.onResume();
		text.setText(ImageManipulation.user_id);
	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == CAMERA_PIC_REQUEST) {
			Bitmap image = (Bitmap) data.getExtras().get("data");
			bitmap = image.copy(Bitmap.Config.ARGB_8888, true);
			im.setImageBitmap(image);
		}
	}
}
