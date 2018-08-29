#!/usr/bin/env bash

docker stop weframe-mysql
docker rm weframe-mysql

docker stop weframe-localstack
docker rm weframe-localstack