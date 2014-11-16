$(document).ready(prueba);
function check_values() {
    if ($("#username").val().length !== 0 && $("#password").val().length !== 0) {
        $("#buttonSubmit").removeClass("hidden");
        $("#lockSubmit").addClass("hidden");
        }else{
            $("#buttonSubmit").addClass("hidden");;
        $("#lockSubmit").removeClass("hidden");
    }

}

