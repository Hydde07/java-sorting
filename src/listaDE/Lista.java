package listaDE;

import java.util.Random;
import listaDE.No;

public class Lista {
    private No inicio;
    private No fim;

    public Lista() {
        this.inicio = null;
        this.fim = null;
    }
    
    public void inserirInicio(int info){
        No nozin = new No(info);
        if(inicio == null)
            inicio = fim = nozin;
        else{
            nozin.setProx(inicio);
            inicio.setAnt(nozin);
            inicio = nozin;
        }
    }
        
    public void inserirFim(int info){
        No nozin = new No(info);
        if(fim == null)
            inicio = fim = nozin;
        else{
            nozin.setAnt(fim);
            fim.setProx(nozin);
            fim = nozin;
        }
    }
    
    public void exibir(){
        No walk = inicio;
        while(walk != null){
            System.out.print("|"+walk.getInfo());
            walk = walk.getProx();
        }
        System.out.println("|");
    }
    
    public void geraRandom(int qt, int range){
        for(int i = 0; i<qt; i++){
            inserirFim(new Random().nextInt(range));
        }
    }
    
    public boolean isVazio(){
        return (inicio == null);
    }
    
    public int pop(){
        int n=-1;
        No aux = inicio;
        if(aux!=null)
            n = aux.getInfo();
        if(inicio == fim)
            inicio = fim = null;
        else if(inicio!=null)
            inicio = inicio.getProx();
        return n;
    }
    
    public void excluirLista(){
        inicio = fim = null;
    }
    
    private No seek(int n){
        No aux = inicio;
        for(int i = 0; aux != null && i < n; i++)
            aux = aux.getProx();
        return aux;
    }
    
    private No seek(No ini, int n){
        No aux = ini;
        if(n>0)
            for(int i = 0; aux != null && i < n; i++)
                aux = aux.getProx();
        else{
            n *= -1;
            for(int i = 0; aux != null && i < n; i++)
                aux = aux.getAnt();
        }
        return aux;
    }
    
    private int indexOf(No index){
        int n = 0;
        No aux = inicio;
        while(aux!=index){
            aux = aux.getProx();
            n++;
        }
        return n;
    }
    
    private int maxElement(){
        int n=-1;
        No aux = inicio;
        while(aux!=null){
            if(aux.getInfo()>n)
                n = aux.getInfo();
            aux = aux.getProx();
        }
        return n;
    }
    
    private int minElement(){
        int n=maxElement();
        No aux = inicio;
        while(aux!=null){
            if(aux.getInfo()<n)
                n = aux.getInfo();
            aux = aux.getProx();
        }
        return n;
    }
    
    public void directInsertSort(){
        No w,aux;
        aux = inicio.getProx();
        int info;
        while(aux!=null){
            w = aux;
            info = w.getInfo();
            while(w!=inicio && w.getAnt().getInfo()>info){
                w.setInfo(w.getAnt().getInfo());
                w = w.getAnt();
            }
            w.setInfo(info);
            aux = aux.getProx();
        }
    }
    
    private void directInsertSort(No in, No fi){
        No w,aux;
        aux = in.getProx();
        int info;
        while(aux!=fi.getProx()){
            w = aux;
            info = w.getInfo();
            while(w!=in && w.getAnt().getInfo()>info){
                w.setInfo(w.getAnt().getInfo());
                w = w.getAnt();
            }
            w.setInfo(info);
            aux = aux.getProx();
        }
    }
    
    public void binaryInsertSort(){
        No w,aux;
        aux = inicio.getProx();
        int info,l,r,meio,end;
        while(aux!=null){
            l = 0;
            r = end = indexOf(aux);
            meio = (r+l)/2;
            w = aux;
            info = w.getInfo();
            while(l<r){
                if(seek(meio).getInfo()>info)
                    l = meio+1;
                else
                    r = meio;
                meio = (r-l)/2;
            }
            if(seek(meio).getInfo()<info)
                meio++;
            while((end--)>meio){
                aux.setInfo(aux.getAnt().getInfo());
                aux = aux.getAnt();
            }
            w.setInfo(info);
            aux = aux.getProx();
        }
    }
    
    public void selectionSort(){
        No aux, f = fim, maN;
        int maI;
        while(f!=inicio){
            aux = inicio;
            maN = aux;
            maI = aux.getInfo();
            while(aux!=f.getProx()){
                if(aux.getInfo()>maI){
                    maI = aux.getInfo();
                    maN = aux;
                }
                aux = aux.getProx();
            }
            maN.setInfo(f.getInfo());
            f.setInfo(maI);
            f = f.getAnt();
        }
    }
    
    public void bubbleSort(){
        No aux, f = fim;
        int auxI;
        while(f!=inicio){
            aux = inicio;
            while(aux!=f){
                if(aux.getInfo()>aux.getProx().getInfo()){
                    auxI = aux.getInfo();
                    aux.setInfo(aux.getProx().getInfo());
                    aux.getProx().setInfo(auxI);
                }
                aux = aux.getProx();
            }
            f = f.getAnt();
        }
    }
    
    public void shakeSort(){
        No aux, f = fim, i = inicio;
        int ed;
        while(f!=i){
            aux = f;
            while(aux!=i){
                if(aux.getInfo()<aux.getAnt().getInfo()){
                    ed = aux.getInfo();
                    aux.setInfo(aux.getAnt().getInfo());
                    aux.getAnt().setInfo(ed);
                }
                aux = aux.getAnt();
            }
            i = aux.getProx();
            aux = i;
            while(aux!=f){
                if(aux.getInfo()>aux.getProx().getInfo()){
                    ed = aux.getInfo();
                    aux.setInfo(aux.getProx().getInfo());
                    aux.getProx().setInfo(ed);
                }
                aux = aux.getProx();
            }
            if(f!=i)
                f = f.getAnt();
        }
    }
    
    public void shellSort(){
        int tam = (indexOf(fim)+1)/2, ed;
        No aux, aux2, back;
        for(int i = tam; i > 0; i/=2){
            for(int j = 0; j<i; j++){
                aux = seek(j);
                aux2 = seek(aux,i);
                while(aux2!=null){
                    if(aux2.getInfo()<aux.getInfo()){
                        ed = aux2.getInfo();
                        aux2.setInfo(aux.getInfo());
                        aux.setInfo(ed);
                        
                        back = seek(aux,-i);
                        while(back!=null && back.getInfo()>aux.getInfo()){
                            ed = back.getInfo();
                            back.setInfo(aux.getInfo());
                            aux.setInfo(ed);
                            
                            aux = back;
                            back = seek(aux,-tam);
                        }
                    }
                    aux = aux2;
                    aux2 = seek(aux,i);
                }
            }
        }
    }
    
    public void heapSort(){
        int p,ed,tl = indexOf(fim)+1;
        No aux, in = inicio, fi = fim, mai;
        while(tl>1){
            p = tl/2-1;
            while(p>=0){
                aux = seek(p);
                mai = seek(aux,p+1);
                if((p*2+2)<tl && seek(aux,p+2).getInfo()>mai.getInfo())
                    mai = seek(aux,p+2);
                if(mai.getInfo()>aux.getInfo()){
                    ed = mai.getInfo();
                    mai.setInfo(aux.getInfo());
                    aux.setInfo(ed);
                }
                p--;
            }
            ed = fi.getInfo();
            fi.setInfo(in.getInfo());
            in.setInfo(ed);
            fi = fi.getAnt();
            tl--;
        }
    }
    
    public void quickSortSP(){
        quickSortSP(inicio,fim);
    }
    
    private void quickSortSP(No ini, No fim){
        int aux;
        No l = ini, r = fim;
        
        while(ini != fim){
            while(ini != fim && ini.getInfo() <= fim.getInfo())
                ini = ini.getProx();
            
            aux = ini.getInfo();
            ini.setInfo(fim.getInfo());
            fim.setInfo(aux);
            
            while(ini != fim && fim.getInfo() >= ini.getInfo())
                fim = fim.getAnt();
            
            aux = ini.getInfo();
            ini.setInfo(fim.getInfo());
            fim.setInfo(aux);
        }
        if(l != ini)
            quickSortSP(l, ini.getAnt());
        if(r != ini)
            quickSortSP(ini.getProx(), r);
    }
    
    public void quickSortCP(){
        quickSortCP(inicio,fim);
    }
    
    private void quickSortCP(No ini, No fim){
        int aux, pivo = ini.getInfo();
        No l = ini, r = fim;
        
        while(ini != fim){
            while(ini.getInfo() < pivo)
                ini = ini.getProx();
            while(fim.getInfo() > pivo)
                fim = fim.getAnt();
            
            if(ini != fim){
                if(ini.getInfo() == fim.getInfo())
                    ini = ini.getProx();
                else{
                    aux = ini.getInfo();
                    ini.setInfo(fim.getInfo());
                    fim.setInfo(aux);
                }
            }
        }
        if(l != ini)
            quickSortCP(l, ini.getAnt());
        if(r != ini)
            quickSortCP(ini.getProx(), r);
    }
    
    public void mergeSort1(){
        int seq = 1;
        while(seq < indexOf(fim)+1){
            Lista a = new Lista();
            Lista b = new Lista();
            part1(a,b);
            fusion1(a,b,seq);
            seq *= 2;
        }
    }
    
    private void part1(Lista a, Lista b){
        int tam = (indexOf(fim)+1)/2;
        No aux = inicio, aux2 = fim;
        for(int i=0; i<tam; i++){
            a.inserirFim(aux.getInfo());
            b.inserirInicio(aux2.getInfo());
            aux = aux.getProx();
            aux2 = aux2.getAnt();
        }
    }
    
    private void fusion1(Lista a, Lista b, int seq){
        int auxSeq = seq, i, j;
        i = j = 0;
        No aux = inicio;
        No noi = a.inicio;
        No noj = b.inicio;
        while(aux!=null){
            while(i<seq && j<seq){
                if(noi.getInfo()<noj.getInfo()){
                    aux.setInfo(noi.getInfo());
                    noi = noi.getProx();
                    i++;
                }
                else{
                    aux.setInfo(noj.getInfo());
                    noj = noj.getProx();
                    j++;
                }
                aux = aux.getProx();
            }
            while(i<seq){
                aux.setInfo(noi.getInfo());
                noi = noi.getProx();
                aux = aux.getProx();
                i++;
            }
            while(j<seq){
                aux.setInfo(noj.getInfo());
                noj = noj.getProx();
                aux = aux.getProx();
                j++;
            }
            seq+=auxSeq;
        }
    }
    
    public void mergeSort2(){
        Lista l = new Lista();
        merge(l,inicio,fim);
    }
    
    private void merge(Lista l, No esq, No dir){
        No mid;
        int e = indexOf(esq);
        int d = indexOf(dir);
        if(e<d){
            mid = seek((e+d)/2);
            merge(l,esq,mid);
            merge(l,mid.getProx(),dir);
            fusion2(l,esq,mid,mid.getProx(),dir);
        }
    }
    
    private void fusion2(Lista l, No ini1, No fim1, No ini2, No fim2){
        No i = ini1, j = ini2;
        while(i!=fim1.getProx() && j!=fim2.getProx()){
            if(i.getInfo()<j.getInfo()){
                l.inserirFim(i.getInfo());
                i = i.getProx();
            }
            else{
                l.inserirFim(j.getInfo());
                j = j.getProx();
            }
        }
        while(i!=fim1.getProx()){
            l.inserirFim(i.getInfo());
            i = i.getProx();
        }
        while(j!=fim2.getProx()){
            l.inserirFim(j.getInfo());
            j = j.getProx();
        }
        No aux = l.inicio;
        while(aux!=null){
            ini1.setInfo(aux.getInfo());
            aux = aux.getProx();
            ini1 = ini1.getProx();
        }
        l.excluirLista();
    }
    
    public void countingSort(){
        int ma = maxElement()+1, ed=0;
        int[] maxVet = new int[ma];
        int[] auxVet = new int[(indexOf(fim)+2)];
        No aux = inicio;
        while(aux!=null){
            maxVet[aux.getInfo()]++;
            aux = aux.getProx();
        }
        for(int i = 1; i < ma; i++)
            maxVet[i]+=maxVet[i-1];
        aux = inicio;
        while(aux!=null){
            auxVet[maxVet[aux.getInfo()]--] = aux.getInfo();
            aux = aux.getProx();
        }
        aux = inicio;
        while(aux!=null){
            aux.setInfo(auxVet[++ed]);
            aux = aux.getProx();
        }
    }
    
    public void bucketSort(int qt){
        int me = minElement(), ma = maxElement(), in = (ma-me)/qt+1, ind;
        Lista[] bucket = new Lista[qt];
        for(int i = 0; i<qt; i++)
            bucket[i] = new Lista();
        No aux = inicio, aux2;
        while(aux!=null){
            ind = (aux.getInfo()-me)/in;
            bucket[ind].inserirInicio(aux.getInfo());
            aux = aux.getProx();
        }
        aux = inicio;
        for(int i = 0; i < qt; i++){
            if(!bucket[i].isVazio())
            {
                bucket[i].quickSortCP();
                aux2 = bucket[i].inicio;
                while(aux2!=null){
                    aux.setInfo(aux2.getInfo());
                    aux = aux.getProx();
                    aux2 = aux2.getProx();
                }
            }
        }
    }
    
    public void radixSort(){
        int ma = maxElement(), exp=1, ind;
        Lista[] l = new Lista[10];
        No aux;
        for(int i = 0; i<10; i++)
            l[i] = new Lista();
        while(ma/exp>0){
            aux = inicio;
            while(aux!=null){
                ind = aux.getInfo()/exp;
                l[(ind%10)].inserirFim(aux.getInfo());
                aux = aux.getProx();
            }
            aux = inicio;
            for(int i = 0; i<10; i++)
                while(!l[i].isVazio()){
                    aux.setInfo(l[i].pop());
                    aux = aux.getProx();
                }
            exp*=10;
        }
    }
    
    public void combSort(){
        No aux, aux2;
        int gap = (int) Math.floor((indexOf(fim)+1)/1.3), ed;
        while(gap>0){
            aux = inicio;
            aux2 = seek(gap);
            while(aux2!=null){
                if(aux.getInfo()>aux2.getInfo()){
                    ed = aux.getInfo();
                    aux.setInfo(aux2.getInfo());
                    aux2.setInfo(ed);
                }
                aux2 = aux2.getProx();
                aux = aux.getProx();
            }
            gap /= 1.3;
        }
    }
    
    public void gnomeSort(){
        No aux = inicio.getProx(),aux2;
        int ed;
        while(aux!=null){
            aux2 = aux;
            while(aux2!=inicio && aux2.getInfo()<aux2.getAnt().getInfo()){
                ed = aux2.getInfo();
                aux2.setInfo(aux2.getAnt().getInfo());
                aux2.getAnt().setInfo(ed);
                aux2 = aux2.getAnt();
            }
            aux = aux.getProx();
        }
    }
    
    public void timSort(){
        No aux1 = inicio, aux2 = inicio, mid;
        int in = 32, n = indexOf(fim)+1;
        Lista l = new Lista();
        
        for(int i = 0; i < n; i += in){
            aux2 = seek(aux1, in-1);
            if(aux2 == null)
                aux2 = fim;
            this.directInsertSort(aux1, aux2);
            if(aux2 != null)
                aux1 = aux2.getProx();
        }
        while(in < n){
            aux2 = aux1 = inicio;
            for(int i = 0; i+in-1 < n; i += in*2){
                mid = seek(aux1,in-1);
                aux2 = seek(mid.getProx(), in - 1);
                if(aux2 == null)
                    aux2 = fim;
                this.fusion2(l, aux1, mid, mid.getProx(), aux2);
                if(aux2 != null)
                    aux1 = aux2.getProx();
            }
            in *= 2;
        }
    }
}
