package demo.equals;

public class Voucher {

    private final String store;
    private final Money money;

    public Voucher(String store, Money money) {
        this.store = store;
        this.money = money;
    }

    public Money value() {
        return this.money;
    }

    public Voucher(int m, String c, String store) {
        this(store, new Money(m, c));
    }



}
