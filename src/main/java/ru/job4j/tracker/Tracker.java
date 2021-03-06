package ru.job4j.tracker;

import java.io.Serializable;
import java.util.Arrays;

public class Tracker {
    private final Item[] items = new Item[100];
    private int ids = 1;
    private int size = 0;

    public Item add(Item item) {
        item.setId(ids++);
        items[size++] = item;
        return item;
    }

    public Item[] findAll(){
        Item[] namesWithoutNull = new Item[items.length];
        int size = 0;
        for(int i = 0; i < items.length; i++) {
            Item name = items[i];
            if(items[i] != null) {
                namesWithoutNull[size] = name;
                size++;
            }
        }
        return Arrays.copyOf(namesWithoutNull, size);
    }

    public Item[] findByName(String key) {
        int junk = 0;
        Item[] neededName = new Item[size];
        for(int i = 0; i < size; i++) {
            Item name = items[i];
            if(key.equals(name.getName())) {
                neededName[junk] = name;
                junk++;
            }
        }
        return Arrays.copyOf(neededName, junk);
    }

    public Item findById(int id) {
        int index = indexOf(id);
        return index != -1 ? items[index] : null;
    }

    private int indexOf(int id) {
        int rsl = -1;
        for (int index = 0; index < size; index++) {
            if (items[index].getId() == id) {
                rsl = index;
                break;
            }
        }
        return rsl;
    }

    public Serializable replace(int id, Item item) {
        if(id < 0) {
            return "Id is less then 0, its not possible to find it";
        } else {
            int neededIndex = indexOf(id);
            int neededId = items[neededIndex].getId();
            if (neededIndex != -1) {
                items[neededIndex] = item;
                items[neededIndex].setId(neededId);
                return true;
            } else {
                return false;
            }
        }
    }

    public Serializable delete(int id) {
        if (id < 0) {
            return "Id is less then 0, its not possible to find it";
        } else {
            int index = indexOf(id);
            int start = index + 1;
            System.arraycopy(items, start, items, index, size - index);
            items[size - 1] = null;
            size--;
            return true;
        }
    }
}
