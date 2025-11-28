import functions.*;
// переделанный меин 7 Задание
public class Main {
    public static void main(String[] args) {
        //создаем функцию f(x) = 2x + 1
        double[] xValues = {-2, -1, 0, 1, 2, 3, 4};
        double[] yValues = {-3, -1, 1, 3, 5, 7, 9};
        double leftX = xValues[0];
        double rightX = xValues[xValues.length - 1];
        TabulatedFunction linearFunc = new TabulatedFunction(leftX, rightX, yValues);

        // небольшая вводная часть
        System.out.println("f(x) = 2x + 1 на [" + leftX + ", " + rightX + "]");
        System.out.println("Точек: " + linearFunc.getPointsCount());

        // вывод всех точек
        System.out.println("Точки после создания функции:");
        printAllPoints(linearFunc);

        // тестируем значения
        System.out.println("Тест самих значений");
        double[] testPoints = {-4, -2, 0, 2, 4, 6};
        for (double x : testPoints) {
            double y = linearFunc.getFunctionValue(x);
            System.out.println("f(" + x + ") = " + y);
        }

        // меняем точки
        linearFunc.setPointY(3, 10);
        // точки после изменения y
        System.out.println("Точки после setPointY(3, 10):");
        printAllPoints(linearFunc);

        linearFunc.setPointX(1, -0.7);

        // вывод точки после изменения x
        System.out.println("Точки после setPointX(1, -0.7):");
        printAllPoints(linearFunc);

        // добавляем и удаляем
        linearFunc.addPoint(new FunctionPoint(2.5, 6));

        // точки после добавления
        System.out.println("Точки после addPoint(2.5, 6):");
        printAllPoints(linearFunc);

        linearFunc.deletePoint(2);
        // точки после удаления
        System.out.println("Точки после deletePoint(2):");
        printAllPoints(linearFunc);

        // финальная проверка
        System.out.println("Финальный результат:");
        System.out.println("Всего точек:" + linearFunc.getPointsCount());
        System.out.printf("f(2.5)=" + linearFunc.getFunctionValue(2.5));
    }

    // метод для вывода всех точек через геттеры
    public static void printAllPoints(TabulatedFunction linearFunc) {
        for (int i = 0; i < linearFunc.getPointsCount(); i++) {
            double x = linearFunc.getPointX(i);  // геттер для x
            double y = linearFunc.getPointY(i);  // геттер для y
            System.out.println("Точка "+ i +":" + "(" +x + "," + y +")");
        }
    }
}