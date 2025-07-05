# Plan rozbudowy aplikacji o funkcjonalność wyszukiwania książek

## Opis funkcjonalności
Dodanie pola wyszukiwania na stronie głównej, które umożliwi filtrowanie książek na podstawie tytułu lub autora.

## Kroki implementacji

### 1. Rozszerzenie interfejsu IBookDAO
- **Plik**: `src/main/java/com/comarch/szkolenia/book/store/dao/IBookDAO.java`
- **Zadanie**: Dodać metodę `List<Book> searchByTitleOrAuthor(String searchTerm)`
- **Uzasadnienie**: Potrzebna jest metoda do wyszukiwania książek według tytułu lub autora

### 2. Implementacja metody wyszukiwania w BookRepository
- **Plik**: `src/main/java/com/comarch/szkolenia/book/store/dao/impl/memory/BookRepository.java`
- **Zadanie**: Zaimplementować metodę `searchByTitleOrAuthor(String searchTerm)`
- **Implementacja**: Przeszukiwanie listy książek, sprawdzanie czy tytuł lub autor zawiera wyszukiwaną frazę (ignorowanie wielkości liter)

### 3. Modyfikacja CommonController
- **Plik**: `src/main/java/com/comarch/szkolenia/book/store/controllers/CommonController.java`
- **Zadania**:
  - Dodać parametr `@RequestParam(required = false) String search` do metody `main()`
  - Dodać logikę sprawdzającą czy parametr search jest pusty
  - Jeśli search nie jest pusty, użyć metody `searchByTitleOrAuthor()` zamiast `getAll()`
  - Przekazać parametr search do modelu, aby pole wyszukiwania zachowało wartość

### 4. Modyfikacja szablonu main.html
- **Plik**: `src/main/resources/templates/main.html`
- **Zadania**:
  - Dodać formularz wyszukiwania po menu, przed div#content
  - Formularz ma zawierać pole tekstowe i przycisk "Szukaj"
  - Formularz ma używać metody GET i kierować na `/main`
  - Pole tekstowe ma zachowywać wpisaną wartość po wyszukiwaniu

### 5. Stylowanie formularza wyszukiwania
- **Plik**: `src/main/resources/static/main.css`
- **Zadanie**: Dodać style CSS dla formularza wyszukiwania
- **Implementacja**: Stylowanie pola tekstowego i przycisku, aby były spójne z resztą aplikacji

## Szczegóły techniczne

### Nowa metoda w IBookDAO:
```java
List<Book> searchByTitleOrAuthor(String searchTerm);
```

### Implementacja w BookRepository:
```java
@Override
public List<Book> searchByTitleOrAuthor(String searchTerm) {
    if (searchTerm == null || searchTerm.trim().isEmpty()) {
        return getAll();
    }
    
    List<Book> result = new ArrayList<>();
    String lowerSearchTerm = searchTerm.toLowerCase();
    
    for (Book book : this.books) {
        if (book.getTitle().toLowerCase().contains(lowerSearchTerm) || 
            book.getAuthor().toLowerCase().contains(lowerSearchTerm)) {
            result.add(book);
        }
    }
    
    return result;
}
```

### Modyfikacja metody main() w CommonController:
```java
@GetMapping({"/main", "/", "/index"})
public String main(Model model, @RequestParam(required = false) String search) {
    List<Book> books;
    
    if (search != null && !search.trim().isEmpty()) {
        books = this.bookDAO.searchByTitleOrAuthor(search);
    } else {
        books = this.bookDAO.getAll();
    }
    
    model.addAttribute("books", books);
    model.addAttribute("search", search != null ? search : "");
    return "main";
}
```

### Formularz wyszukiwania w main.html:
```html
<div class="search-form">
    <form method="get" action="/main">
        <input type="text" name="search" placeholder="Wyszukaj książkę..." 
               th:value="${search}" class="search-field">
        <button type="submit" class="search-button">Szukaj</button>
    </form>
</div>
```

### Style CSS w main.css:
```css
.search-form {
    margin: 20px 0;
    text-align: center;
}

.search-field {
    padding: 10px;
    width: 300px;
    border: 1px solid #29A6A8;
    border-radius: 5px;
    margin-right: 10px;
}

.search-button {
    padding: 10px 20px;
    background-color: #29A6A8;
    color: white;
    border: none;
    border-radius: 5px;
    cursor: pointer;
}

.search-button:hover {
    background-color: #237d7f;
}
```

## Pliki do modyfikacji:
1. `IBookDAO.java` - dodanie metody searchByTitleOrAuthor
2. `BookRepository.java` - implementacja metody wyszukiwania
3. `CommonController.java` - obsługa parametru search
4. `main.html` - dodanie formularza wyszukiwania
5. `main.css` - stylowanie formularza

## Uwagi:
- Wyszukiwanie będzie case-insensitive (ignoruje wielkość liter)
- Puste lub białe znaki będą traktowane jak brak wyszukiwania
- Pole wyszukiwania zachowa wpisaną wartość po przesłaniu formularza
- Formularz używa metody GET, więc URL będzie zawierał parametr search
- Funkcjonalność będzie dostępna dla wszystkich użytkowników (również niezalogowanych)
