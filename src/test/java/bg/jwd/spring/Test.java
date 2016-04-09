package bg.jwd.spring;

import java.util.ArrayList;
import java.util.Collection;

public class Test {

	private Collection<String> list;

	public Test() {
		list = new ArrayList<String>();
	}
	public Test(String arg0) {
		this();
	}

	public static void main(String[] args) {
		Test test = new Test("Just test string");
		System.out.println("test.list = " + test.list);
	}
}