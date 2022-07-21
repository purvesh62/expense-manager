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
    if (expenseTitleInput.value) {
        expenseTitleInput.classList.remove('error');

        events.push({
            date: clicked, title: expenseTitleInput.value,
        });

        // localStorage.setItem('events', JSON.stringify(events));
        closeModal(null);
    } else {
        expenseTitleInput.classList.add('error');
    }
}


function deleteEvent() {
    let selectedEvent = events.filter(e => e.expenseDate == dateFormatter(clicked))[0];
    // events = events.filter(e => e.expenseDate !== dateFormatter(clicked));
    // localStorage.setItem('events', JSON.stringify(events));
    fetch('http://localhost:8080/expense?expense_id=' + event.target.id.split('-')[1], {
        method: 'DELETE',
    }).then(response => console.log(response))
        .then(() => {
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
                load(events);
            }
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

//
// 'use strict';
//
// var CalendarList = [];
//
// function CalendarInfo() {
//     this.id = null;
//     this.name = null;
//     this.checked = true;
//     this.color = null;
//     this.bgColor = null;
//     this.borderColor = null;
//     this.dragBgColor = null;
// }
//
// function addCalendar(calendar) {
//     CalendarList.push(calendar);
// }
//
// function findCalendar(id) {
//     var found;
//
//     CalendarList.forEach(function(calendar) {
//         if (calendar.id === id) {
//             found = calendar;
//         }
//     });
//
//     return found || CalendarList[0];
// }
//
// function hexToRGBA(hex) {
//     var radix = 16;
//     var r = parseInt(hex.slice(1, 3), radix),
//         g = parseInt(hex.slice(3, 5), radix),
//         b = parseInt(hex.slice(5, 7), radix),
//         a = parseInt(hex.slice(7, 9), radix) / 255 || 1;
//     var rgba = 'rgba(' + r + ', ' + g + ', ' + b + ', ' + a + ')';
//
//     return rgba;
// }
//
// // (function() {
// //     var calendar;
// //     var id = 0;
// //
// //     calendar = new CalendarInfo();
// //     id += 1;
// //     calendar.id = String(id);
// //     calendar.name = 'Post';
// //     calendar.color = '#624AC0';
// //     calendar.bgColor = '#F0EFF6';
// //     calendar.dragBgColor = '#F0EFF6';
// //     calendar.borderColor = '#F0EFF6';
// //     addCalendar(calendar);
// //
// //     calendar = new CalendarInfo();
// //     id += 1;
// //     calendar.id = String(id);
// //     calendar.name = 'Events';
// //     calendar.color = '#FF8C1A';
// //     calendar.bgColor = '#FDF8F3';
// //     calendar.dragBgColor = '#FDF8F3';
// //     calendar.borderColor = '#FDF8F3';
// //     addCalendar(calendar);
// //
// //     calendar = new CalendarInfo();
// //     id += 1;
// //     calendar.id = String(id);
// //     calendar.name = 'Offer';
// //     calendar.color = '#578E1C';
// //     calendar.bgColor = '#EEF8F0';
// //     calendar.dragBgColor = '#EEF8F0';
// //     calendar.borderColor = '#EEF8F0';
// //     addCalendar(calendar);
// // })();
//
//
//
// (function(window, Calendar) {
//
//     var cal, resizeThrottled;
//     var useCreationPopup = true;
//     var useDetailPopup = true;
//     var datePicker, selectedCalendar;
//
//     cal = new Calendar('#calendar', {
//         defaultView: 'month',
//         useCreationPopup: useCreationPopup,
//         useDetailPopup: useDetailPopup,
//         calendars: CalendarList,
//         template: {
//             milestone: function(model) {
//                 return '<span class="calendar-font-icon ic-milestone-b"></span> <span style="background-color: ' + model.bgColor + '">' + model.title + '</span>';
//             },
//             allday: function(schedule) {
//                 return getTimeTemplate(schedule, true);
//             },
//             time: function(schedule) {
//                 return getTimeTemplate(schedule, false);
//             }
//         }
//     });
//
//     // event handlers
//     cal.on({
//         'clickMore': function(e) {
//             console.log('clickMore', e);
//         },
//         'clickSchedule': function(e) {
//         },
//         'clickDayname': function(date) {
//             console.log('clickDayname', date);
//         },
//         'beforeCreateSchedule': function(e) {
//
//             // $("#create").fadeIn();
//
//             saveNewSchedule(e);
//         },
//         'beforeUpdateSchedule': function(e) {
//             var schedule = e.schedule;
//             var changes = e.changes;
//
//             console.log('beforeUpdateSchedule', e);
//
//             cal.updateSchedule(schedule.id, schedule.calendarId, changes);
//             refreshScheduleVisibility();
//         },
//         'beforeDeleteSchedule': function(e) {
//             console.log('beforeDeleteSchedule', e);
//             cal.deleteSchedule(e.schedule.id, e.schedule.calendarId);
//         },
//         'afterRenderSchedule': function(e) {
//             var schedule = e.schedule;
//             // var element = cal.getElement(schedule.id, schedule.calendarId);
//             // console.log('afterRenderSchedule', element);
//         },
//         'clickTimezonesCollapseBtn': function(timezonesCollapsed) {
//             console.log('timezonesCollapsed', timezonesCollapsed);
//
//             if (timezonesCollapsed) {
//                 cal.setTheme({
//                     'week.daygridLeft.width': '77px',
//                     'week.timegridLeft.width': '77px'
//                 });
//             } else {
//                 cal.setTheme({
//                     'week.daygridLeft.width': '60px',
//                     'week.timegridLeft.width': '60px'
//                 });
//             }
//
//             return true;
//         }
//     });
//
//     function getTimeTemplate(schedule, isAllDay) {
//         var html = [];
//         var start = moment(schedule.start.toUTCString());
//         if (!isAllDay) {
//             html.push('<strong>' + start.format('HH:mm') + '</strong> ');
//         }
//         if (schedule.isPrivate) {
//             html.push('<span class="calendar-font-icon ic-lock-b"></span>');
//             html.push(' Private');
//         } else {
//             if (schedule.isReadOnly) {
//                 html.push('<span class="calendar-font-icon ic-readonly-b"></span>');
//             } else if (schedule.recurrenceRule) {
//                 html.push('<span class="calendar-font-icon ic-repeat-b"></span>');
//             } else if (schedule.attendees.length) {
//                 html.push('<span class="calendar-font-icon ic-user-b"></span>');
//             } else if (schedule.location) {
//                 html.push('<span class="calendar-font-icon ic-location-b"></span>');
//             }
//             html.push(' ' + schedule.title);
//         }
//
//         return html.join('');
//     }
//
//     function onClickNavi(e) {
//         var action = getDataAction(e.target);
//
//         switch (action) {
//             case 'move-prev':
//                 cal.prev();
//                 break;
//             case 'move-next':
//                 cal.next();
//                 break;
//             case 'move-today':
//                 cal.today();
//                 break;
//             default:
//                 return;
//         }
//
//         setRenderRangeText();
//         setSchedules();
//     }
//
//     function onNewSchedule() {
//         var title = $('#new-schedule-title').val();
//         var location = $('#new-schedule-location').val();
//         var isAllDay = document.getElementById('new-schedule-allday').checked;
//         var start = datePicker.getStartDate();
//         var end = datePicker.getEndDate();
//         var calendar = selectedCalendar ? selectedCalendar : CalendarList[0];
//
//         if (!title) {
//             return;
//         }
//
//         console.log('calendar.id ', calendar.id)
//         cal.createSchedules([{
//             id: '1',
//             calendarId: calendar.id,
//             title: title,
//             isAllDay: isAllDay,
//             start: start,
//             end: end,
//             category: isAllDay ? 'allday' : 'time',
//             dueDateClass: '',
//             color: calendar.color,
//             bgColor: calendar.bgColor,
//             dragBgColor: calendar.bgColor,
//             borderColor: calendar.borderColor,
//             raw: {
//                 location: location
//             },
//             state: 'Busy'
//         }]);
//
//         $('#modal-new-schedule').modal('hide');
//     }
//
//     function onChangeNewScheduleCalendar(e) {
//         var target = $(e.target).closest('a[role="menuitem"]')[0];
//         var calendarId = getDataAction(target);
//         changeNewScheduleCalendar(calendarId);
//     }
//
//     function changeNewScheduleCalendar(calendarId) {
//         var calendarNameElement = document.getElementById('calendarName');
//         var calendar = findCalendar(calendarId);
//         var html = [];
//
//         html.push('<span class="calendar-bar" style="background-color: ' + calendar.bgColor + '; border-color:' + calendar.borderColor + ';"></span>');
//         html.push('<span class="calendar-name">' + calendar.name + '</span>');
//
//         calendarNameElement.innerHTML = html.join('');
//
//         selectedCalendar = calendar;
//     }
//
//     function createNewSchedule(event) {
//         var start = event.start ? new Date(event.start.getTime()) : new Date();
//         var end = event.end ? new Date(event.end.getTime()) : moment().add(1, 'hours').toDate();
//
//         if (useCreationPopup) {
//             cal.openCreationPopup({
//                 start: start,
//                 end: end
//             });
//         }
//     }
//     function saveNewSchedule(scheduleData) {
//         console.log('scheduleData ', scheduleData)
//         var calendar = scheduleData.calendar || findCalendar(scheduleData.calendarId);
//         var schedule = {
//             id: '1',
//             title: scheduleData.title,
//             // isAllDay: scheduleData.isAllDay,
//             start: scheduleData.start,
//             end: scheduleData.end,
//             category: 'allday',
//             // category: scheduleData.isAllDay ? 'allday' : 'time',
//             // dueDateClass: '',
//             color: calendar.color,
//             bgColor: calendar.bgColor,
//             dragBgColor: calendar.bgColor,
//             borderColor: calendar.borderColor,
//             location: scheduleData.location,
//             // raw: {
//             //     class: scheduleData.raw['class']
//             // },
//             // state: scheduleData.state
//         };
//         if (calendar) {
//             schedule.calendarId = calendar.id;
//             schedule.color = calendar.color;
//             schedule.bgColor = calendar.bgColor;
//             schedule.borderColor = calendar.borderColor;
//         }
//
//         cal.createSchedules([schedule]);
//
//         refreshScheduleVisibility();
//     }
//
//
//     function refreshScheduleVisibility() {
//         var calendarElements = Array.prototype.slice.call(document.querySelectorAll('#calendarList input'));
//
//         CalendarList.forEach(function(calendar) {
//             cal.toggleSchedules(calendar.id, !calendar.checked, false);
//         });
//
//         cal.render(true);
//
//         calendarElements.forEach(function(input) {
//             var span = input.nextElementSibling;
//             span.style.backgroundColor = input.checked ? span.style.borderColor : 'transparent';
//         });
//     }
//
//
//     function setRenderRangeText() {
//         var renderRange = document.getElementById('renderRange');
//         var options = cal.getOptions();
//         var viewName = cal.getViewName();
//         var html = [];
//         if (viewName === 'day') {
//             html.push(moment(cal.getDate().getTime()).format('MMM YYYY DD'));
//         } else if (viewName === 'month' &&
//             (!options.month.visibleWeeksCount || options.month.visibleWeeksCount > 4)) {
//             html.push(moment(cal.getDate().getTime()).format('MMM YYYY'));
//         } else {
//             html.push(moment(cal.getDateRangeStart().getTime()).format('MMM YYYY DD'));
//             html.push(' ~ ');
//             html.push(moment(cal.getDateRangeEnd().getTime()).format(' MMM DD'));
//         }
//         renderRange.innerHTML = html.join('');
//     }
//
//     function setSchedules() {
//         cal.clear();
//         var schedules = [
//             {id: 489273, title: 'Workout for 2020-04-05', isAllDay: false, start: '2022-03-03T11:30:00+09:00', end: '2022-03-03T12:00:00+09:00', goingDuration: 30, comingDuration: 30, color: '#ffffff', isVisible: true, bgColor: '#69BB2D', dragBgColor: '#69BB2D', borderColor: '#69BB2D', calendarId: '1', category: 'allday', dueDateClass: '', customStyle: 'cursor: default;', isPending: false, isFocused: false, isReadOnly: false, isPrivate: false, location: '', attendees: '', recurrenceRule: '', state: ''},
//             {id: 489273, title: 'Workout for 2020-04-05', isAllDay: false, start: '2022-03-11T11:30:00+09:00', end: '2022-03-11T12:00:00+09:00', goingDuration: 30, comingDuration: 30, color: '#ffffff', isVisible: true, bgColor: '#69BB2D', dragBgColor: '#69BB2D', borderColor: '#69BB2D', calendarId: '2', category: 'allday', dueDateClass: '', customStyle: 'cursor: default;', isPending: false, isFocused: false, isReadOnly: false, isPrivate: false, location: '', attendees: '', recurrenceRule: '', state: ''},
//             {id: 18073, title: 'completed with blocks', isAllDay: false, start: '2022-03-20T09:00:00+09:00', end: '2022-03-20T10:00:00+09:00', color: '#ffffff', isVisible: true, bgColor: '#54B8CC', dragBgColor: '#54B8CC', borderColor: '#54B8CC', calendarId: '1', category: 'allday', dueDateClass: '', customStyle: '', isPending: false, isFocused: false, isReadOnly: false, isPrivate: false, location: '', attendees: '', recurrenceRule: '', state: ''},
//             {id: 18073, title: 'completed with blocks', isAllDay: false, start: '2022-03-25T09:00:00+09:00', end: '2022-03-25T10:00:00+09:00', color: '#ffffff', isVisible: true, bgColor: '#54B8CC', dragBgColor: '#54B8CC', borderColor: '#54B8CC', calendarId: '1', category: 'allday', dueDateClass: '', customStyle: '', isPending: false, isFocused: false, isReadOnly: false, isPrivate: false, location: '', attendees: '', recurrenceRule: '', state: ''},
//             {id: 18073, title: 'completed with blocks', isAllDay: false, start: '2022-01-28T09:00:00+09:00', end: '2022-01-28T10:00:00+09:00', color: '#ffffff', isVisible: true, bgColor: '#54B8CC', dragBgColor: '#54B8CC', borderColor: '#54B8CC', calendarId: '1', category: 'allday', dueDateClass: '', customStyle: '', isPending: false, isFocused: false, isReadOnly: false, isPrivate: false, location: '', attendees: '', recurrenceRule: '', state: ''},
//             {id: 18073, title: 'completed with blocks', isAllDay: false, start: '2022-03-07T09:00:00+09:00', end: '2022-03-07T10:00:00+09:00', color: '#ffffff', isVisible: true, bgColor: '#54B8CC', dragBgColor: '#54B8CC', borderColor: '#54B8CC', calendarId: '1', category: 'allday', dueDateClass: '', customStyle: '', isPending: false, isFocused: false, isReadOnly: false, isPrivate: false, location: '', attendees: '', recurrenceRule: '', state: ''},
//             {id: 18073, title: 'Time Schedule Need BGCOLOR', isAllDay: false, start: '2022-03-28T09:00:00+09:00', end: '2022-03-28T10:00:00+09:00', color: '#ffffff', isVisible: true, bgColor: '#54B8CC', dragBgColor: '#54B8CC', borderColor: '#54B8CC', calendarId: '1', category: 'time', dueDateClass: '', customStyle: '', isPending: false, isFocused: false, isReadOnly: false, isPrivate: false, location: '', attendees: '', recurrenceRule: '', state: ''},
//             {id: 18073, title: 'Time Schedule Need BGCOLOR', isAllDay: false, start: '2022-03-17T09:00:00+09:00', end: '2022-03-17T10:00:00+09:00', color: '#ffffff', isVisible: true, bgColor: '#54B8CC', dragBgColor: '#54B8CC', borderColor: '#54B8CC', calendarId: '3', category: 'time', dueDateClass: '', customStyle: '', isPending: false, isFocused: false, isReadOnly: false, isPrivate: false, location: '', attendees: '', recurrenceRule: '', state: ''}
//         ];
//         // generateSchedule(cal.getViewName(), cal.getDateRangeStart(), cal.getDateRangeEnd());
//         cal.createSchedules(schedules);
//         // cal.createSchedules(schedules);
//         refreshScheduleVisibility();
//     }
//
//     function setExpenses() {
//         cal.clear();
//         var expenses = [
//             {
//                 id: 489273,
//                 title: 'Workout for 2020-04-05',
//                 isAllDay: true,
//                 start: '2022-07-05T11:30:00+09:00',
//                 end: '2022-07-05T12:00:00+09:00',
//                 goingDuration: 30,
//                 comingDuration: 30,
//                 color: '#ffffff',
//                 isVisible: true,
//                 bgColor: '#69BB2D',
//                 dragBgColor: '#69BB2D',
//                 borderColor: '#69BB2D',
//                 calendarId: '1',
//                 category: 'allday',
//                 dueDateClass: '',
//                 customStyle: 'cursor: default;',
//                 isPending: false,
//                 isFocused: false,
//                 sReadOnly: false,
//                 isPrivate: false,
//                 location: '',
//                 attendees: '',
//                 recurrenceRule: '',
//                 state: ''
//             },
//             {id: 489273, title: 'Workout for 2020-04-05', isAllDay: false, start: '2022-07-04T11:30:00+09:00', end: '2022-07-05T12:00:00+09:00', goingDuration: 30, comingDuration: 30, color: '#ffffff', isVisible: true, bgColor: '#69BB2D', dragBgColor: '#69BB2D', borderColor: '#69BB2D', calendarId: '2', category: 'allday', dueDateClass: '', customStyle: 'cursor: default;', isPending: false, isFocused: false, isReadOnly: false, isPrivate: false, location: '', attendees: '', recurrenceRule: '', state: ''},
//             {id: 18073, title: 'completed with blocks', isAllDay: false, start: '2022-03-20T09:00:00+09:00', end: '2022-03-20T10:00:00+09:00', color: '#ffffff', isVisible: true, bgColor: '#54B8CC', dragBgColor: '#54B8CC', borderColor: '#54B8CC', calendarId: '1', category: 'allday', dueDateClass: '', customStyle: '', isPending: false, isFocused: false, isReadOnly: false, isPrivate: false, location: '', attendees: '', recurrenceRule: '', state: ''},
//             {id: 18073, title: 'completed with blocks', isAllDay: false, start: '2022-03-25T09:00:00+09:00', end: '2022-03-25T10:00:00+09:00', color: '#ffffff', isVisible: true, bgColor: '#54B8CC', dragBgColor: '#54B8CC', borderColor: '#54B8CC', calendarId: '1', category: 'allday', dueDateClass: '', customStyle: '', isPending: false, isFocused: false, isReadOnly: false, isPrivate: false, location: '', attendees: '', recurrenceRule: '', state: ''},
//             {id: 18073, title: 'completed with blocks', isAllDay: false, start: '2022-01-28T09:00:00+09:00', end: '2022-01-28T10:00:00+09:00', color: '#ffffff', isVisible: true, bgColor: '#54B8CC', dragBgColor: '#54B8CC', borderColor: '#54B8CC', calendarId: '1', category: 'allday', dueDateClass: '', customStyle: '', isPending: false, isFocused: false, isReadOnly: false, isPrivate: false, location: '', attendees: '', recurrenceRule: '', state: ''},
//             {id: 18073, title: 'completed with blocks', isAllDay: false, start: '2022-03-07T09:00:00+09:00', end: '2022-03-07T10:00:00+09:00', color: '#ffffff', isVisible: true, bgColor: '#54B8CC', dragBgColor: '#54B8CC', borderColor: '#54B8CC', calendarId: '1', category: 'allday', dueDateClass: '', customStyle: '', isPending: false, isFocused: false, isReadOnly: false, isPrivate: false, location: '', attendees: '', recurrenceRule: '', state: ''},
//             {id: 18073, title: 'Time Schedule Need BGCOLOR', isAllDay: false, start: '2022-03-28T09:00:00+09:00', end: '2022-03-28T10:00:00+09:00', color: '#ffffff', isVisible: true, bgColor: '#54B8CC', dragBgColor: '#54B8CC', borderColor: '#54B8CC', calendarId: '1', category: 'time', dueDateClass: '', customStyle: '', isPending: false, isFocused: false, isReadOnly: false, isPrivate: false, location: '', attendees: '', recurrenceRule: '', state: ''},
//             {id: 18073, title: 'Time Schedule Need BGCOLOR', isAllDay: false, start: '2022-03-17T09:00:00+09:00', end: '2022-03-17T10:00:00+09:00', color: '#ffffff', isVisible: true, bgColor: '#54B8CC', dragBgColor: '#54B8CC', borderColor: '#54B8CC', calendarId: '3', category: 'time', dueDateClass: '', customStyle: '', isPending: false, isFocused: false, isReadOnly: false, isPrivate: false, location: '', attendees: '', recurrenceRule: '', state: ''}
//         ];
//         // generateSchedule(cal.getViewName(), cal.getDateRangeStart(), cal.getDateRangeEnd());
//         cal.createSchedules(schedules);
//         // cal.createSchedules(schedules);
//         refreshScheduleVisibility();
//     }
//
//     function setEventListener() {
//         $('#menu-navi').on('click', onClickNavi);
//         $('#range-container').on('click', onClickNavi);
//
//         // $('.dropdown-menu a[role="menuitem"]').on('click', onClickMenu);
//         // $('#lnb-calendars').on('change', onChangeCalendars);
//
//         $('#btn-save-schedule').on('click', onNewSchedule);
//         $('#btn-new-schedule').on('click', createNewSchedule);
//
//         $('#dropdownMenu-calendars-list').on('click', onChangeNewScheduleCalendar);
//
//         window.addEventListener('resize', resizeThrottled);
//     }
//
//     function getDataAction(target) {
//         return target.dataset ? target.dataset.action : target.getAttribute('data-action');
//     }
//
//     resizeThrottled = tui.util.throttle(function() {
//         cal.render();
//     }, 50);
//
//     window.cal = cal;
//
//     // setDropdownCalendarType();
//     setRenderRangeText();
//     setSchedules();
//     setEventListener();
// })(window, tui.Calendar);
//
// // set calendars
// (function() {
//
// })();

