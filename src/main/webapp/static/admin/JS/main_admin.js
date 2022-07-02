const $ = document.querySelector.bind(document);
const $$ = document.querySelectorAll.bind(document);

// Biến của pagination
const numberOfPaginations = $$('.container__pagination-item');
const paginationList = $('.container__pagination-lists');
const firstBtn = $('.firstBtn');
const prevBtn = $('.prevBtn');
const nextBtn = $('.nextBtn');
const lastBtn = $('.lastBtn');
const paginationBar = $('.container__pagination');

// Biến của giao diện
const memberList = $('.container__table');
const memberContainer = $('.container__table-list');
const iconEyes = $$('.container__table-icon:nth-child(1)');
const iconEdits = $$('.container__table-icon:nth-child(2)');
const iconDeletes = $$('.container__table-icon:nth-child(3)');
const addMoreBtn = $('.container__addMore-btn');
const containerWrappers = $$('.container__wrapper');
const filterItems = $$('.container__filter-title');
const filters = $$('.container__filter-item')
const selections = $$('.container__selection-list');
const selectionItems = $$('.container__selection-item');

// Biến của phần modal
const modalTable = $('.modal');
const modalWraps = $$('.modal__wrap');
const modalTableDelete = $('.modal__wrap--delete');
const modalTableEdit = $('.modal__wrap--edit');
const modalTableView = $('.modal__wrap--view');
const chooseYes = $('.modal__choose--yes');
const chooseNo = $('.modal__choose--no');
const closeBtn = $('.modal__close');
const confirmBtn = $('.modal__edit-confirm');
const cancelBtn = $('.modal__edit-cancel');

// Biến phần sideBar
const sideBarItems = $$('.sideBar__item');

const app = {
    currentIndex: 0,
    visibleUser: 10,
    currentPage: 0,
    isActive: false,
    arr: [memberList.innerHTML],

    paginations: [1, 2, 3, 4, 5],

    Members: [
        {
            STT: 1,
            Name: 'Trần Thị Bích Diệp',
            UserName: 'BichDiep1309',
            Role: 'admin',
            UserType: 'Trưởng nhóm truyền thông',
            Team: 'truyền thông',
            Status: 'Online',

        },
        {
            STT: 2,
            Name: 'Trần Thị Bích Diệp',
            UserName: 'BichDiep1309',
            Role: 'admin',
            UserType: 'Trưởng nhóm truyền thông',
            Team: 'truyền thông',
            Status: 'Online',

        },
        {
            STT: 3,
            Name: 'Trần Thị Bích Diệp',
            UserName: 'BichDiep1309',
            Role: 'admin',
            UserType: 'Trưởng nhóm truyền thông',
            Team: 'truyền thông',
            Status: 'Online',

        },
        {
            STT: 4,
            Name: 'Trần Thị Bích Diệp',
            UserName: 'BichDiep1309',
            Role: 'admin',
            UserType: 'Trưởng nhóm truyền thông',
            Team: 'truyền thông',
            Status: 'Online',

        },
        {
            STT: 5,
            Name: 'Trần Thị Bích Diệp',
            UserName: 'BichDiep1309',
            Role: 'admin',
            UserType: 'Trưởng nhóm truyền thông',
            Team: 'truyền thông',
            Status: 'Online',

        },
        {
            STT: 6,
            Name: 'Trần Thị Bích Diệp',
            UserName: 'BichDiep1309',
            Role: 'admin',
            UserType: 'Trưởng nhóm truyền thông',
            Team: 'truyền thông',
            Status: 'Online',

        },
        {
            STT: 7,
            Name: 'Trần Thị Bích Diệp',
            UserName: 'BichDiep1309',
            Role: 'admin',
            UserType: 'Trưởng nhóm truyền thông',
            Team: 'truyền thông',
            Status: 'Online',

        },
        {
            STT: 8,
            Name: 'Trần Thị Bích Diệp',
            UserName: 'BichDiep1309',
            Role: 'admin',
            UserType: 'Trưởng nhóm truyền thông',
            Team: 'truyền thông',
            Status: 'Online',

        },
        {
            STT: 9,
            Name: 'Trần Thị Bích Diệp',
            UserName: 'BichDiep1309',
            Role: 'admin',
            UserType: 'Trưởng nhóm truyền thông',
            Team: 'truyền thông',
            Status: 'Online',

        },
        {
            STT: 10,
            Name: 'Trần Thị Bích Diệp',
            UserName: 'BichDiep1309',
            Role: 'admin',
            UserType: 'Trưởng nhóm truyền thông',
            Team: 'truyền thông',
            Status: 'Online',

        },
        {
            STT: 11,
            Name: 'Trần Thị Bích Diệp',
            UserName: 'BichDiep1309',
            Role: 'admin',
            UserType: 'Trưởng nhóm truyền thông',
            Team: 'truyền thông',
            Status: 'Online',

        },
        {
            STT: 12,
            Name: 'Trần Thị Bích Diệp',
            UserName: 'BichDiep1309',
            Role: 'admin',
            UserType: 'Trưởng nhóm truyền thông',
            Team: 'truyền thông',
            Status: 'Online',

        },
        {
            STT: 13,
            Name: 'Trần Thị Bích Diệp',
            UserName: 'BichDiep1309',
            Role: 'admin',
            UserType: 'Trưởng nhóm truyền thông',
            Team: 'truyền thông',
            Status: 'Online',

        },
    ],

    render_member: function () {
        const visibleUser = this.visibleUser;
        const currentPage = this.currentPage;

        const start = visibleUser * currentPage;

        const displayedMember = this.Members.slice(start, start + visibleUser);

        const htmls_member = displayedMember.map((member) => {
            return `
            <tr class="container__table-row">
                <td>${member.STT}</td>
                <td>${member.Name}</td>
                <td>${member.UserName}</td>
                <td>${member.Role}</td>
                <td>${member.UserType}</td>
                <td>${member.Team}</td>
                <td>${member.Status}</td>
                <td>
                    <i class="fa-solid fa-eye container__table-icon"></i>
                    <i class="fa-solid fa-pen container__table-icon"></i>
                    <i class="fa-solid fa-trash container__table-icon"></i>
                </td>
            </tr>
            `;
        });

        memberList.innerHTML = htmls_member.join('');
        // memberList.innerHTML = htmls_member.join('');
    },

    render: function () {
        const htmls = this.paginations.map((pagination, index) => {
            return `
                <li class="container__pagination-item ${index === this.currentIndex ? 'active' : ''}" data-index="${index}">${pagination}</li>
            `;
        })

        paginationList.innerHTML = htmls.join('');
    },

    handleEvents: function () {
        //Xu ly khi bam vao nut next page
        nextBtn.onclick = function () {
            app.nextPage();
        }

        //Xu ly khi bam vao nut prev page
        prevBtn.onclick = function () {
            app.prevPage();
        }

        //Xu ly khi bam vao nut last page
        lastBtn.onclick = function () {
            app.skip4Page();
        }

        //Xu ly khi bam vao nut first page
        firstBtn.onclick = function () {
            app.prev4Page();
        }

        //Lang nghe hanh vi click vao pagination list
        paginationList.onclick = function (e) {

            if (!e.target.classList.contains('container__pagination-item')) return;

            const paginationNode = e.target.closest('.container__pagination-item:not(.active)');
            app.currentPage = e.target.dataset.index;

            // app.render_member();

            if (paginationNode) {
                app.currentIndex = Number(paginationNode.dataset.index);
                app.render();
            }
        }

        //Lang nghe hanh vi click vao detele btn
        iconDeletes.forEach((iconDelete) => {
            iconDelete.onclick = function () {
                app.appearModal();
                app.appearDeleteTable();
            }
        })

        //Lang nghe hanh vi click vao eye btn
        iconEyes.forEach((iconEye) => {
            iconEye.onclick = function () {
                app.appearModal();
            }
        })

        //Lang nghe hanh vi click vao edit btn
        iconEdits.forEach((iconEdit) => {
            iconEdit.onclick = function () {
                app.appearModal();
                app.appearEditTable();
            }
        })

        //Lang nghe hanh vi click vao yes or no
        //trong modal delete
        chooseYes.onclick = function () {
            app.hideDeleteTable();
        }

        chooseNo.onclick = function () {
            app.hideDeleteTable();
        }

        //Lang nghe hanh vi click vao modal de ẩn table
        modalTable.onclick = function () {
            app.hideDeleteTable();
            app.hideEditTable();
            app.hideViewTable();
        }

        // Lang nghe hanh vi click vao modal-wrap
        //de ngan hien tuong nổi bọt
        modalWraps.forEach((modalWrap, index) => {
            modalWrap.onclick = function (e) {
                e.stopPropagation();
            }
        })

        //Lắng nghe hành vi click vào nút 'Thêm mới'
        //trong danh sách người dùng
        addMoreBtn.onclick = function () {
            app.appearModal();
            app.appearEditTable();
        }

        // Lắng nghe hành vi click vào nút close
        //trong modal edit
        closeBtn.onclick = function () {
            app.hideDeleteTable();
            app.hideEditTable();
        }

        // Lắng nghe hành vi click vào nút confirm
        //hoặc cancel trong modal edit
        confirmBtn.onclick = function () {
            app.hideDeleteTable();
            app.hideEditTable();
        }

        cancelBtn.onclick = function () {
            app.hideDeleteTable();
            app.hideEditTable();
        }

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

        // Xử lý khi click vào icon eye
        iconEyes.forEach((iconEye, index) => {
            iconEye.onclick = function () {
                app.appearModal();
                app.appearEyeTable();
            }
        })
    },

    activeSideBar: function () {

    },

    nextPage: function () {
        this.currentIndex++;
        this.currentPage++;
        if (this.currentIndex >= this.paginations.length) {
            this.currentPage = 0;
            this.currentIndex = 0;
        }

        this.render();
        // this.render_member();
    },

    prevPage: function () {
        this.currentIndex--;
        this.currentPage--;

        if (this.currentIndex < 0) {
            this.currentPage = this.paginations.length - 1;
            this.currentIndex = this.paginations.length - 1;
        }

        this.render();
        // this.render_member();
    },

    skip4Page: function () {
        this.currentIndex += 3;
        this.currentPage += 3;

        if (this.currentIndex >= this.paginations.length) {
            this.currentIndex = this.paginations.length - 1;
            this.currentPage = this.paginations.length - 1;
        }

        this.render();
        // this.render_member();
    },

    prev4Page: function () {
        this.currentIndex -= 3;
        this.currentPage -= 3;

        if (this.currentIndex < 0) {
            this.currentIndex = 0;
            this.currentPage = 0;
        }

        this.render();
        // this.render_member();
    },


    hideDeleteTable: function () {
        modalTable.classList.remove('appear-flex');
        modalTableDelete.classList.remove('appear-flex');
    },

    hideEditTable: function () {
        modalTable.classList.remove('appear-flex');
        modalTableEdit.classList.remove('appear-block');
    },

    hideViewTable: function() {
        modalTable.classList.remove('appear-flex');
        modalTableView.classList.remove('appear-block');
    },

    appearModal: function () {
        modalTable.classList.add('appear-flex');
    },

    appearDeleteTable: function () {
        modalTableDelete.classList.add('appear-flex');
    },

    appearEditTable: function () {
        modalTableEdit.classList.add('appear-block');
    },

    appearEyeTable: function () {
        modalTableView.classList.add('appear-block');
    },

    //Ham de bat dau khoi dong chuong trinh
    start: function () {
        // render cac trang trong list ra html
        // this.render_member();
        if (this.Members.length > 10) {
            this.render();
            paginationBar.classList.add('appear-flex');
        }
        //xu ly cac thao tac bang tay
        this.handleEvents();
    }
}

app.start();
