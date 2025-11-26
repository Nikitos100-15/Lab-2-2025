package functions;

/* 3 ЗАДАНИЕ */
public class TabulatedFunction {
    // создаем поле в виде массива
    private FunctionPoint[] points;
    // для 6 задание поле количестов точек
    private int pointsCount;

    // первый конструктор по заданию
    public TabulatedFunction(double leftX, double rightX, int pointsCount) {
        this.points = new FunctionPoint[pointsCount]; // создаем объект для массива точек
        double step = (rightX - leftX) / (pointsCount - 1); // считаем шаг между точками
        for (int i = 0; i < pointsCount; i++) {
            double x_c = leftX + i * step;// считаем координату x
            points[i] = new FunctionPoint(x_c, 0);// записываем наши точки в объект вида массива
        }


    }

    // второй конструктор по заданию
    public TabulatedFunction(double leftX, double rightX, double[] values) {
        this.points = new FunctionPoint[values.length]; // создаем объект для массива точек
        double step = (rightX - leftX) / (values.length - 1); // считаем шаг между точками
        for (int i = 0; i < values.length; i++) {
            double x_c = leftX + i * step; // считаем координату x
            points[i] = new FunctionPoint(x_c, values[i]); // записываем наши точки в объект вида массива
        }

    }

    /* 4 ЗАДАНИЕ */
    // метод для возращения самой левой границы области определения
    public double getLeftDomainBorder() {
        return points[0].getX();
    }

    // метод для возращения самой правой границы области определения
    public double getRightDomainBorder() {
        return points[points.length - 1].getX();
    }

    // метод для получения значение функции в точке x, если эта точка лежит в области определения функции
    public double getFunctionValue(double x) {
        if (x >= points[0].getX() && x <= points[points.length - 1].getX()) // проверка лежит ли эта точки  в области определения функции
        {
            // если лежит то проходим по массиву и используем линейную интерполярию для нахождения  значения
            for (int i = 0; i < points.length - 1; i++) {
                if (x >= points[i].getX() && x <= points[i + 1].getX()) {
                    double x1 = points[i].getX();
                    double y1 = points[i].getY();
                    double x2 = points[i + 1].getX();
                    double y2 = points[i + 1].getY();
                    return y1 + (y2 - y1) * (x - x1) / (x2 - x1);
                }
            }
        } else { // иначе, возвращаем значение неопределённости
            return Double.NaN;
        }
        return Double.NaN; // возвращаем значение неопределённости в случае если массив пуст итд
    }

    /* 5 ЗАДАНИЕ */
    // метод для получения количества точек
    public int getPointsCount() {
        return points.length;
    }

    // метод для получения копии точки
    public FunctionPoint getPoint(int index) {
        return new FunctionPoint(points[index]);
    }

    // метод для получения значения абциссы
    public double getPointX(int index) {
        return points[index].getX();
    }

    // метод для получения значения ординаты
    public double getPointY(int index) {
        return points[index].getY();
    }

    // метод для изменения значения ординаты точки с указанным номером
    public void setPointY(int index, double y) {
        points[index].setY(y);
    }

    // метод для замены точки на табулированную
    public void setPoint(int index, FunctionPoint point) {
        // проверяем что новый х между соседними точками
        if (index > 0 && point.getX() <= points[index - 1].getX())
            return;
        if (index < points.length - 1 && point.getX() >= points[index + 1].getX())
            return;

        points[index] = new FunctionPoint(point);  // сохраняем копию
    }

    // метод для замены абциссы точки с указанным номером
    public void setPointX(int index, double x) {
        // аналогичная проверка как и в SetPoint
        if (index > 0 && x <= points[index - 1].getX()) return;
        if (index < points.length - 1 && x >= points[index + 1].getX()) return;

        points[index].setX(x);
    }

    /* 6 ЗАДАНИЕ */
    // метод для удаления заданной точки
    public void deletePoint(int index) {
        // cдвигаем все точки после удаляемой влево
        for (int i = index; i < points.length - 1; i++) {
            points[i] = points[i + 1];
        }
        pointsCount--;

    }

    public void addPoint(FunctionPoint point) {
        // проверяем, заполнен ли массив
        if (pointsCount >= points.length) {
            // увеличиваем массив в 2 раза
            FunctionPoint[] newPoints = new FunctionPoint[points.length * 2];
            System.arraycopy(points, 0, newPoints, 0, pointsCount);
            points = newPoints;
        }

        // ищем позицию для вставки x
        int pos = 0;
        while (pos < pointsCount && points[pos].getX() < point.getX()) {
            pos++;
        }

        // сдвигаем точки вправо, чтобы освободить место для новой точки
        System.arraycopy(points, pos, points, pos + 1, pointsCount - pos);

        // вставляем новую точку
        points[pos] = new FunctionPoint(point);
        pointsCount++;
    }
}
