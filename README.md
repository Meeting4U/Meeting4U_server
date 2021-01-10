#build 및 배포 / 실행 

------------
-   build : ./gradlew clean build
-   실행 (jar file path : /build/libs)
    -   local : java -jar -Dspring.profile.active=local 생성된파일.jar
    -   dev : java -jar -Dspring.profile.active=dev 생성된파일.jar

    

#api test

------------
api test : curl -H "Content-Type: application/x-www-form-urlencoded"  -X GET http://210.89.191.47:12030/api/hello
## DB Structure

------------
[![Picture](https://github.com/Meeting4U/Meeting4U_server/blob/main/table_info.jpeg?raw=true)](https://github.com/Meeting4U/Meeting4U_server/blob/main/table_info.jpeg?raw=true)

