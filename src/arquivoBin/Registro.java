package arquivoBin;

import java.io.IOException;
import java.io.RandomAccessFile;

public class Registro {
    public final int tf=1022;
    private int numero;
    private char lixo[] = new char[tf];
    
    public Registro(){}
    
    public Registro(int numero){
        this.numero = numero;
        for(int i = 0; i < this.tf; i++)
            this.lixo[i] = 'X';
    }
    
    public void gravaNoArq(RandomAccessFile arquivo){
        try{
            arquivo.writeInt(this.numero);
            for(int i = 0; i < this.tf; i++)
                arquivo.writeChar(this.lixo[i]);
        }
        catch(IOException e){}
    }
    
    public void leDoArq(RandomAccessFile arquivo){
        try{
            this.numero = arquivo.readInt();
            for(int i = 0; i < this.tf; i++)
                this.lixo[i]=arquivo.readChar();
        }
        catch(IOException e){}
    }
    public void setNum(int numero)
    {
        this.numero = numero;
    }
    public int getNum()
    {
        return numero;
    }
    public static int getLength(){
        return (2048);
    }
}
