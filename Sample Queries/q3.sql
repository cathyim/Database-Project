select tournaments.tournament_name, tournaments.region, SUM(earnings.prize_money) from tournaments, earnings
where tournaments.tournament_id = earnings.tournament_id
group by tournaments.tournament_id
having sum(earnings.prize_money)>10000
order by SUM(earnings.prize_money) desc;