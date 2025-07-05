# Wyrażenia regularne (regex) – opis i przykłady

## Czym są wyrażenia regularne?

Wyrażenia regularne (ang. regular expressions, regex) to specjalny język służący do wyszukiwania, dopasowywania i manipulowania tekstem na podstawie określonych wzorców. Są wykorzystywane w programowaniu, edytorach tekstu, narzędziach do przetwarzania tekstu i wielu innych miejscach.

## Podstawowe elementy składni

- `.` – dowolny pojedynczy znak
- `*` – zero lub więcej powtórzeń poprzedniego znaku
- `+` – jedno lub więcej powtórzeń poprzedniego znaku
- `?` – zero lub jedno wystąpienie poprzedniego znaku
- `[]` – dowolny znak z podanego zakresu, np. `[a-z]`
- `[^]` – dowolny znak spoza podanego zakresu, np. `[^0-9]`
- `^` – początek linii
- `$` – koniec linii
- `\d` – cyfra (`[0-9]`)
- `\w` – znak słowa (litera, cyfra lub podkreślenie)
- `\s` – biały znak (spacja, tabulator itp.)
- `|` – alternatywa (lub)
- `()` – grupowanie

## Przykłady użycia

### 1. Sprawdzenie, czy tekst jest liczbą

```
^\d+$
```
Dopasuje cały ciąg, jeśli składa się wyłącznie z cyfr.

### 2. Walidacja adresu e-mail

```
^[\w\.-]+@[\w\.-]+\.\w{2,}$
```
Dopasuje prosty adres e-mail.

### 3. Wyszukiwanie numeru telefonu

```
\d{3}-\d{3}-\d{3}
```
Dopasuje numer w formacie 123-456-789.

### 4. Zamiana wszystkich spacji na podkreślenia (w języku Java)

```java
String wynik = tekst.replaceAll("\\s", "_");
```

### 5. Wyodrębnianie daty z tekstu

```
\d{4}-\d{2}-\d{2}
```
Dopasuje datę w formacie RRRR-MM-DD.

## Praktyczne wskazówki

- Używaj narzędzi online do testowania regexów, np. [regex101.com](https://regex101.com/)
- W językach programowania znaki `\` należy często podwajać, np. `\\d`
- Wyrażenia regularne są bardzo wydajne, ale mogą być trudne do czytania – warto je komentować

## Podsumowanie

Wyrażenia regularne to potężne narzędzie do pracy z tekstem. Pozwalają szybko wyszukiwać, walidować i przetwarzać dane tekstowe według zdefiniowanych wzorców.

