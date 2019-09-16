package Dynamic_proxy;
//第二步：创建具体实现类UserImpl.java
public class UserImpl implements Iuser {
	public void eat(String s) {
		System.out.println("我要吃"+s);
	}
}
