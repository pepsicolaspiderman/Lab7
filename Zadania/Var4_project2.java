package Zadania;

import java.io.*;
import java.util.Scanner;

class Tovar implements Serializable{
    String name, madeby;
    int unit, price;
}

public class Var4_project2 {
    public static void main(String[] args) throws IOException, ClassNotFoundException{
        Scanner sc = new Scanner(System.in, "cp1251");
        File f1= new File("C:\\Java\\Var4_project2.txt");
        f1.createNewFile();
        File f2= new File("C:\\Java\\Var4_project2_overprice.txt");
        f2.createNewFile();

        FileOutputStream ohh = new FileOutputStream(f2);
        ObjectOutputStream uhh = new ObjectOutputStream(ohh);

        FileOutputStream oh = new FileOutputStream(f1);
        ObjectOutputStream uh = new ObjectOutputStream(oh);

        System.out.print("Введите количество товаров для записи в файл: ");
        int count = sc.nextInt();
        sc.nextLine();
        Tovar tovar;
        for (int i=0; i<count; i++) {
            tovar = new Tovar();
            System.out.print("Введите название товара =>");
            tovar.name = sc.nextLine();
            System.out.print("Производитель =>");
            tovar.madeby = sc.nextLine();
            System.out.print("Количество =>");
            tovar.unit = sc.nextInt();
            System.out.print("Цена =>");
            tovar.price = sc.nextInt();
            sc.nextLine();
            uh.writeObject(tovar);

            if(tovar.price>1000) uhh.writeObject(tovar);
        }
            uh.flush();
            uh.close();
            uhh.flush();
            uhh.close();

        FileInputStream ooh = new FileInputStream(f1);
        ObjectInputStream uuh = new ObjectInputStream(ooh);

        for (int j=0; j<count; j++) {
            tovar = (Tovar) uuh.readObject();
            System.out.println("Название товара: " + tovar.name + "\t\tПроизводитель: " + tovar.madeby +
                    "\t\tКоличество: " + tovar.unit + "\t\tЦена: " + tovar.price);
        }
        uuh.close();

        FileInputStream oohh = new FileInputStream(f2);
        ObjectInputStream uuhh = new ObjectInputStream(oohh);
        System.out.println("\nТовары с ценой выше 1000 руб: ");
        for (int k=0; k<count; k++) {
            tovar = (Tovar) uuhh.readObject();
            System.out.println("Название товара: " + tovar.name + "\t\tПроизводитель: " + tovar.madeby +
                    "\t\tКоличество: " + tovar.unit + "\t\tЦена: " + tovar.price);
        }
        uuhh.close();
    }

}
