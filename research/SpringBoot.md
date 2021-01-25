# JWT Token

* @ElementCollection

1:N 테이블 관계에서 Primary Key만을 가지고 1의 테이블이 N을 리스트 형태로 가질 수 있는 annotation

jwt token 방식 사용 시 user role 테이블과 user 테이블의 관계를 나타낼 때 사용

한 명의 user는 여러 개의 권한을 가질 수 있으므로 List의 형태로 저장 (1:N)

[![Picture](https://github.com/koogk7/LoginApiForJwtAndSecurity/blob/master/image-20190715235023105.png?raw=true)](https://github.com/koogk7/LoginApiForJwtAndSecurity/blob/master/image-20190715235023105.png?raw=true)

* @Autowired
필요한 의존 객체의 “타입"에 해당하는 빈을 찾아 주입하는 annotation
    1. 생성자
    2. setter
    3. 필드
위 세가지의 경우 Autowired annotation 사용 가능
User - Service - CustomUserDetailService에서 annotation 추가 안해서 NullExceptionError 발생

------------

## JPA / Hibernate

* DDL (Data Definition Language)

  데이터베이스를 정의하는 언어로, 데이터를 생성(insert), 수정(update), 삭제(delete)하는 등의 데이터의 전체의 골격을 결정하는 언어

* `spring.jpa.generate-ddl=true` 

   true로 설정하면 해당 데이터를 근거로 서버 시작 시점에 DDL문을 생성하여 DB에 적용

* `spring.jpa.hibernate.ddl-auto` option

  1. none - 아무것도 실행하지 않음
  2. create-drop -  SessionFactory가 시작될 때 drop및 생성을 실행하고, SessionFactory가 종료될 때 drop을 실행
  3. create - SessionFactory가 시작될 때 데이터베이스 drop을 실행하고 생성된 DDL을 실행
  4. update - 변경된 스키마를 적용
  5. validate - 변경된 스키마가 있다면 변경점을 출력하고 애플리케이션을 종료 (시작 시 Entity 클래스와 DB 스키마 구조를 비교해서 같은지만 확인)

[출처](https://velog.io/@owljoa/%EC%88%98%EC%A0%95%ED%95%84%EC%9A%94-JPA-Hibernate-%EC%B4%88%EA%B8%B0-%EB%8D%B0%EC%9D%B4%ED%84%B0-%EC%83%9D%EC%84%B1-)

----------

## Redis

* In memory 기법
* 

