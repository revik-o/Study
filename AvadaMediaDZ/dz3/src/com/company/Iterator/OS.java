package com.company.Iterator;

public class OS implements Collection {

    String[] s = {"Windows", "Linux", "MacOS"};

    @Override
    public Iterator getIterator() {
        return new It();
    }

    private class It implements Iterator {

        int index = 0;

        @Override
        public boolean hasNext() {
            return index < s.length;
        }

        @Override
        public Object next() {
            return s[index++];
        }
    }

}
