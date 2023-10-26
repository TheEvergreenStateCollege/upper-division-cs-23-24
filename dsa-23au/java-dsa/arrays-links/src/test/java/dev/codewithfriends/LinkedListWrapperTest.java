package dev.codewithfriends;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import java.util.List;
import java.util.ArrayList;

public class LinkedListWrapperTest extends ListWrapperFactory {

    @Override
    public List<String> createList(int maxSize) {
        return new LinkedListWrapper<String>(maxSize);
    }
}