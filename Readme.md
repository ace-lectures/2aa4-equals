source: Baeldung
  - https://www.baeldung.com/java-equals-hashcode-contracts

to compile: mvn package

to run: mvn -q exec:java

# Main class

Create Main class + psvm
sout "Welcome"

# Equals

## Objects

Create Money (int, currency) and  voucher (+ company)
compare 2 moneys

```java
Money expense = new Money(55, "CAD");
Money income = new Money(55, "CAD");
System.out.println("expense == expense? " + expense.equals(expense));
System.out.println("expense == income? " + expense.equals(income));
System.out.println("----------------");
```
It is false (objects are different in memory)

## Generate equals

Generate equals method in Money, not accepting subclasses.
Rerun the code. Now it's ok.

## Money and voucher

Generate equals method in Voucher
Test equality with money and vouchers

```java
Money cash = new Money(42, "CAD");
Money voucher = new Voucher(42, "CAD", "AirCanada");
System.out.println("voucher == cash? " + voucher.equals(cash));
System.out.println("cash == voucher? " + cash.equals(voucher));
```

We broke symmetry!

## Inheritance is root of all evil

Favor composition over inheritance

Refactor Voucher to be a store and containing a money
Provide a service to access voucher's value
test equality of voucher and money (false)
test equality of voucher's value and money (true)

# Hashcode

Create a team class (city, sport)
Generate equals, comment hashcode

## Use a hashset

```java
Team leafs = new Team("Toronto", "hockey");
System.out.println("Toronto's hockey team? " + nhl.getOrDefault(leafs, "unknown"));
System.out.println("Raptors as a key? " + nhl.keySet().contains(leafs));
System.out.println("Raptors as an element? " + nhl.keySet().stream().toList().contains(leafs));
 System.out.println("----------------");
```

## fix by uncommenting the equals method (team both)