package com.lingona.scala;


interface Transformer<P, R> {
	  public R execute(P param);
};

class NumberToFrenchStringTransformer implements Transformer<Integer, String> {
  public String execute(Integer param) {
    switch (param.intValue()) {
      case 0: return "zero";
      case 1: return "un";
      case 2: return "deux";
      case 3: return "trois";
      default: throw new RuntimeException("Je ne sais quoi");
    }
  }
}

public class TransformerJava {
  void countToThree(Transformer<Integer, String> numberToStringTransformer) {
    for (int i=1; i<4; i++)
      System.out.println(
        numberToStringTransformer.execute(
          new Integer(i)));
  }
  
  public static void main(String[] args) {
	    new TransformerJava().countToThree(new NumberToFrenchStringTransformer());
  }
}
