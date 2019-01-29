# DioShop
* 使用纯J2EE完成的电商前台系统，使用纯J2EE核心技术做成，所有代码都在工程中--适合阶段:javaweb   
* 【项目相关】这个文件夹里有我的建表语句以及项目需求说明书还有核心逻辑PPT


#### 注意事项
* 搜索功能、后台、评论为未完成部分
* 由于我使用的是Oracle数据库，使用MySql/sqlserver的建表语句请自行修改
* 建表语句和word文档中的表结构设计需要和【更新文件.txt】一同观看


#### 编写环境:
Spring Tool Suite(就是高级版eclipse) + tomcat9 + jdk1.8 + Oracle11g

#### 系统已实现功能:
1、 用户的登陆注册以及退出（均有验证码判定）  
2、 首页动态展示二级菜单、商品分类列表、查看商品详情  
3、 商品详情页面根据用户选择的颜色和尺码获取该条数据的库存显示到页面  
4、 实现购物车和订单功能，用户选择所需商品后，放到购物车，过滤器进行登陆判断  
5、 购物车实现：商品加入，内部增删改查，数据持久化。购物车为减轻服务器负担，所有功能均在session域实现  
监听器监听到用户退出和session自动销毁时会将购物车和数据库中的数据进行交叉修改  
6、 订单实现：购物车数据指向订单、订单提交、确认订单、结算、支付、我的订单、确认收货、收货成功  

  <br>
  <br>
  <br>
      
## 相关展示:
[项目图片展示](http://skypyb.com/2018/06/02/j2ee%E5%95%86%E5%9F%8E%E9%A1%B9%E7%9B%AE%E5%9B%BE%E7%89%87%E5%B1%95%E7%A4%BA/)  

#### 部分图片展示
![订单页](https://pyb001.oss-cn-shenzhen.aliyuncs.com/%E5%95%86%E5%9F%8E%E9%A1%B9%E7%9B%AE/%E8%AE%A2%E5%8D%95%E9%A1%B5.PNG?x-oss-process=style/blogImg)  
![首页一](https://pyb001.oss-cn-shenzhen.aliyuncs.com/%E5%95%86%E5%9F%8E%E9%A1%B9%E7%9B%AE/%E9%A6%96%E9%A1%B51.jpg?x-oss-process=style/blogImg)  
![首页二](https://pyb001.oss-cn-shenzhen.aliyuncs.com/%E5%95%86%E5%9F%8E%E9%A1%B9%E7%9B%AE/%E9%A6%96%E9%A1%B52.jpg?x-oss-process=style/blogImg)  
  <br>
  <br>
  <br>
  <br>
  <br>
  <br>
[核心逻辑结构](http://skypyb.com/2018/06/04/%E7%BA%AFj2ee%E5%95%86%E5%9F%8E%E3%80%90%E6%89%80%E6%9C%89%E3%80%91%E6%A8%A1%E5%9D%97%E6%B5%81%E7%A8%8B%E5%9B%BE%E4%BB%A5%E5%8F%8A%E9%A1%B9%E7%9B%AE%E6%80%BB%E7%BB%93/)    

#### 部分逻辑结构图展示  
![首页逻辑](https://pyb001.oss-cn-shenzhen.aliyuncs.com/%E5%95%86%E5%9F%8E%E9%A1%B9%E7%9B%AE%E6%B5%81%E7%A8%8B%E5%9B%BE/%E9%A6%96%E9%A1%B5%E9%80%BB%E8%BE%91.PNG?x-oss-process=style/blogImg)
![购物车逻辑](https://pyb001.oss-cn-shenzhen.aliyuncs.com/%E5%95%86%E5%9F%8E%E9%A1%B9%E7%9B%AE%E6%B5%81%E7%A8%8B%E5%9B%BE/%E8%B4%AD%E7%89%A9%E8%BD%A6%E5%AE%9E%E7%8E%B0%E9%80%BB%E8%BE%91.PNG?x-oss-process=style/blogImg)
![订单页逻辑](https://pyb001.oss-cn-shenzhen.aliyuncs.com/%E5%95%86%E5%9F%8E%E9%A1%B9%E7%9B%AE%E6%B5%81%E7%A8%8B%E5%9B%BE/%E6%9F%A5%E7%9C%8B%E8%AE%A2%E5%8D%95%E9%A1%B5%E5%AE%9E%E7%8E%B0%E9%80%BB%E8%BE%91.PNG?x-oss-process=style/blogImg)





>emmm项目名字源于我的恶趣味 
