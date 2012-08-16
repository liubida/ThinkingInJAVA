/**
 * @author liubida 2012-3-1 下午8:04:51
 */

class A33 {
}

class B33 extends A33 {
}

class C33 extends B33 {
}

public class Gener<T> {
//
//    public boolean equals(T value) {
//        return true;
//    }
    
    T print() {
        System.out.println("parent: ");
        return null;
    }

    public static void main(String[] args) {
//        Gener<A33> s = new SonGener();
//        s.print();
    }
}

class SonGener extends Gener<CharSequence> {

    String print() {
        System.out.println("son: ");
        return null;
    }
}
