package top.daoyang.stack;

import top.daoyang.array.Array;

import java.util.Arrays;

public class ArrayStack<E> implements Stack<E> {

    private Array<E> data;

    public ArrayStack() {
        data = new Array<>();
    }

    @Override
    public int getSize() {
        return data.getSize();
    }

    @Override
    public boolean isEmpty() {
        return data.isEmpty();
    }

    @Override
    public void push(E e) {
        data.addLast(e);
    }

    @Override
    public E pop() {
        return data.removeLast();
    }

    @Override
    public E peek() {
        return data.get(data.getSize() - 1);
    }

    @Override
    public String toString() {
        System.out.println("StackArray: size=" + getSize());

        StringBuilder start = new StringBuilder("[ ");
        for (int i = 0; i < getSize(); i++) {
            start.append(data.get(i));

            if (i != getSize() - 1)
                start.append(", ");
        }
        start.append(" ]");
        start.append(" top");

        return start.toString();
    }

    public boolean validParentheses(String[] parentheses) {

        for (String t: parentheses) {
            if (t.equals("[") ||t.equals("(") || t.equals("{"))
                push((E) t);
            else {
                if (t.equals("]") && !"[".equals(pop()))
                    return false;
                if (t.equals("}") && !"{".equals(pop()))
                    return false;
                if (t.equals(")") && !"(".equals(pop()))
                    return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        ArrayStack<String> arrayStack = new ArrayStack<>();

        arrayStack.push("0");
        arrayStack.push("1");
        arrayStack.push("2");
        arrayStack.push("3");

        System.out.println(arrayStack.validParentheses(new String[]{"{", "[", "]", "}"}));

        System.out.println(arrayStack);
    }
}
