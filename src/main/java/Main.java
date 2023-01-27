import demo.equals.Money;
import demo.equals.Voucher;
import demo.equals.VoucherInherit;
import demo.hashcode.Team;
import demo.hashcode.TeamBoth;

import java.util.Map;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        System.out.println("----------------");
        System.out.println("|   EQUALS     |");
        System.out.println("----------------");
        Money expense = new Money(55, "CAD");
        Money income = new Money(55, "CAD");
        System.out.println("expense == expense? " + expense.equals(expense));
        System.out.println("expense == income? " + expense.equals(income));
        System.out.println("----------------");

        Money cash = new Money(42, "CAD");
        Money voucherI = new VoucherInherit(42, "CAD", "AirCanada");
        System.out.println("voucher == cash? " + voucherI.equals(cash));
        System.out.println("cash == voucher? " + cash.equals(voucherI));
        System.out.println("----------------");

        Voucher voucher = new Voucher(42, "CAD", "AirCanada");
        System.out.println("voucher == cash? " + voucher.equals(cash));
        System.out.println("cash == voucher? " + cash.equals(voucher));
        System.out.println("cash == voucher.value()? " + cash.equals(voucher.value()));
        System.out.println("voucher.value() == cash? " + voucher.value().equals(cash));

        System.out.println("\n----------------");
        System.out.println("|   HASHCODE   |");
        System.out.println("----------------\n");

        Map<Team, String> nhl = new HashMap<>();
        nhl.put(new Team("Toronto", "hockey"), "Maple leafs");
        nhl.put( new Team("Ottawa", "hockey"), "senators");
        nhl.put( new Team("Montreal", "hockey"), "canadiens");

        Team leafs = new Team("Toronto", "hockey");
        System.out.println("Toronto's hockey team? " + nhl.getOrDefault(leafs, "unknown"));
        System.out.println("Raptors as a key? " + nhl.keySet().contains(leafs));
        System.out.println("Raptors as an element? " + nhl.keySet().stream().toList().contains(leafs));
        System.out.println("----------------");

        Map<TeamBoth, String> nhlBis = new HashMap<>();
        TeamBoth leafBis = new TeamBoth("Toronto", "hockey");
        nhlBis.put(new TeamBoth("Toronto", "hockey"), "Maple leafs");
        nhlBis.put(new TeamBoth("Ottawa", "hockey"), "senators");
        nhlBis.put(new TeamBoth("Montreal", "hockey"), "canadiens");
        System.out.println("Toronto's hockey team? " + nhlBis.getOrDefault(leafBis, "unknown"));
    }

}
