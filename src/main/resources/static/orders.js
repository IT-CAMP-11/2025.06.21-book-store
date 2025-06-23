function showDetails(event) {
    // Zatrzymujemy propagację (opcjonalnie, jeśli masz inne kliknięcia)
    event.stopPropagation();

    // Znajdujemy kliknięty <tr>
    const clickedRow = event.currentTarget;

    // Szukamy następnego wiersza .details
    const detailsRow = clickedRow.nextElementSibling;

    // Jeśli znaleziony i ma klasę details
    if (detailsRow && detailsRow.classList.contains('details')) {
      if (detailsRow.style.display === 'none' || detailsRow.style.display === '') {
        detailsRow.style.display = 'table-row';
      } else {
        detailsRow.style.display = 'none';
      }
    }
  }