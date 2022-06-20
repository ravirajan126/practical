import java.util.HashMap;

enum Operator {
    NEG("~"), AND("&"), OR("|"), IMP("->"), IFF("<->");
    private final String symbol;

    Operator(String symbol) {
        this.symbol = symbol;
    }

    public String toString() {
        return symbol;
    }
}

class PropConstant implements Wff {
    private final String propConstant;

    public PropConstant(String str) {
        this.propConstant = str;
    }

    public String toString() {
        return propConstant;
    }

    public boolean eval(Valuation val) {
        return val.get(this);
    }
}

class Valuation {
    HashMap<PropConstant, Boolean> val = new HashMap<>();

    public boolean get(PropConstant propConstant) {
        return val.get(propConstant);
    }

    public void put(PropConstant propConstant, boolean tv) {
        val.put(propConstant, tv);
    }
}

public class PropLogicLauncher {
    public static void main(String[] args) {
        PropConstant p = new PropConstant("P");
        PropConstant q = new PropConstant("Q");
        PropConstant r = new PropConstant("R");

        Wff e0 = new NotWff(p);
        Wff e1 = new AndWff(q, e0);
        Wff e2 = new OrWff(e1, p);
        Wff e3 = new IfWff(e1, p);
        Wff e4 = new NotWff(e2);

        System.out.println("Display form of Wff e0 is: " + e0);
        System.out.println("Display form of Wff e1 is: " + e1);
        System.out.println("Display form of Wff e2 is: " + e2);
        System.out.println("Display form of Wff e3 is: " + e3);
        System.out.println("Display form of Wff e4 is: " + e4);
        System.out.println();

        Valuation val = new Valuation();
        val.put(p, true);
        val.put(q, false);
        val.put(r, true);

        System.out.println("The value of Wff e0 is: " + e0.eval(val));
        System.out.println("The value of Wff e1 is: " + e1.eval(val));
        System.out.println("The value of Wff e2 is: " + e2.eval(val));
        System.out.println("The value of Wff e3 is: " + e3.eval(val));
        System.out.println("The value of Wff e4 is: " + e4.eval(val));
    }
}