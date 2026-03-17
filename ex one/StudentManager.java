import java.util.ArrayList;
import java.util.Scanner;

class Student {
    String mssv;
    String name;
    double gpa;

    public Student(String mssv, String name, double gpa) {
        this.mssv = mssv;
        this.name = name;
        this.gpa = gpa;
    }

    @Override
    public String toString() {
        return "MSSV: " + mssv + " | Ten: " + name + " | GPA: " + gpa;
    }
}

public class StudentManager {
    public static void main(String[] args) {
        ArrayList<Student> students = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- QUAN LY SINH VIEN ---");
            System.out.println("1. Them sinh vien");
            System.out.println("2. Hien thi danh sach");
            System.out.println("3. Tim kiem theo ten");
            System.out.println("4. Xoa theo MSSV");
            System.out.println("5. Thoat");
            System.out.print("Chon cac chuc nang (1-5): ");
            
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Nhap MSSV: ");
                    String mssv = scanner.nextLine();
                    System.out.print("Nhap Ten: ");
                    String name = scanner.nextLine();
                    System.out.print("Nhap GPA: ");
                    double gpa = scanner.nextDouble();
                    students.add(new Student(mssv, name, gpa));
                    System.out.println("=> Them sinh vien thanh cong!");
                    break;
                case 2:
                    System.out.println("\n--- DANH SACH SINH VIEN ---");
                    if (students.isEmpty()) {
                        System.out.println("Danh sach trong.");
                    } else {
                        for (Student s : students) {
                            System.out.println(s);
                        }
                    }
                    break;
                case 3:
                    System.out.print("Nhap ten sinh vien can tim: ");
                    String searchName = scanner.nextLine();
                    boolean found = false;
                    for (Student s : students) {
                        if (s.name.toLowerCase().contains(searchName.toLowerCase())) {
                            System.out.println(s);
                            found = true;
                        }
                    }
                    if (!found) System.out.println("=> Không thay sinh vien nao ten: " + searchName);
                    break;
                case 4:
                    System.out.print("Nhap MSSV can xoa: ");
                    String deleteMssv = scanner.nextLine();
                    boolean removed = students.removeIf(s -> s.mssv.equals(deleteMssv));
                    if (removed) {
                        System.out.println("=> Đa xoa sinh vien  MSSV: " + deleteMssv);
                    } else {
                        System.out.println("=> Khong tim thay MSSV nay.");
                    }
                    break;
                case 5:
                    System.out.println("Thoat chuong trinh.");
                    scanner.close();
                    return;
                default:
                    System.out.println("khong hop le. vui long thu lai");
            }
        }
    }
}