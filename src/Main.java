import functions.*;

public class Main {
    public static void main(String[] args) {
        //создаем функцию f(x) = 2x + 1
        double[] xValues = {-2, -1, 0, 1, 2, 3, 4};
        double[] yValues = {-3, -1, 1, 3, 5, 7, 9};
        double leftX = xValues[0];
        double rightX = xValues[xValues.length - 1];
        TabulatedFunction func = new TabulatedFunction(leftX, rightX, yValues);

        //основная информация
        System.out.println("f(x) = 2x + 1 на [" + leftX + ", " + rightX + "]");
        System.out.println("Точек: " + func.getPointsCount());

        // тестируем значения
        System.out.println("\nТест значений:");
        double[] testPoints = {-4, -2, 0, 2, 4, 6};
        for (double x : testPoints) {
            double y = func.getFunctionValue(x);
            System.out.printf("f(%.1f)=%.1f%n", x, y);
        }

        //меняем точки
        func.setPointY(3, 10);
        func.setPointX(1, -0.7);

        // добавляем и удаляем
        func.addPoint(new FunctionPoint(2.5, 6));
        func.deletePoint(2);

        // финальная проверка
        System.out.println("\nПосле изменений:");
        System.out.println("Точек: " + func.getPointsCount());
        System.out.printf("f(2.5)=%.1f%n", func.getFunctionValue(2.5));
    }
}