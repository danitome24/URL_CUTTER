$(document).ready(function () {

    $('#password').keyup(function () {
        var name = $(this).val();
        var myRegExp = new RegExp("((?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{6,20})");
        if (myRegExp.test(name)) {
            $('#checkpw').removeClass('hide');
            $('#checkpw2').addClass('hide');
        }
        else {
            $('#checkpw').addClass('hide');
            $('#checkpw2').removeClass('hide');
        }
    });

    $('#password2').keyup(function () {

        var pw1 = $('#password').val();
        var pw2 = $('#password2').val();
        if (pw1 !== pw2) {
            $("#coincident").removeClass('hide');
            $("#coincident2").addClass('hide');
        }
        else {
            $("#coincident").addClass('hide');
            $("#coincident2").removeClass('hide');
        }
    });

    /*$('#nomUsuariForm').keyup(function () {
        var name = $(this).val();
        $.get('registre.do?form_action=form', {
            username: name
        }, function (responseText) {
            if (responseText === "repeat") {
                document.getElementById("repeat").innerHTML = responseText;
            }
            else {
                document.getElementById("repeat").innerHTML = responseText;
            }
        });
    });*/

    $('#url').keyup(function () {
        var url = $(this).val();
        if (url.length<26){
            $("#shorturl").removeClass('hide');
            $("#SubmitAddUrl").addClass('disabled');
        }
        else {
            $("#shorturl").addClass('hide');
            $("#SubmitAddUrl").removeClass('disabled');
        }
    });
    
    
    
    

    /*
     $.post('registre.do?form_action=form', {
     password: name
     }, function (responseText) {
     if (responseText==="badpw"){
     document.getElementById("checkpw").innerHTML = responseText;
     }
     else  {
     document.getElementById("checkpw").innerHTML = "OK";
     }      
     });*/
    
    
    
});