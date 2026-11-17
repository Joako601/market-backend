-- Esquema y datos de prueba para market-backend (PostgreSQL).
-- Las tablas y columnas coinciden con las entidades JPA de persistence/entity.
-- Uso:  psql -U postgres -d market -f db/market.sql
-- Es re-ejecutable: borra y vuelve a crear todo.

DROP TABLE IF EXISTS compras_productos;
DROP TABLE IF EXISTS compras;
DROP TABLE IF EXISTS productos;
DROP TABLE IF EXISTS clientes;
DROP TABLE IF EXISTS categorias;

CREATE TABLE categorias (
    id_categoria SERIAL PRIMARY KEY,
    descripcion  VARCHAR(45) NOT NULL,
    estado       BOOLEAN     NOT NULL
);

CREATE TABLE productos (
    id_producto    SERIAL PRIMARY KEY,
    nombre         VARCHAR(45),
    id_categoria   INTEGER NOT NULL REFERENCES categorias (id_categoria),
    codigo_barras  VARCHAR(150),
    precio_venta   INTEGER,
    cantidad_stock INTEGER NOT NULL,
    estado         BOOLEAN
);

CREATE TABLE clientes (
    id                 VARCHAR(20) PRIMARY KEY,
    nombre             VARCHAR(40),
    apellidos          VARCHAR(100),
    celular            VARCHAR(20),
    direccion          VARCHAR(80),
    correo_electronico VARCHAR(70)
);

CREATE TABLE compras (
    id_compra  SERIAL PRIMARY KEY,
    id_cliente VARCHAR(20) NOT NULL REFERENCES clientes (id),
    fecha      TIMESTAMP,
    medio_pago CHAR(1),
    comentario VARCHAR(300),
    estado     CHAR(1)
);

CREATE TABLE compras_productos (
    id_compra   INTEGER NOT NULL REFERENCES compras (id_compra),
    id_producto INTEGER NOT NULL REFERENCES productos (id_producto),
    cantidad    INTEGER,
    total       NUMERIC(16, 2),
    estado      BOOLEAN,
    PRIMARY KEY (id_compra, id_producto)
);

-- ===================== Datos de prueba =====================

INSERT INTO categorias (descripcion, estado) VALUES
    ('Frutas y verduras', TRUE),       -- 1
    ('Pastelería', TRUE),              -- 2
    ('Carnes y pescados', TRUE),       -- 3
    ('Lácteos y huevos', TRUE),        -- 4
    ('Bebidas', TRUE),                 -- 5
    ('Licores', TRUE),                 -- 6
    ('Cuidado personal', TRUE),        -- 7
    ('Despensa', TRUE);                -- 8

INSERT INTO productos (nombre, id_categoria, codigo_barras, precio_venta, cantidad_stock, estado) VALUES
    ('Guayaba', 1, '7029 A42 23', 30, 500, TRUE),            -- 1
    ('Mango', 1, '0316 R56 01', 25, 250, TRUE),              -- 2
    ('Manzana', 1, '7923 T23 19', 40, 130, TRUE),            -- 3
    ('Aguacate', 1, '9322 Q33 02', 20, 98, TRUE),            -- 4
    ('Pan tajado blanco', 2, '1234 R78 37', 45, 89, TRUE),   -- 5
    ('Torta de chocolate', 2, '3465 K90 98', 180, 12, TRUE), -- 6
    ('Pechuga de pollo', 3, '4593 F39 12', 95, 40, TRUE),    -- 7
    ('Filete de salmón', 3, '2367 Q45 71', 230, 15, TRUE),   -- 8
    ('Leche entera', 4, '4598 P43 02', 28, 120, TRUE),       -- 9
    ('Huevos (12 pzas)', 4, '2390 W67 11', 55, 60, TRUE),    -- 10
    ('Coca cola', 5, '7501 C01 55', 22, 300, TRUE),          -- 11
    ('Mirinda', 5, '7501 M02 66', 20, 200, TRUE),            -- 12
    ('Agua natural 1L', 5, '7501 A03 77', 12, 400, TRUE),    -- 13
    ('Tequila reposado', 6, '7502 T01 10', 350, 25, TRUE),   -- 14
    ('Shampoo', 7, '7503 S01 20', 75, 50, TRUE),             -- 15
    ('Arroz 1kg', 8, '7504 R01 30', 32, 150, TRUE);          -- 16

INSERT INTO clientes (id, nombre, apellidos, celular, direccion, correo_electronico) VALUES
    ('4546221', 'Galileo', 'Galilei', '3462257293', 'Cl 1 # 11 - 11', 'gali@leo.com'),
    ('2552243', 'Ada', 'Lovelace', '3101234567', 'Av 2 # 22 - 22', 'ada@lovelace.com'),
    ('983824', 'Alan', 'Turing', '3157890123', 'Cr 3 # 33 - 33', 'alan@turing.com');

INSERT INTO compras (id_cliente, fecha, medio_pago, comentario, estado) VALUES
    ('4546221', '2026-07-16 10:30:00', 'E', 'Compra de contado', 'P'),
    ('2552243', '2026-07-17 18:05:00', 'T', '', 'P');

INSERT INTO compras_productos (id_compra, id_producto, cantidad, total, estado) VALUES
    (1, 1, 3, 90, TRUE),
    (1, 11, 2, 44, TRUE),
    (2, 9, 1, 28, TRUE);
