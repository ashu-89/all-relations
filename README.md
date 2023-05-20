# all-relations

Reference:
https://thorben-janssen.com/ultimate-guide-association-mappings-jpa-hibernate/

We can have the following relations in an rdbms:
1. one to one
2. one to many
3. many to many

And each of them can be unidirectional (from either side), or bi-directional in jpa.
<br>
<p>
Although, we can create similar relations (meaning either via FKs or Join tables)  in db using bi-directional as well as uni-directional relations (from either side),
unidirectional or bidirectional helps us to NAVIGATE the other entity from current entity objects within JPQL or Criteria query.
</p>
<p>
Sometimes, say if we have one to many relationship, we may not want to create a list on this side of entity (one-to-many side). <br>   
Imagine we have to entities - <b>Company</b> and <b>Employees</b>.<br>
<br>
If a company has 1000 employees, and we create a relation (uni-directional or bi-directional) from this side,
then everytime a company row is fetched, all 1000s of its employees will be loaded in RAM!
<br>
Of course LAZY and EAGER fetch type is one way to handle it, but needs to be explored.
</p>
--
One to many
Company, Employees
1 Company can have many employees, but 1 employee can belong to just 1 company. (legally)

#1. Unidirectional relation from Company to Employee:

&emsp; #1a. With @JoinColum - hibernate creates FK in Employee which references Comapny's id
   
&emsp; #1b. Without @JoinColumn - hibernate creates a join table
   
#2. Unidirectional relation from Employee to Company

#3. Bidirectional relation



