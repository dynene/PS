import java.io.BufferedReader;
import java.io.InputStreamReader;

/*
240409
BJ10101 삼각형 외우기 - 브4
 */
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int a = Integer.parseInt(in.readLine());
        int b = Integer.parseInt(in.readLine());
        int c = Integer.parseInt(in.readLine());

        if (a + b + c != 180) {
            System.out.println("Error");
        } else {
            if (a == 60 && b == 60 && c == 60) {
                System.out.println("Equilateral");
            } else if (a==b || b==c || a==c) {
                System.out.println("Isosceles");
            } else {
                System.out.println("Scalene");
            }
        }
    }
}
