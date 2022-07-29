// Begin
const $ = document.querySelector.bind(document);
const $$ = document.querySelectorAll.bind(document);

// Interface variables
const deleteBtns = $$('.container__table-icon:nth-child(2)');

// EventListener
deleteBtns.forEach(deleteBtn => {
    deleteBtn.onclick = function () {
        const modal = document.querySelector('.modal');
        modal.classList.add('appear-flex');
        console.log(modal);
    }
});