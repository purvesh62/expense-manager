 document.getElementById('backButton').addEventListener('click', () => {
//        nav--;
//        const dt = new Date();
//
//        if (nav !== 0) {
//            dt.setMonth(new Date().getMonth() + nav);
//        }
//        let month = dt.getMonth();
//        if (month.toString().length == 1) {
//            month = "0" + (month + 1);
//        }
//        const year = dt.getFullYear();
//
//        const daysInMonth = new Date(year, month + 1, 0).getDate();
//
//        const start_date = `${year}-${month}-01`;
//        const end_date = `${year}-${month}-${daysInMonth}`;
//        fetch(`http://localhost:8080/expense?user_id=1&start_date=${start_date}&end_date=${end_date}`)
//            .then(response => response.json())
//            .then(data => load(data));
    });