function showNotification(message, type) {
    // Usuń poprzednie powiadomienie jeśli istnieje
    $('.notification').remove();

    // Utwórz nowe powiadomienie
    const notification = $('<div>').addClass('notification').addClass(type).text(message);

    // Dodaj do body
    $('body').append(notification);

    // Pokaż powiadomienie z animacją
    setTimeout(() => {
        notification.addClass('show');
    }, 100);

    // Ukryj powiadomienie po 3 sekundach
    setTimeout(() => {
        notification.removeClass('show');
        setTimeout(() => {
            notification.remove();
        }, 300);
    }, 3000);
}

function removeFromCart(bookId) {
    $.ajax({
        url: `/rest/api/v1/cart/${bookId}`,
        method: 'DELETE',
        contentType: 'application/json',
        success: function(response) {
            // Znajdź i usuń wiersz z tabeli
            $(`div[onclick="removeFromCart(${bookId})"]`).closest('tr').fadeOut(300, function() {
                $(this).remove();

                // Sprawdź czy tabela jest pusta i zaktualizuj widok
                const remainingRows = $('tbody tr:not(:last-child)').length;
                if (remainingRows === 0) {
                    // Jeśli nie ma więcej produktów, pokaż komunikat o pustym koszyku
                    $('table').fadeOut(300, function() {
                        $(this).remove();
                        $('#content').append('<div>Koszyk jest pusty !!</div>');
                    });

                    // Usuń przycisk "Zamów" gdy koszyk jest pusty
                    $('a.button[href="/order"]').fadeOut(300, function() {
                        $(this).remove();
                    });
                } else {
                    // Przelicz sumę koszyka
                    updateCartTotal();
                }
            });

            showNotification('Usunięto z koszyka!', 'success');
        },
        error: function(xhr, status, error) {
            showNotification('Błąd podczas usuwania z koszyka!', 'error');
        }
    });
}

function updateCartTotal() {
    let total = 0;

    // Oblicz nową sumę na podstawie pozostałych wierszy
    $('tbody tr:not(:last-child) .sum').each(function() {
        const sumText = $(this).text().replace(' zł', '').replace(',', '.');
        total += parseFloat(sumText);
    });

    // Zaktualizuj sumę w ostatnim wierszu
    $('tbody tr:last-child td:nth-child(5)').text(total.toFixed(2).replace('.', ',') + ' zł');
}