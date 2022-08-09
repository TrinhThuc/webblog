// app.sendInfOfEachUser(); 

const $ = document.querySelector.bind(document);
const $$ = document.querySelectorAll.bind(document);

const actionList = $('.container__action-list');
const action = $('.container__action');
const actionTitle = $('.container__action span');
const actionItems = $$('.container__action-item');
const confirmBtn = $('.container__confirm');
const refreshBtn = $('.container__refresh-btn');

const app = {
    paginations: function() {
        const pages = totalPages.getAttribute('value');
        let pagesArray = [];
        for(let i = 1; i <= pages; ++i) {
            pagesArray.push(i);
        }
        return pagesArray;
    },

    handleEvents: function () {
        action.onclick = function () {
            actionList.classList.toggle('appear-block');
            actionItems.forEach((actionItem, index) => {
                actionItem.onclick = function () {
                    var value = actionItem.innerHTML;
                    actionTitle.innerHTML = value;
                }
            })
        }
        
        confirmBtn.onmouseover = function () {
            setTimeout(() => {
                confirmBtn.style.color = 'white';
            }, 100);
            // confirmBtn.style.color = 'white';
            // console.log(123);
        }
        
        confirmBtn.onmouseout = function () {
            setTimeout(() => {
                confirmBtn.style.color = 'var(--primary-color)';
            }, 100);
        }

        // Xử lý hành vi click vào nút xác nhận
        confirmBtn.onclick = function() {
            app.sendInfOfEachUser();
        }

        refreshBtn.onclick = function () {
            location.reload();
        }
    },
    
    sendInfOfEachUser: function () {
        const allUser = document.querySelectorAll('.container__table-row[id]'); 
        var status = document.querySelector('.container__action span').textContent;
        if (status == 'Duyệt bài') {
            status = 'post';
        }
        if (status == 'Gỡ bài') {
            status = 'remove';
        }
        if (status == 'Xóa bài') {
            status = 'delete';
        }
        const ids = [];
        allUser.forEach(user => {
            if (user.querySelector('.container__table-input').checked) {
                ids.push(user.getAttribute('id'));
            }
        });
        const obj = {
            ids: ids,
            status: status
        }
        return obj;
    },

    start: function () {
        //xu ly cac thao tac bang tay
        this.handleEvents();
    }
}

app.start();


