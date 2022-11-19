create database dmtp charset utf8mb4 collate utf8mb4_general_ci;
create user drmotopt identified by '1234Qwer';
grant all privileges on dmtp.* to 'drmotopt'@'%';
flush privileges;