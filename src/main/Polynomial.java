package main;

import com.sun.istack.internal.NotNull;

import java.util.*;

/**
 * @author NevostruevaYana
 * @version 1
 *
 * Полином, содержащий в качестве коэффициентов рациональные числа.
 *
 * Поле:    карта с неотрицательными ключами-степенями полинома и связанными
 *          соответственно с ними рациональными значениями-коэффициентами.
 *
 * Методы:  сравнение двух полиномов на равенство,
 *          подсчет значения при данном (целом) x,
 *          сложение двух полиномов,
 *          вычитание двух полиномов,
 *          умножение двух полиномов,
 *          деление двух полиномов,
 *          нахождение остатка от деления двух полиномов.
 */

public class Polynomial {
    private final static Rational RATIONAL_ZERO = new Rational(0);
    private final static Map<Integer, Rational> MEMBERS_ZERO =
            new HashMap<Integer, Rational>(){{put(0, new Rational(0));}};

    //Создаем Map, содержащую ключом степень одночлена полинома и значением
    //его коэффициент(сразу сортируем в порядке убывания)

    /**
     * Поле карты, содежащей ключами степени, а значениями коэффициенты полинома.
     */
    private Map<Integer, Rational> members = new TreeMap<>(Collections.reverseOrder());

    /**
     * Конструктор
     *
     * @param members Значение карты, содежащей ключами степени, а значениями коэффициенты полинома.
     *
     * @throws NullPointerException
     *         Если значение равно null в {@code members}
     *
     * @throws ArithmeticException
     *         Если в {@code members} хоть один из ключей меньше нуля
     */
    public Polynomial(@NotNull Map<Integer, Rational> members) {
        this.members.putAll(members);
        for (Rational a: this.members.values()) {
            if (a == null)
                throw new NullPointerException("coefficient is equal to null");
        }

        //Удаляем из полинома одночлены, коэффициенты которых равны нулю

        Iterator<Map.Entry<Integer, Rational>> pairs = this.members.entrySet().iterator();
        while(pairs.hasNext()){
            Map.Entry<Integer, Rational> pair = pairs.next();
            if (pair.getKey() < 0)
                throw new ArithmeticException("a polynomial contains a degree that is less than zero");
            if (pair.getValue().equals(RATIONAL_ZERO))
                pairs.remove();
        }
        if (0 == this.members.size()) {
            this.members.put(0,new Rational(0));
        }
    }

    /**
     * Расчет значения полинома по заданному целому числу x
     * @param x Значение, по которому рассчитывается конечное целое значение полинома
     * @return Значение полинома при заданном (целом) x, <b>null</b>, если исходный полином равен null
     */
    public Rational value(int x){
        if (this.members == null)
            return null;
        Rational result = RATIONAL_ZERO;
        for (Integer degree: members.keySet()) {
            int count  = 1;
            for (int i = degree; i > 0; i--) {
                count *= x;
            }
            result = result.plus(members.get(degree).times(new Rational(count)));
        }
        return result;
    }

    /**
     * Сложение двух полиномов
     * @param other Суммируемый полинома
     * @return Результат сложения двух полиномов, <b>null</b>, если исходный полином равен null
     */
    public Polynomial plus(Polynomial other){
        Map<Integer, Rational> members = new HashMap();
        members.putAll(this.members);

        //Проходимся по степеням второго полинома

        for (Integer degree : other.members.keySet()){
            Rational otherCoefficient = other.members.get(degree);
            Rational thisCoefficient = members.get(degree);

            //Если текущая степень второго полинома содержится в первом полиноме, совершаем
            //операцию сложения и добавляем пару в Map, в ином случае просто добавляем пару

            if (thisCoefficient != null){
                otherCoefficient = otherCoefficient.plus(thisCoefficient);
            }
            members.put(degree, otherCoefficient);
        }
        return new Polynomial(members);
    }

    /**
     * Вычитание двух полиномов
     * @param other Вычитаемый полином
     * @return Результат операции вычитания двух полиномов, <b>null</b>, если исходный полином равен null
     */
    public Polynomial minus( Polynomial other){
        Map<Integer, Rational> members = new HashMap();
        members.putAll(this.members);
        for (Integer degree : other.members.keySet()){
            Rational otherCoefficient = other.members.get(degree);
            Rational thisCoefficient = members.get(degree);

            //Если текущая степень второго полинома содержится в первом полиноме, совершаем операцию
            //вычитания и добавляем пару в Map, в ином случае добавляем пару, значением(коэффициентом одночлена)
            // которого будет являться коэффициент второго полинома с противоположным знака

            if (thisCoefficient != null) {
                members.put(degree, thisCoefficient.minus(otherCoefficient));
            } else {
                members.put(degree, otherCoefficient.negate());
            }
        }
        return new Polynomial(members);
    }

    /**
     * Перемножение двух полиномов
     * @param other Умножаемый полином
     * @return Результат операции вычитания двух полиномов, <b>null</b>, если исходный полином равен null
     */
    public Polynomial multiply(Polynomial other) {
        Map<Integer, Rational> result = new HashMap();
        for (Map.Entry<Integer, Rational> i : this.members.entrySet()) {
            for (Map.Entry<Integer, Rational> j : other.members.entrySet()) {
                Integer degree = i.getKey() + j.getKey();
                Rational coefficient = i.getValue().times(j.getValue());
                if (result.get(degree) != null) {
                    coefficient = coefficient.plus(result.get(degree));
                }
                result.put(degree, coefficient);
            }
        }
        return new Polynomial(result);
    }

    /**
     * Деление двух полиномов
     * @param other Полином делитель
     * @return Результат операции деления двух полиномов, <b>null</b>, если исходный полином равен null
     */
    public Polynomial privateNum(Polynomial other){

        if (other.members == MEMBERS_ZERO) {
            throw new ArithmeticException("the divisor is zero");
        }

        //Нахдим степень полинома-делителя и коэффициент при ней

        String string1 = other.members.keySet().toArray()[0].toString();
        int degree1 = Integer.parseInt(string1);
        Rational coefficient1 = other.members.get(degree1);

        Polynomial dividend = this;
        Polynomial residue = this;
        Map<Integer, Rational> privateNumber = new HashMap<>();

        boolean f = true;

        while(f) {

            //Находим степень делимого полинома и коэффициент при ней

            String string = dividend.members.keySet().toArray()[0].toString();
            int degree = Integer.parseInt(string);
            Rational coefficient = dividend.members.get(degree);

            //Находим стпень(а) и коэффициент(b) одночлена, с помощью которого будем совершать деление(умножим его
            //на полином-делитель и вычтем из делимого полинома

            Rational a = new Rational(coefficient.numerator() * coefficient1.denominator(),
                    coefficient.denominator() * coefficient1.numerator());
            int b = degree - degree1;

            //В случае, когда степень полинома-делителя больше степени делимого полинома, назначаем
            //остатком делимый полином

            if (b < 0) {
                return new Polynomial(privateNumber);
            }

            Map<Integer, Rational> monomial = new HashMap<>();
            monomial.put(b, a);
            privateNumber.put(b, a);
            dividend = new Polynomial(monomial);
            dividend = residue.minus(other.multiply(dividend));
            residue = dividend;
            if (b == 0) f = false;
        }
        return new Polynomial(privateNumber);
    }

    /**
     * Остаток от деления двух полиномов
     * @param other Делимый полином
     * @return  Остаток от деления двух полиномов, <b>null</b>, если исходный полином равен null
     */
    public Polynomial remainder(Polynomial other) {
        return this.minus(other.multiply(this.privateNum(other)));
    }

    //Получение строкового представления знака одночлена в зависимости от его размещения
    //в полиноме и знака его коэффициента.

    private String sign(boolean isFirst, Rational coefficient) {
        if (coefficient.numerator() < 0) {
            return (isFirst) ? "-" : " - ";
        } else {
            return (isFirst) ? "" : " + ";
        }
    }

    //Получение строкового представления коэффициента одночлена в зависимости от степени.

    private String coefficient(Rational coefficient, int degree) {
        return (!coefficient.abs().equals(new Rational(1)) || degree == 0) ?
                coefficient.abs().toString() : "";
    }

    //Получение строкового представления степени одночлена.

    private String degree(int degree) {
        switch (degree) {
            case 0: return "";
            case 1: return "x";
            case -1: return "x";
            default: return "x^" + degree;
        }
    }

    @Override
    public boolean equals(@NotNull Object obj) {
        if(obj == this)
            return true;

        if(obj == null)
            return false;

        if(!(getClass() == obj.getClass()))
            return false;
        else {
            Polynomial tmp = (Polynomial) obj;
            if (tmp.members.equals(this.members))
                return true;
            else
                return false;
        }
    }

    @Override
    public String toString() {
        if (members == MEMBERS_ZERO) {
            return "0";
        }
        boolean isFirst = true;
        StringBuilder result = new StringBuilder();
        for (Map.Entry<Integer, Rational> monomial : members.entrySet()) {
            Rational coefficient = monomial.getValue();
            int degree = monomial.getKey();
            result.append(sign(isFirst, coefficient)
                    + coefficient(coefficient, degree)
                    + degree(degree));
            isFirst = false;
        }
        return result.toString();
    }
}