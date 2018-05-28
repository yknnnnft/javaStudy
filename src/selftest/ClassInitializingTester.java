package selftest;

public class ClassInitializingTester {

	static public class Parent {
		
		protected static String staticStr = "Static Variable in Parent";
		protected String str = "Variable in Parent";
		protected int i = 1;
		
		static {
			System.out.println(staticStr);
			System.out.println("static initializing block in Parent");
		}
		
		{
			System.out.println(str);
			System.out.println("i = " + i);
			System.out.println("initializing block in Parent");
		}
		
		Parent() {
			i = 2;
			System.out.println("i = " + i);
			System.out.println("Contructor in Parent");
		}
		
	}
	
	static public class Sub extends Parent{
				
		protected static String staticStr = "Static Variable in Sub";
		protected String str = "Variable in Sub";
		protected int i = 3;
		
		static {
			System.out.println(staticStr);
			System.out.println("static initializing block in Sub");
		}
		
		{
			System.out.println(str);
			System.out.println("i = " + i);
			System.out.println("initializing block in Sub");
		}
		
		Sub() {
			i = 4;
			System.out.println("i = " + i);
			System.out.println("Contructor in Sub");
		}
	}

}
