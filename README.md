This is springboot application created for manage employee details.

--- How To Run Application ---

First have to create data base using MYSQL with below mentioned datails.

1. DB name - practicaldb
2. Host - localhost
3. Port - 3307
4. Username - root
5. Password - 1234

Then create table called Employee in side that DB with below mentioned columns

1. id - Integer - Non Null - Auto Increment
2. first_name - String - Allow Null
3. last_name - String - Allow Null
4. email - String - Allow Null
5. salary - Double - Allow Null

------------------------------------------------------------------------------

Then run the application. And its running on port 8081.

------------------------------------------------------------------------------

After running application can access all defined curd operation controllers by using below swagger url.

http://localhost:8081/practical-test/swagger-ui/index.html#/

------------------------------------------------------------------------------

