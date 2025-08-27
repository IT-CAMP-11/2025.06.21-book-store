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

function addToCart(bookId) {
    $.ajax({
        url: `/rest/api/v1/cart/${bookId}`,
        method: 'GET',
        contentType: 'application/json',
        success: function(response) {
            showNotification('Dodano do koszyka !!', 'success');
        },
        error: function(xhr, status, error) {
            showNotification('Błąd podczas dodawania do koszyka!', 'error');
        }
    });
}
