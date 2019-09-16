package Dynamic_proxy;
//第四步：创建测试类ProxyTest.java
public class proxyTest {
	public static void main(String[] args) {    
	UserProxy proxy = new UserProxy();
	proxy.eat("苹果");
	}
}
