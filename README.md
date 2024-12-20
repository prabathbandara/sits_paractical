This is springboot application created for manage employee details.

--- How To Run Application ---

Data base configurations are follows.

1. DB name - practicaldb
2. Host - localhost
3. Port - 3307
4. Username - root
5. Password - 1234

This DB contains two tables as,

1. Employee
2. User

------------------------------------------------------------------------------

Then run the application. And its running on port 8081.

------------------------------------------------------------------------------

After running application can access all defined curd operation controllers by using below swagger url.

http://localhost:8081/practical-test/swagger-ui/index.html#/

------------------------------------------------------------------------------

This API is secured by Spring Security with JWT. So please follow the instructions to get JWT

1. Open swagger url and request for the JWT using auth-controller with below request body

   {
   "username":"prabathbandara",
   "password":"prabath123"
   }

2. Then it returns response like below

   {
   "token": "eyJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6W3siYXV0aG9yaXR5IjoiUk9MRV9BRE1JTiJ9XSwic3ViIjoicHJhYmF0aGJhbmRhcmEiLCJpYXQiOjE3MzQ2OTk0MjgsImV4cCI6MTczNDc4NTgyOH0.zfBbgFqMGwX1CB8SHsFRnCsuVcJ7bKmu9gkkav7Emg0"
   }


3. Please copy that token and paste in to ![img.png](img.png) button in swagger ui.

4. Then u can access other end points

----------------------------------------------------------------------------------

--- Adding users to DB ---

5. Users are stored in DB first add users with username,password,role.

6. Password allowed encrypted one of type BCrypt. Please use bellow third party web for encrypt password.

    https://bcrypt-generator.com/

-----------------------------------------------------------------------------------

--- Request Body Specification --

1. For Create and Update Employee end points use below mentioned type of request.

   {
   "firstName": "Prabath",
   "lastName": "Bandara",
   "email": "prabath@gmail.com",
   "salary": 150000.25
   }


2. Use this all fields for create Employee and use required fields when doing update process.
    





