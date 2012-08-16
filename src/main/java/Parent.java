/**
 * @author liubida 2012-2-17 下午1:52:11
 */

class A333 {
}

class B333 extends A333 {
}

class C333 extends B333 {
}

public class Parent {

    void print(Object a) {
        System.out.println("parent: " + a);
    }

    B333 eat(B333 a) {
        System.out.println("parent eat: ");
        return null;
    }

    public static void main(String[] args) {
        Parent c = new son();
        c.print("");
        c.eat(new C333());
    }
}

class son extends Parent {

    void print(String s) {
        System.out.println("son" + s);
    }

    B333 eat(B333 b) {
        System.out.println("son eat: ");
        return null;
    }
}
