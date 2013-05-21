package classificacaosolo.org;

//Pacote da aplicacao


import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

public class CSpriteFundo
{
	//Atributos da classe
	public float iX = 0, iY = 0, iZ = 0;
	public int iQuadroAtual = 0;
	public int iAlturaQuadro = 0, iLarguraQuadro = 0;
	public int iAlturaImagem = 0, iLarguraImagem = 0;
	public int iTotalQuadros = 0;
	public int iCodTextura = 0;
	public ResultaCalculo vrFundo =  null;

	//Construtor da classe
	public CSpriteFundo(int pAlturaQuadro, int pLarguraQuadro, int pAlturaImagem, int pLarguraImagem, int pTotalQuadros, ResultaCalculo prcalculo) 
	{
		iAlturaQuadro = pAlturaQuadro;
		iLarguraQuadro = pLarguraQuadro;
		iAlturaImagem = pAlturaImagem;
		iLarguraImagem = pLarguraImagem;
		iTotalQuadros = pTotalQuadros;
		vrFundo = prcalculo;
	}



	
	public void setCodTextura(int iCod)
	{
		iCodTextura = iCod;
		//return iCodTextura;
	}

	//Gera os vetores de vŽrtices e texturas
	public void criaQuadros(GL10 vrOpenGL)
	{
		vrOpenGL.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
		vrOpenGL.glEnableClientState(GL10.GL_VERTEX_ARRAY);


		//Habilita o desenho dos vŽrtices e cria o vetor de vŽrtices
		float[] vetRectangleVertex = {0.0f,0.0f,   0.0f,iAlturaQuadro,   iLarguraQuadro,0.0f,   iLarguraQuadro, iAlturaQuadro};		
		vrOpenGL.glVertexPointer(2, GL10.GL_FLOAT, 0, generateFloatBuffer(vetRectangleVertex)); 

		//Habilita o uso de texturas e cria o vetor de coordenadas de textura 

		vrOpenGL.glTexCoordPointer(2, GL10.GL_FLOAT, 0, generateFloatBuffer(generateCoords())); 

	}

	//Metodo utilizado para gerar as coordenadas de um vetor no formato OpenGL
	FloatBuffer generateFloatBuffer(float vetCoords[])
	{
		//Cria o vetor de coordenadas
		ByteBuffer byteBuffer = ByteBuffer.allocateDirect(4 * (6 * 4));
		byteBuffer.order(ByteOrder.nativeOrder());
		FloatBuffer vertexVector = byteBuffer.asFloatBuffer();

		vertexVector.put(vetCoords);
		vertexVector.flip();

		return vertexVector;
	}

	float[] generateCoords()
	{
		//Calcula as coordenadas de textura para o quadro atual da animacao
		int iTotalColunas = iLarguraImagem / iLarguraQuadro;
		int iTotalLinhas = iAlturaImagem/ iAlturaQuadro;

		float fCoordX1 = (iQuadroAtual % iTotalColunas) * (1.0f / iTotalColunas);
		float fCoordY1 = (iQuadroAtual / iTotalColunas) * (1.0f/ iTotalLinhas);
		float fCoordX2 = fCoordX1 + 1.0f/(iLarguraImagem/iLarguraQuadro);
		float fCoordY2 = fCoordY1 + 1.0f/(iAlturaImagem/iAlturaQuadro);

		//Retorna o vetor de coordenadas
		return new float[]{fCoordX1,fCoordY2, fCoordX1,fCoordY1, fCoordX2,fCoordY2, fCoordX2, fCoordY1};
	}


	public void desenhaSprite(GL10 vrOpenGL)
	{
		//Reinicia a matriz de modelview com a matriz identidade
		vrOpenGL.glLoadIdentity(); 

		//Translada e desenha a primitiva texturizada
		vrOpenGL.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
		vrOpenGL.glTranslatef(iX, iY, iZ);

		vrOpenGL.glTexCoordPointer(2, GL10.GL_FLOAT, 0, generateFloatBuffer(generateCoords())); 
		vrOpenGL.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, 4);

	}

	public boolean testaColisao(int pX, int pY)
	{
		pY = vrFundo.vrSuperficieDesenho.getHeight() - pY;
	

		if((pY >=iY && pY <= iY + iAlturaQuadro*2))
			return true;

		return false;
		
			
	}

	public void pause()
	{
		try
		{
			Thread.sleep(2000);
		}
		catch(Exception e)
		{

		}
	}
}
