package Huffman;
import java.util.Comparator;

public class Carattere /*implements Comparable<Carattere>*/
{
    private char Carattere;
    private int Frequenza;
    private String Codice;
    public Carattere(char k)
    {
        this.Carattere=k;
        this.Frequenza=1;
        this.Codice="";
    }
    
    public void setCarattere(char k)
    {
        this.Carattere=k;
    }
    public char getCarattere()
    {
        return this.Carattere;
    }
    public void setFrequenza(int n)
    {
        this.Frequenza=n;
    }
    public int getFrequenza()
    {
        return this.Frequenza;
    }
    public void incFrequenza()
    {
        this.Frequenza++;
    }
    public void setCodice(String Codice)
    {
        this.Codice=Codice;
    }
    public String getCodice()
    {
        return this.Codice;
    }
    /*
    public int compareTo(Carattere N)
    {
        return this.N.compareTo(N.getN());//retituisce maggiore, minore o uguale a 0
    }
    */
    public String toString()
    {
        StringBuffer sb=new StringBuffer();
        sb.append(this.Frequenza);
        sb.append(" × ");
        sb.append(this.Carattere);
        sb.append(" - ");
        sb.append(this.getCodice());
        return sb.toString();
    }
}