document.addEventListener('DOMContentLoaded', function() {
    var logoutButton = document.getElementById('logout-button');
    if (logoutButton) {
        logoutButton.addEventListener('click', function() {
            document.getElementById('logout-form').submit();
        });
    }
});
