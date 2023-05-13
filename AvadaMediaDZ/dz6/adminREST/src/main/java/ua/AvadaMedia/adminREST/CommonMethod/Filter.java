package ua.AvadaMedia.adminREST.CommonMethod;

public class Filter {

    public static Object[] filterForSortedArray(Object[] objectsArgs) {
        int count = 0;
        for (Object o : objectsArgs) {
            if (o == null) break;
            ++count;
        }
        Object[] objects = new Object[count];
        System.arraycopy(objectsArgs, 0, objects, 0, count);
        return objects;
    }

}