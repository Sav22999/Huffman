package Huffman;
import java.util.HashMap;

public class AlberoBinario
{
    private NodoAlberoBinario root;
    public AlberoBinario()
    {
        this.root=null;
    }
    public NodoAlberoBinario getRoot()
    {
        return this.root;
    }
    public void AggiungiNodo(NodoAlberoBinario n)
    {
        //NodoAlberoBinario n = new NodoAlberoBinario(c,f);
        if(this.root==null) this.root=n;
        else AddNodo(n, this.root);
    }
    private void AddNodo(NodoAlberoBinario n, NodoAlberoBinario p)
    {
        if(n.getInf().getFrequenza()<p.getInf().getFrequenza())
        {
            if(p.getPsx()==null) p.setPsx(n);
            else AddNodo(n, p.getPsx());
        }
        else
        {
            if(p.getPdx()==null) p.setPdx(n);
            else AddNodo(n, p.getPdx());
        }
    }
    public void visitaOA(NodoAlberoBinario p, NodoAlberoBinario prec, String lato)
    {
        if(p!=null)
        {
            if(prec!=null)
                if(lato=="sin") p.getInf().setCodice(prec.getInf().getCodice() + "1");
                else if(lato=="des") p.getInf().setCodice(prec.getInf().getCodice() + "0");
            //if(!p.getRoot()) System.out.println(p);
            visitaOA(p.getPsx(),p,"sin");//sin=sinistra || des=destra
            visitaOA(p.getPdx(),p,"des");
        }
    }
    public void visitaIO(NodoAlberoBinario p)
    {
        if(p!=null)
        {
            visitaIO(p.getPsx());
            System.out.println(p.getInf());
            visitaIO(p.getPdx());
        }
    }
    public void visitaPO(NodoAlberoBinario p)
    {
        if(p!=null)
        {
            visitaIO(p.getPdx());
            System.out.println(p.getInf());
            visitaIO(p.getPsx());
        }
    }
    public NodoAlberoBinario search(NodoAlberoBinario p, int k)
    {
        if(p!=null)
        {
            if(p.getInf().getCarattere()==k) return p;
            else if(k<p.getInf().getCarattere()) p=search(p.getPsx(),k);
            else if(k>p.getInf().getCarattere()) p=search(p.getPdx(),k);
        }
        return p;
    }
    
    private HashMap dictionary=new HashMap();
    public HashMap visitaHashMap(NodoAlberoBinario p)
    {
        if(p!=null)
        {
            if(!p.getRoot()) dictionary.put(p.getInf().getCarattere(), p.getInf().getCodice());
            visitaHashMap(p.getPsx());//sin=sinistra || des=destra
            visitaHashMap(p.getPdx());
        }
        
        /*if(prec==null)*/ return dictionary;
    }
    
    public HashMap visitaHashMapDecompressione(NodoAlberoBinario p)
    {
        if(p!=null)
        {
            String codice=p.getInf().getCodice();
            char carattere=p.getInf().getCarattere();
            //System.out.println(codice+" -- "+carattere);
            if(!p.getRoot())
            {
                dictionary.put(codice, (int)carattere);
                //System.out.println(dictionary2.get(codice));
            }
            visitaHashMapDecompressione(p.getPsx());//sin=sinistra || des=destra
            visitaHashMapDecompressione(p.getPdx());
        }
        
        /*if(prec==null)*/ return dictionary;
    }
}
