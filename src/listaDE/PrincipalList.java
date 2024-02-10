package listaDE;

public class PrincipalList {
    
    
    private Lista novaLista(Lista l, int qt, int range){
        l.excluirLista();
        l.geraRandom(qt, range);
        return l;
    }
    
    public void execute(int tam, int rang){
        
        Lista l = new Lista();
        
        l = novaLista(l, tam, rang);
        System.out.println("Direct Insert Sort");
        l.exibir();
        l.directInsertSort();
        l.exibir();
        System.out.println("");
        
        l = novaLista(l, tam, rang);
        System.out.println("Binary Insert Sort");
        l.exibir();
        l.directInsertSort();
        l.exibir();
        System.out.println("");
        
        l = novaLista(l, tam, rang);
        System.out.println("Selection Sort");
        l.exibir();
        l.selectionSort();
        l.exibir();
        System.out.println("");
        
        l = novaLista(l, tam, rang);
        System.out.println("Bubble Sort");
        l.exibir();
        l.bubbleSort();
        l.exibir();
        System.out.println("");
        
        l = novaLista(l, tam, rang);
        System.out.println("Shake Sort");
        l.exibir();
        l.shakeSort();
        l.exibir();
        System.out.println("");
        
        l = novaLista(l, tam, rang);
        System.out.println("Shell Sort");
        l.exibir();
        l.shellSort();
        l.exibir();
        System.out.println("");
        
        l = novaLista(l, tam, rang);
        System.out.println("Heap Sort");
        l.exibir();
        l.heapSort();
        l.exibir();
        System.out.println("");
        
        l = novaLista(l, tam, rang);
        System.out.println("Quick Sort (Com Pivo)");
        l.exibir();
        l.quickSortCP();
        l.exibir();
        System.out.println("");
        
        l = novaLista(l, tam, rang);
        System.out.println("Quick Sort (Sem Pivo)");
        l.exibir();
        l.quickSortSP();
        l.exibir();
        System.out.println("");
        
        l = novaLista(l, tam, rang);
        System.out.println("Merge Sort (Imp 1)");
        l.exibir();
        l.mergeSort1();
        l.exibir();
        System.out.println("");
        
        l = novaLista(l, tam, rang);
        System.out.println("Merge Sort (Imp 2)");
        l.exibir();
        l.mergeSort2();
        l.exibir();
        System.out.println("");
        
        l = novaLista(l, tam, rang);
        System.out.println("Counting Sort");
        l.exibir();
        l.countingSort();
        l.exibir();
        System.out.println("");
        
        l = novaLista(l, tam, rang);
        System.out.println("Bucket Sort");
        l.exibir();
        l.bucketSort(4);
        l.exibir();
        System.out.println("");
        
        l = novaLista(l, tam, rang);
        System.out.println("Radix Sort");
        l.exibir();
        l.radixSort();
        l.exibir();
        System.out.println("");
        
        l = novaLista(l, tam, rang);
        System.out.println("Comb Sort");
        l.exibir();
        l.combSort();
        l.exibir();
        System.out.println("");
        
        l = novaLista(l, tam, rang);
        System.out.println("Gnome Sort");
        l.exibir();
        l.gnomeSort();
        l.exibir();
        System.out.println("");
        
        l = novaLista(l, tam, rang);
        System.out.println("Tim Sort");
        l.exibir();
        l.timSort();
        l.exibir();
        System.out.println("");
    }
}
