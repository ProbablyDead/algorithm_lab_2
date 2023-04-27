## Задача: нахождение количества пересекающихся прямоугольников в точке

### Три алгоритма реализации: 

#### 1. [Полный перебор](#алгоритм-перебора)
#### 2. [Построение карты](#алгоритм-на-карте)
#### 3. [Построение персистентного дерева отрезков](#алгоритм-на-дереве)

---

### Генерация данных

  - Генерация прямоугольников 

    **Формула:** `{[10*i, 10*i], [10*(2N-i), 10*(2N-i)]}`

  https://github.com/ProbablyDead/algorithm_lab_2/blob/98546d58aee0075d551294138ed03db991e6d889/benchmark/Generator.java#L18-L24

  - Генерация точек 

    **Формула для одной из координат:** `(p*i)^31%(20*N)`
    > Где p - случайное большое простое число   
    > Получение p:
    > https://github.com/ProbablyDead/algorithm_lab_2/blob/98546d58aee0075d551294138ed03db991e6d889/benchmark/Generator.java#L36-L40

    - Получение простых чисел в заданном диапазоне

    https://github.com/ProbablyDead/algorithm_lab_2/blob/98546d58aee0075d551294138ed03db991e6d889/benchmark/Generator.java#L42-L61

    - Генерация точек

  https://github.com/ProbablyDead/algorithm_lab_2/blob/98546d58aee0075d551294138ed03db991e6d889/benchmark/Generator.java#L26-L34

### Алгоритм перебора

  Перебор каждого прямоугольника и определение принадлежности к нему точки

 **Сложность:** подготовка - O(1), поиск - O(N)

https://github.com/ProbablyDead/algorithm_lab_2/blob/8165bf9d053cef19ed174d3da16ebefa9faf907c/algorithms/solution/FirstAlgorithm.java#L37-L46

  > Определение принадлежности
  https://github.com/ProbablyDead/algorithm_lab_2/blob/d97afbfb6c46e2c8002b4b9d70ea0a808f26886c/algorithms/solution/Rectangle.java#L29-L31

### Алгоритм на карте
 
  Построение карты по сжатым координатам и поиск таковых в массиве с помощью бинарного поиска

  **Сложность:** подготовка - O(N^3), поиск - O(log(N))

  - Создание отсортированного массива уникальных сжатых координат

    https://github.com/ProbablyDead/algorithm_lab_2/blob/8165bf9d053cef19ed174d3da16ebefa9faf907c/algorithms/solution/SecondAlgorithm.java#L47-L66

    > Добавление уникального значения
    > https://github.com/ProbablyDead/algorithm_lab_2/blob/8165bf9d053cef19ed174d3da16ebefa9faf907c/algorithms/solution/Rectangle.java#L18-L22

    > Добавление координаты `(x2 + 1, y2 + 1)` нужно для того, чтобы включить правую и верхнюю границы в поиск 

  - Создание карты

    https://github.com/ProbablyDead/algorithm_lab_2/blob/8165bf9d053cef19ed174d3da16ebefa9faf907c/algorithms/solution/SecondAlgorithm.java#L68-L85

  - Поиск количества пересечений

    https://github.com/ProbablyDead/algorithm_lab_2/blob/8165bf9d053cef19ed174d3da16ebefa9faf907c/algorithms/solution/SecondAlgorithm.java#L26-L35

    > Бинарный поиск (используется в следующем алгоритме)
    > https://github.com/ProbablyDead/algorithm_lab_2/blob/8165bf9d053cef19ed174d3da16ebefa9faf907c/algorithms/solution/Point.java#L14-L29

### Алгоритм на дереве


### Замеры и итоговые результаты


### Выводы

