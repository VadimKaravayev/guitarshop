document.addEventListener("DOMContentLoaded",function(){
    document.getElementById("signUp-submit").onclick=(e)=>{
        e.preventDefault();
        if(validate()){
            document.getElementById("signup-form").submit();
        };
    };
    var username = document.getElementById("firstname");
    var userlastname = document.getElementById("lastname");
    var useremail = document.getElementById("useremail");
    var password = document.getElementById("password1");
    var passwordcomfirm = document.getElementById("password2");

    var name_error = document.getElementById("name_error");
    var lastname_error = document.getElementById("lastname_error");
    var email_error = document.getElementById("email_error");
    var pass_error = document.getElementById("pass_error");
    var passconfirm_error = document.getElementById("passconfirm_error");

    username.addEventListener("change", verifyName);
    userlastname.addEventListener("change", verifyLastName);
    useremail.addEventListener("change", verifyEmail);
    password.addEventListener("change", verifyPassword);

    function validate() {

      if (username.value == "") {
        name_error.innerHTML = "**Fill out name";
        negativeLight(username);
        username.focus();
        return false;
      }

      if (!validateName(username.value)) {
        return false;
      }

      if (userlastname.value == "") {
        lastname_error.innerHTML = "**Fill out last name";
        userlastname.focus();
        return false;
      }

      if (!validateName(userlastname.value)) {
        return false;
      }

      if (useremail.value == "") {
        email_error.innerHTML = "**Fill out email";
        useremail.focus();
        return false;
      }

      if (!validateEmail(useremail.value)) {
        return false;
      }

      if (password.value == "") {
        pass_error.innerHTML = "**Fill out password";
        password.focus();
        return false;
      }

      if (!validatePassword(password.value)) {
        return false;
      }

      if (passwordcomfirm.value == "") {
        passconfirm_error.innerHTML = "**Confirm password";
        return false;
      }

      if (password.value != passwordcomfirm.value) {
        pass_error.innerHTML = "**Passwords don't match";
        return false;
      }
      return true;
    }

    function verifyName() {
      if (username.value != "" && validateName(username.value)) {
        positiveLight(username);
        name_error.innerHTML = "";
      } else {
        negativeLight(username);
        name_error.innerHTML = "**Must start with uppercase";
      }
    }

    function verifyLastName() {
      if (userlastname.value != "" && validateName(userlastname.value)) {
        positiveLight(userlastname);
        lastname_error.innerHTML = "";
      } else {
        negativeLight(userlastname);
        lastname_error.innerHTML = "**Must start with uppercase";
      }
    }

    function verifyEmail() {
      if (useremail.value != "" && validateEmail(useremail.value)) {
        positiveLight(useremail);
        email_error.innerHTML = "";
      } else {
        negativeLight(useremail);
        email_error.innerHTML = "**Email must comply with 'name@mail.com' sample";
      }
    }

    function verifyPassword() {
      if (password.value != "" && validatePassword(password.value)) {
          positiveLight(password);
          pass_error.innerHTML = "";
      } else {
          negativeLight(password);
          pass_error.innerHTML = "Minimum 8 characters, at least one Uppercase letter, one lowercase letter, one number and one special character";
      }
    }


    function positiveLight(field) {
      field.style.borderBottom = "1px solid #dcdcdc";
      field.style.color = "green";
    }

    function negativeLight(field) {
      field.style.borderBottom = "1px solid red";
      field.style.color = "red";
    }


    function validateName(name) {
        var nameRegex = /^([A-Z][a-z]+)((\s|-)[A-Z][a-z]+)?$/;
        return nameRegex.test(name);
    }

    function validateEmail(email) {
        var emailRegex = /^[a-zA-Z0-9.!#$%&’*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/;
        return emailRegex.test(email);
    }

    function validatePassword(password) {
        //Minimum 8 characters, at least one Uppercase letter, one lowercase letter, one number and one special character
        var passwordRegex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[$@$!%*?&])[A-Za-z\d$@$!%*?&]{8,}/;
        return passwordRegex.test(password);
    }

});