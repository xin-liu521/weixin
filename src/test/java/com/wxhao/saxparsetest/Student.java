package com.wxhao.saxparsetest;
/**
 * @ClassName: Student
 * @Description: 测试学生类
 * @author zhutulang
 * @date 2016年1月2日
 * @version V1.0
 */
public class Student {
	
	private String name;
	private String age;
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "[name="+name+",age="+age+"]";
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
}
