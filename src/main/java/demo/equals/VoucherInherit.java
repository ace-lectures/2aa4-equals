package demo.equals;

import java.util.Objects;

public class VoucherInherit extends Money {

    protected final String company;

    public VoucherInherit(int amount, String currencyCode, String company) {
        super(amount, currencyCode);
        this.company = company;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        VoucherInherit that = (VoucherInherit) o;
        return Objects.equals(company, that.company);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), company);
    }
}
