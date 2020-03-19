;
$(document).ready(function () {
    var csrf = $('input[name=_csrf]').val();

    // var arr = [];

    $('body').on('click', '.imgSurv1, .imgSurv2', function (e) {

        var url = $(location).attr("href").match("\/java\/catmash\/mash\/(.*)");
        var mashName = url[1];

        var otherTarget = this.id === $('.imgSurv1')[0].id ? $('.imgSurv2')[0].id : $('.imgSurv1')[0].id;

        $.ajax({
            type: "POST",
            url: mashName + "/vote",
            data: {"target": this.id, "otherTarget": otherTarget, "_csrf": csrf},
            success: function (data) {
                if (data.length != 0) {
                    $('.imgSurv1')[0].src = '/java/catmash/img/' + data[0];
                    $('.imgSurv2')[0].src = '/java/catmash/img/' + data[1];
                    $('.imgSurv1')[0].id = data[2];
                    $('.imgSurv2')[0].id = data[3];
                } else {
                    window.location.href = mashName;
                }
            },
            error: function (data) {
                console.log(data);
                // errorDefault()
            }
        });
    }).on('click', '#addMashButton', function (e) {
        var $fileUpload = $("input[type='file']");
        if (parseInt($fileUpload.get(0).files.length) < 3){
            e.preventDefault(e);
            alert("Вы должны добавить не менее 10 файлов");
        }
    });


});