# Dokumentacja projektu Book Store

## Technologie użyte w projekcie

### Backend
- **Java 23** – główny język programowania aplikacji.
- **Spring Boot 3.5.3** – framework do budowy aplikacji webowych, obsługa kontrolerów, wstrzykiwanie zależności, konfiguracja aplikacji.
- **Spring Boot Starter Web** – obsługa żądań HTTP, REST, serwowanie zasobów statycznych.
- **Spring Boot Starter Thymeleaf** – integracja z silnikiem szablonów Thymeleaf do generowania widoków HTML.
- **Lombok** – automatyzacja generowania getterów, setterów, konstruktorów i innych elementów klas modelowych.

### Frontend
- **HTML5 + CSS3** – szablony widoków (Thymeleaf) oraz style w katalogu static.
- **JavaScript** – obsługa dynamicznych elementów na stronie (np. szczegóły zamówień w orders.js).
- **Thymeleaf** – silnik szablonów do generowania dynamicznych stron HTML po stronie serwera.

### Inne
- **Maven** – system budowania i zarządzania zależnościami.

## Funkcjonalności projektu

1. **Rejestracja użytkownika**
   - Formularz rejestracyjny z walidacją haseł i unikalności loginu.
   - Hasło przechowywane w postaci skrótu MD5.
   - Nowy użytkownik otrzymuje domyślną rolę USER.

2. **Logowanie użytkownika**
   - Formularz logowania.
   - Weryfikacja loginu i hasła (MD5).
   - Obsługa sesji użytkownika.

3. **Wylogowanie użytkownika**
   - Usuwanie sesji użytkownika.

4. **Przeglądanie książek**
   - Strona główna z listą dostępnych książek (tytuł, autor, ISBN, cena, ilość).
   - Możliwość dodania książki do koszyka (dla zalogowanych użytkowników).

5. **Koszyk**
   - Przeglądanie zawartości koszyka (tytuł, autor, ilość, cena jednostkowa, suma).
   - Obliczanie sumy zamówienia.
   - Możliwość przejścia do zamówienia.

6. **Zamówienia**
   - Formularz składania zamówienia (adres, telefon, itp.).
   - Walidacja ilości książek (czy są dostępne na stanie).
   - Zapis zamówienia do pamięci.
   - Przeglądanie zamówień (dla użytkownika oraz administratora).
   - Szczegóły zamówienia (frontend: rozwijane szczegóły zamówienia przez JS).

7. **Panel administratora**
   - Dostęp do wszystkich zamówień.
   - Możliwość przeglądania użytkowników.

8. **Statyczne strony informacyjne**
   - Strona kontaktowa.
   - Fragmenty HTML (logo, menu) wspólne dla wszystkich widoków.

## Struktura katalogów
- `src/main/java` – kod źródłowy aplikacji (modele, kontrolery, DAO, repozytoria, konfiguracja).
- `src/main/resources/static` – pliki statyczne (CSS, JS, grafiki).
- `src/main/resources/templates` – szablony HTML (Thymeleaf).

## Uwagi
- Dane (książki, użytkownicy, zamówienia) przechowywane są w pamięci (implementacje repozytoriów w pakiecie `dao.impl.memory`).
- Projekt nie korzysta z bazy danych.
- Hasła użytkowników są haszowane (MD5) – rozwiązanie niezalecane w produkcji.

---
Dokumentacja wygenerowana automatycznie na podstawie kodu źródłowego i konfiguracji projektu.

