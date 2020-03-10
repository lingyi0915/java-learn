package com.mooc.os;

import java.util.Scanner;

public class Q1_FractionTest {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		Fraction a = new Fraction(in.nextInt(), in.nextInt());
		Fraction b = new Fraction(in.nextInt(),in.nextInt());
		a.print();
		b.print();
		a.plus(b).print();
		a.multiply(b).plus(new Fraction(5,6)).print();
		a.print();
		b.print();
		in.close();
	}
}
class Fraction {
	int a;
	int b;

	public Fraction(int a, int b) {
		this.a = a;
		this.b = b;
	}

	public void print() {
		if(a % b == 0) {
      		System.out.println(a/b);
		} else {
			int g = gcd(a,b);
			System.out.println(a/g+"/"+b/g);
		}
	}

	public Fraction plus(Fraction r) {
		return new Fraction(this.a*r.b + this.b*r.a,this.b*r.b);
	}

	public Fraction multiply(Fraction r) {
		return new Fraction(this.a*r.a,this.b*r.b);
	}

	public double toDouble() {
		return a*1.0/b;
	}

	public int gcd(int a,int b) {
		return a%b != 0 ? gcd(b,a%b) : b;
	}
}