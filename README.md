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


### Алгоритм на карте


### Алгоритм на дереве

