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

-----------
## feature branch 생성 및 종료
<pre><code>
$ git checkout -b feature/login develop

/* 새로운 기능에 대한 작업 수행 */
/* feature 브랜치에서 모든 작업이 끝나면 */

$ git checkout develop
$ git merge --no-ff feature/login
$ git branch -d feature/login
$ git push origin develop
</code></pre>

* --no--off
    새로운 커밋 객체를 만들어 ‘develop’ 브랜치에 merge 한다.
    이것은 ‘feature’ 브랜치에 존재하는 커밋 이력을 모두 합쳐서 하나의 새로운 커밋 객체를 만들어 ‘develop’ 브랜치로 병합(merge)하는 것이다.

[출처](https://gmlwjd9405.github.io/2018/05/11/types-of-git-branch.html)