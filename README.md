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
1. SRP - ProductController.java
  > Before applying, the ProductController.java was used as the controller for both product related pages and car related pages. During this tutorial I decided to separate them into 2 classes. As SRP requires us to implement, a class should have only one responsibility or encapsulate only one aspect of the software's functionality. Though the functionality seemed similar between both controllers, the classes they work with differs. It would be better to have them grouped accordingly.
2. OCP - Car.java
  > Before applying, Car.java and Product.java were two different classes. In the real world, anything can be a product as long as it was made. Thus, after some consideration, I have decided to make the Car a child of Product. This is due to the fact that both Car and Product has similar attributes with Car having one more, which is carColor.

  > One particular issue I might be having is that I'm just starting to understand this principle near the deadline, so for now, the naming for the attributes still uses the naming for product only (productId, productName, productQuantity).
3. LSP - Idea - Repository
  > Due to the scoring criteria of our learning module and the fact that I'm not quite sure how connected the Product and Car classes are intended to be, I will leave this for future works. But in any case, I want to combine both Car and Product repository into one single Repository class. By doing so, we should be able to reduce page redundancy. Currently, we have both Product pages and Car pages doing similar things but for different classes. However, with the Car class being turned into a child of the Product class, we can combine both classes' pages into only Product pages. With this, and some altering with the create() function, we can create, read, update, and delete both classes using the same pages. We can even add things like showByClass() to the list page as a filter so that only certain classes can appear in the list at a moment.
4. ISP - ProductService.java and CarService.java
  > If we are to ignore the integrations proposed by point 2 and 3, I'd say ISP is well implemented by separating ProductService.java and CarService.java. Though, even when taking into account the integrations from point 2 and 3, it is theoretically possible to have some service methods combined between the two and some other methods separate. This opens up a possibility to add different interfaces for classes who are children of the Product class. 
5. DIP = CarController.java and CarService.java
  > Instead of calling CarServiceImpl straight away on the controller, I have made it so that it calls on the CarService interface instead. This was done to reduce coupling.

## Module 4
### TDD

1. I feel like, yes, TDD flow will be very useful for me in more future works. The reason being is that most of the time, it's just very hard to focus on what tp implement. However, with TDD, we can make sure to only implement what's needed. For example, when making the product class back in tutorial module 1, I ran into and issue where I don't know how I want the implementation to be like. But this time, when making the order class with TDD, it feels much clearer on what I want and how I want to implement.
2. Here are my analysis on how fulfilled the F.I.R.S.T principle is in current tutorial module:
> Fast, yes, we have separated unit tests and functional tests. That way, we can get the results for unit tests without waiting for functional tests' results and vice versa.

> Isolated/Independent, yes, using MockitoExtension we can call functions without interfering with the actual implementations. We also use setUp() annotated with @BeforeEach to avoid test cases, in a way, crashing into each other.

> Repeatable, if the test results stay the same after different testing times, then we are good to go. In this case, yes, the tests are repeatable.

> Self-Validating, yes, we are using assertions to determine whether the tests pass or not. There is one thing I still haven't fully grasp, which is, one test one assertion, does that mean the multiple assertions actually violates the Fast part of the principle?

> Thorough/Timely, for now yes, we have tried to cover as much Happy and Unhappy tests as possible to improve the product quality. Make sure to prepare the product for every thinkable errors during development planning.
