package Huffman;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Leggi
{
    private InputStreamReader input;
    private BufferedReader tastiera; 
    public Leggi()
    {
        input = new InputStreamReader(System.in);
        tastiera = new BufferedReader(input);
    }

    public int Intero(String testo)
    {
        Boolean errore=false;String riga="";
        int intero=0;
        do
        {
            errore=false;
            try
            {
                System.out.print(testo);
                riga = tastiera.readLine();
                intero = Integer.parseInt(riga);
            }
            catch(IOException e)
            {
                System.out.println("!! Errore: Dispositivo di input non funzionante. !!");
                errore=true;
            }
            catch(NumberFormatException e)
            {
                System.out.println("!! Errore: Inserire un valore intero (0,1,2,3,4,5,6,7,8,9). !!");
                errore=true;
            }
            catch(Exception e)
            {
                System.out.println("!! Errore: Eccezione non gestita. !!");
                errore=true;
            }
        }while(errore);
        return intero;
    }
    
    public String Stringa(String testo)
    {
        Boolean errore=false;String riga="";
        String stringa="";
        do
        {
            errore=false;
            try
            {
                System.out.print(testo);
                riga = tastiera.readLine();
                stringa = riga;
            }
            catch(IOException e)
            {
                System.out.println("!! Errore: Dispositivo di input non funzionante. !!");
                errore=true;
            }
            catch(Exception e)
            {
                System.out.println("!! Errore: Eccezione non gestita. !!");
                errore=true;
            }
        }while(errore);
        return stringa;
    }
    
    public Double Reale(String testo)
    {
        Boolean errore=false;String riga="";
        Double reale=0.0;
        do
        {
            errore=false;
            try
            {
                System.out.print(testo);
                riga = tastiera.readLine();
                reale = Double.parseDouble(riga);
            }
            catch(IOException e)
            {
                System.out.println("!! Errore: Dispositivo di input non funzionante. !!");
                errore=true;
            }
            catch(NumberFormatException e)
            {
                System.out.println("!! Errore: Inserire un valore reale. !!");
                errore=true;
            }
            catch(Exception e)
            {
                System.out.println("!! Errore: Eccezione non gestita. !!");
                errore=true;
            }
        }while(errore);
        return reale;
    }
    public String Hex(String testo)
    {
        Boolean errore=false;String riga="";
        String hex="";
        do
        {
            hex="";
            errore=false;
            try
            {
                System.out.print(testo);
                //riga = tastiera.readLine();
                Boolean end=false;
                while(!end)
                {
                    
                    char ch= (char) System.in.read();
                    if((ch>=48 && ch<=57) || (ch>=65 && ch<=70) || (ch>=97 && ch<=102))
                    {
                        if(ch>=97 && ch<=102) ch-=32;//in ASCII il carattere 'a' è 97, mentre 'A' è 65. Si riporta tutto in maiuscolo quindi si sottrae 32: 97-32=65, che è proprio 'A'.
                        hex+=ch;
                    }else if(ch=='\n') end=true;
                    else
                    {
                        if(!errore)
                        {
                            errore=true;
                            System.out.println("!! Errore: Inserire un valore esadecimale (0,1,2,3,4,5,6,7,8,9,A,B,C,D,E,F). !!");
                        }
                    }
                     
                }
            }
            catch(IOException e)
            {
                System.out.println("!! Errore: Dispositivo di input non funzionante. !!");
                errore=true;
            }
            catch(Exception e)
            {
                System.out.println("!! Errore: Eccezione non gestita. !!");
                errore=true;
            }
        }while(errore);
        return hex;
    }
    public String Bin(String testo)
    {
        Boolean errore=false;String riga="";
        String bin="";
        do
        {
            bin="";
            errore=false;
            try
            {
                System.out.print(testo);
                Boolean end=false;
                while(!end)
                {
                    
                    char ch= (char) System.in.read();//legge un carattere per volta e ne effettua dei controlli (nell'if sottostante)
                    if(ch==48 || ch==49) bin+=ch;//essendo un valore binario dovrà essere solo carattere==48 ('0') e carattere==49 ('1')
                    else if(ch=='\n') end=true;//se il carattere è uguale a '\n' (new line) allora end viene posto 'true'
                    else
                    {
                        if(!errore)//per evitare che entri anche quando è già in errore
                        {
                            errore=true;
                            System.out.println("!! Errore: Inserire un valore binario (0,1). !!");
                        }
                    }
                     
                }
            }
            catch(IOException e)
            {
                System.out.println("!! Errore: Dispositivo di input non funzionante. !!");
                errore=true;
            }
            catch(Exception e)
            {
                System.out.println("!! Errore: Eccezione non gestita. !!");
                errore=true;
            }
        }while(errore);
        return bin;
    }
}
