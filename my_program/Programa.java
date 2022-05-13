import java.io.*;  
import java.util.Scanner;

class Conta{
    String nome;
    int agencia;
    int conta;
    String cpf;
    double saldo;

    void criarConta(String nome, String agencia, String conta, String cpf){
        this.nome = nome;
        this.agencia = Integer.parseInt(agencia);
        this.conta = Integer.parseInt(conta);
        this.cpf = cpf;
        saldo = 0.00;
    }

    void retirar(int valor){
        saldo -= valor;
    }
    
    void colocar(int valor){
        saldo += valor;
    }

    double consultarSaldo(){
        return saldo;
    }
}

public class Programa{
    public static void main (String[] args) throws FileNotFoundException{
        boolean dadosIncompletos, tipoTransferencia, mesmaConta = false;
        String[] entrada = lerEntrada();
        dadosIncompletos = dadosIncompletos(entrada);
        if(!dadosIncompletos){
            mesmaConta = mesmaConta(entrada[4], entrada[5], entrada[8], entrada[9]);
            if(!mesmaConta){
                tipoTransferencia = tipoTranferencia(entrada[2], Integer.parseInt(entrada[1]));
                if(!tipoTransferencia){
                    Conta emissor = new Conta();
                    emissor.criarConta(entrada[3], entrada[4], entrada[5], entrada[6]);
                    Conta receptor = new Conta();
                    receptor.criarConta(entrada[7], entrada[8], entrada[9], entrada[10]);
                    emissor.retirar(Integer.parseInt(entrada[1]));
                    receptor.colocar(Integer.parseInt(entrada[1]));
                    System.out.println("Sua transferência foi realizada com sucesso!\nSaldo do emissor: R$" + emissor.consultarSaldo() + "\nSaldo do receptor: R$" + receptor.consultarSaldo());
                } else{
                    System.out.println("Sua transferência não foi completada pois o valor não é permitido para esse tipo de transferencia");
                }
            } else{
                System.out.println("Sua transferência não foi completada pois não é permitido transferência para a mesma conta");
            }
        } else{
            System.out.println("Sua transferência não foi completada pois todos os dados não foram preenchidos");
        }
    }
    
    private static String[] lerEntrada() throws FileNotFoundException{
        FileInputStream fis = new FileInputStream("entrada.txt");
        Scanner sc = new Scanner(fis); 
        sc.nextLine();
        sc.nextLine();
        String[] entrada = sc.nextLine().split("[|]");
        sc.close();
        return entrada;
    }
    
    private static boolean dadosIncompletos(String[] entrada){
        if(entrada.length != 11){
            return true;
        }
        for(int i = 0; i <= 10; i++){
            if(entrada[i].equals("")){
                return true;
            }
        }
        return false;
    }

    private static boolean mesmaConta(String AgEmissor, String CoEmissor, String AgReceptor, String CoReceptor){
        if(AgEmissor.equals(AgReceptor) && CoEmissor.equals(CoReceptor)){
            return true;
        }
        return false;
    }

    private static boolean tipoTranferencia(String tipo, int valor){
        if(tipo.equals("PIX")){
            if(valor <= 5000){
                return false;
            }
        } else if(tipo.equals("TED")){
            if(valor >= 5000 && valor <= 10000){
                return false;
            }
        } else if(tipo.equals("DOC")){
            if(valor >= 10000){
                return false;
            }
        }
        return true;
    }
}
