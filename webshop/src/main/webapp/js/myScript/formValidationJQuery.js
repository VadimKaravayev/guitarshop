$(function(){
    $("#signUp-submit").on("click",function(e){
        e.preventDefault();
        if(validate()){
            document.getElementById("signup-form").submit();
        };
    });
    var username = $("#firstname");
    var userlastname = $("#lastname");
    var useremail = $("#useremail");
    var password = $("#password1");
    var passwordconfirm = $("#password2");

    var name_error = $("#name_error");
    var lastname_error = $("#lastname_error");
    var email_error = $("#email_error");
    var pass_error = $("#pass_error");
    var passconfirm_error = $("#passconfirm_error");

    username.change(verifyName);
    userlastname.change(verifyLastName);
    useremail.change(verifyEmail);
    password.change(verifyPassword);
    passwordconfirm.change(verifyConfirmpassword);


    function validate() {

        if (isEmptyField(username)) {
            name_error.html("**Fill out name");
            return false;
        }

        if (!validateName(username.val())) {
            return false;
        }

        if (isEmptyField(userlastname)) {
            lastname_error.html("**Fill out last name");
            return false;
        }

        if (!validateName(userlastname.val())) {
            return false;
        }

        if (isEmptyField(useremail)) {
            email_error.html("**Fill out email");
            return false;
        }

        if (!validateEmail(useremail.val())) {
            return false;    
        }

        if (isEmptyField(password)) {
            pass_error.html("**Fill out password");
            return false;
        }

        if (!validatePassword(password.val())) {
            return false;
        }

        if (isEmptyField(passwordconfirm)) {
            passconfirm_error.html("**Confirm password");
        }

        if (password.val() != passwordconfirm.val()) {
            return false;
        }
        return true;
    }

    function isEmptyField(field) {
        if (field.val() == "") {
            field.focus();
            negativeLight(field);
            return true;
        }
    }

    function verifyName() {
      if (username.val() != "" && validateName(username.val())) {
        positiveLight(username);
        name_error.html("");
      } else {
        negativeLight(username);
        name_error.html("Must start with Uppercase, must not contain symbols or digits");
      }
    }

    function verifyLastName() {
      if (userlastname.val() != "" && validateName(userlastname.val())) {
        positiveLight(userlastname);
        lastname_error.html("");
      } else {
        negativeLight(userlastname);
        lastname_error.html("Must start with Uppercase, must not contain symbols or digits");
      }
    }

    function verifyEmail() {
        if (useremail.val() != "" && validateEmail(useremail.val())) {
        positiveLight(useremail);
        email_error.html("");
      } else {
        negativeLight(useremail);
        email_error.html("**Email must comply with 'name@mail.com' sample");
      }
    }


    function verifyPassword() {
        if (password.val() != "" && validatePassword(password.val())) {
            positiveLight(password);
            pass_error.html("");
        } else {
            negativeLight(password);
            pass_error.html("Minimum 8 characters, at least one Uppercase letter, one lowercase letter, one number and one special character");
        }
    }

    function verifyConfirmpassword() {
        if (passwordconfirm.val() != "" && passwordconfirm.val() == password.val()) {
            positiveLight(passwordconfirm);
            passconfirm_error.html("");
        } else {
            negativeLight(passwordconfirm);
            passconfirm_error.html("**Passwords don't match");
        }
    }


    function positiveLight(field) {
        field.css({"border-bottom": "1px solid #dcdcdc", "color": "green"});
    }

    function negativeLight(field) {
        field.css({"border-bottom": "1px solid red", "color": "red"});
    }

    function validateName(name) {
        var nameRegex = /^([A-Z][a-z]+)((\s|-)[A-Z][a-z]+)?$/;
        return name.match(nameRegex);
    }

    function validateEmail(email) {
        var emailRegex = /^[a-zA-Z0-9.!#$%&’*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/;
        return email.match(emailRegex);
    }

    function validatePassword(password) {
        //Minimum eight characters, at least one uppercase letter, one lowercase letter, one number and one //special character
        var passwordRegex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[$@$!%*?&])[A-Za-z\d$@$!%*?&]{8,}/;
        return password.match(passwordRegex);
    }
    
});
