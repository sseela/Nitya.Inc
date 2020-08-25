package com.inc.nitya;

public class FirstProgram {
	public static SecondClass c;
	public static SecondClass a;
	public static void main(String[] args) {
		a.b.c = c.b.a;			// This is the required line
	}
}

class SecondClass {
	public static ThirdClass b;
}

class ThirdClass {
	public static int a = 10;
	public static int c;
}


