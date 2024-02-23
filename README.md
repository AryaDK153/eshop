# Exercises & Reflections
## Module 1
### Exercise 1 - Reflection 1
Before, I was unsure to how I should name my functions and usually I would write long comments explaining the obvious things, thus knowing about clean codes lowered my workload. Branching still fells like it may not be needed when doing simple small projects and by yourself, but it is a very good, simple, and easy way to keep track of version changes when dealing with apps with many features. We do need to keep track of how much branch we make, too much can complicate things and definitely waste resources.

There is a small issue I found on my source code. There is no way to uniquely identify each products, which made me resort to identifying each product with product name. The problem arrives when you input 2 different products with the same name, because only now when I'm writing this reflection note that I realize that I need to implement name checking when using this method, so that users can't give 2 or more products with the same name when creating or editing a product. Another improvement that can be good would be to use product id, given automatically when creating products.

### Exercise 2 - Reflection 2
1. What I realized after making the additional unit tests is that some of my code are still not implemented properly or simply unfinished, especially for the features - edit and delete product. I should be more prepared in the future when dealing with "findBy" methods, because as of now, my implementation forced me to rewrite what was already in the methods but not in the proper class, reducing the cleanliness of the code.
2. 

## Module 2
- Sonarcloud Scan: https://sonarcloud.io/summary/new_code?id=AryaDK153_eshop
- Koyeb: [https//resonant-pierette-aryadk153.koyeb.app](https://resonant-pierette-aryadk153.koyeb.app/)
### 4.2 Reflection
Code quality issues
1. remove field injection (autowired) and use constructor injection instead
  > was not fixed,  but accepted instead. when constructor injection is used, some of the tests broke. with not much time left, I decided to keep the code as is.
2. define constraint instead of rewriting a value
  > cleaned up some redundancy for redirect. created a new static final variable.
3. remove public modifiers from tests
  > switched tests' modifiers to default (package-private) instead of public.

## Module 3
### S.O.L.I.D Principle
Applied in this project:
1. SRP - productController.java
  > Before applying, the productController.java was used as the controller for both product related pages and car related pages. During this tutorial I decided to separate them into 2 classes. As SRP requires us to implement, a class should have only one responsibility or encapsulate only one aspect of the software's functionality. Though the functionality seemed similar between both controllers, the classes they work with differs. It would be better to have them grouped accordingly.
2. OCP
  > 
3. LSP
  > 
4. ISP
  > 
5. DIP
  > 