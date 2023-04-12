# Insurance Management Platform
The Insurance Management Platform is a Spring Boot application built with Java that allows users to manage insurance policies, clients, and claims. It provides RESTful APIs for CRUD operations on these entities and uses a database for data storage.

## Technologies Used
1.  Spring Boot
2.  Java
3.  Spring Data JPA
4.  Postgresql Database
5.  Maven

## APIs
-   ## JWT Authentication:
      1)    POST /api/new: Create a new User.
            -     Create a new User for Role_Admin.
            ![Screenshot (28)](https://user-images.githubusercontent.com/54321306/231414612-985a9a97-55a3-4e91-86d8-eaf0eccfa3df.png)

            -     Create a new User for Role_User. 
            ![Screenshot (60)](https://user-images.githubusercontent.com/54321306/231522759-44e73865-18d3-4b48-89a7-259514cfb495.png)

            -     if same user found.
            ![Screenshot (61)](https://user-images.githubusercontent.com/54321306/231523013-35ecef8d-03c2-430c-bc9b-a97299c8bf50.png)

            
      2.    GET /api/authenticate: Fetch JWT tokens from  Role_User and Role_Admin. 
            -     Fetch JWT tokens from Role_User.
            -     Fetch JWT tokens from Role_Admin.
-   ## Clients:
      1.    GET /api/clients: The API endpoint to fetch all clients can only be accessed by users with the 'Role_Admin' token.
            -     Fetch all clients by Role_Admin token:
            -     Fetch all clients by Role_User token:
            
      2.    GET /api/clients/{id}: The API endpoint to fetch all clients can be accessed by users with the 'Role_Admin' and specific 'Role_User' token.
            -     Fetch all clients by Role_Admin token:
            -     Fetch all clients by Role_User token:
            
      3.    POST /api/clients:The API endpoint to Create a new client can be accessed by users with the 'Role_Admin' and 'Role_User' token.
     
      4.    PUT /api/clients/{id}:The API endpoint to Update a client's information can only be accessed by users with the specific 'Role_User' token.
      
      5.    DELETE /api/clients/{id}: The API endpoint to Delete a client can be accessed by users with the 'Role_Admin' and specific 'Role_User' token.
      
- ##  Insurance Policies:
      1.    GET /api/policies: Fetch all insurance policies.
      2.    GET /api/policies/{id}: Fetch a specific insurance policy by ID.
      3.    POST /api/policies: Create a new insurance policy.
      4.    PUT /api/policies/{id}: Update an insurance policy.
      5.    DELETE /api/policies/{id}: Delete an insurance policy.
      
- ##  Claims:
      1.    GET /api/claims: Fetch all claims.
      2.    GET /api/claims/{id}: Fetch a specific claim by ID.
      3.    POST /api/claims: Create a new claim.
      4.    PUT /api/claims/{id}: Update a claim's information.
      5.    DELETE /api/claims/{id}: Delete a claim.
