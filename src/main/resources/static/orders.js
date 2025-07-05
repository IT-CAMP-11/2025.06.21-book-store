function showDetails(event) {
    // Zatrzymujemy propagację (opcjonalnie, jeśli masz inne kliknięcia)
    event.stopPropagation();

    // Znajdujemy kliknięty <div>>
    const clickedRow = event.currentTarget;

    // Szukamy następnego wiersza .details
    const detailsRow = clickedRow.querySelector(".details");

    // Jeśli znaleziony i ma klasę details
    if (detailsRow && detailsRow.classList.contains('details')) {
      if (detailsRow.style.display === 'none' || detailsRow.style.display === '') {
        detailsRow.style.display = 'block';
      } else {
        detailsRow.style.display = 'none';
      }
    }
  }