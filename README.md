### 스프링부트 기초
#### Controller,Service,Repository,Data,JPA,Thymeleaf
#### 도커 설치 후 마리아디비 사용
방구석 강좌 : https://dkswnkk.tistory.com/697


#### 도커 & 마리아디비설치
```
docker pull mariadb
docker images -- 이미지 확인

docker run -p 3306:3306 --name mariadb -e MARIADB_ROOT_PASSWORD=1234 -d mariadb
``` 

설치확인 후 접속
```
docker exec -it mariadb mariadb -u root -p
```

디비,사용자설치
```
create database basic1__dev;
create user 'demo'@'%' identified by 'demo';
grant all privileges on *.* to 'demo'@'%';
```

