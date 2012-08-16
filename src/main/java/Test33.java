/**
 * @author liubida 2012-3-3 下午2:34:35
 */
public class Test33 {

    class A {

        public String show(D obj) {
            return ("AD");
        }

        public String show(A obj) {
            return ("AA");
        }
    }

    public static void main(String[] args) {
        Test33 t = new Test33();
        A a1 = t.new A();
        A a2 = t.new B();
        B b = t.new B();
        C c = t.new C();
        D d = t.new D();
        System.out.println(a1.show(b));// AA
        System.out.println(a1.show(c));// AA
        System.out.println(a1.show(d));// AD

        System.out.println(a2.show(b));// BA

        System.out.println(a2.show(c));// BA
        System.out.println(a2.show(d));// AD
        System.out.println(b.show(b));// BB
        System.out.println(b.show(c));// BB
        System.out.println(b.show(d));// AD
    }

    class B extends A {

        public String show(B obj) {
            return ("BB");
        }

        public String show(A obj) {
            return ("BA");
        }
    }

    class C extends B {
    }

    class D extends B {
    }
}
