#!/usr/bin/env bash

FILE_PATH=/Users/folea/personal/kotlin-resources-server/dummy-data
BASE_URL=http://localhost:8999


######################################################################################################################################################
#   FRAMES
######################################################################################################################################################

#black frame picture

curl \
--silent \
-s -o /dev/null -w '%{http_code}\n' \
-X POST \
${BASE_URL}/pictures \
-H 'cache-control: no-cache' \
-H 'content-type: multipart/form-data; boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW' \
-H 'postman-token: acef7d44-8d3b-3787-bdfc-4205b524d0a2' \
-F file=@${FILE_PATH}/dev-images/Frame.png \
-F name=black-frame \
-F key=black-frame \
-F formatName=png

#black frame

curl \
--silent \
-s -o /dev/null -w '%{http_code}\n' \
-X POST \
${BASE_URL}/frames \
-H 'cache-control: no-cache' \
-H 'content-type: application/json' \
-H 'postman-token: 0a6aeb5e-3e75-e032-c235-de497cb4feb4' \
-d '{
      "name": "Black Frame",
      "uniqueName": "black-frame",
      "description": "This is a test black frame for developing purposes. Lorem Ipsum bla bla bla...",
      "height": 1920.0,
      "length": 1080.0,
      "picture": {
        "key": "black-frame"
      },
      "price": 1234.5
    }'

#######################################################################################################################################################

# pink frame picture

curl \
--silent \
-s -o /dev/null -w '%{http_code}\n' \
-X POST \
${BASE_URL}/pictures \
-H 'cache-control: no-cache' \
-H 'content-type: multipart/form-data; boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW' \
-H 'postman-token: acef7d44-8d3b-3787-bdfc-4205b524d0a2' \
-F file=@${FILE_PATH}/dev-images/4f4ce3e5e98243ba90d3c4025c03e699.png \
-F name=pink-frame \
-F key=pink-frame \
-F formatName=png

# pink frame

curl \
--silent \
-s -o /dev/null -w '%{http_code}\n' \
-X POST \
${BASE_URL}/frames \
-H 'cache-control: no-cache' \
-H 'content-type: application/json' \
-H 'postman-token: 0a6aeb5e-3e75-e032-c235-de497cb4feb4' \
-d '{
      "name": "Pink Frame",
      "uniqueName": "pink-frame",
      "description": "This is a test pink frame for developing purposes. Lorem Ipsum bla bla bla...",
      "height": 1100.0,
      "length": 791.0,
      "picture": {
        "key": "pink-frame"
      },
      "price": 4321
    }'

#######################################################################################################################################################

# old classic frame picture

curl \
--silent \
-s -o /dev/null -w '%{http_code}\n' \
-X POST \
${BASE_URL}/pictures \
-H 'cache-control: no-cache' \
-H 'content-type: multipart/form-data; boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW' \
-H 'postman-token: acef7d44-8d3b-3787-bdfc-4205b524d0a2' \
-F file=@${FILE_PATH}/dev-images/c03b2cb5cc1e04bbe8dc1763e1ea49d6.png \
-F name=old-classic-frame \
-F key=old-classic-frame \
-F formatName=png

# old classic frame

curl \
--silent \
-s -o /dev/null -w '%{http_code}\n' \
-X POST \
${BASE_URL}/frames \
-H 'cache-control: no-cache' \
-H 'content-type: application/json' \
-H 'postman-token: 0a6aeb5e-3e75-e032-c235-de497cb4feb4' \
-d '{
      "name": "Old Classic Frame",
      "uniqueName": "old-classic-frame",
      "description": "This is a test old classic frame for developing purposes. Lorem Ipsum bla bla bla...",
      "height": 1023.0,
      "length": 777.0,
      "picture": {
        "key": "old-classic-frame"
      },
      "price": 9800.0
    }'

######################################################################################################################################################

# standard frame picture

curl \
--silent \
-s -o /dev/null -w '%{http_code}\n' \
-X POST \
${BASE_URL}/pictures \
-H 'cache-control: no-cache' \
-H 'content-type: multipart/form-data; boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW' \
-H 'postman-token: acef7d44-8d3b-3787-bdfc-4205b524d0a2' \
-F file=@${FILE_PATH}/dev-images/PNGPIX-COM-Photo-Frame-PNG-Transparent-Image-2-500x365.png \
-F name=standard-frame \
-F key=standard-frame \
-F formatName=png

# standard frame

curl \
--silent \
-s -o /dev/null -w '%{http_code}\n' \
-X POST \
${BASE_URL}/frames \
-H 'cache-control: no-cache' \
-H 'content-type: application/json' \
-H 'postman-token: 0a6aeb5e-3e75-e032-c235-de497cb4feb4' \
-d '{
      "name": "Standard Frame",
      "uniqueName": "standard-frame",
      "description": "This is a test standard frame for developing purposes. Lorem Ipsum bla bla bla...",
      "height": 500.0,
      "length": 365.0,
      "picture": {
        "key": "standard-frame"
      },
      "price": 0.99
    }'

#######################################################################################################################################################
##   MAT-TYPE
#######################################################################################################################################################
#
## brown mat type picture
#
#curl \
#--silent \
#-w "%{http_code}\n" \
#-X POST \
#http://localhost:8080/pictures \
#-H 'cache-control: no-cache' \
#-H 'content-type: multipart/form-data; boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW' \
#-H 'postman-token: acef7d44-8d3b-3787-bdfc-4205b524d0a2' \
#-F file=@/C/Users/FRAN/Desktop/projects/WeFrame/dev-images/BackMat.png \
#-F uniqueName=brown-mat-type \
#-F formatName=png
#
## brown mat type
#
#curl \
#--silent \
#-w "%{http_code}\n" \
#-X POST \
#http://localhost:8080/generic-product/mat-types \
#-H 'cache-control: no-cache' \
#-H 'content-type: application/json' \
#-H 'postman-token: 0a6aeb5e-3e75-e032-c235-de497cb4feb4' \
#-d '{
#      "name": "Brown Mat Type",
#      "uniqueName": "brown-mat-type",
#      "description": "This is a test brown mat type for developing purposes. Lorem Ipsum bla bla bla...",
#      "picture": {
#        "imageKey": "brown-mat-type"
#      },
#      "m2Price": 2.99
#    }'
#
## white mat type picture
#
#curl \
#--silent \
#-w "%{http_code}\n" \
#-X POST \
#http://localhost:8080/pictures \
#-H 'cache-control: no-cache' \
#-H 'content-type: multipart/form-data; boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW' \
#-H 'postman-token: acef7d44-8d3b-3787-bdfc-4205b524d0a2' \
#-F file=@/personal/kotlin-resources-server/dummy-data/dev-images/ivory-off-white-paper-texture.jpg \
#-F uniqueName=white-mat-type \
#-F formatName=jpg
#
## white mat type
#
#curl \
#--silent \
#-w "%{http_code}\n" \
#-X POST \
#http://localhost:8080/generic-product/mat-types \
#-H 'cache-control: no-cache' \
#-H 'content-type: application/json' \
#-H 'postman-token: 0a6aeb5e-3e75-e032-c235-de497cb4feb4' \
#-d '{
#      "name": "White Mat Type",
#      "uniqueName": "white-mat-type",
#      "description": "This is a test white mat type for developing purposes. Lorem Ipsum bla bla bla...",
#      "picture": {
#        "imageKey": "white-mat-type"
#      },
#      "m2Price": 19.99
#    }'
#
#######################################################################################################################################################
##   BACKBOARD
#######################################################################################################################################################
#
## green backboard picture
#
#curl \
#--silent \
#-w "%{http_code}\n" \
#-X POST \
#http://localhost:8080/pictures \
#-H 'cache-control: no-cache' \
#-H 'content-type: multipart/form-data; boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW' \
#-H 'postman-token: acef7d44-8d3b-3787-bdfc-4205b524d0a2' \
#-F file=@/personal/kotlin-resources-server/dummy-data/dev-images/green-backboard.jpg \
#-F uniqueName=green-backboard \
#-F formatName=jpg
#
## green backboard
#
#curl \
#--silent \
#-w "%{http_code}\n" \
#-X POST \
#http://localhost:8080/generic-product/backboards \
#-H 'cache-control: no-cache' \
#-H 'content-type: application/json' \
#-H 'postman-token: 0a6aeb5e-3e75-e032-c235-de497cb4feb4' \
#-d '{
#      "name": "Green Backboard",
#      "uniqueName": "green-backboard",
#      "description": "This is a test green backboard for developing purposes. Lorem Ipsum bla bla bla...",
#      "picture": {
#        "imageKey": "green-backboard"
#      },
#      "m2Price": 21.33
#    }'
#
## gray backboard picture
#
#curl \
#--silent \
#-w "%{http_code}\n" \
#-X POST \
#http://localhost:8080/pictures \
#-H 'cache-control: no-cache' \
#-H 'content-type: multipart/form-data; boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW' \
#-H 'postman-token: acef7d44-8d3b-3787-bdfc-4205b524d0a2' \
#-F file=@/personal/kotlin-resources-server/dummy-data/dev-images/gray-backboard.jpg \
#-F uniqueName=gray-backboard \
#-F formatName=jpg
#
## gray backboard
#
#curl \
#--silent \
#-w "%{http_code}\n" \
#-X POST \
#http://localhost:8080/generic-product/backboards \
#-H 'cache-control: no-cache' \
#-H 'content-type: application/json' \
#-H 'postman-token: 0a6aeb5e-3e75-e032-c235-de497cb4feb4' \
#-d '{
#      "name": "Gray Backboard",
#      "uniqueName": "gray-backboard",
#      "description": "This is a test gray backboard for developing purposes. Lorem Ipsum bla bla bla...",
#      "picture": {
#        "imageKey": "gray-backboard"
#      },
#      "m2Price": 19
#    }'
#
#######################################################################################################################################################
##   FRAME-GLASS
#######################################################################################################################################################
#
## glass frame glass
#
#curl \
#--silent \
#-w "%{http_code}\n" \
#-X POST \
#http://localhost:8080/generic-product/frame-glasses \
#-H 'cache-control: no-cache' \
#-H 'content-type: application/json' \
#-H 'postman-token: 0a6aeb5e-3e75-e032-c235-de497cb4feb4' \
#-d '{
#      "name": "Glass",
#      "uniqueName": "glass-frame-glass",
#      "description": "This is a test glass frame glass for developing purposes. Lorem Ipsum bla bla bla...",
#      "m2Price": 76.99
#    }'
#
## acrilic frame glass
#
#curl \
#--silent \
#-w "%{http_code}\n" \
#-X POST \
#http://localhost:8080/generic-product/frame-glasses \
#-H 'cache-control: no-cache' \
#-H 'content-type: application/json' \
#-H 'postman-token: 0a6aeb5e-3e75-e032-c235-de497cb4feb4' \
#-d '{
#      "name": "Acrilic",
#      "uniqueName": "acrilic-frame-glass",
#      "description": "This is a test acrilic frame glass for developing purposes. Lorem Ipsum bla bla bla...",
#      "m2Price": 56.99
#    }'
#
## plastic frame glass
#
#curl \
#--silent \
#-w "%{http_code}\n" \
#-X POST \
#http://localhost:8080/generic-product/frame-glasses \
#-H 'cache-control: no-cache' \
#-H 'content-type: application/json' \
#-H 'postman-token: 0a6aeb5e-3e75-e032-c235-de497cb4feb4' \
#-d '{
#      "name": "Plastic",
#      "uniqueName": "plastic-frame-glass",
#      "description": "This is a test plastic frame glass for developing purposes. Lorem Ipsum bla bla bla...",
#      "m2Price": 36.99
#    }'
