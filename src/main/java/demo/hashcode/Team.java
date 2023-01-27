package demo.hashcode;

import java.util.Objects;

public class Team {

    private final String city;
    private final String sport;

    public Team(String city, String sport) {
        this.city = city;
        this.sport = sport;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Team team = (Team) o;
        return Objects.equals(city, team.city) && Objects.equals(sport, team.sport);
    }

}
