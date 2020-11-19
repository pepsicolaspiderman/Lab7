package Zadania;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class Var4_project1 {
    public static void main(String[]args) {
        try {
            File folder = new File("C:\\Java");
            if (!folder.exists())
                folder.mkdir();
            File f1 = new File("C:\\Java\\Var4_project1.txt");
            f1.createNewFile();

            File f2 = new File("C:\\Java\\Var4_project1_overprice.txt");
            f2.createNewFile();
            RandomAccessFile rr = new RandomAccessFile(f2, "rw");
            long rsize = rr.length();
            String rname, rmadeby;
            int runit, rprice;

            RandomAccessFile rf = new RandomAccessFile(f1, "rw");
            long size = rf.length();
            Scanner sc = new Scanner(System.in, "cp1251");
            System.out.print("Введите количество товаров для записи в файл\n" + "=> ");
            int tov = sc.nextInt();
            sc.nextLine();
            String name, madeby;
            int unit, price;

            for (int i = 0; i < tov; i++) {
                System.out.print("Введите название " + (i + 1) + " товара =>");
                name = sc.next();
                rf.seek(rf.length());
                rf.writeUTF(name);
                for (int j = 0; j < 20 - name.length(); j++)
                    rf.writeByte(1);

                System.out.print("Введите производителся товара =>");
                madeby = sc.next();
                rf.writeUTF(madeby);
                for (int j = 0; j < 20 - madeby.length(); j++)
                    rf.writeByte(1);

                System.out.print("Введите количество данного товара =>");
                unit = sc.nextInt();
                sc.nextLine();
                rf.writeInt(unit);

                System.out.print("Введите цену данного товара =>");
                price = sc.nextInt();
                rf.writeInt(price);

                if (price>1000){
                    rr.seek(rr.length());
                    rname = name;
                    rr.writeUTF(rname);
                    rmadeby = madeby;
                    rr.writeUTF(rmadeby);
                    runit = unit;
                    rr.writeInt(runit);
                    rprice = price;
                    rr.writeInt(rprice);
                }
                sc.nextLine();
            }
            rf.close();
            rr.close();


            rf = new RandomAccessFile(f1, "r");
            rf.seek(0);
            System.out.println("Информация о товарах");
            System.out.println("Название \t\t Производитель \t\t Количество \t\t Цена");
            for (int i = 0; i < tov; i++) {
                name = rf.readUTF();
                for (int j = 0; j < 20 - name.length(); j++)
                    rf.readByte();

                madeby = rf.readUTF();
                for (int j = 0; j < 20 - madeby.length(); j++)
                    rf.readByte();

                unit = rf.readInt();

                price = rf.readInt();

                System.out.println(name + "\t\t\t\t" + madeby + "\t\t\t\t" + unit + "\t\t\t\t" + price);
            }
            rf.close();


        } catch (IOException e) {
            System.out.println("End of file " + e);
        }
    }}
