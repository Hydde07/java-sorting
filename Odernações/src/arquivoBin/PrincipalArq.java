package arquivoBin;

import arquivoBin.Arquivo;
import arquivoBin.ArquivoGrafico;
import java.io.IOException;

public class PrincipalArq {
    private Arquivo arqOrd, arqRev, arqRand, auxRev, auxRand,auxOrd; 
    private ArquivoGrafico graf;
 
    public PrincipalArq() throws IOException
    {
        graf = new ArquivoGrafico("output.txt");
        arqOrd = new Arquivo("ArquivoOrdenado.dale");
        arqRev = new Arquivo("ArquivoReverso.dale");
        arqRand = new Arquivo("ArquivoRandomico.dale");
        auxOrd = new Arquivo("ArquivoOrdenadoAux.dale");
        auxRev = new Arquivo("ArquivoReversoAux.dale");
        auxRand = new Arquivo("ArquivoRandomicoAux.dale");
        System.out.println("Criado arquivos");
        graf.desenhaIntro();
        
        
        
    }
    public void geraTabela() throws IOException
    {
        //byte[] b = new byte[(int)graf.getFile().length()];
        //graf.getFile().readFully(b);
        System.out.println(".");
        int N = arqOrd.getTF();
        long tini,tfim,t1,t2,t3;
        arqOrd.geraArquivoOrdenado();
        arqRev.geraArquivoReverso();
        arqRand.geraArquivoAleatorio();
        System.out.println("Gerando e copiando Arquivos");
        auxOrd.copiaArquivo(arqOrd.getFile());
        auxRev.copiaArquivo(arqRev.getFile());
        auxRand.copiaArquivo(arqRand.getFile());
        System.out.println("Arquivos Gerados");
        
        
        //insertionSort
        arqOrd.initComp();
        arqOrd.initMov();
        arqRev.initMov();
        arqRev.initComp();
        arqRand.initComp();
        arqRand.initMov();
        
        System.out.println(".");
        tini = System.currentTimeMillis();
        System.out.println("INSERCAO DIRETA - ORDENADO");arqOrd.insercaoDireta();
        tfim = System.currentTimeMillis();
        t1 = tfim - tini;
        
        tini = System.currentTimeMillis();
        System.out.println("INSERCAO DIRETA - REVERSO");arqRev.insercaoDireta();
        tfim = System.currentTimeMillis();
        t2 = tfim - tini;
        
        tini = System.currentTimeMillis();
       System.out.println("INSERCAO DIRETA - Randomizado"); arqRand.insercaoDireta();
        tfim = System.currentTimeMillis();
        t3 = tfim - tini;
        
        System.out.println("Escrevendo no output.txt");
        graf.desenhaSort("Insercao Direta", arqOrd.getComp(),arqOrd.getTF()-1, arqOrd.getMov(), 3*(N-1), t1, arqRev.getComp(),((N*N+N-4)/4) , arqRev.getMov(),(N*N+3*N-4)/2, t2, arqRand.getComp(), (N*N+N-2)/4, arqRand.getMov(),(N*N+9*N-10)/4, t3);
        System.out.println("Escrito no output.txt");
        //binaryInsertion
        arqOrd.initComp();
        arqOrd.initMov();
        arqRev.initMov();
        arqRev.initComp();
        arqRand.initComp();
        arqRand.initMov();
        
        arqOrd.copiaArquivo(auxOrd.getFile());
        arqRev.copiaArquivo(auxRev.getFile());
        arqRand.copiaArquivo(auxRand.getFile());
        
        
        tini = System.currentTimeMillis();
        arqOrd.insercaoDireta();System.out.println(".");
        tfim = System.currentTimeMillis();
        t1 = tfim - tini;
        
        tini = System.currentTimeMillis();
        arqRev.insercaoDireta();System.out.println(".");
        tfim = System.currentTimeMillis();
        t2 = tfim - tini;
        
        tini = System.currentTimeMillis();
        arqRand.insercaoDireta();System.out.println(".");
        tfim = System.currentTimeMillis();
        t3 = tfim - tini;
        
        graf.desenhaSort("Insercao Binaria", arqOrd.getComp(), (int) ((int)N*(Math.log(N) - Math.log(Math.E))), arqOrd.getMov(), 3*(N-1), t1, arqRev.getComp(), (int) ((int)N*(Math.log(N) - Math.log(Math.E))),arqRev.getMov(),(N*N+3*N-4)/2, t2, arqRand.getComp(),  (int) ((int)N*(Math.log(N) - Math.log(Math.E))), arqRand.getMov(),(N*N+9*N-10)/4, t3);

        System.out.println(".");
        //selectionSort
        arqOrd.initComp();
        arqOrd.initMov();
        arqRev.initMov();
        arqRev.initComp();
        arqRand.initComp();
        arqRand.initMov();
        
        arqOrd.copiaArquivo(auxOrd.getFile());
        arqRev.copiaArquivo(auxRev.getFile());
        arqRand.copiaArquivo(auxRand.getFile());
        
        System.out.println(".");
        tini = System.currentTimeMillis();
        arqOrd.selectionSort();System.out.println(".");
        tfim = System.currentTimeMillis();
        t1 = tfim - tini;
     
        tini = System.currentTimeMillis();
        arqRev.selectionSort();System.out.println(".");
        tfim = System.currentTimeMillis();
        t2 = tfim - tini;

        tini = System.currentTimeMillis();
        arqRand.selectionSort();System.out.println(".");
        tfim = System.currentTimeMillis();
        t3 = tfim - tini;
        System.out.println(".");
        graf.desenhaSort("Selecao", arqOrd.getComp(),(N*N - N)/2, arqOrd.getMov(),3*(N-1), t1, arqRev.getComp(),(N*N - N)/2,arqRev.getMov(),(N*N)/4 + 3*(N-1), t2, arqRand.getComp(),(N*N - N)/2, arqRand.getMov(), (int) (N*(Math.log(N)+ 0.577216)), t3);

        //MergeSort
        arqOrd.initComp();
        arqOrd.initMov();
        arqRev.initMov();
        arqRev.initComp();
        arqRand.initComp();
        arqRand.initMov();
        
        arqOrd.copiaArquivo(auxOrd.getFile());
        arqRev.copiaArquivo(auxRev.getFile());
        arqRand.copiaArquivo(auxRand.getFile());
        
        System.out.println(".");
        tini = System.currentTimeMillis();
        arqOrd.print();
        arqOrd.mergeSort();System.out.println(".");
        arqOrd.print();
        tfim = System.currentTimeMillis();
        t1 = tfim - tini;
     
        tini = System.currentTimeMillis();
        arqRev.print();
        arqRev.mergeSort();System.out.println(".");
        arqRev.print();
        tfim = System.currentTimeMillis();
        t2 = tfim - tini;

        tini = System.currentTimeMillis();
        arqRand.print();
        arqRand.mergeSort();System.out.println(".");
        arqRand.print();
        tfim = System.currentTimeMillis();
        t3 = tfim - tini;
        System.out.println(".");
        graf.desenhaSort("Merge Sort", arqOrd.getComp(),0, arqOrd.getMov(),0, t1, arqRev.getComp(),0,arqRev.getMov(),0, t2, arqRand.getComp(),0, arqRand.getMov(),0, t3);
        
        //BubbleSort
        arqOrd.initComp();
        arqOrd.initMov();
        arqRev.initMov();
        arqRev.initComp();
        arqRand.initComp();
        arqRand.initMov();
        
        arqOrd.copiaArquivo(auxOrd.getFile());
        arqRev.copiaArquivo(auxRev.getFile());
        arqRand.copiaArquivo(auxRand.getFile());
        
        tini = System.currentTimeMillis();
        arqOrd.print();
        arqOrd.bubbleSort();System.out.println(".");
        arqOrd.print();
        tfim = System.currentTimeMillis();
        t1 = tfim - tini;
     
        tini = System.currentTimeMillis();
        arqRev.print();
        arqRev.bubbleSort();System.out.println(".");
        arqRev.print();
        tfim = System.currentTimeMillis();
        t2 = tfim - tini;

        tini = System.currentTimeMillis();
        arqRand.print();
        arqRand.bubbleSort();System.out.println(".");
        arqRand.print();
        tfim = System.currentTimeMillis();
        t3 = tfim - tini;
        System.out.println(".");
        graf.desenhaSort("Bubble Sort", arqOrd.getComp(),(N*N-N)/2, arqOrd.getMov(),0, t1, arqRev.getComp(),(N*N-N)/2,arqRev.getMov(),3*(N*N-N)/2, t2, arqRand.getComp(),(N*N-N)/2, arqRand.getMov(),3*(N*N-N), t3);
        
     
         //Shake Sort
        arqOrd.initComp();
        arqOrd.initMov();
        arqRev.initMov();
        arqRev.initComp();
        arqRand.initComp();
        arqRand.initMov();
        
        arqOrd.copiaArquivo(auxOrd.getFile());
        arqRev.copiaArquivo(auxRev.getFile());
        arqRand.copiaArquivo(auxRand.getFile());
        
        tini = System.currentTimeMillis();
        arqOrd.print();
        arqOrd.shakeSort();System.out.println(".");
        arqOrd.print();
        tfim = System.currentTimeMillis();
        t1 = tfim - tini;
     
        tini = System.currentTimeMillis();
        arqRev.print();
        arqRev.shakeSort();System.out.println(".");
        arqRev.print();
        tfim = System.currentTimeMillis();
        t2 = tfim - tini;

        tini = System.currentTimeMillis();
        arqRand.print();
        arqRand.shakeSort();System.out.println(".");
        arqRand.print();
        tfim = System.currentTimeMillis();
        t3 = tfim - tini;
        System.out.println(".");
        graf.desenhaSort("Shake Sort", arqOrd.getComp(),0, arqOrd.getMov(),0, t1, arqRev.getComp(),0,arqRev.getMov(),0, t2, arqRand.getComp(),0, arqRand.getMov(),0, t3);
 
         //Shell Sort
        arqOrd.initComp();
        arqOrd.initMov();
        arqRev.initMov();
        arqRev.initComp();
        arqRand.initComp();
        arqRand.initMov();
        
        arqOrd.copiaArquivo(auxOrd.getFile());
        arqRev.copiaArquivo(auxRev.getFile());
        arqRand.copiaArquivo(auxRand.getFile());
        
        tini = System.currentTimeMillis();
        arqOrd.shellSort();System.out.println(".");
        tfim = System.currentTimeMillis();
        t1 = tfim - tini;
     
        tini = System.currentTimeMillis();
        arqRev.shellSort();System.out.println(".");
        tfim = System.currentTimeMillis();
        t2 = tfim - tini;

        tini = System.currentTimeMillis();
        arqRand.shellSort();System.out.println(".");
        tfim = System.currentTimeMillis();
        t3 = tfim - tini;
        System.out.println(".");
        graf.desenhaSort("Shell Sort", arqOrd.getComp(),0, arqOrd.getMov(),0, t1, arqRev.getComp(),0,arqRev.getMov(),0, t2, arqRand.getComp(),0, arqRand.getMov(),0, t3);

        //Heap Sort
        arqOrd.initComp();
        arqOrd.initMov();
        arqRev.initMov();
        arqRev.initComp();
        arqRand.initComp();
        arqRand.initMov();
        
        arqOrd.copiaArquivo(auxOrd.getFile());
        arqRev.copiaArquivo(auxRev.getFile());
        arqRand.copiaArquivo(auxRand.getFile());
        
        tini = System.currentTimeMillis();
        arqOrd.print();
        arqOrd.heapSort();System.out.println(".");
        arqOrd.print();
        tfim = System.currentTimeMillis();
        t1 = tfim - tini;
     
        tini = System.currentTimeMillis();
        arqRev.print();
        arqRev.heapSort();System.out.println(".");
        arqRev.print();
        tfim = System.currentTimeMillis();
        t2 = tfim - tini;

        tini = System.currentTimeMillis();
        arqRand.print();
        arqRand.heapSort();System.out.println(".");
        arqRand.print();
        tfim = System.currentTimeMillis();
        t3 = tfim - tini;
        System.out.println(".");
        graf.desenhaSort("Heap Sort", arqOrd.getComp(),0, arqOrd.getMov(),0, t1, arqRev.getComp(),0,arqRev.getMov(),0, t2, arqRand.getComp(),0, arqRand.getMov(),0, t3);
        
        //Quick Sort Sem pivo
        arqOrd.initComp();
        arqOrd.initMov();
        arqRev.initMov();
        arqRev.initComp();
        arqRand.initComp();
        arqRand.initMov();
        
        arqOrd.copiaArquivo(auxOrd.getFile());
        arqRev.copiaArquivo(auxRev.getFile());
        arqRand.copiaArquivo(auxRand.getFile());
        
        tini = System.currentTimeMillis();
        arqOrd.print();
        arqOrd.quickSortSemPivo(0, arqOrd.getTF());System.out.println(".");
        arqOrd.print();
        tfim = System.currentTimeMillis();
        t1 = tfim - tini;
     
        tini = System.currentTimeMillis();
        arqRev.print();
        arqRev.quickSortSemPivo(0, arqRev.getTF());System.out.println(".");
        arqRev.print();
        tfim = System.currentTimeMillis();
        t2 = tfim - tini;

        tini = System.currentTimeMillis();
        arqRand.print();
        arqRand.quickSortSemPivo(0,arqRand.getTF());System.out.println(".");
        arqRand.print();
        tfim = System.currentTimeMillis();
        t3 = tfim - tini;
        System.out.println(".");
        graf.desenhaSort("Quick Sem Pivo", arqOrd.getComp(),0, arqOrd.getMov(),0, t1, arqRev.getComp(),0,arqRev.getMov(),0, t2, arqRand.getComp(),0, arqRand.getMov(),0, t3);
 
        //Quick Sort Com pivo
        arqOrd.initComp();
        arqOrd.initMov();
        arqRev.initMov();
        arqRev.initComp();
        arqRand.initComp();
        arqRand.initMov();
        
        arqOrd.copiaArquivo(auxOrd.getFile());
        arqRev.copiaArquivo(auxRev.getFile());
        arqRand.copiaArquivo(auxRand.getFile());
        
        tini = System.currentTimeMillis();
        arqOrd.print();
        arqOrd.quickSortComPivo(0, arqOrd.getTF());System.out.println(".");
        arqOrd.print();
        tfim = System.currentTimeMillis();
        t1 = tfim - tini;
     
        tini = System.currentTimeMillis();
        arqRev.print();
        arqRev.quickSortComPivo(0, arqRev.getTF());System.out.println(".");
        arqRev.print();
        tfim = System.currentTimeMillis();
        t2 = tfim - tini;

        tini = System.currentTimeMillis();
        arqRand.print();
        arqRand.quickSortComPivo(0,arqRand.getTF());System.out.println(".");
        arqRand.print();
        tfim = System.currentTimeMillis();
        t3 = tfim - tini;
        System.out.println(".");
        graf.desenhaSort("Quick Com Pivo", arqOrd.getComp(),0, arqOrd.getMov(),0, t1, arqRev.getComp(),0,arqRev.getMov(),0, t2, arqRand.getComp(),0, arqRand.getMov(),0, t3);
 
        //Merge 2 implemento
        arqOrd.initComp();
        arqOrd.initMov();
        arqRev.initMov();
        arqRev.initComp();
        arqRand.initComp();
        arqRand.initMov();
        
        arqOrd.copiaArquivo(auxOrd.getFile());
        arqRev.copiaArquivo(auxRev.getFile());
        arqRand.copiaArquivo(auxRand.getFile());
        
        tini = System.currentTimeMillis();
        arqOrd.print();
        arqOrd.mergeSort();System.out.println(".");
        arqOrd.print();
        tfim = System.currentTimeMillis();
        t1 = tfim - tini;
     
        tini = System.currentTimeMillis();
        arqRev.print();
        arqRev.mergeSort();System.out.println(".");
        arqRev.print();
        tfim = System.currentTimeMillis();
        t2 = tfim - tini;

        tini = System.currentTimeMillis();
        arqRand.print();
        arqRand.mergeSort();System.out.println(".");
        arqRand.print();
        tfim = System.currentTimeMillis();
        t3 = tfim - tini;
        System.out.println(".");
        graf.desenhaSort("Merge 2 impl", arqOrd.getComp(),0, arqOrd.getMov(),0, t1, arqRev.getComp(),0,arqRev.getMov(),0, t2, arqRand.getComp(),0, arqRand.getMov(),0, t3);
 
        //CountingSort
        arqOrd.initComp();
        arqOrd.initMov();
        arqRev.initMov();
        arqRev.initComp();
        arqRand.initComp();
        arqRand.initMov();
        
        arqOrd.copiaArquivo(auxOrd.getFile());
        arqRev.copiaArquivo(auxRev.getFile());
        arqRand.copiaArquivo(auxRand.getFile());
        
        tini = System.currentTimeMillis();
        arqOrd.print();
        arqOrd.countingSort();System.out.println(".");
        arqOrd.print();
        tfim = System.currentTimeMillis();
        t1 = tfim - tini;
     
        tini = System.currentTimeMillis();
        arqRev.print();
        arqRev.countingSort();System.out.println(".");
        arqRev.print();
        tfim = System.currentTimeMillis();
        t2 = tfim - tini;

        tini = System.currentTimeMillis();
        arqRand.print();
        arqRand.countingSort();System.out.println(".");
        arqRand.print();
        tfim = System.currentTimeMillis();
        t3 = tfim - tini;
        System.out.println(".");
        graf.desenhaSort("CountingSort", arqOrd.getComp(),0, arqOrd.getMov(),0, t1, arqRev.getComp(),0,arqRev.getMov(),0, t2, arqRand.getComp(),0, arqRand.getMov(),0, t3);
 
        //Bucket Sort
        arqOrd.initComp();
        arqOrd.initMov();
        arqRev.initMov();
        arqRev.initComp();
        arqRand.initComp();
        arqRand.initMov();
        
        arqOrd.copiaArquivo(auxOrd.getFile());
        arqRev.copiaArquivo(auxRev.getFile());
        arqRand.copiaArquivo(auxRand.getFile());
        
        tini = System.currentTimeMillis();
        arqOrd.print();
        arqOrd.bucketSort(4);System.out.println(".");
        arqOrd.print();
        tfim = System.currentTimeMillis();
        t1 = tfim - tini;
     
        tini = System.currentTimeMillis();
        arqRev.print();
        arqRev.bucketSort(4);System.out.println(".");
        arqRev.print();
        tfim = System.currentTimeMillis();
        t2 = tfim - tini;

        tini = System.currentTimeMillis();
        arqRand.print();
        arqRand.bucketSort(4);System.out.println(".");
        arqRand.print();
        tfim = System.currentTimeMillis();
        t3 = tfim - tini;
        System.out.println(".");
        graf.desenhaSort("Bucket Sort", arqOrd.getComp(),0, arqOrd.getMov(),0, t1, arqRev.getComp(),0,arqRev.getMov(),0, t2, arqRand.getComp(),0, arqRand.getMov(),0, t3);
  
        //Radix Sort
        arqOrd.initComp();
        arqOrd.initMov();
        arqRev.initMov();
        arqRev.initComp();
        arqRand.initComp();
        arqRand.initMov();
        
        arqOrd.copiaArquivo(auxOrd.getFile());
        arqRev.copiaArquivo(auxRev.getFile());
        arqRand.copiaArquivo(auxRand.getFile());
        
        tini = System.currentTimeMillis();
        arqOrd.print();
        arqOrd.radixSort();System.out.println(".");
        arqOrd.print();
        tfim = System.currentTimeMillis();
        t1 = tfim - tini;
     
        tini = System.currentTimeMillis();
        arqRev.print();
        arqRev.radixSort();System.out.println(".");
        arqRev.print();
        tfim = System.currentTimeMillis();
        t2 = tfim - tini;

        tini = System.currentTimeMillis();
        arqRand.print();
        arqRand.radixSort();System.out.println(".");
        arqRand.print();
        tfim = System.currentTimeMillis();
        t3 = tfim - tini;
        System.out.println(".");
        graf.desenhaSort("Radix Sort", arqOrd.getComp(),0, arqOrd.getMov(),0, t1, arqRev.getComp(),0,arqRev.getMov(),0, t2, arqRand.getComp(),0, arqRand.getMov(),0, t3);
   
        //Comb Sort
        arqOrd.initComp();
        arqOrd.initMov();
        arqRev.initMov();
        arqRev.initComp();
        arqRand.initComp();
        arqRand.initMov();
        
        arqOrd.copiaArquivo(auxOrd.getFile());
        arqRev.copiaArquivo(auxRev.getFile());
        arqRand.copiaArquivo(auxRand.getFile());
        
        tini = System.currentTimeMillis();
        arqOrd.print();
        arqOrd.combSort();System.out.println(".");
        arqOrd.print();
        tfim = System.currentTimeMillis();
        t1 = tfim - tini;
     
        tini = System.currentTimeMillis();
        arqRev.print();
        arqRev.combSort();System.out.println(".");
        arqRev.print();
        tfim = System.currentTimeMillis();
        t2 = tfim - tini;

        tini = System.currentTimeMillis();
        arqRand.print();
        arqRand.combSort();System.out.println(".");
        arqRand.print();
        tfim = System.currentTimeMillis();
        t3 = tfim - tini;
        System.out.println(".");
        graf.desenhaSort("Comb Sort", arqOrd.getComp(),0, arqOrd.getMov(),0, t1, arqRev.getComp(),0,arqRev.getMov(),0, t2, arqRand.getComp(),0, arqRand.getMov(),0, t3);
    
        //Gnome Sort
        arqOrd.initComp();
        arqOrd.initMov();
        arqRev.initMov();
        arqRev.initComp();
        arqRand.initComp();
        arqRand.initMov();
        
        arqOrd.copiaArquivo(auxOrd.getFile());
        arqRev.copiaArquivo(auxRev.getFile());
        arqRand.copiaArquivo(auxRand.getFile());
        
        tini = System.currentTimeMillis();
        arqOrd.print();
        arqOrd.gnomeSort();System.out.println(".");
        arqOrd.print();
        tfim = System.currentTimeMillis();
        t1 = tfim - tini;
     
        tini = System.currentTimeMillis();
        arqRev.print();
        arqRev.gnomeSort();System.out.println(".");
        arqRev.print();
        tfim = System.currentTimeMillis();
        t2 = tfim - tini;

        tini = System.currentTimeMillis();
        arqRand.print();
        arqRand.gnomeSort();System.out.println(".");
        arqRand.print();
        tfim = System.currentTimeMillis();
        t3 = tfim - tini;
        System.out.println(".");
        graf.desenhaSort("Gnome Sort", arqOrd.getComp(),0, arqOrd.getMov(),0, t1, arqRev.getComp(),0,arqRev.getMov(),0, t2, arqRand.getComp(),0, arqRand.getMov(),0, t3);
 
        //Tim Sort
        arqOrd.initComp();
        arqOrd.initMov();
        arqRev.initMov();
        arqRev.initComp();
        arqRand.initComp();
        arqRand.initMov();
        
        arqOrd.copiaArquivo(auxOrd.getFile());
        arqRev.copiaArquivo(auxRev.getFile());
        arqRand.copiaArquivo(auxRand.getFile());
        
        tini = System.currentTimeMillis();
        arqOrd.print();
        arqOrd.timSort(0,arqOrd.getTF());System.out.println(".");
        arqOrd.print();
        tfim = System.currentTimeMillis();
        t1 = tfim - tini;
     
        tini = System.currentTimeMillis();
        arqRev.print();
        arqRev.timSort(0,arqOrd.getTF());System.out.println(".");
        arqRev.print();
        tfim = System.currentTimeMillis();
        t2 = tfim - tini;

        tini = System.currentTimeMillis();
        arqRand.print();
        arqRand.timSort(0,arqRand.getTF());System.out.println(".");
        arqRand.print();
        tfim = System.currentTimeMillis();
        t3 = tfim - tini;
        System.out.println(".");
        graf.desenhaSort("Tim Sort", arqOrd.getComp(),0, arqOrd.getMov(),0, t1, arqRev.getComp(),0,arqRev.getMov(),0, t2, arqRand.getComp(),0, arqRand.getMov(),0, t3);

    }
}
