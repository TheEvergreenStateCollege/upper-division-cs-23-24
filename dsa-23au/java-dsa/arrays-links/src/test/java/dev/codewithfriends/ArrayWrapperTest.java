package dev.codewithfriends;

import java.util.List;
public class ArrayWrapperTest extends ListWrapperFactory {

    @Override
    public List<String> createList(int maxSize) {
        return new ArrayWrapper<String>(maxSize);
    }

}

