package main;

import main.Polynomial;
import main.Rational;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class PolynomialTest {

    @Test
    public void equals() {
        Map<Integer, Rational> members1 = new HashMap<Integer, Rational>() {{
            put(1, new Rational(-1));
            put(4, new Rational(6));
            put(6, new Rational(7));
        }};
        Polynomial firstPolynomial = new Polynomial(members1);

        Map<Integer, Rational> members2 = new HashMap<Integer, Rational>() {{
            put(6, new Rational(-4));
            put(0, new Rational(5));
            put(2, new Rational(1));
            put(1, new Rational(-2));
            put(4, new Rational(1));
        }};
        Polynomial secondPolynomial = new Polynomial(members2);

        Map<Integer, Rational> members3 = new HashMap<Integer, Rational>() {{
            put(8, new Rational(0));
            put(6, new Rational(11));
            put(0, new Rational(-5));
            put(1, new Rational(1));
            put(2, new Rational(-1));
            put(4, new Rational(5));
        }};
        Polynomial thirdPolynomial = new Polynomial(members3);

        assertTrue(firstPolynomial.equals(secondPolynomial.plus(thirdPolynomial)));
        assertFalse(firstPolynomial.equals(firstPolynomial.plus(thirdPolynomial)));
    }


    @Test
    public void value() {
        Map<Integer, Rational> members1 = new HashMap<Integer, Rational>() {{
            put(1, new Rational(3));
            put(4, new Rational(6));
            put(6, new Rational(7));
        }};
        Polynomial firstPolynomial = new Polynomial(members1);

        Map<Integer, Rational> members2 = new HashMap<Integer, Rational>() {{
            put(1, new Rational(3));
            put(4, new Rational(6));
            put(6, new Rational(7));
        }};

        Map<Integer, Rational> members = new HashMap<>();
        Polynomial polynomial = new Polynomial(members);

        assertEquals(new Rational(837970), firstPolynomial.value(7));
        assertEquals(new Rational(7060030), firstPolynomial.value(10));
        assertEquals(new Rational(30220), firstPolynomial.value(4));
        assertEquals(new Rational(0), polynomial.value(3));
    }

    @Test
    public void plus() {
        Map<Integer, Rational> members1 = new HashMap<Integer, Rational>(){{
            put(1, new Rational(-3));
            put(4, new Rational(-2));
            put(2, new Rational(1));
            put(6, new Rational(7));
        }};
        Polynomial firstPolynomial = new Polynomial(members1);

        Map<Integer, Rational> members2 = new HashMap<Integer, Rational>() {{
            put(6, new Rational(4));
            put(0, new Rational(-5));
            put(1, new Rational(2));
            put(4, new Rational(1));
        }};
        Polynomial secondPolynomial = new Polynomial(members2);

        Map<Integer, Rational> members3 = new HashMap<Integer, Rational>() {{
            put(6, new Rational(3));
            put(0, new Rational(5));
            put(2, new Rational(1));
            put(1, new Rational(-5));
            put(4, new Rational(-3));
        }};
        Polynomial thirdPolynomial = new Polynomial(members3);

        Map<Integer, Rational> members4 = new HashMap<Integer, Rational>() {{
            put(6, new Rational(10));
            put(0, new Rational(5));
            put(2, new Rational(2));
            put(1, new Rational(-8));
            put(4, new Rational(-5));
        }};
        Polynomial fourthPolynomial = new Polynomial(members4);

        assertEquals(firstPolynomial, secondPolynomial.plus(thirdPolynomial));
        assertEquals(fourthPolynomial, firstPolynomial.plus(thirdPolynomial));
    }

    @Test
    public void minus() {
        Map<Integer, Rational> members1 = new HashMap<Integer, Rational>() {{
            put(1, new Rational(-7));
            put(4, new Rational(-2));
            put(2, new Rational(2));
            put(0, new Rational(10));
            put(6, new Rational(-1));
        }};
        Polynomial firstPolynomial = new Polynomial(members1);

        Map<Integer, Rational> members2 = new HashMap<Integer, Rational>() {{
            put(6, new Rational(-4));
            put(0, new Rational(5));
            put(2, new Rational(1));
            put(1, new Rational(-2));
            put(4, new Rational(1));
        }};
        Polynomial secondPolynomial = new Polynomial(members2);

        Map<Integer, Rational> members3 = new HashMap<Integer, Rational>() {{
            put(6, new Rational(3));
            put(0, new Rational(5));
            put(2, new Rational(1));
            put(1, new Rational(-5));
            put(4, new Rational(-3));
        }};
        Polynomial thirdPolynomial = new Polynomial(members3);

        Map<Integer, Rational> members4 = new HashMap<Integer, Rational>() {{
            put(6, new Rational(2));
            put(0, new Rational(15));
            put(2, new Rational(3));
            put(1, new Rational(-12));
            put(4, new Rational(-5));
        }};
        Polynomial fourthPolynomial = new Polynomial(members4);

        assertEquals(firstPolynomial, secondPolynomial.plus(thirdPolynomial));
        assertEquals(fourthPolynomial, firstPolynomial.plus(thirdPolynomial));
    }

    @Test
    public void multiply() {
        Map<Integer, Rational> members1 = new HashMap<Integer, Rational>() {{
            put(10, new Rational(12));
            put(8, new Rational(-19));
            put(6, new Rational(5));
            put(7, new Rational(20));
            put(5, new Rational(-25));
        }};
        Polynomial firstPolynomial = new Polynomial(members1);

        Map<Integer, Rational> members2 = new HashMap<Integer, Rational>() {{
            put(6, new Rational(-4));
            put(4, new Rational(5));
        }};
        Polynomial secondPolynomial = new Polynomial(members2);

        Map<Integer, Rational> members3 = new HashMap<Integer, Rational>() {{
            put(2, new Rational(1));
            put(1, new Rational(-5));
            put(4, new Rational(-3));
        }};
        Polynomial thirdPolynomial = new Polynomial(members3);

        Map<Integer, Rational> members4 = new HashMap<Integer, Rational>() {{
            put(14, new Rational(-36));
            put(12, new Rational(69));
            put(11, new Rational(-120));
            put(10, new Rational(-34));
            put(9, new Rational(190));
            put(8, new Rational(-95));
            put(7, new Rational(-50));
            put(6, new Rational(125));
        }};
        Polynomial fourthPolynomial = new Polynomial(members4);

        assertEquals(firstPolynomial, secondPolynomial.multiply(thirdPolynomial));
        assertEquals(fourthPolynomial, firstPolynomial.multiply(thirdPolynomial));
    }

    @Test
    public void privateNum(){
        Map<Integer, Rational> members1 = new HashMap<Integer, Rational>() {{
            put(6, new Rational(5,3));
            put(3, new Rational(65,9));
            put(2, new Rational(-17,3));
            put(0, new Rational(845,27));
        }};
        Polynomial firstPolynomial = new Polynomial(members1);

        Map<Integer, Rational> members2 = new HashMap<Integer, Rational>() {{
            put(10, new Rational(5));
            put(6, new Rational(-7));
            put(3, new Rational(1));
            put(0, new Rational(-2));
        }};
        Polynomial secondPolynomial = new Polynomial(members2);

        Map<Integer, Rational> members3 = new HashMap<Integer, Rational>() {{
            put(4, new Rational(3));
            put(1, new Rational(-13));
            put(0, new Rational(6));
        }};
        Polynomial thirdPolynomial = new Polynomial(members3);

        Map<Integer, Rational> members4 = new HashMap<Integer, Rational>() {{
            put(2, new Rational(5,9));
        }};
        Polynomial fourthPolynomial = new Polynomial(members4);

        assertEquals(firstPolynomial, secondPolynomial.privateNum(thirdPolynomial));
        assertEquals(fourthPolynomial, firstPolynomial.privateNum(thirdPolynomial));
    }

    @Test
    public void remainder() {
        Map<Integer, Rational> members1 = new HashMap<Integer, Rational>() {{
            put(3, new Rational(-116));
            put(2, new Rational(34));
            put(1, new Rational(10985, 27));
            put(0, new Rational(-1708, 9));
        }};
        Polynomial firstPolynomial = new Polynomial(members1);

        Map<Integer, Rational> members2 = new HashMap<Integer, Rational>() {{
            put(10, new Rational(5));
            put(6, new Rational(-7));
            put(3, new Rational(1));
            put(0, new Rational(-2));
        }};
        Polynomial secondPolynomial = new Polynomial(members2);

        Map<Integer, Rational> members3 = new HashMap<Integer, Rational>() {{
            put(4, new Rational(3));
            put(1, new Rational(-13));
            put(0, new Rational(6));
        }};
        Polynomial thirdPolynomial = new Polynomial(members3);

        Map<Integer, Rational> members4 = new HashMap<Integer, Rational>() {{
            put(3, new Rational(-116));
            put(2, new Rational(34));
            put(1, new Rational(10985, 27));
            put(0, new Rational(-1708, 9));
        }};
        Polynomial fourthPolynomial = new Polynomial(members4);

        assertEquals(firstPolynomial, secondPolynomial.remainder(thirdPolynomial));
        assertEquals(fourthPolynomial, firstPolynomial.remainder(thirdPolynomial));
        assertEquals("-116x^3 + 34x^2 + 10985/27x - 1708/9", firstPolynomial.toString());
    }

    @Test(expected = Exception.class)
    public void testException(){
        Map<Integer, Rational> members1 = new HashMap<Integer, Rational>() {{
            put(-3, new Rational(-116));
            put(2, new Rational(34));
            put(1, new Rational(10985, 27));
            put(0, new Rational(-1708, 9));
        }};
        Polynomial firstPolynomial = new Polynomial(members1);

        Map<Integer, Rational> members2 = new HashMap<>();
        Polynomial secondPolynomial = new Polynomial(members2);

        firstPolynomial.value(6);
        firstPolynomial.remainder(secondPolynomial);
        firstPolynomial.privateNum(secondPolynomial);
    }
}
