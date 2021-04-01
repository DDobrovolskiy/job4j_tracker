package ru.job4j.ex;

public class FindEl {
    public static int indexOf(String[] value, String key) throws ElementNotFoundException {
        int rsl = -1;
        int count = 0;
        for (String item : value) {
            if (key.equals(item)) {
                rsl = count;
                break;
            }
            count++;
        }
        if(rsl == -1) {
            throw new ElementNotFoundException("Елемент не найден!");
        }
        return rsl;
    }

    public static void main(String[] args) {
        try {
            FindEl.indexOf(new String[]{"Good", "Bad", "Vel"}, "Normal");
        } catch (ElementNotFoundException e) {
            e.printStackTrace();
        }
    }
}
