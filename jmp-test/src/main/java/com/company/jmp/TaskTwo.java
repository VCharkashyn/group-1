package com.company.jmp;

public class TaskTwo {

    public static void main(String[] args) {
        Integer value = new Integer(15454545);
        String type = args == null || args.length == 0 ? null :  args[0];

        if ("obj".equals(type)) {
            while (true) {
                testGetObject(value);
            }
        } else if ("scalar".equals(type)) {
            while (true) {
                testGetScalar(value);
            }
        } else {
            while (true) {
                test(value);
            }
        }
    }

    private static void test(Integer val){
        new Integer(val);
    }

    private static Integer testGetObject(Integer val){
       return new Integer(val);
    }

    private static int testGetScalar(Integer val){
       return val !=null ? val.intValue() : 0;
    }
}
