package Dynamic_proxy;
//��������������������UserProxy.java
public class UserProxy implements Iuser {
	private Iuser user = new UserImpl();
	@Override
	public void eat(String s) {
		System.out.println("��̬����ǰ������");
		user.eat(s);
		System.out.println("��̬�����������");
	}
}
