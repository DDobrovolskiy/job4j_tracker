package ru.job4j.tracker;

public class FindByName implements UserAction {
    @Override
    public String name() {
        return "Find items by name";
    }

    @Override
    public boolean execute(Input input, Tracker tracker) {
        System.out.println("=== Find items by name ====");
        String name = input.askStr("Enter new name: ");
        Item[] findItems = tracker.findByName(name);
        if (findItems.length > 0) {
            for (Item item : findItems) {
                System.out.println(item);
            }
        } else {
            System.out.println("Заявки с таким именем не найдены");
        }
        return true;
    }
}
