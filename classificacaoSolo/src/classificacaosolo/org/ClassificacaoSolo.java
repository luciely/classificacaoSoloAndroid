package classificacaosolo.org;

import java.lang.reflect.Array;
import java.security.Principal;
import java.security.cert.CRLSelector;
import java.util.ArrayList;

import dalvik.system.PathClassLoader;

import android.R.color;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.SearchManager.OnCancelListener;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.SlidingDrawer;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

public class ClassificacaoSolo extends Activity implements OnClickListener
{
	//Classificação do solo Embrapa
	private static final int E_MUITO_ARGILOSA = 0;
	private static final int E_ARGILOSA       = 1;
	private static final int E_MEDIA          = 2;
	private static final int E_ARENOSA        = 3;
	private static final int E_SILTOSA        = 4;
	
	//Variáveis linguísticas
	private static final int MUITO_POUCO = 1;
	private static final int POUCO       = 2;
	private static final int MEDIO       = 4;
	private static final int ALTO        = 8;
	private static final int MUITO_ALTO  = 16;
	
	CRegra regras[] = new CRegra[18];
	String[] sSolo = new String[5];
	CFuzzySolo cfAreia = null;
	CFuzzySolo cfSilte = null;
	CFuzzySolo cfArgila = null;
	
		
	ArrayList<String> nomesBotoes = null;
	SeekBar barra = null;
	Button[] vrBotoes = null;	
	Button btnTelaPrincipal = null;
	TableLayout tabelaTelaPrincipal = null;
	TableRow LinhaTelaPrincipal = null;
	LinearLayout layoutBtnGeral =null;
	int recebeAreia=0, recebeSilte=0, recebeArgila=0, recebeId; 
	final private static int RB_Escolha=1; 
	RadioGroup grupoOpcoes = null;
	int aux =0;
	String teste;
	LinearLayout layoutClassifica = null;
	
	
	
	
	public void onCreate(Bundle savedInstanceState)
	{
//		// Habilita novos recursos na janela  
//		  requestWindowFeature(Window.FEATURE_LEFT_ICON);
// 
//	  
//		  // Adiciona um ícone a esquerda do titulo
//		  setFeatureDrawableResource(Window.FEATURE_LEFT_ICON,
//		    R.drawable.icon);

		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.principal);
		
		tabelaTelaPrincipal = new TableLayout(this);      
		tabelaTelaPrincipal = (TableLayout) findViewById(R.id.tabelaPrincipal);
		

		layoutBtnGeral = new LinearLayout(this);
		layoutBtnGeral = (LinearLayout) findViewById(R.id.layoutGeral);
		//layoutBtnClassifica.setBackgroundColor(Color.WHITE);
		layoutBtnGeral.setBackgroundResource(R.drawable.fundo);
		
			
		layoutClassifica = new LinearLayout(this);
		layoutClassifica = (LinearLayout) findViewById(R.id.layoutClassifica);
		layoutClassifica.setPadding(120, 250, 10, 10);
		//layoutClassifica.setGravity(Gravity.CENTER);		
		
		
		
		nomesBotoes = new ArrayList();
		nomesBotoes.add("Areia");
		nomesBotoes.add("Silte");
		nomesBotoes.add("Argila");
		nomesBotoes.add("Classificar");
		
		vrBotoes = new Button[nomesBotoes.size()];
		
		grupoOpcoes = new RadioGroup(this);
		grupoOpcoes = (RadioGroup) findViewById(R.id.rgClassificacaoTextural);
		

		//inserindo linha na tabela, e o botão na linha de acordo com o nome do ArrayList 
		for(int i=0; i < nomesBotoes.size(); i++)
		{
			if(i==3)
			{
				System.out.println("últimoooooooooooo");
				btnTelaPrincipal = new Button(this);
				btnTelaPrincipal.setText(nomesBotoes.get(i).toString());
				btnTelaPrincipal.setId(i);
				btnTelaPrincipal.setOnClickListener(this);
				btnTelaPrincipal.setWidth(250);
				btnTelaPrincipal.setTextSize(16);
				btnTelaPrincipal.setGravity(Gravity.CENTER);
				vrBotoes[i] = btnTelaPrincipal;
				layoutClassifica.addView(btnTelaPrincipal);
			}
			else
			{    	  
				btnTelaPrincipal = new Button(this);
				btnTelaPrincipal.setOnClickListener(this);
				btnTelaPrincipal.setId(i);
				btnTelaPrincipal.setText(nomesBotoes.get(i).toString());
				btnTelaPrincipal.setTextSize(16);
				LinhaTelaPrincipal = new TableRow(this);
				tabelaTelaPrincipal.addView(LinhaTelaPrincipal);
				btnTelaPrincipal.setWidth(250);
				LinhaTelaPrincipal.addView(btnTelaPrincipal);
				LinhaTelaPrincipal.setGravity(Gravity.CENTER_HORIZONTAL);//Centralizando itens na linha
				LinhaTelaPrincipal.setPadding(10, 50, 10, 0);
				vrBotoes[i] = btnTelaPrincipal;
			}

		}
}
	//teste
	//cria caixa de dialogo de login de acesso
		protected Dialog onCreateDialog(int id) {

			
			AlertDialog dialogDetails = null;
			
			switch (id) {
			case RB_Escolha:
			LayoutInflater inflater1 = LayoutInflater.from(this);
			View dialogview1 = inflater1.inflate(R.layout.radiobutton_layout, null);
			AlertDialog.Builder dialogbuilder1 = new AlertDialog.Builder(this);
			dialogbuilder1.setTitle("Selecione uma das opões de quantidade");
			dialogbuilder1.setView(dialogview1);
			dialogDetails = dialogbuilder1.create();
			break;			
			}
			return dialogDetails;
	  }
		
		//metodo que pega o retorno do usuario logado
		protected void onPrepareDialog(int id, Dialog dialog) {

				///TELA DE DIALOG OPÇÕES SINCONIZA
			

				final AlertDialog alertDialog1 = (AlertDialog) dialog;
				final RadioGroup radioGOpcoes = (RadioGroup) alertDialog1.findViewById(R.id.rgClassificacaoTextural); 
				Button  btnok = (Button) alertDialog1.findViewById(R.id.btnok);
				barra = (SeekBar) findViewById(R.id.barra);
				radioGOpcoes.check(-1);
				
				
				
				
				//evento on-click do botao da caixa de dialog 
				btnok.setOnClickListener(new View.OnClickListener() {

					public void onClick(View v) {
						alertDialog1.dismiss();

						
//						switch (radioGOpcoes.getCheckedRadioButtonId()) {
//						case R.id.rbMtPouco  : recebeId = 0;
//						Toast.makeText(ClassificacaoSolo.this, "Muito Pouco", Toast.LENGTH_SHORT).show();
//						aux = recebeId;
//						break;
//
//						case R.id.rbPouco       : recebeId = 1;
//						Toast.makeText(ClassificacaoSolo.this, "Pouco", Toast.LENGTH_SHORT).show();
//						aux = recebeId;
//						break;
//
//						case R.id.rbMedio  : recebeId = 2;
//						Toast.makeText(ClassificacaoSolo.this, "Medio", Toast.LENGTH_SHORT).show();
//						aux = recebeId;
//						break;
//
//						case R.id.rbAlto       : recebeId = 3;
//						Toast.makeText(ClassificacaoSolo.this, "Alto", Toast.LENGTH_SHORT).show();
//						aux = recebeId;
//						break;
//
//						case R.id.rbMtAlto         : recebeId = 4;
//						Toast.makeText(ClassificacaoSolo.this, "Muito alto", Toast.LENGTH_SHORT).show();
//						aux = recebeId;
//						break;		
												
//						}
//		
					}

				});
				
				
			}
		
public double minimo(double a, double b)
{
	if (a<b)
	   return a;
	return b;
}

public double pertinencia(double valor, int variavelLinguistica)
{
	switch(variavelLinguistica)
	  {
	    case MUITO_POUCO:
	      if(valor < 10)
	        return 1;
	      if(valor < 25)
	        return ((25 - valor)/15);
	      return 0;
	case POUCO:
	      if(valor < 15)
	        return 0;
	      if(valor < 30)
	        return (1 - ((30 - valor)/15));
	      if(valor < 45)
	        return ((45 - valor)/15);
	      return 0;
	case MEDIO:
	      if(valor < 35)
	        return 0;
	      if(valor < 50)
	        return (1 - ((50 - valor)/15));
	      if(valor < 65)
	        return ((65 - valor)/15);
	      return 0;
	case ALTO:
	      if(valor < 55)
	        return 0;
	      if(valor < 70)
	        return (1 - ((70 - valor)/15));
	      if(valor < 85)
	        return ((85 - valor)/15);
	      return 0;
	case MUITO_ALTO:
	      if(valor < 75)
	        return 0;
	      if(valor < 90)
	        return (1 - ((90 - valor)/15));
	      return 1;	
	  }
	return 0;
}

public void criaRegra()
{
	regras[0]= new CRegra(MUITO_ALTO, MUITO_ALTO, MUITO_POUCO, E_ARENOSA);
	regras[1]= new CRegra(MUITO_ALTO, MUITO_ALTO, MUITO_POUCO, E_ARENOSA);
	regras[2]= new CRegra(MUITO_POUCO, MUITO_ALTO, POUCO, E_SILTOSA);
	regras[3]= new CRegra(MUITO_POUCO, MUITO_ALTO, POUCO, E_SILTOSA);
	regras[4]= new CRegra(POUCO, MEDIO, MEDIO, E_ARGILOSA);
	regras[5]= new CRegra(MEDIO, MEDIO, MEDIO, E_ARGILOSA);
	regras[6]= new CRegra(ALTO, MEDIO, MEDIO, E_ARGILOSA);
	regras[7]= new CRegra(MEDIO, MUITO_POUCO, MUITO_ALTO, E_MUITO_ARGILOSA);
	regras[8]= new CRegra(MEDIO, POUCO, ALTO, E_MUITO_ARGILOSA);
	regras[9]= new CRegra(MEDIO, MUITO_POUCO, ALTO, E_MUITO_ARGILOSA);
	regras[10]= new CRegra(MEDIO, POUCO, MUITO_ALTO, E_MUITO_ARGILOSA);
	regras[11]= new CRegra(POUCO, POUCO, ALTO, E_MEDIA);
	regras[12]= new CRegra(POUCO, POUCO, MUITO_ALTO, E_MEDIA);
	regras[13]= new CRegra(MEDIO, POUCO, ALTO, E_MEDIA);
	regras[14]= new CRegra(MEDIO, POUCO, MUITO_ALTO, E_MEDIA);
	regras[15]= new CRegra(ALTO, POUCO, ALTO, E_MEDIA);
	regras[16]= new CRegra(ALTO, POUCO, MUITO_ALTO, E_MEDIA);
	regras[17]= new CRegra(MUITO_ALTO, ALTO, MUITO_POUCO, E_MEDIA);
	
	
	  cfAreia = new CFuzzySolo();
	  cfSilte = new CFuzzySolo();
	  cfArgila = new CFuzzySolo();
	  
	  // Tipos de solos
	  sSolo[E_MUITO_ARGILOSA] = "Muito argiloso";
	  sSolo[E_ARGILOSA] = "Argiloso";
	  sSolo[E_MEDIA] = "Medio";
	  sSolo[E_ARENOSA] = "Arenoso";
	  sSolo[E_SILTOSA] = "Siltoso";
	  
	
}
		
public CFuzzySolo fuzzifica(CFuzzySolo prTipoSolo, double v)
{
	prTipoSolo.setdValorCrisp(v);
	
	prTipoSolo.setdMuito_Pouco(pertinencia(v, MUITO_POUCO));
	prTipoSolo.setdPouco(pertinencia(v, POUCO));
	prTipoSolo.setdMedio(pertinencia(v, MEDIO));
	prTipoSolo.setdAlto(pertinencia(v, ALTO));
	prTipoSolo.setdMuito_Alto(pertinencia(v, MUITO_ALTO));
	prTipoSolo.setiConjuntos(0);
	

	    if(prTipoSolo.getdMuito_Pouco() != 0)
	    	prTipoSolo.setiConjuntos((prTipoSolo.getiConjuntos() | MUITO_POUCO));
	    if(prTipoSolo.getdPouco() != 0)
	    	prTipoSolo.setiConjuntos((prTipoSolo.getiConjuntos() | POUCO));
	    if(prTipoSolo.getdMedio() != 0)
	    	prTipoSolo.setiConjuntos((prTipoSolo.getiConjuntos() | MEDIO));
	    if(prTipoSolo.getdAlto() != 0)
	    	prTipoSolo.setiConjuntos((prTipoSolo.getiConjuntos() | ALTO));
	    if(prTipoSolo.getdMuito_Alto() != 0)
	    	prTipoSolo.setiConjuntos((prTipoSolo.getiConjuntos() | MUITO_ALTO));
	
	    return prTipoSolo;
}

public void showRegras(CRegra prregras)
{
	for(int i=0;i<17;i++)
	{
		System.out.print("Areia: ");
		System.out.print(prregras.getiAreia());
		System.out.print(" Silte: ");
		System.out.print(prregras.getiSilte());
		System.out.print(" Argila: ");
		System.out.print(prregras.getiArgila());
		System.out.print(" Solo: ");
		System.out.print(prregras.getiSolo());
	}
	
}

public void showPertinencias(CFuzzySolo prTipoSolo, String prtipo)
{
	System.out.print("Componentes: ");
	System.out.println(prtipo);
	System.out.print("Valor: ");
	System.out.println(prTipoSolo.getdValorCrisp());
	System.out.print("Conjunto: ");
	System.out.println(prTipoSolo.getiConjuntos());
	System.out.print("Muito pouco: ");
	System.out.println(prTipoSolo.getdMuito_Pouco());
	System.out.print("Pouco: ");
	System.out.println(prTipoSolo.getdPouco());
	System.out.print("Medio: ");
	System.out.println(prTipoSolo.getdMedio());
	System.out.print("Alto: ");
	System.out.println(prTipoSolo.getdAlto());
	System.out.print("Muito alto: ");
	System.out.println(prTipoSolo.getdMuito_Alto());
	System.out.println(" ");	
}




public int defuzzifica(CRegra[] prregra, int num_regra, CFuzzySolo prAreia, CFuzzySolo prSilte, CFuzzySolo prArgila, int iClassificacao)
{
	 System.out.println("Defuzzificando... ");
	  double vFinal, vMin, vMax = 0,vAreia,vSilte, vArgila;
	  for(int i = 0; i < num_regra; i++)
	  {
		  System.out.println("Areia" + prAreia.getiConjuntos() + " - regra: " +i+ " - " + prregra[i].getiAreia() );
		  System.out.println("Silte " + prSilte.getiConjuntos() + " - regra: " +i+  " - "+prregra[i].getiSilte() );
		  System.out.println("Argila " + prSilte.getiConjuntos() + " - regra: " +i+  " - "+prregra[i].getiSilte() );
	  
		  //if((lAreia->conjuntos & regras[i].areia) && (lSilte->conjuntos & regras[i].silte) && (lArgila->conjuntos & regras[i].argila) )
	    if((prAreia.getiConjuntos() & prregra[i].getiAreia()) && (prSilte.getiConjuntos() & prregra[i].getiSilte()) && (prArgila.getiConjuntos() & prregra[i].getiArgila()))
	    {
	      switch(prregra[i].getiAreia())
	      {
	        case MUITO_POUCO: 
	          vAreia = prAreia.getdMuito_Pouco();
	          System.out.println("Muito pouco");
	        break;
	        case POUCO: 
	          vAreia = prAreia.getdPouco(); 
	          System.out.println("Pouco");
	        break;
	        case MEDIO: 
	          vAreia = prAreia.getdMedio(); 
	          System.out.println("Medio");
	        break;
	        case ALTO: 
	          vAreia = prAreia.getdAlto(); 
	          System.out.println("Alto");
	        break;
	        case MUITO_ALTO: 
	          vAreia = prAreia.getdMuito_Alto(); 
	          System.out.println("Muito alto");
	        break;
	      }
	      switch(prregra[i].getiSilte())
	      {
	        case MUITO_POUCO: 
	          vSilte =  prSilte.getdMuito_Pouco();
	          System.out.println(", Muito pouco");
	        break;
	        case POUCO: 
	          vSilte = prSilte.getdPouco();
	          System.out.println(", Pouco");
	        break;
	        case MEDIO: 
	          vSilte =prSilte.getdMedio();
	          System.out.println(", Medio");
	        break;
	        case ALTO: 
	          vSilte = prSilte.getdAlto();
	          System.out.println(", Alto");
	        break;
	        case MUITO_ALTO: 
	          vSilte = prSilte.getdMuito_Alto(); 
	          System.out.println(", Muito alto");
	        break;
	      }
	      switch(prregra[i].getiArgila())
	      {
	        case MUITO_POUCO: 
	          vArgila = prArgila.getdMuito_Pouco();
	          System.out.println(", Muito pouco: ");
	        break;
	        case POUCO: 
	          vArgila = prArgila.getdPouco(); 
	          System.out.println(", Pouco: ");
	        break;
	        case MEDIO: 
	          vArgila = prArgila.getdMedio();
	          System.out.println(", Medio: ");
	        break;
	        case ALTO: 
	          vArgila = prArgila.getdAlto();
	          System.out.println(", Alto: ");
	        break;
	        case MUITO_ALTO: 
	          vArgila =prArgila.getdMuito_Alto();
	          System.out.println(", Muito alto: ");
	        break;
	      }
	      System.out.println(sSolo[regras[i].getiSolo()]);
	      vMin = minimo(vAreia, vSilte);
	      vMin = minimo(vMin, vArgila);
	      
	      if(iClassificacao < 0)
	      {
	        vFinal = vMin;
	        i = regras[i].getiSolo();
	      }
	        
	      if(vFinal < vMin)
	      {
	        vFinal = vMin;
	        iClassificacao = regras[i].getiSolo();
	      }
	        
	    } // fim do if
	    
	  } // fim do for
	  return iClassificacao;	
}

	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		if(v instanceof Button)
		{
			//verifica qual botão está sendo clicado
			if(vrBotoes[0].getText().equals(((Button) v).getText()))
			{
				
				showDialog(RB_Escolha);
				//Toast.makeText(ClassificacaoSolo.this, "Aguarde... abrindo Site,Sistema Quantum", Toast.LENGTH_SHORT).show();
				recebeAreia = aux;
			
			}
			
			if(vrBotoes[1].getText().equals(((Button) v).getText()))
			{
				
				showDialog(RB_Escolha);
				//Toast.makeText(ClassificacaoSolo.this, "Aguarde..." Toast.LENGTH_SHORT).show();
				recebeSilte = aux;
				
			}

			if(vrBotoes[2].getText().equals(((Button) v).getText()))
			{
				showDialog(RB_Escolha);
				//Toast.makeText(ClassificacaoSolo.this, "Aguarde...", Toast.LENGTH_SHORT).show();
				//Toast.makeText(ClassificacaoSolo.this,"Imprime Areia: "+recebeAreia+ "Silte: "+recebeSilte+ "Argila: "+recebeArgila, Toast.LENGTH_SHORT).show();
				teste = ("Imprime Areia: "+recebeAreia+ "Silte: "+recebeSilte+ "Argila: "+recebeArgila);
				Toast.makeText(ClassificacaoSolo.this,teste, Toast.LENGTH_SHORT).show();
				recebeArgila = aux;
				
				
			}

			if(vrBotoes[3].getText().equals(((Button) v).getText()))
			{
				System.out.println("ID: "+ v.getId());
				//calcula atraves da logica fuzzy
				
				int classifica=-1;
				
				criaRegra();				
				fuzzifica(cfAreia, 93);
				fuzzifica(cfSilte, 87);
				fuzzifica(cfArgila, 9);				
				showPertinencias(cfAreia, "Areia");
				showPertinencias(cfSilte, "Silte");
				showPertinencias(cfArgila, "Argila");
				
				defuzzifica(regras, 18, cfAreia, cfSilte, cfArgila,classifica);
				
                Intent vrIntent = new Intent(this, ResultaCalculo.class);
                vrIntent.putExtra("valor", 1);
                startActivity(vrIntent);
				
				
			}
		}
		
		
	}
}