= URLs
 - http://localhost:8088/springWebShop/login


01) Описание на проекта
Този проект представя Уеб магазин.
Според правата за достъп в магазина имаме два вида потребители:
 - Потребители на уеб магазина, които могат да поръчват продукти.
 - Служители на фирмата оперираща уеб магазина, които могат да редактират данните в уеб магазина. 


02) Диаграма на класовете
 - 01_WS_User_Role.jpg
 - 02_WS_Product.jpg
 - 

03) DDL and DML files
 - DDL.sql
 - DML.sql


04) Описание на класовете в проекта

04.1) Слой с класове, които описват предметната област на приложението (Model)
 - bg/jwd/spring/model/security/Role.java
 - bg/jwd/spring/model/security/User.java

 - bg/jwd/spring/model/product/Product.java
 - bg/jwd/spring/model/product/ProductType.java

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

04.3) Слой с класове, които предоставят бизнес функционалност (Service)
 - bg/jwd/spring/service/IUserService.java
 - bg/jwd/spring/service/impl/UserServiceImpl.java

 - bg/jwd/spring/service/IProductService.java
 - bg/jwd/spring/service/impl/ProductServiceImpl.java
   
 - bg/jwd/spring/service/IProductTypeService.java
 - bg/jwd/spring/service/impl/ProductTypeServiceImpl.java

04.4) Слой с класове, които получават заявките от уеб брaузарите (Controller)
 - bg/jwd/spring/controller/LoginController.java
 - bg/jwd/spring/controller/CustomerController.java
 - bg/jwd/spring/controller/ProductController.java
 - bg/jwd/spring/controller/ProductTypeController.java


05) TODO
 - Issues: Editing a customer throws Exception!
   Request processing failed; nested exception is org.hibernate.HibernateException: Illegal attempt to associate a collection with two open sessions. Collection : [bg.jwd.spring.model.security.User.roles#8]
   http://localhost:8088/springWebShop/customer/8/edit
