
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class PolynomialTest {


    @Test
    public void equals() {
        Map<Integer, Rational> members1 = new HashMap<>();
        members1.put(1, new Rational(-1));
        members1.put(4, new Rational(6));
        members1.put(6, new Rational(7));
        Polynomial firstPolynomial = new Polynomial(members1);

        Map<Integer, Rational> members2 = new HashMap<>();
        members2.put(6, new Rational(-4));
        members2.put(0, new Rational(5));
        members2.put(2, new Rational(1));
        members2.put(1, new Rational(-2));
        members2.put(4, new Rational(1));
        Polynomial secondPolynomial = new Polynomial(members2);

        Map<Integer, Rational> members3 = new HashMap<>();
        members3.put(8, new Rational(0));
        members3.put(6, new Rational(11));
        members3.put(0, new Rational(-5));
        members3.put(1, new Rational(1));
        members3.put(2, new Rational(-1));
        members3.put(4, new Rational(5));
        Polynomial thirdPolynomial = new Polynomial(members3);

        assertEquals(false, firstPolynomial.equals(secondPolynomial.plus(thirdPolynomial)));
    }


    @Test
    public void value() {
        Map<Integer, Rational> members1 = new HashMap<>();
        members1.put(1, new Rational(0));
        members1.put(4, new Rational(6));
        members1.put(6, new Rational(7));
        Polynomial firstPolynomial = new Polynomial(members1);
        assertEquals(new Rational(837949), firstPolynomial.value(7));
    }

    @Test
    public void plus() {
        Map<Integer, Rational> members1 = new HashMap<>();
        members1.put(1, new Rational(-3));
        members1.put(4, new Rational(-2));
        members1.put(6, new Rational(7));
        Polynomial firstPolynomial = new Polynomial(members1);

        Map<Integer, Rational> members2 = new HashMap<>();
        members2.put(6, new Rational(-4));
        members2.put(0, new Rational(5));
        members2.put(2, new Rational(1));
        members2.put(1, new Rational(-2));
        members2.put(4, new Rational(1));
        Polynomial secondPolynomial = new Polynomial(members2);

        Map<Integer, Rational> members3 = new HashMap<>();
        members3.put(6, new Rational(3));
        members3.put(0, new Rational(5));
        members3.put(2, new Rational(1));
        members3.put(1, new Rational(-5));
        members3.put(4, new Rational(-1));
        Polynomial thirdPolynomial = new Polynomial(members3);
    }

    @Test
    public void minus() {
        Map<Integer, Rational> members1 = new HashMap<>();
        members1.put(1, new Rational(-1));
        members1.put(4, new Rational(6));
        members1.put(6, new Rational(7));
        Polynomial firstPolynomial = new Polynomial(members1);

        Map<Integer, Rational> members2 = new HashMap<>();
        members2.put(6, new Rational(-4));
        members2.put(0, new Rational(5));
        members2.put(2, new Rational(1));
        members2.put(1, new Rational(-2));
        members2.put(4, new Rational(1));
        Polynomial secondPolynomial = new Polynomial(members2);

        System.out.println(firstPolynomial.minus(secondPolynomial));
    }

    @Test
    public void multiply() {
    }

    @Test
    public void privateNum(){
        Map<Integer, Rational> members1 = new HashMap<>();
        members1.put(5, new Rational(8));
        members1.put(4, new Rational(-3));
        members1.put(0, new Rational(9));
        Polynomial firstPolynomial = new Polynomial(members1);

        Map<Integer, Rational> members2 = new HashMap<>();
        members2.put(4, new Rational(3));
        members2.put(3, new Rational(-7));
        members2.put(0, new Rational(1));
        Polynomial secondPolynomial = new Polynomial(members2);

        System.out.println(firstPolynomial);
        System.out.println(secondPolynomial);

        System.out.println(firstPolynomial.privateNum(secondPolynomial));
    }

    @Test
    public void remainder(){
        Map<Integer, Rational> members1 = new HashMap<>();
        members1.put(5, new Rational(8));
        members1.put(4, new Rational(-3));
        members1.put(0,new Rational(9));
        Polynomial firstPolynomial = new Polynomial(members1);

        Map<Integer, Rational> members2 = new HashMap<>();
        members2.put(4, new Rational(3));
        members2.put(3, new Rational(-7));
        members2.put(0, new Rational(1));
        Polynomial secondPolynomial = new Polynomial(members2);

        System.out.println(firstPolynomial);
        System.out.println(secondPolynomial);

        System.out.println(firstPolynomial.remainder(secondPolynomial));
    }
}