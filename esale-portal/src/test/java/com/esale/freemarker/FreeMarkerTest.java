package com.esale.freemarker;


import freemarker.template.Configuration;

public class FreeMarkerTest {
//	1.可以使用maven加载坐标/也可以使用jar包 ，portal中添加
//	2.freemarker的运行不依赖web容器，可以在java工程中运行。创建一个测试方法进行测试
//	3.创建一个configration对象
	Configuration configuration = new Configuration(Configuration.getVersion());
//	4.告诉config对象模板文件存放的路径

//	5.设置config的默认字符集，一般是utf-8
//	6.从config对象中获得模板对象，需要指定一个模板文件的名字
//	7.创建模板需要的数据集。可以是一个map对象，也可以是一个pojo，把模板需要的数据都放入数据集
//	8.创建一个writer对象，指定生成的文件保存的路径及文件名
//	9.调用模板对象的process方法生成静态文件。需要两个参数数据集和writer对象
//	10.关闭writer对象
}
