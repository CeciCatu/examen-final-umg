package pantallas;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class Productos {
    // Lista de productos accesible globalmente
    public static List<Producto> productos = Arrays.asList(
            new Producto(
                    "P001",
                    "Laptop",
                    1500.00,
                    5,
                    LocalDate.of(2025, 5, 20)),
            new Producto(
                    "P002",
                    "Teclado",
                    50.00,
                    10,
                    LocalDate.of(2024, 12, 15)),
            new Producto(
                    "P003",
                    "Mouse",
                    25.00,
                    30,
                    LocalDate.of(2024, 11, 30)),
            new Producto(
                    "P004",
                    "Monitor",
                    300.00,
                    7,
                    LocalDate.of(2026, 7, 1)),
            new Producto(
                    "P005",
                    "Impresora",
                    200.00,
                    4,
                    LocalDate.of(2025, 3, 25)));
}
