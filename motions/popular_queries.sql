# delete motion categories hierarhy;
delete from motion_categories where id in (select mc1.id from motion_categories mc1 left outer join motion_categories mc2 on mc1.id = mc2.parent_id where mc2.id is null);
