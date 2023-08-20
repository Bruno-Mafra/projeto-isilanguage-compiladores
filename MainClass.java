import java.util.Scanner;
public class MainClass{
public static void main(String args[]) {
Scanner reader = new Scanner(System.in);
int a = 7;
double k = 3.14+a;
boolean b;
String c = "teste";
a = reader.nextInt();
k = reader.nextDouble();
a = 11;
c = "teste2";
b = false;
b = true&&false;
boolean o = !(true&&false);
double y = 2.0*(1.0+k/(5.0+3.0*k));
double w = 3*1.0*k;
if (18>2.4) {
System.out.println(c);
}

if (true) {
a = 2;
if (true) {
if (true) {
a = 3;
}

}
else {
a = 4;
}

}
else {
a = 4;
}

if (true&&true) {
a = 3;
}
else {
a = 4;
}

if (b&&o) {
a = 4;
}
else {
a = 4;
}

if (!b&&o) {
a = 5;
}
else {
a = 4;
}

if (!(b&&o)||(b||o)) {
a = 6;
}
else {
a = 4;
}

if (a>7.5) {
System.out.println(a);
}
else {
System.out.println(b);
}

if (2>1) {
a = 2;
}
else {
System.out.println(b);
}

if (3>2) {
a = 3;
}
else {
a = 1;
}

while (a<5) {
a = a+1;
}

do {
a = a+1;
} while (a<5);
System.out.println("Uma string");
System.out.println(" Uma string bem\n	\n	grande");
System.out.println(123);
System.out.println(true);
System.out.println(1+1);
System.out.println(a+a+2);
}
}