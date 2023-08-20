import java.util.Scanner;
public class MainClass{
public static void main(String args[]) {
Scanner reader = new Scanner(System.in);
int valorInicial;
String nome;
int a = 7;
double b = 2.0;
System.out.println("Insira um nome ");
nome = reader.nextLine();
System.out.println("Insira um valor inicial ");
valorInicial = reader.nextInt();
if (valorInicial<161.33333) {
valorInicial = 5;
}
else {
valorInicial = 3*valorInicial;
}

int valorFinal = valorInicial+10;
while (valorInicial<valorFinal) {
System.out.println(valorInicial);
valorInicial = valorInicial+1;
}

System.out.print("Primeira parte ");
System.out.println("executada");
valorFinal = valorInicial+20;
do {
System.out.println(valorInicial);
valorInicial = valorInicial+2;
} while (valorInicial<valorFinal);
System.out.print("Segunda parte ");
System.out.println("executada");
double var = 1.6666666;
System.out.println(var);
double k = (valorInicial*5.0)/(valorFinal+2*(valorInicial+7.5));
System.out.println(k);
System.out.println("Terceira parte executada");
boolean m = true;
boolean c = false;
if (!c) {
System.out.println("Operador NOT funciona");
}
else {
valorInicial = 3*valorInicial;
}

boolean d = !(m||false);
if (d) {
System.out.println("Nao vai entrar aqui");
}
else {
System.out.println("Expressoes logicas funcionam");
}

if ((!c)&&true) {
System.out.println("Ultimo teste de operadores logicos");
}
else {
System.out.println("Expressoes logicas funcionam");
}

System.out.println(a);
System.out.println(b);
System.out.print("Fim dos testes ");
System.out.print(nome);
}
}