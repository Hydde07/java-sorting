package arquivoBin;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class ArquivoGrafico {
    private RandomAccessFile file;
    private final String linha = "|-----------------------|------------------------------------------------------|------------------------------------------------------|------------------------------------------------------|\n";
    public ArquivoGrafico(String filename) throws FileNotFoundException, IOException
    {
        this.file = new RandomAccessFile(filename,"rw");
        
        file.setLength(0);
    }
    private void print(String val) throws IOException
    {
            file.write(val.getBytes());
        
    }
    public void desenhaIntro() throws IOException
    {
        //String linha = "|-----------------------|------------------------------------------------------|------------------------------------------------------|------------------------------------------------------|\n";
        String intro1 = "|Métodos Ordenação\t|Arquivo Ordenado                                      |Arquivo em Ordem Reversa                              |Arquivo Randômico                                     |\n";
        String intro2 = "|                       |Comp.     |Comp.     |Mov.      |Mov.      |Tempo     |Comp.     |Comp.     |Mov.      | Mov.     |Tempo     |Comp.     |Comp.     |Mov.      |Mov.      |Tempo     |\n";
        String intro3 = "|                       |Prog.*    |Equa.#    |Prog.+    |Equa.-    |          |Prog.*    |Equa.#    |Prog.+    |Equa.-    |          |Prog.*    |Equa.#    |Prog.+    |Equa.-    |          |\n";
        String intro = linha+intro1+linha+intro2+intro3;
        this.print(intro);
       
    }
    private String espaco(long val)
    {
       String esp = val+"";
       int pred = 10;
       int i = 0;
       if(val <= 0)
           i++;
        while(val>0)
       {
           i++;
           val /= 10;
       }
        while(pred - (i++) > 0)
           esp +=" ";
        return esp;
    }
    private String espaco(int val)
    {
       String esp = val+"";
       int pred = 10;
       int i = 0;
       if(val <= 0)
           i++;
       val = Math.abs(val);
       while(val>0)
       {
           i++;
           val /= 10;
       }
       while(pred - (i++) > 0)
           esp +=" ";
       return esp;
    }
    public void desenhaSort(String nomeSort,int cp1,int ce1,int mp1,int me1,long t1,int cp2,int ce2,int mp2,int me2,long t2,int cp3,int ce3,int mp3,int me3,long t3) throws IOException
    {
        //String linha = "|-----------------------|------------------------------------|--------------------------------------|-------------------------------------|\n";
        String esp = "";
        for(int i = 0;i<23 - nomeSort.length();i++)
        {
            esp +=" ";
        }
        String info =linha+ "|" + nomeSort + esp + "|" + espaco(cp1)+"|" + espaco(ce1)+"|"+ espaco(mp1)+"|" + espaco(me1)+"|"+espaco(t1)+"|" + espaco(cp2)+"|"+ espaco(ce2)+"|"+ espaco(mp2)+"|"+ espaco(me2)+"|"+espaco(t2)+"|"+ espaco(cp3)+"|"+ espaco(ce3)+"|"+ espaco(mp3)+"|"+ espaco(me3)+"|"+espaco(t3)+"|\n";
        this.print(info);
        
    } 

    
    public RandomAccessFile getFile()
    {
        return file;
    }
}