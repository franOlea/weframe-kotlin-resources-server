#!/usr/bin/env bash
AUTHORIZATION=eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImtpZCI6Ik56STVNRVE1TmpSRk4wVXhORVV3UXpWQk5rTTNRa0kxT0VJek1VVTNRVEJGUlRBek1VRTRRUSJ9.eyJodHRwczovL2VtYWlsIjoiZnJhbmNpc2NvLm9sZWFAZGF2aW5jaS5lZHUuYXIiLCJodHRwczovL25hbWUiOiJmcmFuY2lzY28ub2xlYUBkYXZpbmNpLmVkdS5hciIsImlzcyI6Imh0dHBzOi8vd2VmcmFtZS5hdXRoMC5jb20vIiwic3ViIjoiYXV0aDB8NWMxYzllYTllZjM0NjAyZjQ5ZmFiYTVjIiwiYXVkIjoiaHR0cDovL2xvY2FsaG9zdDo4MDgwIiwiaWF0IjoxNTQ4OTQ4NTcxLCJleHAiOjE1NDkwMzQ5MTgsImF6cCI6IlQ0b2RKVUJ4TUFpa0c1UDZzRWFxN3drNU9xZGFiVllMIiwic2NvcGUiOiIifQ.KneJwk28iqfLRHy-dsk6C87NnXVqZMrU6_BDk4ak6Fjlwl1mx1HWa70P0BPgZTwhRMJahgMaq_7MfOkHrtiAB_za1AxS67nrz083ceJ58oEtJH_M7sRnIvGvBL5eqjlIJSzQtCdyzxu5RJ-hEJWKOSZeybv8SOPM-d6a9-tP4IymoDWk7HQpxn9nVGJ1KAKwbfnOmGJ6cqDrYKNVszdZaxmCBU3lDRm4q143GLYpRwof7GYP4owSKL2amcOngs5F4u7cAQE6oiWov6U4XfADVJZyeQQeptoFut5HYfeGtSEtjQdzmk0JuXWUcgl4vejDiFnwJ2YnUzUVDB3gBUlKfA
FILE_PATH=/Users/folea/personal/kotlin-resources-server/local-dev
BASE_URL=http://localhost:8080


######################################################################################################################################################
#   FRAMES
######################################################################################################################################################

#black frame picture

IMAGE_KEY=$(curl \
--silent \
-X POST \
${BASE_URL}/pictures \
-H 'cache-control: no-cache' \
-H 'content-type: multipart/form-data; boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW' \
-F file=@${FILE_PATH}/dev-images/Frame.png \
-F name=black-frame \
-F formatName=png | jq -r '.key')

#black frame

curl \
--silent \
-s -o /dev/null -w '%{http_code}\n' \
-X POST \
${BASE_URL}/frames \
-H 'content-type: application/json' \
-d '{
      "name": "Black Frame",
      "uniqueName": "black-frame",
      "description": "This is a test black frame for developing purposes. Lorem Ipsum bla bla bla...",
      "height": 1920.0,
      "length": 1080.0,
      "price": 1234.5,
      "picture": {
        "key": "'${IMAGE_KEY}'"
      }
    }'

#######################################################################################################################################################

# pink frame picture

IMAGE_KEY=$(curl \
--silent \
-X POST \
${BASE_URL}/pictures \
-H 'cache-control: no-cache' \
-H 'content-type: multipart/form-data; boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW' \
-H 'postman-token: acef7d44-8d3b-3787-bdfc-4205b524d0a2' \
-F file=@${FILE_PATH}/dev-images/4f4ce3e5e98243ba90d3c4025c03e699.png \
-F name=pink-frame \
-F formatName=png | jq -r '.key')

# pink frame

curl \
--silent \
-s -o /dev/null -w '%{http_code}\n' \
-X POST \
${BASE_URL}/frames \
-H 'cache-control: no-cache' \
-H 'content-type: application/json' \
-d '{
      "name": "Pink Frame",
      "uniqueName": "pink-frame",
      "description": "This is a test pink frame for developing purposes. Lorem Ipsum bla bla bla...",
      "height": 1100.0,
      "length": 791.0,
      "picture": {
        "key": "'${IMAGE_KEY}'"
      },
      "price": 4321
    }'

#######################################################################################################################################################

# old classic frame picture

IMAGE_KEY=$(curl \
--silent \
-X POST \
${BASE_URL}/pictures \
-H 'cache-control: no-cache' \
-H 'content-type: multipart/form-data; boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW' \
-F file=@${FILE_PATH}/dev-images/c03b2cb5cc1e04bbe8dc1763e1ea49d6.png \
-F name=old-classic-frame \
-F formatName=png | jq -r '.key')

# old classic frame

curl \
--silent \
-s -o /dev/null -w '%{http_code}\n' \
-X POST \
${BASE_URL}/frames \
-H 'cache-control: no-cache' \
-H 'content-type: application/json' \
-d '{
      "name": "Old Classic Frame",
      "uniqueName": "old-classic-frame",
      "description": "This is a test old classic frame for developing purposes. Lorem Ipsum bla bla bla...",
      "height": 1023.0,
      "length": 777.0,
      "picture": {
        "key": "'${IMAGE_KEY}'"
      },
      "price": 9800.0
    }'

######################################################################################################################################################

# standard frame picture

IMAGE_KEY=$(curl \
--silent \
-X POST \
${BASE_URL}/pictures \
-H 'cache-control: no-cache' \
-H 'content-type: multipart/form-data; boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW' \
-F file=@${FILE_PATH}/dev-images/PNGPIX-COM-Photo-Frame-PNG-Transparent-Image-2-500x365.png \
-F name=standard-frame \
-F key=standard-frame \
-F formatName=png | jq -r '.key')

# standard frame

curl \
--silent \
-s -o /dev/null -w '%{http_code}\n' \
-X POST \
${BASE_URL}/frames \
-H 'cache-control: no-cache' \
-H 'content-type: application/json' \
-d '{
      "name": "Standard Frame",
      "uniqueName": "standard-frame",
      "description": "This is a test standard frame for developing purposes. Lorem Ipsum bla bla bla...",
      "height": 500.0,
      "length": 365.0,
      "picture": {
        "key": "'${IMAGE_KEY}'"
      },
      "price": 0.99
    }'

######################################################################################################################################################
#   MAT-TYPE
######################################################################################################################################################

# brown mat type picture
IMAGE_KEY=$(curl \
--silent \
-X POST \
${BASE_URL}/pictures \
-H 'cache-control: no-cache' \
-H 'content-type: multipart/form-data; boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW' \
-F file=@${FILE_PATH}/dev-images/BackMat.png \
-F name=brown-mat-type \
-F formatName=png | jq -r '.key')

# brown mat type

curl \
--silent \
-s -o /dev/null -w '%{http_code}\n' \
-X POST \
${BASE_URL}/mat-types \
-H 'cache-control: no-cache' \
-H 'content-type: application/json' \
-d '{
      "name": "Brown Mat Type",
      "description": "This is a test brown mat type for developing purposes. Lorem Ipsum bla bla bla...",
      "picture": {
        "key": "'${IMAGE_KEY}'"
      },
      "m2Price": 2.99
    }'

#white mat type picture


IMAGE_KEY=$(curl \
--silent \
-X POST \
${BASE_URL}/pictures \
-H 'cache-control: no-cache' \
-H 'content-type: multipart/form-data; boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW' \
-F file=@${FILE_PATH}/dev-images/ivory-off-white-paper-texture.jpg \
-F name=white-mat-type \
-F formatName=jpg | jq -r '.key')

# white mat type

curl \
--silent \
-s -o /dev/null -w '%{http_code}\n' \
-X POST \
${BASE_URL}/mat-types \
-H 'cache-control: no-cache' \
-H 'content-type: application/json' \
-d '{
      "name": "White Mat Type",
      "description": "This is a test white mat type for developing purposes. Lorem Ipsum bla bla bla...",
      "picture": {
        "key": "'${IMAGE_KEY}'"
      },
      "m2Price": 19.99
    }'

#######################################################################################################################################################
##   BACKBOARD
#######################################################################################################################################################

# green backboard picture
IMAGE_KEY=$(curl \
--silent \
-X POST \
${BASE_URL}/pictures \
-H 'cache-control: no-cache' \
-H 'content-type: multipart/form-data; boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW' \
-F file=@${FILE_PATH}/dev-images/green-backboard.jpg \
-F name=green-backboard \
-F formatName=jpg | jq -r '.key')

# green backboard

curl \
--silent \
-s -o /dev/null -w '%{http_code}\n' \
-X POST \
${BASE_URL}/backboards \
-H 'cache-control: no-cache' \
-H 'content-type: application/json' \
-d '{
      "name": "Green Backboard",
      "description": "This is a test green backboard for developing purposes. Lorem Ipsum bla bla bla...",
      "picture": {
        "key": "'${IMAGE_KEY}'"
      },
      "m2Price": 21.33
    }'

# gray backboard picture

IMAGE_KEY=$(curl \
--silent \
-X POST \
${BASE_URL}/pictures \
-H 'cache-control: no-cache' \
-H 'content-type: multipart/form-data; boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW' \
-F file=@${FILE_PATH}/dev-images/gray-backboard.jpg \
-F name=gray-backboard \
-F formatName=jpg | jq -r '.key')

# gray backboard

curl \
--silent \
-s -o /dev/null -w '%{http_code}\n' \
-X POST \
${BASE_URL}/backboards \
-H 'cache-control: no-cache' \
-H 'content-type: application/json' \
-d '{
      "name": "Gray Backboard",
      "description": "This is a test gray backboard for developing purposes. Lorem Ipsum bla bla bla...",
      "picture": {
        "key": "'${IMAGE_KEY}'"
      },
      "m2Price": 19
    }'

######################################################################################################################################################
#   FRAME-GLASS
######################################################################################################################################################

# glass frame glass

curl \
--silent \
-s -o /dev/null -w '%{http_code}\n' \
-X POST \
${BASE_URL}/frame-glasses \
-H 'cache-control: no-cache' \
-H 'content-type: application/json' \
-d '{
      "name": "Glass",
      "description": "This is a test glass frame glass for developing purposes. Lorem Ipsum bla bla bla...",
      "m2Price": 76.99
    }'

# acrilic frame glass

curl \
--silent \
-s -o /dev/null -w '%{http_code}\n' \
-X POST \
${BASE_URL}/frame-glasses \
-H 'cache-control: no-cache' \
-H 'content-type: application/json' \
-d '{
      "name": "Acrilic",
      "description": "This is a test acrilic frame glass for developing purposes. Lorem Ipsum bla bla bla...",
      "m2Price": 56.99
    }'

# plastic frame glass

curl \
--silent \
-s -o /dev/null -w '%{http_code}\n' \
-X POST \
${BASE_URL}/frame-glasses \
-H 'cache-control: no-cache' \
-H 'content-type: application/json' \
-d '{
      "name": "Plastic",
      "description": "This is a test plastic frame glass for developing purposes. Lorem Ipsum bla bla bla...",
      "m2Price": 36.99
    }'

######################################################################################################################################################
#   USER-PICTURES
######################################################################################################################################################

#esta es mi villa picture

IMAGE_KEY=$(curl \
--silent \
-X POST \
${BASE_URL}/pictures \
-H 'cache-control: no-cache' \
-H 'content-type: multipart/form-data; boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW' \
-H 'Authorization: Bearer '${AUTHORIZATION} \
-F file=@${FILE_PATH}/dev-images/25092013_125156_16189224.jpg \
-F name=25092013_125156_16189224 \
-F formatName=jpg | jq -r '.key')

#esta es mi villa

curl \
--silent \
-s -o /dev/null -w '%{http_code}\n' \
-X POST \
${BASE_URL}/user-pictures \
-H 'content-type: application/json' \
-H 'Authorization: Bearer '${AUTHORIZATION} \
-d '{
      "key": "'${IMAGE_KEY}'"
    }'

#civic picture

IMAGE_KEY=$(curl \
--silent \
-X POST \
${BASE_URL}/pictures \
-H 'cache-control: no-cache' \
-H 'content-type: multipart/form-data; boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW' \
-H 'Authorization: Bearer '${AUTHORIZATION} \
-F file=@${FILE_PATH}/dev-images/29694748_1620171924763275_3730102846907301391_n.jpg \
-F name=29694748_1620171924763275_3730102846907301391_n \
-F formatName=jpg | jq -r '.key')

#civic

curl \
--silent \
-s -o /dev/null -w '%{http_code}\n' \
-X POST \
${BASE_URL}/user-pictures \
-H 'content-type: application/json' \
-H 'Authorization: Bearer '${AUTHORIZATION} \
-d '{
      "key": "'${IMAGE_KEY}'"
    }'