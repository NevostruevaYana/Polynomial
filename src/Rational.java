public class Rational {
    private final static Rational ZERO = new Rational(0);

    private int num;
    private int den;

    public int num() { return num; }
    public int den() { return den; }

    public Rational(int numerator, int denominator) {
        if (denominator == 0) {
            throw new ArithmeticException("denominator is zero");
        }
        int g = greatestCommonDivisor(numerator, denominator);
        num = numerator / g;
        den = denominator / g;
        if (den < 0) {
            den = -den;
            num = -num;
        }
    }

    public Rational(int num) {
        this.num = num;
        den = 1;
    }

    private int compareTo(Rational other) { //Comparable
        int lhs = this.num * other.den;
        int rhs = this.den * other.num;
        if (lhs < rhs) return -1;
        if (lhs > rhs) return +1;
        return 0;
    }

    private static int greatestCommonDivisor(int m, int n) {
        if (m < 0) m = -m;
        if (n < 0) n = -n;
        if (0 == n) return m;
        else return greatestCommonDivisor(n, m % n);
    }

    private static int lcm(int m, int n) {
        if (m < 0) m = -m;
        if (n < 0) n = -n;
        return m * (n / greatestCommonDivisor(m, n));
    }

    public Rational multiply(Rational b) {
        Rational a = this;
        Rational c = new Rational(a.num, b.den);
        Rational d = new Rational(b.num, a.den);
        return new Rational(c.num * d.num, c.den * d.den);
    }

    public Rational plus(Rational b) {
        Rational a = this;
        if (a.compareTo(ZERO) == 0) return b;
        if (b.compareTo(ZERO) == 0) return a;

        int f = greatestCommonDivisor(a.num, b.num);
        int g = greatestCommonDivisor(a.den, b.den);

        Rational s = new Rational((a.num / f) * (b.den / g) + (b.num / f) * (a.den / g),
                lcm(a.den, b.den));

        s.num *= f;
        return s;
    }

    public Rational negate() {
        return new Rational(-num, den);
    }

    public Rational minus(Rational b) {
        Rational a = this;
        return a.plus(b.negate());
    }

    public Rational reciprocal() {
        return new Rational(den, num);
    }

    public Rational abs() {
        if (num >= 0) return this;
        else return negate();
    }

    public Rational divides(Rational b) {
        Rational a = this;
        return a.multiply(b.reciprocal());
    }

    public Rational multiplicationByAnInteger(int b){
        Rational a = this;
        return new Rational(a.num * b, a.den);
    }

    public boolean equals(Rational other)
    {
        return this.num == other.num && this.den == other.den;
    }

    @Override
    public String toString() {
        if (den == 1) return num + "";
        else return num + "/" + den;
    }

}