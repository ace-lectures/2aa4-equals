package demo.hashcode;

import java.util.Objects;

public class TeamBoth {

    private final String city;
    private final String sport;

    public TeamBoth(String city, String sport) {
        this.city = city;
        this.sport = sport;
    }

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
}
