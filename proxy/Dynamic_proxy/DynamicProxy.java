package Static_proxy;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
public class DynamicProxy implements InvocationHandler {
	private Object object;//���ڽ��վ���ʵ�����ʵ������
	//ʹ�ô������Ĺ����������ݾ���ʵ����Ķ���
	public DynamicProxy(Object obj){
		this.object = obj;
	}
	@Override
	public Object invoke(Object proxy, Method method, Object[] args)throws Throwable {
		System.out.println("ǰ������");
		method.invoke(object, args);
		System.out.println("��������");
		return null;
	}
}