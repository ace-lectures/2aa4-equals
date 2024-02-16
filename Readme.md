# Unicity / Equivalence Demo

- source: Baeldung
    - https://www.baeldung.com/java-equals-hashcode-contracts

## How to run it?

- to compile: mvn package
- to run: mvn -q exec:java


## Equality

### The `Money` class

```
public class Money {
    private final int amount;
    private final String currencyCode;
}
```

- Generate constructor

```
public Money(int amount, String currencyCode) {
    this.amount = amount;
    this.currencyCode = currencyCode;
}
```

- Generate Equals and hashcode

```
@Override
public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Money money)) return false;
    return amount == money.amount && Objects.equals(currencyCode, money.currencyCode);
}

@Override
public int hashCode() {
    return Objects.hash(amount, currencyCode);
}
```

#### Assess equality

In `Main::main`:

```
Money expense = new Money(55, "CAD");
Money income = new Money(55, "CAD");
System.out.println("expense == expense? " + expense.equals(expense));
System.out.println("expense == income? " + expense.equals(income));
```


### Inherited voucher

```
public class VoucherInherit extends Money {

    private final String company;

    public VoucherInherit(int amount, String currencyCode, String company) {
        super(amount, currencyCode);
        this.company = company;
    }
    
}
```

- Generate equals/hashcode automatically:

```
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
```

#### Assess equality

In `Main::main`:

```
Money cash = new Money(42, "CAD");
Money voucherI = new VoucherInherit(42, "CAD", "AirCanada");
System.out.println("voucher == cash? " + voucherI.equals(cash));
System.out.println("cash == voucher? " + cash.equals(voucherI));
```

Execution result (We just broke the equals relation!):

```
voucher == cash? false
cash == voucher? true
```

### Favor composition over Inheritance

Create a Voucher class:

```
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
}
```

#### Assess equality

```
Voucher voucher = new Voucher("AirCanada", new Money(42, "CAD"));
System.out.println("voucher == cash? " + voucher.equals(cash));
System.out.println("cash == voucher? " + cash.equals(voucher));
System.out.println("cash == voucher.value()? " + cash.equals(voucher.value()));
System.out.println("voucher.value() == cash? " + voucher.value().equals(cash));
```

Now it is ok from a semantic point of view. A voucher is not a money, but its value is.


## Hash

### Only implementing equals:

```
public class Team {

    private final String city;
    private final String sport;

}
```

- Generate constructor

```
public Team(String city, String sport) {
    this.city = city;
    this.sport = sport;
}
```

- Generate Equals

```
@Override
public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Team team = (Team) o;
    return Objects.equals(city, team.city) && Objects.equals(sport, team.sport);
}
```

#### Assess containment

```
Map<Team, String> nhl = new HashMap<>();
nhl.put( new Team("Toronto", "hockey"),  "Maple leafs");
nhl.put( new Team("Ottawa", "hockey"),   "senators");
nhl.put( new Team("Montreal", "hockey"), "canadiens");

Team leafs = new Team("Toronto", "hockey");
System.out.println("Toronto's hockey team? " + nhl.getOrDefault(leafs, "unknown"));
System.out.println("Leaf as a key? " + nhl.keySet().contains(leafs));
System.out.println("Leaf as an element? " + nhl.keySet().stream().toList().contains(leafs));
System.out.println("----------------");
```

So the (Toronto, Hockey) is present in the list of keys, but "contains" returns false... Because 
lists relies on equality, but Hash* structures on hashcodes!

### Implementing boths

```
public class TeamBoth {

    private final String city;
    private final String sport;

}
```

- Generate constructor

```
public TeamBoth(String city, String sport) {
    this.city = city;
    this.sport = sport;
}
```

- Generate both equals and hashcode

```
@Override
public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    TeamBoth teamBoth = (TeamBoth) o;
    return Objects.equals(city, teamBoth.city) && Objects.equals(sport, teamBoth.sport);
}

@Override
public int hashCode() {
    return Objects.hash(city, sport);
}
```

#### Assess containment

```
Map<TeamBoth, String> nhlBis = new HashMap<>();
TeamBoth leafBis = new TeamBoth("Toronto", "hockey");
nhlBis.put(new TeamBoth("Toronto", "hockey"), "Maple leafs");
nhlBis.put(new TeamBoth("Ottawa", "hockey"), "senators");
nhlBis.put(new TeamBoth("Montreal", "hockey"), "canadiens");

System.out.println("Toronto's hockey team? " + nhlBis.getOrDefault(leafBis, "unknown"));
System.out.println("Leaf as a key? " + nhlBis.keySet().contains(leafBis));
System.out.println("Leaf as an element? " + nhlBis.keySet().stream().toList().contains(leafBis));
```
