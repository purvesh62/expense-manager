let nav = 0;
let clicked = null;
// let events = localStorage.getItem('events') ? JSON.parse(localStorage.getItem('events')) : [];
let events = localExpenseData;

const calendar = document.getElementById('calendar');
const newEventModal = document.getElementById('newEventModal');
const deleteEventModal = document.getElementById('deleteEventModal');
const backDrop = document.getElementById('modalBackDrop');
const expenseTitleInput = document.getElementById('expenseTitleInput');
const weekdays = ['Sunday', 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday'];

function openModal(date) {
    clicked = date;
    //yyyy-MM-dd
    document.getElementById("expenseDate").value = clicked.split("/")[2] + "-" + clicked.split("/")[0] + "-" + clicked.split("/")[1];

    const eventForDay = events.find(e => e.date === clicked);

    if (eventForDay) {
        document.getElementById('eventText').innerText = eventForDay.title;
        deleteEventModal.style.display = 'block';
    } else {
        newEventModal.style.display = 'block';
    }

    backDrop.style.display = 'block';
}

function dateFormatter(dateString) {
    let m = dateString.split("/")[0];
    let d = dateString.split("/")[1];
    let y = dateString.split("/")[2];
    if (d.length == 1) {
        d = "0" + d;
    }
    if (m.length == 1) {
        m = "0" + m;
    }
    return `${y}-${m}-${d}`;
}

function load(events) {
    const dt = new Date();

    if (nav !== 0) {
        dt.setMonth(new Date().getMonth() + nav);
    }

    const day = dt.getDate();
    const month = dt.getMonth();
    const year = dt.getFullYear();

    const firstDayOfMonth = new Date(year, month, 1);
    const daysInMonth = new Date(year, month + 1, 0).getDate();

    const dateString = firstDayOfMonth.toLocaleDateString('en-us', {
        weekday: 'long', year: 'numeric', month: 'numeric', day: 'numeric',
    });
    const paddingDays = weekdays.indexOf(dateString.split(', ')[0]);

    document.getElementById('monthDisplay').innerText = `${dt.toLocaleDateString('en-us', {month: 'long'})} ${year}`;

    calendar.innerHTML = '';

    for (let i = 1; i <= paddingDays + daysInMonth; i++) {
        const daySquare = document.createElement('div');
        daySquare.classList.add('day');

        const dayString = `${month + 1}/${i - paddingDays}/${year}`;

        if (i > paddingDays) {
            daySquare.innerText = i - paddingDays;
            const eventForDay = events.find(e => e.expenseDate === dateFormatter(dayString));

            if (i - paddingDays === day && nav === 0) {
                daySquare.id = 'currentDay';
            }

            if (eventForDay) {
                const eventDiv = document.createElement('div');
                eventDiv.classList.add('event');
                eventDiv.innerText = eventForDay.expenseTitle;
                daySquare.appendChild(eventDiv);
            }

            daySquare.addEventListener('click', () => openModal(dayString));
        } else {
            daySquare.classList.add('padding');
        }

        calendar.appendChild(daySquare);
    }
}

function closeModal() {
    expenseTitleInput.classList.remove('error');
    newEventModal.style.display = 'none';
    deleteEventModal.style.display = 'none';
    backDrop.style.display = 'none';
    expenseTitleInput.value = '';
    clicked = null;
    load(events);
}


function saveEvent() {
    if (expenseTitleInput.value) {
        expenseTitleInput.classList.remove('error');

        events.push({
            date: clicked, title: expenseTitleInput.value,
        });

        localStorage.setItem('events', JSON.stringify(events));
        closeModal();
    } else {
        expenseTitleInput.classList.add('error');
    }
}

function deleteEvent() {
    events = events.filter(e => e.date !== clicked);
    localStorage.setItem('events', JSON.stringify(events));
    closeModal();
}

function initButtons() {
    document.getElementById('nextButton').addEventListener('click', () => {
        nav++;
        const dt = new Date();

        if (nav !== 0) {
            dt.setMonth(new Date().getMonth() + nav);
        }
        let month = dt.getMonth();
        if (month.toString().length == 1) {
            month = "0" + (month + 1);
        }
        const year = dt.getFullYear();

        const daysInMonth = new Date(year, month + 1, 0).getDate();

        const start_date = `${year}-${month}-01`;
        const end_date = `${year}-${month}-${daysInMonth}`;
        fetch(`http://localhost:8080/expense?user_id=1&start_date=${start_date}&end_date=${end_date}`)
            .then(response => response.json())
            .then(data => load(data));
    });

    document.getElementById('backButton').addEventListener('click', () => {
        nav--;
        const dt = new Date();

        if (nav !== 0) {
            dt.setMonth(new Date().getMonth() + nav);
        }
        let month = dt.getMonth();
        if (month.toString().length == 1) {
            month = "0" + (month + 1);
        }
        const year = dt.getFullYear();

        const daysInMonth = new Date(year, month + 1, 0).getDate();

        const start_date = `${year}-${month}-01`;
        const end_date = `${year}-${month}-${daysInMonth}`;
        fetch(`http://localhost:8080/expense?user_id=1&start_date=${start_date}&end_date=${end_date}`)
            .then(response => response.json())
            .then(data => load(data));
    });

    document.getElementById('saveButton').addEventListener('click', saveEvent);
    document.getElementById('cancelButton').addEventListener('click', closeModal);
    document.getElementById('deleteButton').addEventListener('click', deleteEvent);
    document.getElementById('closeButton').addEventListener('click', closeModal);
}

initButtons();
load(events);
