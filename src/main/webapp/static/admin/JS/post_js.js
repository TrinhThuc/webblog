const $ = document.querySelector.bind(document);
const $$ = document.querySelectorAll.bind(document);

const actionList = $('.container__action-list');
const action = $('.container__action');
const actionTitle = $('.container__action span');
const actionItems = $$('.container__action-item');
const confirmBtn = $('.btn--animation');

action.onclick = function() {
    actionList.classList.toggle('appear-block');
    actionItems.forEach((actionItem, index) => {
        actionItem.onclick = function() {
            var value = actionItem.innerHTML;
            actionTitle.innerHTML = value;
        }
    })
}

confirmBtn.onmouseover = function() {
    setTimeout(() => {
        confirmBtn.style.color = 'white';
    }, 150);
    // confirmBtn.style.color = 'white';
    // console.log(123);
}

confirmBtn.onmouseout = function() {
    setTimeout(() => {
        confirmBtn.style.color = 'var(--primary-color)';
    }, 150);
    // confirmBtn.style.color = 'white';
    // console.log(123);
}



