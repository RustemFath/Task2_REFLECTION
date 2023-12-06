package ru.mystudy;

public class TextA implements Textable {
    private String text;

    public TextA(String text) {
        this.text = text;
    }

    @Override
    public void print() {
        System.out.println("method print: " + text);
    }

    @Cache
    @Override
    public String get() {
        System.out.println("method get:");
        return text;
    }

    @Setter
    @Override
    public void setText(String text) {
        System.out.println("method setText: " + text);
        this.text = text;
    }
}
