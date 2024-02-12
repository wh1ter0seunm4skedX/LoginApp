document.addEventListener('DOMContentLoaded', function() {
    var passwordInput = document.getElementById('password');
    var passwordStrengthDisplay = document.getElementById('password-strength');

    passwordInput.addEventListener('input', function() {
        var strength = checkPasswordStrength(this.value);
        passwordStrengthDisplay.textContent = strength; // Example of updating the UI
        // Adjust this to update your UI based on the password strength
    });
});

function checkPasswordStrength() {
    var strengthBar = document.getElementById("password-strength");
    var password = document.getElementById("password").value;
    var strength = 0;

    if(password.match(/[a-zA-Z0-9][a-zA-Z0-9]+/)) {
        strength += 1;
    }
    if(password.match(/[~<>?]+/)) {
        strength += 1;
    }
    if(password.match(/[!@#$%^&*()]+/)) {
        strength += 1;
    }
    if(password.length > 5) {
        strength += 1;
    }

    switch(strength) {
        case 0:
            strengthBar.className = "";
            break;
        case 1:
            strengthBar.className = "password-strength strength-weak";
            break;
        case 2:
            strengthBar.className = "password-strength strength-medium";
            break;
        case 3:
            strengthBar.className = "password-strength strength-strong";
            break;
        case 4:
            strengthBar.className = "password-strength strength-very-strong";
            break;
    }
}
