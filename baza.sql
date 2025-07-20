-- Skrypt inicjalizacji bazy danych MySQL
-- Tworzenie tabel zgodnie ze schematem graficznym

-- Usunięcie tabel jeśli istnieją (w odwrotnej kolejności ze względu na klucze obce)
DROP TABLE IF EXISTS tposition;
DROP TABLE IF EXISTS torder;
DROP TABLE IF EXISTS tbook;
DROP TABLE IF EXISTS tuser;

-- Tabela użytkowników
CREATE TABLE tuser (
    id INT(4) NOT NULL AUTO_INCREMENT,
    login VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(33) NOT NULL,
    name VARCHAR(255),
    surname VARCHAR(255),
    age INT,
    role VARCHAR(255),
    PRIMARY KEY (id)
);

-- Tabela książek
CREATE TABLE tbook (
    id INT(4) NOT NULL AUTO_INCREMENT,
    title VARCHAR(255) NOT NULL,
    author VARCHAR(255) NOT NULL,
    isbn VARCHAR(255) NOT NULL UNIQUE,
    price DECIMAL(5,2) NOT NULL,
    quantity INT(3) NOT NULL,
    PRIMARY KEY (id)
);

-- Tabela zamówień
CREATE TABLE torder (
    id INT(5) NOT NULL AUTO_INCREMENT,
    city VARCHAR(255),
    street VARCHAR(255),
    no VARCHAR(255),
    post_code VARCHAR(255),
    phone_number VARCHAR(255),
    user_id INT NOT NULL,
    date DATE,
    price DECIMAL(6,2),
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES tuser(id)
);

-- Tabela pozycji w zamówieniu
CREATE TABLE tposition (
    id INT(4) NOT NULL AUTO_INCREMENT,
    book_id INT NOT NULL,
    quantity INT NOT NULL,
    order_id INT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (book_id) REFERENCES tbook(id),
    FOREIGN KEY (order_id) REFERENCES torder(id)
);

-- Dodanie indeksów dla lepszej wydajności
CREATE INDEX idx_tuser_login ON tuser(login);
CREATE INDEX idx_tbook_isbn ON tbook(isbn);
CREATE INDEX idx_torder_user_id ON torder(user_id);
CREATE INDEX idx_tposition_book_id ON tposition(book_id);
CREATE INDEX idx_tposition_order_id ON tposition(order_id);
