create table skeleton_parts(
  id integer not null primary key,
  name varchar(255) not null unique,
  length float not null
);

create generator gen_skeleton_part_id;
set generator gen_skeleton_part_id to 0;

set term !! ;
create trigger trig_set_skeleton_part_id for skeleton_parts
active before insert position 0
as
begin
  if(new.id is null) then new.id = gen_id(gen_skeleton_part_id, 1);
end!!
set term ; !!

create table dimensions(
  id integer primary key not null,
  skeleton_part_id integer references skeleton_parts not null,
  name varchar(255) not null,
  type varchar(15) not null,
  from_v float,
  to_v float,
  initial_v float,
  constraint uniq_skeleton_part_name unique (skeleton_part_id, name),
  constraint uniq_skeleton_part_type unique (skeleton_part_id, type)
);

create generator gen_dimension_id;
set generator gen_dimension_id to 0;

set term !! ;
create trigger trig_set_dimension_id for dimensions
active before insert position 0
as
begin
  if(new.id is null) then new.id = gen_id(gen_dimension_id, 1);
end!!
set term ; !!

create table category_types(
  id integer primary key not null,
  name varchar(255) unique not null
);

create generator gen_category_type_id;
set generator gen_category_type_id to 0;

set term !! ;
create trigger trig_set_category_type_id for category_types
active before insert position 0
as
begin
  if(new.id is null) then new.id = gen_id(gen_category_type_id, 1);
end!!
set term ; !!

create table categories(
  id integer primary key not null,
  category_type_id integer references category_types,
  name varchar(255),
  parent_id integer references categories(id)
);

create generator gen_motion_category_id;
set generator gen_motion_category_id to 0;

set term !! ;
create trigger trig_set_category_id for categories
active before insert position 0
as
begin
  if(new.id is null) then new.id = gen_id(gen_motion_category_id, 1);
end!!
set term !! ;

create table motions(
id integer primary key not null,
name varchar(255),
category_id integer references categories(id),
created_at timestamp default current_timestamp
);

create generator gen_motion_id;
set generator gen_motion_id to 0;

set term !! ;
create trigger trig_set_motion_id for motions
active before insert position 0
as 
begin
  if(new.id is null) then new.id = gen_id(gen_motion_id, 1);
end!!
set term ; !!

create domain boolean
as smallint
check (value in(0, 1));

create table motion_dimensions(
 id integer primary key not null,
 motion_id integer references motions not null,
 dimension_id integer references dimensions not null,
 from_f float,
 to_f float,
 initial_f float,
 is_bound boolean default 0,
 created_at timestamp default current_timestamp
);

create generator gen_motion_dimension_id;
set generator gen_motion_dimension_id to 0;

set term !! ;
create trigger trig_set_motion_dimension_id for motion_dimensions
active before insert position 0
as
begin
  if(new.id is null) then new.id = gen_id(gen_motion_dimension_id, 1);
end!!
set term ; !!
