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



  

