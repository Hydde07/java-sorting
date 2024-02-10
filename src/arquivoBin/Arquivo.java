package arquivoBin;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import listaDE.Lista;

public class Arquivo {
    
    private RandomAccessFile file;
    private String filename;
    private int comp,mov;
    private final int TF = 16;//2^10
    
    public Arquivo(String filename) throws FileNotFoundException, IOException
    {
        comp = mov = 0 ;
        this.filename = filename;
        file = new RandomAccessFile(filename,"rw");
        truncate(0);
        
    }
    public void copiaArquivo(RandomAccessFile arquivoOrigem) throws IOException{
        byte[] b;
        b = new byte[(int)arquivoOrigem.length()];
    
        
        file.seek(0);
        file.setLength(0);
        arquivoOrigem.seek(0);
        arquivoOrigem.readFully(b);
        file.write(b);
    }
    public RandomAccessFile getFile() 
    {
        return file;
    }
    public void truncate(long pos) throws IOException
    {
        file.setLength(pos);
    }
    public void initComp()
    {
        comp = 0;
    }
    public void initMov()
    {
        mov = 0;
    }
    public int getComp()
    {
        return comp;
    }
    public int getMov()
    {
        return mov;
    }
    public boolean eof() throws IOException
    {
        return file.getFilePointer() == file.length();
    }
    public void seekArq(long pos) throws IOException
    {
       file.seek(Registro.getLength() * pos);
    }

    public long filesize() throws IOException
    {
        return file.length()/Registro.getLength();
    }
    
    
    //SORTS
    
    public void insercaoDireta() throws IOException
    {
        seekArq(0);
        Registro reg = new Registro();
        Registro regaux = new Registro();
        for(int i = 1;i < file.length()/Registro.getLength();i++)
        {
            //System.out.println(i);
            seekArq(i);
            reg.leDoArq(file);
            seekArq(i-1);
            regaux.leDoArq(file);
            comp++;
            mov+=2;
            int anda = i-1;
            while(anda >= 0 && reg.getNum() < regaux.getNum())
            {
                seekArq(anda+1);
                regaux.gravaNoArq(file);
                mov++;
                comp++;
                
                anda--;
                if(anda >=0)
                {
                    
                seekArq(anda);
                regaux.leDoArq(file);
                
                }
            }
            if(anda < 0)
                comp--;
           
            seekArq(anda+1);
            reg.gravaNoArq(file);
            mov++;
        }
        
    }
    public void insercaoBinaria() throws IOException{
        seekArq(0);
        int anda;
        Registro reg = new Registro();
        Registro regaux = new Registro();
        long tam = filesize();
        
        
        comp++;
        for(int i = 1; i < tam; i++){
            seekArq(i);
            reg.leDoArq(file);
            anda = buscaB(reg.getNum(), i);
            comp++;
            for(int j = i; j > anda; j--){
                mov+=2;
                comp++;
                seekArq(j-1);
                regaux.leDoArq(file);
                seekArq(j);
                regaux.gravaNoArq(file);
                
            }
            comp++;
            seekArq(anda);
            reg.gravaNoArq(file);
            
        }
    }
    
    public int buscaB(int chave, int tam) throws IOException{
    Registro reg = new Registro();

    int i = 0, j = tam-1;


    int meio = (i + j)/2;
    seekArq(meio);
    reg.leDoArq(file);
    while(i < j){
        
        if(reg.getNum() < chave)
            i = meio + 1;
        else
            j = meio;
        comp++;
        meio = (i + j)/2;
        seekArq(meio);
        reg.leDoArq(file);
    }
    comp++;
    if(reg.getNum() < chave)
        return meio + 1;
    return meio;
}
    
    
    
    public void selectionSort() throws IOException
    {
        Registro reg = new Registro();       
        long tam = filesize();
        int anda;
        Registro regaux = new Registro();
        for(int i = 0;i<tam-1;i++)
        {
            seekArq(i);
            reg.leDoArq(file);
           mov++;
            anda = i;
            for(int j= i+1;j<tam;j++)
            {
                seekArq(j);
                regaux.leDoArq(file);
                mov++;
                if(reg.getNum()> regaux.getNum())
                {
                    reg.setNum(regaux.getNum());
                    
                    anda = j;
                    comp++;
                }
                comp++;
            }
            if(i != anda)
            {
                comp++;
                seekArq(i);
                regaux.leDoArq(file);
                seekArq(i);
                reg.gravaNoArq(file);
                seekArq(anda);
                regaux.gravaNoArq(file);
                mov+=2;
            }
        }
        
    }
    public void bubbleSort() throws IOException
    {
        Registro reg = new Registro();
        Registro regaux = new Registro();
        
        for(long i = filesize();i>1;i--)
        {
            for(int j =0;j<i - 1;j++)
            {
                seekArq(j);
                reg.leDoArq(file);
                seekArq(j+1);
                regaux.leDoArq(file);
                
                
                comp++;
                if(reg.getNum()>regaux.getNum())
                {
                    seekArq(j);
                    regaux.gravaNoArq(file);
                    seekArq(j+1);
                    reg.gravaNoArq(file);
                    mov += 2;
                }
            }
        }
    }
    public void shakeSort() throws IOException{
        Registro reg = new Registro();
        Registro regaux = new Registro();
        long tam = filesize();
        int i =0;
        
        for(long j = tam-1; i < j;){
            for(int k = i; k < j; k++){
                seekArq(k);
                reg.leDoArq(file);
                regaux.leDoArq(file);
                mov+=2;
                
                if(reg.getNum() > regaux.getNum()){
                    seekArq(k);
                    regaux.gravaNoArq(file);
                    reg.gravaNoArq(file);
                    mov+=2;
                }
                comp++;
            }
            j--;
            
            for(long k = j; k > i; k--){
                seekArq(k-1);
                reg.leDoArq(file);
                seekArq(k);
                regaux.leDoArq(file);
                mov+=2;
                if(reg.getNum() > regaux.getNum()){
                    seekArq(k-1);
                    regaux.gravaNoArq(file);
                    reg.gravaNoArq(file);
                    mov+=2;
                }
                comp++;
            }
            i++;
        }
    }
    
public void shellSort() throws IOException{
        Registro reg1 = new Registro();
        Registro reg2 = new Registro();
        long TL = this.filesize();
        int k;

        for(long N = TL >> 1; N > 0; N >>= 1){
            for(int i = 0; i < TL-N; i ++){
                for(int j = i; j < TL-N; j += N){
                    this.seekArq(j);
                    reg1.leDoArq(this.file);
                    this.seekArq(j+N);
                    reg2.leDoArq(this.file);

                    this.comp++;
                    if(reg1.getNum() > reg2.getNum()){
                        this.seekArq(j);
                        reg2.gravaNoArq(this.file);
                        this.seekArq(j+N);
                        reg1.gravaNoArq(this.file);

                        this.comp += 2;
                        k = j;
                        this.seekArq(k);
                        reg1.leDoArq(this.file);
                        if(k-N>0){
                            this.seekArq(k-N);
                            reg2.leDoArq(this.file);
                            while(k-N > 0 && reg1.getNum() < reg2.getNum()){
                                this.mov += 2;
                                this.seekArq(k);
                                reg2.gravaNoArq(this.file);
                                this.seekArq(k-N);
                                reg1.gravaNoArq(this.file);

                                k -= N;

                                this.comp += 2;
                                this.seekArq(k);
                                reg1.leDoArq(this.file);
                                this.seekArq(k-N);
                                reg2.leDoArq(this.file);
                            }
                        }
                        if(k <= 0) this.comp--;
                    }
                }
            }
        }
    }
    
    
    public void heapSort() throws IOException{
        
        Registro reg = new Registro();
        Registro regaux = new Registro();
        int pai, fe, fd, maior;
        long tam = filesize();
        
        while(tam > 1){
            pai = (int) ((tam/2) - 1);
            while(pai >= 0){
                fe = (pai*2) + 1;
                fd = (pai*2) + 2;
                
                seekArq(fe);
                reg.leDoArq(file);
                seekArq(fd);
                regaux.leDoArq(file);
                mov+=2;
                if(fd < tam && regaux.getNum() > reg.getNum())
                    maior = fd;
                else
                    maior = fe;
                if(fd>=tam)
                    comp++;
                seekArq(pai);
                reg.leDoArq(file);
                seekArq(maior);
                regaux.leDoArq(file);
                mov+=2;
                comp++;
                if(regaux.getNum() > reg.getNum()){
                    seekArq(pai);
                    regaux.gravaNoArq(file);
                    seekArq(maior);
                    reg.gravaNoArq(file);
                    mov+=2;
                }
                
                pai--;
            }
            seekArq(0);
            reg.leDoArq(file);
            seekArq(tam-1);
            regaux.leDoArq(file);
            
            seekArq(0);
            regaux.gravaNoArq(file);
            seekArq(tam-1);
            reg.gravaNoArq(file);
            mov+=4;
            tam--;
        }
    }
    public void quickSortComPivo(int i, int j) throws IOException{
        Registro reg = new Registro();
        Registro regaux = new Registro();
        int l = i, r = j, pivo;
        
        seekArq(i);
        reg.leDoArq(file);
        pivo = reg.getNum();
        mov++;
        while(i < j){
            
            seekArq(i);
            reg.leDoArq(file);
            mov++;
            comp++;
            while(reg.getNum() < pivo){
                seekArq(++i);
                reg.leDoArq(file);
                mov++;
                comp++;
            }

            seekArq(j);
            regaux.leDoArq(file);
            comp++;
            mov++;
            while(regaux.getNum() > pivo){
                seekArq(--j);
                regaux.leDoArq(file);
                comp++;
                mov++;
            }
            
            
            if(i <= j){
                seekArq(i);
                regaux.gravaNoArq(file);
                seekArq(j);
                reg.gravaNoArq(file);
                i++;
                j--;
                mov += 2;
            }
        }
        if(l < j)
            quickSortComPivo(l, j);
        if(i < r)
            quickSortComPivo(i, r);
    }
    
    public void quickSortSemPivo(int i, int j) throws IOException{
        Registro reg = new Registro();
        Registro regaux = new Registro();
        int l = i, r = j;
        
       
        while(i < j){
            seekArq(i);
            reg.leDoArq(file);
            seekArq(j);
            regaux.leDoArq(file);
            mov+=2;
            comp++;
            while(i < j && reg.getNum() <= regaux.getNum()){
                i++;
                seekArq(i);
                reg.leDoArq(file);
                comp++;
                mov++;
            }
            if(i >= j) comp--;
            
            
            if(i != j){
                ;
                seekArq(i);
                regaux.gravaNoArq(file);
                seekArq(j);
                reg.gravaNoArq(file);
                mov += 2;
                
            }
            
            seekArq(i);
            reg.leDoArq(file);
            seekArq(j);
            regaux.leDoArq(file);
            mov+=2;
            comp++;
            while(i < j && reg.getNum() <= regaux.getNum()){
                j--;
                seekArq(j);
                regaux.leDoArq(file);
                comp++;
                mov++;
            }
            if(i >= j) comp--;
            

            if(i != j){
                mov += 2;
                seekArq(i);
                regaux.gravaNoArq(file);
                seekArq(j);
                reg.gravaNoArq(file);
            }
            
        }
        if(r > i+1){
            quickSortSemPivo(i+1, r);
        }
        if(j-1 > l){
            quickSortSemPivo(l, j-1);
        }
    }
    public void combSort() throws IOException{
        Registro reg = new Registro();
        Registro regaux = new Registro();
        int tam = (int)filesize();
        long gap = filesize();
        long pos;
        
        while(gap > 0){ 
            gap /= 1.3;
            for(int i = 0; i < (tam-gap); i++){
                seekArq(i);
                reg.leDoArq(file);
                pos = i+gap;
                seekArq(pos);
                regaux.leDoArq(file); 
                mov+=2;
                comp++;
                if(reg.getNum() > regaux.getNum()){
                    seekArq(i);
                    regaux.gravaNoArq(file);
                    seekArq(pos);
                    reg.gravaNoArq(file);
                    comp++;
                    mov += 2;
                }
            }
        }
    }
    public void gnomeSort() throws IOException{
        Registro reg = new Registro();
        Registro regaux = new Registro();
        long tam = filesize();
        int i = 0;
        while(i < tam )
        {
            
            if(i > 0)
            {
                seekArq(i-1);
                regaux.leDoArq(file);
                reg.leDoArq(file);
                comp++;
                if(reg.getNum() < regaux.getNum())
                {
                    seekArq(i-1);
                    reg.gravaNoArq(file);
                    regaux.gravaNoArq(file);
                    mov+=2;
                    i--;
                }
                else
                    i++;
                
            }
            else
                i++;
        }
        
    }
    public void mergeSort() throws IOException    
    {
        int l,m,r;
        l = 0;
        r = (int) filesize();
        mergeSort(l,r-1);
    }
    private void mergeSort(int l, int r) throws FileNotFoundException, IOException
    {
        if( l < r)
        {
            
            int m = (l+r)/2;
            mergeSort(l,m);
            mergeSort(m+1,r);
            merge(l,m,r);
        }
    }
    public void merge(int l,int m,int r) throws FileNotFoundException, IOException
    {
        Registro reg = new Registro();
        Registro regaux = new Registro();
        int ini1 = l;
        int ini2 = m+1;

        Arquivo arq = new Arquivo("mergeaux1.hpb");
        arq.seekArq(0);
        while(ini1 <= m && ini2 <= r)
        {
            seekArq(ini1);
            reg.leDoArq(file);
            seekArq(ini2);
            regaux.leDoArq(file);
            mov+=3;
            comp++;
            if(reg.getNum() < regaux.getNum())
            {
                
                reg.gravaNoArq(arq.getFile());
                ini1++;
  
            }
            else
            {
                regaux.gravaNoArq(arq.getFile());
                ini2++;  
            } 
            
        }
        while(ini2<=r)
        {
            seekArq(ini2);
            reg.leDoArq(file);
            reg.gravaNoArq(arq.getFile());
            ini2++;
            mov++;
        }
        while(ini1<=m)
        {
            seekArq(ini1);
            reg.leDoArq(file);
            reg.gravaNoArq(arq.getFile());
            ini1++;
            mov++;
        }
        arq.seekArq(0);
        seekArq(l);
        for(int i = l;i<=r;i++)
        {
            reg.leDoArq(arq.getFile());
            reg.gravaNoArq(file);
            mov+=2;
        }
        
        
    }
    public void print() throws IOException
    {
        Registro r =new Registro();
        seekArq(0);
        while(!eof())
        {
            r.leDoArq(file);
            System.out.print(r.getNum() + " | ");
            
        }
        System.out.println("");
    }
    public void timSort(int l, int r) throws IOException{
        Arquivo arq = new Arquivo("tim.tr");
        int gap = (int)Math.pow(2, 3);
        int meio;
        int TL = (int)this.filesize();
        
        for(int i = 0; i < TL; i += gap)
            quickSortComPivo(i,i+gap);
        
        while(gap < TL){
            for(int i = 0; i < TL; i += (gap << 1))
                this.merge( i, i + gap-1, i + (gap << 1) - 1);
            gap <<= 1;
        }
    }
    public void radixSort() throws IOException{
        int ma = getMax(), exp=1, ind;
        Lista[] l = new Lista[10];
        Registro reg = new Registro();
        for(int i = 0; i<10; i++)
            l[i] = new Lista();
        while(ma/exp>0){
            int i = 0;
            
            while(i < filesize()){
                seekArq(i);
                reg.leDoArq(file);mov++;
                ind = reg.getNum()/exp;
                l[(ind%10)].inserirFim(reg.getNum());
                i++;
            }
            int j = 0;
            seekArq(0);
            for(i = 0; i<10; i++)
                while(!l[i].isVazio()){
                    reg.setNum(l[i].pop());
                    reg.gravaNoArq(file);
                    mov++;
                }
            exp*=10;
        }
    }
    public void countingSort() throws IOException{
        int ma = getMax()+1, ed=0;
        int[] maxVet = new int[ma];
        int[] auxVet = new int[(int)filesize()+1];
        int i = 0;
        Registro reg = new Registro();
        while(i < filesize()){
            seekArq(i);
            reg.leDoArq(file);
            maxVet[reg.getNum()]++;
            i++;
        }
        for( i = 1; i < ma; i++)
            maxVet[i]+=maxVet[i-1];
        i = 0;
        while(i < filesize()){
            seekArq(i);
            reg.leDoArq(file);mov++;
            auxVet[maxVet[reg.getNum()]--] = reg.getNum();
            i++;
        }
        i = 0;
        while(i < filesize()){
            reg.setNum(auxVet[++ed]);
            seekArq(i);
            reg.gravaNoArq(file);mov++;
            i++;
        }
    }
    public void bucketSort(int numbaldes) throws IOException
    {
        Registro reg = new Registro();
        Lista[] lista = new Lista[numbaldes];
        for(int i =0;i<numbaldes;i++)
        {
            lista[i] = new Lista();
        }
        int max = getMax();
        int min = getMin();
        int intv = (int)Math.ceil((max - min)/numbaldes);
        int aux;
        seekArq(0);
        while(!eof())
        {
            reg.leDoArq(file);
            mov++;
            aux = reg.getNum();
            if((aux-min+1)/intv >= numbaldes)
                lista[numbaldes-1].inserirFim(aux);
            else
                lista[(aux-min+1)/intv].inserirFim(aux);
        }
        for(int i = 0;i < numbaldes;i++)
        {
            if(!lista[i].isVazio())
                lista[i].quickSortCP();
            
        }
        seekArq(0);
        for(int i = 0;i< numbaldes;i++)
        {
            
            while(!lista[i].isVazio())
            {
                reg.setNum(lista[i].pop());
                reg.gravaNoArq(file);
                mov++;
            }    
                
        } 
        
    }
    
    public void geraArquivoReverso()
    {
        Registro reg = new Registro(1024);
        for(int i = TF;i>0;i--)
        {
            reg.setNum(i);
            reg.gravaNoArq(file);
           
        }
    }
    public void geraArquivoOrdenado()
    {
        Registro reg = new Registro(1024);
        for(int i = 0;i<TF;i++)
        {
            reg.setNum(i);
            reg.gravaNoArq(file);
           
        }
    }
    public void geraArquivoAleatorio()
    {
        Registro reg = new Registro();
        for(int i = 0; i<TF;i++)
        {
            reg.setNum((int)((Math.random()*TF))%TF);
            reg.gravaNoArq(file);
           
        }
    }
    public int getTF()
    {
        return TF;
    }
    
    public int getMax() throws IOException
    {
        Registro reg = new Registro();
        seekArq(0);
        reg.leDoArq(file);
        int max = reg.getNum();
        for(int i = 0;i<filesize();i++)
        {
            reg.leDoArq(file);
            if(reg.getNum() > max)
                max = reg.getNum();
        }
        return max;  
    }
    public int getMin() throws IOException
    {
        Registro reg = new Registro();
        seekArq(0);
        reg.leDoArq(file);        
        int min = reg.getNum();
        for(int i = 0;i<filesize();i++)
        {
            reg.leDoArq(file);
            if(reg.getNum() < min)
                min = reg.getNum();
        }
        return min;  
    }
}
