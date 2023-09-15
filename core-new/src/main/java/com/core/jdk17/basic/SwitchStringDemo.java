package com.core.jdk17.basic;

//String在 Java SE 7 及更高版本中，
public class SwitchStringDemo {

    public static void main(String[] args) {
        String month = "december"; // any month

        /*switch (month.toLowerCase()) {
            case "january":
                monthNumber = 1;
                break;
            case "february":
                monthNumber = 2;
                break;
            case "march":
                monthNumber = 3;
                break;
            case "april":
                monthNumber = 4;
                break;
            case "may":
                monthNumber = 5;
                break;
            case "june":
                monthNumber = 6;
                break;
            case "july":
                monthNumber = 7;
                break;
            case "august":
                monthNumber = 8;
                break;
            case "september":
                monthNumber = 9;
                break;
            case "october":
                monthNumber = 10;
                break;
            case "november":
                monthNumber = 11;
                break;
            case "december":
                monthNumber = 12;
                break;
            default:
                monthNumber = 0;
                break;
        }*/

        var monthNumber = switch (month.toLowerCase()) {
            case "january" -> 1;
            case "february" -> 2;
            case "march" -> 3;
            case "april" -> 4;
            case "may" -> 5;
            case "june" -> 6;
            case "july" -> 7;
            case "august" -> {
                var i = 5;
                yield (3 + i);
            }
            case "september" -> 9;
            case "october" -> 10;
            case "november" -> 11;
            case "december" -> {
                System.out.println("Q1 - Winter");
                var i = 0;
                yield (i + 112);
            }
            default -> 0;
        };
        System.out.println(monthNumber);


        int quarter = 3; // any value

        String quarterLabel =
                switch (quarter) {
                    case 0 :  yield "Q1 - Winter";
                    case 1 :  yield "Q2 - Spring";
                    case 2, 3:  yield "Q3 - Summer";
                    default: System.out.println("Unknown quarter");
                        yield "Unknown quarter";
                };

    }
}
