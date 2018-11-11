package Huffman;

public class NodoAlberoBinario
{
    private NodoAlberoBinario psx, pdx;
    private Carattere inf;
    private boolean root;
    public NodoAlberoBinario(char inf, int freq)
    {
        Carattere car = new Carattere(inf);
        car.setFrequenza(freq);
        this.inf=car;
        this.psx=null;
        this.pdx=null;
        this.root=false;
    }
    public NodoAlberoBinario(Carattere car)
    {
        this.inf=car;
        this.psx=null;
        this.pdx=null;
        this.root=false;
    }
    public void setInf(Carattere inf)
    {
        this.inf=inf;
    }
    public Carattere getInf()
    {
        return this.inf;
    }
    public void setPsx(NodoAlberoBinario p)
    {
        this.psx=p;
    }
    public NodoAlberoBinario getPsx()
    {
        return this.psx;
    }
    public void setPdx(NodoAlberoBinario p)
    {
        this.pdx=p;
    }
    public NodoAlberoBinario getPdx()
    {
        return this.pdx;
    }
    
    public void rootSI()
    {
        this.root=true;
    }
    public void rootNO()
    {
        this.root=false;
    }
    public boolean getRoot()
    {
        return this.root;
    }
    
    public String toString()
    {
        StringBuffer sb=new StringBuffer();
        sb.append(this.getInf().getFrequenza());
        sb.append(" × ");
        sb.append(this.getInf().getCarattere());
        sb.append(" - ");
        sb.append(this.getInf().getCodice());
        return sb.toString();
    }
}
