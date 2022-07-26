let nav = 0;
let clicked = null;
// let events = localStorage.getItem('events') ? JSON.parse(localStorage.getItem('events')) : [];
let events = localExpenseData;

const calendar = document.getElementById('calendar');
const newEventModal = document.getElementById('newEventModal');
const deleteEventModal = document.getElementById('deleteEventModal');
const backDrop = document.getElementById('modalBackDrop');
const expenseTitleInput = document.getElementById('expenseTitleInput');
const expenseAmountInput = document.getElementById('expenseAccountNameInput');
const weekdays = ['Sunday', 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday'];

function openModal(date) {
    clicked = date;
    events = localExpenseData;
    document.getElementById("expenseDate").value = clicked.split("/")[2] + "-" + clicked.split("/")[0] + "-" + clicked.split("/")[1];
    if (deleteEventModal.style.display !== "block") {
        newEventModal.style.display = 'block';
        backDrop.style.display = 'block';
    }
    // const eventForDay = events.find(e => e.expenseDate === dateFormatter(clicked));
    //
    // if (!eventForDay) {
    //     document.getElementById('eventText').innerText = eventForDay.expenseTitle;
    //     document.getElementById('eventDescription').innerText = eventForDay.description;
    //     document.getElementById('eventAmount').innerText = eventForDay.amount;
    //     deleteEventModal.style.display = 'block';
    // } else {
    //     newEventModal.style.display = 'block';
    // }
    // newEventModal.style.display = 'block';
    // backDrop.style.display = 'block';
}


function openExpense(date) {
    clicked = date;
    events = localExpenseData;

    document.getElementById("expenseDate").value = clicked.split("/")[2] + "-" + clicked.split("/")[0] + "-" + clicked.split("/")[1];

    // const eventForDay = getEventsOnDay(dateFormatter(clicked));

    let eventForDay = [];
    for (let i = 0; i < events.length; i++) {
        if (events[i].expenseDate == dateFormatter(clicked)) {
            eventForDay.push(events[i]);
        }
    }
    // const eventForDay = events.find(e => e.expenseDate === dateFormatter(clicked));
    // let eventModal = document.getElementById("eventModal")
    //<div class="card">
    //     <div class="card-header" id="headingOne">
    //       <h2 class="mb-0">
    //         <button class="btn btn-link" type="button" data-toggle="collapse" data-target="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
    //           Collapsible Group Item #1
    //         </button>
    //       </h2>
    //     </div>
    // let modelTable = document.getElementById("modalTable");
    // for (let i = 0; i < eventForDay.length; i++) {
    //     let r1 = document.createElement('tr');
    //     let td1 = document.createElement('td');
    //     td1.innerText = 'Title';
    //     let td2 = document.createElement('td');
    //     td2.innerText = eventForDay[i].title;
    //     r1.appendChild(td1);
    //     r1.appendChild(td2);
    //     modelTable.appendChild(r1);
    // }
    // document.body.appendChild(modelTable);
    // modelTable.style.display = 'block';
    if (eventForDay.length > 0) {
        makeTable(eventForDay);
    }
    // document.getElementById('eventText').innerText = eventForDay[0].expenseTitle;
    // document.getElementById('eventDescription').innerText = eventForDay[0].description;
    // document.getElementById('eventAmount').innerText = "$" + eventForDay[0].amount;
    deleteEventModal.style.display = 'block';
    newEventModal.style.display = 'none';
    backDrop.style.display = 'block';
}

function makeTable(eventForDay) {
    var eventModal = document.getElementById("eventModal");
    eventModal.innerHTML = '';
    for (let i = 0; i < eventForDay.length; i++) {
        var div = document.createElement("div");
        div.id = "eventID-" + eventForDay[i].expenseID;
        div.style.marginTop = "15px";
        div.style.marginBottom = "15px";
        var table = document.createElement("table");

        var r1 = document.createElement("tr");
        var r1c1 = document.createElement("td");
        r1c1.appendChild(document.createTextNode("Title"));
        var r1c2 = document.createElement("td");
        r1c2.appendChild(document.createTextNode(eventForDay[i].expenseTitle));
        r1.appendChild(r1c1);
        r1.appendChild(r1c2);

        var r2 = document.createElement('tr');
        var r2c1 = document.createElement('td');
        r2c1.style = "box-shadow: rgba(0, 0, 0, 0.1) 0px 4px 12px;";
        r2.appendChild(r2c1);

        var r3 = document.createElement("tr");
        var r3c1 = document.createElement("td");
        r3c1.appendChild(document.createTextNode("Description"));
        var r3c2 = document.createElement("td");
        r3c2.appendChild(document.createTextNode(eventForDay[i].description));
        r3.appendChild(r3c1);
        r3.appendChild(r3c2);

        var r4 = document.createElement('tr');
        var r4c1 = document.createElement('td');
        r4c1.style = "box-shadow: rgba(0, 0, 0, 0.1) 0px 4px 12px;";
        r4.appendChild(r4c1);

        var r5 = document.createElement("tr");
        var r5c1 = document.createElement("td");
        r5c1.appendChild(document.createTextNode("Amount"));
        var r5c2 = document.createElement("td");
        r5c2.appendChild(document.createTextNode(eventForDay[i].amount));
        r5.appendChild(r5c1);
        r5.appendChild(r5c2);

        table.appendChild(r1);
        table.appendChild(r2);
        table.appendChild(r3);
        table.appendChild(r4);
        table.appendChild(r5);
        div.appendChild(table);

        var btn = document.createElement('button');
        btn.classList.add("deleteButton");
        btn.id = "deleteBtn-" + eventForDay[i].expenseID;
        btn.innerText = "Delete";
        btn.style.marginTop = "5px";
        btn.addEventListener('click', deleteEvent);
        div.appendChild(btn);
        eventModal.appendChild(div);
    }
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
    localExpenseData = events;
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
        daySquare.addEventListener('click', () => openModal(dayString));

        const dayString = `${month + 1}/${i - paddingDays}/${year}`;

        if (i > paddingDays) {
            daySquare.innerText = i - paddingDays;
            // const eventForDay = events.find(e => e.expenseDate === dateFormatter(dayString));
            const eventForDay = getEventsOnDay(events, dateFormatter(dayString));

            if (i - paddingDays === day && nav === 0) {
                daySquare.id = 'currentDay';
            }

            if (eventForDay.length > 0) {
                let totalExpense = 0;
                const eventDiv = document.createElement('div');
                eventDiv.classList.add('event');
                for (let j = 0; j < eventForDay.length; j++) {
                    totalExpense += eventForDay[j].amount;
                }
                eventDiv.addEventListener('click', () => openExpense(dayString));
                daySquare.appendChild(eventDiv);
                eventDiv.innerText = "$ " + totalExpense;
            }
        } else {
            daySquare.classList.add('padding');
        }
        calendar.appendChild(daySquare);
    }
}

function getEventsOnDay(events, dayString) {
    let eventOnDay = [];
    for (let i = 0; i < events.length; i++) {
        if (events[i].expenseDate == dayString) {
            eventOnDay.push(events[i]);
        }
    }
    return eventOnDay;
}

function closeModal(selectedEvent) {
    if (selectedEvent != null && selectedEvent.expenseID !== undefined) {
        var eventModal = document.getElementById("eventModal");
        if (eventModal.childNodes.length > 1) {
            document.getElementById("eventID-" + selectedEvent.expenseID).remove();
            clicked = null;
            load(events);
        } else {
            expenseTitleInput.classList.remove('error');
            newEventModal.style.display = 'none';
            deleteEventModal.style.display = 'none';
            backDrop.style.display = 'none';
            clicked = null;
            load(events);
        }
    } else {
        expenseTitleInput.classList.remove('error');
        newEventModal.style.display = 'none';
        deleteEventModal.style.display = 'none';
        backDrop.style.display = 'none';
        clicked = null;
        load(events);
    }
}


function saveEvent() {
    if (expenseAmountInput.value) {
        if (expenseTitleInput.value) {
            expenseTitleInput.classList.remove('error');

            events.push({
                date: clicked, title: expenseTitleInput.value,
            });
            $.notify("Successfully Added", {position: 'top center', className: "success"});
            closeModal(null);
        } else {
            expenseTitleInput.classList.add('error');
        }
    } else {
        expenseAmountInput.classList.add('error');
    }
}


function deleteEvent() {
    let selectedEvent = events.filter(e => e.expenseDate == dateFormatter(clicked))[0];
    // events = events.filter(e => e.expenseDate !== dateFormatter(clicked));
    // localStorage.setItem('events', JSON.stringify(events));
    fetch('/expense?expense_id=' + event.target.id.split('-')[1], {
        method: 'DELETE',
    }).then((response) => {
        console.log(response);
        document.getElementById(event.target.id).parentNode.remove();
        if (document.getElementById("eventModal").childNodes.length > 1) {
            clicked = null;
            load(events);
        } else {
            expenseTitleInput.classList.remove('error');
            newEventModal.style.display = 'none';
            deleteEventModal.style.display = 'none';
            backDrop.style.display = 'none';
            clicked = null;
            location.reload();
            load(events);
        }
    }).catch(() => {
        location.reload();
    });
    // .then(closeModal(selectedEvent)) // or res.json()
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
        fetch(`/expense?user_id=1&start_date=${start_date}&end_date=${end_date}`)
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
        fetch(`/expense?user_id=1&start_date=${start_date}&end_date=${end_date}`)
            .then(response => response.json())
            .then(data => load(data));
    });

    var eventElement = document.getElementById('event');
    if (eventElement) {
        eventElement.addEventListener('click', () => {
            openExpense(date)
        });
    }


    document.getElementById('saveButton').addEventListener('click', saveEvent);

    var closeButton = document.getElementsByClassName('closeButton');
    for (var i = 0; i < closeButton.length; i++) {
        document.getElementsByClassName('closeButton')[i].addEventListener('click', closeModal);
    }

    // document.getElementsByClassName('deleteButton')[0].addEventListener('click', deleteEvent);
    var deleteButton = document.getElementsByClassName('deleteButton');
    for (var i = 0; i < deleteButton.length; i++) {
        document.getElementsByClassName('deleteButton')[i].addEventListener('click', deleteEvent);
    }
}


initButtons();
load(events);
