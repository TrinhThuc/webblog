// app.sendInfOfEachUser(); 

const $ = document.querySelector.bind(document);
const $$ = document.querySelectorAll.bind(document);

const actionList = $('.container__action-list');
const action = $('.container__action');
const actionTitle = $('.container__action span');
const actionItems = $$('.container__action-item');
const confirmBtn = $('.container__confirm');
const refreshBtn = $('.container__refresh-btn');

// pagination variables
const numberOfPaginations = $$('.container__pagination-item');
const paginationList = $('.container__pagination-lists');
const firstBtn = $('.firstBtn');
const prevBtn = $('.prevBtn');
const nextBtn = $('.nextBtn');
const lastBtn = $('.lastBtn');
const paginationBar = $('.container__pagination');

const app = {
    currentIndex: 0,
    currentPage: 0,
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
            app.lastPage();
        }

        //Xu ly khi bam vao nut first page
        firstBtn.onclick = function () {
            app.firstPage();
        }

        //Lang nghe hanh vi click vao pagination list
        paginationList.onclick = function (e) {

            if (!e.target.classList.contains('container__pagination-item')) return;

            const paginationNode = e.target.closest('.container__pagination-item:not(.active)');
            app.currentPage = e.target.dataset.index;
            app.currentIndex = Number(paginationNode.dataset.index);
            app.render();
            // app.render_member();

            // if (paginationNode) {
            //     app.currentIndex = Number(paginationNode.dataset.index);
            //     app.render();
            // }
        }

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

    lastPage: function () {
        this.currentIndex = this.paginations.length - 1;
        this.render();
        // this.render_member();
    },

    firstPage: function () {
        this.currentIndex = 0;
        this.render();
        // this.render_member();
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
        const arr = [];
        allUser.forEach(user => {
            if (user.querySelector('.container__table-input').checked) {
                arr.push(user.getAttribute('id'));
            }
        });
        const obj = {
            arr: arr,
            status: status
        }
        return obj;
    },

    start: function () {
        if (this.Members.length > 10) {
            this.render();
            paginationBar.classList.add('appear-flex');
        }
        //xu ly cac thao tac bang tay
        this.handleEvents();
    }
}

app.start();


