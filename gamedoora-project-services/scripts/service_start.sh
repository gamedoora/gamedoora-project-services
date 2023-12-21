#!/usr/bin/env bash
mkdir -p /home/ec2-project/server/project-services
cd /home/ec2-project/server/project-services || exit
sudo java -jar -Dserver.port=8081 \
    *.jar > project_service.log 2> project_service.error.log &
