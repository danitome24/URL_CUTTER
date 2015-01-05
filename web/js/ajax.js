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

    $('#url').keyup(function () {
        var url = $(this).val();
        if (url.length < 26) {
            $("#shorturl").removeClass('hide');
            $("#SubmitAddUrl").addClass('disabled');
        }
        else {
            $("#shorturl").addClass('hide');
            $("#SubmitAddUrl").removeClass('disabled');
        }
    });
    
    $('input[name=password], input[name=newPass1], input[name=newPass2]').keyup(function(){
        if( !$('#checkpwold').hasClass('hide') && !$('#checkpw').hasClass('hide') && !$('#coincident2').hasClass('hide')){
            $('#submitOldPass').removeClass('disabled');
        }else{
            $('#submitOldPass').addClass('disabled');
        }
    });
    /**
     * Part of ajax
     */
    $('#nomUsuariForm').keyup(function () {
        var name = $(this).val();
        $.get('validateUser.do?form_action=validate', {
            user: name
        }, function (responseText) {
            var isValid = responseText;
            if (isValid === "true") {
                $('#userNameValid').removeClass('hide');
            }
            else {
                $('#userNameValid').addClass('hide');
            }
        });
    });

    $('#old').keyup(function () {
        var password = $(this).val();
        $.get('check.do?form_action=checkOldPass',{
            pass: password
        },function(responseText){
            var isTheSame = responseText;
            if( isTheSame === 'true'){
                $('#oldPassValid').addClass('hide');        
                $('#checkpwold').removeClass('hide');
            }else {
                $('#oldPassValid').removeClass('hide');
                $('#checkpwold').addClass('hide');
            }
        });
    });




});