package com.example.olympicgames;

import com.example.olympicgames.entities.Country;
import com.example.olympicgames.entities.Event;
import com.example.olympicgames.entities.Query1Dto;
import com.example.olympicgames.entities.Query3Dto;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class DullRepository {

    private final EntityManager entityManager;

    public DullRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    public void ping() {
//        List<Event> all = entityManager.createQuery("select e from Event e").getResultList();
//        for (var e : all) {
//            System.out.println(e.getId() + " " + e.getName() + " " + e.getEventType() + " " + e.getOlympics().getId() + " " + e.isTeamEvent());
//        }

        Event event = new Event();
        event.setId("0");
        event.setName("0");
        event.setEventType("0");
        event.setTeamEvent(true);
        entityManager.persist(event);
    }

    /**
     * Для Олимпийских игр 2004 года сгенерируйте список (год рождения, количество игроков, количество золотых
     * медалей), содержащий годы, в которые родились игроки, количество игроков, родившихся в каждый из этих лет,
     * которые выиграли по крайней мере одну золотую медаль, и количество золотых медалей, завоеванных игроками,
     * родившимися в этом году.
     */
    public List<Query1Dto> query1() {
//        RowMapper<Query1Dto> rm = (rs, rowNum) -> new Query1Dto(
//                rs.getInt("birthYear"),
//                rs.getInt("nPlayers"),
//                rs.getInt("nGoldMedals")
//        );
//
//        List<Query1Dto> dtos = jdbcTemplate.query("""
//                select extract(year from p.birthdate) as birthYear,
//                       count(distinct p) as nPlayers,
//                       count(distinct r) as nGoldMedals
//                from olympics o
//                    join events e on o.olympic_id = e.olympic_id
//                    join results r on e.event_id = r.event_id
//                    join players p on r.player_id = p.player_id
//                where o.year = 2004 and r.medal = 'GOLD'
//                group by extract(year from p.birthdate)
//                """, rm);
//        return dtos;

        return entityManager.createQuery("""
                select new com.example.olympicgames.entities.Query1Dto(
                    year(p.birthday),
                    count(distinct p),
                    count(distinct r)
                )
                from Olympics o
                    join Event e on o.id = e.olympics.id
                    join Result r on e.id = r.id.eventId
                    join Player p on r.id.playerId = p.id
                where o.year = 2004 and r.medal = 'GOLD'
                group by year(p.birthday)
        """, Query1Dto.class).getResultList();

    }

    /**
     * Перечислите все индивидуальные (не групповые) соревнования, в которых была ничья в счете, и два или более
     * игрока выиграли золотую медаль.
     */
    public List<Event> query2() {
        return entityManager.createQuery("""
                select e
                from Event e
                    join Result r on e.id = r.id.eventId
                where e.isTeamEvent = false and r.medal = 'GOLD'
                group by e.id, e.name, e.eventType, e.olympics.id, e.isTeamEvent
                having count(r) > 1
        """, Event.class).getResultList();
    }

    /**
     * Найдите всех игроков, которые выиграли хотя бы одну медаль (GOLD, SILVER и BRONZE) на одной Олимпиаде.
     * (player-name, olympic-id).
     */
    public List<Query3Dto> query3() {
        return entityManager.createQuery("""
                select distinct new com.example.olympicgames.entities.Query3Dto(
                    p.name,
                    e.olympics.id
                )
                from Event e
                    join Result r on e.id = r.id.eventId
                    join Player p on r.id.playerId = p.id
                where r.medal in ('GOLD', 'SILVER', 'BRONZE')
        """, Query3Dto.class).getResultList();
    }

    /**
     * В какой стране был наибольший процент игроков (из перечисленных в наборе данных), чьи имена начинались с
     * гласной?
     */
    public List<Country> query4() {
        List<Object[]> result = entityManager.createNativeQuery("""
                with c as (
                    select c.country_id, c.name, c.population,
                           sum(case when p.name ~* '^[aeiou]' then 1 else 0 end) * 1.0 / count(p) as pct
                    from countries c
                        join players p on c.country_id = p.country_id
                    group by c.country_id, c.name, c.population
                )
                select c.country_id, c.name, c.population
                from c
                where c.pct = (select max(pct) from c)
        """).getResultList();

        return result.stream()
                .map(o -> {
                    Country c = new Country();
                    c.setId((String) o[0]);
                    c.setName((String) o[1]);
                    c.setPopulation((int) o[2]);
                    return c;
                })
                .collect(Collectors.toList());
    }

    /**
     * Для Олимпийских игр 2000 года найдите 5 стран с минимальным соотношением количества групповых медалей к
     * численности населения.
     */
    public List<Country> query5() {
        List<Object[]> result = entityManager.createNativeQuery("""
                with c as (
                    select distinct c.country_id, c.name, c.population, e.event_id, r.medal
                    from olympics o
                        join events e on o.olympic_id = e.olympic_id
                        join results r on e.event_id = r.event_id
                        join players p on r.player_id = p.player_id
                        join countries c on p.country_id = c.country_id
                    where o.year = 2000 and e.is_team_event = 1
                )
                select c.country_id, c.name, c.population,
                       sum(case when c.medal is not null then 1 else 0 end) * 1.0 / c.population as pct
                from c
                group by c.country_id, c.name, c.population
                order by pct
                limit 5
        """).getResultList();

        return result.stream()
                .map(o -> {
                    Country c = new Country();
                    c.setId((String) o[0]);
                    c.setName((String) o[1]);
                    c.setPopulation((int) o[2]);
                    return c;
                })
                .collect(Collectors.toList());
    }
}
