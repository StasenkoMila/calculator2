import java.util.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Calculator2 {
    private static int result;
    private static String [] inputArray;
    private static final HashMap<Integer, String> map = new HashMap<>();

    static {
        map.put(1, "I");
        map.put(2, "II");
        map.put(3, "III");
        map.put(4, "IV");
        map.put(5, "V");
        map.put(6, "VII");
        map.put(7, "VII");
        map.put(8, "VIII");
        map.put(9, "IX");
        map.put(10, "X");
        map.put(20, "XX");
        map.put(30, "XXX");
        map.put(40, "XL");
        map.put(50, "L");
        map.put(60, "LX");
        map.put(70, "LXX");
        map.put(80, "LXXX");
        map.put(90, "XC");
        map.put(100, "C");
        map.put(200, "CC");
        map.put(300, "CCC");
        map.put(400, "CD");
        map.put(500, "D");
        map.put(600, "DC");
        map.put(700, "DCC");
        map.put(800, "DCCC");
        map.put(900, "CM");
    }


    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        inputArray = input.split(" ");


        try {
            if (inputArray[0].matches(".*[IVXLC].*") && inputArray[2].matches(".*[IVXLC].*")) {
                int num1 = toArabic(inputArray[0]);
                int num2 = toArabic(inputArray[2]);
                int calculated = calculate(num1, num2);
                if (calculated <= 1000){
                    System.out.println(toRoman(String.valueOf(calculate(num1, num2))));
                } else {
                    throw new ArithmeticException();
                }
            } else if (inputArray[0].matches("\\d+") && inputArray[2].matches("\\d+")) {
                int num1 = Integer.parseInt(inputArray[0]);
                int num2 = Integer.parseInt(inputArray[2]);
                int calculated = calculate(num1, num2);
                if (calculated <= 1000){
                    System.out.println(calculate(num1, num2));
                } else {
                    throw new ArithmeticException();
                }
            } else {
                throw new NumberFormatException();
            }
        }catch (NumberFormatException e){
            System.out.println("Ошибка!");
        } catch (ArithmeticException e){
            System.out.println("Упс! Калькулятор умеет считать только до 1000!");
        }

    }

    public static int convertRoman(String rmn){
        int arabic = 0;
        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            String value = entry.getValue();
            if (rmn.equals(value)) {
                arabic = entry.getKey();
            }
        }
        return arabic;
    }

    public static String convertArabic (int arb){
        String roman = "";
        for(Map.Entry<Integer, String> entry : map.entrySet()){
            Integer key = entry.getKey();
            if (arb == key){
                roman = entry.getValue();
            }
        }
        return roman;
    }

    public static String toRoman(String arabicNum){
        String res;
        String [] arabicNumArray = arabicNum.split("");
        StringBuilder bd = new StringBuilder();
        if(arabicNumArray.length == 1){
            int unit = Integer.parseInt(arabicNumArray[0]);
            res = convertArabic(unit);
        } else if (arabicNumArray.length == 2){
            int dec = Integer.parseInt(arabicNumArray[0]) * 10;
            int unit = Integer.parseInt(arabicNumArray[1]);
            res = bd.append(convertArabic(dec)).append(convertArabic(unit)).toString();
        } else {
            int hund = Integer.parseInt(arabicNumArray[0]) * 100;
            int dec = Integer.parseInt(arabicNumArray[0]) * 10;
            int unit = Integer.parseInt(arabicNumArray[1]);
            res = bd.append(convertArabic(hund)).append(convertArabic(dec)).append(convertArabic(unit)).toString();
        }
        return res;
    }

    public static final int toArabic(String romanNum){
        int res = 0;
        String [] splitted = romanNum.split("");
        ArrayList <String> romanNumList = new ArrayList<>(List.of(splitted));
        if (romanNumList.size() % 2 != 0) romanNumList.add("");

        for (int i = romanNumList.size(); i > 0; i = i - 2) {
            int pair;
            if (convertRoman(romanNumList.get(i - 1)) > convertRoman(romanNumList.get(i - 2))) {
                pair = convertRoman(romanNumList.get(i - 1)) - convertRoman(romanNumList.get(i - 2));

            } else {
                pair = convertRoman(romanNumList.get(i - 1)) + convertRoman(romanNumList.get(i - 2));

            }
            res = res + pair;
        }

        return res;
    }

    public final static int calculate(int num1, int num2){
        switch (inputArray[1]){
            case "+" :
                result = num1 + num2;
                break;
            case "-":
                result = num1 - num2;
                break;
            case "*":
                result = num1 * num2;
                break;
            case "/" :
                result = num1 / num2;
                break;
            default: break;
        }
        return result;
    }
}