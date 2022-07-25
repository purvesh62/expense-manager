google.charts.load('current', {'packages': ['corechart']});
google.charts.setOnLoadCallback(drawChart);

let nav_btn = document.getElementById("nav-btn");
let sidebar = document.getElementById("sidebar");
// document.getElementById("selectChart").addEventListener("change", displayGraph);

nav_btn.onclick = function () {
    sidebar.classList.toggle("active");
};

function displayGraph() {
    selectedElement = document.getElementById("selectChart").value;
    switch (selectedElement) {
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

function drawChart() {

    // Create the data table.
    var data = new google.visualization.DataTable();
    data.addColumn('string', 'Topping');
    data.addColumn('number', 'Slices');
    // data.addRows([
    //     ['Mushrooms', 3],
    //     ['Onions', 1],
    //     ['Olives', 1],
    //     ['Zucchini', 1],
    //     ['Pepperoni', 2]
    // ]);
    let arr = []

    // for (const key in expenseDataMap) {
    //     let inner = [];
    //     inner.push(key);
    //     inner.push(expenseDataMap[key]);
    //     arr.push(inner);
    // }
    data.addRows(arr);
    // Set chart options
    var options = {
        'title': 'How Much Pizza I Ate Last Night', 'width': 400, 'height': 300
    };

    // Instantiate and draw our chart, passing in some options.
    // var chart = new google.visualization.PieChart(document.getElementById('chart_div'));
    // analytics.draw(data, options);
}

function drawExpenseLineChart() {

    // Define the chart to be drawn.
    // month, expense
    months = ['1', '2', '3', '4', '5', '6', '7', '7', '9', '10', '11', '12'];
    figureArray = [];
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

    var data = google.visualization.arrayToDataTable(
        figureArray
    );

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

function expensePieChartChart() {
    // document.getElementById(expenseLineChartDiv).hide();
    // document.getElementById(budgetBarChartDiv).hide();
    figureArray = [];
    figureArray.push(["Categories", "Expense"])

    for (const property in monthlyExpenseWithCategoryMap) {
        rowArray = []
        rowArray.push(property);
        rowArray.push(monthlyExpenseWithCategoryMap[property]);
        figureArray.push(rowArray);
    }

    var data = google.visualization.arrayToDataTable(figureArray);

    var options = {
        title: 'Expense made category wise.'
    };

    var chart = new google.visualization.PieChart(document.getElementById('expensePieChartDiv'));

    chart.draw(data, options);
}