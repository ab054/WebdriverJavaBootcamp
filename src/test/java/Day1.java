import org.testng.annotations.Test;

public class Day1 {

    @Test
    public void test001(){
        String mess1 = "Welcome to Java BootCamp!!!";
        String mess2 = "Welcome to Portnov Computer School!";

        print(mess1);
        Tools.myPrint(mess1);
        Tools.myPrint(mess2);
    }

    public void print(String parameter){
        System.out.println(parameter);
    }

    //method start here
    @Test
    public void test002(){
        String mess1 = "Another Welcome to Java!!";
        String mess2 = "One more Welcome to Java!!!";

        Tools.myPrint(mess1);
        Tools.myPrint(mess2);
    }
    //method ends here


    @Test
    public void test003() throws Exception {
        String leftSide = "leftSide Message";
        String rightSide = "rightSide Message";

        System.out.println(leftSide +  " " + rightSide);
    }

    @Test
    public void test004() throws Exception {
        String hello = "Hello my Friend";
        String myFriend = "Aleksei";

        System.out.println(hello + " " + myFriend);
    }

    @Test
    public void test005() throws Exception {
        String iHave = "I have";
        int numberOfapples = 5;
        String apples = "apples";

        Tools.myPrint(iHave + " " + numberOfapples + " " + apples);
    }

    @Test
    public void test006() throws Exception {
        boolean b = false;
        b = true;

        boolean toBe = false;
        // true = true || false;
        // false = true && false;
        b = toBe || !toBe; // b = !false = true // !tobe=true

        // 1 == 1 || 2 == 1  => true

        // !false && true

        // 1 + 0 = 1
        // 1 * 0 = 0

        if (!b) {
            System.out.println(toBe);
        }


    }

    @Test
    public void test007() throws Exception {
        boolean amIPrinting = false;
        String name = "Aleksei";

        if(!amIPrinting) {
            System.out.println(name);
        }
    }

    @Test
    public void test008() throws Exception {
        int a = 4;
        int b = 5;

        if (a == b) {
            Tools.myPrint("a and b are equal");
        } else {
            Tools.myPrint("a and b are NOT equal");
        }
    }

    @Test
    public void test009() throws Exception {
        //string variable
        //string variable

        //of both string are equal
        //perform action 1

        //if not equal
        //perform action 2

        String string1 = "string One";
        String string2 = "string Two";

        if(string1 == string2){
            System.out.println("string1 and string2 ARE equal");

        } else {
            System.out.println("string1 and string2 are NOT equal");
        }
    }

    @Test
    public void test010() throws Exception {
        // i = 0
        // check for condition (0 < 3)
        // executing
        // i = 1
        // check for condition (1 < 3)
        // executing
        // i = 2
        // check for condition (2 < 3)
        // executing
        // i = 3
        // check for condition (3 < 3)
        // not executing


        for (int i = 0; i < 3; i++) {
            System.out.println("Print is happened");
        }

    }

    @Test
    public void test011() {
        //string variable
        // print out it 10 times using for loop

        String name = "Selenium WebDriver";
        for (int i = 100; i <= 109; i++) {
            Tools.myPrint(name);

            System.out.println(i);
        }
    }

    @Test
    public void test012() throws Exception {
        String name = "While Loop";

        int i = 0;

        while (i < 10){
            Tools.myPrint("While Loop print");
            //i++;
            i = i + 2;
        }
    }






}