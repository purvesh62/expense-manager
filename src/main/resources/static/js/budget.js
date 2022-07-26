function minusMonth() {
    // selectedDate = document.getElementById("selectedDate");
    year = selectedDate.split('-')[0];
    month = selectedDate.split('-')[1];
    day = selectedDate.split('-')[2];
    if (parseInt(month) == 1) {
        month = "12";
        year = parseInt(year) - 1;
    } else if (parseInt(month) <= 10) {
        month = parseInt(month) - 1;
        month = "0" + month;
    } else {
        month = parseInt(month) - 1;
    }
    // selectedDate.innerHTML = year + "-" + month + "-" + day;
    window.location.href = "/budget?date=" + year + "-" + month + "-" + day;
}

function plusMonth() {
    // selectedDate = document.getElementById("selectedDate");
    year = selectedDate.split('-')[0];
    month = selectedDate.split('-')[1];
    day = selectedDate.split('-')[2];
    if (parseInt(month) == 12) {
        month = "01";
        year = parseInt(year) + 1;
    } else if (parseInt(month) < 9) {
        month = parseInt(month) + 1;
        month = "0" + month;
    } else {
        month = parseInt(month) + 1;
    }

    window.location.href = "/budget?date=" + year + "-" + month + "-" + day;
    // selectedDate.innerHTML = year + "-" + month + "-" + day;
}