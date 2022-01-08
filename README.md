# Verkkokauppa
**Object oriented programming project made with Java. Contributors Nuutti Jussila, Jenna Hakkarainen ja Juho Glumov.**

The exercise was to create either console or destktop software and practice project working. It had to contain *inheritance, encapsulation and static attributes/methods*. 

We decided to code a webstore where there is customers section and stock section. Program utilizes database where customers information and product information are saved/retrieved.  


**Customer can:**

- see the list of products with prices

- add product to shopping cart

- see product details

- check shopping cart and edit it

- use coupon to get discount

- in order to make the purchase, the customer must fill their information (name, email, phone number, address) and choose payment method

- after filling these, the purchase is succesful and receipt is printed (also printed in kuitti.txt which is emptied after printing to console)

**Admin can (after giving the right pin code):**

- print stock contents

- edit product price

- add products to stock

- print customers orders (id, date, name, product, product id, number of pcs)



My responsibility was making the Kayttaja.java where Kayttaja (User) class is created with all of its attributes and methods. There are also Asiakas (Customer) class that extends from Kayttaja and Yllapito (Admin) that extends from Asiakas. I also made Kuitti.java file that has only the class Kuitti (Receipt) since all methods are in Kayttaja.java. The main program Verkkokauppa.java was made in collaboration with other students but was mainly programmed by Juho. Nuutti created databases and programmed Tietokanta.java and Tuote.java. I also did some codes for Tietokanta.java.



**Grade: 5/5**
