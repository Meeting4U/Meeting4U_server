#!/bin/bash

cd /meeting4u/server/Meeting4U_server

# git 변경내용 fetch 및 merge
git fetch origin develop

h1=`git rev-parse HEAD`
h2=`git rev-parse origin/develop`

if [[ $h1 == $h2 ]]; then
    echo "Ok"
    exit 0
fi

echo "now build.."

git merge origin/develop

# 현재 기동중인 서버 중지.
pid=`jps | grep Gradle | awk '{print $1}'`
kill $pid

# 프로젝트 rebuild
sudo ./gradlew clean build -Pprofile=dev

#서버 실행.
cd /meeting4u/server/Meeting4U_server/build/libs
nohup java -jar Meeting4U_server-1.0-SNAPSHOT.jar &
