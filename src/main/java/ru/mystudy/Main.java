package ru.mystudy;

public class Main {
    public static void main(String[] args) {
        Textable texta = Utils.cache(new TextA("text1"));

        texta.print();
        texta.print();

        System.out.println(texta.get());
        System.out.println(texta.get());

        texta.setText("text2");

        System.out.println(texta.get());
        texta.print();
        System.out.println(texta.get());
    }
}