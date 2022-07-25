let nav_btn = document.getElementById("nav-btn");
let sidebar = document.getElementById("sidebar");

nav_btn.onclick = function () {
    sidebar.classList.toggle("active");
};