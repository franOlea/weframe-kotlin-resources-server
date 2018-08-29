#!/usr/bin/env bash
LOCAL_STORAGE=/Users/folea/personal/docker-storage
#docker run -p 4567-4583:4567-4583 --name localstack localstack
docker run -d --name weframe-localstack -p 4567-4583:4567-4583 -e SERVICES=s3:4406 -e DATA_DIR=/tmp/localstack/data -v ${LOCAL_STORAGE}/localstack:/tmp/localstack/data localstack
docker run -d --name weframe-mysql -p 3306:3306 -e MYSQL_ROOT_PASSWORD=toor -v ${LOCAL_STORAGE}/mysql:/var/lib/mysql mysql

