package classificacaosolo.org;

import java.lang.reflect.Array;
import java.security.Principal;
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
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

public class ClassificacaoSolo extends Activity implements OnClickListener
{
	ArrayList<String> nomesBotoes = null;
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
		super.onCreate(savedInstanceState);
		setContentView(R.layout.principal);
		
		tabelaTelaPrincipal = new TableLayout(this);      
		tabelaTelaPrincipal = (TableLayout) findViewById(R.id.tabelaPrincipal);
		

		layoutBtnGeral = new LinearLayout(this);
		layoutBtnGeral = (LinearLayout) findViewById(R.id.layoutGeral);
		//layoutBtnClassifica.setBackgroundColor(Color.WHITE);
		layoutBtnGeral.setBackgroundResource(R.drawable.triangulo);
			
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
	
	//cria caixa de dialogo de login de acesso
		protected Dialog onCreateDialog(int id) {

			
			AlertDialog dialogDetails = null;
			
			switch (id) {
			case RB_Escolha:
			LayoutInflater inflater1 = LayoutInflater.from(this);
			View dialogview1 = inflater1.inflate(R.layout.radiobutton_layout, null);
			AlertDialog.Builder dialogbuilder1 = new AlertDialog.Builder(this);
			dialogbuilder1.setTitle("Selecione uma opção de sincronização:");
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
				radioGOpcoes.check(-1);
				
				
				//evento on-click do botao da caixa de dialog 
				btnok.setOnClickListener(new View.OnClickListener() {

					public void onClick(View v) {
						alertDialog1.dismiss();

						
						switch (radioGOpcoes.getCheckedRadioButtonId()) {
						case R.id.rbMtPouco  : recebeId = 0;
						Toast.makeText(ClassificacaoSolo.this, "Muito Pouco", Toast.LENGTH_SHORT).show();
						aux = recebeId;
						break;

						case R.id.rbPouco       : recebeId = 1;
						Toast.makeText(ClassificacaoSolo.this, "Pouco", Toast.LENGTH_SHORT).show();
						aux = recebeId;
						break;

						case R.id.rbMedio  : recebeId = 2;
						Toast.makeText(ClassificacaoSolo.this, "Medio", Toast.LENGTH_SHORT).show();
						aux = recebeId;
						break;

						case R.id.rbAlto       : recebeId = 3;
						Toast.makeText(ClassificacaoSolo.this, "Alto", Toast.LENGTH_SHORT).show();
						aux = recebeId;
						break;

						case R.id.rbMtAlto         : recebeId = 4;
						Toast.makeText(ClassificacaoSolo.this, "Muito alto", Toast.LENGTH_SHORT).show();
						aux = recebeId;
						break;		
												
						}
		
					}

				});
				
				
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
				
                Intent vrIntent = new Intent(this, ResultaCalculo.class);
                vrIntent.putExtra("valor", 1);
                startActivity(vrIntent);
				
				
			}
		}
		
		
	}
}