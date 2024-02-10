package trabpo;

import arquivoBin.Arquivo;
import arquivoBin.ArquivoGrafico;
import arquivoBin.PrincipalArq;
import java.io.FileNotFoundException;
import java.io.IOException;
import listaDE.PrincipalList;

public class TrabPO {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        new PrincipalList().execute(2048, 100);
        System.out.println(".");
        PrincipalArq p = new PrincipalArq();
        p.geraTabela();
    }
    
}
