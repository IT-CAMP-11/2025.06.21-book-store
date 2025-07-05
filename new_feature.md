# Plan rozbudowy aplikacji o funkcjonalność usuwania pozycji z koszyka

## Opis funkcjonalności
Dodanie możliwości usuwania pozycji z koszyka poprzez przycisk usuwania przy każdej pozycji w tabeli koszyka.

## Kroki implementacji

### 1. Modyfikacja modelu CartPosition
- **Plik**: `src/main/java/com/comarch/szkolenia/book/store/model/view/CartPosition.java`
- **Zadanie**: Dodać pole `bookId` do klasy CartPosition
- **Uzasadnienie**: Potrzebny jest identyfikator książki do usunięcia pozycji z koszyka

### 2. Modyfikacja CartController
- **Plik**: `src/main/java/com/comarch/szkolenia/book/store/controllers/CartController.java`
- **Zadania**:
  - Zmodyfikować metodę `cart()` aby przekazywać `bookId` do obiektu `CartPosition`
  - Dodać nową metodę `@GetMapping("/cart/remove/{bookId}")` do usuwania pozycji
  - Nowa metoda ma usuwać pozycję z mapy `cart.getPositions()` i przekierowywać na `/cart`

### 3. Rozszerzenie klasy Cart
- **Plik**: `src/main/java/com/comarch/szkolenia/book/store/session/Cart.java`
- **Zadanie**: Dodać metodę `removePosition(int bookId)` do usuwania pozycji z koszyka
- **Implementacja**: Usunięcie wpisu z mapy `positions` na podstawie `bookId`

### 4. Modyfikacja szablonu cart.html
- **Plik**: `src/main/resources/templates/cart.html`
- **Zadania**:
  - Dodać nową kolumnę "Usuń" w nagłówku tabeli (`<th>Usuń</th>`)
  - Dodać nową komórkę w każdym wierszu z pozycją koszyka (`<td>`) po prawej stronie
  - W nowej komórce umieścić link z obrazkiem delete.png
  - Link ma kierować na `/cart/remove/{bookId}`

### 5. Stylowanie przycisku usuwania
- **Plik**: `src/main/resources/static/cart.css`
- **Zadanie**: Dodać style CSS dla przycisku usuwania
- **Implementacja**: Stylowanie obrazka delete.png jako przycisku (hover, kursor, rozmiar)

## Szczegóły techniczne

### Struktura linku usuwania w cart.html:
```html
<td class="actions">
    <a th:href="@{/cart/remove/{id}(id=${position.bookId})}" class="delete-btn">
        <img src="/delete.png" alt="Usuń" class="delete-icon">
    </a>
</td>
```

### Nowa metoda w CartController:
```java
@GetMapping("/cart/remove/{bookId}")
public String removeFromCart(@PathVariable int bookId) {
    this.cart.removePosition(bookId);
    return "redirect:/cart";
}
```

### Nowa metoda w Cart:
```java
public void removePosition(int bookId) {
    this.positions.remove(bookId);
}
```

## Pliki do modyfikacji:
1. `CartPosition.java` - dodanie pola bookId
2. `CartController.java` - modyfikacja metody cart() i dodanie removeFromCart()
3. `Cart.java` - dodanie metody removePosition()
4. `cart.html` - dodanie kolumny "Usuń" z przyciskiem usuwania
5. `cart.css` - stylowanie przycisku usuwania

## Uwagi:
- Obrazek `delete.png` już istnieje w katalogu `static/`
- Funkcjonalność będzie działać tylko dla zalogowanych użytkowników (zgodnie z obecną logiką aplikacji)
- Po usunięciu pozycji nastąpi odświeżenie strony koszyka
- Jeśli koszyk będzie pusty po usunięciu, wyświetli się komunikat "Koszyk jest pusty !!"
