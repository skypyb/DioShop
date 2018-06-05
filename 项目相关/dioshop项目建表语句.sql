CREATE TABLE shop_user(--用户表
       user_id NUMBER NOT NULL,
       user_name VARCHAR2(20) NOT NULL,
       user_pwd VARCHAR2(20) NOT NULL,
       user_type NUMBER(1) NOT NULL,
       CONSTRAINT pk_shop_user PRIMARY KEY(user_id)
)

CREATE TABLE shop_protype(--商品类型表
       prot_id NUMBER NOT NULL,
       prot_name VARCHAR2(20) NOT NULL,
       CONSTRAINT pk_shop_protype PRIMARY KEY(prot_id)
)

CREATE TABLE shop_product(--具体商品表
       pro_id NUMBER NOT NULL,
       prot_id NUMBER NOT NULL,
       pro_type VARCHAR2(20) NOT NULL,
       pro_name VARCHAR2(20) NOT NULL,
       pro_size VARCHAR2(5),
       pro_brand VARCHAR2(20),
       pro_color VARCHAR2(10),
       pro_sex VARCHAR2(2),
       pro_uad VARCHAR2(4),
       pro_price NUMBER NOT NULL,
       pro_inv NUMBER(5) NOT NULL,
       CONSTRAINT pk_shop_product PRIMARY KEY(pro_id),
       CONSTRAINT fk_shop_product_1 FOREIGN KEY(prot_id) REFERENCES shop_protype(prot_id)
)

CREATE TABLE shop_pro_image(--商品图片表
       img_id NUMBER NOT NULL,
       pro_id NUMBER NOT NULL,
       img_type NUMBER(1) NOT NULL,
       img_src VARCHAR2(200) NOT NULL，
       CONSTRAINT pk_shop_pro_image PRIMARY KEY(img_id),
       CONSTRAINT fk_shop_pro_image1 FOREIGN KEY(pro_id) REFERENCES shop_product(pro_id)
)


CREATE TABLE shop_pro_review(--商品评价表
       rev_id NUMBER NOT NULL,
       user_id NUMBER NOT NULL,
       pro_id NUMBER NOT NULL,
       rev_msg VARCHAR2(300) NOT NULL,
       CONSTRAINT pk_shop_pro_review PRIMARY KEY(rev_id)，
       CONSTRAINT fk_shop_pro_review1 FOREIGN KEY(user_id) REFERENCES shop_user(user_id),
       CONSTRAINT fk_shop_pro_review2 FOREIGN KEY(pro_id) REFERENCES shop_product(pro_id)
)


CREATE TABLE  shop_order(--订单表
       order_id NUMBER NOT NULL,
       orderCode NUMBER NOT NULL,
       address VARCHAR2(100) NOT NULL,
       receiver VARCHAR(100) NOT NULL,
       mobile NUMBER(11) NOT NULL,
       userMessage VARCHAR2(100),
       createDate DATE NOT NULL,
       payDate DATE NOT NULL,
       deliveryDate DATE NOT NULL,
       confirmDate DATE NOT NULL,
       status NUMBER(1),
       user_id NUMBER NOT NULL,
       CONSTRAINT pk_shop_order PRIMARY KEY(order_id),
       CONSTRAINT fk_shop_order1 FOREIGN KEY(user_id) REFERENCES shop_user(user_id)
)



CREATE TABLE shop_orderitem(--订单项表
       itme_id NUMBER NOT NULL,
       user_id NUMBER NOT NULL,
       pro_id NUMBER NOT NULL,
       order_id NUMBER,
       quantity NUMBER NOT NULL,
       CONSTRAINT pk_shop_orderitem PRIMARY KEY(itme_id),
       CONSTRAINT fk_shop_orderitem1  FOREIGN KEY(user_id) REFERENCES shop_user(user_id),
       CONSTRAINT fk_shop_orderitem2 FOREIGN KEY(pro_id) REFERENCES shop_product(pro_id),
       CONSTRAINT fk_shop_orderitem3 FOREIGN KEY(order_id) REFERENCES shop_order(order_id)
)



