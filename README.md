1. поднять docker-compose up
2. регистрация 

postman request POST 'localhost:8083/api/v1/auth/register' \
   --header 'Content-Type: application/json' \
   --header 'Cookie: JSESSIONID=F312F830EEA34ECD92EAFA2567B6B577' \
   --body '{
   "username" : "shyngys",
   "password" : "test",
   "firstName" : "Shyngys",
   "lastName" : "Yessenbayev"
   }'

3. логин

postman request POST 'localhost:8083/api/v1/auth/login' \
--header 'Content-Type: application/json' \
--header 'Cookie: JSESSIONID=F312F830EEA34ECD92EAFA2567B6B577' \
--body '{
"username" : "shyngys",
"password" : "test"
}'

4. получение информации

localhost:8083/api/v1/auth/info

вставить токен