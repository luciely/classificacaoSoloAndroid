package classificacaosolo.org;

import java.util.ArrayList;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Criteria;
import android.opengl.GLSurfaceView;
import android.opengl.GLSurfaceView.Renderer;
import android.opengl.GLUtils;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

public class ResultaCalculo extends Activity
{

	float X =0.0f,Y=0.0f, Z=0.0f;
    private int pegaResultado=0;

	
	public GLSurfaceView vrSuperficieDesenho = null;
	private RenderInicio vrRender = null;

	
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);


		//Preparacao da janela da aplica‹o (modo tela cheia)
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
				                  WindowManager.LayoutParams.FLAG_FULLSCREEN);

		vrSuperficieDesenho = new GLSurfaceView(this);
		vrRender = new RenderInicio(this);
		vrSuperficieDesenho.setRenderer(vrRender);
				
		setPegaResultado((Integer) getIntent().getSerializableExtra("valor"));		
		setContentView(vrSuperficieDesenho);
		
		Toast toast = Toast.makeText(ResultaCalculo.this, "A classificação textural do solo é: Argilosa.",  Toast.LENGTH_SHORT);
		toast.setGravity(Gravity.TOP | Gravity.CENTER_VERTICAL, 0, 0);
		toast.show();
		
}


	int getPegaResultado() {
		return pegaResultado;
	}


	void setPegaResultado(int pegaResultado) {
		this.pegaResultado = pegaResultado;
	}



}


class RenderInicio implements Renderer
{

	private ResultaCalculo vrActivity = null;

	private CSpriteFundo[] planoFundo = null;

	private CSpriteFundo[] Ponto = null;

//	private CSpriteFundo[] Asteroid = null;
//
//	private CSpriteFundo Tiro = null;
//
//	private CSpriteFundo[] Over = null;
//
//	private ArrayList<CSpriteFundo> Tiro2 = null;
//
//	public boolean controle = false;
//	
//	public boolean finaliza = true;
	

	public RenderInicio(ResultaCalculo pActivity) 
	{

		vrActivity = pActivity;

		//		vrActivity.vrSuperficieDesenho.setOnTouchListener(this);

		//seta tamanho do quadro dos sprites
		planoFundo = new CSpriteFundo[1];
		Ponto = new CSpriteFundo[2];
//		Asteroid = new CSpriteFundo[1];
//		
//		Tiro2 = new ArrayList<CSpriteFundo>(); 
//		Over = new CSpriteFundo[1];

		planoFundo[0] = new CSpriteFundo(700, 500, 700, 500, 1, vrActivity);
		planoFundo[0].iQuadroAtual =0;

		Ponto[0] = new CSpriteFundo(24,24 , 48, 24, 2, vrActivity);
		Ponto[0].iQuadroAtual =0;
//		Ponto[1] = new CSpriteFundo(12, 32, 24, 32, 2, vrActivity);
//		Ponto[1].iQuadroAtual =1;

//		Asteroid[0] = new CSpriteFundo(32, 32, 32, 32, 1, vrActivity);
//		Asteroid[0].iQuadroAtual=0;
//
//		Tiro = new CSpriteFundo(32, 8, 32, 8, 1, vrActivity);	
//		Tiro.iQuadroAtual=0;
//
//		Over[0] = new CSpriteFundo(500, 400, 512, 512, 1, vrActivity);
//		Over[0].iQuadroAtual=0;

	}
	
	public void setaPonto(int result)
	{

		Ponto[0].iQuadroAtual = result;
	
	}
	

	public void onSurfaceCreated(GL10 vrOpenGl, EGLConfig arg1)		
	{	

		vrOpenGl.glEnable(GL10.GL_TEXTURE_2D);		   

		//Habilita e configura a TRANSPARENCIA
		vrOpenGl.glEnable(GL10.GL_BLEND);
		vrOpenGl.glEnable(GL10.GL_ALPHA_TEST);
		vrOpenGl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);
		vrOpenGl.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
		

		//vrOpenGl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);

		vrOpenGl.glViewport(0, 0, vrActivity.vrSuperficieDesenho.getWidth(), 
				vrActivity.vrSuperficieDesenho.getHeight());   


		vrOpenGl.glMatrixMode(GL10.GL_PROJECTION);
		vrOpenGl.glLoadIdentity();
		vrOpenGl.glOrthof(0.0F, vrActivity.vrSuperficieDesenho.getWidth(),
				          0.0f, vrActivity.vrSuperficieDesenho.getHeight(), -1.0f, 1.0f);
		vrOpenGl.glMatrixMode(GL10.GL_MODELVIEW);

		
		//seta imagem no quadro dos sprites
		planoFundo[0].criaQuadros(vrOpenGl);
		planoFundo[0].iX = 0;
		planoFundo[0].iY =0;
		//planoFundo[0].iX = vrActivity.vrSuperficieDesenho.getWidth();
		//planoFundo[0].iY = vrActivity.vrSuperficieDesenho.getHeight();	     
		planoFundo[0].setCodTextura(carregaImagem(vrOpenGl, R.drawable.tringu));

		Ponto[0].criaQuadros(vrOpenGl);
//		Nave[0].iX = 0;
//		Nave[0].iY = 0;
		Ponto[0].iX = vrActivity.vrSuperficieDesenho.getWidth()/2;
		Ponto[0].iY = vrActivity.vrSuperficieDesenho.getHeight()/2;	     
		Ponto[0].setCodTextura(carregaImagem(vrOpenGl, R.drawable.bola));
		
//		Ponto[1].criaQuadros(vrOpenGl);
////		Nave[0].iX = 0;
////		Nave[0].iY = 0;
//		Ponto[1].iX = vrActivity.vrSuperficieDesenho.getWidth()/2;
//		Ponto[1].iY = vrActivity.vrSuperficieDesenho.getHeight()/2;	     
//		Ponto[1].setCodTextura(carregaImagem(vrOpenGl, R.drawable.ponto1));

//		Asteroid[0].criaQuadros(vrOpenGl);
//		Asteroid[0].iX= vrActivity.vrSuperficieDesenho.getWidth()/3;
//		Asteroid[0].iY= vrActivity.vrSuperficieDesenho.getHeight()/3;
//	    Asteroid[0].setCodTextura(carregaImagem(vrOpenGl, R.drawable.aster));
//
//		Tiro.criaQuadros(vrOpenGl);
//		Tiro.iX=0;
//		Tiro.iY=0;
//		Tiro.setCodTextura(carregaImagem(vrOpenGl, R.drawable.shut));

	}
//
//	//desenha tiro na tela
//	public void desenhaTiro(GL10 vrOpenGl)
//	{
//		Tiro.iX = Nave[0].iX+50;
//		Tiro.iY = Nave[0].iY+30;
//
//		CSpriteFundo Tiro3;
//		Tiro3 = new CSpriteFundo(32, 16, 32, 16, 1, vrActivity);
//		Tiro3.iQuadroAtual=0;
//		Tiro3.iX= Nave[0].iX+50;
//		Tiro3.iY= Nave[0].iY+30;
//		Tiro3.setCodTextura(carregaImagem(vrOpenGl, R.drawable.shut));
//
//		Tiro2.add(Tiro3);	
//
//		controle = false;
//
//	}
//
//	//sprite da tela de game over
//    public void gameOver(GL10 vrOpenGl)
//    {
//
//
//		Over[0].criaQuadros(vrOpenGl);
//		Over[0].iX=vrActivity.vrSuperficieDesenho.getWidth()/4-80;
//		Over[0].iY=vrActivity.vrSuperficieDesenho.getHeight()/4-90;
//		vrOpenGl.glEnable(GL10.GL_TEXTURE_2D);
//		vrOpenGl.glBindTexture(GL10.GL_TEXTURE_2D, Over[0].iCodTextura);
//		Over[0].desenhaSprite(vrOpenGl);
//		//Over[0].setCodTextura(carregaImagem(vrOpenGl, R.drawable.finish));
//			
//	}
//    

	public void onDrawFrame(GL10 vrOpenGl)
	{	
		
		vrOpenGl.glClear(GL10.GL_COLOR_BUFFER_BIT);


		planoFundo[0].criaQuadros(vrOpenGl);
		vrOpenGl.glEnable(GL10.GL_TEXTURE_2D);
		vrOpenGl.glBindTexture(GL10.GL_TEXTURE_2D, planoFundo[0].iCodTextura);
		planoFundo[0].desenhaSprite(vrOpenGl);
		
				
		Ponto[0].criaQuadros(vrOpenGl);
		vrOpenGl.glEnable(GL10.GL_TEXTURE_2D);
        vrOpenGl.glBindTexture(GL10.GL_TEXTURE_2D, Ponto[0].iCodTextura);
		Ponto[0].desenhaSprite(vrOpenGl);
		//vrOpenGl.glDisable(GL10.GL_TEXTURE_2D);
		String teste = "A classificação textural do solo é: Argilosa.";
		//Toast.makeText(ResultaCalculo, "Muito Pouco", Toast.LENGTH_SHORT).show();
	
		try {	
			Thread.sleep(500);
			if(Ponto[0].iQuadroAtual==0)
			{
				Ponto[0].iQuadroAtual =1;
							}
			else
				Ponto[0].iQuadroAtual =0;
			} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


}

	public void onSurfaceChanged(GL10 vrOpenGl, int arg1, int arg2)
	{
		// TODO Auto-generated method stub

	}
	
    //metodo para carregar imagens
	private int carregaImagem(GL10 vrOpenGl, int iCodImagem)
	{
		//Carrega a imagem bitmap para a mem—ria RAM
		Bitmap vrImage = BitmapFactory.decodeResource(vrActivity.getResources(), iCodImagem);

		//Gera o identificador da textura e cria a ‡rea da textura
		int[] textureId = new int[1];
		vrOpenGl.glGenTextures(1, textureId, 0);

		//Cria a mem—ria da textura na VRAM e associa ao seu identificador
		vrOpenGl.glBindTexture(GL10.GL_TEXTURE_2D, textureId[0]);

		//Envia o bitpmap para a ‡rea da textura na VRAM
		GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, vrImage, 0);

		//Define os filtros de textura
		vrOpenGl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER, GL10.GL_NEAREST);
		vrOpenGl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_NEAREST);


		//Sai do modo textura e libera a imagem do Bitmap na RAM
		vrOpenGl.glBindTexture(GL10.GL_TEXTURE_2D, 0);
		vrImage.recycle();
		vrOpenGl.glBindTexture(GL10.GL_TEXTURE_2D, textureId[0]);

		return textureId[0];
	}

}




