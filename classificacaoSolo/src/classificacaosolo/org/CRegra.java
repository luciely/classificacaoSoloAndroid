package classificacaosolo.org;

public class CRegra 
{
	private int iAreia;
	private int iSilte;
	private int iArgila;
	private int iSolo;
	
	public CRegra() {
		iAreia = -1;
		iSilte = -1;
		iArgila = -1;
		iSolo = -1;
	}

	public CRegra(int prAreia, int prSilte, int prArgila, int prSolo)
	{
		iAreia = prAreia;
		iSilte = prSilte;
		iArgila = prArgila;
		iSolo = prSolo;
	}
	
	public int getiAreia() {
		return iAreia;
	}

	public void setiAreia(int iAreia) {
		this.iAreia = iAreia;
	}

	public int getiSilte() {
		return iSilte;
	}

	public void setiSilte(int iSilte) {
		this.iSilte = iSilte;
	}

	public int getiArgila() {
		return iArgila;
	}

	public void setiArgila(int iArgila) {
		this.iArgila = iArgila;
	}

	public int getiSolo() {
		return iSolo;
	}

	public void setiSolo(int iSolo) {
		this.iSolo = iSolo;
	}
	
	
	
	
	
	
	
}
