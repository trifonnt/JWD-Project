= URLs
 - http://localhost:8088/springWebShop/login


01.1) Описание на проекта-BG

Този проект представя Уеб магазин.
Според правата за достъп в магазина имаме два вида потребители:
 - Потребители на уеб магазина, които могат да поръчват продукти.
 - Служители на фирмата оперираща уеб магазина, които могат да редактират данните в уеб магазина. 

01.2) Description-EN

From technical point of view this project is implemented as Standard JEE Application.
This project needs Web Container like Apache Tomcat and Oracle DB in order to run.

02) Диаграма на класовете
 - 01_WS_User_Role.jpg
 - 02_WS_Product.jpg
 - 03_WS_Order_n_OrderLine.png

03) DDL and DML files
 - DDL.sql
 - DML.sql


04) Описание на класовете в проекта

04.1) Слой с класове, които описват предметната област на приложението (Model)
 - bg/jwd/spring/model/common/Location.java

 - bg/jwd/spring/model/security/Role.java
 - bg/jwd/spring/model/security/User.java

 - bg/jwd/spring/model/product/Product.java
 - bg/jwd/spring/model/product/ProductType.java

 - bg/jwd/spring/model/order/Order.java
 - bg/jwd/spring/model/order/OrderLine.java

04.2) Слой за достъп до данните (DAO)
 - bg/jwd/spring/dao/AbstractHibernateDAO.java

 - bg/jwd/spring/dao/security/IRoleDao.java
 - bg/jwd/spring/dao/security/IUserDao.java
 - bg/jwd/spring/dao/security/impl/RoleDaoImpl.java
 - bg/jwd/spring/dao/security/impl/UserDaoImpl.java

 - bg/jwd/spring/dao/product/IProductDao.java
 - bg/jwd/spring/dao/product/IProductTypeDao.java
 - bg/jwd/spring/dao/product/impl/ProductDaoImpl.java
 - bg/jwd/spring/dao/product/impl/ProductTypeDaoImpl.java

 - bg/jwd/spring/dao/order/IOrderDao.java
 - bg/jwd/spring/dao/order/impl/OrderDaoImpl.java


04.3) Слой с класове, които предоставят бизнес функционалност (Service)
 - bg/jwd/spring/service/IUserService.java
 - bg/jwd/spring/service/impl/UserServiceImpl.java
 - bg/jwd/spring/service/impl/UserDetailsServiceImpl.java

 - bg/jwd/spring/service/IProductService.java
 - bg/jwd/spring/service/impl/ProductServiceImpl.java
   
 - bg/jwd/spring/service/IProductTypeService.java
 - bg/jwd/spring/service/impl/ProductTypeServiceImpl.java

 - bg/jwd/spring/service/IOrderService.java
 - bg/jwd/spring/service/impl/OrderServiceImpl.java


04.4) Слой с класове, които получават заявките от уеб брaузарите (Controller)
 - bg/jwd/spring/controller/LoginController.java
 - bg/jwd/spring/controller/CustomerController.java
 - bg/jwd/spring/controller/ProductController.java
 - bg/jwd/spring/controller/ProductTypeController.java
 - bg/jwd/spring/controller/OrderController.java


05) TODO

-05.1) Issues
 - Editing a customer throws Exception!
   Request processing failed; nested exception is org.hibernate.HibernateException: Illegal attempt to associate a collection with two open sessions. Collection : [bg.jwd.spring.model.security.User.roles#8]
   http://localhost:8088/springWebShop/customer/8/edit

-05.2) Improvements
 - Add Localization
 - Add Properties to store DB specific settings(DB username, DB password, DB URL)
 - Add Tests


06) Notes
 - In xxxDTO classes always use primitive wrapper classes(Long, Integer, Float).
