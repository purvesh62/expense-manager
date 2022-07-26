google.charts.load('current', {'packages': ['corechart']});

let nav_btn = document.getElementById("nav-btn");
let sidebar = document.getElementById("sidebar");
// document.getElementById("selectChart").addEventListener("change", displayGraph);

nav_btn.onclick = function () {
    sidebar.classList.toggle("active");
};

function displayGraph() {
    let selectedElement = document.getElementById("selectChart");

    switch (selectedElement.selectedIndex) {
        case "expenseLineChart": {
            drawExpenseLineChart();
            break;
        }
        case "budgetBarChart": {
            drawBudgetBarChart();
            break;
        }
        case "expensePieChart": {
            expensePieChartChart();
            break;
        }
    }
}

function minusMonth() {
    selectedDate = document.getElementById("selectedDate");
    year = selectedDate.innerHTML.split('-')[0];
    month = selectedDate.innerHTML.split('-')[1];
    day = selectedDate.innerHTML.split('-')[2];
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
    window.location.href = "/analytics?date=" + year + "-" + month + "-" + day;
}

function plusMonth() {
    selectedDate = document.getElementById("selectedDate");
    year = selectedDate.innerHTML.split('-')[0];
    month = selectedDate.innerHTML.split('-')[1];
    day = selectedDate.innerHTML.split('-')[2];
    if (parseInt(month) == 12) {
        month = "01";
        year = parseInt(year) + 1;
    } else if (parseInt(month) < 9) {
        month = parseInt(month) + 1;
        month = "0" + month;
    } else {
        month = parseInt(month) + 1;
    }

    window.location.href = "/analytics?date=" + year + "-" + month + "-" + day;
    // selectedDate.innerHTML = year + "-" + month + "-" + day;
}

function drawExpenseLineChart() {

    // Define the chart to be drawn.
    // month, expense
    months = ['1', '2', '3', '4', '5', '6', '7', '7', '9', '10', '11', '12'];
    let figureArray = [];
    figureArray.push(["Month", "Expense"])
    for (let i = 0; i < months.length; i++) {
        rowArray = [];
        if (months[i] in monthlyExpenseMap) {
            rowArray.push(months[i]);
            rowArray.push(monthlyExpenseMap[months[i]]);
        } else {
            rowArray.push(months[i]);
            rowArray.push(0);
        }
        figureArray.push(rowArray);
    }
    console.log(figureArray);

    var data = google.visualization.arrayToDataTable(figureArray);

    var options = {
        title: 'Total Expenses Made In Year'
    };

    // Instantiate and draw the chart.
    var chart = new google.visualization.BarChart(document.getElementById('expenseLineChartDiv'));
    chart.draw(data, options);


    google.charts.setOnLoadCallback(drawChart);
}

function drawBudgetBarChart() {

}

function createPieChartData() {
    let figureArray = [];
    figureArray.push(["Categories", "Expense"])

    for (const property in monthlyExpenseWithCategoryMap) {
        rowArray = []
        rowArray.push(property);
        rowArray.push(monthlyExpenseWithCategoryMap[property]);
        figureArray.push(rowArray);
    }
    return figureArray;
}

function createLineChartData() {
    let months = ['1', '2', '3', '4', '5', '6', '7', '8', '9', '10', '11', '12'];
    let figureArray = [];
    figureArray.push(["Month", "Expense"])
    for (let i = 0; i < months.length; i++) {
        rowArray = [];
        if (months[i] in monthlyExpenseMap) {
            rowArray.push(months[i]);
            rowArray.push(monthlyExpenseMap[months[i]]);
        } else {
            rowArray.push(months[i]);
            rowArray.push(0);
        }
        figureArray.push(rowArray);
    }
    return figureArray;
}

function generateChart() {
    let selectElement = document.getElementById("selectChart");
    let selectedOption = selectElement.options[selectElement.selectedIndex].value;

    google.charts.setOnLoadCallback(drawChart);

    function drawChart(x, y) {

        // Create LineChart data
        let expenseLineChartData = google.visualization.arrayToDataTable(createLineChartData());

        let expenseLineChartOptions = {
            width: 700, height: 500, legend: "Expense made per month", hAxis: {
                title: "Month(s)", minValue: 30, maxValue: 38
            }, vAxis: {
                title: "Monthly Expense"
            }, pointSize: 0, title: "Title", pointShape: "circle"
        };

        let expensePieChartData = google.visualization.arrayToDataTable(createPieChartData());

        let expensePieChartOptions = {
            width: 700,
            height: 500,
            legend: '',
            pointSize: 0,
            title: 'Expense based on the categories',
            pointShape: 'circle',
            pieHole: 0.4,
            pieSliceText: 'label',
            is3D: true
        }
        let chart;

        if (selectedOption === "expenseLineChart") {
            x = expenseLineChartData;
            y = expenseLineChartOptions;
            chart = new google.visualization.ColumnChart(document.getElementById('chartDiv'));

        } else if (selectedOption === "expensePieChart") {
            x = expensePieChartData;
            y = expensePieChartOptions;
            chart = new google.visualization.PieChart(document.getElementById('chartDiv'));
        }
        chart.draw(x, y);
    }
}