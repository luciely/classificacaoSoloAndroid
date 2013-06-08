//package classificacaosolo.org;
//
//import android.app.Activity;
//import android.os.Bundle;
//import android.widget.SeekBar;
//
//public class SeekBar extends Activity
//{
//	
//	SeekBar barra = null;
//
//	@Override
//	protected void onCreate(Bundle savedInstanceState) {
//		// TODO Auto-generated method stub
//		super.onCreate(savedInstanceState);
//		
//		setContentView(R.layout.seekbar_layout);
//		 
//		barra = new SeekBar();
//		
//        barra = (SeekBar) findViewById(R.id.barra);
// 
//        barra.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
//            int progressChanged = 0;
// 
//            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser){
//                progressChanged = progress;
//            }
// 
//            public void onStartTrackingTouch(SeekBar seekBar) {
//                // TODO Auto-generated method stub
//            }
// 
//            public void onStopTrackingTouch(SeekBar seekBar) {
//                Toast.makeText(SeekbarActivity.this,"seek bar progress:"+progressChanged, 
//                        Toast.LENGTH_SHORT).show();
//            }
//        });
// 
//    }
// 
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.seekbar, menu);
//        return true;
//    }
//	}
//
//	
//}
