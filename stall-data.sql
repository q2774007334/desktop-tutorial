/*
SQLyog Ultimate v13.1.1 (64 bit)
MySQL - 5.5.56 : Database - licai
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`licai` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `licai`;

/*Table structure for table `licai_consume` */

DROP TABLE IF EXISTS `licai_consume`;

CREATE TABLE `licai_consume` (
  `id` varchar(50) NOT NULL,
  `c_type` varchar(50) DEFAULT NULL COMMENT '消费类型',
  `c_money` decimal(10,0) DEFAULT NULL,
  `c_max_money` decimal(10,0) DEFAULT NULL,
  `c_date` datetime DEFAULT NULL,
  `u_id` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `licai_consume` */

insert  into `licai_consume`(`id`,`c_type`,`c_money`,`c_max_money`,`c_date`,`u_id`) values 
('1368541594261004288','食品',15,2000,'2021-03-01 20:39:00','1369107397888843776'),
('1368541874121744384','家用',20,2000,'2021-03-03 20:39:10','1369107397888843776'),
('1371276125686603776','食品',15,NULL,'2021-03-15 00:00:00','1369107397888843776'),
('1371276184507523072','食品',15,NULL,'2021-03-15 00:00:00','1369107397888843776'),
('1371276240665059328','食品',15,NULL,'2021-03-15 00:00:00','1369107397888843776'),
('1371276792845180928','手机',10,NULL,'2021-03-15 00:00:00','1369216878350049280'),
('1372783169937743872','手机',15,NULL,'2021-03-19 00:00:00','1369216878350049280'),
('1660302807611613184','出行',5,NULL,'2023-05-21 00:00:00','1369107397888843776'),
('1660302877711015936','出行',20,NULL,'2023-05-21 00:00:00','1369107397888843776');

/*Table structure for table `licai_family` */

DROP TABLE IF EXISTS `licai_family`;

CREATE TABLE `licai_family` (
  `id` varchar(50) NOT NULL COMMENT '主键',
  `f_name` varchar(50) DEFAULT NULL COMMENT '家庭成员名称',
  `f_tel` varchar(11) DEFAULT NULL COMMENT '电话',
  `f_mail` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `f_relationship` varchar(20) DEFAULT NULL COMMENT '人物关系',
  `u_id` varchar(50) DEFAULT NULL COMMENT '会员账号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `licai_family` */

insert  into `licai_family`(`id`,`f_name`,`f_tel`,`f_mail`,`f_relationship`,`u_id`) values 
('1369107397888843776','小美','13265789421','13265789421@qq.com','妹妹','1367407052804464640'),
('1369216878350049280','小林','13467590731','13467590731@qq.com','弟弟','1367407052804464640'),
('1369568107500544000','小王','13678595431','13678595431@qq.com','二哥','1367407052804464640'),
('1616449066575994880','小天','13865439061','13865439061@qq.com','儿子','1367407052804464640'),
('1659743320022589440','小五','13265478909','13265478909@qq.com','三弟','1367407052804464640'),
('1660298680928182272','小白','13246532187','13246532187@qq.com','兄弟','1367407052804464640');

/*Table structure for table `licai_family_income` */

DROP TABLE IF EXISTS `licai_family_income`;

CREATE TABLE `licai_family_income` (
  `id` varchar(50) NOT NULL,
  `f_name` varchar(50) DEFAULT NULL COMMENT '收入类型',
  `f_income` decimal(10,0) DEFAULT NULL COMMENT '家庭收入',
  `f_type_money` smallint(6) DEFAULT NULL COMMENT '1代表收入,0代表消费',
  `f_date` datetime DEFAULT NULL,
  `u_id` varchar(50) DEFAULT NULL COMMENT '用户id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `licai_family_income` */

insert  into `licai_family_income`(`id`,`f_name`,`f_income`,`f_type_money`,`f_date`,`u_id`) values 
('1369315071293267968','理财收入',3000,1,'2021-02-28 00:00:00','1369107397888843776'),
('1369316039737090048','工资收入',10000,1,'2021-02-27 23:55:31','1369216878350049280'),
('1369567197709869056','工资收入',6000,1,'2021-02-01 00:00:00','1369107397888843776'),
('1369611778480545792','家用',200,0,'2021-03-02 19:31:18','1369107397888843776'),
('1369611778480545793','食品',100,0,'2021-03-03 19:32:05','1369107397888843776'),
('1369624476027068416','手机',100,0,'2021-03-10 00:00:00','1369216878350049280'),
('1371099020738830336','家用',300,0,'2021-03-09 00:00:00','1369107397888843776'),
('1371741015610105856','理财收入',2000,1,'2021-02-16 00:00:00','1369568107500544000'),
('1372784285781991424','美妆',300,0,'2021-03-14 00:00:00','1369568107500544000'),
('1637450551409385472','工资收入',8000,1,'2023-03-19 00:00:00','1616449066575994880'),
('1660300837521858560','出行',100,0,'2023-05-21 00:00:00','1369107397888843776'),
('1660551272119803904','其他',1000,1,'2023-02-22 00:00:00','1369107397888843776');

/*Table structure for table `licai_tips` */

DROP TABLE IF EXISTS `licai_tips`;

CREATE TABLE `licai_tips` (
  `id` varchar(32) NOT NULL,
  `tips_title` varchar(100) DEFAULT NULL,
  `tips_content` text,
  `tips_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `licai_tips` */

insert  into `licai_tips`(`id`,`tips_title`,`tips_content`,`tips_date`) values 
('1624339040491151360','邮储银行首批代理销售个人养老金理财产品','2月10日，银行业理财登记托管中心有限公司公布了首批个人养老金理财名录。同日，中国邮政储蓄银行作为首批银行，在全国36个个人养老金制度先行城市或地区上线个人养老金理财业务，为个人养老金参加人提供资金账户开立、基金投资、理财投资、保险保障等全方位的养老金投资服务。\n\n根据首批入选个人养老金理财产品名单，邮储银行上线旗下全资理财子公司中邮理财有限责任公司推出的个人养老金理财产品——添颐·鸿锦最短持有期系列产品。该系列产品支持通过个人养老金资金账户购买，以长期、稳健、普惠为主要立足点，向客户提供养老金融服务、理财手续费和税收双重优惠，支持长期小额投资积累养老储备，让广大投资者切实受益','2023-02-11 00:00:00'),
('1624416981807931392','快讯丨渤海财险2022年实现保险业务收入33.75亿元','财经网金融讯 1月30日，渤海财产保险股份有限公司发布2022年第四季度偿付能力报告。报告披露，2022年该公司实现保险业务收入33.75亿元，净利润为3471.83万元，投资收益率为3.6%。截至2022年四季度末，该公司核心偿付能力充足率为103.76%，综合偿付能力充足率为121.34%。','2023-02-07 00:00:00'),
('1624417087772827648','中银保险2022年实现保险业务收入约58亿元','财经网金融讯 1月30日，中银保险有限公司发布2022年4季度偿付能力季度报告。报告披露，2022年该公司实现保险业务收入共约58亿元，净利润为3.02亿元，投资收益率为3.98%。截至2022年四季度末，该公司核心偿付能力充足率为321.8%，综合偿付能力充足率为337.89%。','2023-02-10 00:00:00'),
('1624417171218505728','中华联合财险董事长高兴华任职资格获批','财经网金融讯 1月30日，银保监会官网发布《关于中华联合财产保险股份有限公司高兴华任职资格的批复》。批复显示，中国银保监会核准高兴华中华联合财产保险股份有限公司董事长的任职资格。银保监会指出，中华联合财产保险股份有限公司应督促上述核准任职资格人员持续学习和掌握经济金融相关法律法规，熟悉任职岗位职责，忠实勤勉履职。','2023-02-08 00:00:00'),
('1660552172133556224','中办、国办发文！明确基本养老大事 2025年基本养老服务制度体系基本健全','据新华社5月21日消息，中共中央办公厅、国务院办公厅印发了《关于推进基本养老服务体系建设的意见》。《意见》提出，到2025年，基本养老服务制度体系基本健全，基本养老服务清单不断完善，服务对象、服务内容、服务标准等清晰明确，服务供给、服务保障、服务监管等机制不断健全，基本养老服务体系覆盖全体老年人','2023-05-22 00:00:00');

/*Table structure for table `licai_user` */

DROP TABLE IF EXISTS `licai_user`;

CREATE TABLE `licai_user` (
  `id` varchar(50) NOT NULL COMMENT 'id',
  `u_name` varchar(50) DEFAULT NULL COMMENT '账号',
  `u_password` varchar(32) DEFAULT NULL COMMENT '密码',
  `u_tel` varchar(20) DEFAULT NULL,
  `u_img` varchar(500) DEFAULT NULL COMMENT '头像',
  `u_stop` smallint(6) DEFAULT NULL COMMENT '禁用',
  `u_isadmin` smallint(6) DEFAULT NULL COMMENT '管理员',
  `u_email` varchar(50) DEFAULT NULL,
  `u_desc` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `licai_user` */

insert  into `licai_user`(`id`,`u_name`,`u_password`,`u_tel`,`u_img`,`u_stop`,`u_isadmin`,`u_email`,`u_desc`) values 
('1367027456749740032','admin','e10adc3949ba59abbe56e057f20f883e','18380239821','https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=2382124539,922770330&fm=26&gp=0.jpg',1,1,'123456@qq.com','熊猫说竞技'),
('1367119987944595456','lisi','e10adc3949ba59abbe56e057f20f883e','18380006578','https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=1332282845,3547126283&fm=26&gp=0.jpg',1,1,'25023434@qq.com','超级霞'),
('1367121411881443328','zhangsan','e10adc3949ba59abbe56e057f20f883e','18399006578','https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=2910462874,64375929&fm=26&gp=0.jpg',1,1,'0209@qq.com','说说爱情'),
('1367404739478036480','buzhidao','e10adc3949ba59abbe56e057f20f883e','182972994929','https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=244154007,3408945172&fm=26&gp=0.jpg',1,1,'buzhidao@qq.com','不知道行不'),
('1367405286847291392','taishengyige','e10adc3949ba59abbe56e057f20f883e','18390279328','https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fpic.soutu123.cn%2Felement_origin_min_pic%2F16%2F09%2F13%2F2057d7ea86286ed.jpg%21%2Ffw%2F700%2Fquality%2F90%2Funsharp%2Ftrue%2Fcompress%2Ftrue&refer=http%3A%2F%2Fpic.soutu123.cn&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1617441721&t=e5bd808236645259bdcce1ec79e579b8',1,0,'taishengyige@qq.com','不拍你如何的好'),
('1367405916953387008','xiongmaotiantian','e10adc3949ba59abbe56e057f20f883e','18328479762','https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=2372543626,2764414893&fm=26&gp=0.jpg',0,0,'xiongmaotiantian@163.com','熊猫天天'),
('1367407052804464640','yaoliyaoqi','e10adc3949ba59abbe56e057f20f883e','138790466781','https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=1175571471,2229885055&fm=26&gp=0.jpg',1,0,'yaoliyaoqi@163.com','妖里妖气得很'),
('1367727059962830848','有车云我笔随我心','e10adc3949ba59abbe56e057f20f883e','1380470032','https://upload.jianshu.io/users/upload_avatars/23849326/f69211c0-03eb-4077-b912-694e5ca1c718.jpg?imageMogr2/auto-orient/strip|imageView2/1/w/240/h/240',1,1,'youchezhiqiang@163.com','有车云我笔随我心'),
('1367727334169649152','苍榆之历史','e10adc3949ba59abbe56e057f20f883e','17802374902','https://upload.jianshu.io/users/upload_avatars/23034476/1a963fac-ae0d-4b77-9c16-d478a78daf13.jpg?imageMogr2/auto-orient/strip|imageView2/1/w/240/h/240',1,1,'cangshu@sina.com','苍榆'),
('1367738946322571264','seven_小汤豆','96e79218965eb72c92a549dd5a330112','13804743072','https://upload.jianshu.io/users/upload_avatars/16425144/55e80bc4-4fd3-4890-a1c9-c90d7938e299.jpg?imageMogr2/auto-orient/strip|imageView2/1/w/240/h/240',1,1,'seven@sina.com','seven_小汤豆'),
('1367739346211708928','柒情社之美','96e79218965eb72c92a549dd5a330112','1382075903','https://upload.jianshu.io/users/upload_avatars/16535071/a7044302-0bd5-4fc1-89e5-00fe934d1dba.png?imageMogr2/auto-orient/strip|imageView2/1/w/240/h/240',1,1,'qiqise@126.com','柒情社'),
('1367739649019486208','里_拉的里_拉','96e79218965eb72c92a549dd5a330112','1380437907','https://upload.jianshu.io/admin/source_image/1bd6b3bb26b959d8bd7a?imageMogr2/auto-orient/strip|imageView2/1/w/134/h/134/format/webp',1,1,'lials@qq.com','里_拉的里_拉'),
('1372569630820147200','shoujishuma','e10adc3949ba59abbe56e057f20f883e','13872937421','https://p6-xg.byteimg.com/img/user-avatar/c350f4748ab85d160550e6489e2d8714~tplv-xg-center-qs:116:116:q75.webp',1,0,'shoujishuma@163.com','手机数码'),
('1372570387590029312','kaixin56789','e10adc3949ba59abbe56e057f20f883e','13386039851','https://p6-tt-ipv6.byteimg.com/large/user-avatar/818f950adaee2f5290a330ee414bc4ae',1,0,'kaixin56789@qq.com','kaixin56789'),
('1372571238803054592','宇宙观察室','e10adc3949ba59abbe56e057f20f883e','18283892811','https://p3-xg.byteimg.com/img/pgc-image/164a3511a8dc4b639dc16d1e30b829d3~tplv-xg-center-qs:116:116:q75.jpg',1,0,'cksaow@sina.com','宇宙观察室'),
('1592899702264045568','lisi123456','e10adc3949ba59abbe56e057f20f883e','13898745760','https://img2.baidu.com/it/u=1807443568,704507401&fm=253&fmt=auto&app=138&f=JPEG?w=524&h=500',1,1,'lisi@sina.com','小姐姐'),
('1611002714702356480','海贼王路飞','e10adc3949ba59abbe56e057f20f883e','13278904681','https://t7.baidu.com/it/u=2511982910,2454873241&fm=193&f=GIF',1,0,'haizei@163.com','理财记账系统'),
('1660549869112532992','k777','2be19a2994c5d8a3bc2a4777c446092f','13245687654','https://img0.baidu.com/it/u=1993557595,4075530522&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=500',1,0,'k777@163.com','k777');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
