-- :name count-users :? :1
select count(*) from users

-- :name add-user :! :1
insert into users (first_name, last_name, email, pw_hash, verified)
values (:first_name, :last_name, :email, :pw_hash, false)

-- :name mark-user-verified :! :1
update users 
set first_name, last_name = :first_name, :last_name
where id = :id

-- :name update-user :! :1
update users 
set first_name, last_name = :first_name, :last_name
where id = :id

-- :name update-user-email :! :1
update users 
set email = :email
where id = :id

-- :name update-user-pw :! :1
update users 
set pw_hash = :pw_hash
where id = :id

-- :name user-by-id :? :1
select id, first_name, last_name, email, verified
from users where id = :id limit 1

-- :name user-by-email :? :1
select id, first_name, last_name, email, verified
from users where email = :email limit 1

-- :name prune-unverified-users :! :n
delete from users where verifed = false
