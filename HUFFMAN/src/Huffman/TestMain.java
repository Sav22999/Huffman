package Huffman;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.File;
import java.io.Reader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Writer;
import java.io.PrintWriter;

public class TestMain
{
    public static void main(String[] args) throws FileNotFoundException, IOException
    {
        System.out.println("\n-------------------------------------\nCOMPRESSIONE:\n--------------------------------------\n");
        //COMPRESSIONE FILE
        
        Leggi l = new Leggi();
        AlberoBinario albero = new AlberoBinario();
        ArrayList<NodoAlberoBinario> array=new ArrayList<>();
        HashMap caratteri_hm=new HashMap();
        HashMap hm=new HashMap();
        try(FileReader file = new FileReader("\\4^anno\\AREA DI PROGETTO\\HUFFMAN\\HUFFMAN\\src\\Huffman\\testo.txt"))
        {
            int x=file.read();//carattere
            while(x!=-1)
            {
                boolean inserito=false;
                for(int i=0;i<array.size()&&!inserito&&!array.isEmpty();i++)
                {
                    if(array.get(i).getInf().getCarattere()==x)
                    {
                        array.get(i).getInf().incFrequenza();
                        inserito=true;
                    }
                }
                if(array.isEmpty() || !inserito)
                {
                    Carattere car=new Carattere((char)x);
                    NodoAlberoBinario n=new NodoAlberoBinario(car);
                    array.add(n);
                    caratteri_hm.put(caratteri_hm.size(), (char)x);
                }
                x=file.read();
            }
        } //carattere
        
        Collections.sort(array,new FrequenzaComparator());//ordinamento crescente
        
        //crea il file contenente la frequenza ed i caratteri
        try (FileWriter file_carfreq = new FileWriter("\\4^anno\\AREA DI PROGETTO\\HUFFMAN\\HUFFMAN\\src\\Huffman\\testo_carfreq.!txt"))
        {
            for(int i=0;i<array.size();i++)
            {
                file_carfreq.write((int)array.get(i).getInf().getCarattere() + " " + array.get(i).getInf().getFrequenza() + "\n");
            }
        }
        
        boolean termina=false;
        while(array.size()>=2 && !termina)
        {
            if(array.size()==2)
            {
                Collections.sort(array,new FrequenzaComparator());//ordinamento crescente
                NodoAlberoBinario sx = array.get(0);
                NodoAlberoBinario dx = array.get(1);
                NodoAlberoBinario n = new NodoAlberoBinario('^',(array.get(0).getInf().getFrequenza()+array.get(1).getInf().getFrequenza()));
                n.rootSI();
                n.setPsx(sx);
                n.setPdx(dx);
                array.remove(array.get(0));
                array.remove(array.get(0));
                array.add(n);
                albero.AggiungiNodo(n);
                Collections.sort(array,new FrequenzaComparator());//ordinamento crescente
                //System.out.println(array);
                termina=true;
            }
            else
            {
                char c='*';
                Collections.sort(array,new FrequenzaComparator());//ordinamento crescente
                NodoAlberoBinario sx = array.get(0);
                NodoAlberoBinario dx = array.get(1);
                NodoAlberoBinario n = new NodoAlberoBinario(c,(array.get(0).getInf().getFrequenza()+array.get(1).getInf().getFrequenza()));
                n.rootSI();
                n.setPsx(sx);
                n.setPdx(dx);
                array.remove(array.get(0));
                array.remove(array.get(0));
                array.add(n);
                Collections.sort(array,new FrequenzaComparator());//ordinamento crescente
                //System.out.println(array);
            }
        }
        
        //System.out.println("Albero:");
        albero.visitaOA(albero.getRoot(),null,null);
        hm.putAll(albero.visitaHashMap(albero.getRoot()));
        
        BufferedReader br;
        //try(FileWriter file_temp = new FileWriter("\\4^anno\\AREA DI PROGETTO\\HUFFMAN\\HUFFMAN\\src\\Huffman\\testo_temporaneo.!txt"))
        try(Writer file_temp = new BufferedWriter(new FileWriter("\\4^anno\\AREA DI PROGETTO\\HUFFMAN\\HUFFMAN\\src\\Huffman\\testo_temporaneo.!txt")))
        {
            br = new BufferedReader(new FileReader("\\4^anno\\AREA DI PROGETTO\\HUFFMAN\\HUFFMAN\\src\\Huffman\\testo.txt"));
            String linea_file="";
            Boolean primavolta=true;
            int righe=0;
            BufferedReader rbr=new BufferedReader(new FileReader("\\4^anno\\AREA DI PROGETTO\\HUFFMAN\\HUFFMAN\\src\\Huffman\\testo.txt"));
            while(rbr.ready())
            {
                righe++;
                rbr.readLine();
            }
            while(br.ready())
            {
                if(righe>1) if(!primavolta) file_temp.write(hm.get((char)13)+"");
                //System.out.println(hm.get((char)13)+"");
                primavolta=false;
                linea_file=br.readLine();
                for(int i=0;i<caratteri_hm.size();i++)
                {
                    /*(char)Integer.parseInt(hm.get(caratteri_hm.get(i))+"",2)*/
                    linea_file=linea_file.replaceAll(caratteri_hm.get(i)+"", hm.get(caratteri_hm.get(i))+"");
                }
                file_temp.write(linea_file);
            }
        }
        br.close();
        
        try(FileWriter file_compresso = new FileWriter("\\4^anno\\AREA DI PROGETTO\\HUFFMAN\\HUFFMAN\\src\\Huffman\\testo_compresso.txt");FileReader file = new FileReader("\\4^anno\\AREA DI PROGETTO\\HUFFMAN\\HUFFMAN\\src\\Huffman\\testo_temporaneo.!txt"))
        {/*
            int x=file.read();//carattere
            String s_bit="";
            while(x!=-1)//fino a quando il file non è finito
            {
                if(s_bit.length()<8)
                {
                    s_bit=s_bit+""+(char)x;
                    //System.out.println(s_bit + "  " + (char)x);
                }
                if(s_bit.length()==8)
                {
                    //System.out.println(Integer.parseInt(s_bit,2) + "    " + (char)Integer.parseInt(s_bit,2));
                    file_compresso.write((char)Integer.parseInt(s_bit,2)+"");
                    s_bit="";
                }
                x=file.read();
            }*/
            /*
            if(s_bit.length()>0)
            {
                String ultima_stringa_binaria=s_bit;
                while(ultima_stringa_binaria.length()<8) ultima_stringa_binaria="0"+ultima_stringa_binaria;
                file_compresso.write((char)Integer.parseInt(ultima_stringa_binaria,2)+"");
                //System.out.println(s_bit+" <> "+ultima_stringa_binaria);
                s_bit="";
            }*/
            int x=file.read();//carattere
            String s_bit="";
            int n_bit=0,n_byte=0,n_bit_restanti=0;
            while(x!=-1)//fino a quando il file non è finito
            {
                if(n_bit<8)
                {
                    n_bit++;
                    s_bit=s_bit+""+(char)x;
                    //System.out.println(s_bit + "  " + (char)x);
                }
                
                if(n_bit==8)
                {
                    n_bit=0;
                    //System.out.println(Integer.parseInt(s_bit,2) + "    " + (char)Integer.parseInt(s_bit,2));
                    file_compresso.write((char)Integer.parseInt(s_bit,2)+"");
                    s_bit="";
                    n_byte++;
                }
                x=file.read();
            }
            if(n_bit>0)
            {
                n_bit_restanti=n_bit;
                while(n_bit<8)
                {
                    s_bit=""+s_bit;
                    n_bit++;
                }
                //System.out.println(s_bit + "  " + (char)x);
                file_compresso.write((char)Integer.parseInt(s_bit,2));
                
            }
            try (FileWriter file_carfreq = new FileWriter("\\4^anno\\AREA DI PROGETTO\\HUFFMAN\\HUFFMAN\\src\\Huffman\\testo_carfreq.!txt", true);PrintWriter file_da_aggiornare=new PrintWriter(file_carfreq))
            {
                //VIENE AGGIUNTO UN . INZIALE (ALLA RIGA) CHE STA AD INDICARE CHE CIO' CHE SEGUE SONO IL NUMERO DEI BYTE FORMATI
                file_da_aggiornare.append("."+n_byte+"\n");
            }try (FileWriter file_carfreq = new FileWriter("\\4^anno\\AREA DI PROGETTO\\HUFFMAN\\HUFFMAN\\src\\Huffman\\testo_carfreq.!txt", true);PrintWriter file_da_aggiornare=new PrintWriter(file_carfreq))
            {
                //VIENE AGGIUNTO UN - INIZIALE (ALLA RIGA) CHE STA AD INDICARE CHE CIO' CHE SEGUE E' L'ULTIMO BYTE INCOMPLETO, QUINDI DI NON 8 BIT
                //System.out.println(n_bit_restanti);
                file_da_aggiornare.append("-"+n_bit_restanti);
            }
        }
        System.out.println("Compressione riuscita!");
        
        System.out.println("\n-------------------------------------\nDECOMPRESSIONE:\n--------------------------------------\n");
        //DECOMPRESSIONE FILE
        
        AlberoBinario albero_dec=new AlberoBinario();
        BufferedReader br_dec=new BufferedReader(new FileReader("\\4^anno\\AREA DI PROGETTO\\HUFFMAN\\HUFFMAN\\src\\Huffman\\testo_carfreq.!txt"));
        HashMap hm_dec=new HashMap();
        ArrayList<NodoAlberoBinario> array_dec=new ArrayList();
        HashMap hm_dec_carfreq=new HashMap();
        String linea_file_carfreq="";
        int CODICE_RESTANTE_BINARIO=0, NUMERO_BYTE_DA_8_BIT=0;
        //creazione dizionari e dell'array list
        try(FileReader file = new FileReader("\\4^anno\\AREA DI PROGETTO\\HUFFMAN\\HUFFMAN\\src\\Huffman\\testo.txt"))
        {
            while(br_dec.ready())
            {
                linea_file_carfreq=br_dec.readLine();
                if(!linea_file_carfreq.contains(".") && !linea_file_carfreq.contains("-"))
                {
                    int n_freq=0;
                    boolean fine_carfreq_dec=false;
                    for(int i=0;i<linea_file_carfreq.length()&&!fine_carfreq_dec;i++)
                    {
                        if(linea_file_carfreq.charAt(i)==' ')
                        {
                            fine_carfreq_dec=true;
                            n_freq=i+1;
                        }
                    }
                    char carattere=(char)Integer.parseInt(linea_file_carfreq.substring(0, n_freq-1));
                    int frequenza=Integer.parseInt(linea_file_carfreq.substring(n_freq));
                    hm_dec.put(carattere, frequenza);
                    hm_dec_carfreq.put(hm_dec_carfreq.size(), carattere);

                    Carattere car=new Carattere(carattere);
                    car.setFrequenza(frequenza);
                    NodoAlberoBinario n=new NodoAlberoBinario(car);
                    array_dec.add(n);
                }else if(linea_file_carfreq.contains("."))
                {
                    linea_file_carfreq=linea_file_carfreq.replace(".", "");
                    //System.out.println(linea_file_carfreq);
                    NUMERO_BYTE_DA_8_BIT=Integer.parseInt(linea_file_carfreq);
                    //System.out.println(CODICE_RESTANTE_BINARIO);
                }else if(linea_file_carfreq.contains("-"))
                {
                    linea_file_carfreq=linea_file_carfreq.replace("-", "");
                    //System.out.println(linea_file_carfreq);
                    CODICE_RESTANTE_BINARIO=Integer.parseInt(linea_file_carfreq);
                    //System.out.println(CODICE_RESTANTE_BINARIO);
                }
            }
        }
        Collections.sort(array_dec,new FrequenzaComparator());//ordinamento crescente
        termina=false;
        while(array_dec.size()>=2 && !termina)
        {
            if(array_dec.size()==2)
            {
                Collections.sort(array_dec,new FrequenzaComparator());//ordinamento crescente
                NodoAlberoBinario sx = array_dec.get(0);
                NodoAlberoBinario dx = array_dec.get(1);
                NodoAlberoBinario n = new NodoAlberoBinario('^',(array_dec.get(0).getInf().getFrequenza()+array_dec.get(1).getInf().getFrequenza()));
                n.rootSI();
                n.setPsx(sx);
                n.setPdx(dx);
                array_dec.remove(array_dec.get(0));
                array_dec.remove(array_dec.get(0));
                array_dec.add(n);
                albero_dec.AggiungiNodo(n);
                Collections.sort(array_dec,new FrequenzaComparator());//ordinamento crescente
                //System.out.println(array_dec);
                termina=true;
            }
            else
            {
                char c='*';
                /*if(array.size()==4) c='@';
                else if(array.size()==3) c='#';*/
                Collections.sort(array_dec,new FrequenzaComparator());//ordinamento crescente
                NodoAlberoBinario sx = array_dec.get(0);
                NodoAlberoBinario dx = array_dec.get(1);
                NodoAlberoBinario n = new NodoAlberoBinario(c,(array_dec.get(0).getInf().getFrequenza()+array_dec.get(1).getInf().getFrequenza()));
                n.rootSI();
                n.setPsx(sx);
                n.setPdx(dx);
                array_dec.remove(array_dec.get(0));
                array_dec.remove(array_dec.get(0));
                array_dec.add(n);
                Collections.sort(array_dec,new FrequenzaComparator());//ordinamento crescente
                //System.out.println(array_dec);
            }
        }
        
        //System.out.println("Albero:");
        HashMap hm_dec_finale=new HashMap();
        albero_dec.visitaOA(albero_dec.getRoot(),null,null);
        hm_dec_finale.putAll(albero_dec.visitaHashMapDecompressione(albero_dec.getRoot()));
        
        try(FileWriter file_temporaneo_dec = new FileWriter("\\4^anno\\AREA DI PROGETTO\\HUFFMAN\\HUFFMAN\\src\\Huffman\\testo_temporaneo_decompressione.!txt");FileReader file_dec = new FileReader("\\4^anno\\AREA DI PROGETTO\\HUFFMAN\\HUFFMAN\\src\\Huffman\\testo_compresso.txt");FileWriter file_decompresso = new FileWriter("\\4^anno\\AREA DI PROGETTO\\HUFFMAN\\HUFFMAN\\src\\Huffman\\testo_decompresso.txt"))
        {
            int x=file_dec.read();//carattere dal file di orgine della decompressione --> quindi dal file compresso
            int numero_max_di_8_bit=NUMERO_BYTE_DA_8_BIT;
            int numero_di_8_bit=0;
            while(x!=-1)
            {
                    String bin8bit=Integer.toBinaryString(x);
                    if(numero_di_8_bit<numero_max_di_8_bit) while(bin8bit.length()<8) bin8bit="0"+bin8bit;
                    else while(bin8bit.length()<CODICE_RESTANTE_BINARIO) bin8bit="0"+bin8bit;
                    numero_di_8_bit++;
                    //System.out.println(bin8bit);
                    file_temporaneo_dec.write(bin8bit);
                    //System.out.println(x + " ---- " + bin8bit);
                x=file_dec.read();
            }
            //FILE DECOMPRESSO TEMPORANEO (BINARIO) COMPLETATO
            file_temporaneo_dec.close();
            file_dec.close();
            
            try(FileReader file_temporaneo_reader_dec = new FileReader("\\4^anno\\AREA DI PROGETTO\\HUFFMAN\\HUFFMAN\\src\\Huffman\\testo_temporaneo_decompressione.!txt"))
            {
                int i=file_temporaneo_reader_dec.read();
                //System.out.println((char)i);
                String stringa_binaria="";
                while(i!=-1)
                {
                    stringa_binaria+=""+(char)i;
                    if(hm_dec_finale.get(stringa_binaria)!=null)
                    {
                        //System.out.println(hm_dec_finale + "   " + stringa_binaria);
                        //System.out.println(stringa_binaria+" -> "+" -- "+ ((char)Integer.parseInt(stringa_binaria))+": "+hm_dec.get((char)Integer.parseInt(stringa_binaria)));
                        int carattere=Integer.parseInt(hm_dec_finale.get(stringa_binaria)+"");
                        //System.out.print((char)carattere);
                        if(carattere==13) file_decompresso.write("\n");
                        else file_decompresso.write(carattere);
                        stringa_binaria="";
                    }//else System.out.println((char)Integer.parseInt(stringa_binaria) + " -- " + Integer.parseInt(stringa_binaria) + " -- " + stringa_binaria);
                    i=file_temporaneo_reader_dec.read();
                }
            }
        }
        
        System.out.println("Decompressione riuscita!");
    }
}