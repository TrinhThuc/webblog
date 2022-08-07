// Begin
const $ = document.querySelector.bind(document);
const $$ = document.querySelectorAll.bind(document);

// Interface variales
const memberList = $('.container__table');
const memberContainer = $('.container__table-list');
const iconEyes = $$('.fa-solid.fa-eye.container__table-icon');
const iconEdits = $$('.fa-solid.fa-pen.container__table-icon');
const iconDeletes = $$('.fa-solid.fa-trash.container__table-icon');
const addMoreBtn = $('.container__addMore-btn');
const containerWrappers = $$('.container__wrapper');
const filterItems = $$('.container__filter-title');
const filters = $$('.container__filter-item')
const selections = $$('.container__selection-list');
const selectionItems = $$('.container__selection-item');
const refreshBtn = $('.container__refresh-btn');

// modal variables
const modalTables = $$('.modal');
const modalWraps = $$('.modal__wrap');
const modalTableDeletes = $$('.modal__wrap--delete');
const modalTableEdits = $$('.modal__wrap--edit');
const modalTableViews = $$('.modal__wrap--view');

const chooseYess = $$('.modal__choose--yes');
const chooseNos = $$('.modal__choose--no');
const closeBtns = $$('.modal__close');
const confirmBtns = $$('.modal__edit-confirm');
const cancelBtns = $$('.modal__edit-cancel');

// sideBar variables
const sideBarItems = $$('.sideBar__item');

// test variables


const app = {
    visibleUser: 10,
    isActive: false,
    arr: [memberList.innerHTML],

    handleEvents: function () {
        //Lang nghe hanh vi click vao detele btn
        iconDeletes.forEach((iconDelete) => {
            iconDelete.onclick = function () {
                const value = this.closest('.container__table-row');
                app.appearModal(value);
                app.appearDeleteTable(value);
            }
        })

        //Lang nghe hanh vi click vao edit btn
        iconEdits.forEach((iconEdit) => {
            iconEdit.onclick = function () {
                const value = this.closest('.container__table-row');

                app.appearModal(value);
                app.appearEditTable(value);
            }
        })

        // Xử lý khi click vào eye btn
        iconEyes.forEach((iconEye, index) => {
            iconEye.onclick = function () {
                const value = this.closest('.container__table-row');
                app.appearModal(value);
                app.appearEyeTable(value);
            }
        })

        //Lang nghe hanh vi click vao yes or no 
        //trong modal delete
        chooseYess.forEach(chooseYes => {
            chooseYes.onclick = function () {
                app.hideAllTableDeletes();
            }
        })

        chooseNos.forEach(chooseNo => {
            chooseNo.onclick = function () {
                app.hideAllTableDeletes();
            }
        })

        //Lang nghe hanh vi click vao modal de ẩn table
        modalTables.forEach(modalTable => {
            modalTable.onclick = function () {
                app.hideAllTable();
            }
        })

        // Lang nghe hanh vi click vao modal-wrap 
        //de ngan hien tuong nổi bọt
        modalWraps.forEach((modalWrap, index) => {
            modalWrap.onclick = function (e) {
                e.stopPropagation();
            }
        })

        // Lắng nghe hành vi click vào nút 'Thêm mới' 
        // trong danh sách người dùng
        addMoreBtn.onclick = function () {
            const value = this.closest('.container__table-row');
            app.appearModal(value);
            app.appearEditTable(value);
        }

        // Lắng nghe hành vi click vào nút close 
        //trong modal edit
        closeBtns.forEach(closeBtn => {
            closeBtn.onclick = function () {
                app.hideAllTableEdits();
            }
        })

        // Lắng nghe hành vi click vào nút confirm 
        //hoặc cancel trong modal edit
        confirmBtns.forEach(confirmBtn => {
            confirmBtn.onclick = function () {
                // app.sendAllInputChecked(this); // checked
                // app.sendStatusBlock(this);  // checked
                // app.sendRoleInf(this);
            }
        })

        cancelBtns.forEach(cancelBtn => {
            cancelBtn.onclick = function () {
                app.hideAllTableEdits();
            }
        })


        //Lắng nghe hành vi click vào filter
        filters.forEach((filter, index) => {
            filter.onclick = function () {
                document.getElementById(`filter-${index}`).classList.toggle('appear-block');
                selectionItems.forEach((selectionItem, index1) => {
                    selectionItem.onclick = function () {
                        var value = selectionItem.innerHTML;
                        document.getElementById(`title-${index}`).innerHTML = value;
                    }
                })
            }
        })

        // Lắng nghe hành vi click vào refreshBtn
        refreshBtn.onclick = function () {
            location.reload();
        }

    },

    hideDeleteTable: function (value) {
        modalTableDeletes.forEach(modalTableDelete => {
            if (modalTableDelete.dataset.value == value) {
                modalTableDelete.classList.remove('appear-flex');
            }
        })

        modalTables.forEach(modalTable => {
            if (modalTable.dataset.value == value) {
                modalTable.classList.remove('appear-flex');
            }
        })
    },

    hideEditTable: function (value) {
        modalTables.forEach(modalTable => {
            if (modalTable.dataset.value == value) {
                modalTable.classList.remove('appear-flex');
            }
        })
        modalTableEdits.forEach(modalTableEdit => {
            if (modalTableEdit.dataset.value == value) {
                modalTableEdit.classList.remove('appear-flex');
            }
        })
    },

    hideViewTable: function () {
        modalTable.classList.remove('appear-flex');
        modalTableView.classList.remove('appear-block');
    },

    appearModal: function (value) {
        value.querySelector('.modal').classList.add('appear-flex');
    },

    appearDeleteTable: function (value) {
        // Tìm table delete của người được bấm vào
        value.querySelector('.modal__wrap--delete').classList.add('appear-flex');
    },

    appearEditTable: function (value) {
        value.querySelector('.modal__wrap--edit').classList.add('appear-block');
    },

    appearEyeTable: function (value) {
        value.querySelector('.modal__wrap--view').classList.add('appear-block');
    },

    hideAllTableEdits: function () {
        modalTableEdits.forEach(modalTableEdit => {
            modalTableEdit.classList.remove('appear-block');
        })

        modalTables.forEach(modalTable => {
            modalTable.classList.remove('appear-flex');
        })
    },

    hideAllTable: function () {
        // Xóa modal
        modalTables.forEach(modalTable => {
            modalTable.classList.remove('appear-flex');
        })

        // Xóa table edit
        modalTableEdits.forEach(modalTableEdit => {
            modalTableEdit.classList.remove('appear-block');
        })

        // Xóa table delete
        modalTableDeletes.forEach(modalTableDelete => {
            modalTableDelete.classList.remove('appear-flex');
        })

        // Xóa table view
        modalTableViews.forEach(modalTableView => {
            modalTableView.classList.remove('appear-block');
        })
    },

    hideAllTableDeletes: function () {
        modalTables.forEach(modalTable => {
            modalTable.classList.remove('appear-flex');
        })

        modalTableDeletes.forEach(modalTableDelete => {
            modalTableDelete.classList.remove('appear-flex');
        })
    },

    test: function (value) {
        const tests = document.querySelector(`[class='container__table-row'][id='${value}']`);
        tests.querySelector('.modal').classList.add('appear-flex');
    },

    sendAllInputChecked: function (value) {
        const parentEle = value.closest('.modal__edit-role');
        const valueOfInputs = parentEle.querySelectorAll('.modal__role-input');
        const array = [];
        valueOfInputs.forEach(valueOfInput => {
            if (valueOfInput.checked) {
                array.push(valueOfInput.closest('.modal__role-item').querySelector('.modal__role-label').getAttribute('id'));
            }
        })
        console.log(array);
    },

    sendStatusBlock: function (value) {
        const parentEle = value.closest('.modal__wrap--edit');
        const switchBtn = parentEle.querySelector('.switch-input');
        if (switchBtn.checked) {
            return 0;
        }
        else {
            return 1;
        }
    },

    sendRoleInf: function (value) {
        const parentEle = value.closest('.container__table-row');
        const code = parentEle.querySelector('.modal__edit-input[name="code"]').value;
        const name = parentEle.querySelector('.modal__edit-input[name="name"]').value;
        const ID = parentEle.getAttribute('id');
        const obj = {
            id: ID,
            code: code,
            name: name,
        }
        console.log(obj);
        return obj;
    },

    //Ham de bat dau khoi dong chuong trinh
    start: function () {
        //xu ly cac thao tac bang tay
        this.handleEvents();
    }
}

app.start();
