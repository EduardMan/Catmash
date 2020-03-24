;
$(document).ready(function () {
    var csrf = $('input[name=_csrf]').val();

    $('body').on('click', '.removeMash', function (e) {
        e.preventDefault();
        var currentMash = this.id;
        $.ajax({
            type: "POST",
            url: "removeMash",
            data: {"currentMash": currentMash, "_csrf": csrf},
            success: function (data) {
                if (data) {
                    $('#mash_' + currentMash).remove();
                } else {
                    window.location.href = mashName;
                }
            },
            error: function (data) {
                console.log(data);
            }
        })
    });

    // listener for voting
    // when user clock on picture or name of image it will sent to server
    $('.imgTarget1, .imgTarget2, .nameTarget1, .nameTarget2').on('click', function (e) {
        var mashName = $('#mashName').text();
        var servletContextPath = $('#servletContextPath').text();
        var imgTarget1 = $('.imgTarget1')[0];
        var imgTarget2 = $('.imgTarget2')[0];
        var otherTarget = this.id === imgTarget1.id ? imgTarget2.id : imgTarget1.id;
        $.ajax({
            type: "POST",
            url: mashName + "/vote",
            data: {"target": this.id, "otherTarget": otherTarget, "_csrf": csrf},
            success: function (data) {
                // if we do not have more targets just show a Top
                if (data.length !== 0) {
                    imgTarget1.src = servletContextPath + '/img/' + data[0].fileName;
                    imgTarget2.src = servletContextPath + '/img/' + data[1].fileName;
                    imgTarget1.id = data[0].id;
                    imgTarget2.id = data[1].id;
                    $('.nameTarget1')[0].id = data[0].id;
                    $('.nameTarget2')[0].id = data[1].id;
                } else {
                    window.location.href = mashName;
                }
            },
            error: function (data) {
                console.log(data);
            }
        });
    });

    $('#login').on('click', function (e) {
        e.preventDefault();
        window.location.href = "login";
    });

    // Validate and add new mash to mashes list
    $('#addMashButton').on('click', function (e) {
        var $fileUpload = $("input[type='file']");
        var picturesMinCount = $('#buttonAddFiles').attr('picturesMinCount');
        var picturesMaxCount = $('#buttonAddFiles').attr('picturesMaxCount');
        var maxFileSize =  $('#maxFileSize').attr('maxPictureSize');
        var numberOfFiles = parseInt($fileUpload.get(0).files.length);
        for (i = 0; i < numberOfFiles; i++) {
            if ($fileUpload.get(0).files[i].size/1024 > maxFileSize) {
                e.preventDefault(e);
                alert("Размер каждого файла не должен превышать " + maxFileSize + " kb");
                break;
            }
        }
        if (numberOfFiles < picturesMinCount) {
            e.preventDefault(e);
            alert("Вы должны добавить не менее " + picturesMinCount + " файлов");
        } else if (numberOfFiles > picturesMaxCount) {
            e.preventDefault(e);
            alert("Максимальное количество файлов " + picturesMaxCount);
        } else if (numberOfFiles % 2 !== 0) {
            e.preventDefault(e);
            alert("Количество файлов должно быть четным");
        }
    })
});