# grapemarket-web

https://www.youtube.com/watch?v=7whw2mchDvI&ab_channel=localhost

#### DB 사용자 설정
```sql
create user 'grapemarket'@'%' identified by 'bitc5600';
GRANT ALL PRIVILEGES ON *.* TO grapemarket@'%';
create database grapemarket;
use grapemarket;
