package Huffman;
import java.util.Comparator;

public class FrequenzaComparator implements Comparator<NodoAlberoBinario>
{
    @Override
    public int compare(NodoAlberoBinario c1, NodoAlberoBinario c2)
    {
        if(c1.getInf().getFrequenza()>c2.getInf().getFrequenza()) return 1;
        else if(c1.getInf().getFrequenza()<c2.getInf().getFrequenza()) return -1;
        else return 0;
    }
}
